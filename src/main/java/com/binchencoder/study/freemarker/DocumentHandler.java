package com.binchencoder.study.freemarker;

import static freemarker.template.Configuration.VERSION_2_3_31;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.util.Map;

/**
 * Created by chenbin on 21-8-31.
 */
public class DocumentHandler {

  private Configuration configuration = null;

  public DocumentHandler() {
    configuration = new Configuration(VERSION_2_3_31);
    configuration.setDefaultEncoding("utf-8");
  }

  public void createDoc(Map<String, Object> dataMap, String fileName)
      throws UnsupportedEncodingException {
    // 需要导出模板的包路径
    configuration.setClassForTemplateLoading(DocumentHandler.class, "/templates");
    Template t = null;
    try {
      t = configuration.getTemplate("董事会会议纪要.ftl");
    } catch (IOException e) {
      e.printStackTrace();
    }

    File outFile = new File(fileName);
    Writer out = null;
    FileOutputStream fos = null;
    try {
      fos = new FileOutputStream(outFile);
      OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fos, "UTF-8");
      out = new BufferedWriter(outputStreamWriter);
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }

    try {
      t.process(dataMap, out);
      out.close();
      fos.close();
    } catch (TemplateException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }

    System.out.println("文档导出完成");
  }
}
