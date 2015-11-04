package org.ucll.demo.ui;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.ucll.demo.service.api.java.to.ExaminationDetail;

@Named
@RequestScoped
public class ExaminationBean extends FormBean {
	
	private ExaminationDetail examination = new ExaminationDetail();
	
	@Inject
	private PersonBean personBean;
	
	private String personId;

	public String getPersonId() {
		return personId;
	}
	
	public void setPersonId(String personId) {
		this.personId = personId;
	} 
	
    public ExaminationDetail getExamination() {
        return examination;
    }

    public void setExamination(ExaminationDetail examination) {
        this.examination = examination;
    }

	private String navigateToPersonDetail() {
		personBean.setSelectedPersonId(getPersonId());
        return "persondetail";
	}
    
    public String saveExamination() {
    	String nextPage = "";
    	try {
            getService().addExamination(examination, getPersonId());
            nextPage = navigateToPersonDetail();
    	} catch (Exception e) {
    		setMessage(e);
    		nextPage = "examinationdetail";
    	}
        return nextPage;
    }
}