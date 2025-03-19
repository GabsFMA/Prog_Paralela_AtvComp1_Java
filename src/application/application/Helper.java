package application;

import java.util.ArrayList;
import java.util.List;

public class Helper {

    public static List<List<Integer>> splitList(List<Integer> list, int parts, int partSize) {
        List<List<Integer>> subLists = new ArrayList<>();
        int start = 0;
        for (int i = 0; i < parts - 1; i++) {
            subLists.add(new ArrayList<>(list.subList(start, start + partSize)));
            start += partSize;
        }
        subLists.add(new ArrayList<>(list.subList(start, list.size())));
        return subLists;
    }

    public static List<Integer> createIndexList(int size) {
        List<Integer> indices = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            indices.add(i);
        }
        return indices;
    }
}
