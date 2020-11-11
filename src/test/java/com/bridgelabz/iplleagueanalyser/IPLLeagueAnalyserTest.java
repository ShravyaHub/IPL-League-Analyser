package com.bridgelabz.iplleagueanalyser;

import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

public class IPLLeagueAnalyserTest {

    public static final String FILE_PATH = "C:\\Users\\Shravya\\Desktop\\MostRuns.csv";

    @Test
    public void givenIPLData_ShouldReturnNumberOfRecords() throws CensusAnalyserException {
        Assert.assertEquals(101,new IPLLeagueAnalyser().loadCSVData(FILE_PATH));
    }

    @Test
    public void givenIPLData_ShouldReturnTopThreeBattingAverages() throws Exception {

        List<IPLMostRunsCSV> topBattingAverage = new IPLLeagueAnalyser().getTopBattingAverages(FILE_PATH);
        Assert.assertEquals(83.2, topBattingAverage .get(0).getAverage(), 0.0);
        Assert.assertEquals(69.2, topBattingAverage .get(1).getAverage(), 0.0);
        Assert.assertEquals(56.66, topBattingAverage .get(2).getAverage(), 0.0);
    }

    @Test
    public void givenIPLData_ShouldReturnTopThreeStrikingRates() throws Exception {

        List<IPLMostRunsCSV> topStrikeRate = new IPLLeagueAnalyser().getTopStrikingRates(FILE_PATH);
        Assert.assertEquals(333.33, topStrikeRate.get(0).getSR(), 0.0);
        Assert.assertEquals(204.81, topStrikeRate.get(1).getSR(), 0.0);
        Assert.assertEquals(200.00, topStrikeRate.get(2).getSR(), 0.0);
    }

}
