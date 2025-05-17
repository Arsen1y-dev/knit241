package org.knit.solutions.task18;

import java.util.Arrays;

public class NullDuplicator {

    public void duplicateNulls(int[] arr) {
        if (arr == null || arr.length == 0) return;

        int zeros = 0;
        for (int num : arr) {
            if (num == 0) zeros++;
        }

        int i = arr.length - 1;
        int j = arr.length - 1 + zeros;

        while (i >= 0 && j >= 0) {
            if (arr[i] == 0) {
                if (j < arr.length) arr[j] = 0;
                j--;
                if (j < arr.length) arr[j] = 0;
                j--;
                i--;
            } else {
                // Копируем ненулевой элемент
                if (j < arr.length) arr[j] = arr[i];
                j--;
                i--;
            }
        }
    }

    public void printArray(int[] arr) {
        if (arr == null) {
            System.out.println("null");
            return;
        }
        System.out.println(Arrays.toString(arr));
    }
}