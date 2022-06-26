package com.mobiquity.bean;

import java.sql.Time;
import java.sql.Date;

public class Notification {
	private String sender,subject, content;
	private Date date;
	private Time time;
	private boolean read;
	public int serial;
	public boolean delete, archive;
	
	public void setSender(String send) {
		this.sender= send;
	}
	public String getSender() {
		return sender;
	}
	public void setSubject(String sub) {
		this.subject=sub;
	}
	public String getSubject() {
		return subject;
	}
	
	public void setContent(String con) {
		this.content=con;
	}
	public String getcontent() {
		return content;
	}
	
	public void setDate(Date d) {
		this.date=d;
	}
	public Date getDate() {
		return date;
	}
	
	public void setTime(Time t) {
		this.time =t;
	}
	public Time getTime() {
		return time;
	}
	
	public void setRead(boolean r) {
		this.read=r;
	}
	public boolean getRead() {
		return read;
	}
	
	public int getSerial() {
		return serial;
	}
	public void setSerial(int s) {
		this.serial=s;
	}
	
	public boolean getDelete() {
		return delete;
	}
	public void setDelete(boolean d ) {
		this.delete=d;
	}
	
	public boolean getArchive() {
		return archive;
	}
	public void setArchive(boolean d ) {
		this.archive=d;
	}

}
