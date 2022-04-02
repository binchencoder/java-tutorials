package com.binchencoder.study.stream;

import lombok.Builder;
import lombok.Data;

/**
 * @author: chenbin
 * @date: 2022/4/2 下午2:24
 */
@Data
@Builder
public class CompanyAndDept implements Comparable<CompanyAndDept> {

  // 公司ID
  private Long cid;

  // 部门ID
  private Long deptId;

  @Override
  public int compareTo(CompanyAndDept o) {
    if (cid > o.getCid()) {
      return 1;
    }
    if (cid < o.getCid()) {
      return -1;
    }
    return 0;
  }
}
