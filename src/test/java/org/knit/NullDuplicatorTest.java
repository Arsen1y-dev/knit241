package org.knit;

import org.junit.Test;
import static org.junit.Assert.*;
import java.util.Arrays;
import org.knit.solutions.task18.NullDuplicator;

/**
 * Тесты для класса NullDuplicator.
 */
public class NullDuplicatorTest {

    private final NullDuplicator solution = new NullDuplicator();

    @Test
    public void тестПример1() {
        int[] arr = {1, 0, 2, 3, 0, 4, 5, 0};
        int[] expected = {1, 0, 0, 2, 3, 0, 0, 4};
        solution.duplicateNulls(arr);
        assertArrayEquals(expected, arr);
    }

    @Test
    public void тестПример2() {
        int[] arr = {1, 2, 3};
        int[] expected = {1, 2, 3};
        solution.duplicateNulls(arr);
        assertArrayEquals(expected, arr);
    }

    @Test
    public void тестВсеНули() {
        int[] arr = {0, 0, 0, 0};
        int[] expected = {0, 0, 0, 0};
        solution.duplicateNulls(arr);
        assertArrayEquals(expected, arr);
    }

    @Test
    public void тестОдинНоль() {
        int[] arr = {0};
        int[] expected = {0};
        solution.duplicateNulls(arr);
        assertArrayEquals(expected, arr);
    }

    @Test
    public void тестНулиПоКраям() {
        int[] arr = {0, 1, 2, 0};
        int[] expected = {0, 0, 1, 2};
        solution.duplicateNulls(arr);
        assertArrayEquals(expected, arr);
    }

    @Test
    public void тестНулевойМассив() {
        int[] arr = null;
        solution.duplicateNulls(arr);
        assertNull(arr);
    }

    @Test
    public void тестПустойМассив() {
        int[] arr = {};
        solution.duplicateNulls(arr);
        assertArrayEquals(new int[] {}, arr);
    }

    @Test
    public void тестПроизводительности() {
        // Создаем большой массив с чередующимися нулями и единицами
        int size = 10000;
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = i % 2 == 0 ? 0 : 1;
        }
        int[] arrCopy = arr.clone();

        long startTime = System.nanoTime();
        solution.duplicateNulls(arr);
        long endTime = System.nanoTime();

        double durationMs = (endTime - startTime) / 1_000_000.0;
        System.out.printf("Время выполнения теста производительности: %.2f мс%n", durationMs);

        // Проверяем корректность для первых нескольких элементов
        int[] expectedStart = {0, 0, 1, 0, 0, 1};
        int[] actualStart = Arrays.copyOfRange(arr, 0, Math.min(6, arr.length));
        assertArrayEquals(expectedStart, actualStart);
    }
}