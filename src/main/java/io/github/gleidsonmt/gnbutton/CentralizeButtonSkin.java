package io.github.gleidsonmt.gnbutton;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

import java.util.List;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  28/04/2025
 */
public class CentralizeButtonSkin extends AnimatedButtonSkin {

    public CentralizeButtonSkin(AnimatedButton control) {
        super(1, control);

        getSkinnable().setOnMouseExited(event -> {
            timeline.setRate(-1);
            timeline.play();
        });

        oldTextFill = control.getTextFill();

        getSkinnable().setOnMouseEntered(event -> {
            timeline.getKeyFrames().clear();
            timeline.setRate(1);
//            old = control.getTextFill();
            shapes.forEach(rect -> {

                timeline.getKeyFrames().addAll(
                        new KeyFrame(Duration.ZERO, new KeyValue(rect.prefWidthProperty(), 0)),
                        new KeyFrame(Duration.ZERO, new KeyValue(rect.maxWidthProperty(), 0)),
                        new KeyFrame(Duration.ZERO, new KeyValue(getSkinnable().textFillProperty(), oldTextFill)),

                        new KeyFrame(velocity.get(), new KeyValue(rect.prefWidthProperty(), getSkinnable().getWidth())),
                        new KeyFrame(velocity.get(), new KeyValue(rect.maxWidthProperty(), getSkinnable().getWidth())),

                        new KeyFrame(velocity.get(), new KeyValue(getSkinnable().textFillProperty(), control.getTransitionTextFill()))

                );
            });


//            if (timeExited.getStatus() == Animation.Status.RUNNING) {
//                timeExited.stop();
//            }

            if(timeline.getStatus() == Animation.Status.RUNNING){
                timeline.playFromStart();
                return;
            }

            timeline.play();

        });
    }

    @Override
    protected void layoutChildren(double x, double y, double w, double h) {
        super.layoutChildren(x, y, w, h);
        shapes.forEach(rect -> {
            layoutInArea(rect, x, y, w, h, 0,
                    HPos.CENTER, VPos.CENTER);
        });

    }
}
