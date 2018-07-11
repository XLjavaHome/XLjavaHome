package com.xl.exception;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: 徐立
 * @Date: 2017-12-29
 * @Time: 16:35
 * To change this template use File | Settings | File Templates.
 */
public class XLException extends Exception {
    public XLException(String message) {
        super(message);
    }

    public XLException(String message, Throwable cause) {
        super(message, cause);
    }
}
