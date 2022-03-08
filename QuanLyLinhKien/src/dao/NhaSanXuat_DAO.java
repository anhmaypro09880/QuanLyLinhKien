package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import connectDB.ConnectDB;
import entity.NhaSanXuat;


public class NhaSanXuat_DAO {
	public ArrayList<NhaSanXuat> getallnsx()
	{
		ArrayList<NhaSanXuat> dsnsx=new ArrayList<NhaSanXuat>();
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		String sql= "select * from Nhasanxuat";
		try {
			ResultSet rs=con.createStatement().executeQuery(sql);
			while(rs.next())
			{
				int ma = rs.getInt("maNSX");
				String ten = rs.getString("tenNSX");
				String diaChi=rs.getString("diaChi");
				int sdt=rs.getInt("SDT");
				NhaSanXuat nsx=new NhaSanXuat(ma, ten, diaChi, sdt);
				dsnsx.add(nsx);
			}
		} catch (SQLException e) {			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return dsnsx;
	}
	
	public   NhaSanXuat getnsx(int x)
	{
		NhaSanXuat nsx=null;
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement sta= null;
		String sql="select * from Nhasanxuat where maNSX= ?";
		try {
			sta=con.prepareStatement(sql);
			sta.setInt(1,x);
			ResultSet rs= sta.executeQuery();
			while(rs.next())
			{
				int ma = rs.getInt("maNSX");
				String ten = rs.getString("tenNSX");
				String diaChi=rs.getString("diaChi");
				int sdt=rs.getInt("SDT");
				 nsx=new NhaSanXuat(ma, ten, diaChi, sdt);
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return nsx;
	}
	
	public boolean themnsx(NhaSanXuat nsx)
	{
		ConnectDB.getInstance();
		Connection con= ConnectDB.getConnection();
		PreparedStatement sta =null;
		int n=0;
		try {
			sta= con.prepareStatement("insert into Nhasanxuat values(?, ?, ?, ?)");
			sta.setInt(1, nsx.getMaNSX());
			sta.setString(2, nsx.getTenNSX());
			sta.setString(3,nsx.getDiaChi());
			sta.setInt(4,nsx.getSoDT());
			n=sta.executeUpdate();
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}finally {
			try {
				sta.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	public boolean xoansx(int mansx)
	{
		ConnectDB.getInstance();
		Connection con =ConnectDB.getConnection();
		PreparedStatement sta =null;
		int n=0;
		try {
			sta=con.prepareStatement("delete from Nhasanxuat where maNSX=?");
			sta.setInt(1, mansx);
			n=sta.executeUpdate();
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		finally {
			try {
				sta.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	public boolean suansx(NhaSanXuat nsx)
	{
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement sta = null;
		int n =0 ;
		try {
			sta= con.prepareStatement("update Nhasanxuat set tenNSX=?, diaChi= ?, SDT= ? where maNSX =?");
			sta.setString(1, nsx.getTenNSX());
			sta.setString(2,nsx.getDiaChi());
			sta.setInt(3, nsx.getSoDT());
			sta.setInt(4,nsx.getMaNSX());
			n=sta.executeUpdate();
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		finally {
			try {
				sta.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
	

}
