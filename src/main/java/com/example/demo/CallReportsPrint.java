package com.example.demo;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;

public class CallReportsPrint {

	public static void callReport(CallReport callReportData, Document document) throws DocumentException {

		float coulmnsWidth[] = new float[] { 40, 30, 30 };
		float singleCoulmnWidth[] = new float[] { 100 };

		/* adding call report header */
		addParagraph(FontFactory.HELVETICA_BOLD, (float) 18, BaseColor.BLUE, "Call Report", document,
				Element.ALIGN_CENTER);

		document.add(Chunk.NEWLINE);

		String[] firstheaders = { "Report For", "Account Name", "Account Number" };
		List<String> values = new ArrayList<String>();
		values.add("Account Type");
		values.add("John David");
		values.add("123456789");
		designTradeInfromation(3, firstheaders, values, document, coulmnsWidth);

		String[] secondheaders = { "is these Customer Contact/Visitation ?", "Type of Visit", "Visiting Officer" };
		values = new ArrayList<String>();
		values.add("Yes");
		values.add("Call");
		values.add("UHDFC34H");
		designTradeInfromation(3, secondheaders, values, document, coulmnsWidth);

		String[] thirdheaders = { "Call Date", "Call Time", "Attendees" };
		values = new ArrayList<String>();
		values.add("12-12-2002:10:30");
		values.add("10:30");
		values.add("ABC DEFG");
		designTradeInfromation(3, thirdheaders, values, document, coulmnsWidth);

		String[] fourthheaders = { "Completed By", "Completion Time", "" };
		values = new ArrayList<String>();
		values.add("John DAVID");
		values.add("12-12-2002:10:30");
		values.add("");

		designTradeInfromation(3, fourthheaders, values, document, coulmnsWidth);

		document.add(Chunk.NEWLINE);

		String[] fifthheaders = { "Country of Client Location", "Client Phone Number", "" };
		values = new ArrayList<String>();
		values.add("United States");
		values.add("3125478953");
		values.add("");

		designTradeInfromation(3, fifthheaders, values, document, coulmnsWidth);

		String[] sixthheaders = { "Visit Summary" };
		values = new ArrayList<String>();
		values.add("Test");

		designTradeInfromation(1, sixthheaders, values, document, singleCoulmnWidth);

		addParagraph(FontFactory.HELVETICA_BOLD, (float) 12, BaseColor.BLACK, "BI Rationale Report", document,
				Element.ALIGN_LEFT);

		document.add(Chunk.NEWLINE);

		designQuetionsandAnswers(document);

		document.add(Chunk.NEXTPAGE);

	}

	public static void designQuetionsandAnswers(Document document) {

		Map<String, String> rationaleData = new LinkedHashMap<String, String>();

		rationaleData.put("BI Rationale is for", "Account Type");
		rationaleData.put(
				"Did you consider the complexity of and risks associated with the recommended investment strategy, product, or account and discuss them with your client?",
				"N/A");
		rationaleData.put("Comments", "N/A");
		rationaleData.put(
				"Were the costs associated with all products in the recommendation considered, communicated, and explained to client?",
				"N/A");
		rationaleData.put("Comments", "N/A");
		rationaleData.put(
				"With consideration to past trading history, activity, and previous recommendations received by the client, does this recommendation (or series of recommendations) fit the best interest of the client?",
				"N/A");
		rationaleData.put("Comments", "N/A");
		rationaleData.put(
				"Did you review and compare reasonable alternatives and determine that the recommendation is in the best interest of the client?",
				"N/A");
		rationaleData.put("Comments", "N/A");
		rationaleData.put(
				"Did you consider the appropriateness of Account Type recommendation and how it fits the best interest of the client?",
				"N/A");
		rationaleData.put("Comments", "N/A");

		rationaleData.put(
				"Did you consider the appropriateness of the Product Type recommendation within the client's account type and how it fits the best interest of the client?",
				"N/A");
		rationaleData.put("Comments", "N/A");

		rationaleData.put(
				"Did you consider the appropriateness of the Product Type recommendation within the client's account type and how it fits the best interest of the client?",
				"N/A");
		rationaleData.put("Comments", "N/A");

		rationaleData.put(
				"How was this recommendation made for the best interest of the client and did the recommendation ensure that the firm or the representative's interests were not placed ahead of the customers?",
				"N/A");
		rationaleData.put(
				"Received consent from the client/prospect to deliver Form CRS and HSBC Brokerage Brochure via Live Sign?",
				"Physically Mailed");
		rationaleData.put("How was the Form CRS/HBB delivered?", "N/A");

		rationaleData.forEach((question, answer) -> {
			try {
				addParagraph(FontFactory.HELVETICA_BOLD, (float) 10, BaseColor.BLACK, question, document,
						Element.ALIGN_LEFT);
				addParagraph(FontFactory.HELVETICA, (float) 10, BaseColor.BLACK, answer, document, Element.ALIGN_LEFT);

			} catch (DocumentException e) {
				e.printStackTrace();
			}

		});

	}

	public static void addParagraph(String fontName, Float fontSize, BaseColor color, String text, Document document,
			int alignment) throws DocumentException {
		Paragraph para = new Paragraph(text, FontFactory.getFont(fontName, fontSize, color));
		para.setAlignment(alignment);
		document.add(para);
	}

	public static void designTradeInfromation(int coulmns, String[] headersList, List<String> values, Document document,
			float columnsWidth[]) throws DocumentException {
		PdfPTable tradeInfromation = new PdfPTable(coulmns);

		tradeInfromation.setWidthPercentage(100);
		tradeInfromation.setTotalWidth(columnsWidth);
		Stream.of(headersList).forEach(headerTitle -> {
			PdfPCell header = new PdfPCell();
			Font headFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 10);
			header.setHorizontalAlignment(Element.ALIGN_LEFT);
			header.setBorderWidth(0);
			header.setPhrase(new Phrase(headerTitle, headFont));
			tradeInfromation.addCell(header);
		});

		values.forEach(val -> {
			addPdfCell(val, Element.ALIGN_LEFT, Element.ALIGN_LEFT, 0, tradeInfromation);
		});

		document.add(tradeInfromation);
	}

	public static void addPdfCell(String text, int verticalAlignment, int horizontalAlignment, int borderWidth,
			PdfPTable pdfTable) {
		PdfPCell cell = new PdfPCell(new Phrase(text, FontFactory.getFont(FontFactory.HELVETICA, 10)));
		cell.setPaddingLeft(4);
		cell.setBorderWidth(borderWidth);
		cell.setVerticalAlignment(verticalAlignment);
		cell.setHorizontalAlignment(horizontalAlignment);
		pdfTable.addCell(cell);
	}

}
