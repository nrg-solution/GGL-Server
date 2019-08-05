package com.ggl.email;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.ggl.dto.Member;
import com.ggl.mongo.model.PrivateTree;
import com.ggl.mongo.model.Publictree;
import com.ggl.util.PushEmail;

public class InvestmentEmail {

	public static final Logger logger = LoggerFactory.getLogger(InvestmentEmail.class);

	
	// After 8 out and 1 gets Saved as new Tree
	
	
	
public static void temp8OutandOnseSavedPublicTree(Member member,String emailID) {
		
		StringBuffer sb = new StringBuffer();
		int tempAmount=0;
	//	for(Member m: list) {
			sb.append("<h3>  Unit Chain Invoice Number #:"+member.getInvoiceNumber()+"</h3>");
			//m.getInvoiceNumber();
			tempAmount=tempAmount+100;
	//	}
		System.out.println("String --->"+sb);
		String email ="<!DOCTYPE html>\r\n" + 
				"<html lang=\"en\">\r\n" + 
				"<head>  \r\n" + 
				"</head>\r\n" + 
				"<body>\r\n" + 
				"<div class=\"container\">\r\n" + 
				"<div style=\"background-color: #3c8dbc !important; width: 320px; color: white;padding-left: 200px; padding-right: 200px;\">\r\n" + 
				"<h2> Public Tree Invoice Info</h2>\r\n" + 
				"</div>\r\n" + 
				"</div>\r\n" + 
				"<div class=\"container-fluid bg-grey\">\r\n" + 
				"<div class=\"row\"> \r\n" + 
				"<div class=\"col-sm-8\">\r\n" + 
				"<h2>Thank you for buy the Public Unit from GGL Investement:</h2>\r\n" + 
				"<br>	  \r\n" + sb + 
				"Amount Details :  " +tempAmount+"SGD\r\n" + 
				"Note Please make the payment via Wire transfer or Online payment For and on behalf of Global Gains Limited\r\n" + 
				"</div>\r\n" + 
				"</div>\r\n" + 
				"</div>\r\n" + 
				"<footer class=\"container-fluid text-center\">\r\n" + 
				"<a href=\"#myPage\" title=\"To Top\">\r\n" + 
				"<span class=\"glyphicon glyphicon-chevron-up\"></span>\r\n" + 
				"</a>\r\n" + 
				"<p>GGL System Made By <a href=\"https://www.gglway.com\" title=\"Visit gglway.com\">www.gglway.com</a></p>\r\n" + 
				"</footer>\r\n" + 
				"</body>\r\n" + 
				"</html>\r\n" + 
				"";
		try {

			
			logger.info("Calling Email Service -------------");
			PushEmail.sendMail(emailID,"GGL Public Tree | Purchase Order",email);
			logger.info("Successfully  Email Called Service------------");	
			
		logger.info("Successfully Saved data.");
		}catch(Exception e) {
			logger.info("Exception -->"+e.getMessage());
		}
		
		}


	public static void tempPublicTree(ArrayList<Member> list,String emailID) {
		
		StringBuffer sb = new StringBuffer();
		int tempAmount=0;
		for(Member m: list) {
			sb.append("<h3>  Unit Invoice Number #:"+m.getInvoiceNumber()+"</h3>");
			//m.getInvoiceNumber();
			tempAmount=tempAmount+100;
		}
		System.out.println("String --->"+sb);
		String email ="<!DOCTYPE html>\r\n" + 
				"<html lang=\"en\">\r\n" + 
				"<head>  \r\n" + 
				"</head>\r\n" + 
				"<body>\r\n" + 
				"<div class=\"container\">\r\n" + 
				"<div style=\"background-color: #3c8dbc !important; width: 320px; color: white;padding-left: 200px; padding-right: 200px;\">\r\n" + 
				"<h2> Public Tree Invoice Info</h2>\r\n" + 
				"</div>\r\n" + 
				"</div>\r\n" + 
				"<div class=\"container-fluid bg-grey\">\r\n" + 
				"<div class=\"row\"> \r\n" + 
				"<div class=\"col-sm-8\">\r\n" + 
				"<h2>Thank you for buy the Public Unit from GGL Investement:</h2>\r\n" + 
				"<br>	  \r\n" + sb + 
				"Amount Details :  " +tempAmount+"SGD\r\n" + 
				"Note Please make the payment via Wire transfer or Online payment For and on behalf of Global Gains Limited\r\n" + 
				"</div>\r\n" + 
				"</div>\r\n" + 
				"</div>\r\n" + 
				"<footer class=\"container-fluid text-center\">\r\n" + 
				"<a href=\"#myPage\" title=\"To Top\">\r\n" + 
				"<span class=\"glyphicon glyphicon-chevron-up\"></span>\r\n" + 
				"</a>\r\n" + 
				"<p>GGL System Made By <a href=\"https://www.gglway.com\" title=\"Visit gglway.com\">www.gglway.com</a></p>\r\n" + 
				"</footer>\r\n" + 
				"</body>\r\n" + 
				"</html>\r\n" + 
				"";
		try {

			
			logger.info("Calling Email Service -------------");
			PushEmail.sendMail(emailID,"GGL Public Tree | Purchase Order",email);
			logger.info("Successfully  Email Called Service------------");	
			
		logger.info("Successfully Saved data.");
		}catch(Exception e) {
			logger.info("Exception -->"+e.getMessage());
		}
		
		}
	
