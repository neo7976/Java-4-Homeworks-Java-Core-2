package task1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(1, 2, 5, 16, -1, -2, 0, 32, 3, 5, 8, 23, 4);
        List<Integer> listNew = new ArrayList<>();

        for (Integer integer : list) {
            if (integer > 0)
                if (integer % 2 == 0)
                    listNew.add(integer);
        }
        Collections.sort(listNew);
        System.out.println(listNew);
    }
}
