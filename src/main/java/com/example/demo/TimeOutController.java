package com.example.demo;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TimeOutController {

	@GetMapping("test")
	public String getData() throws InterruptedException {
		Thread.sleep(8000);
		return "dsfds";
	}

	@GetMapping(value = "/users",
	            produces = MediaType.APPLICATION_PDF_VALUE)
	    public ResponseEntity<InputStreamResource> customerReport() throws IOException {
	        List<User> users = new ArrayList<User>();
	        
	        users.add(new User("Anil Kumar", "Somasi"));
	        users.add(new User("Malathi", "Garndhi"));
	        users.add(new User("Mythili", "Nishtala"));
	        users.add(new User("Sai Krishna", "Koganti"));
	        users.add(new User("Leela Pavani", "Mujje"));
	        for(int i=0;i<=1000;i++) {
	        	users.add(new User("Anil Kumar_"+i, "Somasi_"+i));
	        }
	        ByteArrayInputStream bis = PDFGenerator.usersPDFReport(users);

	        HttpHeaders headers = new HttpHeaders();
	        headers.add("Content-Disposition", "inline; filename=customers.pdf");

	        return ResponseEntity
	                .ok()
	                .headers(headers)
	                .contentType(MediaType.APPLICATION_PDF)
	                .body(new InputStreamResource(bis));
	    }

}