	// Own Tree
	public static void tempOwnTree(ArrayList<Member> list,String emailID) {
		
		StringBuffer sb = new StringBuffer();
		int tempAmount=0;
		for(Member m: list) {
			sb.append("<h3>  Own Unit Invoice Number #:"+m.getInvoiceNumber()+"</h3>");
			//m.getInvoiceNumber();
			tempAmount=tempAmount+100;
		}
		System.out.println("String --->"+sb);
		String email ="<!DOCTYPE html>\r\n" + 
				"<html lang=\"en\">\r\n" + 
				"<head>  \r\n" + 
				"</head>\r\n" + 
				"<body>\r\n" + 
				"<div class=\"container\">\r\n" + 
				"<div style=\"background-color: #3c8dbc !important; width: 320px; color: white;padding-left: 200px; padding-right: 200px;\">\r\n" + 
				"<h2> Own Tree Invoice Info</h2>\r\n" + 
				"</div>\r\n" + 
				"</div>\r\n" + 
				"<div class=\"container-fluid bg-grey\">\r\n" + 
				"<div class=\"row\"> \r\n" + 
				"<div class=\"col-sm-8\">\r\n" + 
				"<h2>Thank you for buy the Own Unit from GGL Investement:</h2>\r\n" + 
				"<br>	  \r\n" + sb + 
				"Amount Details :  " +tempAmount+"SGD\r\n" + 
				"Note Please make the payment via Wire transfer or Online payment For and on behalf of Global Gains Limited\r\n" + 
				"</div>\r\n" + 
				"</div>\r\n" + 
				"</div>\r\n" + 
				"<footer class=\"container-fluid text-center\">\r\n" + 
				"<a href=\"#myPage\" title=\"To Top\">\r\n" + 
				"<span class=\"glyphicon glyphicon-chevron-up\"></span>\r\n" + 
				"</a>\r\n" + 
				"<p>GGL System Made By <a href=\"https://www.gglway.com\" title=\"Visit gglway.com\">www.gglway.com</a></p>\r\n" + 
				"</footer>\r\n" + 
				"</body>\r\n" + 
				"</html>\r\n" + 
				"";
		try {

			
			logger.info("Calling Email Service -------------");
			PushEmail.sendMail(emailID,"GGL Own Tree | Purchase Order",email);
			logger.info("Successfully  Email Called Service------------");	
			
		logger.info("Successfully Saved data.");
		}catch(Exception e) {
			logger.info("Exception -->"+e.getMessage());
		}
		
		}
	
	
	// Private Tree
	
