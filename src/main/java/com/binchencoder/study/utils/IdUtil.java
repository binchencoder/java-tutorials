package com.binchencoder.study.utils;

import com.google.common.base.Preconditions;
import java.util.Calendar;

public class IdUtil {

  /**
   * 生成唯一ID
   *
   * @param userID 当前用户ID
   */
  public static Long getUniqueID(long userID) {
    Preconditions.checkArgument(userID > 0L, "UserID must be greater than 0");

    String orderPrefix = new StringBuilder().append(System.currentTimeMillis()).append(userID)
        .toString();
    int hashCode = orderPrefix.hashCode();

    Calendar calendar = Calendar.getInstance();
    String orderNo = new StringBuffer().append(calendar.get(Calendar.MONTH) + 1)
        .append(calendar.get(Calendar.DAY_OF_MONTH))
        .append(hashCode < 0 ? "0" + (-hashCode) : hashCode).toString();
    return Long.parseLong(orderNo);
  }
}
