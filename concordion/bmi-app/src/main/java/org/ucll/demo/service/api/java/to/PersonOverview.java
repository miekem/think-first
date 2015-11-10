package org.ucll.demo.service.api.java.to;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class PersonOverview {

	@NotNull(message="{error.no.socialsecuritynumber}")
	@Size(min=1, message="{error.no.socialsecuritynumber}")
	private String socialSecurityNumber;
	
	public PersonOverview(){
	}

	public PersonOverview(String socialSecurityNumber){
		setSocialSecurityNumber(socialSecurityNumber);
	}
	
	public String getSocialSecurityNumber() {
		return socialSecurityNumber;
	}
	public void setSocialSecurityNumber(String socialSecurityNumber) {
		this.socialSecurityNumber = socialSecurityNumber;
	}
	@Override
	public String toString(){
		return getSocialSecurityNumber();
	}
}
