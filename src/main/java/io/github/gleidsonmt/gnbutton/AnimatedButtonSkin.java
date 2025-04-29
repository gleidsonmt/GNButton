package io.github.gleidsonmt.gnbutton;

import javafx.animation.Animation;
import javafx.animation.Timeline;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.scene.control.skin.ButtonSkin;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.util.Duration;

import java.util.List;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  28/04/2025
 */
public class AnimatedButtonSkin extends ButtonSkin {

    // fixed
    protected List<Pane> shapes;
    protected Timeline timeline;

    // testing
    protected ObjectProperty<Duration> velocity = new SimpleObjectProperty<>(this, "velocity", Duration.millis(200));
    protected Paint oldTextFill = Color.BLACK;

    public AnimatedButtonSkin(int quant, AnimatedButton control) {
        super(control);
        this.shapes = List.of(createPanes(quant));
        this.timeline = new Timeline();

        control.getStyleClass().remove("button");

        control.setBorder(new Border(new BorderStroke(control.getTransitionFill(), BorderStrokeStyle.SOLID, new CornerRadii(0), new BorderWidths(0))));
        control.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, new CornerRadii(0), Insets.EMPTY)));

        configDefaultListeners();

        shapes.forEach(rect -> {
            rect.getStyleClass().add("animated-button-rect");
            rect.setPrefWidth(0);
            rect.setMaxWidth(0);

            rect.setPrefHeight(Region.USE_COMPUTED_SIZE);
            rect.setMaxHeight(Region.USE_COMPUTED_SIZE);
            getChildren().add(rect);
            rect.toBack();
            rect.setBackground(new Background(new BackgroundFill(control.getTransitionFill(), new CornerRadii(0), new Insets(-1))));

            rect.setStyle(" -fx-border-insets: -4px -8px -4px -8px;");

        });

    }

    protected void configDefaultListeners() {
        // update the background with transition color
        registerChangeListener((ObservableValue<?>) ((AnimatedButton) getSkinnable()).transitionColorProperty(), c -> {
            if (c.getValue() != null) {
                if (shapes != null) {
                    shapes.forEach(rect -> {
                        BackgroundFill backgroundFill = new BackgroundFill((Paint) c.getValue(), new CornerRadii(0), new Insets(-1));
                        rect.setBackground(new Background(backgroundFill));
                    });
                }
//                getSkinnable().setBorder(new Border(new BorderStroke((Paint) c.getValue(), BorderStrokeStyle.SOLID, new CornerRadii(0), new BorderWidths(2))));
            }
        });
        registerChangeListener(getSkinnable().textFillProperty(), c -> {
            // timeline it's updating the text fill, so if the timeline doesn't finish, that can get unliked fill resulting
            if(timeline.getStatus() == Animation.Status.STOPPED ) {
                oldTextFill = (Paint) c.getValue();
            }
        });
    }

    private Pane[] createPanes(int quant) {
        Pane[] panes = new Pane[quant];
        for (int i = 0; i < quant; i++) {
            panes[i] = new Pane();
        }
        return panes;
    }
}
