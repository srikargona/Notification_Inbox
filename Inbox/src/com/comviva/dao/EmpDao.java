package com.comviva.dao;  
import java.sql.ResultSet;   
import java.sql.SQLException;
import java.sql.*;
import java.util.*;  
import org.springframework.jdbc.core.BeanPropertyRowMapper;  
import org.springframework.jdbc.core.JdbcTemplate;  
import org.springframework.jdbc.core.RowMapper;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.math.BigInteger; 
import java.security.MessageDigest; 
import java.security.NoSuchAlgorithmException;

import com.comviva.model.Emp;  
 


public class EmpDao {  
	
JdbcTemplate template;  
  
public void setTemplate(JdbcTemplate template) {  
    this.template = template;  
}  

enum level{ ca , na, sa}

public static String maker;
public static int adminid;


public void createCheckerNotifications( final String makerLevel, final String contentjava,final String makerusername) {
	
    template.query("select username,adminLevel,adminid from admins",new RowMapper<Emp>()
    {  
        public Emp mapRow(ResultSet rs, int row) throws SQLException 
        {  
            String username = rs.getString(1);
            String adminLevel = rs.getString(2);
            int id=rs.getInt(3);
            //System.out.println(adminLevel);
            //System.out.println(makerLevel);
            
            
            if(level.valueOf(adminLevel).ordinal() >= level.valueOf(makerLevel).ordinal() && !(username.equals(makerusername)))
            {
            	System.out.println(username);
	            //System.out.println(maker);
           	 //date
           	 DateFormat df = new SimpleDateFormat("dd/MM/yy");
           	 Date dateobj = new Date();
           	 String datejava=df.format(dateobj);
           	   
           	 //time
           	 DateFormat df1 = new SimpleDateFormat("HH:mm:ss");
           	 String timejava=df1.format(dateobj);
           	
           	 
           	 String sqlnotifyadmin ="insert into notifications(sender,content,date,time,checker_id) values('"+makerusername+"','"+contentjava+"','"+ datejava+"','"+timejava+"',"+id+")";
           	 template.update(sqlnotifyadmin);
           	 
            }
            Emp e1=new Emp();
            return e1;
        }  
    }  );
}

public int newreg(Emp p) throws Exception
{
	
	//password encryption
	System.out.println(p.getName()+p.getNewpassword()+p.getAdminlevel());
	MessageDigest md = MessageDigest.getInstance("SHA-256"); 
    byte[] messageDigest = md.digest(p.getNewpassword().getBytes()); 
    BigInteger no = new BigInteger(1, messageDigest); 
    String hashtext = no.toString(16); 

    while (hashtext.length() < 32) 
    { 
        hashtext = "0" + hashtext; 
    } 
   
	String sql="insert into mobiquityuserreg values('"+p.getName()+"','"+p.getMobile()+"','"+p.getEmail()+"',"+"'"+hashtext+"','"+p.getAdminlevel()+"')";
	 
	
	 //storing data in the notification table
	 //date
	 DateFormat df = new SimpleDateFormat("dd/MM/yy");
	 Date dateobj = new Date();
	 String datejava=df.format(dateobj);
	   
	 //time
	 DateFormat df1 = new SimpleDateFormat("HH:mm:ss");
	 String timejava=df1.format(dateobj);
	 
	 final String s1=p.getName();
	 String s2=p.getEmail();
	 String s3=p.getMobile();
	 final String contentjava_maker=(" "+p.getName()+" "+p.getEmail()+" "+p.getMobile()+" ");
	 //final String contentjava_checker=(" Name : "+p.getName()+"<br> Email Id:  "+p.getEmail()+"<br> Mobile No: "+p.getMobile()+"<br>");
	 System.out.println(adminid);
	 System.out.println(maker);
	 
	 String maker1="'"+maker+"'";
	 System.out.println(maker1);
	 int id1=adminid;
	 String sub= "You have raised the following request ";
	 String sqlnotify ="insert into notifications(sender,subject,content,date,time,checker_id) values('"+maker+"','"+sub+"','"+contentjava_maker+"','"+ datejava+"','"+timejava+"',"+id1+")";
	 template.update(sqlnotify);
	 final String adminlevel=p.getAdminlevel();
	 createCheckerNotifications(adminlevel,contentjava_maker,maker);
	 
	 return template.update(sql);
}



public List<Emp> getEmployees(Emp p) throws Exception
{  
	     
	    //password encryption
	   
		MessageDigest md = MessageDigest.getInstance("SHA-256"); 
	    byte[] messageDigest = md.digest(p.getPassword().getBytes()); 
	    BigInteger no = new BigInteger(1, messageDigest); 
	    String passwordent = no.toString(16); 

	    while (passwordent.length() < 32) 
	    { 
	        passwordent = "0" + passwordent; 
	    } 
	    		
	    		   template.query("select adminid,username from admins where STRCMP(username,\"" + p.getUsername()+"\") = 0",new RowMapper<Emp>()
	    		    {  
	    		        public Emp mapRow(ResultSet rs, int row) throws SQLException 
	    		        {  
	    		            Emp e=new Emp();  
	    		            e.setAdminid(rs.getInt(1));
	    		            e.setUsername(rs.getString(2));
	    		            adminid=e.getAdminid();
	    		            maker=e.getUsername();
	    		            return e;
	    		        }  
	    		    }  );
	    
	   
    return template.query("select username,password from admins where STRCMP(username,\"" + p.getUsername()+"\") = 0 and STRCMP(password,\""+passwordent+"\")= 0",new RowMapper<Emp>()
    {  
        public Emp mapRow(ResultSet rs, int row) throws SQLException 
        {  
            Emp e=new Emp();  
            e.setUsername(rs.getString(1));  
            e.setPassword(rs.getString(2)); 
            System.out.println(e.getPassword());
            return e;
        }  
    }  );
}  
}  