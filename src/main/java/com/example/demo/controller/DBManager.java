package com.example.demo.controller;
import java.io.Reader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.ui.Model;

import com.example.demo.dao.ChallengeListDAO;
import com.example.demo.vo.AddressVO;
import com.example.demo.vo.CartVO;
import com.example.demo.vo.CerBoardVO;
import com.example.demo.vo.ChallengeListVO;
import com.example.demo.vo.ChallengeUserVO;
import com.example.demo.vo.ChallengeVO;
import com.example.demo.vo.CommunityVO;
import com.example.demo.vo.EasyToStartVO;
import com.example.demo.vo.ImgVO;
import com.example.demo.vo.MemberVO;
import com.example.demo.vo.MyReviewVO;
import com.example.demo.vo.MyWishVO;
import com.example.demo.vo.NoticeVO;
import com.example.demo.vo.OrderBillVO;
import com.example.demo.vo.Pro_add_optionVO;
import com.example.demo.vo.OrderListVO;
import com.example.demo.vo.ProQnaVO;
import com.example.demo.vo.OrdersProductVO;
import com.example.demo.vo.OrdersVO;
import com.example.demo.vo.PointVO;
import com.example.demo.vo.ProductVO;
import com.example.demo.vo.QnaVO;
import com.example.demo.vo.ReceiverVO;
import com.example.demo.vo.ReviewVO;
import com.example.demo.vo.WishListVO;
 
public class DBManager {
	private static SqlSessionFactory factory;
	
	static {
		try {
			Reader reader = Resources.getResourceAsReader("com/example/demo/db/sqlMapConfig.xml");
			factory = new SqlSessionFactoryBuilder().build(reader);
			reader.close();
		}catch (Exception e) {
			System.out.println("μμΈλ°μ:"+e.getMessage());
		}
	}
	
	
			
	//-----------------------ReviewVO---------------------------
	
	public static List<ReviewVO> findAllReview(){
		SqlSession session = factory.openSession();
		List<ReviewVO> list = session.selectList("review.findAll");
		session.close();
		return list;
	}
	
	//μνλ²νΈ λ¦¬λ·°λͺ©λ‘μ κ°κ³  μ€λ sql
		public static List<ReviewVO> findAllReviewRate(int pro_no){
			SqlSession session = factory.openSession();
			List<ReviewVO> list = session.selectList("review.findAllRate", pro_no);
			session.close();
			return list;
		}
	
	public static ReviewVO findAllReviewDetail(int no) {
		SqlSession session = factory.openSession();
		ReviewVO r = session.selectOne("review.findAllDetail", no);
		session.close();
		return r;
	}
	
	public static int insertReview(ReviewVO r) {
		
		SqlSession session  = factory.openSession();
		int re=session.insert("review.insert",r);
		session.commit();
		session.close();
		return re;
	}
	
	
	public static int updateReview(ReviewVO r) {
		SqlSession session = factory.openSession(true);
		int re = session.update("review.update", r);
		session.commit();
		session.close();
		return re;				
	}	
	
	public static int deleteReview(int no) {
		SqlSession session  = factory.openSession();
		int re=session.delete("review.delete", no);
		session.commit();
		session.close();
		return re;
	}
	
	public static void updateReviewHit(int no) {
		System.out.println(no);
		SqlSession session = factory.openSession();
		session.update("review.updateHit", no);
		session.commit();
		session.close();
	}
	
	//--------------------ProQna-------------------------------
	
	public static int insertProQna(ProQnaVO p) {
		
		SqlSession session  = factory.openSession();
		int re=session.insert("proqna.insert",p);
		session.commit();
		session.close();
		return re;
	}
	
	public static int updateProQna(ProQnaVO p) {
		SqlSession session = factory.openSession(true);
		int re = session.update("proqna.update", p);
		session.commit();
		session.close();
		return re;				
	}	
	
	public static int deleteProQna(int no) {
		SqlSession session  = factory.openSession();
		int re=session.delete("proqna.delete", no);
		session.commit();
		session.close();
		return re;
	}
	
	
	
	
	//-----------------------QnaVO---------------------------
	
	public static List<QnaVO> findAllQna(HashMap map){
		SqlSession session = factory.openSession();
		List<QnaVO> list = session.selectList("qna.findAll",map);
		session.close();
		return list;
	}
	
	public static List<QnaVO> findAllQnaList(){
		SqlSession session = factory.openSession();
		List<QnaVO> list = session.selectList("qna.findAllList");
		session.close();
		return list;
	}
	   
	public static QnaVO findAllQnaDetail(int no) {
		SqlSession session = factory.openSession();
		QnaVO r = session.selectOne("qna.findAllDetail", no);
		session.close();
		return r;
	}
	
