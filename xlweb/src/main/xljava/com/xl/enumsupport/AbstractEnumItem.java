package com.xl.enumsupport;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA. 抽象的枚举项实现类，可以基于该抽象类实现枚举项<br/>
 * 子类只能使用给定的构造函数，并且所有的枚举项必须通过public static final的域进行定义<br/>
 * 如：<Br/>
 * <code>
 * public static final IEnumItem DEMO_ITEM = new DemoEnum( "item0", 0, "测试枚举项", "" );
 * </code>
 * <br/>
 * 子类不应该提供public的构造方法，toString和equals方法已经被重载，子类在一般情况下无需重载上述方法。
 *
 * @author: 徐立
 * @Date: 2018-03-16
 * @Time: 13:22
 * To change this template use File | Settings | File Templates.
 */
public abstract class AbstractEnumItem implements IEnumItem, Serializable {
    // ------------------------------------------- private fields
    /**
     * 枚举项显示信息
     */
    protected String enumItemDisplayValue;
    /**
     * 枚举项说明信息
     */
    protected String enumItemInfo;
    /**
     * 枚举项名称
     */
    protected String enumItemName;
    /**
     * 枚举项值
     */
    protected int enumItemValue;
    // ------------------------------------------- constructor

    /**
     * 只允许子类使用的构造函数
     *
     * @param enumItemDisplayValue 枚举项显示值
     * @param enumItemInfo         枚举项说明信息
     * @param enumItemName         枚举项名称
     * @param enumItemValue        枚举项值
     */
    protected AbstractEnumItem(String enumItemName, int enumItemValue, String enumItemInfo, String enumItemDisplayValue) {
        this.enumItemDisplayValue = enumItemDisplayValue;
        this.enumItemInfo = enumItemInfo;
        this.enumItemName = enumItemName;
        this.enumItemValue = enumItemValue;
    }

    @Override
    public final boolean equals(Object obj) {
        if (obj instanceof IEnumItem) {
            IEnumItem item = (IEnumItem) obj;
            if (this.getEnumName().equalsIgnoreCase(item.getEnumName())) {
                return item.getEnumItemValue() == this.enumItemValue && item.getEnumItemName().equalsIgnoreCase(this.enumItemName);
            }
        }
        return false;
    }

    @Override
    public String getEnumName() {
        return getClass().getName();
    }

    @Override
    public String getEnumItemName() {
        return enumItemName;
    }

    @Override
    public String getEnumItemInfo() {
        return enumItemInfo;
    }

    @Override
    public int getEnumItemValue() {
        return enumItemValue;
    }

    @Override
    public String getEnumItemDisplayValue() {
        return enumItemDisplayValue;
    }

    // ------------------------------------------- override method
    @Override
    public String toString() {
        return "[" + enumItemName + "]-[" + enumItemValue + "]";
    }
}
