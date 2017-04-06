package com.schultzz.twoDimension;

import javafx.scene.canvas.Canvas;

public class Main {

    public static void main(String[] args) {
        PSOLogic logic = new PSOLogic(new Canvas());
        logic.runner();
    }
}
