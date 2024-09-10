package ru.nsu.chepenkov;

import java.util.Scanner;

/**
 * Класс, в котором реализован алгоритм пирамидальной сортировки.
 *
 * @author ArtemChepenkov
 */
public class HeapSort {
    static int amount = 0;
    /**
     * Рекурсивная функция для создания кучи.
     *
     * @param array - массив, который мы меняем
     * @param size - размер
     * @param i - то, где мы сейчас
     */
    static void heapify(int[] array, int size, int i) {
        int maximum = i;
        int temporary;
        int left = 2 * i + 1;
        int right = 2 * i + 2;

        if (left < size && array[left] > array[maximum]) {
            maximum = left;
        }
        if (right < size && array[right] > array[maximum]) {
            maximum = right;
            amount++;
        }
        if (maximum != i) {
            temporary = array[i];
            array[i] = array[maximum];
            array[maximum] = temporary;
            amount += 3;
            heapify(array, size, maximum);
        }
    }

    /**
     * Сама функция хипсорта.
     *
     * @param array - массив, который мы сортируем
     * @return - возвращает новый массив
     */
    static int[] heapsort(int[] array) {
        int size = array.length;
        int[] newArray = array.clone();
        for (int i = size / 2 - 1; i >= 0; i--) {
            heapify(newArray, size, i);
            amount++;
        }

        for (int i = size - 1; i > 0; i--) {
            int temporary = newArray[0];
            newArray[0] = newArray[i];
            newArray[i] = temporary;
            amount+=2;
            heapify(newArray, i, 0);
        }
        return newArray;
    }

    /**
     * Сама функция main.
     *
     * @param args - не используется
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String inputText = "0";
        try {
            inputText = scanner.nextLine();
        } catch (java.util.NoSuchElementException e) {
            System.out.println("Exception occured");
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
        System.out.println();
        System.out.println(amount);
        scanner.close();
    }
}




