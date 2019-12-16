package com.xl.entity;

import java.io.Serializable;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created with 徐立.大写的实体类
 *
 * @author 徐立
 * @version 1.0 2019-12-15 17:20
 * To change this template use File | Settings | File Templates.
 * @date 2019-12-15
 * @time 17:20
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PersonUpper implements Serializable {
    // 唯一序列化标识
    public String NAME;
    /**
     * transient 该字段无法序列化，所以传输对象的时候不会传过去
     */
    private int AGE;
    private String PASSWORD;
    private Date birthDay;
    private Student student;
}
