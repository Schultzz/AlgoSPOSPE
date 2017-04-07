package com.schultzz.fourDimension;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by ms on 06-04-17.
 */
public class PSOLogic {

    private double globalBest;
    private double funcMaxValue;
    private double[] globalBestCor;
    private int swarmSize = 5;
    private int maxIteration = 150;
    private static final double X_LOW = -2;
    private static final double X_HIGH = 2;
    private static final double Y_LOW = -2;
    private static final double Y_HIGH = 2;
    private static final double U_LOW = -2;
    private static final double U_HIGH = 2;
    private static final double W_LOW = -2;
    private static final double W_HIGH = 2;
    private static final double VEL_LOW = -1;
    private static final double VEL_HIGH = 1;
    private static final double P1 = 2.00;
    private static final double P2 = 2.00;
    private static final double W_UPPERBOUND = 0.9;
    private static final double W_LOWERBOUND = 0.4;
    private List<Bee> swarm = new ArrayList<>();


    public void runner() {
        initSwarm();
        updateFitnessValues(swarm, 0);

        for (int t = 0; t < maxIteration; t++) {
            calculateGlobalBest(swarm);
            calculateVelocity(swarm, t);
            updateFitnessValues(swarm, t);
//            printSwarm();
        }
        printSwarm();
        System.out.println("Max value of the function: " + funcMaxValue);
    }

    private void calculateVelocity(List<Bee> swarm, int t) {
        Random random = new Random();
        double w = W_UPPERBOUND - (((double) t / maxIteration) * (W_UPPERBOUND - W_LOWERBOUND));

        for (Bee bee : swarm) {
            double r1 = random.nextDouble();
            double r2 = random.nextDouble();
            double[] velocityArr = new double[4];

            velocityArr[0] = (w * bee.get_velocity()[0]) +
                    (r1 * P1) * (bee.getCorBest()[0] - bee.getLocation()[0]) + (r2 * P2) * (globalBestCor[0] - bee.getLocation()[0]);

            velocityArr[1] = (w * bee.get_velocity()[1]) +
                    (r1 * P1) * (bee.getCorBest()[1] - bee.getLocation()[1]) + (r2 * P2) * (globalBestCor[1] - bee.getLocation()[1]);

            velocityArr[2] = (w * bee.get_velocity()[2]) +
                    (r1 * P1) * (bee.getCorBest()[2] - bee.getLocation()[2]) + (r2 * P2) * (globalBestCor[2] - bee.getLocation()[2]);

            velocityArr[3] = (w * bee.get_velocity()[1]) +
                    (r1 * P1) * (bee.getCorBest()[3] - bee.getLocation()[3]) + (r2 * P2) * (globalBestCor[3] - bee.getLocation()[3]);

            double[] newLocation = new double[]{
                    bee.getLocation()[0] + velocityArr[0],
                    bee.getLocation()[1] + velocityArr[1],
                    bee.getLocation()[2] + velocityArr[2],
                    bee.getLocation()[3] + velocityArr[3]
            };


            bee.set_velocity(velocityArr);
            bee.setLocation(newLocation);

        }

    }

    private void updateFitnessValues(List<Bee> swarm, int t) {

        for (Bee bee : swarm) {
            double fitnessValue = calculateFitnessValue(bee);
            if (bee.getPBest() > fitnessValue || t == 0) {
                bee.setPBest(fitnessValue);
                double[] corBest = {
                        bee.getLocation()[0],
                        bee.getLocation()[1],
                        bee.getLocation()[2],
                        bee.getLocation()[3]
                };
                bee.setCorBest(corBest);
            }
        }

    }

    private void calculateGlobalBest(List<Bee> swarm) {

        double tempBest, beeBest;
        double[] tempBestLoc;

        tempBest = swarm.get(0).getPBest();
        tempBestLoc = new double[]{
                swarm.get(0).getLocation()[0],
                swarm.get(0).getLocation()[1],
                swarm.get(0).getLocation()[2],
                swarm.get(0).getLocation()[3]
        };

        for (int i = 1; i < swarm.size(); i++) {
            beeBest = swarm.get(i).getPBest();
            if (beeBest < tempBest) {
                tempBest = swarm.get(i).getPBest();
                tempBestLoc = new double[]{
                        swarm.get(i).getLocation()[0],
                        swarm.get(i).getLocation()[1],
                        swarm.get(i).getLocation()[2],
                        swarm.get(i).getLocation()[3]

                };
            }
        }

        globalBest = tempBest;
        globalBestCor = tempBestLoc;
    }

    public void initSwarm() {

        Random rand = new Random();
        Bee bee;

        for (int i = 0; i < this.swarmSize; i++) {

            double[] location = new double[]{
                    X_LOW + (X_HIGH - X_LOW) * rand.nextDouble(),
                    Y_LOW + (Y_HIGH - Y_LOW) * rand.nextDouble(),
                    U_LOW + (U_HIGH - U_LOW) * rand.nextDouble(),
                    W_LOW + (W_HIGH - W_LOW) * rand.nextDouble(),
            };

            double[] velocity = new double[]{
                    VEL_LOW + (VEL_HIGH - VEL_LOW) * rand.nextDouble(),
                    VEL_LOW + (VEL_HIGH - VEL_LOW) * rand.nextDouble(),
                    VEL_LOW + (VEL_HIGH - VEL_LOW) * rand.nextDouble(),
                    VEL_LOW + (VEL_HIGH - VEL_LOW) * rand.nextDouble()
            };

            bee = new Bee();
            bee.setLocation(location);
            bee.set_velocity(velocity);

            swarm.add(bee);
        }

        printSwarm();


    }

    public void printSwarm() {

        for (Bee p : swarm) {
            System.out.println("X: " + p.getLocation()[0] + " Y: " + p.getLocation()[1] + " U: " + p.getLocation()[2] + " W: " + p.getLocation()[3]);
        }
//        if (globalBestCor != null) {
//            System.out.println(
//                    "gBest X: " + globalBestCor[0] +
//                            " gBest Y: " + new BigDecimal(globalBestCor[1]).toPlainString() +
//                            " gBest u: " + globalBestCor[2] +
//                            " gBest w: " + globalBestCor[3]
//            );

//        }
        System.out.println("----------------------------------------------");
    }

    public double calculateFitnessValue(Bee p) {
        double result = 0;
        double x = p.getLocation()[0];
        double y = p.getLocation()[1];
        double u = p.getLocation()[2];
        double w = p.getLocation()[3];

        result = 2 * x * Math.exp(-x * x - y * y - (u - 1) * (u - 1) - w * w);

        if (result > funcMaxValue) {
            funcMaxValue = result;
        }

        return result;
    }
}
