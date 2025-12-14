package com.iod_l04_beta.project.algorithm;

import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class SelectionSort<T extends Comparable<T>> implements SortAlgorithm<T> {

    @Override
    public String getName() {
        return "selection";
    }

    @Override
    public void sort(List<T> data) {
        int n = data.size();

        for (int i = 0; i < n - 1; i++) {
            int minIndex = i;

            for (int j = i + 1; j < n; j++) {
                if (data.get(j).compareTo(data.get(minIndex)) < 0) {
                    minIndex = j;
                }
            }

            swap(data, i, minIndex);
        }
    }

    private void swap(List<T> data, int i, int j) {
        T temp = data.get(i);
        data.set(i, data.get(j));
        data.set(j, temp);
    }
}
