package entity;

public class KhachHang {


	private int maKH;
	private String tenKH;
	private int SDTKH;
	private String Email;
	private String diaChi;
	
	public int getMaKH() {
		return maKH;
	}
	public void setMaKH(int maKH) {
		this.maKH = maKH;
	}
	public String getTenKH() {
		return tenKH;
	}
	public void setTenKH(String tenKH) {
		this.tenKH = tenKH;
	}
	public int getSDTKH() {
		return SDTKH;
	}
	public void setSDTKH(int sDTKH) {
		SDTKH = sDTKH;
	}
	public String getEmail() {
		return Email;
	}
	public void setEmail(String email) {
		Email = email;
	}
	public String getDiaChi() {
		return diaChi;
	}
	public void setDiaChi(String diaChi) {
		this.diaChi = diaChi;
	}
	
	public KhachHang(int maKH, String tenKH, int sDTKH, String email, String diaChi) {
		super();
		this.maKH = maKH;
		this.tenKH = tenKH;
		SDTKH = sDTKH;
		Email = email;
		this.diaChi = diaChi;
	}
	public KhachHang(int maKH) {
		super();
		this.maKH = maKH;
	}
	public KhachHang() {
		super();
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + maKH;
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
		KhachHang other = (KhachHang) obj;
		if (maKH != other.maKH)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "KhachHang [maKH=" + maKH + ", tenKH=" + tenKH + ", SDTKH=" + SDTKH + ", Email=" + Email + ", diaChi="
				+ diaChi + "]";
	}
	
	

}
