package io.github.gleidsonmt.gnbutton;

import javafx.scene.control.Skin;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  28/04/2025
 */
public class AlternateButton extends AnimatedButton {

    public AlternateButton() {
        this("Alternate");
    }

    public AlternateButton(String text) {
        super(text);
        getStyleClass().addAll("alternate-button");
    }

    @Override
    protected Skin<?> createDefaultSkin() {
        return new AlternateButtonSkin(this);
    }
}
