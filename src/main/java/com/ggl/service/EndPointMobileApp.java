package com.ggl.service;

/*
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import ch.qos.logback.core.net.SyslogOutputStream;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONObject;
import org.json.simple.parser.ParseException;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Context;*/

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.boot.autoconfigure.EnableAutoConfiguration;

//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;

//import javax.websocket.server.PathParam;
import javax.ws.rs.Produces;

import org.springframework.web.bind.annotation.RestController;
import org.codehaus.jettison.json.JSONException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
//import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RequestParam;

import com.ggl.bo.EmployerBo;
import com.ggl.bo.GglBo;
import com.ggl.dto.EmployerDto;
import com.ggl.dto.GLGMem;
import com.ggl.dto.JobSeekerDto;
import com.ggl.dto.Member;
import com.ggl.dto.User;
import com.ggl.mongo.dal.PublicTreeDAL;
import com.ggl.mongo.dal.RandomNumberDAL;
//import com.ggl.mongo.model.Publictree;
import com.google.gson.Gson;
//import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
//import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import javax.ws.rs.Consumes;
import javax.ws.rs.core.MediaType;

import java.io.File;
import java.io.IOException;
//import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
public class EndPointMobileApp implements Filter {

	public static final Logger logger = LoggerFactory.getLogger(EndPointMobileApp.class);

	
	@Autowired
	//@Qualifier("mobile")
	GglBo bo1;
	
	@Autowired
	EmployerBo employerBo;
	
	private final PublicTreeDAL publicTreeDAL;
	List<String> files = new ArrayList<String>();
	
	public EndPointMobileApp(RandomNumberDAL randamNumberDAL,PublicTreeDAL publicTreeDAL) {
			this.publicTreeDAL = publicTreeDAL;
	}


	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {

	    HttpServletRequest request = (HttpServletRequest) req;
	    HttpServletResponse response = (HttpServletResponse) res;

	    response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
	    response.setHeader("Access-Control-Allow-Credentials", "true");
	    response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
	    response.setHeader("Access-Control-Max-Age", "3600");
	    response.setHeader("Access-Control-Allow-Headers", "Content-Type, Accept, X-Requested-With, remember-me");

	    chain.doFilter(req, res);
	}

	@Override
	public void init(FilterConfig filterConfig) {
	}

	@Override
	public void destroy() {
	}

	
	
	

	User user = new User();
	Member member = new Member(); 
	SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd");
	
	HashMap<String,String> hm =  new HashMap<String,String>();
	ArrayList<Member> hotelList = null;
	ArrayList<String> listCountry=null;
	
	@GetMapping("/login")
	@Produces(MediaType.APPLICATION_JSON)
    public User getLogin(String username,String pwd) throws JSONException{
		logger.info("User Name ..............."+username);
		logger.info("Password  ..............."+pwd);
		try{
			user.setUsername(username);
			user.setPassword(pwd);
			user = bo1.userLogin(user);
			logger.info(" Member Number ---------------->"+user.getMemberNumber());
		}catch(Exception e) {
			user.setStatus("Network Error Please try again");
			logger.info("Exception ------------->"+e.getMessage());
		}finally {
			
		}
		return user;

    }   
	  
  	@GetMapping("/getMemberIDValidation")
	@Produces(MediaType.APPLICATION_JSON)
    public User getMemberIDValidation(String memberID) throws JSONException{
		logger.info("User Name ..............."+memberID);
		boolean UIResponse = false;
		try{
			logger.info("Member Number  -->"+memberID);
			UIResponse = bo1.getMemberIDValidate(memberID);
			logger.info("Response ------------------>"+UIResponse);
			if(UIResponse==true) {
				user.setStatus("Valid");
			} 
			if(UIResponse==false) {
				user.setStatus("InValid");
			}
		}catch(Exception e) {
			user.setStatus("Network Error Please try again");
			logger.info("Exception ------------->"+e.getMessage());
		}finally {
			
		}
		return user;
    }
  	
