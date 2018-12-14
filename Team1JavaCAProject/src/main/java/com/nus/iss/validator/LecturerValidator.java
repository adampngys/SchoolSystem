package com.nus.iss.validator;

import java.util.regex.Pattern;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.nus.iss.model.Lecturer;

@Component
public class LecturerValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return Lecturer.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		// TODO Auto-generated method stub
		Lecturer lecturer = (Lecturer) target;
		ValidationUtils.rejectIfEmpty(errors, "name", "Name cannot be blank");
		ValidationUtils.rejectIfEmpty(errors, "faculty", "Faculty cannot be blank");
		ValidationUtils.rejectIfEmpty(errors, "userInfo.password", "password cannot be blank");

		ValidationUtils.rejectIfEmpty(errors, "email", "email cannot be blank");
		ValidationUtils.rejectIfEmpty(errors, "phone", "phone cannot be blank");

		System.out.println(lecturer.toString());

		if (!(lecturer.getName().isEmpty())) {
			if (!(Pattern.matches("[a-zA-Z ]+", lecturer.getName()))) {

				errors.rejectValue("name", "Name should not be numeric");
			}
		}
		if (!(lecturer.getPhone().isEmpty())) {
			String phone = lecturer.getPhone();

			// should be 8 digit
			if (phone.length() != 8) {
				errors.rejectValue("phone", "Phone should have 8 numbers!");
			}

			// cannot have alphabet
			if (!(Pattern.matches("[0-9]+", lecturer.getPhone()))) {

				errors.rejectValue("phone", "Phone should have only numbers!");
			}

		}

	}

}
