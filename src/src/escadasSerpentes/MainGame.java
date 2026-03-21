package src.escadasSerpentes;

import src.escadasSerpentes.dto.*;

import java.util.Collections;
import java.util.List;

public class MainGame {
    private final List<IPlayer> players;
    private int currentPlayerIndex;

    private Board board;
    private Card card;
    private Die die;
    private int dieValue;

    public MainGame(List<IPlayer> players, Board board, Die die) {
        this.players = players;
        currentPlayerIndex = 0;

        this.board = board;
        this.die = die;
    }

    public List<IPlayer> getPlayers() {
        return Collections.unmodifiableList(players);
    }

    public IPlayer getCurrentPlayer() {
        return players.get(currentPlayerIndex);
    }

    public void goToNextPlayer() {
        currentPlayerIndex = (currentPlayerIndex + 1) % players.size();
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public Card getCard() {
        return card;
    }

    public void setCard(Card card) {
        this.card = card;
    }

    public Die getDie() {
        return die;
    }

    public void setDie(Die die) {
        this.die = die;
    }

    public int getDieValue() {
        return dieValue;
    }

    public ISpecial getSpecial() {
        IPlayer player = getCurrentPlayer();
        int position = player.getPosition();
        return board.getSpecial(position);
    }

    public int rollDie() {
        dieValue = die.roll();
        return dieValue;
    }

    public void movePlayer() {
        IPlayer player = getCurrentPlayer();
        player.move(dieValue);
    }

    public void applySpecial() {
        IPlayer player = getCurrentPlayer();
        ISpecial special = getSpecial();
        if (special != null) {
            special.apply(player);
        }
    }

    public boolean checkWinner() {
        IPlayer player = getCurrentPlayer();
        return player.getPosition() == board.getNumSpaces() - 1;
    }
}
