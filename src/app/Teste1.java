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
package app;

import com.gn.lab.ButtonType;
import com.gn.lab.GNButton;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import org.scenicview.ScenicView;

import java.util.Scanner;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  19/12/2018
 */
public class Teste1 extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        StackPane root = new StackPane();
        root.setPadding(new Insets(100,100 , 100, 200));

        GNButton btn = new GNButton();
//        btn.setButtonType(ButtonType.DIAGONAL_SWIPE);

        root.getChildren().add(btn);

        stage.setTitle("Hello World");
        stage.setScene(new Scene(root, 600, 600));
        stage.show();

        ScenicView.show(stage.getScene());
    }

    public static void main(String[] args) {
        launch(args);
    }
}
