package com.demo.controllers;

import java.io.FileOutputStream;
import java.util.Random;

import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;

import com.demo.model.Account;

@Controller // to indicate that this class is a controller
@SessionAttributes("aNewAccount")
public class MyDemoController {

	private String[] quotes = {"To be or not to be - Shakespeare", "Spring is nature's way of saying Let's Party - Robin Williams", "The time is always right to do what is right - Martin Luther King, Jr."}; 
	
	//@ModelAttribute
	public void addAccountToModel(Model model) {
		
		System.out.println("Making sure account object is on the model");
		if(!model.containsAttribute("aNewAccount")) {
			Account a = new Account();
			model.addAttribute("aNewAccount", a);
		}
	}
	
	
	//@RequestMapping(value="/getQuote", method = RequestMethod.GET )
	//@RequestMapping(value="/getQuote", params = "from", method = RequestMethod.GET )
	//@RequestMapping(value="/getQuote", params = "!from", method = RequestMethod.GET )
	//@RequestMapping(value="/getQuote", params = "from=kunj", method = RequestMethod.GET )
	//@RequestMapping(value="/getQuote", headers = "X-API-KEY", method = RequestMethod.GET )
	@RequestMapping(value="/getQuote", headers = "X-API-KEY=12345", method = RequestMethod.GET )
	public String getRandomQuote(Model model) {
			
		 int rand = new Random().nextInt(quotes.length); 
		 String randomQuote = quotes[rand]; 
		
		model.addAttribute("randomQuote", randomQuote);
		System.out.println("Model updated with random quote");
		return "quote"; // quote is a view name
		
	}
	
	// The only purpose of this method is to enhance the spring Model
	// You cannot call this method from anywhere
 //	@ModelAttribute // to run this method prior to any handler method in the class
	public void setUserDetails(@RequestParam ("userName") String userName, Model model) {
		model.addAttribute( "userName", userName);
		//Simulate going off and retrieving role based on userName 
		String userRole = "undefined";
		if(userName.equals( "Andy")){
			userRole = "Student";
			} else if(userName.equals("John")){ 
				userRole = "Teacher"; 
				} else if(userName.equals("Allana")){
					userRole = "Dean"; 
					} 
		model.addAttribute("userRole", userRole);
		System.out.println("Model updated with user information."); 
		}
	
	
	
	@RequestMapping(value="/createAccount")
	public String createAccount( @Valid @ModelAttribute("aNewAccount") Account account,
								BindingResult result) {
		System.out.println("Inside createAccount()");
		if(result.hasErrors()) {
			System.out.println("Form has errors");
			return "createAccount";
		}
		System.out.println("Form validated");
		System.out.println(account.getFirstName());
		return "createAccount";
	}
	
	@RequestMapping("/doCreate")
	public String doCreate(@ModelAttribute ("aNewAccount") Account account) {
	
		System.out.println("Do Create : " + account.getFirstName());
		return "redirect:accConfirm";
	}
		
	/* if we refresh the "http://localhost:8080/SpringMVCDemo/doCreate" url (without redirect), the same request is being sent every time creating duplications
	   The solution is the below method
	*/
	
	@RequestMapping(value="/accConfirm")
	public String accountConfirmation(@ModelAttribute ("aNewAccount") Account account) {
		
		System.out.println("Account confirmed in accountConfirmation() " + account.getFirstName() + " " + account.getLastName());
		return "accountConfirmed";
	}
	
	
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
	
	@RequestMapping(value="/addCookie")
	public String addCookie(HttpServletResponse response) {
		
		// Add a random cookie
		response.addCookie(new Cookie("myRandomCookie", "aCookieIAdded"));
		System.out.println("Cookie added");
		return "demoPage";
	}
	
	@RequestMapping("/getCookie")
	public String getCookie(@CookieValue("myRandomCookie") String myCookie) {
		System.out.println("cookie retrieved: " + myCookie);
		return "demoPage";
	}
	
}