	public static List<QnaVO> findAllQnaSearch(HashMap map){
		//{start=1, end=5, searchColumn=QDE, qnaColumn=null, keyword=μμ}
		System.out.println("mapμ μ μ₯λ λ΄μ©"+map);
		SqlSession session = factory.openSession();
		List<QnaVO> list = session.selectList("qna.findAllSearch",map);
		session.close();
		return list;
	}
	
	public static int getTotalRecordQna(HashMap map) {
		SqlSession session = factory.openSession();
		int no = session.selectOne("qna.totalRecord", map);
		session.close();
		return no;
	}
	
	//-----------------------MyReviewVO---------------------------
		public static List<MyReviewVO> findAllMyReview(){
			SqlSession session = factory.openSession();
			List<MyReviewVO> list = session.selectList("myreview.findAll");
			session.close();
			return list;
		}
		
		public static List<MyReviewVO> findAllMyReviewRate(){
			SqlSession session = factory.openSession();
			List<MyReviewVO> list = session.selectList("myreview.findAllRate");
			session.close();
			return list;
		}
		
		
	
	
	
	//-----------------------MYWISH ( μ₯λ°κ΅¬λ, μμλ¦¬μ€νΈ)------------------------
	public static List<MyWishVO> findByMember(int member_no){
		SqlSession session = factory.openSession();
		List<MyWishVO> list = session.selectList("myWish.findByMember", member_no);
		session.close();
		return list;
	}
	public static MyWishVO getProInfoForOrder(HashMap map){
		SqlSession session = factory.openSession();
		MyWishVO list = session.selectOne("myWish.getProInfoForOrder", map);
		session.close();
		return list;
	}
	
	public static int cntOfCart(int member_no){
		SqlSession session = factory.openSession();
		int cnt = session.selectOne("myWish.cntOfCart", member_no);
			session.close();
			return cnt;
	}
	
	public static CartVO findByCartNo(HashMap map){
		SqlSession session = factory.openSession();
		CartVO c = session.selectOne("cart.findByCartNo", map);
		session.close();
		return c;
	}
	
	//-----------------------ProductVO---------------------------
	//κ΄λ¦¬μ μν λ¦¬μ€νΈ
	public static List<ProductVO> findAll(HashMap map){
		SqlSession session = factory.openSession();
		List<ProductVO> list= session.selectList("product.findAll",map);
		session.close();
		return list;
	}	
	
	
	public static int getTotalRecord(HashMap map) {
		SqlSession session = factory.openSession();
		int no = session.selectOne("product.totalRecord", map);
		session.close();
		return no;
	}
	
 
	public static List<ProductVO> findAll_home(HashMap map){
		SqlSession session = factory.openSession();
		List<ProductVO> list= session.selectList("product.findAll_home", map);
		session.close();
		return list;
	}	
	
	//μν μμΈ 
	public static ProductVO findByNo(int no) {
		SqlSession session = factory.openSession();
		ProductVO p = session.selectOne("product.findByNo", no);
		session.close();
		return p;		
	}
	
	
	public static List<ProductVO> findOption(int no){
		SqlSession session = factory.openSession();
		List<ProductVO> list= session.selectList("product.findOption",no);
		session.close();
		return list;
	}	
	
	public static int findOptionView(int no){
		SqlSession session = factory.openSession();
		int re= session.selectOne("product.findOptionView",no);
		session.close();
		return re;
	}	
 
	public static ProductVO findOptionName(int no){
		SqlSession session = factory.openSession();
		ProductVO list= session.selectOne("product.findOptionName",no);
		session.close();
		return list;
	}	
 	
 
	 public static List<ProductVO> findOptionDetailName(HashMap map){
		SqlSession session = factory.openSession();
		List<ProductVO> list= session.selectList("product.findOptionDetailName",map);
		session.close();
		return list;
	}
 	 
  
	public static List<ProductVO> findDBOption(){
		SqlSession session = factory.openSession();
		List<ProductVO> list= session.selectList("product.findDBOption");
		session.close();
		return list;
	}	
	
	public static List<ProductVO> findDBDetailOption(String pro_option_code){
		SqlSession session = factory.openSession();
		List<ProductVO> list= session.selectList("product.findDBDetailOption", pro_option_code);
		session.close();
		return list;
	}
	
	
	
	public static int update(ProductVO b) {
		SqlSession session = factory.openSession(true);
		int re = session.update("product.update", b);
		session.commit();
		session.close();
		return re;				
	}	
	public static int updateStockcuzBuy(HashMap map) {
		SqlSession session = factory.openSession(true);
		int re = session.update("product.updateStockcuzBuy", map);
		session.commit();
		session.close();
		return re;				
	}	
	
	public static int delete(int no) {
		SqlSession session  = factory.openSession();
		int re=session.delete("product.delete", no);
		session.commit();
		session.close();
		return re;
	}
	
	public static void updateHit(int no) {
		SqlSession session = factory.openSession();
		session.update("product.updateHit", no);
		session.commit();
		session.close();
	}
	
