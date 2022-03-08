package entity;

public class NhaSanXuat {
	private int maNSX;
	private String tenNSX;
	private String diaChi;
	private int soDT;
	public int getMaNSX() {
		return maNSX;
	}
	public void setMaNSX(int maNSX) {
		this.maNSX = maNSX;
	}
	public String getTenNSX() {
		return tenNSX;
	}
	public void setTenNSX(String tenNSX) {
		this.tenNSX = tenNSX;
	}
	public String getDiaChi() {
		return diaChi;
	}
	public void setDiaChi(String diaChi) {
		this.diaChi = diaChi;
	}
	public int getSoDT() {
		return soDT;
	}
	public void setSoDT(int soDT) {
		this.soDT = soDT;
	}
	public NhaSanXuat(int maNSX, String tenNSX, String diaChi, int soDT) {
		super();
		this.maNSX = maNSX;
		this.tenNSX = tenNSX;
		this.diaChi = diaChi;
		this.soDT = soDT;
	}
	public NhaSanXuat(int maNSX) {
		super();
		this.maNSX = maNSX;
	}
	public NhaSanXuat() {
		super();
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + maNSX;
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
		NhaSanXuat other = (NhaSanXuat) obj;
		if (maNSX != other.maNSX)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "nhasanxuat [maNSX=" + maNSX + ", tenNSX=" + tenNSX + ", diaChi=" + diaChi + ", soDT=" + soDT + "]";
	}
	
}
