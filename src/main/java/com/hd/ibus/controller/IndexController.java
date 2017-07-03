package com.hd.ibus.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("index")
public class IndexController {

	@RequestMapping("index_one")
	public String toindexone(HttpServletRequest request,Model model){
		System.out.println("1");
		return "index/index_one";
	}

	@RequestMapping("index_two")
	public String toindextwo(HttpServletRequest request,Model model){
		System.out.println("2");
		return "index/index_two";
	}

	@RequestMapping("index_three")
	public String toindexthree(HttpServletRequest request,Model model){
		System.out.println("3");
		return "index/index_three";
	}

	@RequestMapping("index_four")
	public String toindexfour(HttpServletRequest request,Model model){
		System.out.println("4");
		return "index/index_four";
	}

	@RequestMapping("index_five")
	public String toindexfive(HttpServletRequest request,Model model){
		System.out.println("5");
		return "index/index_five";
	}

}