		public static void tempPrivateTree(ArrayList<Member> list,String emailID) {
			
			StringBuffer sb = new StringBuffer();
			int tempAmount=0;
			for(Member m: list) {
				sb.append("<h3>Temp Private Unit Invoice Number #:"+m.getInvoiceNumber()+"</h3>");
				//m.getInvoiceNumber();
				tempAmount=tempAmount+100;
			}
			System.out.println("String --->"+sb);
			String email ="<!DOCTYPE html>\r\n" + 
					"<html lang=\"en\">\r\n" + 
					"<head>  \r\n" + 
					"</head>\r\n" + 
					"<body>\r\n" + 
					"<div class=\"container\">\r\n" + 
					"<div style=\"background-color: #3c8dbc !important; width: 320px; color: white;padding-left: 200px; padding-right: 200px;\">\r\n" + 
					"<h2> Private Tree Invoice Info</h2>\r\n" + 
					"</div>\r\n" + 
					"</div>\r\n" + 
					"<div class=\"container-fluid bg-grey\">\r\n" + 
					"<div class=\"row\"> \r\n" + 
					"<div class=\"col-sm-8\">\r\n" + 
					"<h2>Thank you for buy the Private Unit from GGL Investement:</h2>\r\n" + 
					"<br>	  \r\n" + sb + 
					"Amount Details :  " +tempAmount+"SGD\r\n" + 
					"Note Pleae make the payment via Wire transfer or Online payment For and on behalf of Global Gains Limited\r\n" + 
					"</div>\r\n" + 
					"</div>\r\n" + 
					"</div>\r\n" + 
					"<footer class=\"container-fluid text-center\">\r\n" + 
					"<a href=\"#myPage\" title=\"To Top\">\r\n" + 
					"<span class=\"glyphicon glyphicon-chevron-up\"></span>\r\n" + 
					"</a>\r\n" + 
					"<p>GGL System Made By <a href=\"https://www.gglway.com\" title=\"Visit gglway.com\">www.gglway.com</a></p>\r\n" + 
					"</footer>\r\n" + 
					"</body>\r\n" + 
					"</html>\r\n" + 
					"";
			try {

				
				logger.info("Calling Email Service -------------");
				PushEmail.sendMail(emailID,"GGL Private Tree | Purchase Order",email);
				logger.info("Successfully  Email Called Service------------");	
				
			logger.info("Successfully Saved data.");
			}catch(Exception e) {
				logger.info("Exception -->"+e.getMessage());
			}
			
			}
		
		
		// Private
		
	public static void approvePrivateTree(PrivateTree l ,String emailID) {
			
			StringBuffer sb = new StringBuffer();
			int tempAmount=100;
			//for(Member m: list) {
				sb.append("<h3>  Private Unit Original Invoice Number #:"+l.getInvoiceNumber()+"</h3>");
				//m.getInvoiceNumber();
				//tempAmount=tempAmount+100;
			//}
			//System.out.println("String --->"+sb);
			String email ="<!DOCTYPE html>\r\n" + 
					"<html lang=\"en\">\r\n" + 
					"<head>  \r\n" + 
					"</head>\r\n" + 
					"<body>\r\n" + 
					"<div class=\"container\">\r\n" + 
					"<div style=\"background-color: #3c8dbc !important; width: 320px; color: white;padding-left: 200px; padding-right: 200px;\">\r\n" + 
					"<h2> Private Tree Invoice Info</h2>\r\n" + 
					"</div>\r\n" + 
					"</div>\r\n" + 
					"<div class=\"container-fluid bg-grey\">\r\n" + 
					"<div class=\"row\"> \r\n" + 
					"<div class=\"col-sm-8\">\r\n" + 
					"<h2>Thank you for buy the Private Unit from GGL Investement:</h2>\r\n" + 
					"<br>	  \r\n" + sb + 
					"Paid Amount Details :  " +tempAmount+"SGD\r\n" + 
					"Note Please trak and check in My status in Your Member Login in GGL System behalf of Global Gains Limited\r\n" + 
					"</div>\r\n" + 
					"</div>\r\n" + 
					"</div>\r\n" + 
					"<footer class=\"container-fluid text-center\">\r\n" + 
					"<a href=\"#myPage\" title=\"To Top\">\r\n" + 
					"<span class=\"glyphicon glyphicon-chevron-up\"></span>\r\n" + 
					"</a>\r\n" + 
					"<p>GGL System Made By <a href=\"https://www.gglway.com\" title=\"Visit gglway.com\">www.gglway.com</a></p>\r\n" + 
					"</footer>\r\n" + 
					"</body>\r\n" + 
					"</html>\r\n" + 
					"";
			try {

				
				logger.info("Calling Email Service -------------");
				PushEmail.sendMail(emailID,"GGL Private Tree | Purchase Order Confirmed Invoice",email);
				logger.info("Successfully  Email Called Service------------");	
				
			logger.info("Successfully Saved data.");
			}catch(Exception e) {
				logger.info("Exception -->"+e.getMessage());
			}
			
			}
		
