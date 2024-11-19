package ru.nsu.chepenkov.substring;

import java.util.ArrayList;
import java.util.List;

/**
 * Алгортим Рабина-Карпа.
 */
public class RabinKarp {
    public static List<Integer> search(String text, String subString) {
        int subStringLength = subString.length();
        int textLength = text.length();
        int prime = 17; //change to a normal one

        int subStringHash = 0;
        int textHash = 0;
        int slidingValue = 1;

        List<Integer> result = new ArrayList<>();

        for (int i = 0; i < subStringLength - 1; i++) {
            slidingValue = (slidingValue * 256) % prime;
        }
        if (subStringLength > text.length()) {
            return result;
        }
        for (int i = 0; i < subStringLength; i++) {
            subStringHash = (256 * subStringHash + subString.charAt(i)) % prime;
            textHash = (256 * textHash + text.charAt(i)) % prime;
        }

        for (int i = 0; i <= textLength - subStringLength; i++) {
            if (subStringHash == textHash) {
                int j;
                for (j = 0; j < subStringLength; j++) {
                    if (text.charAt(i + j) != subString.charAt(j)) {
                        break;
                    }
                }

                if(j == subStringLength) {
                    result.add(i);
                }
            }

            if (i < textLength - subStringLength) {
                textHash = (256 * (textHash - text.charAt(i) * slidingValue)
                + text.charAt(i + subStringLength)) % prime;
                if (textHash < 0) {
                    textHash += prime;
                }
            }

        }
        return result;
    }

}
