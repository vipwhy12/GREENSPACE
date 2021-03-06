<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="/css/style.css">
<title>녹지공간 - 장바구니</title>
<script  type="text/javascript" src="https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script src="https://kit.fontawesome.com/5b334c6c49.js" crossorigin="anonymous"></script>
<script type="text/javascript" src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script type="text/javascript" src="/js/qty.js"></script>
<script type="text/javascript" src="/js/product.js"></script>
<script type="text/javascript" src="/js/checkbox.js"></script>
<script type="text/javascript" src="/js/address.js"></script>
<script type="text/javascript">

	$(function(){
		//***** 체크박스에 대한 변수 선언
		let checkbox = "input[name=checkList]";
		let allCheck = "#checkedAll";
		
		//***** 체크박스 클릭시 실행되는 이벤트함수
		$(document).on("click","#checkedAll",function() {
			checkedAll();
			
			showOrderPriceInfo();
		});
		
		$(document).on("click", "#listCart "+ checkbox, function() {
			$(allCheck).prop("checked", false)
			showOrderPriceInfo();
		}); 
		
		
		//*****  수량 버튼 기능
		$(document).on("click", "#minus", function() {
			
			let qty = $(this).siblings();
			let cart_qty = Number( $( qty ).val() ) - 1;
			
			let td = $( $(this).parent() ).siblings()[1];
			let no =$( $(td).find( "input[name=pro_no]" ) ).val();
			
			updateQty(cart_qty,no);
			
		}); // end minus
		
		$(document).on("click", "#plus", function() {
			let qty = $(this).siblings()[1];
			let cart_qty = Number( $( qty ).val() )+1;
			
			let td = $( $(this).parent() ).siblings()[1];
			let no =$( $(td).find( "input[name=pro_no]" ) ).val();
			
			updateQty(cart_qty,no);
		}); // end plus
		

		//장바구니 상품 삭제		
		$(document).on("click", "#delete", function() {
			let noArr = new Array();
			noArr.push( $(this).val() );
			deleteCart(noArr);
		});
		
		
		//***** 선택 상품 삭제
		$("#delSelected").click(function(){
			let select = $("input[name=checkList]:checked");
			let noArr = new Array();
			
			$.each(select, function() {
				let no = $($(this).siblings()[2]).val();
				noArr.push(no);
			});
			
			deleteCart(noArr);
		});
		
		//배송정보 관련 이벤트
		$("#newAddr").click(function(){
			chooseNewAddr();
		});
		
		$("#basicAddr").click(function(){
			chooseBasicAddr();
			
			$("input[name=addr_no]").val(${info.addr_no });
			$("input[name=name]").val("${info.name }");
			$("input[name=phone]").val("${info.phone }");
			$("input[name=addr_postal]").val(${info.addr_postal });
			$("input[name=addr_road]").val("${info.addr_road }");
			$("input[name=addr_detail]").val("${info.addr_detail }");
			$("input[name=addr_msg]").val("${info.addr_msg }");
		});
		
		
		//***** 선택 상품 주문하기
		$("#order").click(function(){
			let select = $("input[name=checkList]:checked");
			let noArr = new Array();
			
			$.each(select, function() {
				let no = $($(this).siblings()[2]).val();
				noArr.push(no);
			});
		
			let receiverInfo = new Array();
			let arr_receiver = document.getElementsByClassName("receiverInfo");
			let addr_type = $("#receiverNo").attr("name");
			
			for( let i = 0; i < arr_receiver.length; i++){
				receiverInfo.push( $(arr_receiver[i]).val());
			} 
			
			receiverInfo.push( addr_type);
			let orderInfo = new Array();
			let arr = document.getElementsByClassName("orderInfo");
			
			for( let i = 0; i < arr.length; i++){
				orderInfo.push( $(arr[i]).text() );
			} 

			$.ajax({
				url: "order_form",
				type: "post",
				data: {
					proInfo:noArr,
					receiverInfo:receiverInfo,
					orderInfo:orderInfo
				},
				success: function(){
					location.href="/shop/order_form"
				} 
			});
		});
		
		
		//***** 서 	버동작시 실행
		checkedAll();
	})//end function
