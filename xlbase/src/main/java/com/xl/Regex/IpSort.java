package com.xl.Regex;

import java.util.TreeSet;

/*
 192.68.1.254  102.29.23.013  10.10.10.10  2.2.2.2  8.109.90.30
 还按照字符串自然排序，只要让它们每一段都是3位即可。
 1.按照每一段需要的最多的0进行补齐，那么每一段就会至少保证有位
 2.将每一段只保留3位，这样，所有的ip地址都是每一段3位
 */
public class IpSort {
    public static void main(String[] args) {
        ipSort();
    }

    public static void ipSort() {
        String ip = "192.68.1.254  102.29.23.013  10.10.10.10  2.2.2.2  8.109.90.30";
        ip = ip.replaceAll("(\\d+)", "00$1");   //要重用就封装成组
        System.out.println(ip);
        ip = ip.replaceAll("0*(\\d{3})", "$1");  //取右边的3位
        System.out.println(ip);
        String[] arr = ip.split(" ");
        /*
//		方法一：
		Arrays.sort(arr);
		for(String s:arr){
			Print.print(s.replaceAll("0*(\\d+)","$1"));
		}
		*/
        //方法二：
        TreeSet<String> ts = new TreeSet<String>();
        for (String s : arr) {
            ts.add(s);
        }
        for (String s : ts) {
            System.out.println(s.replaceAll("0*(\\d+)", "$1"));//保留几位不知道  0出现一次或多次0*
        }
    }
}
