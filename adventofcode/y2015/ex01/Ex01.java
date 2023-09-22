package y2015.ex01;

import utils.SampleReader;

public class Ex01 {

    public static void main(String[] args) {
        char[] data = SampleReader.getTestDataAsCharArray(Ex01.class);
        part1(data);
        part2(data);
    }

    /**
     * Finding the final floor.
     * Starting floor is 0.
     * An opening parenthesis '(' means up one floor and a closing parenthesis ')' means down one floor.
     */
    private static void part1(char[] data) {

        int level = 0;
        for (var c : data) {
            if (c == '(') {
                level++;
            } else if (c == ')') {
                level--;
            }
        }
        System.out.println("Final position: " + level);
    }

    /**
     * Finding the position of the first character that causes entering the basement (floor -1)
     */
    private static void part2(char[] data) {

        boolean firstBasement = false;
        int counter = 0;
        int level = 0;
        for (var c : data) {
            counter++;

            if (c == '(') {
                level++;
            } else if (c == ')') {
                level--;
            }

            //basement
            if (level < 0 && !firstBasement) {
                firstBasement = true;
                System.out.println("Basement at position: " + counter);
            }
        }
    }
}
