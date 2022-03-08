package entity;

public class NhanVien {
	private int maNV;
	private String tenNV;
	private int SDTNV;
	private double luong;
	private String diaChi;
	public int getMaNV() {
		return maNV;
	}
	public void setMaNV(int maNV) {
		this.maNV = maNV;
	}
	public String getTenNV() {
		return tenNV;
	}
	public void setTenNV(String tenNV) {
		this.tenNV = tenNV;
	}
	public int getSDTNV() {
		return SDTNV;
	}
	public void setSDTNV(int sDTNV) {
		SDTNV = sDTNV;
	}
	public double getLuong() {
		return luong;
	}
	public void setLuong(double luong) {
		this.luong = luong;
	}
	public String getDiaChi() {
		return diaChi;
	}
	public void setDiaChi(String diaChi) {
		this.diaChi = diaChi;
	}
	public NhanVien(int maNV, String tenNV, int sDTNV, double luong, String diaChi) {
		super();
		this.maNV = maNV;
		this.tenNV = tenNV;
		SDTNV = sDTNV;
		this.luong = luong;
		this.diaChi = diaChi;
	}
	public NhanVien(int maNV) {
		super();
		this.maNV = maNV;
	}
	public NhanVien() {
		super();
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + maNV;
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
		NhanVien other = (NhanVien) obj;
		if (maNV != other.maNV)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "NhanVien [maNV=" + maNV + ", tenNV=" + tenNV + ", SDTNV=" + SDTNV + ", luong=" + luong + ", diaChi="
				+ diaChi + "]";
	}
	
	
	

}
