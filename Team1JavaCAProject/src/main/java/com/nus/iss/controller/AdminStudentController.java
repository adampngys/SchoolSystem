package com.nus.iss.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.nus.iss.model.CapsUser;
import com.nus.iss.model.Student;
import com.nus.iss.service.AdminStudentService;
import com.nus.iss.validator.StudentValidator;

@RequestMapping(value = "/student")
@Controller
public class AdminStudentController {

	@Autowired
	AdminStudentService sService;

	@Autowired
	private StudentValidator sValidator;

	@InitBinder("student")
	private void initLecturerBinder(WebDataBinder binder) {
		binder.addValidators(sValidator);
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView listAll(HttpSession session) {

		UserSession us = (UserSession) session.getAttribute("USERSESSION");
		ModelAndView mav = new ModelAndView();
		try {
			if (us != null) {
				mav = new ModelAndView("managestudent");
				ArrayList<Student> students = sService.findAllStudents();
				mav.addObject("students", students);
			} else {

				mav = new ModelAndView("redirect:/");
			}
		} catch (Exception e) {
			e.printStackTrace();
			mav = new ModelAndView("redirect:/");
		}
		return mav;
	}

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView newStudentPage(HttpSession session) {
		UserSession us = (UserSession) session.getAttribute("USERSESSION");
		ModelAndView mav = new ModelAndView();
		try {
			if (us != null) {
				mav = new ModelAndView("studentform", "student", new Student());
			} else {

				mav = new ModelAndView("redirect:/");
			}
		} catch (Exception e) {
			e.printStackTrace();
			mav = new ModelAndView("redirect:/");
		}
		return mav;
	}

	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public ModelAndView createNewStudent(@ModelAttribute @Valid Student student, BindingResult result,
			final RedirectAttributes redirectAttributes) {
		if (result.hasErrors())
			return new ModelAndView("studentform");
		ModelAndView mav = new ModelAndView();

		CapsUser user1 = new CapsUser();
		user1.setPassword(student.getUserInfo().getPassword());
		user1.setId(student.getUserInfo().getId());

		sService.CreateUserAndStudent(user1, student);
		String message = "New student " + student.getNric() + " was successfully created.";
		mav.setViewName("redirect:/student/list");
		redirectAttributes.addFlashAttribute("message", message);
		return mav;
	}

	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	public ModelAndView editStudentPage(@PathVariable Integer id, HttpSession session) {

		UserSession us = (UserSession) session.getAttribute("USERSESSION");
		ModelAndView mav = new ModelAndView();
		try {
			if (us != null) {
				mav = new ModelAndView("studenteditform");
				mav.addObject("student", sService.findStudent(id));
			} else {

				mav = new ModelAndView("redirect:/");
			}
		} catch (Exception e) {
			e.printStackTrace();
			mav = new ModelAndView("redirect:/");
		}
		return mav;
	}
	/*
	 * @RequestMapping(value = "/edit/{id}", method = RequestMethod.POST) public
	 * ModelAndView editStudent(@ModelAttribute @Valid Student student,
	 * BindingResult result,
	 * 
	 * @PathVariable Integer id, final RedirectAttributes redirectAttributes) {
	 * System.out.println("student" + student.toString()); if (result.hasErrors())
	 * return new ModelAndView("studenteditform"); sService.updateStudent(student);
	 * ModelAndView mav = new ModelAndView("redirect:/student/list"); String message
	 * = "Student" + student.getId() + " was successfully updated.";
	 * redirectAttributes.addFlashAttribute("message", message); return mav; }
	 */

	@RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
	public ModelAndView editStudent(@ModelAttribute @Valid Student student, BindingResult result,
			@PathVariable Integer id, final RedirectAttributes redirectAttributes) {
		if (result.hasErrors())
			return new ModelAndView("studenteditform");

		String strPwd = student.getUserInfo().getPassword();

		sService.UpdateUserAndStudent(strPwd, student);

		ModelAndView mav = new ModelAndView("redirect:/student/list");
		String message = "Student " + student.getId() + " was successfully updated.";
		redirectAttributes.addFlashAttribute("message", message);
		return mav;
	}

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	public ModelAndView deleteStudent(@PathVariable Integer id, final RedirectAttributes redirectAttributes,
			HttpSession session) {

		UserSession us = (UserSession) session.getAttribute("USERSESSION");
		ModelAndView mav = new ModelAndView();
		try {
			if (us != null) {
				Student student = sService.findStudent(id);
				sService.removeStudent(student);
				mav = new ModelAndView("redirect:/student/list");
				String message = "The student " + student.getId() + " was successfully deleted.";
				redirectAttributes.addFlashAttribute("message", message);
			} else {

				mav = new ModelAndView("redirect:/");
			}
		} catch (Exception e) {
			e.printStackTrace();
			mav = new ModelAndView("redirect:/");
		}
		return mav;
	}

}