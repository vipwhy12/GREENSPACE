package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.Setter;

@Controller
@Setter
public class EcoMagazineController{
	
	@GetMapping("/ecoMagazine/ecoMagazineInfo")
	public void listInfo() {
		
	}
	
	
	
}