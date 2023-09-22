package y2015.ex05;

import utils.SampleReader;

import java.util.ArrayList;
import java.util.List;

public class Ex05 {

    public static void main(String[] args) {
        String[] data = SampleReader.getTestDataAsLines(Ex05.class);
        part1(data);
        part2(data);
    }

    /**
     * Counting nice strings.
     * It contains at least three vowels (aeiou only), like aei, xazegov, or aeiouaeiouaeiou.
     * It contains at least one letter that appears twice in a row, like xx, abcdde (dd), or aabbccdd (aa, bb, cc, or dd).
     * It does not contain the strings ab, cd, pq, or xy, even if they are part of one of the other requirements.
     */
    private static void part1(String[] data) {

        int niceStrings = 0;

        for (String s : data) {

            int nVowels = 0;
            boolean hasVowels = false;
            boolean letterTwice = false;

            char previousChar = '\t';
            for (var c : s.toCharArray()) {
                if (c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u') {
                    nVowels++;
                }

                if (c == previousChar) {
                    letterTwice = true;
                }
                previousChar = c;
            }
            if (nVowels > 2) {
                hasVowels = true;
            }
            boolean containString = s.contains("ab") || s.contains("cd") || s.contains("pq") || s.contains("xy");

            if (hasVowels && letterTwice && !containString) {
                niceStrings++;
            }
        }

        System.out.println("Nice strings: " + niceStrings);
    }

    /**
     * Counting nice strings.
     * It contains a pair of any two letters that appears at least twice in the string without overlapping, like xyxy (xy) or aabcdefgaa (aa), but not like aaa (aa, but it overlaps).
     * It contains at least one letter which repeats with exactly one letter between them, like xyx, abcdefeghi (efe), or even aaa.
     */
    private static void part2(String[] data) {

        int niceStrings = 0;
        for (String s : data) {

            char[] chars = s.toCharArray();

            boolean pairRepeated = false;
            boolean letterRepeated = false;
            List<String> pairs = new ArrayList<>();
            String previousPair = "";
            for (var i = 0; i < chars.length; i++) {

                if (i >= 2 && !letterRepeated) {
                    letterRepeated = chars[i - 2] == chars[i];
                }

                if (i >= 1) {
                    String pair = "" + chars[i - 1] + chars[i];
                    if (!pair.equals(previousPair)) {
                        if (pairs.contains(pair)) {
                            pairRepeated = true;
                        }
                        pairs.add(pair);
                    }
                    previousPair = pair;
                }
            }

            if (letterRepeated && pairRepeated) {
                niceStrings++;
            }
        }
        System.out.println("Nice strings: " + niceStrings);
    }
}
