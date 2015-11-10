package org.ucll.demo.ui;

import java.util.Collection;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import org.ucll.demo.service.api.java.to.PersonOverview;

@Named
@RequestScoped
public class PersonOverviewBean extends ServiceBean{
	private Collection<PersonOverview> persons;    
	private String selectedPersonId;

    public Collection<PersonOverview> getPersons() {
        if(persons == null){
            persons = getService().getPersons();
        }
        return persons;
    }

    public String deletePerson() {
    	getService().deletePerson(selectedPersonId);
    	persons = getService().getPersons();
        return "personoverview";
    }

    public String addPerson() {
        return "personform";
    }

    public String viewPerson() {
        return "persondetail";
    }

	public void setSelectedPersonId(String selectedPersonId) {
		this.selectedPersonId = selectedPersonId;
	}
}