	public static int insert(ProductVO p) {		
		SqlSession session  = factory.openSession();
		int re=session.insert("product.insert",p);
		session.commit();
		session.close();
		return re;
	}
	
	
	
	
	
	//------------------------μν μμ²΄ μ΅μ(Pro_add_optionVO)--------------
	public static int deletePro_add_option(HashMap map) {
		SqlSession session = factory.openSession();
		int re=session.delete("pro_add_option.delete", map);
		session.commit();
		session.close();
		return re;
	}
	public static Integer findProAddPriceNo(HashMap map) {
		SqlSession session = factory.openSession();
		Integer re = session.selectOne("pro_add_option.findProAddPriceNo", map);
		if(re == null) {
			re = 0;
		}
		session.close();
		return re;
	}
	public static int findProAddOptionNoForWishList(HashMap map) {
		SqlSession session = factory.openSession();
		int no = session.selectOne("pro_add_option.findProAddOptionNoForWishList", map);
		session.close();
		return no;
	}
	
	
	public static int insertPro_add_option(Pro_add_optionVO po) {		
		SqlSession session  = factory.openSession();
		int re=session.insert("pro_add_option.insert",po);
		session.commit();
		session.close();
		return re;
	}

	public static List<Pro_add_optionVO> findOptionByProNo(int no) {	 
		SqlSession session = factory.openSession();
		List<Pro_add_optionVO> list= session.selectList("pro_add_option.findOptionByProNo", no);
		session.close();
		return list;
	}
 
	//------------------Address ( μ£Όμλ‘ )------------------------
 
	public static AddressVO getMainAddress(int member_no) {
		SqlSession session = factory.openSession();
		AddressVO main =  session.selectOne("address.getMainAddress", member_no);
		session.close();
		return main;
	}
	
	public static List<AddressVO> findAllAddress() {
		SqlSession session = factory.openSession();
		List<AddressVO> list =  session.selectList("address.findAll");
		session.close();
		return list;
	}
	
	public static AddressVO findAllAddressDetail(int no) {
		SqlSession session = factory.openSession();
		AddressVO a = session.selectOne("address.findAllDetail", no);
		session.close();
		return a;
	}
	
	public static AddressVO allMainAddress(int member_no) {
		SqlSession session = factory.openSession();
		AddressVO main =  session.selectOne("address.allMainAddress", member_no);
		session.close();
		return main;
	}
	
	public static AddressVO findAddressInfoByNo(int addr_no) {
		SqlSession session = factory.openSession();
		AddressVO a =  session.selectOne("address.findAddressInfoByNo", addr_no);
		session.close();
		return a;
	}
	
	public static List<AddressVO> allSubAddress(int member_no) {
		SqlSession session = factory.openSession();
		List<AddressVO> sub =  session.selectList("address.allSubAddress", member_no);
		session.close();
		return sub;
	}
	
    public static int insertAddress(AddressVO a) {
		
		SqlSession session  = factory.openSession();
		int re=session.insert("address.insert",a);
		session.commit();
		session.close();
		return re;
	}
	
	
	public static int updateAddress(AddressVO a) {
		SqlSession session = factory.openSession(true);
		int re = session.update("address.update", a);
		session.commit();
		session.close();
		return re;				
	}	
	
	
	public static int updateMainBtnAddress(int no) {
		SqlSession session = factory.openSession(true);
		int re = session.update("address.updatemainbtn", no);
		session.commit();
		session.close();
		return re;				
	}
	public static int updateSubBtnAddress(AddressVO a) {
		SqlSession session = factory.openSession(true);
		int re = session.update("address.updatesubbtn", a);
		session.commit();
		session.close();
		return re;				
	}
	
	//---------------------λ©μΈλ² μ‘μ§λ‘ λ³κ²½νλ©΄ λλ¨Έμ§ μλΈλ‘ 
	public static void updateBtnAddress(int member_no) {
		SqlSession session = factory.openSession();
		session.update("address.updatebtn", member_no);
		session.commit();
		session.close();
						
	}
	
	public static int deleteAddress(int no) {
		SqlSession session  = factory.openSession();
		int re=session.delete("address.delete", no);
		session.commit();
		session.close();
		return re;
	}
	
	
	//************ Cart ( μ₯λ°κ΅¬λ )
	public static int insertCart(CartVO c) {
		SqlSession session = factory.openSession();
		int re = session.insert("cart.insert", c);
		session.commit();
		session.close();
		return re;
	}
	
	public static int deleteCart(int no) {
		SqlSession session = factory.openSession();
		int re = session.delete("cart.delete",no);
		session.commit();
		session.close();
		return re;
	}
	
	public static int isCart(CartVO c) {
		SqlSession session = factory.openSession();
		int re= session.selectOne("cart.isCart", c);
		session.close();
		return re;
	}
	
