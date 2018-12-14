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

import com.nus.iss.model.LecturerCourse;
import com.nus.iss.model.StdCourse;
import com.nus.iss.service.LecturerService;
import com.nus.iss.validator.GradeValidator;

@Controller
@RequestMapping(value = "/lecturer")
public class LecturerController {

	@Autowired
	private LecturerService lService;

	@Autowired
	private GradeValidator gValidator;

	@InitBinder("stdCourse")
	private void initBinder(WebDataBinder binder) {
		// binder.setValidator(studentValidator);
		binder.addValidators(gValidator);

	}

	@RequestMapping(value = "/course", method = RequestMethod.GET)
	public ModelAndView listLecturerCoursespage(HttpSession session) {
		ModelAndView mav = new ModelAndView("lecturercourses");
		UserSession us = (UserSession) session.getAttribute("USERSESSION");
		ArrayList<LecturerCourse> lCCourse = lService.findAllCoursesByLID(us.getLecturer().getLect_id_pk());
		// ArrayList<Course> course = cService.findAllCourses();
		mav.addObject("lCCourse", lCCourse);

		System.out.println(lCCourse);
		return mav;
	}

	@RequestMapping(value = "/viewstudent/{courseid}", method = RequestMethod.GET)
	public ModelAndView listCourseStudentspage(@PathVariable("courseid") String courseid, HttpSession session,
			RedirectAttributes attributes) {
		UserSession us = (UserSession) session.getAttribute("USERSESSION");
		ModelAndView mav = new ModelAndView();
		try {
			if (us != null) {

				mav = new ModelAndView("coursestudents");
				ArrayList<StdCourse> sCStudent = lService.findAllStudentsByCID(courseid);
				// ArrayList<Course> course = cService.findAllCourses();
				mav.addObject("sCStudent", sCStudent);
				System.out.println(sCStudent);

			} else {

				mav = new ModelAndView("redirect:/");
			}
		} catch (Exception e) {
			e.printStackTrace();
			mav = new ModelAndView("redirect:/");
		}

		return mav;
	}

	@RequestMapping(value = "/updategrade/{id}", method = RequestMethod.GET)

	public ModelAndView editCourseStudentPage(@PathVariable Integer id, HttpSession session,
			RedirectAttributes attributes) {

		UserSession us = (UserSession) session.getAttribute("USERSESSION");
		ModelAndView mav = new ModelAndView();
		try {
			if (us != null) {
				mav = new ModelAndView("editgrade");
				mav.addObject("stdCourse", lService.findStdCourse(id));
			} else {
				mav = new ModelAndView("redirect:/");
			}

		} catch (Exception e) {
			e.printStackTrace();
			mav = new ModelAndView("redirect:/");
		}

		return mav;

	}

	//
	@RequestMapping(value = "/updategrade/{id}", method = RequestMethod.POST)

	public ModelAndView editCourseStudent(@ModelAttribute @Valid StdCourse stdCourse, BindingResult result,
			@PathVariable Integer id,

			final RedirectAttributes redirectAttributes) {

		System.out.println("stdcourse" + stdCourse.toString());

		String cid = stdCourse.getCourse().getCourseId();

		if (result.hasErrors())

			return new ModelAndView("editgrade");

		double gpa = lService.getGPA(Integer.parseInt(stdCourse.getGrade()));
		stdCourse.setGpa(gpa);
		String grade = lService.getGrade(gpa);
		stdCourse.setGrade(grade);

		lService.updategrade(stdCourse);

		ModelAndView mav = new ModelAndView("redirect:/lecturer/viewstudent/" + cid);

		String message = "Student " + stdCourse.getStudent().getName() + "'s grade was successfully updated.";

		redirectAttributes.addFlashAttribute("message", message);

		return mav;

	}

}
