package com.xl.util;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import lombok.Data;

/**
 * @author zhuming
 * @description 用于统一接收过程返回值的对象
 * @date 2013-9-10 下午5:25:09
 */
@Data
public class ResultVO<T> implements Serializable {
    /**
     * ResultVO.java long
     */
    private static final long serialVersionUID = 1L;
    /**************************************
     * 变量定义区
     **************************************/
    /**
     * 结果集
     */
    private List<T> resultList;
    private String returnCode;
    private String errorMessage;
    private String sqlError;
    /**
     * 总页数
     */
    private Integer pageCount;
    private Integer rowCount;
    /**
     * 其他返回结果集
     */
    private Map<String, Object> othermap;

    /**
     * 是否有返回结果集并且大于1
     *
     * @throws
     * @param: @return
     * @return: boolean
     */
    public boolean hasResult() {
        boolean bool = false;
        if (this.getResultList() != null && this.getResultList().size() > 0) {
            bool = true;
        }
        return bool;
    }

    /**
     * @return the resultList
     */
    public List<T> getResultList() {
        return resultList;
    }

    /**
     * @param resultList the resultList to set
     */
    public void setResultList(List<T> resultList) {
        this.resultList = resultList;
    }

    /**
     * 结果集为空
     *
     * @throws
     * @param: @return
     * @return: boolean
     */
    public boolean hasEmptyResult() {
        boolean bool = false;
        if (this.getResultList() != null && this.getResultList().size() == 0) {
            bool = true;
        }
        return bool;
    }

    /**
     * @return the returnCode
     */
    public String getReturnCode() {
        return returnCode;
    }

    /**
     * @param returnCode the returnCode to set
     */
    public void setReturnCode(String returnCode) {
        this.returnCode = returnCode;
    }

    /**
     * @return the errorMessage
     */
    public String getErrorMessage() {
        return errorMessage;
    }

    /**
     * @param errorMessage the errorMessage to set
     */
    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    /**
     * @return the sqlError
     */
    public String getSqlError() {
        return sqlError;
    }

    /**
     * @param sqlError the sqlError to set
     */
    public void setSqlError(String sqlError) {
        this.sqlError = sqlError;
    }

    /**
     * @return the pageCount
     */
    public int getPageCount() {
        return pageCount;
    }

    /**
     * @param pageCount the pageCount to set
     */
    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    /**
     * @return the othermap
     */
    public Map<String, Object> getOthermap() {
        return othermap;
    }

    /**
     * @param othermap the othermap to set
     */
    public void setOthermap(Map<String, Object> othermap) {
        this.othermap = othermap;
    }

    /**
     * @return the rowCount
     */
    public int getRowCount() {
        return rowCount;
    }

    /**
     * @param rowCount the rowCount to set
     */
    public void setRowCount(int rowCount) {
        this.rowCount = rowCount;
    }
}