	public static int updateQty(CartVO c) {
		SqlSession session = factory.openSession();
		int re = session.update("cart.updateQty", c);
		session.commit();
		session.close();
		return re;
	}
	
	//-----------------Member ( νμκ΄λ ¨ )------------------------
	
	public static int insertMember(MemberVO m) {
		SqlSession session = factory.openSession();
		int re = session.insert("member.insert",m);
		session.commit();
		session.close();
		return re;
	}
	
	public static MemberVO findById(String id) {
		SqlSession session = factory.openSession();
		MemberVO m = session.selectOne("member.findById", id);
		session.close();
		return m;
	}
	
	public static int checkId(String id) {
		SqlSession session = factory.openSession();
		int cnt = session.selectOne("member.checkId",id);
		session.close();
		return cnt;
	}
	
	public static int checkNickname(String nickname) {
		SqlSession session = factory.openSession();
		int cnt = session.selectOne("member.checkNickname",nickname);
		session.close();
		return cnt;
	}	
	
	public static int checkEmail(String email) {
		SqlSession session = factory.openSession();
		int cnt = session.selectOne("member.checkEmail",email);
		session.close();
		return cnt;
	}		
	
	public static String findIdByEmail(HashMap<String,String> m) { 
		SqlSession session = factory.openSession();
		String id = session.selectOne("member.findIdByEmail", m);
		session.close();
		return id;
	}
	
	public static String findIdByPhone(HashMap<String,String> m) { 
		SqlSession session = factory.openSession();
		String id = session.selectOne("member.findIdByPhone", m);
		session.close();
		return id;
	}
	public static int findPwdByEmail(HashMap<String,String> m) {
		SqlSession session = factory.openSession();
		int re = session.selectOne("member.findPwdByEmail", m);
		session.close();
		return re;
	}
	
	public static int findPwdByPhone(HashMap<String,String> m) {
		SqlSession session = factory.openSession();
		int re = session.selectOne("member.findPwdByPhone", m);
		session.close();
		return re;
	}
	

	
	public static MemberVO getMemberInfo(int member_no) {
		SqlSession session = factory.openSession();
		MemberVO m = session.selectOne("member.mainMemberInfo", member_no);
		session.close();
		return m;
	}
	public static MemberVO orderInfo(int member_no) {
		SqlSession session = factory.openSession();
		MemberVO m = session.selectOne("member.orderInfo", member_no);
		session.close();
		return m;
	}


	//λΉλ°λ²νΈ update
	public static int updatePwd(HashMap<String,String> m) {
		SqlSession session = factory.openSession();
		int re = session.update("member.updatePwd", m);
		System.out.println("result: " + re);
		session.commit();
		session.close();
		return re;
	}

	
	public static int buyProduct(HashMap map) {
		SqlSession session = factory.openSession();
		int re = session.update("member.buyProduct", map);
		session.commit();
		session.close();
		return re;
	}
	
	//κ²μλ¬Ό μμ±μ ν¬μΈνΈ μμ 
	public static int insertBoardPoint(HashMap map) {
		SqlSession session = factory.openSession();
		int re = session.update("member.insertBoardPoint", map);
		session.commit();
		session.close();
		return re;
	}

	
	
	
	/*-------- νμ λͺ©λ‘ -------*/
	public static List<MemberVO> findAllMember(){
		SqlSession session = factory.openSession();
		List<MemberVO> list= session.selectList("member.findAll");
		session.close();
		return list;
	}	



	

	//--------------------OrderListVOκ΄λ ¨--------------
	/*  μ£Όλ¬Έ μ‘°ν λ‘κ·ΈμΈ */
	public static OrderListVO LoginByOrderId(String name,String id) {

		HashMap map = new HashMap();
		map.put("receiver_name", name);
		map.put("ord_id", id);
		SqlSession session = factory.openSession();
		OrderListVO o = session.selectOne("orderList.LoginByOrderId", map);
		session.close();
		return o;
	}
	
	public static OrderListVO initOrderInfo(int member_no) {
		
		SqlSession session = factory.openSession();
		OrderListVO o = session.selectOne("orderList.initOrderInfo", member_no);
		session.close();
		return o;
	}
	
	public static List<OrderListVO> findAllOrderListByMemberNo(HashMap map) {
		
		SqlSession session = factory.openSession();
		List<OrderListVO> o = session.selectList("orderList.findAllOrderListByMemberNo", map);
		session.close();
		return o;
	}
	public static List<OrderListVO> findOrderListByOrdId(String id) {
		
		SqlSession session = factory.openSession();
		List<OrderListVO> o = session.selectList("orderList.findOrderListByOrdId", id);
		session.close();
		return o;
	}
	
	public static int cntByMember(HashMap map) {
		SqlSession session = factory.openSession();
		int cnt = session.selectOne("cntByMember", map);
		session.close();
		return cnt;
	}
	
