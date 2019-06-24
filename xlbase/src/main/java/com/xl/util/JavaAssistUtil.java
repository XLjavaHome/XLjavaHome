package com.xl.util;

import javassist.CtMethod;
import javassist.Modifier;
import javassist.NotFoundException;
import javassist.bytecode.CodeAttribute;
import javassist.bytecode.LocalVariableAttribute;
import javassist.bytecode.MethodInfo;

/**
 * Created with 徐立.
 *
 * @author 徐立
 * @date 2019-06-24
 * @time 18:04
 * To change this template use File | Settings | File Templates.
 */
public class JavaAssistUtil {
    /**
     * 获取方法的参数变量名称
     *
     * @param cm
     * @return
     */
    public static String[] getMethodVariableName(CtMethod cm) throws NotFoundException {
        MethodInfo methodInfo = cm.getMethodInfo();
        CodeAttribute codeAttribute = methodInfo.getCodeAttribute();
        String[] paramNames = new String[cm.getParameterTypes().length];
        LocalVariableAttribute attr = (LocalVariableAttribute) codeAttribute.getAttribute(LocalVariableAttribute.tag);
        if (attr != null) {
            int pos = Modifier.isStatic(cm.getModifiers()) ? 0 : 1;
            for (int i = 0; i < paramNames.length; i++) {
                paramNames[i] = attr.variableName(i + pos);
            }
            return paramNames;
        }
        return null;
    }
}
