package com.bridgelabz.iplleagueanalyser;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class IPLLeagueAnalyser {

    public static List<IPLMostRunsCSV> iplBattingCSVList;
    public static List<IPLMostWicketsCSV> iplBowlingCSVList;

    public int loadCSVBattingData(String csvFilePath) throws IPLAnalyserException {
        try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath))) {
            ICSVBuilder csvBuilder = CSVBuilderFactory.createCSVBuilder();
            iplBattingCSVList = csvBuilder.getCSVFileList(reader, IPLMostRunsCSV.class);
            return iplBattingCSVList.size();
        } catch (IOException ioException) {
            throw new IPLAnalyserException("Wrong file path", IPLAnalyserException.ExceptionType.NO_SUCH_FILE);
        }

    }

    public int loadCSVBowlingData(String csvFilePath) throws IPLAnalyserException {
        try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath))) {
            ICSVBuilder csvBuilder = CSVBuilderFactory.createCSVBuilder();
            iplBowlingCSVList = csvBuilder.getCSVFileList(reader, IPLMostWicketsCSV.class);
            return iplBowlingCSVList.size();
        } catch (IOException ioException) {
            throw new IPLAnalyserException("Wrong file path", IPLAnalyserException.ExceptionType.NO_SUCH_FILE);
        }

    }

    public List<IPLMostRunsCSV> getTopBattingAverages(String csvFilePath) throws IPLAnalyserException {
        loadCSVBattingData(csvFilePath);
        List<IPLMostRunsCSV> sortedAvgList = iplBattingCSVList.stream()
                .sorted(Comparator.comparingDouble(IPLMostRunsCSV::getAverage))
                .collect(Collectors.toList());
        Collections.reverse(sortedAvgList);
        return sortedAvgList;
    }

    public List<IPLMostRunsCSV> getTopStrikingRates(String csvFilePath) throws Exception {
        loadCSVBattingData(csvFilePath);
        List<IPLMostRunsCSV> sortedStrikingRateList = iplBattingCSVList.stream()
                .sorted(Comparator.comparingDouble(IPLMostRunsCSV::getSR))
                .collect(Collectors.toList());
        Collections.reverse(sortedStrikingRateList);
        return sortedStrikingRateList;
    }

    public List<IPLMostRunsCSV> getPlayerWithMax6s(String csvFilePath) throws IPLAnalyserException {
        loadCSVBattingData(csvFilePath);
        List<IPLMostRunsCSV> playerWithMax6s = iplBattingCSVList.stream()
                .sorted(Comparator.comparingDouble(IPLMostRunsCSV::getNum6s))
                .collect(Collectors.toList());
        Collections.reverse(playerWithMax6s);
        return playerWithMax6s ;
    }

    public List<IPLMostRunsCSV> getPlayerWithMax4s(String csvFilePath) throws IPLAnalyserException {
        loadCSVBattingData(csvFilePath);
        List<IPLMostRunsCSV> playerWithMax4s = iplBattingCSVList.stream()
                .sorted(Comparator.comparingDouble(IPLMostRunsCSV::getNum4s))
                .collect(Collectors.toList());
        Collections.reverse(playerWithMax4s);
        return playerWithMax4s ;
    }

    public List<IPLMostRunsCSV> getPlayerWithBestStrikingRateWith6sAnd4s(String csvFilePath) throws IPLAnalyserException {
        loadCSVBattingData(csvFilePath);
        int max4sAnd6s = iplBattingCSVList.stream()
                .map(player -> (player.getNum4s() + player.getNum6s()))
                .max(Integer::compare)
                .get();
        List<IPLMostRunsCSV> playerWithMax4sAnd6s = iplBattingCSVList.stream()
                .filter((player -> (player.getNum6s() + player.getNum4s()) == max4sAnd6s))
                .collect(Collectors.toList());

        double bestStrikingRate = playerWithMax4sAnd6s.stream()
                .map(IPLMostRunsCSV::getSR)
                .max(Double::compare)
                .get();

        return playerWithMax4sAnd6s.stream()
                .filter(player->player.getSR() == bestStrikingRate)
                .collect(Collectors.toList());
    }

    public List<IPLMostRunsCSV> getPlayerWithBestAverageAndBestStrikingRate() {
        double bestAverage = iplBattingCSVList.stream()
                .map(IPLMostRunsCSV::getAverage)
                .max(Double::compare)
                .get();
        List<IPLMostRunsCSV> playerWithBestAverage = iplBattingCSVList.stream()
                .filter(player -> player.getAverage() == bestAverage)
                .collect(Collectors.toList());
        double bestStrikeRate = playerWithBestAverage.stream()
                .map(IPLMostRunsCSV::getSR)
                .max(Double::compare)
                .get();

        return playerWithBestAverage.stream()
                .filter(player -> player.getSR() == bestStrikeRate)
                .collect(Collectors.toList());
    }

    public List<IPLMostRunsCSV> getPlayerWithMaximumRunsAndBestAverage(String csvFilePath) throws IPLAnalyserException {
        loadCSVBattingData(csvFilePath);
        int maximumRuns = iplBattingCSVList.stream()
                .map(IPLMostRunsCSV::getRuns)
                .max(Integer::compare)
                .get();
        List<IPLMostRunsCSV> playerWithMaximumRuns = iplBattingCSVList.stream()
                .filter(player -> player.getRuns() == maximumRuns)
                .collect(Collectors.toList());
        double bestAverage = playerWithMaximumRuns.stream()
                .map(IPLMostRunsCSV::getAverage)
                .max(Double::compare)
                .get();

        return playerWithMaximumRuns.stream()
                .filter(player -> player.getAverage() == bestAverage)
                .collect(Collectors.toList());
    }

    public List<IPLMostWicketsCSV> getTopBowlingAverages(String csvFilePath) throws IPLAnalyserException {
        loadCSVBowlingData(csvFilePath);
        List<IPLMostWicketsCSV> sortedAvgBowlingList = iplBowlingCSVList.stream()
                .sorted(Comparator.comparingDouble(player -> player.avg))
                .collect(Collectors.toList());
        Collections.reverse(sortedAvgBowlingList);
        return sortedAvgBowlingList;
    }

    public List<IPLMostWicketsCSV> getTopBowlingStrikingRates(){
        return iplBowlingCSVList.stream()
                .filter(player -> player.sr!=0)
                .sorted(Comparator.comparingDouble(player -> player.sr))
                .collect(Collectors.toList());
    }

    public List<IPLMostWicketsCSV> getTopBowlingEconomyRates(){
        return iplBowlingCSVList.stream()
                .sorted(Comparator.comparingDouble(player -> player.econ))
                .collect(Collectors.toList());
    }

    public List<IPLMostWicketsCSV> getTopStrikingRatesWith5wAnd4w(String csvFilePath) throws IPLAnalyserException {
        loadCSVBowlingData(csvFilePath);
        int max4wAnd5w = iplBowlingCSVList.stream()
                .map(player -> player.num4w + player.num5w)
                .max(Integer::compare)
                .get();
        List<IPLMostWicketsCSV> bowlersWithMax4wAnd5w = iplBowlingCSVList.stream()
                .filter(player -> player.num4w + player.num5w == max4wAnd5w)
                .collect(Collectors.toList());

        return bowlersWithMax4wAnd5w.stream()
                .sorted(Comparator.comparingDouble(player -> player.sr))
                .collect(Collectors.toList());
    }

    public List<IPLMostWicketsCSV> getBestBowlingAveragesWithBestStrikingRates(){
        return iplBowlingCSVList.stream()
                .filter(player -> player.avg != 0 && player.sr != 0)
                .sorted(Comparator.comparingDouble(player -> player.sr + player.avg))
                .collect(Collectors.toList());
    }

}
