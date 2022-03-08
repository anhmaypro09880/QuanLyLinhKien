package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import java.util.HashSet;

import connectDB.ConnectDB;
import entity.HoaDon;
import entity.KhachHang;
import entity.LinhKien;
import entity.NhanVien;


public class HoaDon_DAO {
	public ArrayList<HoaDon> getalltbHoaDon(){
		ArrayList<HoaDon> dsHD = new ArrayList<HoaDon>();
		try {
			ConnectDB.getInstance();
			Connection con = ConnectDB.getConnection();			
			String sql = "Select * from HoaDon";
			Statement statement = con.createStatement();
			//Thực thi câu lệnh SQL trả về đối tượng ResultSet
			ResultSet rs = statement.executeQuery(sql);
			//Duyệt trên kết quả trả về
			while(rs.next()) {
				int maHD = rs.getInt(1);
				NhanVien nhanVien = new  NhanVien(rs.getInt(2));
				KhachHang khachHang = new KhachHang(rs.getInt(3));
				float laiSuatThue = rs.getFloat(4);
				java.util.Date ngayLap = rs.getDate(5); 
				HoaDon hoaDon = new HoaDon(maHD, ngayLap, laiSuatThue, khachHang, nhanVien);
				dsHD.add(hoaDon);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dsHD;
	}	

	public boolean create(HoaDon hd) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement stmt = null;
		int n = 0;
		try {
			stmt = con.prepareStatement("insert into HoaDon values(?,?,?,?,?)");
			
			stmt.setInt(1, hd.getMaHD());
			stmt.setInt(2, hd.getNhanVien().getMaNV());
			stmt.setInt(3, hd.getKhachHang().getMaKH());
			stmt.setFloat(4, hd.getLaiSuatThue());
			stmt.setDate(5, (Date) hd.getNgayLap());
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
			stmt = con.prepareStatement("delete from CTHD where maHD=? delete from HoaDon where maHD = ? ");
			stmt.setInt(1, ma);
			stmt.setInt(2, ma);
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
	public boolean update(HoaDon hd) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement stmt = null;
		int n = 0;
		try {
			stmt = con.prepareStatement("update HoaDon set maNV=?, maKH=?, laiSuatThue=?, ngayLap=? where maHD=?");
			
			stmt.setInt(1, hd.getNhanVien().getMaNV());
			stmt.setInt(2, hd.getKhachHang().getMaKH());
			stmt.setFloat(3, hd.getLaiSuatThue());
			stmt.setDate(4, (Date) hd.getNgayLap());
			stmt.setInt(5, hd.getMaHD());
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
