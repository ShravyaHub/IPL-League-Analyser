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

    public List<IPLMostRunsCSV> getPlayerWithMax6s(String csvFilePath) throws IOException, CensusAnalyserException {
        loadCSVData(csvFilePath);
        List<IPLMostRunsCSV> playerWithMax6s = iplBattingCSVList.stream()
                .sorted((player1, player2) -> Double.compare(player1.getNum6s(), player2.getNum6s()))
                .collect(Collectors.toList());
        Collections.reverse(playerWithMax6s);
        return playerWithMax6s ;
    }

    public List<IPLMostRunsCSV> getPlayerWithMax4s(String csvFilePath) throws IOException, CensusAnalyserException {
        loadCSVData(csvFilePath);
        List<IPLMostRunsCSV> playerWithMax4s = iplBattingCSVList.stream()
                .sorted((player1, player2) -> Double.compare(player1.getNum4s(), player2.getNum4s()))
                .collect(Collectors.toList());
        Collections.reverse(playerWithMax4s);
        return playerWithMax4s ;
    }

}
