package y2015.ex06;

import utils.Point2D;
import utils.SampleReader;

public class Ex06 {

    public static void main(String[] args) {
        String[] data = SampleReader.getTestDataAsLines(Ex06.class);
        part1(data);
        part2(data);
    }

    /**
     * Counting lights for the ideal lighting configuration.
     * Deploying one million lights in a 1000x1000 grid.
     * Instructions include whether to turn on, turn off, or toggle.
     * Each coordinate pair represents opposite corners of a rectangle.
     */
    private static void part1(String[] strings) {

        boolean[][] lightsMatrix = new boolean[1000][1000];

        for (var s : strings) {

            CoordinatesAndOperation coordinatesAndOperation = getCoordinatesAndOperation(s);
            LightsType operation = coordinatesAndOperation.getOperation();
            int aX = coordinatesAndOperation.getA().getX();
            int aY = coordinatesAndOperation.getA().getY();
            int bX = coordinatesAndOperation.getB().getX();
            int bY = coordinatesAndOperation.getB().getY();

            switch (operation) {
                case TOGGLE:
                    for (int i = aX; i <= bX; i++) {
                        for (int j = aY; j <= bY; j++) {
                            lightsMatrix[i][j] = !lightsMatrix[i][j];
                        }
                    }
                    break;
                case TURN_ON:
                    for (int i = aX; i <= bX; i++) {
                        for (int j = aY; j <= bY; j++) {
                            lightsMatrix[i][j] = true;
                        }
                    }
                    break;
                case TURN_OFF:
                    for (int i = aX; i <= bX; i++) {
                        for (int j = aY; j <= bY; j++) {
                            lightsMatrix[i][j] = false;
                        }
                    }
                    break;
                default:
                    break;
            }
        }

        int nLights = 0;
        for (boolean[] matrix : lightsMatrix) {
            for (boolean b : matrix) {
                if (b) {
                    nLights++;
                }
            }
        }

        System.out.println("Lights on: " + nLights);
    }

    /**
     * Counting the brightness of the lights for the ideal lighting configuration.
     * Deploying one million lights in a 1000x1000 grid.
     * Each light can have a brightness of zero or more
     * - turn on means increasing the brightness by 1.
     * - turn off means decreasing the brightness by 1, to a minimum of zero.
     * - toggle means increasing the brightness by 2.
     */
    private static void part2(String[] strings) {

        int[][] lightsMatrix = new int[1000][1000];

        for (var s : strings) {

            CoordinatesAndOperation coordinatesAndOperation = getCoordinatesAndOperation(s);
            LightsType operation = coordinatesAndOperation.getOperation();
            int aX = coordinatesAndOperation.getA().getX();
            int aY = coordinatesAndOperation.getA().getY();
            int bX = coordinatesAndOperation.getB().getX();
            int bY = coordinatesAndOperation.getB().getY();

            switch (operation) {
                case TOGGLE:
                    for (int i = aX; i <= bX; i++) {
                        for (int j = aY; j <= bY; j++) {
                            lightsMatrix[i][j] += 2;
                        }
                    }
                    break;
                case TURN_ON:
                    for (int i = aX; i <= bX; i++) {
                        for (int j = aY; j <= bY; j++) {
                            lightsMatrix[i][j] += 1;
                        }
                    }
                    break;
                case TURN_OFF:
                    for (int i = aX; i <= bX; i++) {
                        for (int j = aY; j <= bY; j++) {
                            if (lightsMatrix[i][j] > 0) {
                                lightsMatrix[i][j] -= 1;
                            }
                        }
                    }
                    break;
                default:
                    break;
            }
        }

        int lightBrightness = 0;
        for (int[] matrix : lightsMatrix) {
            for (int b : matrix) {
                lightBrightness += b;
            }
        }

        System.out.println("Total brightness: " + lightBrightness);
    }

    private static CoordinatesAndOperation getCoordinatesAndOperation(String s) {

        var coordinatesAndOperation = new CoordinatesAndOperation();
        if (s.startsWith("toggle ")) {
            s = s.replace("toggle ", "");
            coordinatesAndOperation.setOperation(LightsType.TOGGLE);
        } else if (s.startsWith("turn off ")) {
            s = s.replace("turn off ", "");
            coordinatesAndOperation.setOperation(LightsType.TURN_OFF);
        } else if (s.startsWith("turn on ")) {
            s = s.replace("turn on ", "");
            coordinatesAndOperation.setOperation(LightsType.TURN_ON);
        } else {
            coordinatesAndOperation.setOperation(LightsType.OTHER);
        }

        s = s.replace(" through ", ",");
        String[] coordinates = s.split(",");

        coordinatesAndOperation.setA(new Point2D(Integer.parseInt(coordinates[0]), Integer.parseInt(coordinates[1])));
        coordinatesAndOperation.setB(new Point2D(Integer.parseInt(coordinates[2]), Integer.parseInt(coordinates[3])));
        return coordinatesAndOperation;
    }

    private enum LightsType {
        TOGGLE,
        TURN_ON,
        TURN_OFF,
        OTHER
    }

    private static class CoordinatesAndOperation {

        private Point2D a;
        private Point2D b;
        private LightsType operation;

        public Point2D getA() {
            return a;
        }

        public void setA(Point2D a) {
            this.a = a;
        }

        public Point2D getB() {
            return b;
        }

        public void setB(Point2D b) {
            this.b = b;
        }

        public LightsType getOperation() {
            return operation;
        }

        public void setOperation(LightsType operation) {
            this.operation = operation;
        }
    }
}
