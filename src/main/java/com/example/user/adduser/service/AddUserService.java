package com.example.user.adduser.service;

import java.util.Date;
import java.util.Optional;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.example.user.adduser.entity.AddUserEntity;

@Service
public class AddUserService {
	@Autowired
    private JavaMailSender javaMailSender;
	
	public static boolean isEmailValid(String email) {
		System.out.println(email);
	    final Pattern rex = Pattern.compile("[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", Pattern.CASE_INSENSITIVE);
	    System.out.println(rex.matcher(email).matches());
	    return rex.matcher(email).matches();
	}
	


	
public static String passwordvalidation(String password, String confirmPassword) {
	if(password.equals(confirmPassword)) {
		return "valid";
		
	} else {
		return "invalid";
		
	}
}
private static final String PHONE_REGEX ="^\\+(?:[0-9] ?){6,14}[0-9]$";
public String validate(String contactno) {
	String pattern = "^(\\+\\d{1,3}( )?)?((\\(\\d{3}\\))|\\d{3})[- .]?\\d{3}[- .]?\\d{4}$";

if (contactno.matches(PHONE_REGEX)) {
	 System.out.println("Valid"); 
	return "Valid";
   
} else if(contactno.matches(pattern))
{
	return"valid"; 
}
	else{
	System.out.println("Invalid"); 
	return "Invalid";
}
}

public void senttextemail(String email){
Date currentUtilDate = new Date();
SimpleMailMessage msg=new SimpleMailMessage();
		msg.setFrom("codabanulab@gmail.com");
				msg.setTo(email);
				System.out.println(email);
				msg.setBcc(email);
				msg.setCc(email);
				msg.setSentDate(currentUtilDate);
			msg.setSubject("Testing from Spring Boot verification");
			msg.setReplyTo("banupriya3195@gmail.com");
			msg.setText("Hello Everyone this is my testing program");
			System.out.println("msg2"+msg);
			javaMailSender.send(msg);
			System.out.println(msg);
			System.out.println("done");

	}






	}


