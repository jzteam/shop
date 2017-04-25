package com.mall.model;

import java.util.List;

import java.util.Map;

import com.mall.dao.AdminAdminDao;
import com.mall.dao.AdminGoodsDao;
import com.mall.dao.AdminDao;
import com.mall.dao.AdminInformDao;
import com.mall.dao.AdminLoginDao;
import com.mall.dao.AdminNoteDao;
import com.mall.dao.AdminOrderDao;
import com.mall.dao.AdminSubTypeDao;
import com.mall.dao.AdminSuperTypeDao;
import com.mall.dao.AdminTypeDao;
import com.mall.dao.AdminUserDao;
import com.mall.dao.GoodsDao;
import com.mall.dao.OrderDao;
import com.mall.dao.SubTypeDao;
import com.mall.dao.SuperTypeDao;
import com.mall.dao.UserDao;
import com.mall.daoimpl.AdminAdminDaoImpl;
import com.mall.daoimpl.AdminGoodsDaoImpl;
import com.mall.daoimpl.AdminDaoImpl;
import com.mall.daoimpl.AdminInformDaoImpl;
import com.mall.daoimpl.AdminLoginDaoImpl;
import com.mall.daoimpl.AdminNoteDaoImpl;
import com.mall.daoimpl.AdminOrderDaoImpl;
import com.mall.daoimpl.AdminSubTypeDaoImpl;
import com.mall.daoimpl.AdminSuperTypeDaoImpl;
import com.mall.daoimpl.AdminTypeDaoImpl;
import com.mall.daoimpl.AdminUserDaoImpl;
import com.mall.daoimpl.GoodsDaoImpl;
import com.mall.daoimpl.OrderDaoImpl;
import com.mall.daoimpl.SubTypeDaoImpl;
import com.mall.daoimpl.SuperTypeDaoImpl;
import com.mall.daoimpl.UserDaoImpl;
import com.mall.po.Admin;
import com.mall.po.AdminPager;
import com.mall.po.Goods;
import com.mall.po.GoodsPager;
import com.mall.po.Inform;
import com.mall.po.InformPager;
import com.mall.po.Note;
import com.mall.po.NotePager;
import com.mall.po.Order;
import com.mall.po.OrderFreezePager;
import com.mall.po.OrderNotSendPager;
import com.mall.po.OrderPager;
import com.mall.po.OrderSendPager;
import com.mall.po.Page;
import com.mall.po.SubType;
import com.mall.po.SuperType;
import com.mall.po.User;
import com.mall.po.UserPager;

public class Model {
	private UserDao ud = new UserDaoImpl();
	private GoodsDao bd = new GoodsDaoImpl();
	private OrderDao od = new OrderDaoImpl();
	private SuperTypeDao superDao = new SuperTypeDaoImpl();

	private SubTypeDao subDao = new SubTypeDaoImpl();
    //获取大类
	public List showAllSuperType() {
		return superDao.showAllSuperType();
	}
    //根据大类Id获取对应所有的小类
	public List showAllSubTypeBySuperId(int superId) {
		return subDao.showAllSubTypeBySuperId(superId);
	}
	//用户注册
	public boolean addUser(User user) {
		return ud.addUser(user);
	}
	//用户登录
	public User userLogin(String name,String password) {
		return ud.userLogin(name,password);
	}
	//检查用户名是否存在
	public boolean checkNameExist(String name){
		return ud.checkNameExist(name);
	}
	//用户管理页面
	public List listUser() {
		return ud.listUser();
	}
	//根据ID删除用户
	public boolean delete(int id,String powerType){
		return ud.delete(id,powerType);
	}
	//根据ID更改用户权限
	public boolean changePower(int id,String powerType){
		return ud.changePower(id,powerType);
	}
	//根据用户名，获取其权限
	public String getPower(String name){
		return ud.getPower(name);
	}
	//根据用户名，找到该用户
	public User getUser(String name){
		return ud.getUser(name);
	}
	//显示商品
	public List showGoods(int type,int flag) {
		return bd.showGoods(type, flag);
	}
	//根据商品的GoodsId来显示商品的详细信息
	public Goods showGoodsById(int GoodsId){
		return bd.showGoodsById(GoodsId);
	}
	
	//根据商品的GoodsId来显示商品的购买记录
	public List showBuyRecordsById(int GoodsId){
		return bd.showBuyRecordsById(GoodsId);
	}
	
