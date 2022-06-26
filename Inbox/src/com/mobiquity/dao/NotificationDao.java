package com.mobiquity.dao;

import java.sql.ResultSet;    
import java.sql.SQLException;    
import java.util.List;    
import org.springframework.jdbc.core.BeanPropertyRowMapper;    
import org.springframework.jdbc.core.JdbcTemplate;    
import org.springframework.jdbc.core.RowMapper;

import com.mobiquity.bean.*;

import com.comviva.dao.EmpDao;


public class NotificationDao{
	EmpDao obj1= new EmpDao();
	static int x;
	//public int x= obj1.adminid;
	
	JdbcTemplate template;
	public void setTemplate(JdbcTemplate template) {    
	    this.template = template;
	    }
	
	
	
	public List<Notification> getNotification(){  
		
		
		x=obj1.adminid;
		System.out.println("admin id in notfcontroller id:"+x);
	    return template.query("select * from notifications  where checker_id="+x+"",new RowMapper<Notification>(){  
	        public Notification mapRow(ResultSet rs, int row) throws SQLException {  
	            Notification notf=new Notification();  
	            notf.setSender( rs.getString(2));
				notf.setSubject( rs.getString(3));
				notf.setContent( rs.getString(4));
				notf.setDate( rs.getDate(5));
				notf.setTime( rs.getTime(6));  
				notf.setRead(rs.getBoolean(7));
				notf.setSerial(rs.getInt(1));
				notf.setDelete(rs.getBoolean(8));
				notf.setArchive(rs.getBoolean(9));
	            return notf;  
	        }  
	    });  
	}
	
	public void markasread(int s) {
		//System.out.println("Read2");
		template.update("update notifications  set `read`=? where serial=?;",true,s);
	}
	
	public void delete(int s) {
		//System.out.println("Read2");
		template.update("update notifications  set `delete`=? where serial=?;",true,s);
	}
	
	public void archive(int s) {
		//System.out.println("Read2");
		template.update("update notifications  set `archive`=? where serial=?;",true,s);
	}
	
	public User find(String s) {
		System.out.println(s);
		String sql="select * from mobiquityuserreg where name=?";  
	    return template.queryForObject(sql, new Object[]{s},new UserMapper());
	    }
	
	public int approve(User obj) {
		String name= obj.getUserName();
		System.out.println(name);
		String pass= obj.getPassword();
		System.out.println(pass);
		String level = obj.getLevel();
		System.out.println(level);
		String sql="insert into admins(username, password,adminLevel) values('"+name+"','"+pass+"','"+level+"')";  
	    return template.update(sql);  
	}



	public String getAdminDetail() {
		
		return obj1.maker;
	}
	
}
