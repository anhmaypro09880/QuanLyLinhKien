package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import connectDB.ConnectDB;
import entity.CTHD;
import entity.HoaDon;
import entity.LinhKien;

public class CTHD_DAO {
	public ArrayList<CTHD> getalltbCTHD(){
		ArrayList<CTHD> dsCTHD = new ArrayList<CTHD>();
		try {
			ConnectDB.getInstance();
			Connection con = ConnectDB.getConnection();
			
			String sql = "Select * from CTHD";
			Statement statement = con.createStatement();
			//Thực thi câu lệnh SQL trả về đối tượng ResultSet
			ResultSet rs = statement.executeQuery(sql);
			//Duyệt trên kết quả trả về
			while(rs.next()) {
				int maCTHD = rs.getInt(1);
				HoaDon hoaDon =new HoaDon(rs.getInt(2)); 
				LinhKien linhKien = new LinhKien(rs.getInt(3)) ;
				int soLuong = rs.getInt(4);
				double donGia = rs.getDouble(5);
				
				CTHD cthd = new CTHD(maCTHD, soLuong, donGia, hoaDon, linhKien);
				dsCTHD.add(cthd);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dsCTHD;
	}
	public boolean create(CTHD cthd) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement stmt = null;
		int n = 0;
		try {
			stmt = con.prepareStatement("insert into CTHD values(?,?,?,?,?)");
			
			stmt.setInt(1, cthd.getMaCTHD());
			stmt.setInt(2, cthd.getHoaDon().getMaHD());
			stmt.setInt(3, cthd.getLinhKien().getMaLk());
			stmt.setInt(4, cthd.getSoLuong());
			stmt.setDouble(5, cthd.getDonGia());
			n = stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return n > 0;
	}
	public boolean delete(int ma) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement stmt = null;
		int n = 0;
		try {
			stmt = con.prepareStatement("delete from CTHD where maCTHD = ?");
			stmt.setInt(1, ma);
			n = stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return n > 0;
	}
	public boolean update(CTHD cthd) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement stmt = null;
		int n = 0;
		try {
			stmt = con.prepareStatement("update CTHD set maHD=?, maLK=?, soluong=?, dongia=? where maCTHD=?");
			
			stmt.setInt(1, cthd.getHoaDon().getMaHD());
			stmt.setInt(2, cthd.getLinhKien().getMaLk());
			stmt.setInt(3, cthd.getSoLuong());
			stmt.setDouble(4, cthd.getDonGia());
			stmt.setInt(5, cthd.getMaCTHD());
			n = stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return n > 0;
	}
}
