package com.xl.word.entity;

/**
 * Created with 徐立.
 *
 * @author 徐立
 * @date 2019-03-13
 * @time 21:17
 * To change this template use File | Settings | File Templates.
 */
public enum TableColumnEnum {/**
 * 属性名称列
 */
attributeNameColumn(0),
    /**
     * 显示名
     */
    displayName(1),
    /**
     * 类型列
     */
    typeColumn(2),
    /**
     * 说明列
     */
    descriptionColumn(3);
    /**
     * 列
     */
    private int column;
    
    TableColumnEnum(int column) {
        this.column = column;
    }
    
    public int getColumn() {
        return column;
    }
    
    public void setColumn(int column) {
        this.column = column;
    }}
