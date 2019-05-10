package com.xl.enumsupport;

/**
 * Created with IntelliJ IDEA.
 *
 * @author 徐立
 * @Date: 2018-03-27
 * @Time: 15:40
 * To change this template use File | Settings | File Templates.
 */
public class Sex extends AbstractEnumItem {
    public static final Sex NAN = new Sex(1, "男");
    public static final Sex NV = new Sex(2, "女");

    protected Sex(int enumItemValue, String enumItemName) {
        super(enumItemName, enumItemValue, enumItemName, enumItemName);
    }
}
