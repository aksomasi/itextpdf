package com.example.demo;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.List;
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
import com.itextpdf.text.pdf.PdfWriter;

public class PDFGenerator {

	public static void addParagraph(String fontName, Float fintSize, BaseColor color, String text, Document document,
			int alignment) throws DocumentException {
		Paragraph para = new Paragraph(text, FontFactory.getFont(fontName, fintSize, color));
		para.setAlignment(alignment);
		document.add(para);

	}

	public static ByteArrayInputStream usersPDFReport(List<StaReport> reports) {
		Document document = new Document();
		ByteArrayOutputStream out = new ByteArrayOutputStream();

		try {

			CallReport callReportData = new CallReport();
			
			PdfWriter.getInstance(document, out);
			document.open();

			CallReportsPrint.callReport(callReportData,document);
			
			for (StaReport staReport : reports) {
				// Add Text to PDF file ->

				Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 18, BaseColor.BLUE);
				Paragraph para = new Paragraph("SUPERVISOR TRADE APPROVAL Report", font);
				para.setAlignment(Element.ALIGN_CENTER);
				document.add(para);
				document.add(Chunk.NEWLINE);

				font = FontFactory.getFont(FontFactory.COURIER, 14, BaseColor.BLACK);
				para = new Paragraph("STA STATUS: " + staReport.getStatus(), font);
				para.setAlignment(Element.ALIGN_RIGHT);
				document.add(para);
				font = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 14, BaseColor.BLACK);
				para = new Paragraph("Trade Information", font);
				para.setAlignment(Element.ALIGN_LEFT);
				document.add(para);
				document.add(designTradeInfromation(staReport));

				document.add(Chunk.NEWLINE);

				para = new Paragraph("Trade Details", font);
				para.setAlignment(Element.ALIGN_LEFT);
				document.add(para);
				document.add(designTradeDetails(staReport));

				document.add(Chunk.NEWLINE);

				designRrRotaionale(staReport, document);

				document.add(Chunk.NEWLINE);

				para = new Paragraph("Justification for Approval", font);
				para.setAlignment(Element.ALIGN_LEFT);
				document.add(para);
				document.add(designApproval(staReport));

				document.add(Chunk.NEWLINE);

				para = new Paragraph("AuditTrail", font);
				para.setAlignment(Element.ALIGN_LEFT);
				document.add(para);
				document.add(designAuidtTrail(staReport));

				document.add(Chunk.NEXTPAGE);

			}