	//添加订单，并返回订单号
	public int addOrder(Order order){
		return od.addOrder(order);
	}	
	//分页显示商品
	public Page doPage(int type,int currentPage,int pageSize, int flag){
		return bd.doPage(type,currentPage, pageSize,flag);
	}
	//根据用户输入的关键字搜索相关商品
	public List searchGoods(String keywords){
		return bd.searchGoods(keywords);
	}
	//根据用户输入的关键字搜索相关商品
	public boolean updateGoodsNum(int num,int GoodsId){
		return bd.updateGoodsNum(num,GoodsId);
	}
	public boolean updateGoods(Goods Goods){
		return bd.updateGoods(Goods);
	}
	
	/**
	 * 分页显示所有用户留言
	 * @param currentPage 显示出来的当前页
	 * @param pageSize 每页显示数目
     * @return Page
	 */
	public Page doPage(String keywords,int currentPage,int pageSize){
		return bd.doPage(keywords, currentPage, pageSize);
	}
	//显示所有用户留言
    public List showNote(){
		return ud.showNote();
	}
	/**
	 * 显示所有用户留言
	 * @param currentPage 显示出来的当前页码
	 * @param pageSize 每页显示数目
	 * @return Page
	 */
	public Page doNotePage(int currentPage,int pageSize){
		return ud.doNotePage(currentPage, pageSize);
	}
	//用户添加留言
	public boolean addNote(Note note){
		return ud.addNote(note);
	}
	/**
	 * 根据用户选择的搜索条件搜索相关商品
	 * @param superTypeId 所属大类的ID
	 * @param subTypeId 所属小类的ID
	 * @param searchMethod （可以是按品牌、商品名、产地、编码的一个） 
	 * @return List
	 */
	public List searchGoodsByConditions(int superTypeId,int subTypeId,String searchMethod){
		return bd.searchGoodsByConditions(superTypeId, subTypeId, searchMethod);//djm
	}
	/**
	 *根据用户选择的搜索条件搜索相关商品
	 * @param superTypeId 所属大类的ID
	 * @param subTypeId 所属小类的ID
	 * @param searchMethod 可以是按品牌、商品名、产地、编码的一个
	 */
	public Page doPageByConditons(int superTypeId,int subTypeId,String searchMethod,int currentPage,int pageSize){
		return bd.doPageByConditons(superTypeId, subTypeId, searchMethod, currentPage, pageSize);
	}
	/**
	 * 后台*/
	private AdminUserDao userDao = new AdminUserDaoImpl();
	private AdminAdminDao adminDao = new AdminAdminDaoImpl();
	private AdminDao adminDao1=new AdminDaoImpl();
	private AdminSubTypeDao subTypeDao = new AdminSubTypeDaoImpl();
	private AdminSuperTypeDao superTypeDao = new AdminSuperTypeDaoImpl();
	private AdminTypeDao adminTypeDao = new AdminTypeDaoImpl();
	private AdminGoodsDao GoodsDao = new AdminGoodsDaoImpl();
	private AdminNoteDao noteDao = new AdminNoteDaoImpl();
	private AdminLoginDao adminLoginDao = new AdminLoginDaoImpl();
	private AdminInformDao adminInformDao = new AdminInformDaoImpl();
	private AdminOrderDao adminOrderDao = new AdminOrderDaoImpl();
	
	public Map getAllUsers() {
		return userDao.getAllUsers();
	}
	
	public boolean deleteUserById(int id) {
		return userDao.deleteUserById(id);
	}
	
	public boolean deleteUsers(int[] ids) {
		return userDao.deleteUsers(ids);
	}
	
	public UserPager getUserPager(int index, int pageSize) {
		return userDao.getUserPager(index, pageSize);
	}
	
	public AdminPager getAdminPager(int index, int pageSize) {
		return adminDao.getAdminPager(index, pageSize);
	}
	
	public List getSubTypeBySuperTypeId(int superTypeId) {
		return subTypeDao.getSubTypeBySuperTypeId(superTypeId);
	}
	
	public boolean addSubType(SubType type) {
		return subTypeDao.addSubType(type);
	}
	
	public boolean checkSubTypeIsExist(String subTypeName) {
		return subTypeDao.checkSubTypeIsExist(subTypeName);
	}
	
	public boolean checkLoginNameIsExist(String loginName) {
		return adminDao1.checkNameExist(loginName);
	}
	
	public boolean addAdmin(Admin admin){
		return adminDao1.addAdmin(admin);
	}
	
	public boolean updateAdmin(Admin admin){
		return adminDao1.updateAdmin(admin);
	}
	
    public boolean deleteAdmin(int[] ids){
		return adminDao1.deleteAdmin(ids);
	}
    
