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

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.control.Label;
import javafx.scene.control.Labeled;
import javafx.scene.control.SkinBase;
import javafx.scene.layout.*;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  14/12/2018
 */
public class AlternateSkin extends SkinBase<Labeled>{

    private Rectangle clip = new Rectangle();
    private Label title = new Label("Button");

    private Paint firstColor;

    private StackPane rect = ((GNButton) getSkinnable()).rect;
    private StackPane rect1 = new StackPane();
    private StackPane rect2 = new StackPane();
    private StackPane rect3 = new StackPane();

    private double velocity = 500;

    public AlternateSkin(GNButton control) {
        super(control);

        rect.setShape(null);

        rect.setPrefHeight(0);
        rect.setMaxHeight(0);

        rect.setPrefWidth(Region.USE_COMPUTED_SIZE);
        rect.setMaxWidth(Region.USE_COMPUTED_SIZE);

        rect1.setPrefWidth(Region.USE_COMPUTED_SIZE);
        rect1.setMaxWidth(Region.USE_COMPUTED_SIZE);
        rect2.setPrefWidth(Region.USE_COMPUTED_SIZE);
        rect2.setMaxWidth(Region.USE_COMPUTED_SIZE);
        rect3.setPrefWidth(Region.USE_COMPUTED_SIZE);
        rect3.setMaxWidth(Region.USE_COMPUTED_SIZE);

        rect1.setMaxHeight(0);
        rect1.setPrefHeight(0);
        rect2.setMaxHeight(0);
        rect2.setPrefHeight(0);
        rect3.setMaxHeight(0);
        rect3.setPrefHeight(0);



        getChildren().clear();

        getChildren().add(rect);
        getChildren().add(rect1);
        getChildren().add(rect2);
        getChildren().add(rect3);
        getChildren().add(title);

        title.textProperty().bind(getSkinnable().textProperty());
        title.fontProperty().bind(getSkinnable().fontProperty());
        title.textFillProperty().bind(getSkinnable().textFillProperty());
        title.underlineProperty().bind(getSkinnable().underlineProperty());
        title.textAlignmentProperty().bind(getSkinnable().textAlignmentProperty());
        title.contentDisplayProperty().bind(getSkinnable().contentDisplayProperty());
        title.ellipsisStringProperty().bind(getSkinnable().ellipsisStringProperty());
        title.backgroundProperty().bind(getSkinnable().backgroundProperty());
        title.alignmentProperty().bind(getSkinnable().alignmentProperty());
        title.textOverrunProperty().bind(getSkinnable().textOverrunProperty());

        clip.setArcWidth(0);
        clip.setArcHeight(0);
        getSkinnable().setClip(clip);

        clip.widthProperty().bind(getSkinnable().widthProperty());
        clip.heightProperty().bind(getSkinnable().heightProperty());

        Timeline timeEntered = new Timeline();
        Timeline timeExited = new Timeline();

        firstColor = getSkinnable().getTextFill();

        rect.setBackground(new Background(new BackgroundFill(
                ((GNButton)getSkinnable()).getTransitionColor(), CornerRadii.EMPTY, Insets.EMPTY
        )));

        rect1.setBackground(new Background(new BackgroundFill(
                ((GNButton)getSkinnable()).getTransitionColor(), CornerRadii.EMPTY, Insets.EMPTY
        )));

        rect2.setBackground(new Background(new BackgroundFill(
                ((GNButton)getSkinnable()).getTransitionColor(), CornerRadii.EMPTY, Insets.EMPTY
        )));

        rect3.setBackground(new Background(new BackgroundFill(
                ((GNButton)getSkinnable()).getTransitionColor(), CornerRadii.EMPTY, Insets.EMPTY
        )));

        ((GNButton)getSkinnable()).transitionColorProperty().addListener(new ChangeListener<Paint>() {
            @Override
            public void changed(ObservableValue<? extends Paint> observable, Paint oldValue, Paint newValue) {
                rect.setBackground(new Background(new BackgroundFill(newValue, CornerRadii.EMPTY, Insets.EMPTY)));
                rect1.setBackground(new Background(new BackgroundFill(newValue, CornerRadii.EMPTY, Insets.EMPTY)));
                rect2.setBackground(new Background(new BackgroundFill(newValue, CornerRadii.EMPTY, Insets.EMPTY)));
                rect3.setBackground(new Background(new BackgroundFill(newValue, CornerRadii.EMPTY, Insets.EMPTY)));
            }
        });

        getSkinnable().textFillProperty().addListener((observable, oldValue, newValue) -> {
            if (timeEntered.getStatus() == Animation.Status.STOPPED && timeExited.getStatus() == Animation.Status.STOPPED) {
                firstColor = newValue;
            }
        });

//        rect.setStyle("-fx-background-color : red;");
//        rect1.setStyle("-fx-background-color : blue;");
//        rect2.setStyle("-fx-background-color : yellow;");
//        rect3.setStyle("-fx-background-color : green;");

        getSkinnable().setOnMouseEntered(event -> {
            timeEntered.getKeyFrames().clear();

            timeEntered.getKeyFrames().addAll(



                    new KeyFrame(Duration.ZERO, new KeyValue(rect.maxHeightProperty(), rect.getHeight())),
                    new KeyFrame(Duration.ZERO, new KeyValue(rect.maxHeightProperty(), rect.getHeight())),

                    new KeyFrame(Duration.ZERO, new KeyValue(rect1.prefHeightProperty(), rect1.getHeight())),
                    new KeyFrame(Duration.ZERO, new KeyValue(rect1.maxHeightProperty(), rect1.getHeight())),

                    new KeyFrame(Duration.ZERO, new KeyValue(rect2.prefHeightProperty(), rect2.getHeight())),
                    new KeyFrame(Duration.ZERO, new KeyValue(rect2.maxHeightProperty(), rect2.getHeight())),

                    new KeyFrame(Duration.ZERO, new KeyValue(rect3.prefHeightProperty(), rect3.getHeight())),
                    new KeyFrame(Duration.ZERO, new KeyValue(rect3.maxHeightProperty(), rect3.getHeight())),

                    new KeyFrame(Duration.millis(velocity), new KeyValue(rect.prefHeightProperty(), getSkinnable().getHeight())),
                    new KeyFrame(Duration.millis(velocity), new KeyValue(rect.maxHeightProperty(), getSkinnable().getHeight())),

                    new KeyFrame(Duration.millis(velocity), new KeyValue(rect1.prefHeightProperty(), getSkinnable().getHeight())),
                    new KeyFrame(Duration.millis(velocity), new KeyValue(rect1.maxHeightProperty(), getSkinnable().getHeight())),

                    new KeyFrame(Duration.millis(velocity), new KeyValue(rect2.prefHeightProperty(), getSkinnable().getHeight())),
                    new KeyFrame(Duration.millis(velocity), new KeyValue(rect2.maxHeightProperty(), getSkinnable().getHeight())),

                    new KeyFrame(Duration.millis(velocity), new KeyValue(rect3.prefHeightProperty(), getSkinnable().getHeight())),
                    new KeyFrame(Duration.millis(velocity), new KeyValue(rect3.maxHeightProperty(), getSkinnable().getHeight())),

                    new KeyFrame(Duration.ZERO, new KeyValue(getSkinnable().textFillProperty(), getSkinnable().getTextFill())),
                    new KeyFrame(Duration.millis(velocity), new KeyValue(getSkinnable().textFillProperty(), ((GNButton) getSkinnable()).getTransitionText()))

            );

            if (timeExited.getStatus() == Animation.Status.RUNNING) {
                timeExited.stop();
            }

            timeEntered.play();

        });

        getSkinnable().setOnMouseExited(event -> {
            timeExited.getKeyFrames().clear();
            timeExited.getKeyFrames().addAll(

                    new KeyFrame(Duration.ZERO, new KeyValue(rect.prefHeightProperty(), rect.getHeight())),
                    new KeyFrame(Duration.ZERO, new KeyValue(rect.maxHeightProperty(), rect.getHeight())),

                    new KeyFrame(Duration.ZERO, new KeyValue(rect1.prefHeightProperty(), rect1.getHeight())),
                    new KeyFrame(Duration.ZERO, new KeyValue(rect1.maxHeightProperty(), rect1.getHeight())),

                    new KeyFrame(Duration.ZERO, new KeyValue(rect2.prefHeightProperty(), rect2.getHeight())),
                    new KeyFrame(Duration.ZERO, new KeyValue(rect2.maxHeightProperty(), rect2.getHeight())),

                    new KeyFrame(Duration.ZERO, new KeyValue(rect3.prefHeightProperty(), rect3.getHeight())),
                    new KeyFrame(Duration.ZERO, new KeyValue(rect3.maxHeightProperty(), rect3.getHeight())),

                    new KeyFrame(Duration.millis(velocity), new KeyValue(rect.prefHeightProperty(), 0D)),
                    new KeyFrame(Duration.millis(velocity), new KeyValue(rect.maxHeightProperty(), 0D)),

                    new KeyFrame(Duration.millis(velocity), new KeyValue(rect1.prefHeightProperty(), 0D )),
                    new KeyFrame(Duration.millis(velocity), new KeyValue(rect1.maxHeightProperty(), 0D )),

                    new KeyFrame(Duration.millis(velocity), new KeyValue(rect2.prefHeightProperty(), 0D )),
                    new KeyFrame(Duration.millis(velocity), new KeyValue(rect2.maxHeightProperty(), 0D )),

                    new KeyFrame(Duration.millis(velocity), new KeyValue(rect3.prefHeightProperty(), 0D )),
                    new KeyFrame(Duration.millis(velocity), new KeyValue(rect3.maxHeightProperty(), 0D )),

                    new KeyFrame(Duration.ZERO, new KeyValue(getSkinnable().textFillProperty(), getSkinnable().getTextFill())),
                    new KeyFrame(Duration.millis(velocity), new KeyValue(getSkinnable().textFillProperty(), firstColor))

            );

            if (timeEntered.getStatus() == Animation.Status.RUNNING) {
                timeEntered.stop();
            }

            timeExited.play();
        });
    }

