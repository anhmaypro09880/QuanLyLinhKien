package dao;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;

import connectDB.ConnectDB;
import entity.LinhKien;
import entity.NhaSanXuat;
	




public class LinhKien_DAO implements Serializable {
	public LinhKien_DAO() {
	}
	
	public ArrayList<LinhKien> getalltbLinhKien() {
		ArrayList<LinhKien> dslk = new ArrayList<LinhKien>();
		try {
			ConnectDB.getInstance();
			Connection con = ConnectDB.getConnection();		
			String sql = "Select * from linhkien";
			Statement statement = con.createStatement();
			// Thực thi câu lệnh SQL trả về đối tượng ResultSet.
			ResultSet rs = statement.executeQuery(sql);
			// Duyệt trên kết quả trả về.
			while (rs.next()) {// Di chuyển con trỏ xuống bản ghi kế tiếp.
				int maLk = rs.getInt(1);
				String tenLk = rs.getString(2);
				String loaiLk = rs.getString(3);
				double donGia = rs.getDouble(4);
				String moTa = rs.getString(5);
				String mau = rs.getString(6);
				int soLuong = rs.getInt(7);
				String baoHanh = rs.getString(8);
				String nguonDien = rs.getString(9);
				Date ngay = rs.getDate(10);
				String nuocSX = rs.getString(11);
				NhaSanXuat nsx = new NhaSanXuat(rs.getInt(12));
				LinhKien lk = new LinhKien(maLk, tenLk, loaiLk, donGia, moTa, mau, soLuong, baoHanh, nguonDien, ngay, nuocSX, nsx); 
				dslk.add(lk);
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Sai lấy tất cả lk");
		}
		return dslk;
	}
	
	public ArrayList<LinhKien> getNhanVienTheoMaLK(int id) {
		ArrayList<LinhKien> dslk = new ArrayList<LinhKien>();
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement statement =null;
		try {						
			String sql = " Select * from linhkien where malk = ? ";
			statement=con.prepareStatement(sql);
			statement.setInt(1, id);
			// Thực thi câu lệnh SQL trả về đối tượng ResultSet.
			ResultSet rs = statement.executeQuery(sql);
			// Duyệt trên kết quả trả về.
			while (rs.next()) {// Di chuyển con trỏ xuống bản ghi kế tiếp.
				int maLk = rs.getInt(1);
				String tenLk = rs.getString(2);
				String loaiLk = rs.getString(3);
				double donGia = rs.getDouble(4);
				String moTa = rs.getString(5);
				String mau = rs.getString(6);
				int soLuong = rs.getInt(7);
				String baoHanh = rs.getString(8);
				String nguonDien = rs.getString(9);
				Date ngay = rs.getDate(10);
				String nuocSX = rs.getString(11);
				NhaSanXuat nsx = new NhaSanXuat(rs.getInt(12));
				LinhKien lk = new LinhKien(maLk, tenLk, loaiLk, donGia, moTa, mau, soLuong, baoHanh, nguonDien, ngay, nuocSX, nsx); 
				dslk.add(lk);
				
			}
		} catch (SQLException e) {e.printStackTrace();				}
		finally {
			try {				statement.close();
			} catch (SQLException e) {				e.printStackTrace();	System.out.println("Sai where mlk");	}		}
		return dslk;
	}
	public ArrayList<LinhKien> getNhanVienTheoMaNSX(int id) {
		ArrayList<LinhKien> dslk = new ArrayList<LinhKien>();
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement statement =null;
		try {						
			String sql = " Select * from linhkien where maNSX = ?";
			statement=con.prepareStatement(sql);
			statement.setInt(1, id);
			// Thực thi câu lệnh SQL trả về đối tượng ResultSet.
			ResultSet rs = statement.executeQuery(sql);
			// Duyệt trên kết quả trả về.
			while (rs.next()) {// Di chuyển con trỏ xuống bản ghi kế tiếp.
				int maLk = rs.getInt(1);
				String tenLk = rs.getString(2);
				String loaiLk = rs.getString(3);
				double donGia = rs.getDouble(4);
				String moTa = rs.getString(5);
				String mau = rs.getString(6);
				int soLuong = rs.getInt(7);
				String baoHanh = rs.getString(8);
				String nguonDien = rs.getString(9);
				Date ngay = rs.getDate(10);
				String nuocSX = rs.getString(11);
				NhaSanXuat nsx = new NhaSanXuat(rs.getInt(12));
				LinhKien lk = new LinhKien(maLk, tenLk, loaiLk, donGia, moTa, mau, soLuong, baoHanh, nguonDien, ngay, nuocSX, nsx); 
				dslk.add(lk);
			}
		} catch (SQLException e) {		
			e.printStackTrace();		
		}
		finally {
			try {	
				statement.close();
			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("Sai Select * From Where");
			}					
		}
		return dslk;
	}
	public boolean create(LinhKien lk) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement stmt = null;
		int n = 0;
		try {
			stmt = con.prepareStatement("insert into"
					+ " linhkien values(?, ?, ?, ?, ?, ?,?,?,?,?,? , ?)");
			stmt.setInt(1,lk.getMaLk());
			stmt.setString(2,lk.getTenLk());
			stmt.setString(3, lk.getLoaiLk());
			stmt.setDouble(4,lk.getDonGia());
			stmt.setString(5, lk.getMoTa());
			stmt.setString(6, lk.getMau());
			stmt.setInt(7, lk.getSoLuong());
			stmt.setString(8,lk.getBaoHanh());
			stmt.setString(9, lk.getNguonDienYeuCau());
			stmt.setDate(10, (Date) lk.getNgayNhap());
			stmt.setString(11, lk.getNuocSx());
			stmt.setInt(12, lk.getMaNSX().getMaNSX());		
			n = stmt.executeUpdate();						
		} catch (SQLException e) {			
			e.printStackTrace();
		}
		finally {
			try {
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println("Sai Tao Moi");
			}
		}
		return n > 0; 
	}
	

	public boolean delete(int maLk) {
		// TODO Auto-generated method stub
		ConnectDB.getInstance();
		Connection con = ConnectDB.getInstance().getConnection();
		PreparedStatement stmt=null;
		int n=0;
		try {
		stmt=con.prepareStatement("delete from linhkien where maLk=?");
			stmt.setInt(1, maLk);
			n=stmt.executeUpdate();
		}catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return n>0;
	}
	public boolean update(LinhKien lk) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getInstance().getConnection();
		PreparedStatement stmt=null;
		int n=0;
		try {
			stmt=con.prepareStatement("update linhkien set tenLk=?, loaiLk=?, donGia=?,moTa=?, mau=?, soLuong=?, baoHanh=?, nguonDienYeuCau=?, ngayNhap=?, nuocSX=?, maNSX = ? where maLk = ?");
			
			stmt.setString(1, lk.getTenLk());
			stmt.setString(2, lk.getLoaiLk());
			stmt.setDouble(3, lk.getDonGia());
			stmt.setString(4, lk.getMoTa());
			stmt.setString(5, lk.getMau());
			stmt.setInt(6, lk.getSoLuong());
			stmt.setString(7, lk.getBaoHanh());
			stmt.setString(8, lk.getNguonDienYeuCau());
			stmt.setDate(9, lk.getNgayNhap());
			stmt.setString(10, lk.getNuocSx());
			stmt.setInt(11, lk.getMaNSX().getMaNSX());
			
			stmt.setInt(12, lk.getMaLk());
			n=stmt.executeUpdate();
		}catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return n>0;
	}
	
}
