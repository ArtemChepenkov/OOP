package ru.nsu.chepenkov;

import java.util.Scanner;

/**
 * Класс, в котором реализован алгоритм пирамидальной сортировки.
 *
 * @author ArtemChepenkov
 */
public class HeapSort {
    static void heapify(int[] array, int size, int i) {
        int maximum = i, temporary;
        int left = 2 * i + 1;
        int right = 2 * i + 2;

        if (left < size && array[left] > array[maximum]) {
            maximum = left;
        }
        if (right < size && array[right] > array[maximum]) {
            maximum = right;
        }
        if (maximum != i) {
            temporary = array[i];
            array[i] = array[maximum];
            array[maximum] = temporary;
            heapify(array, size, maximum);
        }
    }

    static int[] heapsort(int[] array) {
        int size = array.length;
        int[] newArray = array.clone();
        for (int i = size / 2 - 1; i >= 0; i--) {
            heapify(newArray, size, i);
        }

        for (int i = size - 1; i > 0; i--) {
            int temporary = newArray[0];
            newArray[0] = newArray[i];
            newArray[i] = temporary;
            heapify(newArray, i, 0);
        }
        return newArray;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String inputText = "0";
        try {
            inputText = scanner.nextLine();
        } catch (java.util.NoSuchElementException e) {

        }

        String[] strNumbers = inputText.split(" ");
        int length = strNumbers.length;
        int[] array = new int[length];

        for (int i = 0; i < length; i++) {
            array[i] = Integer.parseInt(strNumbers[i]);
        }

        int[] result = heapsort(array);

        for (int i = 0; i < length; i++) {
            System.out.print(result[i] + " ");
        }
        scanner.close();
    }
}




