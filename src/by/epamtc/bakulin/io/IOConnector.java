package by.epamtc.bakulin.io;

import java.util.List;

public interface IOManager {

    List<String> readDocumentData(String propertyName);

    void appendDataLine(String propertyName, String data, boolean isAppend);

    void replaceDataLine(String sourceProperty, String cacheProperty, String oldData, String newData);

}
