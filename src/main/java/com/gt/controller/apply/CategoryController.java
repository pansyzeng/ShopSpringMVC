package com.gt.controller.apply;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.gt.controller.base.BaseController;
import com.gt.model.Account;
import com.gt.model.Category;
import com.gt.model.Page;
import com.gt.services.base.AccountServiceI;
import com.gt.services.base.CategoryServiceI;
import com.sun.tools.internal.ws.processor.model.Model;


@Controller

public class CategoryController extends BaseController{
	@Autowired
	 private CategoryServiceI categoryService;
	 @RequestMapping(value = "categoryquery")
     public String test(Page page){
		 System.out.println(categoryService.test());
    	 return "category/query";
     }
	 @RequestMapping(value = "categoryQueryAll")
	 @ResponseBody
     public Map<String, Object> queryCategory(Page page){
		 System.out.println("page"+page.getPage());
		 System.out.println("rows"+page.getRows());
		 System.out.println("rows"+page.getKeyword());
		 int total = categoryService.countTypeAll("%"+page.getKeyword()+"%");
	     map = new HashMap<String,Object>();
	     Map<String , Object> mapquery = new HashMap<String,Object>();
	     mapquery.put("type", "%"+page.getKeyword()+"%");
	     mapquery.put("page", (page.getPage()-1)*page.getRows());
	     mapquery.put("size", page.getRows());
	     List<Category> list = categoryService.queryCategoryAll(mapquery);
	     map.put("total", total);
	     map.put("rows", list);
    	 return map;
     }

}