	public List getSuperType() {
		return superTypeDao.getSuperType();
	}
	
	public List getAdminType() {
		return adminTypeDao.getAdminType();
	}
	
	public boolean addSuperType(SuperType type) {
		return superTypeDao.addSuperType(type);
	}
	
	public boolean checkSuperTypeIsExist(String superTypeName) {
		return superTypeDao.checkSuperTypeIsExist(superTypeName);
	}
	
	public List getAllGoods() {
		return GoodsDao.getAllGoods();
	}
	
	public boolean addGoods(Goods Goods) {
		return GoodsDao.addGoods(Goods);
	}
	
	public boolean checkGoodsNameIsExist(String GoodsName) {
		return GoodsDao.checkGoodsNameIsExist(GoodsName);
	}

	public boolean checkISBNIsExist(String ISBN) {
		return GoodsDao.checkISBNIsExist(ISBN);
	}
	
	public GoodsPager searchGood(String GoodsName) {
		return GoodsDao.searchGoods(GoodsName);
	}

	public List getAllGoodsName() {
		return GoodsDao.getAllGoodsName();
	}
	
	public GoodsPager getGoodsPager(int index,int pageSize) {
		return GoodsDao.getGoodsPager(index, pageSize);
	}
	
	public boolean deleteGoods(int[] GoodsIds) {
		return GoodsDao.deleteGoods(GoodsIds);
	}
	
	public List getAllNotes() {
		return noteDao.getAllNotes();
	}
	
	public boolean deleteNote(int[] ids) {
		return noteDao.deleteNote(ids);
	}
	
	public NotePager getNotePager(int index, int pageSize) {
		return noteDao.getNotePager(index, pageSize);
	}
	
	public int login(Admin admin) {
		return adminLoginDao.login(admin);
	}
	
	public boolean updatePassword(Admin admin) {
		return adminLoginDao.updatePassword(admin);
	}
	
	public boolean addInform(Inform inform) {
		return adminInformDao.addInform(inform);
	}
	
	public List getAllInform() {
		return adminInformDao.getAllInform();
	}
	
	public boolean deleteInform(int[] ids) {
		return adminInformDao.deleteInform(ids);
	}
	
	public Inform getOneInform(int id) {
		return adminInformDao.getOneInform(id);
	}
	
	public InformPager getInformPager(int index, int pageSize) {
		return adminInformDao.getInformPager(index, pageSize);
	}
	
	public List getAllOrder() {
		return adminOrderDao.getAllOrder();
	}
	
	public Order getOneOrder(int id) {
		return adminOrderDao.getOneOrder(id);
	}
	
	public List getNotSendOrder(int flag) {
		return adminOrderDao.getNotSendOrder(flag);
	}

	public List getSendOrder(int flag) {
		return adminOrderDao.getSendOrder(flag);
	}

	public boolean SendOrders(int[] orderids) {
		return adminOrderDao.SendOrders(orderids);
	}
	
	public boolean SendOrder(int orderid) {
		return adminOrderDao.SendOrder(orderid);
	}
	
	public OrderPager getOrderPager(int index, int pageSize) {
		return adminOrderDao.getOrderPager(index, pageSize);
	}
	
	public OrderSendPager getOrderSendPager(int index, int pageSize) {
		return adminOrderDao.getOrderSendPager(index, pageSize);
	}
	
	public boolean deleteOrder(int[] ids) {
		return adminOrderDao.deleteOrder(ids);
	}
	
	public OrderNotSendPager getOrderNotSendPager(int index, int pageSize) {
		return adminOrderDao.getOrderNotSendPager(index, pageSize);
	}
	
	public OrderFreezePager getOrderFreezePager(int index,int pageSize) {
		return adminOrderDao.getOrderFreezePager(index, pageSize);
	}
	
	public boolean freezeOrder(int[] orderids) {
		return adminOrderDao.freezeOrder(orderids);
	}
	
	//根据当前用户的用户名查订单
	public List selectOrder(String name){
		return od.selectOrder(name);
	}
	//根据订单号查订单项
	public List selectOrderItem(int id ){
		return od.selectOrderItem(id);
	}
	
	public Boolean loggin(String name, String password){
		return ud.loggin(name, password);
	}
	
    public User SelectOneUser(String name){
		return ud.SelectOneUser(name);
	}
	 //用于修改用户的个人密码
	public Boolean updatePassword(String name, String password){
		return ud.updatePassword(name, password);
	}

	public Boolean updateUser(User user) {
		return ud.updateUser(user);
	}
}
