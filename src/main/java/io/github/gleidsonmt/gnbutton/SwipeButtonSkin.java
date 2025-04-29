package io.github.gleidsonmt.gnbutton;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.util.Duration;


/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  26/04/2025
 */
public class SwipeButtonSkin extends AnimatedButtonSkin {

    public SwipeButtonSkin(SwipeButton control) {
        super(1, control);
//        rect.setStyle(" -fx-border-insets: -4px -8px -4px -8px;");


        getSkinnable().setOnMouseEntered(_ -> {
            timeline.getKeyFrames().clear();
            timeline.setRate(1);
            shapes.forEach(rect -> {
                timeline.getKeyFrames().addAll(
                        new KeyFrame(Duration.ZERO, new KeyValue(rect.prefWidthProperty(), rect.getPrefWidth())),
                        new KeyFrame(Duration.ZERO, new KeyValue(rect.maxWidthProperty(), rect.getPrefWidth())),
                        new KeyFrame(Duration.ZERO, new KeyValue(getSkinnable().textFillProperty(), oldTextFill)),

                        new KeyFrame(velocity.get(), new KeyValue(rect.prefWidthProperty(), getSkinnable().getWidth())),
                        new KeyFrame(velocity.get(), new KeyValue(rect.maxWidthProperty(), getSkinnable().getWidth())),

                        new KeyFrame(velocity.get(), new KeyValue(getSkinnable().textFillProperty(), control.getTransitionTextFill()))

                );
            });


            if(timeline.getStatus() == Animation.Status.RUNNING) {
                timeline.playFromStart();
                return;
            }

            timeline.play();

        });

        getSkinnable().setOnMouseExited(_ -> {
            timeline.setRate(-1);
            timeline.play();
        });
    }

    @Override
    protected void layoutChildren(double x, double y, double w, double h) {
        super.layoutChildren(x, y, w, h);
        shapes.forEach(rect -> {
            layoutInArea(rect, x, y, w, h, 0,
                    HPos.LEFT, VPos.CENTER);
        });
    }

}
