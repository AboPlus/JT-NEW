package com.abo.collection;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class testCollection {

    @Test
    public void testList(){
        List list = new ArrayList();
        list.add(1);
        list.add(2);
        Object[] objects = list.toArray();
        String res = Arrays.toString(objects);
        System.out.println(objects.getClass());
        System.out.println(res.getClass());
        System.out.println("=======");
        int[] arr = {1,2,3,4,5};
        List<int[]> ints = Arrays.asList(arr);
        System.out.println(ints.getClass());
    }


}
