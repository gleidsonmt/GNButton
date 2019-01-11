/*
 * Copyright (C) Gleidson Neves da Silveira
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.gn.lab;

import com.sun.javafx.css.converters.DurationConverter;
import com.sun.javafx.css.converters.PaintConverter;
import javafx.css.*;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Control;
import javafx.scene.control.Labeled;
import javafx.scene.control.Skin;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.util.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  14/12/2018
 */
public class GNButton extends Labeled {

    StackPane rect = new StackPane();

    private StyleableObjectProperty<ButtonType> buttonType;

    private StyleableObjectProperty<Paint> transitionColor;
    private StyleableObjectProperty<Paint> transitionText;
    private StyleableObjectProperty<Duration> transitionDuration;

    private List<CssMetaData<? extends Styleable, ?>> STYLEABLES;

    public GNButton(){
        this(null);
    }

    public GNButton(String title) {

        getStyleClass().add("gn-button");
        setAlignment(Pos.CENTER);

        setPrefSize(50, 25);

        setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, CornerRadii.EMPTY, Insets.EMPTY)));

        this.buttonType = new SimpleStyleableObjectProperty<ButtonType>(GNButton.StyleableProperties.BUTTON_TYPE, this, "buttonType", ButtonType.SWIPE);

        this.transitionColor = new SimpleStyleableObjectProperty<Paint>(StyleableProperties.TRANSITION_COLOR, this, "transitionColor", Color.web("33B5E5"));

        this.transitionText = new SimpleStyleableObjectProperty<Paint>(StyleableProperties.TRANSITION_TEXT, this, "transitionText", Color.WHITE);

        this.transitionDuration = new SimpleStyleableObjectProperty<Duration>(StyleableProperties.TRANSITION_DURATION, this, "transitionDuration", Duration.millis(300D));

        setPadding(new Insets(0D));
        setText( title != null ? title : "Button" );
        setBorder(new Border(new BorderStroke(Color.web("33B5E5"), BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT )));

        updateButtonType(getButtonType());
        updateTransitionColor(getTransitionColor());

        this.buttonTypeProperty().addListener((o, oldVal, newVal) -> updateButtonType(newVal));
        this.transitionColorProperty().addListener((o, oldVal, newVal) -> updateTransitionColor(newVal));

    }

    private void updateButtonType(ButtonType type) {
        switch (type) {
            case ALTERNATE:
                setSkin(new AlternateSkin(this));
                break;
            case SMOOSH :
                setSkin(new SmooshSkin(this));
                break;
            case CENTRALIZE:
                setSkin(new CentralizeSkin(this));
                break;
            case DIAGONAL_SWIPE:
                setSkin(new SwipeDiagonalSkin(this));
                break;
            case CORNERS :
                setSkin(new CornerSkin(this));
                break;
            case SWIPE :
                setSkin(new SwipeSkin(this));
                break;
        }
    }

    private void updateTransitionColor(Paint color){
        rect.setBackground(new Background(new BackgroundFill(color, CornerRadii.EMPTY, Insets.EMPTY)));
        setBorder(new Border(new BorderStroke(color, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(1))));
    }

    public List<CssMetaData<? extends Styleable, ?>> getControlCssMetaData() {
        if (this.STYLEABLES == null) {
            List<CssMetaData<? extends Styleable, ?>> styleables = new ArrayList(Control.getClassCssMetaData());
            styleables.addAll(getClassCssMetaData());
            styleables.addAll(Labeled.getClassCssMetaData());
            this.STYLEABLES = Collections.unmodifiableList(styleables);
        }
        return this.STYLEABLES;
    }

    private static class StyleableProperties {

        private static final CssMetaData<GNButton, ButtonType> BUTTON_TYPE;
        private static final CssMetaData<GNButton, Paint> TRANSITION_COLOR;
        private static final CssMetaData<GNButton, Paint> TRANSITION_TEXT;

        private static final CssMetaData<GNButton, Duration> TRANSITION_DURATION;

        private static final List<CssMetaData<? extends Styleable, ?>> CHILD_STYLEABLES;

        private StyleableProperties(){}

        static {

            BUTTON_TYPE = new CssMetaData<GNButton, ButtonType>("-gn-button-type", ButtonTypeConverter.getInstance(), ButtonType.SWIPE) {

                public boolean isSettable(GNButton control) {
                    return control.buttonType == null || !control.buttonType.isBound();
                }

                public StyleableProperty<ButtonType> getStyleableProperty(GNButton control) {
                    return control.buttonTypeProperty();
                }
            };

            TRANSITION_COLOR = new CssMetaData<GNButton, Paint>("-gn-transition-color", PaintConverter.getInstance(), Color.RED) {
                @Override
                public boolean isSettable(GNButton styleable) {
                    return styleable.transitionColor == null || !styleable.transitionColor.isBound();
                }

                @Override
                public StyleableProperty<Paint> getStyleableProperty(GNButton styleable) {
                    return styleable.transitionColorProperty();
                }
            };

            TRANSITION_TEXT = new CssMetaData<GNButton, Paint>("-gn-transition-text", PaintConverter.getInstance(), Color.WHITE) {
                @Override
                public boolean isSettable(GNButton styleable) {
                    return styleable.transitionText == null || !styleable.transitionText.isBound();
                }

                @Override
                public StyleableProperty<Paint> getStyleableProperty(GNButton styleable) {
                    return styleable.transitionTextProperty();
                }
            };

            TRANSITION_DURATION = new CssMetaData<GNButton, Duration>("-gn-transition-duration", DurationConverter.getInstance(), Duration.millis(300D)) {
                @Override
                public boolean isSettable(GNButton styleable) {
                    return styleable.transitionDuration == null || !styleable.transitionDuration.isBound();
                }

                @Override
                public StyleableProperty<Duration> getStyleableProperty(GNButton styleable) {
                    return styleable.transitionDurationProperty();
                }
            };

            List<CssMetaData<? extends Styleable, ?>> styleables = new ArrayList(Control.getClassCssMetaData());
            Collections.addAll(styleables, BUTTON_TYPE, TRANSITION_COLOR, TRANSITION_TEXT, TRANSITION_DURATION);
            CHILD_STYLEABLES = Collections.unmodifiableList(styleables);
        }
    }

    public static List<CssMetaData<? extends Styleable, ?>> getClassCssMetaData() {
        return GNButton.StyleableProperties.CHILD_STYLEABLES;
    }

    public StyleableObjectProperty<ButtonType> buttonTypeProperty() {
        return this.buttonType;
    }

    public void setButtonType(ButtonType buttonType) {
        this.buttonType.set(buttonType);
    }

    public ButtonType getButtonType() {
        return this.buttonType == null ? ButtonType.SWIPE : this.buttonType.get();
    }

    public Paint getTransitionColor() {
        return transitionColor.get();
    }

    public StyleableObjectProperty<Paint> transitionColorProperty() {
        return transitionColor;
    }

    public void setTransitionColor(Paint transitionColor) {
        this.transitionColor.set(transitionColor);
    }

    public Paint getTransitionText() {
        return transitionText.get();
    }

    public StyleableObjectProperty<Paint> transitionTextProperty() {
        return transitionText;
    }

    public Duration getTransitionDuration() {
        return transitionDuration.get();
    }

    public StyleableObjectProperty<Duration> transitionDurationProperty() {
        return transitionDuration;
    }

    public void setTransitionDuration(Duration transitionDuration) {
        this.transitionDuration.set(transitionDuration);
    }

    @Override
    protected Skin<?> createDefaultSkin() {
        return new SwipeSkin(this);
    }
}
