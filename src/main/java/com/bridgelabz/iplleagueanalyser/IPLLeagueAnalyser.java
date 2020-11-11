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

    public int loadCSVData(String csvFilePath) throws CensusAnalyserException {
        try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath))) {
            ICSVBuilder csvBuilder = CSVBuilderFactory.createCSVBuilder();
            iplBattingCSVList = csvBuilder.getCSVFileList(reader, IPLMostRunsCSV.class);
            return iplBattingCSVList.size();
        } catch (IOException ioException) {
            throw new CensusAnalyserException("Wrong file path", CensusAnalyserException.ExceptionType.NO_SUCH_FILE);
        }

    }

    public List<IPLMostRunsCSV> getTopBattingAverages(String csvFilePath) throws CensusAnalyserException {
        loadCSVData(csvFilePath);
        List<IPLMostRunsCSV> sortedAvgList = iplBattingCSVList.stream()
                .sorted(Comparator.comparingDouble(IPLMostRunsCSV::getAverage))
                .collect(Collectors.toList());
        Collections.reverse(sortedAvgList);
        return sortedAvgList;
    }

    public List<IPLMostRunsCSV> getTopStrikingRates(String csvFilePath) throws Exception {
        loadCSVData(csvFilePath);
        List<IPLMostRunsCSV> sortedStrikingRateList = iplBattingCSVList.stream()
                .sorted(Comparator.comparingDouble(IPLMostRunsCSV::getSR))
                .collect(Collectors.toList());
        Collections.reverse(sortedStrikingRateList);
        return sortedStrikingRateList;
    }

    public List<IPLMostRunsCSV> getPlayerWithMax6s(String csvFilePath) throws CensusAnalyserException {
        loadCSVData(csvFilePath);
        List<IPLMostRunsCSV> playerWithMax6s = iplBattingCSVList.stream()
                .sorted(Comparator.comparingDouble(IPLMostRunsCSV::getNum6s))
                .collect(Collectors.toList());
        Collections.reverse(playerWithMax6s);
        return playerWithMax6s ;
    }

    public List<IPLMostRunsCSV> getPlayerWithMax4s(String csvFilePath) throws CensusAnalyserException {
        loadCSVData(csvFilePath);
        List<IPLMostRunsCSV> playerWithMax4s = iplBattingCSVList.stream()
                .sorted(Comparator.comparingDouble(IPLMostRunsCSV::getNum4s))
                .collect(Collectors.toList());
        Collections.reverse(playerWithMax4s);
        return playerWithMax4s ;
    }

    public List<IPLMostRunsCSV> getPlayerWithBestStrikingRateWith6sAnd4s() {
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

}