	//--------------------OrdersVOκ΄λ ¨--------------
	public static int getCntOfToday() {
		SqlSession session = factory.openSession();
		int cnt = session.selectOne("orders.getCntOfToday");
		session.close();
		return cnt;
	}
	
	public static int insertOrders(OrdersVO o) {
		SqlSession session = factory.openSession();
		int cnt = session.insert("orders.insertOrders", o);
		session.commit();
		session.close();
		return cnt;
	}
	public static int getTotalPay(HashMap map) {
		SqlSession session = factory.openSession();
		Integer re = session.selectOne("orders.getTotalPay", map);
		session.close();
		return re;
	}
	public static OrderBillVO billOfOrder(OrdersVO o) {
		SqlSession session = factory.openSession();
		OrderBillVO ob = session.selectOne("orders.billOfOrder", o);
		session.close();
		return ob;
	}
	
	public static OrdersVO receiverInfoByOrdId(String ord_id) {
		SqlSession session = factory.openSession();
		OrdersVO o = session.selectOne("orders.receiverInfoByOrdId", ord_id);
		session.close();
		return o;
	}
	
	
	//--------------------OrdersProductVOκ΄λ ¨--------------
	public static int insertOrdersProduct(HashMap map) {
		SqlSession session = factory.openSession();
		int cnt = session.insert("ordersProduct.insertOrdersProduct", map);
		session.commit();
		session.close();
		return cnt;
	}
	
	
	
	//---------------Challenge (μ±λ¦°μ§ κ΄λ ¨) ----------

	/*κ΄λ¦¬μ - μ±λ¦°μ§ λͺ©λ‘*/
	public static List<ChallengeVO> findAllChg(){
		SqlSession session = factory.openSession();
		List<ChallengeVO> list= session.selectList("challenge.findAll");
		session.close();
		return list;
	}		
	
	/* κ΄λ¦¬μ μ±λ¦°μ§ ν­λͺ© μΆκ° */
	public static int insertChg(ChallengeVO c) {
		SqlSession session  = factory.openSession();
		int re=session.insert("challenge.insert",c);
		session.commit();
		session.close();
		return re;
	}	
	
	/* κ΄λ¦¬μ μ±λ¦°μ§ ν­λͺ© μμΈ */
	public static ChallengeVO findChgByNo(int no) {
		SqlSession session = factory.openSession();
		ChallengeVO c = session.selectOne("challenge.findByNo", no);
		session.close();
		return c;		
	}	
	
	/*κ΄λ¦¬μ - μ±λ¦°μ§ ν­λͺ© μμ */
	public static int updateChg(ChallengeVO c) {
		SqlSession session = factory.openSession(true);
		int re = session.update("challenge.update", c);
		session.commit();
		session.close();
		return re;				
	}	
	
	/*κ΄λ¦¬μ - μ±λ¦°μ§ ν­λͺ© μ­μ */
	public static int deleteChg(int no) {
		SqlSession session  = factory.openSession();
		int re=session.delete("challenge.delete", no);
		session.commit();
		session.close();
		return re;
	}
	
	/*κ΄λ¦¬μ -μ±λ¦°μ§ 3κ° λλ€κ°μ Έμ€κΈ°*/
	public static List<ChallengeVO> selectChgRandom(){
		SqlSession session = factory.openSession();
		List<ChallengeVO> list= session.selectList("challenge.selectChgRandom");
		session.close();
		return list;
	}
	
	/*κ΄λ¦¬μ - μ±λ¦°μ§λ¦¬μ€νΈ μΆκ° νκΈ° */
	public static int insertChglist(HashMap map) {
		SqlSession session  = factory.openSession();
		int re=session.insert("challengelist.insert", map);
		session.commit();
		session.close();
		return re;
	}
	
	/*κ΄λ¦¬μ - μ€λμ±λ¦°μ§λ¦¬μ€νΈ λΆλ¬μ€κΈ° */
	public static List<ChallengeListVO> todayChgList(){
		SqlSession session = factory.openSession();
		List<ChallengeListVO> list= session.selectList("challengelist.todayChgList");
		session.close();
		return list;
	}	
	
	/*κ΄λ¦¬μ - λ΄μΌμ±λ¦°μ§λ¦¬μ€νΈ λΆλ¬μ€κΈ° */
	public static List<ChallengeListVO> tomorrowChgList(){
		SqlSession session = factory.openSession();
		List<ChallengeListVO> list= session.selectList("challengelist.tomorrowChgList");
		session.close();
		return list;
	}	
	
	
	/*κ΄λ¦¬μ - μ΄μ μ±λ¦°μ§λ¦¬μ€νΈ λΆλ¬μ€κΈ° */
	public static List<ChallengeListVO> yesterdayChgList(){
		SqlSession session = factory.openSession();
		List<ChallengeListVO> list= session.selectList("challengelist.yesterdayChgList");
		session.close();
		return list;

	}	
	
