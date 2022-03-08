package entity;

import java.io.Serializable;
import java.sql.Date;
import java.time.LocalDate;

public class LinhKien implements Serializable{
		private int maLk;
		private String tenLk;
		private String loaiLk;
		private double donGia;
		private String moTa;
		private String mau;
		private int soLuong;
		private String baoHanh;
		private String nguonDienYeuCau;
		private Date ngayNhap;
		private String nuocSx;
		private NhaSanXuat maNSX;
		public LinhKien(int maLk, String tenLk, String loaiLk, double donGia, String moTa, String mau, int soLuong,
				String baoHanh, String nguonDienYeuCau, Date ngaynhapkho, String nuocSx, NhaSanXuat maNSX) {
			super();
			this.maLk = maLk;
			this.tenLk = tenLk;
			this.loaiLk = loaiLk;
			this.donGia = donGia;
			this.moTa = moTa;
			this.mau = mau;
			this.soLuong = soLuong;
			this.baoHanh = baoHanh;
			this.nguonDienYeuCau = nguonDienYeuCau;
			this.ngayNhap = ngaynhapkho;
			this.nuocSx = nuocSx;
			this.maNSX = maNSX;
		}
		public LinhKien(int maLk) {
			super();
			this.maLk = maLk;
		}
		public LinhKien() {
			super();
		}
		public int getMaLk() {
			return maLk;
		}
		public void setMaLk(int maLk) {
			this.maLk = maLk;
		}
		public String getTenLk() {
			return tenLk;
		}
		public void setTenLk(String tenLk) {
			this.tenLk = tenLk;
		}
		public String getLoaiLk() {
			return loaiLk;
		}
		public void setLoaiLk(String loaiLk) {
			this.loaiLk = loaiLk;
		}
		public double getDonGia() {
			return donGia;
		}
		public void setDonGia(double donGia) {
			this.donGia = donGia;
		}
		public String getMoTa() {
			return moTa;
		}
		public void setMoTa(String moTa) {
			this.moTa = moTa;
		}
		public String getMau() {
			return mau;
		}
		public void setMau(String mau) {
			this.mau = mau;
		}
		public int getSoLuong() {
			return soLuong;
		}
		public void setSoLuong(int soLuong) {
			this.soLuong = soLuong;
		}
		public String getBaoHanh() {
			return baoHanh;
		}
		public void setBaoHanh(String baoHanh) {
			this.baoHanh = baoHanh;
		}
		public String getNguonDienYeuCau() {
			return nguonDienYeuCau;
		}
		public void setNguonDienYeuCau(String nguonDienYeuCau) {
			this.nguonDienYeuCau = nguonDienYeuCau;
		}
		public Date getNgayNhap() {
			return ngayNhap;
		}
		public void setNgayNhap(Date ngayNhap) {
			this.ngayNhap = ngayNhap;
		}
		public String getNuocSx() {
			return nuocSx;
		}
		public void setNuocSx(String nuocSx) {
			this.nuocSx = nuocSx;
		}
		public NhaSanXuat getMaNSX() {
			return maNSX;
		}
		public void setMaNSX(NhaSanXuat maNSX) {
			this.maNSX = maNSX;
		}
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + maLk;
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
			LinhKien other = (LinhKien) obj;
			if (maLk != other.maLk)
				return false;
			return true;
		}
		@Override
		public String toString() {
			return "LinhKien [maLk=" + maLk + ", tenLk=" + tenLk + ", loaiLk=" + loaiLk + ", donGia=" + donGia
					+ ", moTa=" + moTa + ", mau=" + mau + ", soLuong=" + soLuong + ", baoHanh=" + baoHanh
					+ ", nguonDienYeuCau=" + nguonDienYeuCau + ", ngayNhap=" + ngayNhap + ", nuocSx=" + nuocSx
					+ ", maNSX=" + maNSX + "]";
		}
		
		
		
}
