<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="/css/style.css">
<title>녹지몰-위시리스트</title>
<script src="https://kit.fontawesome.com/5b334c6c49.js" crossorigin="anonymous"></script>
<script type="text/javascript" src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script type="text/javascript" src="/js/product.js"></script>
<script type="text/javascript" src="/js/checkbox.js"></script>
<script type="text/javascript">
	$(function() {
		//***** 체크박스에 대한 변수 선언
		let checkbox = "input[name=checkList]";
		let allCheck = "#checkedAll";
		
		$("a").click(function(event){
			event.preventDefault();
		});
		
		//***** 체크박스 클릭시 실행되는 이벤트함수
		$(document).on("click","#checkedAll",function() {
			checkedAll();
		});
		
		$(document).on("click", "input[name=checkList]"+ checkbox, function() {
			$(allCheck).prop("checked", false)
		}); 
		
		checkedAll();
		
		//***** 선택 상품 삭제
		$("#delSelected").click(function(){
			let select = $("input[name=checkList]:checked");
			let noArr = new Array();
			
			$.each(select, function() {
				let no = $($(this).siblings()[0]).val();
				noArr.push(no);
			});
			deleteWishList(noArr);
		});
		
		$("#isDelete").click(function(){
			let wishListNo = $(this).val();
			let noArr = [wishListNo];
			deleteWishList(noArr);
		});
	})
	
	

</script>
</head>
<body>
	<div id="root">
		<jsp:include page="../header.jsp"></jsp:include>
		<div class="section">
			<div class="cart-header	">
				<div class="cart-title">
					<i class="fa-solid fa-heart fa-2x"  style="color:red;"></i>
					<h2>위시 리스트 <span class="cart-cnt cnt_position">${cnt }</span></h2>
				</div>
			</div>
				<div class="cart_btns">
			<ul>
				<li>
					<input type="checkbox" id="checkedAll" checked="checked">
					<label for="checkedAll">전체선택</label>
				</li>
				<li >
					<a id="soldOut" href="#">품절모두삭제</a>
				</li>
				<li >
					<a id="delSelected" href="#">선택삭제</a>
				</li>
			</ul>
			</div>
			<hr>
			<div>
				<c:forEach var="c" items="${list }">
					<div>
						<div>
							<input type="checkbox" name="checkList">
							<input type="hidden" name="wishlist_no" value="${c.no }">
							<input type="hidden" name="pro_add_option_no" value="${c.pro_add_option_no }">
							<input type="hidden" name="pro_no" value="${c.pro_no }">
							<img src="/upload/${c.pro_thumbnail }">	
						</div>
						<div>
							<h4>${c.pro_brand }</h4>
							<h4>${c.pro_name }</h4>
							<p>
								<i class="fa-solid fa-heart-pulse"></i>
								${c.cnt }
							</p>
							<c:if test="${c.pro_option != null}">
								<h5>옵션: (${c.pro_option }) ${c.option_detail }</h5>
							</c:if>
							<p>
								<span>${c.price + c.pro_add_price }</span>
								<span>${c.saleprice + c.pro_add_price }</span>
								<span>(${Math.round((1 - c.saleprice / c.price) * 100 *100) /100 } %)</span>
							</p>
						</div>
						<div>
							<input type="button" value="장바구니 담기">
							<button type="button" id="isDelete" value="${c.no }">삭제</button>
						</div>
					</div>
				</c:forEach>
			</div>
		</div>
	</div>
</body>
</html>