package com.xl.util;

import com.xl.enumsupport.CharsetEnum;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class PinYinHelper {
    private static PinYinHelper instance;
    private HashMap hypy = new HashMap();

    public PinYinHelper() {
    }

    public static synchronized PinYinHelper getInstance() {
        if (instance == null) {
            instance = new PinYinHelper();
            instance.loadPinYin();
        }
        return instance;
    }

    private void loadPinYin() {
        try {
            InputStream ex = Thread.currentThread().getContextClassLoader().getResourceAsStream("com/sinitek/base/common/pinyin/pinyin.txt");
            InputStreamReader rdr = new InputStreamReader(ex, CharsetEnum.UTF8.getValue());
            BufferedReader brdr = new BufferedReader(rdr);
            while (true) {
                String[] items;
                int length;
                do {
                    String s;
                    if ((s = brdr.readLine()) == null) {
                        brdr.close();
                        rdr.close();
                        return;
                    }
                    items = s.split(":|,");
                    length = items.length;
                } while (length <= 1);
                ArrayList list = new ArrayList(length - 1);
                for (int i = 1; i < length; ++i) {
                    String value = items[i].trim();
                    if (value.compareTo("") != 0) {
                        list.add(value);
                    }
                }
                this.hypy.put(items[0].trim(), list);
            }
        } catch (Exception var10) {
            throw new RuntimeException("读取拼音数据字典文件失败", var10);
        }
    }

    public String getFullPinYin(String sentence) {
        char[] charset = sentence.toCharArray();
        int length = charset.length;
        StringBuffer sb = new StringBuffer(length);
        for (int i = 0; i < length; ++i) {
            char c = charset[i];
            if (this.hypy.get(String.valueOf(c)) != null) {
                ArrayList list = (ArrayList) this.hypy.get(String.valueOf(c));
                sb.append(list.get(0));
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }

    public List traslatePinYins(String sentence) {
        ArrayList _result = new ArrayList();
        char[] charset = sentence.toCharArray();
        if (charset.length <= 0) {
            return _result;
        } else {
            List _pinyins = (List) this.hypy.get(String.valueOf(charset[0]));
            String var7;
            if (_pinyins == null) {
                var7 = this.getSimplePinYin(sentence);
                _result.add(var7);
                return _result;
            } else {
                Iterator _str = _pinyins.iterator();
                while (_str.hasNext()) {
                    String i = (String) _str.next();
                    _result.add(i.substring(0, 1));
                }
                if (charset.length > 1) {
                    var7 = this.getSimplePinYin(sentence.substring(1));
                    for (int var8 = 0; var8 < _result.size(); ++var8) {
                        _result.set(var8, _result.get(var8) + var7);
                    }
                }
                return _result;
            }
        }
    }

    public String getSimplePinYin(String sentence) {
        char[] charset = sentence.toCharArray();
        int length = charset.length;
        StringBuffer sb = new StringBuffer(length);
        for (int i = 0; i < length; ++i) {
            char c = charset[i];
            if (this.hypy.get(String.valueOf(c)) != null) {
                ArrayList list = (ArrayList) this.hypy.get(String.valueOf(c));
                String s = (String) list.get(0);
                sb.append(s.charAt(0));
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }
}