	//---------------νμ Challenge --------------------
	/* νμ μ±λ¦°μ§λ¦¬μ€νΈ λΆλ¬μ€κΈ° */
	public static List<ChallengeListVO> findChglist(int member_no){
		SqlSession session = factory.openSession();
		List<ChallengeListVO> list= session.selectList("challengelist.findByNo",member_no);
		session.close();
		return list;
	}	
	
	/* νμ μ±λ¦°μ§ λμ μν λ³κ²½ */
	public static int updateChgStatus(ChallengeListVO c) {
		SqlSession session = factory.openSession();
		int re = session.update("challengelist.updateChgStatus", c);
		session.commit();
		session.close();
		return re;				
	}	

	/* νμ μ±λ¦°μ§μλ£ ν΄λ¦­μ ING-> STAλ‘ λμ μν λ³κ²½ */
	public static int updateChgStatusSTA(int member_no) {
		SqlSession session = factory.openSession();
		int re = session.update("challengelist.updateChgStatusSTA", member_no);
		session.commit();
		session.close();
		return re;				
	}	
	
	/* λμ μλ£ν μ±λ¦°μ§ μλμ§ μ²΄ν¬ */
	public static int checkEndstatus(int member_no) {
		SqlSession session = factory.openSession();
		int cnt = session.selectOne("challengelist.checkEndstatus",member_no);
		session.close();
		return cnt;
	}
	
	
	
	//--------------ChallengeUser (μλ£ν μ±λ¦°μ§λ§ λ΄λ νμ΄λΈ) ----------------
	/* μλ£ν μ±λ¦°μ§λ§ insert */
	public static int insertEndChg(int member_no) {
		SqlSession session  = factory.openSession();
		int re=session.insert("challengeuser.insert",member_no);
		session.commit();
		session.close();
		return re;
	}	
	
	
	//------------------νμ μλ£λ μ±λ¦°μ§ λͺ©λ‘ ----------------------
	public static List<ChallengeUserVO> listChgUserByMemberNO(int member_no){
		SqlSession session = factory.openSession();
		List<ChallengeUserVO> list= session.selectList("challengeuser.listChgUserByMemberNO",member_no);
		session.close();
		return list;
	}		
	
	//--------------μλ£ν μ±λ¦°μ§ λλ¬΄μ
	public static ChallengeUserVO getSaveTree(int member_no) {
		SqlSession session = factory.openSession();
		ChallengeUserVO c = session.selectOne("challengeuser.getSaveTree", member_no);
		session.close();
		return c;		
	}
	
	
	
	
	//---------------CerBoard  (μΈμ¦κ²μν) ---------------------
	
	/* μ€λ μΈμ¦κΈ μλμ§ μ²΄ν¬*/
	public static int checkTodayCer(int member_no) {
		SqlSession session = factory.openSession();
		int cnt = session.selectOne("cerboard.checkTodayCer",member_no);
		session.close();
		return cnt;
	}	
	
	/* μ±λ¦°μ§ μΈμ¦κ²μν λ μ½λκ°μ   */
	public static int getTotalRecordCer(){
		SqlSession session = factory.openSession();
		int no = session.selectOne("cerboard.getTotalRecord");
		session.close();
		return no;
	}

	
	/*  μ±λ¦°μ§ μΈμ¦κ²μν λͺ©λ‘   */
	public static List<CerBoardVO> findCerBoard(HashMap map){
		SqlSession session = factory.openSession();
		List<CerBoardVO> list= session.selectList("cerboard.findAll",map);
		session.close();
		return list;
	}		

	
	/*  μ±λ¦°μ§ μΈμ¦κ²μν λ±λ‘   */
	public static int insertCerBoard(CerBoardVO c) {
		SqlSession session  = factory.openSession();
		int re=session.insert("cerboard.insert",c);
		session.commit();
		session.close();
		return re;
	}	
	
	
	/* μΈμ¦κ²μν μμΈ */
	public static CerBoardVO getCerBoard(int no) {
		SqlSession session = factory.openSession();
		CerBoardVO c = session.selectOne("cerboard.getCerBoard", no);
		session.close();
		return c;		
	}
	
	/* μΈμ¦κ²μν μμ  */
	public static int updateCerBoard(CerBoardVO c) {
		SqlSession session = factory.openSession();
		int re = session.update("cerboard.update", c);
		session.commit();
		session.close();
		return re;	
	}
	
	/* μΈμ¦κ²μν μ­μ  */
	public static int deleteCerBoard(int no) {
		SqlSession session = factory.openSession();
		int re = session.delete("cerboard.delete", no);
		session.commit();
		session.close();
		return re;	
	}	
	