</script>
</head>
<body>
	<div id="root">
		<jsp:include page="../header.jsp"></jsp:include>
		<div class="cart-header	">
			<div class="cart-title">
				<i class="fa-solid fa-cart-shopping fa-2x"></i>
				<h2>장바구니 <span class="cart-cnt">${cnt }</span></h2>
			</div>
		</div>
		<div class="section mypage">
			<div id="cart_products">
				<div class="cart_btns">
					<button id="soldOut">품절 모두삭제</button>
					<button id="delSelected">선택 삭제</button>
				</div>
				<form action="insertOrder" >
					<table class="product_table">
						<thead>
							<tr>
								<th>전체 ${cnt } 개</th>
								<th style="width:2%"><input type="checkbox" id="checkedAll" checked="checked"></th>
								<th>상품명(옵션)</th>
								<th>가격</th>
								<th>수량</th>
								<th>주문관리</th>
							</tr>
						</thead>
						<tbody id="listCart">
							<c:forEach var="c" items="${list}">
								<tr>
									<td>
										${c.rownum }
									</td>
									<td id="values">
										<input type="checkbox" name="checkList"">
										<input type="hidden" name="price" value="${c.price }">
										<input type="hidden" name="saleprice" value="${c.saleprice }">
										<input type="hidden" name="pro_no" value="${c.no }">
										<input type="hidden" name="discount" value="${c.price - c.saleprice}">
										<input type="hidden" name="qty" value="${c.qty}">
									</td>
									<td>
										<div>
											<img src="/upload/${c.img }">
										</div>
										<div>
											<p>${c.pro_name }</p>
											<c:if test="${c.pro_option != null}">
												<p>옵션: ${c.pro_option }/${c.option_detail}</p>
											</c:if>
										</div>
									</td>
									<td>
										<span id="price">${c.price }</span>
										<span id="saleprice">${c.saleprice }</span>
									</td>
									<td id="addSub">
										<c:if test="${c.qty == 1 }">
											<button type="button" disabled="disabled" id="minus">-</button>
										</c:if>
										<c:if test="${c.qty != 1 }">
											<button type="button" id="minus">-</button>
										</c:if>
										<input type="number" readonly="readonly" id="qty" value="${c.qty }">
										<button type="button" id="plus">+</button>
									</td>
									<td>
										 <button type="button"  id="delete" value="${c.no }">삭제하기</button>
									</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</form>
			</div>
			<div id="orderList">
				<div id="receiver">
					<h5>배송지</h5>
					<div class="receiver-radio">
						<input type="radio" name="address"  id="basicAddr" checked="checked">
						<label for="basicAddr">기본 배송지</label>
						<button type="button" id="changeAddr">주소록</button>
						<input type="radio" name="address"  id="newAddr">
						<label for="newAddr">신규 배송지</label>
					</div>
					<div id="receiverInfo">
						<input type="hidden" id="receiverNo" class="receiverInfo" name="addr_no" value="${info.addr_no }">
						<input type="text" readonly="readonly" class="receiverInfo" name="name" value="${info.name }" placeholder="수령인">
						<input type="text" readonly="readonly" class="receiverInfo" name="phone" value="${info.phone }" placeholder="연락처">
						<div class="address_search">
							<input type="text" readonly="readonly" style="margin:0" class="receiverInfo" name="addr_postal" value="${info.addr_postal }" placeholder="우편번호">
							<input type="hidden" name="kakao-search"  value="찾기" onclick="kakaopost()">
						</div>
						<input type="text" readonly="readonly"   class="receiverInfo" name="addr_road" value="${info.addr_road }"placeholder="주소">
						<input type="text" readonly="readonly"class="receiverInfo"  name="addr_detail" value="${info.addr_detail }" placeholder="상세주소">
						<input type="hidden" name="addr_msg" class="receiverInfo" value="${info.addr_msg }">
					</div>
				</div>
				<div id="orderInfo">
					<h5>결제 정보</h5>
					<div class="orderInfo-price">
						<p>상품금액  <span id="tot_price" class="orderInfo">${info.pro_price }</span> </p>
						<p>상품할인금액  <span id="tot_discount" class="orderInfo">${info.pro_discount }</span> </p>
						<p>배송비  <span id="delivery_price" class="orderInfo">${info.delivery_price}</span>  </p>
						<p>결제예정금액  <span id="tot_saleprice" class="orderInfo">${info.pro_saleprice + info.delivery_price}</span>  </p>
					</div>
				</div>
				<div>
					<button id="order">주문하기</button>
				</div>
			</div>
		</div>
	</div>
</body>
</html>