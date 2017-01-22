package com.noboll.plugin.file.controller;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@RequestMapping("/progress")
@SessionAttributes("status")
public class ProgressController {
	
	@RequestMapping(value = "/getProgress" )
	@ResponseBody
	public Object getProgress(Map<String, Object> model) {
		return model.get("status");
	}
}
