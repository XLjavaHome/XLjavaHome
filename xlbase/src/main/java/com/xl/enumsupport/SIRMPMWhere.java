package com.xl.enumsupport;

/**
 * Created with IntelliJ IDEA.用户所在地
 *
 * @author 徐立
 * @Date: 2018-02-22
 * @Time: 15:46
 * To change this template use File | Settings | File Templates.
 */
public class SIRMPMWhere extends AbstractEnumItem {
    public static final SIRMPMWhere F0001 = new SIRMPMWhere(1, "上海");
    public static final SIRMPMWhere F0002 = new SIRMPMWhere(2, "北京");
    public static final SIRMPMWhere F0003 = new SIRMPMWhere(3, "深圳");
    public static final SIRMPMWhere F0004 = new SIRMPMWhere(4, "武汉");
    public static final SIRMPMWhere F0005 = new SIRMPMWhere(5, "舟山");
    public static final SIRMPMWhere F0006 = new SIRMPMWhere(6, "佛山");

    protected SIRMPMWhere(int enumItemValue, String enumItemName) {
        super(enumItemName, enumItemValue, enumItemName, null);
    }
}