    @Override
    protected double computeMinWidth(double height, double topInset, double rightInset, double bottomInset, double leftInset) {
        return title.minWidth(height);
    }

    @Override
    protected double computeMinHeight(double width, double topInset, double rightInset, double bottomInset, double leftInset) {
        return title.minHeight(width);
    }

    @Override
    protected double computePrefWidth(double height, double topInset, double rightInset, double bottomInset, double leftInset) {
        return title.prefWidth(height) + leftInset + rightInset;
    }

    @Override
    protected double computePrefHeight(double width, double topInset, double rightInset, double bottomInset, double leftInset) {
        return title.prefHeight(width) + topInset + bottomInset;
    }

    @Override
    protected void layoutChildren(double contentX, double contentY, double contentWidth, double contentHeight) {
        super.layoutChildren(contentX, contentY, contentWidth, contentHeight);

        layoutInArea(rect, contentX, contentY, contentWidth / 4, contentHeight, 0,
                HPos.LEFT, VPos.TOP);

        layoutInArea(rect1, contentWidth / (getChildren().size() - 1), contentY, contentWidth / 4, contentHeight, 0,
                HPos.LEFT, VPos.BOTTOM);

        layoutInArea(rect2, (contentWidth / (getChildren().size() - 1)) * 2, contentY, contentWidth / 4, contentHeight, 0,
                HPos.LEFT, VPos.TOP);

        layoutInArea(rect3, (contentWidth / (getChildren().size() - 1)) * 3, contentY, contentWidth / 4, contentHeight, 0,
                HPos.LEFT, VPos.BOTTOM);


        layoutInArea(title, contentX, contentY, contentWidth, contentHeight, 0,
                title.getAlignment().getHpos(), title.getAlignment().getVpos());
    }
}