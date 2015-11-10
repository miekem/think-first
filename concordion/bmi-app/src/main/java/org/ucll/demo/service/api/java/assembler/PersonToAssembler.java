package org.ucll.demo.service.api.java.assembler;

import java.util.ArrayList;
import java.util.List;

import org.ucll.demo.domain.Examination;
import org.ucll.demo.domain.Person;
import org.ucll.demo.service.api.java.to.ExaminationDetail;
import org.ucll.demo.service.api.java.to.PersonDetail;
import org.ucll.demo.service.api.java.to.PersonOverview;

public class PersonToAssembler {

    private ExaminationToAssembler examinationToAssembler = new ExaminationToAssembler();

    public PersonDetail createPersonDetailTo(Person person) {
        PersonDetail to = new PersonDetail();
        to.setSocialSecurityNumber(person.getSocialSecurityNumber());
        to.setBirthdate(person.getBirthDate());
        to.setBmi(person.getBmi());
        to.setGender(person.getGender());
        to.setSocialSecurityNumber(person.getSocialSecurityNumber());

        ExaminationDetail examinationDetailTo = examinationToAssembler
                .createExaminationDetailTo(person.getLength(),
                        person.getWeight(), person.getLastExaminationDate());
        to.setExaminationDetail(examinationDetailTo);
        return to;
    }

    public List<PersonOverview> createPersonOverviewTos(List<?> persons) {
        List<PersonOverview> tos = new ArrayList<PersonOverview>();

        for (Object o : persons) {
            PersonOverview to = new PersonOverview();
            Person person = (Person) o;
            to.setSocialSecurityNumber(person.getSocialSecurityNumber());
            tos.add(to);
        }
        return tos;
    }

    public Person createPerson(PersonDetail to) {
        Person person;
            ExaminationDetail examinationDetail = to.getExaminationDetail();
			Examination examination = new Examination(
                    examinationDetail.getLength(),
                    examinationDetail.getWeight(),
                    examinationDetail.getExaminationDate());

            person = new Person(
                    to.getSocialSecurityNumber(),
                    to.getBirthdate(),
                    to.getGender(),
                    examination);

		return person;
	}
}
