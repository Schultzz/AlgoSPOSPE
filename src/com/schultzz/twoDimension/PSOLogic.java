package com.schultzz.twoDimension;

import javafx.scene.canvas.Canvas;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by ms on 06-04-17.
 */
public class PSOLogic {

    private GraphPlotter plotter;
    private Canvas canvas;

    private double globalBest;
    private double maxDistance;
    private double[] globalBestCor;
    private int swarmSize;
    private int maxIteration;

    private final double X_LOW = -2;
    private final double X_HIGH = 2;
    private final double Y_LOW = -2;
    private final double Y_HIGH = 2;
    private final double VEL_LOW = -1;
    private final double VEL_HIGH = 1;
    private final double P1 = 2.00;
    private final double P2 = 2.00;
    private final double W_UPPERBOUND = 0.9;
    private final double W_LOWERBOUND = 0.4;

    private List<Bee> swarm = new ArrayList<>();

    public PSOLogic(Canvas canvas, int swarmSize, int maxIteration) {
        plotter = new GraphPlotter();
        this.canvas = canvas;
        this.swarmSize = swarmSize;
        this.maxIteration = maxIteration;
        //plotter.resetCanvas(canvas);
    }

    public void run() {
        initSwarm();
        updateFitnessValues(swarm, 0);

        for (int t = 0; t < maxIteration; t++) {
            calculateGlobalBest(swarm);
            calculateVelocity(swarm, t);
            updateFitnessValues(swarm, t);
//            printSwarm();
        }
        printSwarm();
    }

    private void calculateVelocity(List<Bee> swarm, int t) {
        Random random = new Random();
        double w = W_UPPERBOUND - (((double) t / maxIteration) * (W_UPPERBOUND - W_LOWERBOUND));

        for (Bee bee : swarm) {
            double r1 = random.nextDouble();
            double r2 = random.nextDouble();
            double[] velocityArr = new double[2];

            velocityArr[0] = (w * bee.get_velocity()[0]) +
                    (r1 * P1) * (bee.getCorBest()[0] - bee.get_xLoc()) + (r2 * P2) * (globalBestCor[0] - bee.get_xLoc());

            velocityArr[1] = (w * bee.get_velocity()[1]) +
                    (r1 * P1) * (bee.getCorBest()[1] - bee.get_yLoc()) + (r2 * P2) * (globalBestCor[1] - bee.get_yLoc());

            bee.set_velocity(velocityArr);

            bee.set_xLoc(bee.get_xLoc() + bee.get_velocity()[0]);
            bee.set_yLoc(bee.get_yLoc() + bee.get_velocity()[1]);

            plotter.plotPoints(canvas, bee.get_xLoc(), bee.get_yLoc());

        }

    }

    private void updateFitnessValues(List<Bee> swarm, int t) {

        for (Bee bee : swarm) {
            double fitnessValue = calculateFitnessValue(bee);
            if (bee.getPBest() > fitnessValue || t == 0) {
                bee.setPBest(fitnessValue);
                double[] corBest = {bee.get_xLoc(), bee.get_yLoc()};
                bee.setCorBest(corBest);
            }
        }

    }

    private void calculateGlobalBest(List<Bee> swarm) {

        double tempBest, beeBest;
        double[] tempBestLoc;

        tempBest = swarm.get(0).getPBest();
        tempBestLoc = new double[]{swarm.get(0).get_xLoc(), swarm.get(0).get_yLoc()};

        for (int i = 1; i < swarm.size(); i++) {
            beeBest = swarm.get(i).getPBest();
            if (beeBest < tempBest) {
                tempBest = swarm.get(i).getPBest();
                tempBestLoc = new double[]{swarm.get(i).get_xLoc(), swarm.get(i).get_yLoc()};
            }
        }

        globalBest = tempBest;
        globalBestCor = tempBestLoc;
    }

    public void initSwarm() {

        Random rand = new Random();
        double locX = 0;
        double locY = 0;
        Bee bee;

        for (int i = 0; i < this.swarmSize; i++) {

            locX = X_LOW + (X_HIGH - X_LOW) * rand.nextDouble();
            locY = Y_LOW + (Y_HIGH - Y_LOW) * rand.nextDouble();

            double[] velocity = new double[]{VEL_LOW + (VEL_HIGH - VEL_LOW) * rand.nextDouble(),
                    VEL_LOW + (VEL_HIGH - VEL_LOW) * rand.nextDouble()};

            bee = new Bee();
            bee.set_xLoc(locX);
            bee.set_yLoc(locY);
            bee.set_velocity(velocity);

            swarm.add(bee);
        }

        //printSwarm();


    }

    public void printSwarm() {

        for (Bee p : swarm) {
//            System.out.println("X: " + p.get_xLoc() + " Y: " + p.get_yLoc());
            for (Bee bee : swarm) {
                double distance = Math.sqrt((Math.pow(bee.get_xLoc() - p.get_xLoc(), 2)) + (Math.pow(bee.get_yLoc() - p.get_yLoc(), 2)));
                if (distance > maxDistance) {
                    maxDistance = distance;
                }
            }

        }

        if (globalBestCor != null) {
            System.out.println("gBest X: " + globalBestCor[0] + " gBest Y: " +
                    new BigDecimal(globalBestCor[1]).toPlainString());

        }
        System.out.println("Maximum distance between the swarm particles " + maxDistance);
        System.out.println("----------------------------------------------");
    }

    public double calculateFitnessValue(Bee p) {
        double result = 0;
        double x = p.get_xLoc();
        double y = p.get_yLoc();

        result = x * Math.exp(-x * x - y * y);

        return result;
    }

}
