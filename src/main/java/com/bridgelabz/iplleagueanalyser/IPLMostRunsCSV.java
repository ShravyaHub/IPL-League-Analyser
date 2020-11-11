package com.bridgelabz.iplleagueanalyser;

import com.opencsv.bean.CsvBindByName;

public class IPLMostRunsCSV {

    @CsvBindByName(column = "POS")
    public int pos;

    @CsvBindByName(column = "PLAYER")
    public String player;

    @CsvBindByName(column = "Mat")
    public int mat;

    @CsvBindByName(column = "Inns")
    public int inns;

    @CsvBindByName(column = "NO")
    public int no;

    @CsvBindByName(column = "Runs")
    public int runs;

    @CsvBindByName(column = "HS")
    public int hs;

    @CsvBindByName(column = "Avg")
    public double avg;

    @CsvBindByName(column = "BF")
    public int bf;

    @CsvBindByName(column = "SR")
    public double sr;

    @CsvBindByName(column = "100")
    int num100;

    @CsvBindByName(column = "50")
    public int num50;

    @CsvBindByName(column = "4s")
    public int num4s;

    @CsvBindByName(column = "6s")
    public int num6s;

    public double getAverage() {
        return this.avg;
    }

    public double getSR() {
        return sr;
    }

    public int getNum4s() {
        return num4s;
    }

    public int getNum6s() {
        return num6s;
    }

    public String getPlayer() {
        return player;
    }
}
