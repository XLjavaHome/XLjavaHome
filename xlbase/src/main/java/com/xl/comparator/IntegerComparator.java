package com.xl.comparator;

import com.xl.enumsupport.Sort;
import java.util.Comparator;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Created with 徐立.
 *
 * @author 徐立
 * @Date: 2019-01-05
 * @Time: 22:09
 * To change this template use File | Settings | File Templates.
 */
@Data
@AllArgsConstructor
public class IntegerComparator implements Comparator<Integer> {
    private Sort sort;
    
    public IntegerComparator() {
        this.sort = Sort.ASC;
    }
    
    /**
     * a 比b大就返回1
     *
     * @param a
     * @param b
     * @return
     */
    @Override
    public int compare(Integer a, Integer b) {
        if (sort == Sort.ASC) {
            return a - b;
        } else if (sort == Sort.DESC) {
            return b - a;
        }
        throw new RuntimeException("排序错误");
    }
}
