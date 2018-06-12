package com.demo.controllers;

import java.io.FileOutputStream;
import java.util.Random;

import javax.servlet.annotation.MultipartConfig;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.demo.model.Account;

@Controller // to indicate that this class is a controller
public class MyDemoController {

	private String[] quotes = {"To be or not to be - Shakespeare", "Spring is nature's way of saying Let's Party - Robin Williams", "The time is always right to do what is right - Martin Luther King, Jr."}; 
	
	/*@RequestMapping(value="/getQuote", method = RequestMethod.GET )
	public String getRandomQuote(Model model) {
			
		 int rand = new Random().nextInt(quotes.length); 
		 String randomQuote = quotes[rand]; 
		
		model.addAttribute("randomQuote", randomQuote);
		return "quote"; // quote is a view name
		
	}*/
	
	/*@RequestMapping(value="/createAccount")
	public String createAccount( @Valid @ModelAttribute("aNewAccount") Account account,
								BindingResult result) {
		if(result.hasErrors()) {
			System.out.println("Form has errors");
			return "createAccount";
		}
		System.out.println("Form validated");
		System.out.println(account.getFirstName());
		return "createAccount";
	}*/
	
	/*@RequestMapping(value="/accountCreated", method=RequestMethod.POST)
	public String performCreate(Account account) {
		System.out.println(account.getFirstName());
		return "accountCreated";
	}*/
	
	@RequestMapping(value="/myForm")
	public String myForm() {
		return "myForm";
	}
	
	@RequestMapping(value="/handleForm")
	public String handleForm(@RequestParam("file") MultipartFile file) {
		try {
			if(!file.isEmpty()) {
				byte[] bytes = file.getBytes();
				FileOutputStream fos = new FileOutputStream("C:\\Users\\Administrator\\Desktop\\Spring_Boot.png");
				fos.write(bytes);
				fos.close();
				System.out.println("file saved successfully");
			} else {
				System.out.println("No file available to save");
			}
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}		
		return "operationComplete";
	}
	
	
}
