package com.iod_l04_beta.project.algorithm;

import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class InsertionSort<T extends Comparable<T>> implements SortAlgorithm<T> {

    @Override
    public String getName() {
        return "insertion";
    }

    @Override
    public void sort(List<T> data) {
        for (int i = 1; i < data.size(); i++) {
            T key = data.get(i);
            int j = i - 1;

            while (j >= 0 && data.get(j).compareTo(key) > 0) {
                data.set(j + 1, data.get(j));
                j--;
            }
            data.set(j + 1, key);
        }
    }
}