	//@POST
  	@PostMapping("/memberRegister")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public String memberRegister(@RequestBody String myData)throws JSONException
	{
		logger.info("----- Inside memberRegister Method Calling ----");
		Gson gson = new Gson();
		logger.info("json Value----"+myData); 
		JsonParser jsonParser = new JsonParser();
		JsonElement element = jsonParser.parse(myData);
		String refmemberID=element.getAsJsonObject().get("refmemberID").getAsString();
		String emailId=element.getAsJsonObject().get("email").getAsString();
		String country=element.getAsJsonObject().get("selectedCountry").getAsString();
		String phonenumber=element.getAsJsonObject().get("phoneNumber").getAsString();
		String firstName=element.getAsJsonObject().get("firstName").getAsString();
		String lastName=element.getAsJsonObject().get("lastName").getAsString();
		String userName=element.getAsJsonObject().get("username").getAsString();
		String password=element.getAsJsonObject().get("password").getAsString();		
		String bankName = element.getAsJsonObject().get("bankName").getAsString();
		String accountNumber = element.getAsJsonObject().get("bankAcctNumber").getAsString();
		String accountType=element.getAsJsonObject().get("actType").getAsString();
		try{
			logger.info("------------ Inside Try Condition -------------"); 
			member.setRefmemberID(refmemberID);
			member.setEmailID(emailId);
			member.setCountry(country);
			member.setPhoneNumber(phonenumber);
			member.setFirstName(firstName);
			member.setLastName(lastName);
			member.setUsername(userName);
			member.setPassword(password);
			member.setBankName(bankName);
			member.setBankAcctNumber(accountNumber);
			member.setActType(accountType);
			logger.info("User Name ----------->"+userName);
			logger.info("Password ----------->"+password);
			logger.info("Before calling Bo ------------->");
			member=bo1.createMember(member);
			logger.info("Status --------------->"+member.getStatus());
			logger.info("Successfuly called Bo --------------->");
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return gson.toJson(member);
	}
	
	//------------ Employer Register -------------
	@PostMapping("/registerEmployer")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public String registerEmployer(@RequestBody EmployerDto employer)throws JSONException
	{
		 logger.info("Name ....."+employer.getName());
		 logger.info("Phone Number ....."+employer.getPhoneNumber());
		 logger.info("Email Address ....."+employer.getEmailID());
		 logger.info("Password ....."+employer.getPassword());
		 logger.info("Country ....."+employer.getCountry());
		 Gson gson = null;
		 try {
			 gson = new Gson();
			 logger.info("------------ Before Register Employee BO Calling -------------");
			 employer = employerBo.registerEmployer(employer);
			 logger.info("------------ After Register Employee BO Calling -------------");
		 }catch(Exception e) {
			 System.out.println("[UI] RegisterEmp Exception ---------->"+e.getMessage()); 
		 }
		 finally {
			 
		 }
		
		 return gson.toJson(employer);
	}
	
	//------------ Employee Login ---------------
	@GetMapping("/emplyerlogin")
	@Produces(MediaType.APPLICATION_JSON)
    public JobSeekerDto getemplyerlogin(String username,String password) throws JSONException{
		logger.info("User Name ..............."+username);
		logger.info("Password  ..............."+password);
		EmployerDto employer = null; 
		try{
			employer = new EmployerDto();
			employer.setUsername(username); // Email ID
			employer.setPassword(password);
			logger.info("Before Calling Employer BO for LoginEmplyee");
			employer = employerBo.loginEmployer(employer);
			System.out.println("Status ------------>"+employer.getStatus());
			logger.info("Successfully Called Employer BO for LoginEmployer");
		}catch(Exception e) {
			employer.setStatus("Network Error Please try Again");
			logger.info("Exception ------------->"+e.getMessage());
		}finally {
			
		}
		return employer;
    }
	
	//---------- getCountry List -------
	@GetMapping("/getCountryList")
	@Produces(MediaType.APPLICATION_JSON)
	public String countryChange(){
		List<String> listCountry=null;
		Gson gson = new Gson();
		try {
			logger.info("--------- Server side Mobile getCountryList Called -----------");
			listCountry = new ArrayList<String>();
			hm=bo1.getCountry(hm);		
			Set<String> keys = hm.keySet();
		    for(String key: keys){
		        listCountry.add(key);
		    }
	        Collections.sort(listCountry);
	        listCountry.add("Other International");
		}catch(Exception e){
			logger.info("Exception ------------->"+e.getMessage());
		}finally{
			
		}
		return gson.toJson(listCountry);
	}
	
	//---------- get Dashboard List -----
	@GetMapping("/getCompanyList")
	@Produces(MediaType.APPLICATION_JSON)
	public String getCompanyList(String country){
		logger.info("-------------- getCompanyInfo Called--------------");
		hotelList = null;
		int temp = 2;
		String ImagePath = "MobileCompanyList";
		Gson gson = new Gson();
		try{
			logger.info("Chooseen Country --->"+country);
		    Member member = new Member();
		    this.hotelList = new ArrayList<Member>();
		    member.setSelectedCountry(country);
		    member.setSequanceNumber(temp); 
			this.hotelList = bo1.getCountryInfo(member,hotelList,ImagePath);
		}catch(Exception e){
			logger.info("UI Application Exception -->"+e.getMessage());
		}
		finally{
			
		}
		return gson.toJson(hotelList);
	}
	
	@GetMapping("/profileView")
	@Produces(MediaType.APPLICATION_JSON)
	public String profileView(String primaryKey)throws JSONException
	{
		System.out.println("------ Inside profileView Method Calling -----------");
		Gson gson = new Gson();
		try{ 
			System.out.println("primaryKey ID  ----------->"+primaryKey);
			member.setUserloginPrimaryKeyString(primaryKey);
			System.out.println("Before calling Bo ------------->");
			member = bo1.getMyProfile(member);
			System.out.println("Successfuly called Bo --------------->");
		}catch(Exception e){
			e.printStackTrace();
		}
		return gson.toJson(member);
	}
	
	@PostMapping("/profileUpdate")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public String profileUpdate(@RequestBody String jsonValue)throws JSONException
	{
		System.out.println("------ Inside profileUpdate Method Calling -----------");
		Gson gson = new Gson();
		JsonParser jsonParser = new JsonParser();
		JsonElement element = jsonParser.parse(jsonValue);
		System.out.println("json Value----"+jsonValue); 	
		String primaryKey = element.getAsJsonObject().get("primaryKey").getAsString();
		String userName = element.getAsJsonObject().get("userName").getAsString();
		String firstName = element.getAsJsonObject().get("firstName").getAsString();
		String actType = element.getAsJsonObject().get("memberType").getAsString();
		String phonenumber = element.getAsJsonObject().get("phoneNumber").getAsString();
		String emailId = element.getAsJsonObject().get("email").getAsString();		
		String lastName = element.getAsJsonObject().get("lastName").getAsString();
		String country = element.getAsJsonObject().get("country").getAsString();
		String bankName = element.getAsJsonObject().get("bankName").getAsString();
		String accountNumber = element.getAsJsonObject().get("accountNumber").getAsString();
		try{
			member.setUsername(userName);
			member.setUserloginPrimaryKeyString(primaryKey); 
			member.setPhoneNumber(phonenumber);
			member.setEmailID(emailId);
			member.setFirstName(firstName);
			member.setLastName(lastName);
			member.setCountry(country);
			member.setBankName(bankName);
			member.setBankAcctNumber(accountNumber);
			member.setActType(actType); 
			System.out.println("Before calling Bo ------------->");
			member= bo1.updateMyProfile(member);
			System.out.println("Successfuly called Bo --------------->");
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return gson.toJson(member);
	}
	
	@GetMapping("/getHotelNameList")
	@Produces(MediaType.APPLICATION_JSON)
	public String getHotelNameList()throws JSONException
	{
		System.out.println("------ Inside getHotelNameList Method Calling -----------");
		Gson gson = new Gson();	
		int temp;
		ArrayList<String> hotelList = null;
		try{
			hotelList = new ArrayList<String>();
			//if(country == null || country.isEmpty()){
				temp = 1;
			/*}else{
				member.setSelectedCountry(country); 
				member.setSelectedState(state);
				member.setCategoryname(categoryname);
				temp = 2;
			}*/
			System.out.println("Before getHotel BO Calling ------------->");
			hotelList = bo1.getName(hotelList,member,temp);
			for(String hotlename : hotelList) { 
				logger.info("Hotle Names --------->"+hotlename);
			}
			System.out.println("Successfuly called Bo --------------->");
		}catch(Exception e){
			e.printStackTrace();
		}
		return gson.toJson(hotelList);
	}
	
	@PostMapping("/saveHotelBooking")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public String saveHotelBooking(@RequestBody String myData)throws JSONException
	{
		logger.info("----- Inside saveHotelBooking Method Calling ----");
		Gson gson = new Gson();
		logger.info("json Value----"+myData); 
		JsonParser jsonParser = new JsonParser();
		JsonElement element = jsonParser.parse(myData);
		String selectedCountry=element.getAsJsonObject().get("selectedCountry").getAsString();
		String selectedState=element.getAsJsonObject().get("selectedState").getAsString();
		String categoryname=element.getAsJsonObject().get("categoryname").getAsString();
		String companyname=element.getAsJsonObject().get("companyname").getAsString();
		String noofadult=element.getAsJsonObject().get("noofadult").getAsString();
		String noofchild=element.getAsJsonObject().get("noofchild").getAsString();
		String noofrooms=element.getAsJsonObject().get("noofrooms").getAsString();
		String bookingdate=element.getAsJsonObject().get("bookingdate").getAsString();
		String medicaltime=element.getAsJsonObject().get("medicaltime").getAsString();
		int noofTables=element.getAsJsonObject().get("noofTables").getAsInt();
		String primaryKey=element.getAsJsonObject().get("primaryKey").getAsString();
		try{
			logger.info("------------ Inside Try Condition -------------"); 
			member.setUserloginPrimaryKeyString(primaryKey);
			member.setCountry(selectedCountry);
			member.setSelectedState(selectedState);
			member.setCategoryname(categoryname);
			member.setCompanyname(companyname);
			member.setNoofadult(noofadult);
			member.setNoofchild(noofchild);
			member.setNoofrooms(noofrooms);
			member.setBookingdate(ft.parse(bookingdate));
			member.setMedicaltime(medicaltime);
			member.setNoofTables(noofTables);
			member.setNoofpax("");
			
			logger.info("Before calling Bo ------------->");
			member= bo1.saveReservation(member,1); 
			logger.info("Status --------------->"+member.getStatus());
			logger.info("Successfuly called Bo --------------->");
		}catch(Exception e){
			e.printStackTrace();
		}	
		return gson.toJson(member.getStatus());
	}
	
	@GetMapping("/getMyMemberViewList")
	@Produces(MediaType.APPLICATION_JSON)
	public String getMyMemberViewList(String memberNumber)throws JSONException
	{
		System.out.println("------ Inside getMyMemberList Method Calling -----------");
		Gson gson = new Gson();	
		ArrayList<GLGMem> myMemList = null;
		try{
			myMemList = new ArrayList<GLGMem>();
			logger.info("Member Number ----------"+memberNumber); 
			myMemList = bo1.getMyMemberList(memberNumber,myMemList);
			for(GLGMem g :myMemList){
				logger.info("Value ------->"+g.getMemberName());
			}
			System.out.println("Successfuly called Bo --------------->");
		}catch(Exception e){
			e.printStackTrace();
		}
		return gson.toJson(myMemList);
	}
	
	@GetMapping("/getMyBookingList")
	@Produces(MediaType.APPLICATION_JSON)
	public String getMyBookingList(String primaryKey)throws JSONException
	{
		System.out.println("------ Inside getMyBookingList Method Calling -----------");
		Gson gson = new Gson();	
		 ArrayList<Member> list = null;
		try{
			list = new ArrayList<Member>();
			logger.info("Primary Key ----------"+primaryKey); 
			list = bo1.getMyReservationDetails(list, primaryKey);
			System.out.println("Successfuly called Bo --------------->");
		}catch(Exception e){
			e.printStackTrace();
		}
		return gson.toJson(list);
	}
	
	@GetMapping("/getMyBookingView")
	@Produces(MediaType.APPLICATION_JSON)
	public String getMyBookingView(String userloginPrimaryKeyString)throws JSONException
	{
		System.out.println("------ Inside getMyBookingView Method Calling -----------");
		Gson gson = new Gson();	
		logger.info("Booking ID ---------->"+userloginPrimaryKeyString);
		try{
			logger.info("Primary Key ----------"+userloginPrimaryKeyString); 
			member = bo1.getMyReservationView(userloginPrimaryKeyString,member);
			System.out.println("Successfuly called Bo --------------->");
		}catch(Exception e){
			e.printStackTrace();
		}
		return gson.toJson(member);
	}
	
	@PostMapping("/submitWithdraw")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public String submitWithdraw(@RequestBody String jsonValue)throws JSONException
	{
		System.out.println("------ Inside submitWithdraw Method Calling -----------");
		Gson gson = new Gson();
		JsonParser jsonParser = new JsonParser();
		JsonElement element = jsonParser.parse(jsonValue);
		System.out.println("json Value----"+jsonValue); 	
		String memberID = element.getAsJsonObject().get("memberID").getAsString();
		int totalAmount = element.getAsJsonObject().get("totalAmount").getAsInt();
		int commission = element.getAsJsonObject().get("commission").getAsInt();
		int overriding = element.getAsJsonObject().get("overriding").getAsInt();
		try{
			member.setMemberID(memberID);
			member.setTotalAmount(totalAmount);
			member.setMemberCommition(commission); 
			member.setMemberOvrriding(overriding);
			System.out.println("Before calling Bo ------------->");
			if(member.getTotalAmount() == 0){ 
				logger.info(" Withdraw Amount is Equal to zero ");
				member.setStatus("exsist");
			}else {
			 	logger.info("--- Both Withdraw and Total Amount are Equal ---");
			 	member = bo1.submitWith(member);
			}
			System.out.println("Successfuly called Bo --------------->");
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return gson.toJson(member);
	}
	
	@GetMapping("/checkUserName")
	@Produces(MediaType.APPLICATION_JSON)
	public String checkUserName(String username)throws JSONException
	{
		System.out.println("------ Inside checkUserName Method Calling -----------");
		Gson gson = new Gson();	
		logger.info("User Name ---------->"+username);
		try{
			user = new User();
			user.setUsername(username);
			user = bo1.Checkuser(user,1);
			logger.info("Status for userCheck ----"+user.getStatus());
		}catch(Exception e) {
			user.setStatus("Network Error Please try Again");
			logger.info("Exception ------------->"+e.getMessage());
		}finally {
			
		}
		return gson.toJson(user.getStatus());
	}
	  
	@GetMapping("/otpCheck")
	@Produces(MediaType.APPLICATION_JSON)
	public String otpCheck(String otp)throws JSONException
	{
		System.out.println("------ Inside otpCheck Method Calling -----------");
		Gson gson = new Gson();	
		logger.info("OTP ---------->"+otp);
		try{
			user = new User();
			user.setMemberID(otp);
			user = bo1.Checkuser(user,2);
			logger.info("Status for otpCheck ----"+user.getStatus());
		}catch(Exception e) {
			user.setStatus("Network Error Please try Again");
			logger.info("Exception ------------->"+e.getMessage());
		}finally {
			
		}
		return gson.toJson(user.getStatus());
	}
	
	@GetMapping("/changePassword")
	@Produces(MediaType.APPLICATION_JSON)
	public String changePassword(String newPassword1,String forgetUser)throws JSONException
	{
		System.out.println("------ Inside changePassword Method Calling -----------");
		Gson gson = new Gson();	
		logger.info("[UI Application-resetPassword] New Password ---------------->"+newPassword1);
		logger.info("[UI Application-resetPassword] User Name ---------------->"+forgetUser);
		try {
			user = new User();
			user.setPassword(newPassword1);
			user.setUsername(forgetUser);
			user = bo1.Checkuser(user,3);	
			logger.info("Status for changePassword ----"+user.getStatus());
		}catch(Exception e) {
			user.setStatus("Network Error Please try Again");
			logger.info("Exception ------------->"+e.getMessage());
		}finally {
			
		}
		return gson.toJson(user.getStatus());
	}
	
	@PostMapping("/uploadimage")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public String uploadimage(@RequestBody String treeimage)throws JSONException, IOException{
		logger.debug("inside image upload-----");
 		Gson gson=new Gson();
 		JsonParser jsonParser = new JsonParser();
		JsonElement element = jsonParser.parse(treeimage);
		String id=element.getAsJsonObject().get("srcData").getAsString();
		String invoiceNumber=element.getAsJsonObject().get("invoiceNumber").getAsString();
		String treeName=element.getAsJsonObject().get("treeName").getAsString();
		try{
			System.out.println("Image File ------->"+id);
			System.out.println("Invoice Number ------->"+invoiceNumber);
			System.out.println("Tree Name ------->"+treeName);
			String base64Image = id.split(",")[1];
 			//byte[] decoded = Base64.decodeBase64(base64Image);
 			File files = new File(base64Image);
			// publicTreeDAL.storeImage(files,invoiceNumber,treeName);
			// files.add(files.getOriginalFilename());
			// message = "You successfully uploaded " + file.getOriginalFilename() + "!";
		}catch(Exception e){
			e.printStackTrace();
		}
		return gson.toJson("Success");
	}
	
	
}
