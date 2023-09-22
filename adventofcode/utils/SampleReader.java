package utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

public final class SampleReader {

    private static final String SAMPLE_FILE = "sample";
    private SampleReader() {}

    /**
     * Reads the sample file of a given exercise class as a String.
     *
     * @return the contents of the file as a String
     */
    public static String getTestDataAsString(Class<?> clazz) {
        try (InputStream is = clazz.getResourceAsStream(SAMPLE_FILE)) {
            if (is == null) {
                return null;
            }
            try (var isr = new InputStreamReader(is);
                var reader = new BufferedReader(isr)) {
                return reader.lines().collect(Collectors.joining(System.lineSeparator()));
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Reads the sample file of a given exercise class as a char array.
     *
     * @return the contents of the file as a char array
     */
    public static char[] getTestDataAsCharArray(Class<?> clazz){
        var testData = getTestDataAsString(clazz);
        assert testData != null;
        return testData.toCharArray();
    }

    /**
     * Reads the sample file of a given exercise class as a String array of lines.
     *
     * @return the contents of the file as a String array
     */
    public static String[] getTestDataAsLines(Class<?> clazz){
        var testData = getTestDataAsString(clazz);
        assert testData != null;
        return testData.split(System.lineSeparator());
    }
}
