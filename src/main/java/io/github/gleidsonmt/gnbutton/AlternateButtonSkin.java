package io.github.gleidsonmt.gnbutton;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.layout.Region;
import javafx.util.Duration;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  28/04/2025
 */
public class AlternateButtonSkin extends AnimatedButtonSkin {

    public AlternateButtonSkin(AlternateButton control) {
        super(4, control);


        shapes.forEach(rect -> {
            rect.setPrefHeight(0);
            rect.setMaxHeight(0);

            rect.setPrefWidth(Region.USE_COMPUTED_SIZE);
            rect.setMaxWidth(Region.USE_COMPUTED_SIZE);
        });

        getSkinnable().setOnMouseEntered(_ -> {
            timeline.getKeyFrames().clear();
            timeline.setRate(1);
//            old = control.getTextFill();
            shapes.forEach(rect -> timeline.getKeyFrames().addAll(
                    new KeyFrame(Duration.ZERO, new KeyValue(rect.prefHeightProperty(), 0)),
                    new KeyFrame(Duration.ZERO, new KeyValue(rect.maxHeightProperty(), 0)),
                    new KeyFrame(Duration.ZERO, new KeyValue(getSkinnable().textFillProperty(), oldTextFill)),

                    new KeyFrame(velocity.get(), new KeyValue(rect.prefHeightProperty(), getSkinnable().getHeight())),
                    new KeyFrame(velocity.get(), new KeyValue(rect.maxHeightProperty(), getSkinnable().getHeight())),

                    new KeyFrame(velocity.get(), new KeyValue(getSkinnable().textFillProperty(), control.getTransitionTextFill()))

            ));

            if(timeline.getStatus() == Animation.Status.RUNNING){
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
        layoutInArea(shapes.get(0), x , y, w / 4, h, -1,
                HPos.LEFT, VPos.TOP);

        layoutInArea(shapes.get(1), w / (getChildren().size() - 1), y, w / 4, h, -1,
                HPos.LEFT, VPos.BOTTOM);

        layoutInArea(shapes.get(2), (w / (getChildren().size() - 1)) * 2, y, w / 4, h, -1,
                HPos.LEFT, VPos.TOP);

        layoutInArea(shapes.get(3), (w / (getChildren().size() - 1)) * 3 + 2, y, w / 4, h, -1,
                HPos.LEFT, VPos.BOTTOM);

    }

}
