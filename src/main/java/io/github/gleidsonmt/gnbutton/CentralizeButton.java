package io.github.gleidsonmt.gnbutton;

import javafx.scene.control.Skin;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  28/04/2025
 */
public class CentralizeButton extends AnimatedButton {

    public CentralizeButton() {
        this("Centralize");
    }

    public CentralizeButton(String text) {
        super(text);
        getStyleClass().addAll("centralize-button");
    }

    @Override
    protected Skin<?> createDefaultSkin() {
        return new CentralizeButtonSkin(this);
    }
}
