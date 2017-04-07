package com.schultzz.twoDimension;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.PixelWriter;
import javafx.scene.paint.Color;

/**
 * Created by ms on 06-04-17.
 */
public class GraphPlotter {


    public void resetCanvas(Canvas canvas){
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
    }

    public void plotPoints(Canvas canvas, double xD, double yD) {
        int x = calculatePosition(xD, true);
        int y = calculatePosition(yD, false);
        plotPointOnCanvas(canvas, x, y);
    }

    private int calculatePosition(double point, boolean x) {
        if (x) return (int) (point * 100) + 300;
        else return (int) (300 - (point * 100));
    }

    private void plotPointOnCanvas(Canvas canvas, int x, int y) {
        PixelWriter pw = canvas.getGraphicsContext2D().getPixelWriter();
        pw.setColor(x, y, Color.RED);
        pw.setColor(x - 1, y, Color.RED);
        pw.setColor(x + 1, y, Color.RED);
        pw.setColor(x, y - 1, Color.RED);
        pw.setColor(x, y + 1, Color.RED);
        pw.setColor(x + 1, y - 1, Color.RED);
        pw.setColor(x - 1, y + 1, Color.RED);
        pw.setColor(x - 1, y - 1, Color.RED);
        pw.setColor(x + 1, y + 1, Color.RED);
    }
}
