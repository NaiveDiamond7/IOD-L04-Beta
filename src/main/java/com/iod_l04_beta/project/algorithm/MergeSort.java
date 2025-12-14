package com.iod_l04_beta.project.algorithm;

import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;

@Component
public class MergeSort<T extends Comparable<T>> implements SortAlgorithm<T> {

    @Override
    public String getName() {
        return "merge";
    }

    @Override
    public void sort(List<T> data) {
        if (data.size() <= 1) {
            return;
        }
        List<T> sorted = mergeSort(data);
        data.clear();
        data.addAll(sorted);
    }

    private List<T> mergeSort(List<T> data) {
        if (data.size() <= 1) {
            return data;
        }

        int mid = data.size() / 2;
        List<T> left = mergeSort(new ArrayList<>(data.subList(0, mid)));
        List<T> right = mergeSort(new ArrayList<>(data.subList(mid, data.size())));

        return merge(left, right);
    }

    private List<T> merge(List<T> left, List<T> right) {
        List<T> result = new ArrayList<>();
        int i = 0, j = 0;

        while (i < left.size() && j < right.size()) {
            if (left.get(i).compareTo(right.get(j)) <= 0) {
                result.add(left.get(i++));
            } else {
                result.add(right.get(j++));
            }
        }

        while (i < left.size()) {
            result.add(left.get(i++));
        }

        while (j < right.size()) {
            result.add(right.get(j++));
        }

        return result;
    }
}
