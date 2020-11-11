package com.bridgelabz.iplleagueanalyser;

public class CSVBuilderFactory {
    public static ICSVBuilder createCSVBuilder() {
        return new OpenCSVBuilder();
    }
}
