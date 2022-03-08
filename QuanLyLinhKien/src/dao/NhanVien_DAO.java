package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import connectDB.ConnectDB;
import entity.NhanVien;

public class NhanVien_DAO {
	public ArrayList<NhanVien> getalltbNhanVien() {
		ArrayList<NhanVien> dsnv = new ArrayList<NhanVien>();
		try {
			ConnectDB.getInstance();
			Connection con = ConnectDB.getConnection();
			
			String sql = " Select * from NhanVien ";
			Statement statement = con.createStatement();
			// Thực thi câu lệnh SQL trả về đối tượng ResultSet.
			ResultSet rs = statement.executeQuery(sql);
			// Duyệt trên kết quả trả về.
			while (rs.next()) {// Di chuyển con trỏ xuống bản ghi kế tiếp.
				int maNV = rs.getInt(1);
				String tenNV = rs.getString(2);
				int SDTNV = rs.getInt(3);
				double luong = rs.getDouble(4);
				String diaChi = rs.getString(5);
				NhanVien nv = new NhanVien(maNV, tenNV, SDTNV, luong, diaChi);
				dsnv.add(nv);
			}
		} catch (SQLException e) {
			e.printStackTrace();		
		}
		return dsnv;
	}
}
