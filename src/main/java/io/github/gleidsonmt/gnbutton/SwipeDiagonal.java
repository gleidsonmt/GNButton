package io.github.gleidsonmt.gnbutton;

import javafx.scene.control.Skin;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  28/04/2025
 */
public class SwipeDiagonal extends AnimatedButton {
    public SwipeDiagonal() {
        this("SwipeDiagonal");
    }

    public SwipeDiagonal(String text) {
        super(text);
        getStyleClass().add("swipe-diagonal");
    }

    @Override
    protected Skin<?> createDefaultSkin() {
        return new SwipeDiagonalSkin(this);
    }
}
