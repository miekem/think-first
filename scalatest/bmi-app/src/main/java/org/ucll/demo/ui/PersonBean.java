package org.ucll.demo.ui;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import org.ucll.demo.domain.Gender;
import org.ucll.demo.service.api.java.to.ExaminationDetail;
import org.ucll.demo.service.api.java.to.PersonDetail;

@Named
@RequestScoped
public class PersonBean extends FormBean {
	private String selectedPersonId;

	private PersonDetail person;
	private ExaminationDetail examination = new ExaminationDetail();

	public Gender[] getGenderValues() {
		return Gender.values();
	}

	public void setPerson(PersonDetail person) {
		this.person = person;
	}

	public String cancel() {
		return "personoverview";
	}

	public String savePerson() {
		String nextPage = "";
		try {
			person.setExaminationDetail(examination);
			getService().addPerson(person);
			nextPage = "personoverview";
		} catch (Exception e) {
			setMessage(e);
			nextPage = "personform";
		}
		return nextPage;
	}
	
    public ExaminationDetail getExamination() {
        return examination;
    }

    public void setExamination(ExaminationDetail examination) {
        this.examination = examination;
    }

	private String navigateToPersonDetail() {
        return "persondetail";
	}
    
    public String saveExamination() {
    	String nextPage = "";
    	try {
            getService().addExamination(examination, selectedPersonId);
            nextPage = navigateToPersonDetail();
    	} catch (Exception e) {
    		setMessage(e);
    		nextPage = "examinationdetail";
    	}
        return nextPage;
    }

	public String addExamination() {
		return "examinationdetail";
	}

	public boolean isNewPerson() {
		return selectedPersonId == null;
	}

	public PersonDetail getPerson() {
		if(person == null){
			if(selectedPersonId == null){
				person = new PersonDetail();
			} else {
				person = getService().getPerson(selectedPersonId);
				examination = person.getExaminationDetail();
			}
		}
		return person;
	}

	public String getSelectedPersonId() {
		return selectedPersonId;
	}

	public void setSelectedPersonId(String selectedPersonId) {
		this.selectedPersonId = selectedPersonId;
	}

}