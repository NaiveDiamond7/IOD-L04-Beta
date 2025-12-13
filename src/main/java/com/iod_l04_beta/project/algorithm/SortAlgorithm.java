package com.iod_l04_beta.project.algorithm;
import java.util.List;

public interface SortAlgorithm<T extends Comparable<T>> {
    String getName();
    void sort(List<T> data);
}