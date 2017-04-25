package com.mall.daoimpl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mall.common.DbUtil;
import com.mall.dao.GoodsDao;
import com.mall.po.BuyRecord;
import com.mall.po.Goods;
import com.mall.po.Page;

public class GoodsDaoImpl implements GoodsDao {
	/**
	 * 显示商品
	 *  @param type 显示类别（热卖，新商品，降价,特别推荐）
	 * @param flag 表示是否是（热卖，新商品，降价）
	 */
	public List showGoods(int type,int flag){
		List all = new ArrayList();
		PreparedStatement pstmt = null;
		DbUtil dbUtil = null;
		ResultSet rs = null;
		String sql = null;
		if(type==0){
			//显示所有商品
			sql = "select * from tb_goods";
			try {
				dbUtil = new DbUtil();
				pstmt = dbUtil.getCon().prepareStatement(sql);
				rs = pstmt.executeQuery();
				while(rs.next()) {
					Goods Goods = new Goods();
					Goods.setGoodsId(rs.getInt("bookId"));
					Goods.setGoodsName(rs.getString("GoodsName"));
					Goods.setISBN(rs.getString("ISBN"));
					Goods.setProduceDate(rs.getString("produceDate"));
					Goods.setAuthor(rs.getString("author"));
					Goods.setPublisher(rs.getString("publisher"));
					Goods.setIntroduce(rs.getString("introduce"));
					Goods.setPrice(rs.getFloat("price"));
					Goods.setNowPrice(rs.getFloat("nowPrice"));
					Goods.setPicture(rs.getString("picture"));
					all.add(Goods);					
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}finally {
				try {
					rs.close();
					pstmt.close();
					dbUtil.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}else {
		if(type==1) {
			//热卖商品
			sql = "select * from tb_goods where hostGoods=?";
		}
		if(type==2){
			//新到商品
			sql = "select * from tb_goods where newGoods=?";
		}
		if(type==3){
			//打折商品
			sql = "select * from tb_goods where saleGoods=?";
		}
		if(type==4){
		 // 特别推荐
			sql = "select * from tb_goods where specialGoods=?";
		}
		if(type==9){
			 // 分类查看
				sql = "select * from tb_goods where superTypeId=?";
		}
		
		
		try {
			dbUtil = new DbUtil();
			pstmt = dbUtil.getCon().prepareStatement(sql);
			pstmt.setInt(1, flag);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				Goods Goods = new Goods();
				Goods.setGoodsId(rs.getInt("bookId"));
				Goods.setGoodsName(rs.getString("goodsName"));
				Goods.setISBN(rs.getString("ISBN"));
				Goods.setProduceDate(rs.getString("produceDate"));
				Goods.setAuthor(rs.getString("author"));
				Goods.setPublisher(rs.getString("publisher"));
				Goods.setIntroduce(rs.getString("introduce"));
				Goods.setPrice(rs.getFloat("price"));
				Goods.setNowPrice(rs.getFloat("nowPrice"));
				Goods.setPicture(rs.getString("picture"));
				all.add(Goods);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				rs.close();
				pstmt.close();
				dbUtil.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		}
		return all;
	}
	public boolean updateGoodsNum(int num,int GoodsId) {
		DbUtil daoUtil = null;
		PreparedStatement ps = null;
		String sql = "update tb_goods set GoodsNum=? where bookId=?";
		try {
			daoUtil = new DbUtil();
			ps = daoUtil.getCon().prepareStatement(sql);
			ps.setInt(1, num);
			ps.setInt(2, GoodsId);
			int i = ps.executeUpdate();
			if(i != 0) {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				ps.close();
				daoUtil.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return false;
	}
	public boolean updateGoods(Goods Goods){
		DbUtil daoUtil = null;
		PreparedStatement ps = null;
		String sql = "update tb_goods set goodsName=?,goodsNum=?,introduce=?,ISBN=?,author=?,superTypeId=?,subTypeId=?,publisher=?,price=?,nowPrice=?," +
				"produceDate=?,hostGoods=?,newGoods=?,saleGoods=?,specialGoods=? where bookId=?";
		try {
			daoUtil = new DbUtil();
			ps = daoUtil.getCon().prepareStatement(sql);
			ps.setString(1, Goods.getGoodsName());
			ps.setInt(2, Goods.getGoodsNum());
            ps.setString(3, Goods.getIntroduce());
            ps.setString(4, Goods.getISBN());
            ps.setString(5, Goods.getAuthor());
            ps.setInt(6, Goods.getSuperTypeId());
            ps.setInt(7, Goods.getSubTypeId());
            ps.setString(8, Goods.getPublisher());
            ps.setFloat(9, Goods.getPrice());
            ps.setFloat(10, Goods.getNowPrice());
            ps.setString(11, Goods.getProduceDate());
            ps.setInt(12, Goods.getHostGoods());
            ps.setInt(13, Goods.getNewGoods());
            ps.setInt(14, Goods.getSaleGoods());
            ps.setInt(15, Goods.getSpecialGoods());
            ps.setInt(16, Goods.getGoodsId());
			int i = ps.executeUpdate();
			if(i != 0) {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				ps.close();
				daoUtil.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return false;
	}
	/**
	 *  根据商品的GoodsId号来展示该商品的详细信息
	 * @param GoodsId 商品号
	 * @return Goods
	 */
	public Goods showGoodsById(int GoodsId){
		Goods Goods = new Goods();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		DbUtil dbUtil = null;
		String sql = "select * from tb_goods where bookId=?";
		try {
			dbUtil = new DbUtil();
			pstmt = dbUtil.getCon().prepareStatement(sql);
			pstmt.setInt(1, GoodsId);
			rs = pstmt.executeQuery();
			if(rs.next()){
				Goods.setGoodsId(rs.getInt("bookId"));
				Goods.setGoodsName(rs.getString("goodsName"));
				Goods.setIntroduce(rs.getString("introduce"));
				Goods.setISBN(rs.getString("ISBN"));
				Goods.setAuthor(rs.getString("author"));
				Goods.setProduceDate(rs.getString("produceDate"));
				Goods.setSuperTypeId(rs.getInt("superTypeId"));
				Goods.setSubTypeId(rs.getInt("subTypeId"));
				Goods.setPublisher(rs.getString("publisher"));
				Goods.setPrice(rs.getFloat("price"));
				Goods.setNowPrice(rs.getFloat("nowPrice"));
				Goods.setPicture(rs.getString("picture"));
				Goods.setGoodsNum(rs.getInt("goodsNum"));
				Goods.setHostGoods(rs.getInt("hostGoods"));
				Goods.setNewGoods(rs.getInt("newGoods"));
				Goods.setSaleGoods(rs.getInt("saleGoods"));
				Goods.setSpecialGoods(rs.getInt("specialGoods"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				rs.close();
				pstmt.close();
				dbUtil.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return Goods;
	}
	
	public List showBuyRecordsById(int GoodsId){
		List searchList = new ArrayList();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		DbUtil dbUtil = null;
		String sql = "select B.name,A.goodsNum,B.orderDate from tb_orderitem A, tb_order B where A.orderId=B.orderId and A.bookId="+GoodsId+"";
		try 
		{
			dbUtil = new DbUtil();
			pstmt = dbUtil.getCon().prepareStatement(sql);
			rs = pstmt.executeQuery();
				
				while(rs.next()){
					BuyRecord brc = new BuyRecord();
					brc.setName(rs.getString("name"));
					brc.setGoodsNum(rs.getInt("goodsNum"));
					brc.setOrderDate(rs.getString("orderDate"));
					
					searchList.add(brc);
				}	
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return searchList;
	}
	
	/**
	 *		将商品本信息录入数据库
	 */
	public boolean addGoods(Goods Goods){
		boolean flag = false;
		PreparedStatement pstmt = null;
		DbUtil dbUtil = null;
		String sql = "insert into tb_goods values(?,?,?,?,?,?,?,?,?,?,?,getdate(),?,?,?,?)";
		try {
			dbUtil = new DbUtil();
			pstmt = dbUtil.getCon().prepareStatement(sql);
			pstmt.setInt(1, Goods.getSuperTypeId());
			pstmt.setInt(2, Goods.getSubTypeId());
			pstmt.setString(3, Goods.getGoodsName());
			pstmt.setString(4, Goods.getISBN());
			pstmt.setString(5, Goods.getIntroduce());
			pstmt.setFloat(6, Goods.getPrice());
			pstmt.setFloat(7, Goods.getNowPrice());
			pstmt.setString(8, Goods.getPicture());
			pstmt.setString(9, Goods.getProduceDate());
			pstmt.setString(10, Goods.getPublisher());
			pstmt.setString(11, Goods.getAuthor());
			pstmt.setInt(12, Goods.getNewGoods());
			pstmt.setInt(13, Goods.getSaleGoods());
			pstmt.setInt(14, Goods.getHostGoods());
			pstmt.setInt(15, Goods.getGoodsNum());
			int i = pstmt.executeUpdate();
			if(i != 0){
				//插入商品成功
				return flag;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return flag;
	}
	/**
	 * 分页显示商品
	 * @param type 按要求显示相应的商品
	 * @param currentPage 显示出来的当前页码
	 * @param pageSize 每页显示数目
	 * @param flag 是否分类显示标识，
	 *             -1          否
	 *             other 大分类ID
	 *             
	 * @return Page
	 */
	public Page doPage(int type,int currentPage,int pageSize,int flag){
		Page page = new Page();
		int totalNum = 0;
		if(flag == -1) {
			totalNum = showGoods(type,1).size();
		} else {
			totalNum = showGoods(9,flag).size();
		}
		
		List pageList = new ArrayList();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		DbUtil dbUtil = null;
		String sql = null;
		
		if(flag == -1) {
			if(type==0){//显示所有商品
				//select top @pageSize id from tablename where id not in 
				//(  select top @offset id from tablename  ) 
				sql = "select top "+pageSize+" * from tb_goods where bookId not in "
					+ "(select top "+currentPage+" bookId from tb_goods )";
			}
			if(type==1) {
				//热卖商品
				 
				sql = "select top "+pageSize+" * from tb_goods where hostGoods=1 and bookId not in "
				+ "(select top "+currentPage+" bookId from tb_goods where hostGoods=1)";
			}
			if(type==2){
				//新商品
				sql = "select top "+pageSize+" * from tb_goods where newGoods=1 and bookId not in "
				+ "(select top "+currentPage+" bookId from tb_goods where newGoods=1)";
			}
			if(type==3){
				//打折商品
				sql = "select top "+pageSize+" * from tb_goods where saleGoods=1 and bookId not in "
				+ "(select top "+currentPage+" bookId from tb_goods where saleGoods=1)";
			}
			if(type==4){
				//特别推荐
				sql = "select top "+pageSize+" * from tb_goods where specialGoods=1 and bookId not in "
				+ "(select top "+currentPage+" bookId from tb_goods where specialGoods=1)";
			}
		} else {
			//分类商品
			sql = "select top "+pageSize+" * from tb_goods where superTypeId="+flag+" and bookId not in "
			+ "(select top "+currentPage+" bookId from tb_goods where superTypeId="+flag+")";
		}
		
		try {
			dbUtil = new DbUtil();
			pstmt = dbUtil.getCon().prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()){
				Goods Goods = new Goods();
			    Goods.setGoodsId(rs.getInt("bookId"));
				Goods.setGoodsName(rs.getString("goodsName"));
				Goods.setPicture(rs.getString("picture"));
				Goods.setPrice(rs.getFloat("price"));
				Goods.setNowPrice(rs.getFloat("nowPrice"));
				pageList.add(Goods);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				rs.close();
				pstmt.close();
				dbUtil.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
		page.setPageList(pageList);
		page.setTotalNum(totalNum);
		return page;
	}
	/**
	 * 根据用户输入的关键字搜索相关商品
	 * @param keywords 用户输入的关键字 
	 */
	public List searchGoods(String keywords){
		List searchList = new ArrayList();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		DbUtil dbUtil = null;
		String sql = "select * from tb_goods where goodsName like '%"+keywords+"%'";
		try {
			dbUtil = new DbUtil();
			pstmt = dbUtil.getCon().prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(rs.next()){
				//有相关商品			
				while(rs.next()){
					Goods Goods = new Goods();
					Goods.setGoodsName(rs.getString("goodsName"));
					Goods.setPicture(rs.getString("picture"));
					searchList.add(Goods);
				}	
			}else{
				//没有找到相关商品
				throw new NoRelativeGoodsException("没有搜索到你想找的相关商品！！");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return searchList;
	}
	/**
	 * 分页显示商品
	 * @param currentPage 显示出来的当前页码
	 * @param pageSize 每页显示数目
	 * @return Page
	 */
	public Page doPage(String keywords,int currentPage,int pageSize){
		Page page = new Page();
		int totalNum = searchGoods(keywords).size();
		//System.out.println("totalNum="+totalNum);
		List pageList = new ArrayList();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		DbUtil dbUtil = null;
		
		String sql = "select top "+pageSize+" * from tb_goods where goodsName like '%"+keywords+"%' and bookId not in (select top "+currentPage+" bookId from tb_goods where goodsName like '%"+keywords+"%') ";
		
		try {
			dbUtil = new DbUtil();
			pstmt = dbUtil.getCon().prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()){
				Goods Goods = new Goods();
				Goods.setGoodsId(rs.getInt("bookId"));
				Goods.setGoodsName(rs.getString("goodsName"));
				Goods.setPicture(rs.getString("picture"));
				Goods.setPrice(rs.getFloat("price"));
				Goods.setNowPrice(rs.getFloat("nowPrice"));
				pageList.add(Goods);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				rs.close();
				pstmt.close();
				dbUtil.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
		page.setPageList(pageList);
		page.setTotalNum(totalNum);
		return page;
	}

	public List searchGoodsByConditions(int superTypeId,int subTypeId,String searchMethod){
		List GoodsList = new ArrayList();
		String str[] = searchMethod.split("=");
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		DbUtil dbUtil = null;
		String sql = null;
		sql = "select * from tb_goods where superTypeId=? and subTypeId=? and "+str[0]+" =?";
		try {
			dbUtil = new DbUtil();
			pstmt = dbUtil.getCon().prepareStatement(sql);
			pstmt.setInt(1, superTypeId);
			pstmt.setInt(2, subTypeId);
			pstmt.setString(3, str[1]);
			rs = pstmt.executeQuery();
			if(rs.next()){//有相关商品
				while(rs.next()){
					Goods Goods = new Goods();
					Goods.setGoodsName(rs.getString("goodsName"));
					Goods.setPicture(rs.getString("picture"));
					GoodsList.add(Goods);
				}	
			}else{//没有找到相关商品
				throw new NoRelativeGoodsException("没有搜索到你想找的相关商品！！");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				rs.close();
				pstmt.close();
				dbUtil.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return GoodsList;
	}
	/**
	 * 根据用户选择的搜索条件搜索相关商品
	 * @param superTypeId 所属大类的ID
	 * @param subTypeId 所属小类的ID
	 * @param searchMethod （可以是按品牌、商品名、产地、编码、价格中的一个）
	 * @param upLoadTime 商品上价时间
	 */
	public Page doPageByConditons(int superTypeId,int subTypeId,String searchMethod,int currentPage,int pageSize){
		Page page = new Page();
		String str[] = searchMethod.split("=");
		int totalNum = searchGoodsByConditions(superTypeId,subTypeId,searchMethod).size();
		List pageList = new ArrayList();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		DbUtil dbUtil = null;
		String sql = null;
		sql = "select * from tb_goods where superTypeId=? and subTypeId=? and "+str[0]+" =?";
		try {
			dbUtil = new DbUtil();
			pstmt = dbUtil.getCon().prepareStatement(sql);
			pstmt.setInt(1, superTypeId);
			pstmt.setInt(2, subTypeId);
			pstmt.setString(3, str[1]);
			rs = pstmt.executeQuery();
			while(rs.next()){
				Goods Goods = new Goods();
				Goods.setGoodsId(rs.getInt("bookId"));
				Goods.setGoodsName(rs.getString("goodsName"));
				Goods.setPicture(rs.getString("picture"));
				Goods.setPrice(rs.getFloat("price"));
				Goods.setNowPrice(rs.getFloat("nowPrice"));
				Goods.setAuthor(rs.getString("author"));
				Goods.setGoodsNum(rs.getInt("goodsNum"));
				Goods.setProduceDate(rs.getString("produceDate"));
				Goods.setPublisher(rs.getString("publisher"));
				Goods.setSubTypeId(rs.getInt("subTypeId"));
				Goods.setSuperTypeId(rs.getInt("superTypeId"));
				Goods.setISBN(rs.getString("ISBN"));
				Goods.setInTime(rs.getString("inTime"));
				pageList.add(Goods);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				rs.close();
				pstmt.close();
				dbUtil.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		page.setPageList(pageList);
		page.setTotalNum(totalNum);
		return page;
	}
}

