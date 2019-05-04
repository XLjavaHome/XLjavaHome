package com.xl.comparator;

import java.util.Comparator;

public class LengthComparator implements Comparator<String>  //这加了<String?
{
    @Override
    public int compare(String o1, String o2)  //这儿就不写Object 写String
    {
        int num = new Integer(o2.length()).compareTo(new Integer(o1.length()));
        if (num == 0) {
            return o1.compareTo(o2);
        } else {
            return num;
        }
    }
}
