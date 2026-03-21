package src.escadasSerpentes.drawable.animations;

import src.escadasSerpentes.Pair;
import src.escadasSerpentes.Util;
import src.escadasSerpentes.drawable.PlayerDrawable;
import src.escadasSerpentes.dto.IPlayer;
import src.escadasSerpentes.providers.IResourceProvider;

import java.awt.*;

public class PlayerJumpAnimation extends Animation {
    PlayerDrawable drawable;

    /**
     * Creates a new player jump animation.
     */
    public PlayerJumpAnimation(IPlayer player, IResourceProvider resourceProvider) {
        super();

        drawable = new PlayerDrawable(player, resourceProvider);
    }

    @Override
    public void update(double deltaTime) {
        super.update(deltaTime);

        if(shouldContinueAnimation()) {
            float t = getAnimationTime() * 3;
            float y = Util.jumpFunc(t) * 30;
            drawable.setPosition(0, -y);

            if (getAnimationTime() > 2.5f) {
                setAnimationTime(0);
            }
        }
    }

    @Override
    protected void drawAnimation(Graphics2D graphics2D, float animationTime) {
        drawable.draw(graphics2D);
    }

    @Override
    public Pair<Float, Float> getSize() {
        return drawable.getSize();
    }
}
