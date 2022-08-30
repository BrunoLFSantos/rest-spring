package br.com.brunosantos.exceptions;

import java.io.Serializable;
import java.util.Date;

public class ExceptionResponse implements Serializable{

	private static final long serialVersionUID = 1L;
	private Date timeStampe;
	private String message;
	private String details;
	public ExceptionResponse(Date timeStampe, String message, String details) {
		this.timeStampe = timeStampe;
		this.message = message;
		this.details = details;
	}
	public Date getTimeStampe() {
		return timeStampe;
	}
	public void setTimeStampe(Date timeStampe) {
		this.timeStampe = timeStampe;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getDetails() {
		return details;
	}
	public void setDetails(String details) {
		this.details = details;
	}
	
	

}
