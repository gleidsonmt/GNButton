package io.github.gleidsonmt.gnbutton;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.util.Duration;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  28/04/2025
 */
public class CornerButtonSkin extends AnimatedButtonSkin {

    public CornerButtonSkin(AnimatedButton control) {
        super(4, control);

        getSkinnable().setOnMouseExited(_ -> {
            timeline.setRate(-1);
            timeline.play();
        });

        oldTextFill = control.getTextFill();

        shapes.forEach(rect -> {
            rect.setPrefHeight(0);
            rect.setMaxHeight(0);


        });

        getSkinnable().setOnMouseEntered(_ -> {
            timeline.getKeyFrames().clear();
            timeline.setRate(1);
            shapes.forEach(rect -> timeline.getKeyFrames().addAll(
                    new KeyFrame(Duration.ZERO, new KeyValue(rect.prefHeightProperty(), 0)),
                    new KeyFrame(Duration.ZERO, new KeyValue(rect.maxHeightProperty(), 0)),

                    new KeyFrame(Duration.ZERO, new KeyValue(rect.prefWidthProperty(), 0)),
                    new KeyFrame(Duration.ZERO, new KeyValue(rect.maxWidthProperty(), 0)),

                    new KeyFrame(Duration.ZERO, new KeyValue(getSkinnable().textFillProperty(), oldTextFill)),

                    new KeyFrame(velocity.get(), new KeyValue(rect.prefHeightProperty(), getSkinnable().getHeight() /2)),
                    new KeyFrame(velocity.get(), new KeyValue(rect.maxHeightProperty(), getSkinnable().getHeight() /2)),

                    new KeyFrame(velocity.get(), new KeyValue(rect.prefWidthProperty(), getSkinnable().getWidth() / 2)),
                    new KeyFrame(velocity.get(), new KeyValue(rect.maxWidthProperty(), getSkinnable().getWidth() / 2)),

                    new KeyFrame(velocity.get(), new KeyValue(getSkinnable().textFillProperty(), control.getTransitionTextFill()))

            ));


//            if (timeExited.getStatus() == Animation.Status.RUNNING) {
//                timeExited.stop();
//            }

            if (timeline.getStatus() == Animation.Status.RUNNING) {
                timeline.playFromStart();
                return;
            }

            timeline.play();

        });
    }

    @Override
    protected void layoutChildren(double x, double y, double w, double h) {
        super.layoutChildren(x, y, w, h);
        layoutInArea(shapes.get(0), x, y, w, h, -1,
                HPos.LEFT, VPos.TOP);

        layoutInArea(shapes.get(1), x, y, w, h, -1,
                HPos.LEFT, VPos.BOTTOM);

        layoutInArea(shapes.get(2), x, y, w, h, -1,
                HPos.RIGHT, VPos.TOP);

        layoutInArea(shapes.get(3), x, y, w, h, -1,
                HPos.RIGHT, VPos.BOTTOM);
    }
}
