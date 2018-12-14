package com.nus.iss.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
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

import com.nus.iss.model.Course;
import com.nus.iss.model.StdCourse;
import com.nus.iss.model.Student;
import com.nus.iss.service.CourseService;
import com.nus.iss.service.StdCourseService;
import com.nus.iss.service.StudentService;

@RequestMapping(value = "/course")
@Controller

public class CourseController {

	@Autowired
	private CourseService cService;

	@Autowired
	private StdCourseService scService;

	@Autowired
	private StudentService sService;

	@InitBinder("course")
	private void initCourseBinder(WebDataBinder binder) {

	}

	@RequestMapping(value = "/courselist", method = RequestMethod.GET)
	public ModelAndView findAllCourses(RedirectAttributes redirectAttributes, HttpSession session, Integer page) {

		UserSession us = (UserSession) session.getAttribute("USERSESSION");
		ModelAndView mav = new ModelAndView();
		PagedListHolder<Course> pagedListHolder;

		try {

			mav = new ModelAndView("enrollcourse");
			ArrayList<Course> courses = cService.findAllCourses();
			ArrayList<StdCourse> stdCourses = scService.listStudentCourse(us.getStudent().getId());
			if (stdCourses.isEmpty()) {
				mav.addObject("courses", courses);
				pagedListHolder = new PagedListHolder<>(courses);
				pagedListHolder.setPageSize(3);

				mav.addObject("maxPages", pagedListHolder.getPageCount());

				if (page == null || page < 1 || page > pagedListHolder.getPageCount())
					page = 1;

				mav.addObject("page", page);
				if (page == null || page < 1 || page > pagedListHolder.getPageCount()) {
					pagedListHolder.setPage(0);
					mav.addObject("courses", pagedListHolder.getPageList());
				} else if (page <= pagedListHolder.getPageCount()) {
					pagedListHolder.setPage(page - 1);
					mav.addObject("courses", pagedListHolder.getPageList());
				}

			} else {

				ArrayList<Course> courseCompare = new ArrayList<Course>(courses);

				ArrayList<Course> studentCourseCompare = new ArrayList<Course>();
				for (StdCourse y : stdCourses) {
					studentCourseCompare.add(y.getCourse());
				}

				courseCompare.removeAll(studentCourseCompare);
//				mav.addObject("courses", courseCompare);
				pagedListHolder = new PagedListHolder<>(courseCompare);
				pagedListHolder.setPageSize(3);

				mav.addObject("maxPages", pagedListHolder.getPageCount());

				if (page == null || page < 1 || page > pagedListHolder.getPageCount())
					page = 1;

				mav.addObject("page", page);
				if (page == null || page < 1 || page > pagedListHolder.getPageCount()) {
					pagedListHolder.setPage(0);
					mav.addObject("courses", pagedListHolder.getPageList());
				} else if (page <= pagedListHolder.getPageCount()) {
					pagedListHolder.setPage(page - 1);
					mav.addObject("courses", pagedListHolder.getPageList());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			mav = new ModelAndView("redirect:/");
		}

		return mav;
	}

	@RequestMapping(value = "/grade", method = RequestMethod.GET)
	public ModelAndView viewGrades(HttpSession session) {
		ModelAndView mav = new ModelAndView("grade");
		UserSession us = (UserSession) session.getAttribute("USERSESSION");
		ArrayList<StdCourse> stdCourses = scService.listStudentCourse(us.getStudent().getId());
		mav.addObject("stdCourses", stdCourses);
		return mav;
	}

	@RequestMapping(value = "/viewcourse", method = RequestMethod.GET)
	public ModelAndView findAddedCourse(HttpSession session, RedirectAttributes redirectAttributes) {
		ModelAndView mav = new ModelAndView("viewcourses");
		UserSession us = (UserSession) session.getAttribute("USERSESSION");
		try {
			ArrayList<StdCourse> stdCourses = scService.listStudentCourse(us.getStudent().getId());
			mav.addObject("stdCourses", stdCourses);
		} catch (Exception e) {
			e.printStackTrace();
			mav = new ModelAndView("redirect:/");
		}

		return mav;
	}

	@RequestMapping(value = "/add/{courseId}", method = RequestMethod.GET)
	public ModelAndView addCourse(@PathVariable String courseId, @ModelAttribute Course course, BindingResult result,
			final RedirectAttributes redirectAttributes, HttpSession session) {

		/* courseId=request.getParameter("mySubmitButton"); */
		course = cService.findById(courseId);
		UserSession us = (UserSession) session.getAttribute("USERSESSION");
		ModelAndView mav = new ModelAndView();
		try {
			Student student = sService.findStudentById(us.getStudent().getId());
			StdCourse stdCourse = new StdCourse(null, null, "in-progress", student, course);

			// add std course into db
			StdCourse sc1 = scService.addCourse(stdCourse);

			// fetch all std course results
			ArrayList<StdCourse> scList = scService.listStudentCourse(student.getId());

			mav = new ModelAndView("viewcourses");
			mav.addObject("stdCourses", scList);
		} catch (Exception e) {
			e.printStackTrace();
			mav = new ModelAndView("redirect:/");
		}

		return mav;
	}

	@RequestMapping(value = "/delete/{StdCourseId}", method = RequestMethod.GET)
	public ModelAndView deleteAddedCourse(@PathVariable int StdCourseId, HttpSession session,
			final RedirectAttributes redirectAttributes) {

		UserSession us = (UserSession) session.getAttribute("USERSESSION");
		ModelAndView mav = new ModelAndView();
		try {
			if (!(us.getStudent().getId() == 0)) {
				StdCourse stdCourse = scService.findStdCourse(StdCourseId);
				scService.removeStdCourse(stdCourse);
				mav = new ModelAndView("redirect:/course/viewcourse");
				String message = "The course was successfully deleted.";
				redirectAttributes.addFlashAttribute("message", message);
			}
		}

		catch (Exception e) {
			e.printStackTrace();
			mav = new ModelAndView("redirect:/");
		}

		return mav;
	}

}
