package io.github.gleidsonmt.gnbutton;

import javafx.scene.control.Skin;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  28/04/2025
 */
public class SmoothButton extends AnimatedButton {

    public SmoothButton() {
        this("Smooth");
    }

    public SmoothButton(String text) {
        super(text);
        getStyleClass().addAll("smooth-button");
    }

    @Override
    protected Skin<?> createDefaultSkin() {
        return new SmoothButtonSkin(this);
    }
}
