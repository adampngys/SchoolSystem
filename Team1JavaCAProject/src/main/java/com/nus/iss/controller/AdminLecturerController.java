package com.nus.iss.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
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

import com.nus.iss.exception.LecturerNotFound;
import com.nus.iss.exception.MyRepositoryException;
import com.nus.iss.model.CapsUser;
import com.nus.iss.model.Lecturer;
import com.nus.iss.service.AdminLecturerService;
import com.nus.iss.validator.LecturerValidator;

@RequestMapping(value = "/lecturer")
@Controller
public class AdminLecturerController {

	@Autowired
	AdminLecturerService lService;

	@Autowired
	private LecturerValidator lValidator;

	@InitBinder("lecturer")
	private void initLecturerBinder(WebDataBinder binder) {
		binder.addValidators(lValidator);
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView listAll(HttpSession session) {
		UserSession us = (UserSession) session.getAttribute("USERSESSION");
		ModelAndView mav = new ModelAndView();
		try {
			if (us != null) {
				mav = new ModelAndView("managelecturer");
				ArrayList<Lecturer> lecturers = lService.findAllLecturers();
				mav.addObject("lecturers", lecturers);
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
	public ModelAndView newLecturerPage(HttpSession session) {
		UserSession us = (UserSession) session.getAttribute("USERSESSION");
		ModelAndView mav = new ModelAndView();
		try {
			if (us != null) {
				mav = new ModelAndView("lecturerform", "lecturer", new Lecturer());
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
	public ModelAndView createNewLecturer(@ModelAttribute @Valid Lecturer lecturer, BindingResult result,
			HttpServletRequest request, final RedirectAttributes redirectAttributes) throws MyRepositoryException {
		if (result.hasErrors())
			return new ModelAndView("lecturerform");
		ModelAndView mav = new ModelAndView();

		CapsUser user1 = new CapsUser();
		user1.setPassword(lecturer.getUserInfo().getPassword());
		user1.setId(lecturer.getUserInfo().getId());

		try {
			lService.CreateUserAndLecturer(user1, lecturer);
		} catch (MyRepositoryException e) { // TODO Auto-generated catch block
			e.printStackTrace();
		}

		String message = "New lecturer " + lecturer.getLectId() + " was successfully created.";
		mav.setViewName("redirect:/lecturer/list");		
		redirectAttributes.addFlashAttribute("message", message);
		return mav;
	}

	@RequestMapping(value = "/edit/{lectId}", method = RequestMethod.GET)
	public ModelAndView editLecturerPage(@PathVariable Integer lectId, HttpSession session) {

		UserSession us = (UserSession) session.getAttribute("USERSESSION");
		ModelAndView mav = new ModelAndView();
		try {
			if (us != null) {
				mav = new ModelAndView("lecturereditform");
				mav.addObject("lecturer", lService.FindById(lectId));
			} else {

				mav = new ModelAndView("redirect:/");
			}
		} catch (Exception e) {
			e.printStackTrace();
			mav = new ModelAndView("redirect:/");
		}
		return mav;
	}

	@RequestMapping(value = "/edit/{lectId}", method = RequestMethod.POST)
	public ModelAndView editLecturer(@ModelAttribute @Valid Lecturer lecturer, BindingResult result,
			@PathVariable Integer lectId, final RedirectAttributes redirectAttributes) {
		if (result.hasErrors())
			return new ModelAndView("lecturereditform");

		String strPwd = lecturer.getUserInfo().getPassword();

		try {
			lService.UpdateUserAndLecturer(strPwd, lecturer);
		} catch (MyRepositoryException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ModelAndView mav = new ModelAndView("redirect:/lecturer/list");
		String message = "Lecturer " + lecturer.getLectId() + " was successfully updated.";
		redirectAttributes.addFlashAttribute("message", message);
		return mav;
	}

	@RequestMapping(value = "/delete/{lectId}", method = RequestMethod.GET)
	public ModelAndView deleteLecturer(@PathVariable Integer lectId, final RedirectAttributes redirectAttributes, HttpSession session)
			throws LecturerNotFound {
		
		UserSession us = (UserSession) session.getAttribute("USERSESSION");
		ModelAndView mav = new ModelAndView();
		try {
			if (us != null) {
				
				boolean bRes = false;
				try {
					bRes = lService.DeleteUserAndLecturer(lectId);
				} catch (MyRepositoryException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				mav = new ModelAndView("redirect:/lecturer/list");

				String message = "The lecturer " + lectId + " was not deleted.";
				if (bRes) {
					message = "The lecturer " + lectId + " was successfully deleted.";
				}
				redirectAttributes.addFlashAttribute("message", message);				
			}
			else {

				mav = new ModelAndView("redirect:/");
			}
		} catch (Exception e) {
			e.printStackTrace();
			mav = new ModelAndView("redirect:/");
		}
		return mav;
	}
}