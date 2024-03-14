package com.ticketingapp.ticketingapp.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {

	@Autowired
	private JavaMailSender mailSender;

	@RequestMapping(value = "/")
	public ModelAndView test(HttpServletResponse response) throws IOException {
		return new ModelAndView("home");
	}

	@RequestMapping(path = "/home")
	public ModelAndView homepage(HttpServletResponse response) throws IOException {
		return new ModelAndView("home");
	}

	@RequestMapping(path = "/tickets")
	public ModelAndView viewtickets() throws IOException {
		return new ModelAndView("tickets");
	}

	@RequestMapping(path = "/mail")
	public ModelAndView mail() throws IOException {
		return new ModelAndView("mail");
	}

	@RequestMapping(value = "/EmailSendingServlet", method = RequestMethod.POST)
	public ModelAndView doSendEmail(HttpServletRequest request) {
		// takes input from e-mail form
		String toMail = request.getParameter("tomail");
		String subject = request.getParameter("subject");
		String body = request.getParameter("body");

		// printing mail info
		System.out.println("To: " + toMail);
		System.out.println("Subject: " + subject);
		System.out.println("Message: " + body);
		try {
			// creates a simple e-mail object
			SimpleMailMessage email = new SimpleMailMessage();

			email.setTo(toMail);
			email.setSubject(subject);
			email.setText(body);

			System.out.println(email);

			// sending email

			mailSender.send(email);
		}

		catch (Exception e) {
			return new ModelAndView("error");
		}
		return new ModelAndView("success");
	}
}
