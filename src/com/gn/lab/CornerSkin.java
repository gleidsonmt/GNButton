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
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
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
public class CornerSkin extends SkinBase<Labeled>{

    private Label title = new Label("Button");

    private Paint firstColor;

    private StackPane rect = ((GNButton) getSkinnable()).rect;
    private StackPane rect1 = new StackPane();
    private StackPane rect2 = new StackPane();
    private StackPane rect3 = new StackPane();

    private ObjectProperty<Duration> velocity = new SimpleObjectProperty<>(this, "velocity");

    CornerSkin(GNButton control) {
        super(control);

        rect.setShape(null);

        rect.setPrefHeight(0);
        rect.setMaxHeight(0);
        rect.setPrefWidth(0);
        rect.setMaxWidth(0);

        rect1.setPrefHeight(0);
        rect1.setMaxHeight(0);
        rect1.setPrefWidth(0);
        rect1.setMaxWidth(0);

        rect2.setPrefHeight(0);
        rect2.setMaxHeight(0);
        rect2.setPrefWidth(0);
        rect2.setMaxWidth(0);


        rect3.setPrefHeight(0);
        rect3.setMaxHeight(0);
        rect3.setPrefWidth(0);
        rect3.setMaxWidth(0);

        getChildren().clear();

        getChildren().add(rect);
        getChildren().add(rect1);
        getChildren().add(rect2);
        getChildren().add(rect3);
        getChildren().add(title);

        velocity.bind( ((GNButton)getSkinnable()).transitionDurationProperty());
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

        Rectangle clip = new Rectangle();
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

        getSkinnable().setOnMouseEntered(event -> {
            timeEntered.getKeyFrames().clear();

            timeEntered.getKeyFrames().addAll(

                    /*************************************************************
                     *
                     *          First rect
                     *
                     ***********************************************************/

                    new KeyFrame(Duration.ZERO, new KeyValue(rect.prefWidthProperty(), rect.getWidth())),
                    new KeyFrame(Duration.ZERO, new KeyValue(rect.minWidthProperty(), rect.getWidth())),

                    new KeyFrame(velocity.get(), new KeyValue(rect.prefWidthProperty(), getSkinnable().getWidth() / 2)),
                    new KeyFrame(velocity.get(), new KeyValue(rect.minWidthProperty(), getSkinnable().getWidth() / 2)),

                    new KeyFrame(Duration.ZERO, new KeyValue(rect.minHeightProperty(), rect.getHeight())),
                    new KeyFrame(Duration.ZERO, new KeyValue(rect.prefHeightProperty(), rect.getHeight())),

                    new KeyFrame(velocity.get(), new KeyValue(rect.minHeightProperty(), getSkinnable().getHeight()/ 2)),
                    new KeyFrame(velocity.get(), new KeyValue(rect.prefHeightProperty(), getSkinnable().getHeight()/ 2)),

                    /*************************************************************
                     *
                     *          Second rect
                     *
                     ***********************************************************/

                    new KeyFrame(Duration.ZERO, new KeyValue(rect1.prefWidthProperty(), rect1.getWidth())),
                    new KeyFrame(Duration.ZERO, new KeyValue(rect1.minWidthProperty(), rect1.getWidth())),

                    new KeyFrame(velocity.get(), new KeyValue(rect1.prefWidthProperty(), getSkinnable().getWidth() / 2)),
                    new KeyFrame(velocity.get(), new KeyValue(rect1.minWidthProperty(), getSkinnable().getWidth() / 2)),

                    new KeyFrame(Duration.ZERO, new KeyValue(rect1.minHeightProperty(), rect.getHeight())),
                    new KeyFrame(Duration.ZERO, new KeyValue(rect1.prefHeightProperty(), rect.getHeight())),

                    new KeyFrame(velocity.get(), new KeyValue(rect1.minHeightProperty(), getSkinnable().getHeight()/ 2)),
                    new KeyFrame(velocity.get(), new KeyValue(rect1.prefHeightProperty(), getSkinnable().getHeight()/ 2)),


                    /*************************************************************
                     *
                     *          Third rect
                     *
                     ***********************************************************/

                    new KeyFrame(Duration.ZERO, new KeyValue(rect2.prefWidthProperty(), rect2.getWidth())),
                    new KeyFrame(Duration.ZERO, new KeyValue(rect2.minWidthProperty(), rect2.getWidth())),

                    new KeyFrame(velocity.get(), new KeyValue(rect2.prefWidthProperty(), getSkinnable().getWidth() / 2)),
                    new KeyFrame(velocity.get(), new KeyValue(rect2.minWidthProperty(), getSkinnable().getWidth() / 2)),

                    new KeyFrame(Duration.ZERO, new KeyValue(rect2.minHeightProperty(), rect2.getHeight())),
                    new KeyFrame(Duration.ZERO, new KeyValue(rect2.prefHeightProperty(), rect2.getHeight())),

                    new KeyFrame(velocity.get(), new KeyValue(rect2.minHeightProperty(), getSkinnable().getHeight()/ 2)),
                    new KeyFrame(velocity.get(), new KeyValue(rect2.prefHeightProperty(), getSkinnable().getHeight()/ 2)),


                    /*************************************************************
                     *
                     *          Third rect
                     *
                     ***********************************************************/

                    new KeyFrame(Duration.ZERO, new KeyValue(rect3.prefWidthProperty(), rect3.getWidth())),
                    new KeyFrame(Duration.ZERO, new KeyValue(rect3.minWidthProperty(), rect3.getWidth())),

                    new KeyFrame(velocity.get(), new KeyValue(rect3.prefWidthProperty(), getSkinnable().getWidth() / 2)),
                    new KeyFrame(velocity.get(), new KeyValue(rect3.minWidthProperty(), getSkinnable().getWidth() / 2)),

                    new KeyFrame(Duration.ZERO, new KeyValue(rect3.minHeightProperty(), rect3.getHeight())),
                    new KeyFrame(Duration.ZERO, new KeyValue(rect3.prefHeightProperty(), rect3.getHeight())),

                    new KeyFrame(velocity.get(), new KeyValue(rect3.minHeightProperty(), getSkinnable().getHeight()/ 2)),
                    new KeyFrame(velocity.get(), new KeyValue(rect3.prefHeightProperty(), getSkinnable().getHeight()/ 2)),

                    /*************************************************************
                     *
                     *          Text
                     *
                     ***********************************************************/


                    new KeyFrame(Duration.ZERO, new KeyValue(getSkinnable().textFillProperty(), getSkinnable().getTextFill())),
                    new KeyFrame(velocity.get(), new KeyValue(getSkinnable().textFillProperty(), ((GNButton) getSkinnable()).getTransitionText()))


            );

            if (timeExited.getStatus() == Animation.Status.RUNNING) {
                timeExited.stop();
            }

            timeEntered.play();

        });

        getSkinnable().setOnMouseExited(event -> {
            timeExited.getKeyFrames().clear();
            timeExited.getKeyFrames().addAll(

                    /*************************************************************
                     *
                     *          First rect
                     *
                     ***********************************************************/

                    new KeyFrame(Duration.ZERO, new KeyValue(rect.prefWidthProperty(), rect.getWidth())),
                    new KeyFrame(Duration.ZERO, new KeyValue(rect.minWidthProperty(), rect.getWidth())),

                    new KeyFrame(velocity.get(), new KeyValue(rect.prefWidthProperty(), 0D)),
                    new KeyFrame(velocity.get(), new KeyValue(rect.minWidthProperty(), 0D)),

                    new KeyFrame(Duration.ZERO, new KeyValue(rect.minHeightProperty(), rect.getHeight())),
                    new KeyFrame(Duration.ZERO, new KeyValue(rect.prefHeightProperty(), rect.getHeight())),

                    new KeyFrame(velocity.get(), new KeyValue(rect.minHeightProperty(), 0D)),
                    new KeyFrame(velocity.get(), new KeyValue(rect.prefHeightProperty(), 0D)),


                    /*************************************************************
                     *
                     *          Second rect
                     *
                     ***********************************************************/

                    new KeyFrame(Duration.ZERO, new KeyValue(rect1.prefWidthProperty(), rect1.getWidth())),
                    new KeyFrame(Duration.ZERO, new KeyValue(rect1.minWidthProperty(), rect1.getWidth())),

                    new KeyFrame(velocity.get(), new KeyValue(rect1.prefWidthProperty(), 0D)),
                    new KeyFrame(velocity.get(), new KeyValue(rect1.minWidthProperty(), 0D)),

                    new KeyFrame(Duration.ZERO, new KeyValue(rect1.minHeightProperty(), rect1.getHeight())),
                    new KeyFrame(Duration.ZERO, new KeyValue(rect1.prefHeightProperty(), rect1.getHeight())),

                    new KeyFrame(velocity.get(), new KeyValue(rect1.minHeightProperty(), 0D)),
                    new KeyFrame(velocity.get(), new KeyValue(rect1.prefHeightProperty(), 0D)),

                    /*************************************************************
                     *
                     *          Third rect
                     *
                     ***********************************************************/

                    new KeyFrame(Duration.ZERO, new KeyValue(rect2.prefWidthProperty(), rect2.getWidth())),
                    new KeyFrame(Duration.ZERO, new KeyValue(rect2.minWidthProperty(), rect2.getWidth())),

                    new KeyFrame(velocity.get(), new KeyValue(rect2.prefWidthProperty(), 0D)),
                    new KeyFrame(velocity.get(), new KeyValue(rect2.minWidthProperty(), 0D)),

                    new KeyFrame(Duration.ZERO, new KeyValue(rect2.minHeightProperty(), rect2.getHeight())),
                    new KeyFrame(Duration.ZERO, new KeyValue(rect2.prefHeightProperty(), rect2.getHeight())),

                    new KeyFrame(velocity.get(), new KeyValue(rect2.minHeightProperty(), 0D)),
                    new KeyFrame(velocity.get(), new KeyValue(rect2.prefHeightProperty(), 0D)),

                    /*************************************************************
                     *
                     *          Fourth rect
                     *
                     ***********************************************************/

                    new KeyFrame(Duration.ZERO, new KeyValue(rect3.prefWidthProperty(), rect3.getWidth())),
                    new KeyFrame(Duration.ZERO, new KeyValue(rect3.minWidthProperty(), rect3.getWidth())),

                    new KeyFrame(velocity.get(), new KeyValue(rect3.prefWidthProperty(), 0D)),
                    new KeyFrame(velocity.get(), new KeyValue(rect3.minWidthProperty(), 0D)),

                    new KeyFrame(Duration.ZERO, new KeyValue(rect3.minHeightProperty(), rect3.getHeight())),
                    new KeyFrame(Duration.ZERO, new KeyValue(rect3.prefHeightProperty(), rect3.getHeight())),

                    new KeyFrame(velocity.get(), new KeyValue(rect3.minHeightProperty(), 0D)),
                    new KeyFrame(velocity.get(), new KeyValue(rect3.prefHeightProperty(), 0D)),

                    /*************************************************************
                     *
                     *          Text
                     *
                     ***********************************************************/

                    new KeyFrame(Duration.ZERO, new KeyValue(getSkinnable().textFillProperty(), getSkinnable().getTextFill())),
                    new KeyFrame(velocity.get(), new KeyValue(getSkinnable().textFillProperty(), firstColor))

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

        layoutInArea(rect, contentX, contentY, contentWidth / 2, contentHeight, 0,
                HPos.LEFT, VPos.TOP);

        layoutInArea(rect1, contentX, contentY, contentWidth / 2, contentHeight, 0,
                HPos.LEFT, VPos.BOTTOM);

        layoutInArea(rect2, (contentWidth + snappedRightInset()) / 2, contentY, contentWidth / 2, contentHeight, 0,
                HPos.RIGHT, VPos.TOP);

        layoutInArea(rect3, (contentWidth + snappedRightInset()) / 2, contentY, contentWidth / 2, contentHeight, 0,
                HPos.RIGHT, VPos.BOTTOM);


        layoutInArea(title, contentX, contentY, contentWidth, contentHeight, 0,
                title.getAlignment().getHpos(), title.getAlignment().getVpos());
    }
}