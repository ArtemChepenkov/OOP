package ru.nsu.chepenkov;

import java.util.Scanner;

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
        int[] new_array = array.clone();
        for (int i = size / 2 - 1; i >= 0; i--) {
            heapify(new_array, size, i);
        }

        for (int i = size - 1; i > 0; i--) {
            int temporary = new_array[0];
            new_array[0] = new_array[i];
            new_array[i] = temporary;
            heapify(new_array, i, 0);
        }
        return new_array;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input_text = "0";
        try {
            input_text = scanner.nextLine();
        } catch (java.util.NoSuchElementException e) {

        }

        String[] str_numbers = input_text.split(" ");
        int length = str_numbers.length;
        int[] array = new int[length];

        for (int i = 0; i < length; i++) {
            array[i] = Integer.parseInt(str_numbers[i]);
        }

        int[] result = heapsort(array);

        for (int i = 0; i < length; i++) {
            System.out.print(result[i] + " ");
        }
        scanner.close();
    }
}




