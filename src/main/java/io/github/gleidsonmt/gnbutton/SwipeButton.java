package io.github.gleidsonmt.gnbutton;

import javafx.scene.control.Skin;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  28/04/2025
 */
@SuppressWarnings("unused")
public class SwipeButton extends AnimatedButton {

    public SwipeButton() {
        this("Swipe");
    }
    public SwipeButton(String text) {
        super(text);
        getStyleClass().add("swipe-button");
    }

    @Override
    protected Skin<?> createDefaultSkin() {
        return new SwipeButtonSkin(this);
    }

}