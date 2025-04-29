package io.github.gleidsonmt.gnbutton;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  29/04/2025
 */
public class Start extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        SwipeButton button = new SwipeButton();
        button.setPrefSize(100, 40);

        CentralizeButton centralizeButton = new CentralizeButton();
        centralizeButton.setPrefSize(100, 40);

        SmoothButton smoothButton = new SmoothButton();
        smoothButton.setPrefSize(100, 40);

        AlternateButton alternate = new AlternateButton();
        alternate.setPrefSize(100, 40);

        SwipeDiagonal swipeDiagonal = new SwipeDiagonal();
        swipeDiagonal.setPrefSize(100, 40);

        CornerButton cornerButton = new CornerButton();
        cornerButton.setPrefSize(100, 40);
//        cornerButton.setAlignment(Pos.CENTER);

        VBox container = new VBox(button, centralizeButton, smoothButton, alternate, swipeDiagonal, cornerButton);

        container.setAlignment(Pos.CENTER);
        container.setSpacing(10);
        stage.setScene(new Scene(container));
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