		// Own Tree
public static void approveOwnTree(ArrayList<Member> list,String emailID,String owntreeName) {
			
			StringBuffer sb = new StringBuffer();
			int tempAmount=0;
			for(Member m: list) {
				sb.append("<h3> Own Tree Unit Original Invoice Number #:"+m.getInvoiceNumber()+"</h3>");
				//m.getInvoiceNumber();
				tempAmount=tempAmount+100;
			}
			System.out.println("String --->"+sb);
			String email ="<!DOCTYPE html>\r\n" + 
					"<html lang=\"en\">\r\n" + 
					"<head>  \r\n" + 
					"</head>\r\n" + 
					"<body>\r\n" + 
					"<div class=\"container\">\r\n" + 
					"<div style=\"background-color: #3c8dbc !important; width: 320px; color: white;padding-left: 200px; padding-right: 200px;\">\r\n" + 
					"<h2> Own Tree Invoice Info</h2>\r\n" + 
					"</div>\r\n" + 
					"</div>\r\n" + 
					"<div class=\"container-fluid bg-grey\">\r\n" + 
					"<div class=\"row\"> \r\n" + 
					"<div class=\"col-sm-8\">\r\n" + 
					"<h2>Your OWN Tree Name : "+owntreeName+"</h2>\r\n" + 
					"<h2>Thank you for buy the Own Tree Unit from GGL Investement:</h2>\r\n" + 
					"<br>	  \r\n" + sb + 
					"Paid Amount Details :  " +tempAmount+"SGD\r\n" + 
					"Note Please make note Please check the status of your Unit details on behalf of Global Gains Limited\r\n" + 
					"</div>\r\n" + 
					"</div>\r\n" + 
					"</div>\r\n" + 
					"<footer class=\"container-fluid text-center\">\r\n" + 
					"<a href=\"#myPage\" title=\"To Top\">\r\n" + 
					"<span class=\"glyphicon glyphicon-chevron-up\"></span>\r\n" + 
					"</a>\r\n" + 
					"<p>GGL System Made By <a href=\"https://www.gglway.com\" title=\"Visit gglway.com\">www.gglway.com</a></p>\r\n" + 
					"</footer>\r\n" + 
					"</body>\r\n" + 
					"</html>\r\n" + 
					"";
			try {

				
				logger.info("Calling Email Service -------------");
				PushEmail.sendMail(emailID,"GGL Own Tree | Purchase Order Confirmed Invoice",email);
				logger.info("Successfully  Email Called Service------------");	
				
			logger.info("Successfully Saved data.");
			}catch(Exception e) {
				logger.info("Exception -->"+e.getMessage());
			}
			
			}
		

// Approved Public Tree 
		
		public static void approvePublicTree( Publictree l ,String emailID) {
			
			StringBuffer sb = new StringBuffer();
			int tempAmount=100;
			//for(Member m: list) {
				sb.append("<h3>  Public Unit Original Invoice Number #:"+l.getInvoiceNumber()+"</h3>");
				//m.getInvoiceNumber();
				//tempAmount=tempAmount+100;
			//}
			//System.out.println("String --->"+sb);
			String email ="<!DOCTYPE html>\r\n" + 
					"<html lang=\"en\">\r\n" + 
					"<head>  \r\n" + 
					"</head>\r\n" + 
					"<body>\r\n" + 
					"<div class=\"container\">\r\n" + 
					"<div style=\"background-color: #3c8dbc !important; width: 320px; color: white;padding-left: 200px; padding-right: 200px;\">\r\n" + 
					"<h2> Public Tree Invoice Info</h2>\r\n" + 
					"</div>\r\n" + 
					"</div>\r\n" + 
					"<div class=\"container-fluid bg-grey\">\r\n" + 
					"<div class=\"row\"> \r\n" + 
					"<div class=\"col-sm-8\">\r\n" + 
					"<h2>Thank you for buy the Private Unit from GGL Investement:</h2>\r\n" + 
					"<br>	  \r\n" + sb + 
					"Paid Amount Details :  " +tempAmount+"SGD\r\n" + 
					"Note Please check the status in Your Member Login in GGL System behalf of Global Gains Limited\r\n" + 
					"</div>\r\n" + 
					"</div>\r\n" + 
					"</div>\r\n" + 
					"<footer class=\"container-fluid text-center\">\r\n" + 
					"<a href=\"#myPage\" title=\"To Top\">\r\n" + 
					"<span class=\"glyphicon glyphicon-chevron-up\"></span>\r\n" + 
					"</a>\r\n" + 
					"<p>GGL System Made By <a href=\"https://www.gglway.com\" title=\"Visit gglway.com\">www.gglway.com</a></p>\r\n" + 
					"</footer>\r\n" + 
					"</body>\r\n" + 
					"</html>\r\n" + 
					"";
			try {

				
				logger.info("Calling Email Service -------------");
				PushEmail.sendMail(emailID,"GGL Public Tree | Purchase Order Confirmed Invoice",email);
				logger.info("Successfully  Email Called Service------------");	
				
			logger.info("Successfully Saved data.");
			}catch(Exception e) {
				logger.info("Exception -->"+e.getMessage());
			}
			
			}
		
		
}
