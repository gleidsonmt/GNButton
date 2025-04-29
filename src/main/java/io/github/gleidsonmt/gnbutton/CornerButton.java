package io.github.gleidsonmt.gnbutton;

import javafx.scene.control.Skin;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  28/04/2025
 */
public class CornerButton extends AnimatedButton {

    public CornerButton() {
        this("Corner");
    }

    public CornerButton(String text) {
        super(text);
        getStyleClass().addAll("corner-button");
    }

    @Override
    protected Skin<?> createDefaultSkin() {
        return new CornerButtonSkin(this);
    }
}
