package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import connectDB.ConnectDB;
import entity.KhachHang;

public class KhachHang_DAO {
	public KhachHang_DAO() {	
	}
	public ArrayList<KhachHang> gettalltbKhachHang() {
		ArrayList<KhachHang> dskh = new ArrayList<KhachHang>();
		try {
			ConnectDB.getInstance();
			Connection con = ConnectDB.getConnection();
			String sql = "Select * from KhachHang";
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			while (rs.next()) {
				int maKH = rs.getInt(1);
				String tenKH = rs.getString(2);
				int SDTKH =  rs.getInt(3);
				String Email =  rs.getString(4);
				String diaChi =  rs.getString(5);
				KhachHang kh = new KhachHang(maKH,tenKH,SDTKH,Email,diaChi);
				dskh.add(kh);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dskh;
	}
	public ArrayList<KhachHang> getKhachHangTheoMaKH(int id) {
		ArrayList<KhachHang> dskh = new ArrayList<KhachHang>();
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement statement = null;
		try {
			String sql = "Select * from KhachHang where maKH = ?";
			statement = con .prepareStatement(sql);
			statement.setInt(1, id);
			ResultSet rs = statement.executeQuery(sql);
			while (rs.next()) {
				int maKH = rs.getInt(1);
				String tenKH = rs.getString(2);
				int SDTKH =  rs.getInt(3);
				String Email =  rs.getString(4);
				String diaChi =  rs.getString(5);
				KhachHang kh = new KhachHang(maKH,tenKH,SDTKH,Email,diaChi);
				dskh.add(kh);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			try {
				statement.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			return dskh;
		}
	}
	public boolean create(KhachHang kh) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement stmt = null;
		int n = 0;
		try {
			stmt = con.prepareStatement("insert into" + " KhachHang values(?, ?, ?, ?, ?)");
			stmt.setInt(1,kh.getMaKH());
			stmt.setString(2,kh.getTenKH());
			stmt.setInt(3,kh.getSDTKH());
			stmt.setString(4,kh.getEmail());
			stmt.setString(5,kh.getDiaChi());
			n=stmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			try {
				stmt.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return n > 0;
	}
	public boolean XoaKH(int maKH)
	{
		ConnectDB.getInstance();
		Connection con =ConnectDB.getConnection();
		PreparedStatement stmt =null;
		int n=0;
		try {
			stmt=con.prepareStatement("delete from KhachHang where maKH=?");
			stmt.setInt(1, maKH);
			n=stmt.executeUpdate();
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		finally {
			try {
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	public boolean update(KhachHang kh) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement stmt = null;
		int n = 0;
		try {
			stmt = con.prepareStatement("update KhachHang set tenKH=?," + "SDT=?, Email=?, diaChi=?" + " Where maKH=?");
			
			stmt.setString(1,kh.getTenKH());
			stmt.setInt(2,kh.getSDTKH());
			stmt.setString(3,kh.getEmail());
			stmt.setString(4,kh.getDiaChi());
			stmt.setInt(5,kh.getMaKH());
			
			n=stmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			try {
				stmt.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return n > 0;
	}
}
