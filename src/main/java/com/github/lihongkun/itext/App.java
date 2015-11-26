package com.github.lihongkun.itext;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

import com.itextpdf.text.Document;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfWriter;

public class App {
	
	public static void main(String[] args)throws Exception {
		
		File file = new File("itext.pdf");
		if(!file.exists())
			file.createNewFile();
		
		OutputStream os = new FileOutputStream(file);
		Document document = new Document();
		PdfWriter pdfWriter = PdfWriter.getInstance(document, os);
		
		//打开文档
		document.open();
		
		//每页使用
		pdfWriter.setPageEvent(new WatermarkPageEvent("this is the watermark"));
		
		//设置字体
		BaseFont bfChinese = BaseFont.createFont("STSongStd-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
		Font font = new Font(bfChinese);	
		
		//创建段落
		Paragraph paragraph = null;
		
		for(int i=0;i<50;i++){
			paragraph = new Paragraph("this is a paragraph,this is a paragraph,this is a paragraph,this is a paragraph,this is a paragraph",font);
			document.add(paragraph);
		}
		
		//读入并设置印章图片
		Image image = Image.getInstance("seal.png");
	    image.setScaleToFitLineWhenOverflow(true);
	    image.setAbsolutePosition(100, 670);
	    image.scaleAbsolute(125, 125);
	    
	    //写入印章
	    PdfContentByte pcb = pdfWriter.getDirectContent();
	    pcb.addImage(image);
		
	    //关闭文档
		document.close();
	}
}
