package com.veterinaria.entity;



import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmailValuesDTO {
	
	 private String mailFrom;
	    private String mailTo;
	    private String subject;
	    private String userName;
	    private String tokenPassword;

	    public EmailValuesDTO() {
	    }

}
