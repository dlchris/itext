package com.github.lihongkun.itext;

import org.apache.commons.lang3.StringUtils;

import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.GrayColor;
import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.itextpdf.text.pdf.PdfWriter;

/**
 * 水印事件
 * 
 * @author lihongkun
 */
public class WatermarkPageEvent extends PdfPageEventHelper {

	private Font font;

	private String text;

	public WatermarkPageEvent(String text) {
		this(text, null);
	}

	public WatermarkPageEvent(String text, Font font) {
		this.text = text;
		if (font == null) {
			try {
				BaseFont bfChinese = BaseFont.createFont("STSongStd-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
				this.font = new Font(bfChinese, 36, Font.BOLD, new GrayColor(0.90f));
			} catch (Exception ex) {
				throw new RuntimeException(ex);
			}
		} else
			this.font = font;
	}

	/**
	 * 每页事件
	 */
	public void onEndPage(PdfWriter writer, Document document) {
		ColumnText.showTextAligned(writer.getDirectContentUnder(), Element.ALIGN_LEFT,
				new Phrase(StringUtils.isEmpty(text) ? "" : this.text, font), 85, 550, 25);
		
		ColumnText.showTextAligned(writer.getDirectContentUnder(), Element.ALIGN_LEFT,
				new Phrase(StringUtils.isEmpty(text) ? "" : this.text, font), 85, 350, 25);
		
		ColumnText.showTextAligned(writer.getDirectContentUnder(), Element.ALIGN_LEFT,
				new Phrase(StringUtils.isEmpty(text) ? "" : this.text, font), 85, 150, 25);

	}

}
