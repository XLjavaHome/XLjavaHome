/**
 * Created with 徐立.
 *
 * @author 徐立
 * @date 2019-07-23
 * @time 11:42
 * To change this template use File | Settings | File Templates.
 */

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.Serializable;

/**
 * 统一API响应结果封装
 */
public class Result<T> implements Serializable {
    private String code;
    private boolean success = true;
    private String message;
    private T data;
    
    public String getCode() {
        return code;
    }
    
    public Result setCode(String code) {
        this.code = code;
        return this;
    }
    
    public String getMessage() {
        return message;
    }
    
    public Result setMessage(String message) {
        this.message = message;
        return this;
    }
    
    public boolean isSuccess() {
        return success;
    }
    
    public Result setSuccess(boolean success) {
        this.success = success;
        return this;
    }
    
    public T getData() {
        return data;
    }
    
    public Result setData(T data) {
        this.data = data;
        return this;
    }
    
    @Override
    public String toString() {
        try {
            return new ObjectMapper().writeValueAsString(this);
        } catch (Exception e) {
            return super.toString();
        }
    }
}
