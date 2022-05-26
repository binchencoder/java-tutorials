package com.binchencoder.study.utils;

import java.util.Date;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.commons.lang3.time.DateFormatUtils;

/**
 * Utility class for date.
 *
 * @author chenbin
 */
public class DateUtils {

    public static final String DEFAULT_VERSION_DATE_FORMAT = "yyyMMddHHmmss";

    /**
     * 将当前时间转换成 年月日时分秒格式的数字
     *
     * @return
     */
    public static Long parseYmdHmsOfNow() {
        return parseYmdHms(new Date());
    }

    /**
     * 将制定日期转换成 年月日时分秒格式的数字
     *
     * @return
     */
    public static Long parseYmdHms(Date date) {
        String dateStr = DateFormatUtils.format(date, DEFAULT_VERSION_DATE_FORMAT);
        if (NumberUtils.isDigits(dateStr)) {
            return Long.parseLong(dateStr);
        }
        return 0L;
    }
}
