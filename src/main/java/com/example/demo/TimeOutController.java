package com.example.demo;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.security.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ch.qos.logback.classic.spi.STEUtil;

@RestController
public class TimeOutController {

	@GetMapping("test")
	public String getData() throws InterruptedException {
		Thread.sleep(8000);
		return "dsfds";
	}

	@GetMapping(value = "/print", produces = MediaType.APPLICATION_PDF_VALUE)
	public ResponseEntity<InputStreamResource> customerReport(@RequestParam int pages) throws IOException {
		List<StaReport> reports = new ArrayList<StaReport>();
		for (int i = 0; i < pages; i++) {
			byte[] array = new byte[24]; // length is bounded by 7
			new Random().nextBytes(array);
			String generatedString = new String(array, Charset.forName("UTF-8"));
			if (i == 0) {
				generatedString = "John David";
			}

			StaReport staReport = new StaReport();
			staReport.setClientName("Mr. " + generatedString);
			staReport.setMdmId("17739972_"+i);
			staReport.setBrokarageAccount("69PW123456");
			staReport.setStatus("Approved");
			staReport.setFirstComments("test");
			staReport.setSecondComments("N/A");
			staReport.setPrincipleApproval(
					"Spectrum - All New Accounts (via STA) >= $500K OR PVC(AGE>=75) and other PVC Categories");
			staReport.setReasonForApproval("N/A");
			staReport.setNsdApproval("N/A");

			List<TradeDetails> trades = new ArrayList<TradeDetails>();
			trades.add(new TradeDetails("Buy", "Solicited", "Spectrum", "500,00", "Non Qualified"));

			staReport.setTradeDetails(trades);

			List<AuditTrail> auditTrail = new ArrayList<AuditTrail>();
			auditTrail.add(new AuditTrail("John", "Approved", "aasdas sfsdf sdfds", "sdfdsfds",
					new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date())));
			auditTrail.add(new AuditTrail("Raj Kumar", "Approved", "aasdas sfsdf sdfds", "sdfdsfds",
					new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date())));
			auditTrail.add(new AuditTrail("SANJEEV", "Approved", "aasdas sfsdf sdfds", "sdfdsfds",
					new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date())));
			auditTrail.add(new AuditTrail("Daniel", "Rejected", "aasdas sfsdf sdfds", "sdfdsfds",
					new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date())));

			staReport.setAuditTrail(auditTrail);
			
			reports.add(staReport);
		}

		ByteArrayInputStream bis = PDFGenerator.usersPDFReport(reports);

		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Disposition", "inline; filename=customers.pdf");

		return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF)
				.body(new InputStreamResource(bis));
	}

}
