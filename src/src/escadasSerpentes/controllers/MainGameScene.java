package src.escadasSerpentes.controllers;

import src.escadasSerpentes.*;
import src.escadasSerpentes.creators.*;
import src.escadasSerpentes.drawable.BoardDrawable;
import src.escadasSerpentes.drawable.BoardGridDrawable;
import src.escadasSerpentes.drawable.CardDrawable;
import src.escadasSerpentes.drawable.PlayerDrawable;
import src.escadasSerpentes.drawable.animations.DieRollAnimation;
import src.escadasSerpentes.dto.*;
import src.escadasSerpentes.framework.Scene;
import src.escadasSerpentes.providers.*;
import src.escadasSerpentes.views.MainGameView;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class MainGameScene extends Scene implements IGameStateChangeListener, IUpdateCardListener {
    private final int rows = 10;
    private final int columns = 10;
    private final float specialProbability = 0.16f; // 16% probability of getting a special per space.

    private MainGameView view;

    private final IResourceProvider resourceProvider;
    private final ISpecialCreator specialCreator;
    private final ISpecialDrawableCreator specialDrawableCreator;
    private final IBoardCreator boardCreator;

    private final List<IPlayer> players;
    private GameState state;

    private MainGame game;

    private BoardDrawable boardDrawable;
    private CardDrawable cardDrawable;
    private DieRollAnimation dieRollAnimation;

    private List<PlayerDrawable> playerDrawables;

    public MainGameScene(List<PlayerRowData> players) {
        resourceProvider = ResourceProvider.getInstance();

        SpecialCreator specialCreator = SpecialCreator.getInstance();
        specialCreator.useCreateCardListener(this);
        this.specialCreator = specialCreator;

        SpecialDrawableCreator specialDrawableCreator = SpecialDrawableCreator.getInstance();
        specialDrawableCreator.useResourceProvider(resourceProvider);
        this.specialDrawableCreator = specialDrawableCreator;

        BoardCreator boardCreator = BoardCreator.getInstance();
        boardCreator.useSpecialCreator(specialCreator);
        boardCreator.useSpecialDrawableCreator(specialDrawableCreator);
        boardCreator.useResourceProvider(resourceProvider);
        this.boardCreator = boardCreator;

        Collections.shuffle(players);

        this.players = new ArrayList<>();
        for (int i = 0; i < players.size(); i++) {
            PlayerRowData pdata = players.get(i);
            String name = Objects.equals(pdata.getName(), "") ? "Player " + (i + 1) : pdata.getName();
            this.players.add(new Player(i, name, rows * columns - 1));
        }

        state = GameState.start;
    }

    @Override
    protected void init() {
        getPanel().setBackground(Color.white);

        Pair<Board, BoardDrawable> boardDrawablePair = boardCreator.create(rows, columns, specialProbability);
        Board board = boardDrawablePair.getFirst();
        boardDrawable = boardDrawablePair.getSecond();

        cardDrawable = new CardDrawable(resourceProvider);
        dieRollAnimation = new DieRollAnimation(resourceProvider);

        view = new MainGameView(getPanel(), resourceProvider, this);

        Die die = new Die();
        game = new MainGame(players, board, die);

        playerDrawables = new ArrayList<>();
        for (IPlayer player : players) {
            playerDrawables.add(new PlayerDrawable(player, resourceProvider));
        }
    }

    @Override
    public void update(double deltaTime) {
        // Offset put on x-axis to center the board.
        float offsetX = (getPanel().getWidth() - boardDrawable.getSize().getFirst()) / 2;
        float offsetY = 50;

        var size = getPanel().getSize();
        size = new Dimension(size.width, size.height - 2 * (int) offsetY);

        boardDrawable.setSize(size);
        boardDrawable.setPosition(offsetX, offsetY);

        Card card = game.getCard();
        cardDrawable.setCard(card);
        cardDrawable.setPosition(getPanel().getWidth() / 2f, getPanel().getHeight() / 2f);

        dieRollAnimation.setPosition(80, getPanel().getHeight() - 80);
        dieRollAnimation.update(deltaTime);

        for (int i = 0; i < playerDrawables.size(); i++) {
            PlayerDrawable playerDrawable = playerDrawables.get(i);
            IPlayer player = players.get(i);
            Pair<Float, Float> position = computePlayerDrawablePosition(player.getPosition(), player.getID(), players.size());

            BoardGridDrawable grid = boardDrawable.getBoardGrid();
            float scale = grid.computeSpaceSideLength() / playerDrawable.getSize().getSecond();
            scale = scale / 1.5f;

            playerDrawable.setPosition(position.getFirst() + offsetX, position.getSecond() + offsetY);
            playerDrawable.setScale(scale, scale);
        }
    }

    @Override
    public void draw(Graphics2D graphics2D) {
        boardDrawable.draw(graphics2D);

        for (PlayerDrawable playerDrawable : playerDrawables) {
            playerDrawable.drawCentered(graphics2D);
        }

        dieRollAnimation.drawCentered(graphics2D);
        cardDrawable.drawCentered(graphics2D);
    }

    private Pair<Float, Float> computePlayerDrawablePosition(int playerPosition, int playerId, int totalPlayers) {
        BoardGridDrawable grid = boardDrawable.getBoardGrid();

        int playersPerSide = (int) Math.ceil(Math.sqrt(totalPlayers));
        float sideLength = grid.computeSpaceSideLength();
        float increment = sideLength / playersPerSide;

        int xIndex = playerId % playersPerSide;
        int yIndex = playerId / playersPerSide;
        float xIncrement = increment * (xIndex + 0.5f);
        float yIncrement = increment * (yIndex + 0.5f);

        // The increments make the positions for each player always
        // a little different than the other players positions so they
        // don't overlap each other when in the same space.
        Pair<Float, Float> result = grid.computeSpacePosition(playerPosition);
        result.setFirst(result.getFirst() + xIncrement);
        result.setSecond(result.getSecond() + yIncrement);

        return result;
    }

    @Override
    public void onContinueGame() {
        switch (state) {
            case start -> startGame();
            case rollDie -> rollDie();
            case movePlayer -> movePlayer();
            case applySpecial -> applySpecial();
            case checkWinner -> checkWinner();
            case changePlayer -> changePlayer();
            case end -> endGameState();
            default -> {
            }
        }
    }

    @Override
    public void onUpdateCard(Card card) {
        game.setCard(card);
    }

    private void startGame() {
        view.getContinueGameButton().setText("Rolar dado");
        state = GameState.rollDie;
    }

    private void rollDie() {
        // Rolls die.
        int dieValue = game.rollDie();
        dieRollAnimation.start(dieValue);

        view.getContinueGameButton().setText("Mover jogador");
        state = GameState.movePlayer;
    }

    private void movePlayer() {
        // Stops rolling the die.
        dieRollAnimation.stop();

        // Moves player.
        game.movePlayer();

        // Checks if has special to apply.
        if (game.getSpecial() != null) {
            view.getContinueGameButton().setText("Aplicar especial");
            state = GameState.applySpecial;
        }
        else {
            view.getContinueGameButton().setText("Continuar");
            state = GameState.checkWinner;
        }
    }

    private void applySpecial() {
        // Applies special.
        game.applySpecial();

        view.getContinueGameButton().setText("Continuar");
        state = GameState.checkWinner;
    }

    private void checkWinner() {
        // Removes special card if exists.
        game.setCard(null);

        // Checks if player is the winner.
        if (game.checkWinner()) {
            view.getContinueGameButton().setText("Finalizar jogo");
            state = GameState.end;
            return;
        }

        view.getContinueGameButton().setText("Trocar jogador");
        state = GameState.changePlayer;
    }

    private void changePlayer() {
        game.goToNextPlayer();

        view.getContinueGameButton().setText("Rolar dado");
        state = GameState.rollDie;
    }

    private void endGameState() {
        IPlayer winner = game.getCurrentPlayer();
        goTo(new WinnerScene(winner));
    }

}
