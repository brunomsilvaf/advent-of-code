package y2015.ex03;

import utils.SampleReader;

import java.util.ArrayList;
import java.util.List;

public class Ex03 {

    public static void main(String[] args) {
        char[] data = SampleReader.getTestDataAsCharArray(Ex03.class);
        part1(data);
        part2(data);
    }

    /**
     * Counting houses that receive at least one present.
     * Beginning of the delivery in the house at the starting location.
     * Next move based on north (^), south (v), east (>), or west (<).
     */
    private static void part1(char[] data) {

        int xSanta = 0;
        int ySanta = 0;

        List<String> positions = new ArrayList<>();
        positions.add(toPosition(xSanta, ySanta));

        for (var c : data) {

            switch (c) {
                case '>':
                    xSanta++;
                    break;
                case '<':
                    xSanta--;
                    break;
                case '^':
                    ySanta++;
                    break;
                case 'v':
                    ySanta--;
                    break;
                default:
                    break;
            }

            String position = toPosition(xSanta, ySanta);
            if (!positions.contains(position)) {
                positions.add(position);
            }
        }
        System.out.println("Total houses: " + positions.size());
    }

    /**
     * Counting houses that receive at least one present with a robot helper.
     * Robot starts at the same location.
     * Robot and Santa take turns.
     */
    private static void part2(char[] data) {

        int xSanta = 0;
        int ySanta = 0;
        int xRobot = 0;
        int yRobot = 0;

        List<String> positions = new ArrayList<>();
        positions.add(toPosition(xSanta, ySanta));

        int counter = 0;
        for (char c : data) {
            counter++;
            boolean isSanta = counter % 2 != 0;

            switch (c) {
                case '>':
                    if (isSanta) {
                        xSanta++;
                    } else {
                        xRobot++;
                    }
                    break;
                case '<':
                    if (isSanta) {
                        xSanta--;
                    } else {
                        xRobot--;
                    }
                    break;
                case '^':
                    if (isSanta) {
                        ySanta++;
                    } else {
                        yRobot++;
                    }
                    break;
                case 'v':
                    if (isSanta) {
                        ySanta--;
                    } else {
                        yRobot--;
                    }
                    break;
                default:
                    break;
            }

            String position = toPosition(isSanta ? xSanta : xRobot, isSanta ? ySanta : yRobot);
            if (!positions.contains(position)) {
                positions.add(position);
            }
        }
        System.out.println("Total houses: " + positions.size());
    }

    private static String toPosition(int x, int y) {
        return x + "-" + y;
    }
}
