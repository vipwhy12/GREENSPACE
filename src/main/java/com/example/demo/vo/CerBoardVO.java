package com.example.demo.vo;

import java.util.Date;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class CerBoardVO {
	private int no;
	private String cer_status;
	private String cer_content;
	private Date cer_date;
	private int cer_like;
	private int cer_hit;
	private int member_no;
	private int chg_user_no;
	private String nickname;
	private String img;
	
	private String cer_thumbnail;
	private MultipartFile uploadFile;
	
}