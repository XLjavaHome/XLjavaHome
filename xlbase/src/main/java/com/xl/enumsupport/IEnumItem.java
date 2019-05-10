package com.xl.enumsupport;

/**
 * Created with IntelliJ IDEA. 枚举项接口<br/>
 * 关于枚举名称请注意，如果是从持久化介质中装载的枚举数据，请使用配置的枚举名称，如果是固化枚举类，该方法直接返回类全名。
 * 框架首先假设此名称为配置的枚举名称，如错误，则尝试类名称<br/>
 * <br/>
 * 对于<code>getEnumItemDisplayValue</code>方法，至少需支持以下3中表达式替换：
 * <ul>
 * <li>${itemName}：替换为枚举项名称</li>
 * <li>${itemInfo}：替换为枚举项说明</li>
 * <li>${itemValue}：替换为枚举项值</li>
 * </ul>
 *
 * @author 徐立
 * @Date: 2018-03-16
 * @Time: 13:04
 * To change this template use File | Settings | File Templates.
 */
public interface IEnumItem {
    /**
     * 获得枚举大类名称，一般为一个名词短语（全小写）或固化枚举类类名
     *
     * @return 枚举大类名称
     */
    String getEnumName();

    /**
     * 获得枚举项名称，一般为一个名词
     *
     * @return 枚举名称
     */
    String getEnumItemName();

    /**
     * 获得枚举项说明信息
     *
     * @return 枚举说明信息，可以使用较长文字
     */
    String getEnumItemInfo();

    /**
     * 获取枚举值，一般用于数据库存储
     *
     * @return 枚举项值
     */
    int getEnumItemValue();

    /**
     * 获得枚举项显示输出信息，一般可以使用html语言，实现类需要按类说明中的规范进行翻译
     *
     * @return 枚举项显示输出信息
     */
    String getEnumItemDisplayValue();
}
