package by.epamtc.bakulin.io;

import java.util.List;

public interface IOEntityCollector<T>{
    List<T> collectFileData(List<String> fileData);

    String[] parseStringLine(String line);

    T buildEntity(String[] entityProps);
}
