package com.bridgelabz.iplleagueanalyser;

import org.junit.Assert;
import org.junit.Test;
import java.util.List;

public class IPLLeagueAnalyserTest {

    public static final String IPL_BATTNG_FILE_PATH = "C:\\Users\\My PC\\Desktop\\Shravya\\MostRuns.csv";
    public static final String IPL_BOWLING_FILE_PATH = "C:\\Users\\My PC\\Desktop\\Shravya\\MostWickets.csv";

    @Test
    public void givenIPLBattingData_ShouldReturnNumberOfRecords() throws IPLAnalyserException {
        Assert.assertEquals(101,new IPLLeagueAnalyser().loadCSVBattingData(IPL_BATTNG_FILE_PATH));
    }

    @Test
    public void givenIPLBattingData_ShouldReturnTopThreeBattingAverages() throws Exception {

        List<IPLMostRunsCSV> topBattingAverage = new IPLLeagueAnalyser().getTopBattingAverages(IPL_BATTNG_FILE_PATH);
        Assert.assertEquals(83.2, topBattingAverage .get(0).getAverage(), 0.0);
        Assert.assertEquals(69.2, topBattingAverage .get(1).getAverage(), 0.0);
        Assert.assertEquals(56.66, topBattingAverage .get(2).getAverage(), 0.0);
    }

    @Test
    public void givenIPLBattingData_ShouldReturnTopThreeStrikingRates() throws Exception {

        List<IPLMostRunsCSV> topStrikeRate = new IPLLeagueAnalyser().getTopStrikingRates(IPL_BATTNG_FILE_PATH);
        Assert.assertEquals(333.33, topStrikeRate.get(0).getSR(), 0.0);
        Assert.assertEquals(204.81, topStrikeRate.get(1).getSR(), 0.0);
        Assert.assertEquals(200.00, topStrikeRate.get(2).getSR(), 0.0);
    }

    @Test
    public void givenIPLBattingData_ShouldReturnPlayerWithMax6s() throws Exception {

        Assert.assertEquals("Andre Russell", new IPLLeagueAnalyser().getPlayerWithMax6s(IPL_BATTNG_FILE_PATH).get(0).getPlayer());
    }

    @Test
    public void givenIPLBattingData_ShouldReturnPlayerWithMax4s() throws Exception {

        Assert.assertEquals("Shikhar Dhawan", new IPLLeagueAnalyser().getPlayerWithMax4s(IPL_BATTNG_FILE_PATH).get(0).player);
    }

    @Test
    public void givenIPLBattingData_ShouldReturnBestStrikingRatesWith6sAnd4s() throws IPLAnalyserException {
        Assert.assertEquals("Andre Russell", new IPLLeagueAnalyser().getPlayerWithBestStrikingRateWith6sAnd4s(IPL_BATTNG_FILE_PATH).get(0).getPlayer());
    }

    @Test
    public void givenIPLBattingData_ShouldReturnPlayerWithBestAverageAndBestStrikingRate() {
        Assert.assertEquals("MS Dhoni", new IPLLeagueAnalyser().getPlayerWithBestAverageAndBestStrikingRate().get(0).getPlayer());
    }

    @Test
    public void givenIPLBattingData_ShouldReturnPlayerWithMaximumRunsAndBestAverage() throws IPLAnalyserException {
        Assert.assertEquals("David Warner", new IPLLeagueAnalyser().getPlayerWithMaximumRunsAndBestAverage(IPL_BATTNG_FILE_PATH).get(0).getPlayer());
    }

    @Test
    public void givenIPLBowlingData_ShouldReturnNumberOfRecords() throws IPLAnalyserException {
        Assert.assertEquals(99,new IPLLeagueAnalyser().loadCSVBowlingData(IPL_BOWLING_FILE_PATH));
    }

    @Test
    public void givenIPLBowlingData_ShouldReturnTopBowlingAverages() throws IPLAnalyserException {
        List<IPLMostWicketsCSV> topBowlingAverages = new IPLLeagueAnalyser().getTopBowlingAverages(IPL_BOWLING_FILE_PATH);
        Assert.assertEquals("Krishnappa Gowtham", topBowlingAverages.get(0).player);
        Assert.assertEquals("Tim Southee", topBowlingAverages.get(1).player);
        Assert.assertEquals("Prasidh Krishna", topBowlingAverages.get(2).player);
    }

    @Test
    public void givenIPLData_ShouldReturnTopBowlingStrikingRates() {
        List<IPLMostWicketsCSV> topBowlingStrikingRatesList = new IPLLeagueAnalyser().getTopBowlingStrikingRates();
        Assert.assertEquals("Alzarri Joseph",topBowlingStrikingRatesList.get(0).player);
        Assert.assertEquals("Ish Sodhi",topBowlingStrikingRatesList.get(1).player);
        Assert.assertEquals("Khaleel Ahmed",topBowlingStrikingRatesList.get(2).player);
    }

    @Test
    public void givenIPLData_ShouldReturnBestEconomyBowlingRates() {
        Assert.assertEquals("Shivam Dube",new IPLLeagueAnalyser().getTopBowlingEconomyRates().get(0).player);
    }

    @Test
    public void givenIPLData_ShouldReturnTopStrikingRatesWith5wAnd4w() throws IPLAnalyserException {
        Assert.assertEquals("Kagiso Rabada",new IPLLeagueAnalyser().getTopStrikingRatesWith5wAnd4w(IPL_BOWLING_FILE_PATH).get(0).player);
    }

    @Test
    public void givenIPLData_ShouldReturnBestBowlingAveragesWithBestStrikingRates() {
        Assert.assertEquals("Anukul Roy",new IPLLeagueAnalyser().getBestBowlingAveragesWithBestStrikingRates().get(0).player);
    }

}
