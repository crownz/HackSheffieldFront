package io.mlh.utilities;

public class WordUtils {

    private WordUtils() {

    }

    public static String toCamelCase(String s){
        String[] parts = s.split(" ");
        String camelCaseString = "";
        for (String part : parts){
            camelCaseString = camelCaseString + toProperCase(part);
        }

        camelCaseString = camelCaseString.substring(0, 1).toLowerCase() + camelCaseString.substring(1, camelCaseString.length());
        return camelCaseString;
    }

    static String toProperCase(String s) {
        return s.substring(0, 1).toUpperCase() +
                s.substring(1).toLowerCase();
    }
}
