package by.epamtc.bakulin.io;

import java.util.List;

public interface IOConnector {

    List<String> readDocumentData(String propertyName);

    void writeDataLine(String sourceConnectionProperty, String stringData);

    void writeDataLine(String sourceConnectionProperty, String stringData, boolean isAppend);

    void updateDataLine(String sourceConnectionProperty, String cacheConnectionProperty, String oldDataLine, String newDataLine);

    void deleteDataLine(String sourceConnectionProperty, String cacheConnectionProperty, String dataToReplace);

}
