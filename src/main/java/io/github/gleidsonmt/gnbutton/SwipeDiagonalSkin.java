package io.github.gleidsonmt.gnbutton;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.control.Skin;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.SVGPath;
import javafx.util.Duration;

import java.util.List;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  28/04/2025
 */
public class SwipeDiagonalSkin extends AnimatedButtonSkin {


    public SwipeDiagonalSkin(AnimatedButton control) {
        super(1, control);

        Rectangle clip = new Rectangle();
        clip.setArcWidth(0);
        clip.setArcHeight(0);
        getSkinnable().setClip(clip);

        clip.widthProperty().bind(getSkinnable().widthProperty());
        clip.heightProperty().bind(getSkinnable().heightProperty());

        shapes.forEach(rect -> {
            SVGPath shape = new SVGPath();
            shape.setContent("M 250 200 L 250 300 L 500 300 L 450 200 L 250 200 ");
            rect.setShape(shape);
            rect.minWidthProperty().bind(getSkinnable().widthProperty().add(getSkinnable().heightProperty()));
        });

        getSkinnable().setOnMouseExited(event -> {
            timeline.setRate(-1);
            timeline.play();
        });

        getSkinnable().setOnMouseEntered(event -> {
            timeline.getKeyFrames().clear();
            timeline.setRate(1);

            shapes.forEach(rect -> {
                timeline.getKeyFrames().addAll(
                        new KeyFrame(Duration.ZERO, new KeyValue(rect.translateXProperty(), 0)),
                        new KeyFrame(Duration.ZERO, new KeyValue(getSkinnable().textFillProperty(), oldTextFill)),

                        new KeyFrame(velocity.get(), new KeyValue(rect.translateXProperty(),
                                getSkinnable().getWidth() + getSkinnable().getHeight())),

                        new KeyFrame(velocity.get(), new KeyValue(getSkinnable().textFillProperty(), control.getTransitionTextFill()))
                );

                if(timeline.getStatus() == Animation.Status.RUNNING){
                    timeline.playFromStart();
                    return;
                }

                timeline.play();

            });
        });
    }

    @Override
    protected void layoutChildren(double x, double y, double w, double h) {
        super.layoutChildren(x, y, w, h);
        layoutInArea(shapes.getFirst(), -(shapes.getFirst().getWidth()), y, w+150 , h, -1,
                HPos.LEFT, VPos.CENTER);
        positionInArea(shapes.getFirst(), -(shapes.getFirst().getWidth()), y, w+150 , h, -1,
                HPos.LEFT, VPos.CENTER);
    }

}
