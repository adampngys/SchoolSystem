package com.nus.iss.validator;

import java.util.regex.Pattern;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.nus.iss.model.StdCourse;

@Component
public class GradeValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return StdCourse.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		// TODO Auto-generated method stub
		StdCourse s = (StdCourse) target;
		ValidationUtils.rejectIfEmpty(errors, "grade", "Grade field cannot be empty");

		// System.out.println(s.toString());

		if (!(s.getGrade().isEmpty())) {
			if ((Pattern.matches("[a-zA-Z]+", s.getGrade())) || (Pattern.matches("[/[^\\d]/g,'']+", s.getGrade()))) {

				errors.rejectValue("grade", "Grade must be numeric");
			}
		}

	}

}
