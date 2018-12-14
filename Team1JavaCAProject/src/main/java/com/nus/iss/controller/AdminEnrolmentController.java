package com.nus.iss.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.nus.iss.model.StdCourse;
import com.nus.iss.service.AdminEnrolmentService;

@RequestMapping(value = "/enrolment")
@Controller
public class AdminEnrolmentController {

	@Autowired
	AdminEnrolmentService sService;

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView listAll(HttpSession session) {
		UserSession us = (UserSession) session.getAttribute("USERSESSION");
		ModelAndView mav = new ModelAndView();
		try {
			if (us != null) {
				mav = new ModelAndView("manageenrolments");
				ArrayList<StdCourse> stdcourses = sService.findAllApply();
				mav.addObject("stdcourses", stdcourses);
			} else {

				mav = new ModelAndView("redirect:/");
			}
		} catch (Exception e) {
			e.printStackTrace();
			mav = new ModelAndView("redirect:/");
		}
		return mav;
	}

	@RequestMapping(value = "/approve/{StdCourseId}", method = RequestMethod.GET)
	public ModelAndView ApproveController(@PathVariable("StdCourseId") Integer StdCourseId, HttpSession session) {

		UserSession us = (UserSession) session.getAttribute("USERSESSION");
		ModelAndView mav = new ModelAndView();
		try {
			if (us != null) {
				// process approve
				sService.enrolmentApprove(StdCourseId);

				// redirect to list page
				mav = new ModelAndView("redirect:/enrolment/list");
			} else {

				mav = new ModelAndView("redirect:/");
			}
		} catch (Exception e) {
			e.printStackTrace();
			mav = new ModelAndView("redirect:/");
		}
		return mav;
	}

	@RequestMapping(value = "/reject/{StdCourseId}", method = RequestMethod.GET)
	public ModelAndView RejectController(@PathVariable("StdCourseId") Integer StdCourseId, HttpSession session) {

		UserSession us = (UserSession) session.getAttribute("USERSESSION");
		ModelAndView mav = new ModelAndView();
		try {
			if (us != null) {
				// process reject
				sService.enrolmentReject(StdCourseId);

				// redirect to list page
				mav = new ModelAndView("redirect:/enrolment/list");
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