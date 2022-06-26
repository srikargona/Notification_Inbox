package com.mobiquity.controller;

import java.util.List;    
import org.springframework.beans.factory.annotation.Autowired;    
import org.springframework.stereotype.Controller;  
import org.springframework.ui.Model;  
import org.springframework.web.bind.annotation.ModelAttribute;    
import org.springframework.web.bind.annotation.PathVariable;    
import org.springframework.web.bind.annotation.RequestMapping;    
import org.springframework.web.bind.annotation.RequestMethod;     
import com.mobiquity.bean.*;    
import com.mobiquity.dao.NotificationDao;    
@Controller    


public class notfcontroller {
	 @Autowired  
	 NotificationDao notf;
	 
	 @RequestMapping("/viewnotf")    
	    public String viewemp(Model m){    
	        List<Notification> list=notf.getNotification();
	        String admin=notf.getAdminDetail();
	        m.addAttribute("list",list);  
	        m.addAttribute("adminDetail",admin);
	        return "viewnotf";
	 }
	 
	 @RequestMapping(value="/read/{serial}")
	 public String markread(@PathVariable int serial, Model m) {
		 System.out.println(serial);
		 notf.markasread(serial);
		 return "viewnotf";
	 }
	 @RequestMapping(value="/delete/{serial}")
	 public String delte(@PathVariable int serial, Model m) {
		 //System.out.println(serial);
		 notf.delete(serial);
		 return "viewnotf";
	 }
	 @RequestMapping(value="/archive/{serial}")
	 public String archive(@PathVariable int serial, Model m) {
		 //System.out.println(serial);
		 notf.archive(serial);
		 return "viewnotf";
	 }
	 @RequestMapping(value="/approve/{serial}")
	 public String approve(@PathVariable String serial, Model m) {
		 System.out.println(serial);
		User temp= notf.find(serial);
		int i=notf.approve(temp);
		
		 return "viewnotf";
	 }
	 
	 @RequestMapping(value="/All")
	 public String All() {
		 //System.out.println("jzdfnhkjd");
		 return "redirect:./save";
	 }
	 
	 @RequestMapping(value="/archive")
	 public String Archive(Model m) {
		 List<Notification> list=notf.getNotification();    
	        m.addAttribute("list",list);
		 return "archive";
	 }
	 
	 @RequestMapping(value="/unread")
	 public String Unread(Model m) {
		 List<Notification> list=notf.getNotification();    
	        m.addAttribute("list",list);
		 return "unread";
	 }

}
