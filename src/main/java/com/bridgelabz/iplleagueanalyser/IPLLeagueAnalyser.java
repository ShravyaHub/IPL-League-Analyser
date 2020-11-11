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

}