	/* μΈμ¦κ²μν μ‘°νμ */
	public static void updateHitCer(int no) {
		SqlSession session = factory.openSession();
		session.update("cerboard.updateHit", no);
		session.commit();
		session.close();
	}
	
	
	/*  My μ±λ¦°μ§ μΈμ¦ κ²μν λͺ©λ‘ */
	public static List<CerBoardVO> findCerByMember(HashMap map){
		SqlSession session = factory.openSession();
		List<CerBoardVO> list= session.selectList("cerboard.findCerByMember",map);
		session.close();
		return list;
	}		
	
	/* My μ±λ¦°μ§ μΈμ¦κ²μν λ μ½λκ°μ   */
	public static int getTotalRecordMyCer(HashMap map){
		SqlSession session = factory.openSession();
		int no = session.selectOne("cerboard.getTotalRecordMyCer",map);
		session.close();
		return no;
	}
	
	
	
	//---------------WishList (μμλ¦¬μ€νΈ κ΄λ ¨) ----------
	public static List<WishListVO> findByMemberWish(int member_no){
		SqlSession session = factory.openSession();
		List<WishListVO> list = session.selectList("wishList.findByMember", member_no);
		session.close();
		return list;
	}
	public static int cntOfWishList(int member_no){
		SqlSession session = factory.openSession();
		int cnt = session.selectOne("wishList.cntOfWishList", member_no);
		session.close();
		return cnt;
	}
	public static int deleteWishList(int no){
		SqlSession session = factory.openSession();
		int re = session.delete("wishList.deleteWishList", no);
		session.commit();
		session.close();
		return re;
	}
	public static int insertWishList(WishListVO w) {
		SqlSession session = factory.openSession();
		int re = session.insert("wishList.insert", w);
		session.commit();
		session.close();
		return re;
	}
	
	public static int isWishList(WishListVO w) {
		SqlSession session = factory.openSession();
		int cnt = session.selectOne("wishList.isWishList", w);
		session.close();
		return cnt;
	}
	
	//--------------POINT (ν¬μΈνΈ κ΄λ ¨) ----------
	public static int insertPoint(PointVO p) {
		SqlSession session = factory.openSession();
		int re = session.insert("point.insert", p);
		session.commit();
		session.close();
		return re;
	}
	 
	/* μΈμ¦κ²μν κ³΅κ°/λΉκ³΅κ° μμ μ ν¬μΈνΈ μμ  */
	public static int updateCerPoint(HashMap map) {
		SqlSession session = factory.openSession();
		int re = session.update("point.updateCerPoint",map);
		session.commit();
		session.close();
		return re;	
	}	
	

	
	//---------------Community (μ»€λ?€λν°) ---------------------
	
	/*  μ»€λ?€λν°κ²μν λͺ©λ‘   */
	public static List<CommunityVO> findAllCommunity(HashMap map){
		SqlSession session = factory.openSession();
		List<CommunityVO> list= session.selectList("community.findAll",map);
		session.close();
		return list;
	}		
	
	/*  μ»€λ?€λν°κ²μν λ μ½λκ°μ   */
	public static int getTotalRecordCommu(){
		SqlSession session = factory.openSession();
		int no = session.selectOne("community.getTotalRecord");
		session.close();
		return no;
	}

	
	/*  μ»€λ?€λν°κ²μν λ±λ‘   */
	public static int insertCommunity(CommunityVO c) {
		SqlSession session  = factory.openSession();
		int re=session.insert("community.insert",c);
		session.commit();
		session.close();
		return re;
	}	
	
	
	/* μ»€λ?€λν°κ²μν μμΈ */
	public static CommunityVO getCommunity(int no) {
		SqlSession session = factory.openSession();
		CommunityVO c = session.selectOne("community.getCommunity", no);
		session.close();
		return c;		
	}
	
	/* μ»€λ?€λν°κ²μν μμ  */
	public static int updateCommunity(CommunityVO c) {
		SqlSession session = factory.openSession();
		int re = session.update("community.update", c);
		session.commit();
		session.close();
		return re;	
	}
	
	/* μ»€λ?€λν°κ²μν μ­μ  */
	public static int deleteCommunity(int no) {
		SqlSession session = factory.openSession();
		int re = session.delete("community.delete", no);
		session.commit();
		session.close();
		return re;	
	}	
	
	/* μ»€λ?€λν°κ²μν μ‘°νμ */
	public static void updateHitCommu(int no) {
		SqlSession session = factory.openSession();
		session.update("community.updateHit", no);
		session.commit();
		session.close();
	}
	
	
	
	/*  My μ»€λ?€λν°κ²μν λͺ©λ‘ */
	public static List<CommunityVO> findCommuByMember(int member_no){
		SqlSession session = factory.openSession();
		List<CommunityVO> list= session.selectList("community.findCommuByMember",member_no);
		session.close();
		return list;
	}		

	
	//---------------EasyToStart---------------
		
		
		
