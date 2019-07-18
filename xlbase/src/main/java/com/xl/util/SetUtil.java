package com.xl.util;

import java.util.*;
import javax.annotation.Nullable;
import lombok.NonNull;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;

/**
 * Created with 徐立.
 *
 * @author 徐立
 * @date 2019-03-13
 * @time 18:38
 * To change this template use File | Settings | File Templates.
 */
public class SetUtil {
    /**
     * 获取set集合中的Integer值
     *
     * @param list
     * @param key
     * @return
     */
    public static Set<Integer> getSets(List<Map> list, String key) {
        Set<Integer> projectIds = new HashSet<>(list.size());
        for (Map data : list) {
            projectIds.add(MapUtils.getInteger(data, key));
        }
        return projectIds;
    }
    
    /**
     * 将字符串切割成set集合,忽略空格
     *
     * @param str
     * @param operator
     * @return
     */
    public static Set<String> split(@Nullable String str, @NonNull String operator) {
        if (str == null) {
            return null;
        }
        String[] split = StringUtils.split(str, operator);
        Set<String> resultSet = new HashSet<>(split.length);
        for (String s : split) {
            resultSet.add(s);
        }
        return resultSet;
    }
    
    /**
     * 两个集合是否是相等的
     * <p>两个为空是相等的</p>
     * <p>有一个为空就是不相等</p>
     * <p>集合大小不一样也是不等的</p>
     * <p>两个集合的元素都一样是相等的</p>
     *
     * @param lastSet
     * @param newSet
     * @return
     */
    public static boolean isEqual(Set<String> lastSet, Set<String> newSet) {
        if (isEmpty(lastSet) && isEmpty(newSet)) {
            return true;
        }
        if (isEmpty(lastSet) || isEmpty(newSet)) {
            return false;
        }
        if (lastSet.size() != newSet.size()) {
            return false;
        }
        for (String s : lastSet) {
            if (!newSet.contains(s)) {
                return false;
            }
        }
        return true;
    }
    
    /**
     * Return <code>true</code> if the supplied Collection is <code>null</code>
     * or empty. Otherwise, return <code>false</code>.
     *
     * @param collection the Collection to check
     * @return whether the given Collection is empty
     */
    public static boolean isEmpty(Collection collection) {
        return (collection == null || collection.isEmpty());
    }
}
