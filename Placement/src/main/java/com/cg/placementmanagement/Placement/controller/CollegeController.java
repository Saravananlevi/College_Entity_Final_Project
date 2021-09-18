package com.cg.placementmanagement.Placement.controller;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.cg.placementmanagement.Placement.domain.College;
import com.cg.placementmanagement.Placement.service.CollegeService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

@Controller
public class CollegeController {
	
	 @Autowired
	    private CollegeService service;

	    @GetMapping("/")
	    public String viewHomePage(Model model) {
	        List<College> listcollege = service.listAll();
	        model.addAttribute("listcollege", listcollege);
	        System.out.print("Get / ");	
	        return "index";
	    }

	    @GetMapping("/new")
	    public String add(Model model) {
	        model.addAttribute("college", new College());
	        return "new";
	    }

	    @RequestMapping(value = "/save", method = RequestMethod.POST)
	    public String saveCollege(@ModelAttribute("college") College std) {
	        service.save(std);
	        return "redirect:/";
	    }

	    @RequestMapping("/edit/{id}")
	    public ModelAndView showEditCollegePage(@PathVariable(name = "id") int id) {
	        ModelAndView mav = new ModelAndView("new");
	        College std = service.get(id);
	        mav.addObject("college", std);
	        return mav;
	        
	    }
	    @RequestMapping("/delete/{id}")
	    public String deleteCollege(@PathVariable(name = "id") int id) {
	        service.delete(id);
	        return "redirect:/";
	    }
	

}
