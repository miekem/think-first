package org.ucll.demo.service.api.java.assembler;

import java.util.Date;

import org.ucll.demo.domain.Examination;
import org.ucll.demo.service.api.java.to.ExaminationDetail;

public class ExaminationToAssembler {

    public ExaminationDetail createExaminationDetailTo(int length, int weight, Date date) {
        ExaminationDetail to = new ExaminationDetail();
        to.setLength(length);
        to.setExaminationDate(date);
        to.setWeight(weight);
        return to;
    }

    public ExaminationDetail createExaminationDetailTo(Examination examination) {
        ExaminationDetail to = new ExaminationDetail();
        to.setLength(examination.getLength());
        to.setExaminationDate(examination.getDate());
        to.setWeight(examination.getWeight());
        return to;
    }

    public Examination createExamination(ExaminationDetail to) {
    	return new Examination(to.getLength(), to.getWeight(), to.getExaminationDate());
    }
}
