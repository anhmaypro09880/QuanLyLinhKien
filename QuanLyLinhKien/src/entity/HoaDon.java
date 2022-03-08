package entity;

import java.util.Date;

public class HoaDon {
	private int maHD;
	private Date ngayLap;
	private float laiSuatThue;
	
	private KhachHang khachHang;
	private NhanVien nhanVien;
	
	public int getMaHD() {
		return maHD;
	}
	public void setMaHD(int maHD) {
		this.maHD = maHD;
	}
	public Date getNgayLap() {
		return ngayLap;
	}
	public void setNgayLap(Date ngayLap) {
		this.ngayLap = ngayLap;
	}
	public float getLaiSuatThue() {
		return laiSuatThue;
	}
	public void setLaiSuatThue(float laiSuatThue) {
		this.laiSuatThue = laiSuatThue;
	}
	public KhachHang getKhachHang() {
		return khachHang;
	}
	public void setKhachHang(KhachHang khachHang) {
		this.khachHang = khachHang;
	}
	public NhanVien getNhanVien() {
		return nhanVien;
	}
	public void setNhanVien(NhanVien nhanVien) {
		this.nhanVien = nhanVien;
	}
	public HoaDon(int maHD, Date ngayLap, float laiSuatThue, KhachHang khachHang, NhanVien nhanVien) {
		super();
		this.maHD = maHD;
		this.ngayLap = ngayLap;
		this.laiSuatThue = laiSuatThue;
		this.khachHang = khachHang;
		this.nhanVien = nhanVien;
	}
	public HoaDon(int maHD) {
		super();
		this.maHD = maHD;
	}
	public HoaDon() {
		super();
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + maHD;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		HoaDon other = (HoaDon) obj;
		if (maHD != other.maHD)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "HoaDon [maHD=" + maHD + ", ngayLap=" + ngayLap + ", laiSuatThue=" + laiSuatThue + ", khachHang="
				+ khachHang + ", nhanVien=" + nhanVien + "]";
	}
	
}
