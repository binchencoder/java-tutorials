package com.binchencoder.study.freemarker;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by chenbin on 21-8-31.
 */
public class Main {

  public static void main(String[] args) throws UnsupportedEncodingException {
    Map<String, Object> dataMap = new HashMap<String, Object>();
    dataMap.put("reporterName", "今目标");

    // 列表数据封装
//    List<String> list1 = new ArrayList<String>();
//    list1.add("itema");
//    list1.add("itemb");
//    list1.add("itemc");
//    dataMap.put("list1", list1);

    DocumentHandler mdoc = new DocumentHandler();
    mdoc.createDoc(dataMap, "/home/chenbin/桌面/董事会会议纪要.doc");
  }
}
