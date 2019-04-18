package com.xl.comparator;

import com.xl.util.PinYinHelper;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.util.Comparator;
import java.util.Date;
import java.util.Map;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.log4j.Logger;

/**
 * File Desc:
 * Product Name: SIRM
 * Module Name: com.sinitek.sirm.common.utils
 * Author:      潘虹
 * History:     11-7-14 created by 潘虹
 */
public class ListComparator implements Comparator {
    final static Logger LOGGER = Logger.getLogger(ListComparator.class);
    private String property;
    private String order;
    private boolean pinyinEnabled = false;

    public ListComparator() {
    }

    public ListComparator(String propertyStr, String orderStr) {
        this.setProperty(propertyStr);
        this.setOrder(orderStr);
    }

    @Override
    public int compare(Object o1, Object o2) {
        Object v1 = null, v2 = null;
        int returnInt = 0;
        try {
            if (o1 instanceof Map && o2 instanceof Map) {
                Map map1 = (Map) o1;
                Map map2 = (Map) o2;
                v1 = map1.get(this.getProperty());
                v2 = map2.get(this.getProperty());
            } else {
                v1 = PropertyUtils.getProperty(o1, this.getProperty());
                v2 = PropertyUtils.getProperty(o2, this.getProperty());
            }
            if (this.pinyinEnabled) {
                if (v1 != null) {
                    v1 = PinYinHelper.getInstance().getFullPinYin(String.valueOf(v1));
                }
                if (v2 != null) {
                    v2 = PinYinHelper.getInstance().getFullPinYin(String.valueOf(v2));
                }
            }
            if (v1 != null && v2 != null) {
                if (v1 instanceof String && v2 instanceof String) {
                    //returnInt = new PinYinComparator(true).compare(v1, v2);
                } else if (v1 instanceof Date && v2 instanceof Date) {
                    returnInt = ((Date) v1).compareTo((Date) v2);
                } else if (v1 instanceof Integer && v2 instanceof Integer) {
                    returnInt = ((Integer) v1).compareTo((Integer) v2);
                } else if (v1 instanceof Double && v2 instanceof Double) {
                    returnInt = ((Double) v1).compareTo((Double) v2);
                } else if (v1 instanceof Float && v2 instanceof Float) {
                    returnInt = ((Float) v1).compareTo((Float) v2);
                } else if (v1 instanceof Long && v2 instanceof Long) {
                    returnInt = ((Long) v1).compareTo((Long) v2);
                } else if (v1 instanceof Short && v2 instanceof Short) {
                    returnInt = ((Short) v1).compareTo((Short) v2);
                } else if (v1 instanceof Boolean && v2 instanceof Boolean) {
                    returnInt = ((Boolean) v1).compareTo((Boolean) v2);
                } else if (v1 instanceof Byte && v2 instanceof Byte) {
                    returnInt = ((Byte) v1).compareTo((Byte) v2);
                } else if (v1 instanceof BigDecimal && v2 instanceof BigDecimal) {
                    returnInt = ((BigDecimal) v1).compareTo((BigDecimal) v2);
                }
                if (returnInt != 0 && "desc".equals(this.getOrder())) {
                    returnInt = 0 - returnInt;
                }
            } else if (v1 != null && v2 == null) {
                if ("desc".equals(this.getOrder())) {
                    returnInt = -1;
                } else {
                    returnInt = 1;
                }
            } else if (v1 == null && v2 != null) {
                if ("desc".equals(this.getOrder())) {
                    returnInt = 1;
                } else {
                    returnInt = -1;
                }
            }
        } catch (IllegalAccessException e) {
            LOGGER.error("compare", e);
        } catch (InvocationTargetException e) {
            LOGGER.error("compare", e);
        } catch (NoSuchMethodException e) {
            LOGGER.error("compare", e);
        } catch (Exception e) {
            LOGGER.error("compare", e);
        }
        return returnInt;
    }

    public String getProperty() {
        return property;
    }

    public void setProperty(String property) {
        this.property = property;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public boolean isPinyinEnabled() {
        return pinyinEnabled;
    }

    public void setPinyinEnabled(boolean pinyinEnabled) {
        this.pinyinEnabled = pinyinEnabled;
    }
}
