package com.bridgelabz.iplleagueanalyser;

import java.io.Reader;
import java.util.List;

public interface ICSVBuilder<E> {

    List<E> getCSVFileList(Reader reader, Class<E> csvClass) throws IPLAnalyserException;


}
