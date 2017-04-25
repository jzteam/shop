package com.mall.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.mall.common.DbUtil;
import com.mall.dao.AdminGoodsDao;
import com.mall.po.Goods;
import com.mall.po.GoodsPager;

public class AdminGoodsDaoImpl implements AdminGoodsDao{

	public List getAllGoods() {
		List goodslist = new ArrayList();
		DbUtil daoUtil = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = null;
		try {
			daoUtil = new DbUtil();
			sql = "select * from tb_goods";
			ps = daoUtil.getCon().prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				Goods goods1 = new Goods();
				goods1.setGoodsId(rs.getInt("bookId"));
				goods1.setSuperTypeId(rs.getInt("superTypeId"));
				goods1.setSubTypeId(rs.getInt("subTypeId"));
				goods1.setGoodsName(rs.getString("goodsName"));
				goods1.setIntroduce(rs.getString("introduce"));
				goods1.setPrice(rs.getFloat("price"));
				goods1.setNowPrice(rs.getFloat("nowPrice"));
				goods1.setPicture(rs.getString("picture"));
				goods1.setInTime(rs.getString("inTime"));
				goods1.setNewGoods(rs.getInt("newGoods"));
				goods1.setSaleGoods(rs.getInt("saleGoods"));
				goods1.setHostGoods(rs.getInt("hostGoods"));
				goods1.setGoodsNum(rs.getInt("goodsNum"));
				goodslist.add(goods1);
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				ps.close();
				daoUtil.close();
			} catch(SQLException s) {
				s.printStackTrace();
			}
		}
		return goodslist;
	}

	public boolean addGoods(Goods Goods) {
		DbUtil dao = null;
		PreparedStatement ps = null;
		String sql = "";
		try {
			dao = new DbUtil();
			sql = "insert into tb_goods values(?,?,?,?,?,?,?,?,?,?,?,getdate(),?,?,?,?,?)";
			ps = dao.getCon().prepareStatement(sql);
			ps.setInt(1, Goods.getSuperTypeId());
			ps.setInt(2, Goods.getSubTypeId());
			ps.setString(3, Goods.getGoodsName());
			ps.setString(4, Goods.getISBN());
			ps.setString(5, Goods.getIntroduce());
			ps.setFloat(6, Goods.getPrice());
			ps.setFloat(7, Goods.getNowPrice());
			ps.setString(8, Goods.getPicture());
			ps.setString(9, Goods.getProduceDate());
			ps.setString(10, Goods.getPublisher());
			ps.setString(11, Goods.getAuthor());
			ps.setInt(12, Goods.getNewGoods());
			ps.setInt(13, Goods.getSaleGoods());
			ps.setInt(14, Goods.getHostGoods());
			ps.setInt(15, Goods.getSpecialGoods());
			ps.setInt(16, Goods.getGoodsNum());
			int i = ps.executeUpdate();
			if(i != 0) {
				return true;
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				ps.close();
				dao.close();
			} catch(SQLException s) {
				s.printStackTrace();
			}
		}
		return false;
	}

	public boolean checkGoodsNameIsExist(String GoodsName) {
		DbUtil daoUtil = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = null;
		try {
			daoUtil = new DbUtil();
			sql = "select * from tb_goods where goodsName = ?";
			ps = daoUtil.getCon().prepareStatement(sql);
			ps.setString(1, GoodsName);
			rs = ps.executeQuery();
			if (rs.next()) {
				return true;
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				ps.close();
				daoUtil.close();
			} catch(SQLException s) {
				s.printStackTrace();
			}
		}
		return false;
	}

	public boolean checkISBNIsExist(String ISBN) {
		DbUtil daoUtil = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = null;
		try {
			daoUtil = new DbUtil();
			sql = "select * from tb_goods where ISBN = ?";
			ps = daoUtil.getCon().prepareStatement(sql);
			ps.setString(1, ISBN);
			rs = ps.executeQuery();
			if (rs.next()) {
				return true;
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				ps.close();
				daoUtil.close();
			} catch(SQLException s) {
				s.printStackTrace();
			}
		}
		return false;
	}

	public GoodsPager searchGoods(String GoodsName) {
		Map GoodsMap = new HashMap();
		DbUtil daoUtil = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = null;
		try {
			daoUtil = new DbUtil();
			sql = "select * from tb_goods where goodsName like ?";
			ps = daoUtil.getCon().prepareStatement(sql);
			ps.setString(1, GoodsName+"%");
			rs = ps.executeQuery();
			while (rs.next()) {
				Goods Goods = new Goods();
				Goods.setGoodsId(rs.getInt("bookId"));
				Goods.setSuperTypeId(rs.getInt("superTypeId"));
				Goods.setSubTypeId(rs.getInt("subTypeId"));
				Goods.setGoodsName(rs.getString("goodsName"));
				Goods.setIntroduce(rs.getString("introduce"));
				Goods.setPrice(rs.getFloat("price"));
				Goods.setNowPrice(rs.getFloat("nowPrice"));
				Goods.setPicture(rs.getString("picture"));
				Goods.setInTime(rs.getString("inTime"));
				Goods.setNewGoods(rs.getInt("newGoods"));
				Goods.setSaleGoods(rs.getInt("saleGoods"));
				Goods.setHostGoods(rs.getInt("hostGoods"));
				Goods.setGoodsNum(rs.getInt("goodsNum"));
				GoodsMap.put(Goods.getGoodsId(),Goods);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				ps.close();
				daoUtil.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		GoodsPager bp = new GoodsPager();
		bp.setGoodsMap(GoodsMap);
		bp.setTotalNum(GoodsMap.size());
		return bp;
	}

	public List getAllGoodsName() {
		List GoodsNameList = new ArrayList();
		DbUtil daoUtil = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = null;
		try {
			daoUtil = new DbUtil();
			sql = "select goodsName from tb_goods";
			ps = daoUtil.getCon().prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				GoodsNameList.add(rs.getString("goodsName"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				ps.close();
				daoUtil.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return GoodsNameList;
	}
	public GoodsPager getGoodsPager(int index,int pageSize) {
		Map GoodsMap = new HashMap();
		DbUtil db = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			db = new DbUtil(); 
			String sql = "select top "+pageSize+" * from tb_goods where bookId not in (select top "+index+" bookId from tb_goods) ";
			ps = db.getCon().prepareStatement(sql);
			 
			rs = ps.executeQuery();
			while(rs.next()) {
				Goods Goods = new Goods();
				Goods.setGoodsId(rs.getInt("bookId"));
				Goods.setSuperTypeId(rs.getInt("superTypeId"));
				Goods.setSubTypeId(rs.getInt("subTypeId"));
				Goods.setGoodsName(rs.getString("goodsName"));
				Goods.setIntroduce(rs.getString("introduce"));
				Goods.setPrice(rs.getFloat("price"));
				Goods.setNowPrice(rs.getFloat("nowPrice"));
				Goods.setPicture(rs.getString("picture"));
				Goods.setInTime(rs.getString("inTime"));
				Goods.setNewGoods(rs.getInt("newGoods"));
				Goods.setSaleGoods(rs.getInt("saleGoods"));
				Goods.setHostGoods(rs.getInt("hostGoods"));
				Goods.setGoodsNum(rs.getInt("goodsNum"));
				GoodsMap.put(Goods.getGoodsId(), Goods);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				ps.close();
				db.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		GoodsPager bp = new GoodsPager();
		bp.setGoodsMap(GoodsMap);
		bp.setPageSize(pageSize);
		bp.setTotalNum(getAllGoods().size());
		return bp;
	}
	public boolean deleteGoods(int[] GoodsIds) {
		DbUtil daoUtil = null;
		PreparedStatement ps = null;
		Connection conn = null;
		String sql = "delete from tb_goods where bookId=?";
		try {
			daoUtil = new DbUtil();
			conn = daoUtil.getCon();
			conn.setAutoCommit(false);
			ps = conn.prepareStatement(sql);
			for(int j=0;j<GoodsIds.length;j++) {
				ps.setInt(1, GoodsIds[j]);
				ps.addBatch();
			}
			int[] k = ps.executeBatch();
			conn.commit();
			if(k.length == GoodsIds.length) {
				return true;
			}
		} catch (Exception e) {
			try {
				conn.rollback();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
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
}
