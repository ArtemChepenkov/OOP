package ru.nsu.chepenkov.substring;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

/**
 * Считываем bufferSize, при этом не забывая о граничном случае.
 */

public class SubstringFinder {
    private static final int bufferSize = 1_000_000_0;

    /**
     * Осуществялет поиск с помощью алгоритма Рабина-Карпа.
     *
     * Также проверяет граничный случай
     */
    public static List<Long> find(String path, String subString) throws FileNotFoundException {
        List<Long> res = new ArrayList<>();
        int subStringLength = subString.length();
        try (Reader br = new BufferedReader(new FileReader(path))) {
            char[] buffer = new char[bufferSize];
            StringBuilder tempBuffer;
            int readChars;
            long curPos = 0L;
            List<Integer> tempRes;
            tempBuffer = new StringBuilder(subString);

            while ((readChars = br.read(buffer, 0, bufferSize)) != -1) {
                String curString = new String(buffer, 0, readChars);
                tempRes = (RabinKarp.search(curString, subString));
                for (int i = 0; i < tempRes.size(); i++) {
                    res.add(tempRes.get(i) + curPos);
                }


                for (int j = 0; j < subStringLength - 1; j++) {
                    tempBuffer.append(buffer[j]);
                }
                if (curPos != 0) {
                    tempRes = RabinKarp.search(String.valueOf(tempBuffer), subString);
                }
                for (int i = 0; i < tempRes.size() && curPos != 0; i++) {
                    res.add(tempRes.get(i) + curPos - subStringLength);
                }

                tempBuffer = new StringBuilder();


                for (int j = bufferSize - subStringLength; j < bufferSize; j++) {
                    tempBuffer.append(buffer[j]);
                }

                curPos += bufferSize;
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return res;
    }

    /**Нужно, чтобы в тестах проверить граничный случай.*/
    public int getBufferSize() {
        return bufferSize;
    }
}
