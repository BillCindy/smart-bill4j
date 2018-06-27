package bill4j.utils;

import org.apache.commons.lang3.StringUtils;

/**
 * @author bill
 * @date 2018/6/22
 */
public final class StringUtil {

    /**
     * 判断字符串是否为空
     *
     * @param string
     * @return
     */
    public static boolean isEmpty(String string) {
        return StringUtils.isEmpty(string);
    }

    /**
     * 判断字符串是否非空
     *
     * @param string
     * @return
     */
    public static boolean isNotEmpty(String string) {
        return !isEmpty(string);
    }

}
