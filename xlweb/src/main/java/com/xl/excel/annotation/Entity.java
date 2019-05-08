package com.xl.excel.annotation;

import java.io.Serializable;
import lombok.Data;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: 徐立
 * Date: 2017-11-20
 * Time: 16:28
 * To change this template use File | Settings | File Templates.
 */
@Data
public class Entity implements Serializable {
    private static final long serialVersionUID = 1L;
    private String name;
    private String text;
    private String type;
}
