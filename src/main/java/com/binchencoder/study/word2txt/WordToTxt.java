package com.binchencoder.study.word2txt;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.Writer;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.apache.poi.POIXMLDocument;
import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.converter.WordToTextConverter;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;

public class WordToTxt {

    /**
     * 将word转换为txt
     *
     * @param filesName   word文件绝对路径 F:\bbb\ddd\周五.docx
     * @param fileName    word文件名 周五.docx
     * @param outdocPath  doc文件转txt输出路径
     * @param outdocxPath docx文件转txt输出路径
     * @throws Exception
     */
    public static void word2txt(String filesName, String fileName, String outdocPath,
        String outdocxPath) throws Exception {
        String fileType = filesName.substring(filesName.length() - 4, filesName.length());
        if (fileType.equals("docx")) {
//            要转换的文档全路径
            String docxPath = filesName;
//            转换后的文档全路径
            String docxtotxtPath =
                outdocxPath + "/" + fileName.substring(0, fileName.length() - 5) + ".txt";

            //得到.docx文件提取器
            XWPFWordExtractor docx = new XWPFWordExtractor(POIXMLDocument.openPackage(docxPath));
            //提取.docx正文文本
            String text = docx.getText();

            FileWriter writer = new FileWriter(docxtotxtPath);
            writer.write(text);
            writer.close();
        } else if (fileType.equals(".doc")) {
//            要转换的文档全路径
            String docPath = filesName;
//            转换后的文档全路径
            String doctotxtPath =
                outdocPath + "/" + fileName.substring(0, fileName.length() - 4) + ".txt";
            InputStream is = new FileInputStream(docPath);
            HWPFDocument wordDocument = new HWPFDocument(is);
            WordToTextConverter converter = new WordToTextConverter(
                DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument());

            //对HWPFDocument进行转换
            converter.processDocument(wordDocument);
            Writer writer = new FileWriter(new File(doctotxtPath));
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.setOutputProperty(OutputKeys.ENCODING, "utf-8");
            //是否添加空格
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty(OutputKeys.METHOD, "text");
            transformer.transform(
                new DOMSource(converter.getDocument()),
                new StreamResult(writer));
        }
    }
}
