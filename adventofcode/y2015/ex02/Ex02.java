package y2015.ex02;


import utils.SampleReader;

import java.util.Arrays;

public class Ex02 {

    public static void main(String[] args) {
        String[] data = SampleReader.getTestDataAsLines(Ex02.class);
        part1(data);
        part2(data);
    }

    /**
     * Amount of wrapping paper.
     * Every present is a perfect right rectangular prism.
     * Total paper area is the area of the box, which is 2*l*w + 2*w*h + 2*h*l, plus the area of the smallest side.
     */
    private static void part1(String[] data) {

        int totalPaper = 0;
        for (var s : data) {

            String[] lwh = s.split("x");
            int l = Integer.parseInt(lwh[0]);
            int w = Integer.parseInt(lwh[1]);
            int h = Integer.parseInt(lwh[2]);

            int[] sides = new int[]{l, w, h};
            Arrays.sort(sides);

            int lw = l * w;
            int wh = w * h;
            int hl = h * l;

            totalPaper += 2 * lw + 2 * wh + 2 * hl + sides[0] * sides[1];
        }
        System.out.println("Total paper: " + totalPaper);
    }

    /**
     * Amount of ribbon.
     * Total ribbon required is the shortest distance around its sides plus the volume of the present (for the bow).
     */
    private static void part2(String[] data) {

        int totalRibbon = 0;
        for (var s : data) {

            String[] lwh = s.split("x");
            int l = Integer.parseInt(lwh[0]);
            int w = Integer.parseInt(lwh[1]);
            int h = Integer.parseInt(lwh[2]);

            int[] sides = new int[]{l, w, h};
            Arrays.sort(sides);

            int lw = l * w;

            totalRibbon += 2 * sides[0] + 2 * sides[1] + lw * h;
        }
        System.out.println("Total ribbon: " + totalRibbon);
    }
}
