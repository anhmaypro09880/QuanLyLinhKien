package entity;

public class CTHD {
	private int maCTHD;
	private int soLuong;
	private double donGia;
	
	private HoaDon hoaDon;
	private LinhKien linhKien;
	public int getMaCTHD() {
		return maCTHD;
	}
	public void setMaCTHD(int maCTHD) {
		this.maCTHD = maCTHD;
	}
	public int getSoLuong() {
		return soLuong;
	}
	public void setSoLuong(int soLuong) {
		this.soLuong = soLuong;
	}
	public double getDonGia() {
		return donGia;
	}
	public void setDonGia(double donGia) {
		this.donGia = donGia;
	}
	public HoaDon getHoaDon() {
		return hoaDon;
	}
	public void setHoaDon(HoaDon hoaDon) {
		this.hoaDon = hoaDon;
	}
	public LinhKien getLinhKien() {
		return linhKien;
	}
	public void setLinhKien(LinhKien linhKien) {
		this.linhKien = linhKien;
	}
	public CTHD(int maCTHD, int soLuong, double donGia, HoaDon hoaDon, LinhKien linhKien) {
		super();
		this.maCTHD = maCTHD;
		this.soLuong = soLuong;
		this.donGia = donGia;
		this.hoaDon = hoaDon;
		this.linhKien = linhKien;
	}
	public CTHD(int maCTHD) {
		super();
		this.maCTHD = maCTHD;
	}
	public CTHD() {
		super();
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + maCTHD;
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
		CTHD other = (CTHD) obj;
		if (maCTHD != other.maCTHD)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "CTHD [maCTHD=" + maCTHD + ", soLuong=" + soLuong + ", donGia=" + donGia + ", hoaDon=" + hoaDon
				+ ", linhKien=" + linhKien + "]";
	}
	
}
