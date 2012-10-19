package com.mincoms.book.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

@Controller("testInterceptor")
public class TestInterceptor  extends HandlerInterceptorAdapter {
	 
	 
	 public boolean preHandle(HttpServletRequest request, HttpServletResponse response, ModelAndView mav) {
	  
	/*  System.out.println("testInterceptor!!");
	  System.out.println("testInterceptor!!");
	  System.out.println("testInterceptor!!");
	  System.out.println("testInterceptor!!");
	  System.out.println("testInterceptor!!");
	  System.out.println("testInterceptor!!");
	  System.out.println("testInterceptor!!");
	  
	  mav.addObject("msg", "1232355345");*/
	  
	  return true;
	  
	 }
	 
	 public void postHandle(HttpServletRequest request, HttpServletResponse response, 
	   Object handler, ModelAndView mav) {
	  
	/*  System.out.println("postHandle testInterceptor!!");
	  System.out.println("postHandle testInterceptor!!");
	  System.out.println("postHandle testInterceptor!!");
	  System.out.println("postHandle testInterceptor!!");
	  System.out.println("postHandle testInterceptor!!");
	  System.out.println("postHandle testInterceptor!!");
	  System.out.println("postHandle testInterceptor!!");
	  System.out.println("postHandle testInterceptor!!");
	  System.out.println("postHandle testInterceptor!!");
	  System.out.println("postHandle testInterceptor!!");
	  
	  mav.addObject("msg", "sdsddsdd");*/
	  
	 }
	}
	