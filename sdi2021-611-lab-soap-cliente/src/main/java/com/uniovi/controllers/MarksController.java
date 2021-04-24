package com.uniovi.controllers;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.uniovi.services.*;
import com.uniovi.wsdl.*;

@Controller
public class MarksController {
	
	@Autowired
	private MarksSoapService marksSoapService;

	@RequestMapping("/marks/list")
	public String getMarks(Model model, @RequestParam String dni) {
		List<Mark> marks = new ArrayList<Mark>();
		User user = marksSoapService.getMarks(dni).getUser();
		if (user != null) {
			marks = user.getMark();
		}
		model.addAttribute("dni", dni);
		model.addAttribute("markList", marks);
		return "marks/list";
	}
}