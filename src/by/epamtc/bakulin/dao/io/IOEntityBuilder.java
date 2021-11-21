package by.epamtc.bakulin.dao.io;

import java.util.List;

public interface IOEntityBuilder<T>{
    List<T> parseFileData(List<String> fileData);

    String[] parseStringLine(String line);

    T buildEntity(String[] entityProps);
}
