package com.xl.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

/**
 * Created by Administrator on 14-5-6.
 */
@Data
@AllArgsConstructor
public class Medicine implements Serializable {
    private int id;
    /**
     * 药品名称
     *
     * @hibernate.property unique="true"
     */
    private String name;
    /**
     * 放药药框
     *
     * @hibernate.property
     */
    private String ark;
    /**
     * 条形码
     *
     * @hibernate.property
     */
    private String barCode;
    /**
     * 功效描述
     *
     * @hibernate.property
     */
    private String efficacy;
    /**
     * 进货价格
     *
     * @hibernate.property
     */
    private double buyPrice;
    /**
     * 出售价格
     *
     * @hibernate.property
     */
    private double sellPrice;
    /**
     * 库存量
     *
     * @hibernate.property
     */
    private double stockpile;
    /**
     * 库存不足提示数量
     *
     * @hibernate.property
     */
    private double cueNumber;
    /**
     * 打折百分比
     *
     * @hibernate.property
     */
    private double rebate;
    /**
     * 提成百分比
     *
     * @hibernate.property
     */
    private double rakeOff;
    /**
     * 拼音简查码
     *
     * @hibernate.property
     */
    private String spellShort;
    /**
     * 数字简查码
     *
     * @hibernate.property
     */
    private String numberShort;

    public Medicine(String name, String ark, String barCode, double buyPrice, double stockpile, String efficacy, double rebate, double sellPrice) {
        this.name = name;
        this.ark = ark;
        this.barCode = barCode;
        this.buyPrice = buyPrice;
        this.stockpile = stockpile;
        this.rebate = rebate;
        this.sellPrice = sellPrice;
        this.efficacy = efficacy;
    }
}
