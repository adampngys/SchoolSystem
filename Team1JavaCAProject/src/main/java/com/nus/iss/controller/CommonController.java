package com.nus.iss.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.nus.iss.exception.UserNotFoundException;
import com.nus.iss.model.CapsUser;
import com.nus.iss.service.LecturerService;
import com.nus.iss.service.StudentService;
import com.nus.iss.service.UserService;

@Controller
public class CommonController {

	@Autowired
	private UserService userService;
	@Autowired
	private StudentService sService;
	@Autowired
	private LecturerService lService;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String logic(Model model) {
		model.addAttribute("user", new CapsUser());
		return "login";
	}

	@RequestMapping(value = "/relogin", method = RequestMethod.GET)
	public String reloginlogic(Model model, @ModelAttribute("errormsg") boolean errorMsg) {
		if (errorMsg == true) {
			model.addAttribute("errormsg", "User not found.");
		}

		model.addAttribute("user", new CapsUser());
		return "login";
	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(Model model, HttpSession session) {
		session.removeAttribute("USERSESSION");
		model.addAttribute("user", new CapsUser());
		return "login";
	}

	@RequestMapping(value = "/authenticate", method = RequestMethod.POST)
	public ModelAndView authenticate(@ModelAttribute CapsUser user, HttpSession session, BindingResult result,
			RedirectAttributes attributes) throws UserNotFoundException {
		UserSession us = new UserSession();
		ModelAndView mav = new ModelAndView("hello");
		CapsUser u1 = null;
		try {
			u1 = userService.authenticate(user.getId(), user.getPassword());
			mav.addObject(us);
		} catch (UserNotFoundException e) {
			e.printStackTrace();
			attributes.addAttribute("errormsg", true);
			mav = new ModelAndView("redirect:/relogin");
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (u1 != null) {
			us.setSessionId(session.getId());
			us.setUser(u1);
			us.getRole();
			mav.addObject("userSession", us);
			roleEnum userRole = us.getRole();
			String name = "";

			switch (userRole) {
			case admin:
				mav = new ModelAndView("yolo");
				name = "Admin";
				mav.addObject("name", name);
				session.setAttribute("USERSESSION", us);
				break;
			case lecturer:
				mav = new ModelAndView("yolo");
				us.setSessionId(session.getId());
				us.setLecturer(lService.findByLecturerId(us.getUser().getLec().getLect_id_pk()));
				session.setAttribute("USERSESSION", us);
				break;
			case student:
				mav = new ModelAndView("yolo");
				us.setSessionId(session.getId());
				us.setStudent(sService.findStudentById(us.getUser().getStd().getId()));
				session.setAttribute("USERSESSION", us);
				break;
			}
		}
		return mav;
	}

}
