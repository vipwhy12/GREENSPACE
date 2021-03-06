package com.example.demo.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.dao.AddressDAO;
import com.example.demo.dao.CartDAO;
import com.example.demo.dao.MemberDAO;
import com.example.demo.dao.MyWishDAO;
import com.example.demo.dao.OrderListDAO;
import com.example.demo.vo.MemberVO;
import com.example.demo.vo.MyWishVO;
import com.example.demo.vo.OrderListVO;

import lombok.Setter;

@Controller
@Setter
public class MyWishController {
	
	@Autowired
	private MyWishDAO dao_mywish;
	
	@Autowired
	private AddressDAO dao_address;

	@Autowired
	private OrderListDAO dao_orderList;
	
	@Autowired
	private MemberDAO dao_member;
	
	@Autowired
	private CartDAO dao_cart;
	
	@RequestMapping("/shop/cart")
	public void myWishList(HttpSession session,Model model) {
		MemberVO m = (MemberVO)session.getAttribute("m");
		int member_no = m.getNo();
		
		List<MyWishVO> list = dao_mywish.findByMember(member_no);
		OrderListVO info = dao_orderList.initOrderInfo(member_no);
		
		if(info.getPro_saleprice() < 50000) {
			info.setDelivery_price(2500);
		}else {
			info.setDelivery_price(0);
		}
		
		model.addAttribute("list", list);
		model.addAttribute("cnt", dao_mywish.cntOfCart(member_no));
		model.addAttribute("info", info);
	}
	
	@RequestMapping(value = "/shop/order_form", method = RequestMethod.POST)
	public void getProInfoForOrder(HttpSession session,
			@RequestParam(value="proInfo[]") List<String> proInfo,
			@RequestParam(value="receiverInfo[]") List<String> receiverInfo,
			@RequestParam(value="orderInfo[]") List<String> orderInfo
	) {
		MemberVO m = (MemberVO)session.getAttribute("m");
		
		HashMap map = new HashMap();
		
		
		map.put("member_no", m.getNo());
		
		int rownum = 0;
		List<MyWishVO> list = new ArrayList<MyWishVO>();
		
		for(String pro_no : proInfo) { 
			int no = (Integer.parseInt(pro_no)); 
			rownum += 1;
			map.put("no", no);
			MyWishVO mw = dao_mywish.getProInfoForOrder(map);
			mw.setNo(no);
			mw.setRownum(rownum);
			list.add(mw);
		}
		MemberVO mb = dao_member.getMemberInfo(m.getNo());

		session.setAttribute("rownum", rownum);
		session.setAttribute("list", list);
		session.setAttribute("receiverInfo", receiverInfo);
		session.setAttribute("orderInfo", orderInfo);
		session.setAttribute("point", mb.getPoint_use());
		session.setAttribute("mainAddr", dao_address.getMainAddress(m.getNo()));
	}
	
	@RequestMapping(value = "/shop/order_form", method = RequestMethod.GET)
	public void getProInfoForOrder() {
	}

}
