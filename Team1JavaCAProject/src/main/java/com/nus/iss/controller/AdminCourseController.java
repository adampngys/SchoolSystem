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

import com.nus.iss.exception.CourseNotFound;
import com.nus.iss.model.Course;
import com.nus.iss.service.AdminCourseService;
import com.nus.iss.validator.CourseValidator;

@RequestMapping(value = "/course")
@Controller
public class AdminCourseController {

	@Autowired
	AdminCourseService cService;

	@Autowired
	private CourseValidator cValidator;

	@InitBinder("course")
	private void initLecturerBinder(WebDataBinder binder) {
		binder.addValidators(cValidator);
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView listAll(HttpSession session) {
		UserSession us = (UserSession) session.getAttribute("USERSESSION");
		ModelAndView mav = new ModelAndView();
		try {
			if (us != null) {
				mav = new ModelAndView("managecourses");
				ArrayList<Course> courses = cService.findAllCourses();
				mav.addObject("courses", courses);
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
	public ModelAndView newCoursePage(HttpSession session) {
		UserSession us = (UserSession) session.getAttribute("USERSESSION");
		ModelAndView mav = new ModelAndView();
		try {
			if (us != null) {
				mav = new ModelAndView("courseform", "course", new Course());
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
	public ModelAndView createNewCourse(@ModelAttribute @Valid Course course, BindingResult result,
			final RedirectAttributes redirectAttributes) {
		if (result.hasErrors())
			return new ModelAndView("courseform");
		ModelAndView mav = new ModelAndView();

		cService.createCourse(course);
		String message = "New course " + course.getCourseId() + " was successfully created.";
		mav.setViewName("redirect:/course/list");
		redirectAttributes.addFlashAttribute("message", message);
		return mav;
	}

	@RequestMapping(value = "/edit/{courseId}", method = RequestMethod.GET)
	public ModelAndView editCoursePage(@PathVariable String courseId, HttpSession session,
			RedirectAttributes attributes) {
		UserSession us = (UserSession) session.getAttribute("USERSESSION");
		ModelAndView mav = new ModelAndView();
		try {
			if (us != null) {
				mav = new ModelAndView("courseeditform");
				mav.addObject("course", cService.findCourse(courseId));
			} else {

				mav = new ModelAndView("redirect:/");
			}
		} catch (Exception e) {
			e.printStackTrace();
			mav = new ModelAndView("redirect:/");
		}
		return mav;
	}

	@RequestMapping(value = "/edit/{courseId}", method = RequestMethod.POST)
	public ModelAndView editCourse(@ModelAttribute @Valid Course course, BindingResult result,
			@PathVariable String courseId, final RedirectAttributes redirectAttributes, HttpSession session)
			throws CourseNotFound {
		UserSession us = (UserSession) session.getAttribute("USERSESSION");
		ModelAndView mav = new ModelAndView();
		try {
			if (us != null) {

				System.out.println("course" + course.toString());
				if (result.hasErrors())
					return new ModelAndView("courseeditform");
				cService.updateCourse(course);
				mav = new ModelAndView("redirect:/course/list");
				String message = "Course" + course.getCourseId() + " was successfully updated.";
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

	@RequestMapping(value = "/deleteCourse/{courseId}", method = RequestMethod.GET)
	public ModelAndView deleteCourse(@PathVariable String courseId, final RedirectAttributes redirectAttributes,
			HttpSession session) throws CourseNotFound {
		UserSession us = (UserSession) session.getAttribute("USERSESSION");
		ModelAndView mav = new ModelAndView();
		try {
			if (us != null) {
				Course course = cService.findCourse(courseId);
				cService.removeCourse(course);
				mav = new ModelAndView("redirect:/course/list");
				String message = "The course " + course.getCourseId() + " was successfully deleted.";
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