		public static List<EasyToStartVO> findAllETS(HashMap map){
			SqlSession session = factory.openSession();
			List<EasyToStartVO> list = session.selectList("easyToStart.findAll", map);
			session.close();
			return list;
			
		}

		public static int getTotalRecordETS(){
			SqlSession session = factory.openSession();
			int no = session.selectOne("easyToStart.getTotalRecord");
			session.close();
			return no;
		}

		
		public static EasyToStartVO findByNoETS(int no) {
			SqlSession session = factory.openSession();
			EasyToStartVO b = session.selectOne("easyToStart.findByNoETS", no);
			session.close();
			return b;		
		}
		
		public static int findLikeETS(int no) {
			SqlSession session = factory.openSession();
			int likeCount = session.selectOne("easyToStart.findLikeETS", no);
			session.close();
			return likeCount;		
		}
		
		public static void updateHitETS(int no) {
			SqlSession session = factory.openSession();
			session.update("easyToStart.updateHitETS", no);
			session.commit();
			session.close();
		}
		
		public static void updateLikeETS(int no) {
			SqlSession session = factory.openSession();
			session.update("easyToStart.updateLikeETS", no);
			session.commit();
			session.close();
		}


		//myPage.memberInfo

		//νμ λ³ΈμΈνμΈ
		public static int isMember(HashMap<String, String> hashMap){
			SqlSession session = factory.openSession();
			int re = session.selectOne("member.isMember", hashMap);
			session.close();
			return re;
		}

		//notice------------------------------------------
		public static List<NoticeVO> getList(){
			SqlSession session = factory.openSession();
			List<NoticeVO> list = session.selectList("notice.getList");
			session.close();
			return list;
		}

		public static List<NoticeVO> findAllNotice(HashMap map){
			SqlSession session = factory.openSession();
			List<NoticeVO> list = session.selectList("notice.findAll", map);
			session.close();
			return list;
		}

		public static int getTotalRecord(){
			SqlSession session = factory.openSession();
			int no = session.selectOne("notice.getTotalRecord");
			session.close();
			return no;
		}

		
		public static NoticeVO findByNoNotice(int no) {
			SqlSession session = factory.openSession();
			NoticeVO b = session.selectOne("notice.findByNoNotice", no);
			session.close();
			return b;		
		}
		
		public static int updateNotice(NoticeVO b) {
			SqlSession session = factory.openSession(true);
			int re = session.update("notice.updateNotice", b);
			session.commit();
			session.close();
			return re;
		}

		public static int deleteNotice(HashMap map) {
			SqlSession session  = factory.openSession();
			int re = session.delete("notice.deleteNotice", map);
			session.commit();
			session.close();
			return re;
		}
		
		public static void updateHitNotice(int no) {
			SqlSession session = factory.openSession();
			session.update("notice.updateHitNotice", no);
			session.commit();
			session.close();
		}

		public static int insertNotice(NoticeVO n){
			SqlSession session = factory.openSession();
			int re = session.insert("notice.insert", n);
			session.commit();
			session.close();
			return re;
		}
	 
		//-------------------RECEIVERVO------------------
		public static AddressVO findByReceiverNo(int receiver_no) {
			SqlSession session = factory.openSession();
			AddressVO r = session.selectOne("receiver.findByNo", receiver_no);
			session.close();
			return r;
		}
		
		
		//-------------IMG (νμΌ)------------
		//λ μ½λ μΆκ°
		public static int uploadFile(HashMap map){
			SqlSession session = factory.openSession();
			int re = session.insert("img.uploadFile", map);
			session.commit();
			session.close();
			return re;
		}	
		
		//-- λ μ½λ μ­μ 
		public static int deleteImg(int no) {
			SqlSession session  = factory.openSession();
			int re=session.delete("img.delete", no);
			session.commit();
			session.close();
			return re;
		}
		
		//--λ μ½λ μ λ³΄ (μ΄λ―Έμ§ λ²νΈλ‘ μ°Ύμμ€κΈ°)
		public static ImgVO findImgByNo(int no) {
			SqlSession session = factory.openSession();
			ImgVO i = session.selectOne("img.findImgByNo", no);
			session.close();
			return i;		
		}
		
		//---μ»€λ?€λν° μ΄λ―Έμ§λ¦¬μ€νΈ
		public static List<ImgVO> listCommuImg(int no){
			SqlSession session = factory.openSession();
			List<ImgVO> list = session.selectList("img.listCommuImg",no);
			session.close();
			return list;	
		}
		
		//---μ»€λ?€λν° μ΄λ―Έμ§λ¦¬μ€νΈ
		public static List<ImgVO> listCerImg(int no){
			SqlSession session = factory.openSession();
			List<ImgVO> list = session.selectList("img.listCerImg",no);
			session.close();
			return list;	
		}
		

				
		
		
}