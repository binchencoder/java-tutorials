package com.binchencoder.study.apache.commonslang3;

import java.text.ParseException;
import org.apache.commons.lang3.time.DateUtils;
import org.junit.jupiter.api.Test;

/**
 * @author: chenbin
 * @date: 2021/11/29 下午2:30
 */
public class Tests {

  public static final String YYYY_MM_DD_HH_MI_SS = "yyyy-MM-dd HH:mm:ss";

  @Test
  public void testDateUtils() throws ParseException {
    System.out.println(DateUtils.parseDate("2021-11-29 14:15:21", YYYY_MM_DD_HH_MI_SS));
  }

}
