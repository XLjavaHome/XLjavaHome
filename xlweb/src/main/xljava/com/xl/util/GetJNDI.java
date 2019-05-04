package com.xl.util;

import java.util.Properties;
import javax.naming.Context;
import javax.naming.InitialContext;

/**
 * @author 徐立
 * @Decription Ejb, Weblogic查找JNDI的工具类
 * @date 2013-12-21
 */
public class GetJNDI {
    private static int webType = 0;
    // EJB服务器IP地址
    private static String ejbServerIP = "";
    // EJB服务器端口
    private static String ejbServerPort = "";

    // 适用版本：WebLogic 10g/11g
    public static Object webLogic10(String mappedName, String className) {
        try {
            Properties env = new Properties();
            env.setProperty(Context.INITIAL_CONTEXT_FACTORY, "weblogic.jndi.WLInitialContextFactory");
            env.setProperty(Context.PROVIDER_URL, "t3://" + ejbServerIP + ":" + ejbServerPort);
            InitialContext ctx = new InitialContext(env);
            return ctx.lookup("ejb/" + mappedName + "#" + className);
        } catch (Exception e) {
            return null;
        }
    }

    // 适用版本：JBoss 4.2.3
    public static Object jBoss4(String mappedName) {
        try {
            Properties env = new Properties();
            env.setProperty(Context.INITIAL_CONTEXT_FACTORY, "org.jnp.interfaces.NamingContextFactory");
            env.setProperty(Context.PROVIDER_URL, ejbServerIP + ":" + ejbServerPort);
            InitialContext ctx = new InitialContext(env);
            return ctx.lookup(mappedName + "/remote");
        } catch (Exception e) {
            return null;
        }
    }

    // 适用版本：JBoss 5.0.0/5.0.1/6.0.0/6.1.0
    public static Object jBoss5(String mappedName) {
        try {
            Properties env = new Properties();
            env.setProperty(Context.INITIAL_CONTEXT_FACTORY, "org.jnp.interfaces.NamingContextFactory");
            env.setProperty(Context.PROVIDER_URL, ejbServerIP + ":" + ejbServerPort);
            InitialContext ctx = new InitialContext(env);
            return ctx.lookup("ejb/" + mappedName);
        } catch (Exception e) {
            return null;
        }
    }

    // 适用版本：JBoss 7.1.1 需传入EJB的Jar包名
    public static Object jBoss7(String jarName, String mappedName) {
        try {
            Properties env = new Properties();
            env.setProperty(Context.INITIAL_CONTEXT_FACTORY, "org.jboss.as.naming.InitialContextFactory");
            env.setProperty(Context.PROVIDER_URL, ejbServerIP + ":" + ejbServerPort);
            InitialContext ctx = new InitialContext(env);
            return ctx.lookup("java:global/" + jarName + "/" + mappedName);
        } catch (Exception e) {
            return null;
        }
    }

    // EJB服务器地址和端口完整时的自适应方法
    public Object initLookup(String jarName, String mappedName, String className) {
        if (null != webLogic10(mappedName, className)) {
            webType = 1;
        } else if (null != jBoss4(mappedName)) {
            webType = 2;
        } else if (null != jBoss5(mappedName)) {
            webType = 3;
        } else if (null != jarName && null != jBoss7(jarName, mappedName)) {
            webType = 4;
        }
        return webType == 0 ? null : lookup(jarName, mappedName, className);
    }

    // EJB服务器地址或端口残缺时的自适应方法
    public Object autoLookup(String jarName, String mappedName, String className) {
        ejbServerIP = ejbServerIP.length() == 0 ? "localhost" : ejbServerIP;
        ejbServerPort = ejbServerPort.length() == 0 ? "7001" : ejbServerPort;
        if (null != webLogic10(mappedName, className)) {
            webType = 1;
        } else {
            ejbServerPort = "1099";
            if (null != jBoss4(mappedName)) {
                webType = 2;
            } else if (null != jBoss5(mappedName)) {
                webType = 3;
            } else if (null != jarName && null != jBoss7(jarName, mappedName)) {
                webType = 4;
            }
        }
        return webType == 0 ? null : lookup(jarName, mappedName, className);
    }

    public Object lookup(String mappedName, String className) {
        return lookup(null, mappedName, className);
    }

    public Object lookup(String jarName, String mappedName, String className) {
        Object daoImpl = null;
        switch (webType) {
            case 1:
                daoImpl = webLogic10(mappedName, className);
                break;
            case 2:
                daoImpl = jBoss4(mappedName);
                break;
            case 3:
                daoImpl = jBoss5(mappedName);
                break;
            case 4:
                daoImpl = jBoss7(jarName, mappedName);
                break;
            default:
                daoImpl = ejbServerIP.length() == 0 || ejbServerPort.length() == 0 ? autoLookup(jarName, mappedName, className) : initLookup(jarName, mappedName, className);
                break;
        }
        return daoImpl;
    }
}
