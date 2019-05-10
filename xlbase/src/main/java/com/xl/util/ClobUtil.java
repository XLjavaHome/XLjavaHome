package com.xl.util;

import java.io.IOException;
import java.io.Reader;
import java.sql.Clob;
import java.sql.SQLException;

/**
 * Created with IntelliJ IDEA.Clob工具类
 *
 * @author 徐立
 * @Date: 2017-11-30
 * @Time: 18:17
 * To change this template use File | Settings | File Templates.
 */
public class ClobUtil {
    /**
     * 讲clob转成String
     *
     * @param clob
     * @return
     * @throws IOException
     * @throws SQLException
     */
    public static String clobToString(Clob clob) throws IOException, SQLException {
        return clobToString(clob, null);
    }

    public static String clobToString(Clob clob, String sprtStr) throws IOException, SQLException {
        StringBuffer content = new StringBuffer();
        if (clob != null) {
            Reader in = null;
            try {
                int p = 0;
                char[] buff = new char[1024];
                in = clob.getCharacterStream();
                while ((p = in.read(buff, 0, 1024)) != -1) {
                    content.append(new String(buff, 0, p));
                }
            } finally {
                if (in != null) {
                    try {
                        in.close();
                    } catch (IOException e) {
                        System.out.println(e);
                    }
                }
            }
        }
        return content.length() > 0 ? content.toString() : sprtStr;
    }
}
