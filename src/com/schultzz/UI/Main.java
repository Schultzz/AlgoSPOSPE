package com.schultzz.UI;

import com.schultzz.twoDimension.PSOLogic;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Canvas sharpCanvas = createCanvas(600, 600);
        VBox root = new VBox(5, sharpCanvas);
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
        PSOLogic psoLogic = new PSOLogic(sharpCanvas, 15, 100);
        psoLogic.run();
    }

    private Canvas createCanvas(int width, int height) {
        Canvas canvas = new Canvas(width, height);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.setLineWidth(1.0);


        int number = 4;
        for (int x = 0; x < height; x += 100) {
            gc.fillText("" + (number - 1), width / 2, x);
            gc.moveTo(width / 2, x);
            gc.lineTo(width / 2, (x + 100));

            gc.stroke();
            number--;
        }

        number = -2;
        for (int y = 0; y < width; y += 100) {
            if (y != 0) {
                gc.fillText("" + (number - 1), y, height / 2);
            }
            gc.moveTo(0, height / 2);
            gc.lineTo((y + 100), height / 2);

            gc.stroke();
            number++;
        }

        return canvas;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
