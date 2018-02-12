package com.alyaromin.textfilter;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    private static List<Pattern> patterns;
    private static List<String> lines;
    private static List<String> filteredLines;

    public static void main(String[] args) {

        transformArgsToPatternList(args);

        readLines();

        filterLinesWithPatterns();

        printFilteredLines();

    }

    private static void transformArgsToPatternList(String[] args) {
        patterns = new ArrayList<Pattern>();

        for (String arg :
                args) {
            patterns.add(Pattern.compile(arg));
        }
    }

    private static void readLines() {
        System.out.println("Строки:");
        Scanner scanner = new Scanner(System.in);
        lines = new ArrayList<String>();

        for (int i = 0; true; i++) {
            String line = scanner.nextLine();

            if (line.length() == 0) {
                break;
            }

            line.replace(";", "");
            lines.add(line);
        }
    }

    private static void filterLinesWithPatterns() {
        filteredLines = new ArrayList<String>();
        Matcher matcher;

        nextLineLabel:
        for (String line :
                lines) {
            StringTokenizer stringTokenizer = new StringTokenizer(line, " ", true);

            while (stringTokenizer.hasMoreTokens()) {
                String token = stringTokenizer.nextToken();

                for (Pattern pattern :
                        patterns) {
                    matcher = pattern.matcher(token);

                    if (matcher.matches()) {
                        filteredLines.add(line);
                        continue nextLineLabel;
                    }
                }
            }
        }
    }

    private static void printFilteredLines() {
        System.out.println("Вывод:");
        for (String line :
                filteredLines) {
            System.out.println(line + ";");
        }
    }
}
