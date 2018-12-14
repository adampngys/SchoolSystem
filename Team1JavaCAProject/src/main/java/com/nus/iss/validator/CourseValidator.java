package com.nus.iss.validator;

import java.util.regex.Pattern;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.nus.iss.model.Course;

@Component
public class CourseValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return Course.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		// TODO Auto-generated method stub
		Course course = (Course) target;
		ValidationUtils.rejectIfEmpty(errors, "name", "* mandatory");
		ValidationUtils.rejectIfEmpty(errors, "duration", "* mandatory");
		ValidationUtils.rejectIfEmpty(errors, "courseId", "* mandatory");
		ValidationUtils.rejectIfEmpty(errors, "size", "* mandatory");
		ValidationUtils.rejectIfEmpty(errors, "credit", "* mandatory");
		ValidationUtils.rejectIfEmpty(errors, "vacancy", "* mandatory");
		System.out.println(course.toString());

		if (!(course.getName().isEmpty())) {
			if (!(Pattern.matches("[a-zA-Z ]+", course.getName()))) {

				errors.rejectValue("name", "Name should not be numeric");
			}
		}
		// if(course.getStartDate() < )

	}

}