			document.close();
		} catch (DocumentException e) {
			System.out.println(e.toString());
		}

		return new ByteArrayInputStream(out.toByteArray());
	}

	public static PdfPTable designTradeInfromation(StaReport staReport) {
		PdfPTable tradeInfromation = new PdfPTable(3);

		Stream.of("Client Name", "MDM ID", "Broakarage Account Number").forEach(headerTitle -> {
			PdfPCell header = new PdfPCell();
			Font headFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
			header.setHorizontalAlignment(Element.ALIGN_CENTER);
			header.setBorderWidth(0);
			header.setPhrase(new Phrase(headerTitle, headFont));
			tradeInfromation.addCell(header);
		});
		PdfPCell clientNameCell = new PdfPCell(new Phrase(staReport.getClientName()));
		clientNameCell.setPaddingLeft(4);
		clientNameCell.setBorderWidth(0);
		clientNameCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		clientNameCell.setHorizontalAlignment(Element.ALIGN_CENTER);
		tradeInfromation.addCell(clientNameCell);

		PdfPCell mdmIdCell = new PdfPCell(new Phrase(staReport.getMdmId()));
		mdmIdCell.setPaddingLeft(4);
		mdmIdCell.setBorderWidth(0);
		mdmIdCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		mdmIdCell.setHorizontalAlignment(Element.ALIGN_CENTER);
		tradeInfromation.addCell(mdmIdCell);

		PdfPCell brAccountCell = new PdfPCell(new Phrase(String.valueOf(staReport.getBrokarageAccount())));
		brAccountCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		brAccountCell.setHorizontalAlignment(Element.ALIGN_CENTER);
		brAccountCell.setBorderWidth(0);
		brAccountCell.setPaddingRight(4);
		tradeInfromation.addCell(brAccountCell);
		return tradeInfromation;
	}

	public static PdfPTable designTradeDetails(StaReport staReport) {
		PdfPTable designAuidtTrail = new PdfPTable(5);

		// Add PDF Table Header ->
		Stream.of("Type of Trade", "Type of Praposal", "Secuirty Name", "Trade Amount", "Type of Funds")
				.forEach(headerTitle -> {
					PdfPCell header = new PdfPCell();
					Font headFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
					header.setPaddingBottom(5);
					header.setBackgroundColor(BaseColor.LIGHT_GRAY);
					header.setHorizontalAlignment(Element.ALIGN_CENTER);
					header.setBorderWidth(1);
					header.setPhrase(new Phrase(headerTitle, headFont));
					designAuidtTrail.addCell(header);
				});

		for (TradeDetails trade : staReport.getTradeDetails()) {
			PdfPCell typeCell = new PdfPCell(new Phrase(trade.getTypeofTrade()));
			typeCell.setPaddingLeft(4);
			typeCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			typeCell.setHorizontalAlignment(Element.ALIGN_CENTER);
			designAuidtTrail.addCell(typeCell);

			PdfPCell praposalCell = new PdfPCell(new Phrase(trade.getTypeofPraposal()));
			praposalCell.setPaddingLeft(4);
			praposalCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			praposalCell.setHorizontalAlignment(Element.ALIGN_LEFT);
			designAuidtTrail.addCell(praposalCell);

			PdfPCell secuirtyCell = new PdfPCell(new Phrase(String.valueOf(trade.getSecuirtyName())));
			secuirtyCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			secuirtyCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
			secuirtyCell.setPaddingRight(4);
			designAuidtTrail.addCell(secuirtyCell);

			PdfPCell amountCell = new PdfPCell(new Phrase(String.valueOf(trade.getTradeamound())));
			amountCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			amountCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
			amountCell.setPaddingRight(4);
			designAuidtTrail.addCell(amountCell);

			PdfPCell fundCell = new PdfPCell(new Phrase(String.valueOf(trade.getTradefunds())));
			fundCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			fundCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
			fundCell.setPaddingRight(4);
			designAuidtTrail.addCell(fundCell);
		}
		return designAuidtTrail;
	}

	public static PdfPTable designAuidtTrail(StaReport staReport) {
		PdfPTable designAuidtTrail = new PdfPTable(5);

		// Add PDF Table Header ->
		Stream.of("Updated By", "Status", "Description", "comment", "updated time").forEach(headerTitle -> {
			PdfPCell header = new PdfPCell();
			Font headFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
			header.setBackgroundColor(BaseColor.LIGHT_GRAY);
			header.setHorizontalAlignment(Element.ALIGN_CENTER);
			header.setBorderWidth(2);
			header.setPhrase(new Phrase(headerTitle, headFont));
			designAuidtTrail.addCell(header);
		});

		for (AuditTrail audit : staReport.getAuditTrail()) {
			PdfPCell updatedByCell = new PdfPCell(new Phrase(audit.getUpdatedBy()));
			updatedByCell.setPaddingLeft(4);
			updatedByCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			updatedByCell.setHorizontalAlignment(Element.ALIGN_CENTER);
			designAuidtTrail.addCell(updatedByCell);

			PdfPCell statusCell = new PdfPCell(new Phrase(audit.getStatus()));
			statusCell.setPaddingLeft(4);
			statusCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			statusCell.setHorizontalAlignment(Element.ALIGN_LEFT);
			designAuidtTrail.addCell(statusCell);

			PdfPCell descriptionCell = new PdfPCell(new Phrase(String.valueOf(audit.getDescription())));
			descriptionCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			descriptionCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
			descriptionCell.setPaddingRight(4);
			designAuidtTrail.addCell(descriptionCell);

			PdfPCell commentsCell = new PdfPCell(new Phrase(String.valueOf(audit.getComment())));
			commentsCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			commentsCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
			commentsCell.setPaddingRight(4);
			designAuidtTrail.addCell(commentsCell);

			PdfPCell updateTimeCell = new PdfPCell(new Phrase(String.valueOf(audit.getUpdatedTime())));
			updateTimeCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			updateTimeCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
			updateTimeCell.setPaddingRight(4);
			designAuidtTrail.addCell(updateTimeCell);
		}
		return designAuidtTrail;
	}

	public static PdfPTable designApproval(StaReport staReport) {
		PdfPTable designApproval = new PdfPTable(1);
		Stream.of("First Approval/Rejection").forEach(headerTitle -> {
			PdfPCell header = new PdfPCell();
			Font headFont = FontFactory.getFont(FontFactory.COURIER, 14);
			header.setHorizontalAlignment(Element.ALIGN_LEFT);
			header.setBorderWidth(0);
			header.setPhrase(new Phrase(headerTitle, headFont));
			designApproval.addCell(header);
		});
		Font cellFont = FontFactory.getFont(FontFactory.COURIER, 14);

		PdfPCell designApproval1 = new PdfPCell(new Phrase(String.valueOf(staReport.getFirstComments())));
		designApproval1.setVerticalAlignment(Element.ALIGN_MIDDLE);
		designApproval1.setHorizontalAlignment(Element.ALIGN_CENTER);
		designApproval1.setBorderWidth(1);
		designApproval.addCell(designApproval1);

		PdfPCell designApproval2 = new PdfPCell(new Phrase(String.valueOf("Second Approval/Rejection"), cellFont));
		designApproval2.setVerticalAlignment(Element.ALIGN_MIDDLE);
		designApproval2.setHorizontalAlignment(Element.ALIGN_LEFT);
		designApproval2.setBorderWidth(0);
		designApproval2.setPaddingRight(4);
		designApproval.addCell(designApproval2);

		PdfPCell designApproval3 = new PdfPCell(new Phrase(String.valueOf(staReport.getSecondComments())));
		designApproval3.setVerticalAlignment(Element.ALIGN_MIDDLE);
		designApproval3.setHorizontalAlignment(Element.ALIGN_CENTER);
		designApproval3.setBorderWidth(1);
		designApproval3.setPaddingRight(4);
		designApproval.addCell(designApproval3);

		return designApproval;
	}

	public static void designRrRotaionale(StaReport staReport, Document document) throws DocumentException {
		addParagraph(FontFactory.HELVETICA_BOLD, (float) 18, BaseColor.BLACK, "RR Rotationale", document,
				Element.ALIGN_LEFT);

		addParagraph(FontFactory.HELVETICA_BOLD, (float) 12, BaseColor.BLACK, "New Account Transaction", document,
				Element.ALIGN_LEFT);

		addParagraph(FontFactory.HELVETICA, (float) 12, BaseColor.BLACK, staReport.getPrincipleApproval(), document,
				Element.ALIGN_LEFT);

		addParagraph(FontFactory.HELVETICA_BOLD, (float) 12, BaseColor.BLACK, "If New Account/Transaction", document,
				Element.ALIGN_LEFT);

		addParagraph(FontFactory.HELVETICA, (float) 12, BaseColor.BLACK, staReport.getReasonForApproval(), document,
				Element.ALIGN_LEFT);

		addParagraph(FontFactory.HELVETICA_BOLD, (float) 12, BaseColor.BLACK, "Non retirment Account/Transaction",
				document, Element.ALIGN_LEFT);

		addParagraph(FontFactory.HELVETICA, (float) 12, BaseColor.BLACK, staReport.getNsdApproval(), document,
				Element.ALIGN_LEFT);

	}
}