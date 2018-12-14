package com.nus.iss.validator;

import java.util.regex.Pattern;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.nus.iss.model.Student;

@Component
public class StudentValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return Student.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		// TODO Auto-generated method stub

		Student student = (Student) target;
		ValidationUtils.rejectIfEmpty(errors, "name", "Name cannot be blank");
		ValidationUtils.rejectIfEmpty(errors, "nric", "Name cannot be blank");
		ValidationUtils.rejectIfEmpty(errors, "userInfo.id", "User ID cannot be blank");
		ValidationUtils.rejectIfEmpty(errors, "phone", "Phone cannot be blank");
		System.out.println(student.toString());

		if (!(student.getName().isEmpty())) {
			if (!(Pattern.matches("[a-zA-Z ]+", student.getName()))) {

				errors.rejectValue("name", "Name should not be numeric");
			}
		}

		if (!(student.getPhone().isEmpty())) {
			String phone = student.getPhone();
			if (phone.length() != 8) {
				errors.rejectValue("phone", "Phone should have 8 numbers!");
			}
			if (!(Pattern.matches("[0-9]+", student.getPhone()))) {

				errors.rejectValue("phone", "Phone should have only numbers!");
			}
		}
		String nric = student.getNric();
		if (nric.length() != 9) {
			errors.rejectValue("nric", "NRIC should have 9 numbers!");
		}

	}

}
