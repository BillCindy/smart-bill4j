package com.dynamic.bill4j.framework.utils;

/**
 * @author bill
 * @date 2018/6/22
 */
public class CastUtil {

    /**
     * 转String
     *
     * @param object
     * @return
     */
    public static String castString(Object object) {
        return CastUtil.castString(object, "");
    }

    /**
     * 转String
     *
     * @param object
     * @return
     */
    public static String castString(Object object, String defaultValue) {
        return object != null ? String.valueOf(object) : defaultValue;
    }

    /**
     * 转Int
     *
     * @param object
     * @return
     */
    public static int castInt(Object object) {
        return CastUtil.castInt(object, 0);
    }

    /**
     * 转Int
     *
     * @param object
     * @return
     */
    public static int castInt(Object object, int defaultValue) {
        int intValue = defaultValue;
        if (object != null) {
            String strValue = castString(object);
            if (StringUtil.isNotEmpty(strValue)) {
                try {
                    intValue = Integer.parseInt(strValue);
                } catch (NumberFormatException e) {
                    intValue = defaultValue;
                }
            }
        }
        return intValue;
    }

    /**
     * 转Double
     *
     * @param object
     * @return
     */

    public static double castDobule(Object object) {
        return castDobule(object, 0);
    }

    /**
     * 转Double
     *
     * @param object
     * @return
     */
    public static double castDobule(Object object, double defaultValue) {
        double doubleValue = defaultValue;
        if (object != null) {
            String strValue = castString(object);
            if (StringUtil.isNotEmpty(strValue)) {
                try {
                    doubleValue = Double.parseDouble(strValue);
                } catch (NumberFormatException e) {
                    doubleValue = defaultValue;
                }


            }
        }
        return doubleValue;
    }

    /**
     * 转long
     *
     * @param object
     * @return
     */
    public static long castLong(Object object) {
        return castLong(object, 0);
    }

    private static long castLong(Object object, long defaultValue) {
        long longValue = defaultValue;

        if (object != null) {
            String strValue = castString(object);
            if (StringUtil.isNotEmpty(strValue)) {
                try {
                    longValue = Long.parseLong(strValue);
                } catch (NumberFormatException e) {
                    longValue = defaultValue;
                }
            }
        }
        return longValue;
    }

    /**
     * 转Boolean
     *
     * @param object
     * @return
     */
    public static boolean castBoolean(Object object) {
        return castBoolean(object, false);
    }

    /**
     * 转Boolean
     *
     * @param object
     * @return
     */
    public static boolean castBoolean(Object object, boolean defaultValue) {
        boolean booleanValue = defaultValue;
        if (object != null) {
            booleanValue = Boolean.parseBoolean(castString(object));
        }
        return booleanValue;
    }


}
