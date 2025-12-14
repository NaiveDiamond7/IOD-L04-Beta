package com.iod_l04_beta.project.algorithm;

import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class HeapSort<T extends Comparable<T>> implements SortAlgorithm<T> {

    @Override
    public String getName() {
        return "heap";
    }

    @Override
    public void sort(List<T> data) {
        int n = data.size();

        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(data, n, i);
        }

        for (int i = n - 1; i > 0; i--) {
            swap(data, 0, i);
            heapify(data, i, 0);
        }
    }

    private void heapify(List<T> data, int heapSize, int root) {
        int largest = root;
        int left = 2 * root + 1;
        int right = 2 * root + 2;

        if (left < heapSize && data.get(left).compareTo(data.get(largest)) > 0) {
            largest = left;
        }

        if (right < heapSize && data.get(right).compareTo(data.get(largest)) > 0) {
            largest = right;
        }

        if (largest != root) {
            swap(data, root, largest);
            heapify(data, heapSize, largest);
        }
    }

    private void swap(List<T> data, int i, int j) {
        T temp = data.get(i);
        data.set(i, data.get(j));
        data.set(j, temp);
    }
}
