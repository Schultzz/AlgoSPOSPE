package com.schultzz.twoDimension;

import javafx.scene.canvas.Canvas;

public class Main {

    public static void main(String[] args) {

        int[] swarmSizes = new int[]{5, 10, 15, 20, 25, 30, 35, 40};
        int[] iterations = new int[]{80, 100, 120, 140, 160, 180, 200, 220};

        for (int i = 0; i < swarmSizes.length; i++) {

            System.out.println("Swarm size: " + swarmSizes[i] + " " + " Iterations: " + iterations[i]);
            PSOLogic logic = new PSOLogic(new Canvas(), swarmSizes[i], iterations[i]);
            logic.run();

        }


    }
}
