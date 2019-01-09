package app;

import com.gn.lab.ButtonType;
import com.gn.lab.GNButton;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Screen;
import javafx.stage.Stage;
import org.scenicview.ScenicView;

public class Demo extends Application {

    @Override
    public void start(Stage primaryStage) {

        double width = Screen.getPrimary().getBounds().getWidth();
        double height = Screen.getPrimary().getVisualBounds().getHeight();

        double btnWidth = 200;
        double btnHeigh = 50;

        StackPane root = new StackPane();
        root.setAlignment(Pos.CENTER);
//        root.setPadding(new Insets(100,100 , 100, 100));


        GNButton swipe = new GNButton("Swipe");
        GNButton swipe_diagonal = new GNButton("Swipe Diagonal");
        GNButton centralize = new GNButton("Centralize");
        GNButton smoosh = new GNButton("Smoosh");
        GNButton alternate = new GNButton("Alternate");
        GNButton corners = new GNButton("Corners");

        swipe.setButtonType(ButtonType.SWIPE);
        swipe_diagonal.setButtonType(ButtonType.DIAGONAL_SWIPE);
        centralize.setButtonType(ButtonType.CENTRALIZE);
        smoosh.setButtonType(ButtonType.SMOOSH);
        alternate.setButtonType(ButtonType.ALTERNATE);
        corners.setButtonType(ButtonType.CORNERS);

        swipe.setPrefSize(btnWidth, btnHeigh);
        swipe_diagonal.setPrefSize(btnWidth, btnHeigh);
        centralize.setPrefSize(btnWidth, btnHeigh);
        smoosh.setPrefSize(btnWidth, btnHeigh);
        alternate.setPrefSize(btnWidth, btnHeigh);
        corners.setPrefSize(btnWidth, btnHeigh);

        GridPane grid = new GridPane();
        grid.setHgap(20D);
        grid.setAlignment(Pos.CENTER);

        grid.add(swipe, 0, 0);
        grid.add(centralize, 1, 0);
        grid.add(swipe_diagonal, 2, 0);
        grid.add(smoosh, 3,0);
        grid.add(alternate, 4,0);
        grid.add(corners, 5,0);

        root.getChildren().add(grid);

        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, width, height));
        primaryStage.show();

        new Button();

    }

    public static void main(String[] args) {
        launch(args);
    }
}
