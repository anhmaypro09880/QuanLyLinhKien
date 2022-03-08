package ungDung_GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.ComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import connectDB.ConnectDB;
import dao.LinhKien_DAO;
import dao.NhaSanXuat_DAO;
import entity.LinhKien;
import entity.NhaSanXuat;

public class FrmLinhKien extends JFrame implements ActionListener, MouseListener {
		private JLabel lblMaLinhKien;
		private JTextField txtMaLinhKien;
		private JLabel lblTenLinhKien;
		private JTextField txtTenLinhKien;
		private JLabel lblDonGia;
		private JTextField txtDonGia;
		private JLabel lblMoTa;
		private JTextField txtMoTa;
		private JLabel lblSoLuongTon;
		private JTextField txtSoLuongTon;
		private JLabel lblLoaiLinhKien;
		private JTextField txtLoaiLinhKien;
		private JLabel lblthoiGianSanXuat;
		private JTextField txtthoiGianSanXuat;
		private JLabel lblnguonDienYeuCau;
		private JTextField txtnguonDienYeuCau;
		private JLabel lblngayNhapKho;
		private JTextField txtngayNhapKho;
		private JLabel lblnuocSanXuat;
		private JTextField txtnuocSanXuat;
		private JLabel lblMau;
		private JTextField txtMau;
		private JButton btnThem;
		private JButton btnXoa;
		private JButton btnSua;
		private JButton btnXoaTrang;
		
		private JComboBox cbxTim;
		private JTextField txtTim;
		private JButton btnTim;
		private DefaultTableModel dataModel;
		private JTable table;
		private JLabel lblnhaSanXuat;
		private JComboBox cbonhaSX;
		private ComboBoxModel cbxStr;
		private JLabel lblBaoHanh;
		private JTextField txtBaoHanh;
		private LinhKien_DAO lk_dao;
		private NhaSanXuat_DAO nhaSanXuat_DAO;
		private JPanel pback;
		private JButton btnback;
	
		public FrmLinhKien() {
			try {
				ConnectDB.getInstance().connect();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			lk_dao = new LinhKien_DAO();
			nhaSanXuat_DAO = new NhaSanXuat_DAO();
			
			pback=new JPanel();
			pback.setBounds(10, 0, 100,50);
			pback.add(btnback=new JButton("Back"));
			
			ImageIcon imageIcon1 = new ImageIcon("Image/Back.png");
			Image image1 =imageIcon1.getImage();
			Image imageResize1 = image1.getScaledInstance(25, 25, Image.SCALE_SMOOTH);
			
			btnback.setIcon(new ImageIcon(imageResize1));
			add(pback);
			
			JPanel pnlBanner=new JPanel();
			add(pnlBanner);
			pnlBanner.setBounds(0, 50, 1055, 30);
			JLabel lblTieuDe;
			pnlBanner.add(lblTieuDe=new JLabel("QUẢN LÝ LINH KIỆN"));
			lblTieuDe.setFont(new Font("arial",Font.BOLD,20));
			lblTieuDe.setForeground(Color.RED);
			
			JPanel pnlContent=new JPanel();
			add(pnlContent);
			pnlContent.setBounds(0,80,1055,320);
			pnlContent.setLayout(null);
			
			pnlContent.add(lblMaLinhKien=new JLabel("Mã linh kiện:"));
			pnlContent.add(txtMaLinhKien=new JTextField());
			txtMaLinhKien.setText("12345");
			pnlContent.add(lblTenLinhKien=new JLabel("Tên linh kiện:"));
			pnlContent.add(txtTenLinhKien=new JTextField());
			txtTenLinhKien.setText("Ban Phim");
			pnlContent.add(lblLoaiLinhKien=new JLabel("Loại linh kiện:"));
			pnlContent.add(txtLoaiLinhKien=new JTextField());
			txtLoaiLinhKien.setText("May Tinh");
			pnlContent.add(lblDonGia=new JLabel("Đơn giá:"));
			pnlContent.add(txtDonGia=new JTextField());
			txtDonGia.setText("120000");
			pnlContent.add(lblMoTa=new JLabel("Mô tả:"));
			pnlContent.add(txtMoTa=new JTextField());
			txtMoTa.setText("Cam");
			pnlContent.add(txtnuocSanXuat=new JTextField());
			pnlContent.add(lblMau=new JLabel("Màu:"));
			pnlContent.add(txtMau=new JTextField());
			txtMau.setText("Xanh");
			pnlContent.add(lblSoLuongTon=new JLabel("Số lượng:"));
			pnlContent.add(txtSoLuongTon=new JTextField());
			txtSoLuongTon.setText("12");
			
			pnlContent.add(lblBaoHanh=new JLabel("Bảo Hành:"));
			pnlContent.add(txtBaoHanh=new JTextField());
			txtBaoHanh.setText("2 nam");
			pnlContent.add(lblnguonDienYeuCau=new JLabel("Nguồn điện:"));
			pnlContent.add(txtnguonDienYeuCau=new JTextField());
			txtnguonDienYeuCau.setText("105W");
			pnlContent.add(lblngayNhapKho=new JLabel("Ngày nhập kho:"));
			pnlContent.add(txtngayNhapKho=new JTextField());
			txtngayNhapKho.setText("24/05/2021");
			pnlContent.add(lblnuocSanXuat=new JLabel("Nước SX:"));
			pnlContent.add(lblnhaSanXuat = new JLabel("Nhà SX:"));
			txtnuocSanXuat.setText("VietNam");
			pnlContent.add(cbonhaSX = new JComboBox<>());
			
			
			
			
			
			pnlContent.add(btnThem=new JButton("Thêm"));
			pnlContent.add(btnXoa=new JButton("Xóa"));
			pnlContent.add(btnSua=new JButton("Sửa"));
			pnlContent.add(btnXoaTrang=new JButton("Xóa trắng"));
			
			
			int w1=100, w2=300, h=25;
			lblMaLinhKien.setBounds(50,100, w1, h);txtMaLinhKien.setBounds(150,100, w2, h);
			lblTenLinhKien.setBounds(550, 100, w1, h);txtTenLinhKien.setBounds(650, 100, w2, h);
			
			lblLoaiLinhKien.setBounds(50, 150, w1, h);txtLoaiLinhKien.setBounds(150, 150, w2, h);
			lblDonGia.setBounds(550, 150, w1, h);txtDonGia.setBounds(650, 150, w2, h);
			
			lblMoTa.setBounds(50, 200, w1, h);txtMoTa.setBounds(150, 200, w2, h);
			lblMau.setBounds(550, 200, w1, h);txtMau.setBounds(650, 200, w2, h);
			
			lblSoLuongTon.setBounds(50, 250, w1, h);txtSoLuongTon.setBounds(150, 250, w2, h);
			lblBaoHanh.setBounds(550, 250, w1, h);txtBaoHanh.setBounds(650, 250, w2, h);
			
			lblnguonDienYeuCau.setBounds(50, 300, w1, h);txtnguonDienYeuCau.setBounds(150, 300, w2, h);
			lblngayNhapKho.setBounds(550, 300, w1, h);txtngayNhapKho.setBounds(650, 300, w2, h);
			
			lblnuocSanXuat.setBounds(50, 350, w1, h);txtnuocSanXuat.setBounds(150, 350, w2, h);
			lblnhaSanXuat.setBounds(550, 350, w1, h);cbonhaSX.setBounds(650, 350, w2, h);
			
			JSplitPane split=new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
			pnlContent.add(split);
			split.setBounds(0, 395, 1055, 40);
			JPanel pnlRight,pnlLeft;
			split.add(pnlLeft=new JPanel());
			split.add(pnlRight=new JPanel());
			
			
//			String[] cbxStr = {"Mã Linh Kiện","Tên Linh Kiện"};
			cbxTim = new JComboBox();
			cbxTim.addItem("Mã linh kiện");
			cbxTim.addItem("Tên linh kiện");
			pnlLeft.add(txtTim=new JTextField(20));
			txtTim.setEnabled(true);
			txtTim.setDisabledTextColor(Color.GRAY);
			pnlLeft.add(cbxTim);
			pnlLeft.add(btnTim=new JButton("Tìm"));
			pnlRight.add(btnThem);
			pnlRight.add(btnXoa);
			pnlRight.add(btnXoaTrang);
			pnlRight.add(btnSua);
			
			
			
			ArrayList<NhaSanXuat> listNSX = nhaSanXuat_DAO.getallnsx();
			for(NhaSanXuat p : listNSX) {
				cbonhaSX.addItem(p.getMaNSX());
				
			}

			
			JScrollPane scroll;
			String[] header= {"Mã Linh Kiện","Tên linh kiện","Loại linh kiện","Đơn giá","Mô tả","Màu","Số lượng","Thời gian sản xuất","Nguồn điện","Ngày nhập kho","Nước SX","Mã NSX"};
			dataModel=new DefaultTableModel(header,0);
			add(scroll=new JScrollPane(table=new JTable(dataModel),JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED),BorderLayout.SOUTH);
			scroll.setBorder(BorderFactory.createTitledBorder("Danh Sách Linh Kiện"));
			table.setRowHeight(20);
			scroll.setPreferredSize(new Dimension(0,285));
			
			DocDuLieuTuDataBase();
			
			btnThem.addActionListener(this);
			btnXoa.addActionListener(this);
			btnXoaTrang.addActionListener(this);
			btnSua.addActionListener(this);
			btnTim.addActionListener(this);
			btnback.addActionListener(this);
			
			btnThem.setMnemonic(KeyEvent.VK_T);
			btnXoa.setMnemonic(KeyEvent.VK_X);
			btnXoaTrang.setMnemonic(KeyEvent.VK_R);
			btnSua.setMnemonic(KeyEvent.VK_S);
			btnTim.setMnemonic(KeyEvent.VK_F);
			
			table.addMouseListener(this);
			setTitle("Quản lý linh kiện");
			setSize(1055,780);
			setDefaultCloseOperation(EXIT_ON_CLOSE);
			setVisible(true);
			setLocationRelativeTo(null);
		}
		public static void main(String[] args) {
			new FrmLinhKien().setVisible(true);
		}
		
		public void DocDuLieuTuDataBase() {
			ArrayList<LinhKien> listLk = lk_dao.getalltbLinhKien();
			for(int i =0 ; i<listLk.size();i++) {
					LinhKien  lk = listLk.get(i);
					dataModel.addRow(new Object[] { lk.getMaLk(),lk.getTenLk(),lk.getLoaiLk(),lk.getDonGia(),lk.getMoTa(),
					lk.getMau(),lk.getSoLuong(),lk.getBaoHanh(),lk.getNguonDienYeuCau(),lk.getNgayNhap(),lk.getNuocSx(),lk.getMaNSX().getMaNSX()
						
					});
			}
		}
		public void Sua() {
			int row = table.getSelectedRow();
			if(row != -1){						 
				int ma = Integer.parseInt(txtMaLinhKien.getText());
				String tenLk = txtTenLinhKien.getText();
				double dongia = Double.parseDouble(txtDonGia.getText());
				String maNSX = cbonhaSX.getSelectedItem().toString();
				String loaiLk = txtLoaiLinhKien.getText();
				
				String baohanh = txtBaoHanh.getText();
				String nguonDien = txtnguonDienYeuCau.getText();
				String moTa = txtMoTa.getText();
				String mau = txtMau.getText();
				int soLuong = Integer.parseInt(txtSoLuongTon.getText());
				String nuocSX = txtnuocSanXuat.getText();
				
				DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
				java.util.Date ngaynhapkho = null;
				java.sql.Date ngaynhap = null;
				try {
					ngaynhapkho =  df.parse(txtngayNhapKho.getText());
					ngaynhap =  new java.sql.Date(ngaynhapkho.getYear(),ngaynhapkho.getMonth(),ngaynhapkho.getDate());
				}catch (ParseException e1) {		
					}
				int nhaSx = Integer.parseInt(cbonhaSX.getSelectedItem().toString()) ;
				NhaSanXuat nsx = new NhaSanXuat(nhaSx);
					LinhKien lk = new LinhKien(ma, tenLk, loaiLk,dongia, moTa, mau, soLuong, baohanh, nguonDien, ngaynhap,nuocSX , nsx);
					if(lk_dao.update(lk)) {
						dataModel.setValueAt(tenLk, row,1);
						dataModel.setValueAt(loaiLk, row,2);
						dataModel.setValueAt(dongia, row,3);
						dataModel.setValueAt(moTa, row,4);
						dataModel.setValueAt(mau, row,5);
						dataModel.setValueAt(soLuong, row,6);
						dataModel.setValueAt(baohanh, row,7);
						dataModel.setValueAt(nguonDien, row,8);
						dataModel.setValueAt(ngaynhap, row,9);
						dataModel.setValueAt(nuocSX, row,10);
						dataModel.setValueAt(nsx.getMaNSX(), row,11);
						
						JOptionPane.showMessageDialog(this, "Sửa thành công");
					}			
				}
			else {
				JOptionPane.showMessageDialog(this, "Chọn dòng cần sửa");	
			}
		}
		public void tim() {
			String maTim = txtTim.getText();
			table.clearSelection();
		
			String ktma = "";
			for (int i = 0; i < table.getRowCount(); i++) {
				ktma = table.getValueAt(i, 0).toString();
				if(ktma.equals(maTim)){
					table.addRowSelectionInterval(i, i);
					break;
				}
			}
			if(!(table.getSelectedRow() != -1 )) {
				JOptionPane.showMessageDialog(this, "Không tìm thấy");
			}
			
		}
		public void xoa() {
			int yn = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn","Chú ý",JOptionPane.YES_NO_OPTION);
			if (yn == JOptionPane.YES_OPTION) {
				int row = table.getSelectedRow();
				if (row >0) {
					int maLk = (int) table.getValueAt(row, 0);
					if(lk_dao.delete(maLk)) {
						dataModel.removeRow(row);
					}
				}
			}
		}
		public void actionPerformed(ActionEvent e) {
			Object o = e.getSource();
			if(o.equals(btnThem)) {
				if(KiemTraDuLieu()) {
					int ma = Integer.parseInt(txtMaLinhKien.getText());
					String tenLk = txtTenLinhKien.getText();
					double dongia = Double.parseDouble(txtDonGia.getText());
					String maNSX = cbonhaSX.getSelectedItem().toString();
					String loaiLk = txtLoaiLinhKien.getText();
					String nguondien = txtnguonDienYeuCau.getText();
					String baohanh = txtBaoHanh.getText();
					String nguonDien = txtnguonDienYeuCau.getText();
					String moTa = txtMoTa.getText();
					String mau = txtMau.getText();
					int soLuong = Integer.parseInt(txtSoLuongTon.getText());
					
					DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
					java.util.Date ngaynhapkho = null;
					java.sql.Date ngaynhap = null;
					try {
						ngaynhapkho =  df.parse(txtngayNhapKho.getText());
						ngaynhap =  new java.sql.Date(ngaynhapkho.getYear(),ngaynhapkho.getMonth(),ngaynhapkho.getDate());
					} catch (ParseException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
						System.out.println("Sai định dạng ngày");
					}
					String nuocSx = txtnuocSanXuat.getText();
					int nhaSx = Integer.parseInt(cbonhaSX.getSelectedItem().toString()) ;
					NhaSanXuat nsx = new NhaSanXuat(nhaSx);
					
					LinhKien lk = new LinhKien(ma, tenLk, loaiLk, dongia, moTa, mau, soLuong, baohanh, nguonDien,ngaynhap , nuocSx, nsx);
					
					int ktma,flag=0 ;
					for (int i = 0; i < table.getRowCount(); i++) {
						ktma = Integer.parseInt(table.getValueAt(i, 0).toString());
						if(ktma==lk.getMaLk()){
							JOptionPane.showMessageDialog(this, "Trùng mã");
							flag=1;
							break;
						}
					}
					if(flag==0) {
						lk_dao.create(lk);
						dataModel.addRow(new Object[] {
								lk.getMaLk(),lk.getTenLk(),lk.getLoaiLk(),lk.getDonGia(),lk.getMoTa(),lk.getMau(),lk.getSoLuong(),
								lk.getBaoHanh(),lk.getNguonDienYeuCau(),lk.getNgayNhap(),lk.getNuocSx(),lk.getMaNSX().getMaNSX()
						});
					}	
				
				}
			}
			else  if (o.equals(btnXoaTrang)) {
				txtMaLinhKien.setText("");
				txtTenLinhKien.setText("");
				txtLoaiLinhKien.setText("");
				txtMoTa.setText("");
				txtMau.setText("");
				txtSoLuongTon.setText("");
				txtDonGia.setText("");
				cbonhaSX.setSelectedItem(null);
				txtBaoHanh.setText("");
				txtnguonDienYeuCau.setText("");
				txtngayNhapKho.setText("");
				txtnuocSanXuat.setText("");
				txtMaLinhKien.setEditable(true);
				txtMaLinhKien.requestFocus();
			}
			else if(o.equals(btnXoa)) {
				xoa();
			}
			else if(o.equals(btnSua)) {
				Sua();
			}
			else if(o.equals(btnTim)) {
				tim();
			}	
			else if(o.equals(btnback)) {
				new GiaoDienChinh().setVisible(true);
				setVisible(false);
			}
		}
		@Override
		public void mouseClicked(MouseEvent e) {
			int row = table.getSelectedRow();
			txtMaLinhKien.setText(dataModel.getValueAt(row, 0).toString());
			txtTenLinhKien.setText(dataModel.getValueAt(row, 1).toString());
			txtLoaiLinhKien.setText(dataModel.getValueAt(row, 2).toString());
			txtDonGia.setText(dataModel.getValueAt(row, 3).toString());
			txtMoTa.setText(dataModel.getValueAt(row, 4).toString());
			txtMau.setText(dataModel.getValueAt(row, 5).toString());
			txtSoLuongTon.setText(dataModel.getValueAt(row, 6).toString());
			txtBaoHanh.setText(dataModel.getValueAt(row, 7).toString());
			txtnguonDienYeuCau.setText(dataModel.getValueAt(row, 8).toString());
			
			String dateString =  dataModel.getValueAt(row, 9).toString();
			String [] a = dateString.split("-");
			String date = a[2]+ "/" + a[1] + "/" +a[0];
			
			txtngayNhapKho.setText(date);
			
			txtnuocSanXuat.setText(dataModel.getValueAt(row, 10).toString());
			cbonhaSX.setSelectedItem(Integer.parseInt(dataModel.getValueAt(row, 11).toString()));
			
			txtMaLinhKien.setEditable(false);
		}
		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
		public void traVe(JTextField txt) {
			txt.requestFocus();
			txt.selectAll();
		}
		public boolean KiemTraDuLieu() {
			String maLk = txtMaLinhKien.getText();
			String tenLk = txtTenLinhKien.getText();
			String loaiLk = txtLoaiLinhKien.getText();
			String donGia = txtDonGia.getText();
			String moTa = txtMoTa.getText();
			String mau = txtMau.getText();
			String soLuong = txtSoLuongTon.getText();
			String baoHanh = txtBaoHanh.getText();
			String nguoDien = txtnguonDienYeuCau.getText();
			String nuocSX = txtnuocSanXuat.getText();
			String ngayNhap = txtngayNhapKho.getText();
			if(!(maLk.trim().length()>0 && maLk.matches("\\d{5}"))) {
				JOptionPane.showMessageDialog(this, "Mã không được rỗng và gồm 5 ký số");
				traVe(txtMaLinhKien);
				return false;
			}
			
			if(!(tenLk.trim().length()>0 && tenLk.matches("([A-Z][A-Za-z]*)(\\s[A-Za-z]*)*"))) {
				JOptionPane.showMessageDialog(this, "Tên linh kiện không được rỗng");
				traVe(txtTenLinhKien);
				return false;
			}
			if(!(loaiLk.trim().length()>0 && loaiLk.matches("([A-Z][A-Za-z]*)(\\s[A-Za-z]*)*"))) {
				JOptionPane.showMessageDialog(this, "Loại Linh Kiện không được rỗng");
				traVe(txtLoaiLinhKien);
				return false;
			}
			if(donGia.trim().length()>0) {
				try {
					Double.parseDouble(donGia);
				} catch (Exception e) {
					JOptionPane.showMessageDialog(this, "Đơn giá là số thực");
					traVe(txtDonGia);
					return false;
				}
			}
			else {
				JOptionPane.showMessageDialog(this, "Đơn giá không được rỗng");
				traVe(txtDonGia);
				return false;
			}
			if(!(moTa.trim().length()>0 && moTa.matches("([A-Z][A-Za-z]*)(\\\\s[A-Za-z]*)*"))) {
				JOptionPane.showMessageDialog(this, "Mô tả không được rỗng");
				traVe(txtMoTa);
				return false;
			}
			if(!(mau.trim().length()>0 && mau.matches("([A-Z][A-Za-z]*)(\\s[A-Za-z]*)*"))) {
				JOptionPane.showMessageDialog(this, "Màu không được rỗng");
				traVe(txtMau);
				return false;
			}
			if(soLuong.trim().length()>0) {
				try {
					Integer.parseInt(soLuong);
				} catch (Exception e) {
					JOptionPane.showMessageDialog(this, "Số lượng là sô");
					traVe(txtSoLuongTon);
					return false;
				}
			}
			else {
				JOptionPane.showMessageDialog(this, "Số lượng không được rỗng");
				traVe(txtSoLuongTon);
				return false;
			}
			
			if(nguoDien.trim().length()>0 && nguoDien.matches("")) {
				JOptionPane.showMessageDialog(this, "Nguồn Điện không được rỗng");
				traVe(txtnguonDienYeuCau);
				return false;
			}
		
			if(!(baoHanh.trim().length()>0 )) {
				JOptionPane.showMessageDialog(this, "Màu không được rỗng");
				traVe(txtBaoHanh);
				return false;
			}
			 if(ngayNhap.trim().length()>0) {
					if(!ngayNhap.matches("[0-9]{1,2}(/)[0-9]{1,2}(/)[0-9]{1,4}")) {
						JOptionPane.showMessageDialog(this, "Ngày lập theo mẫu dd/mm/yyyy");
						traVe(txtngayNhapKho);
						return false;
					}
				}
				else {
					JOptionPane.showMessageDialog(this, "Ngày lập không được rỗng");
					traVe(txtngayNhapKho);
					return false;
				}
			if(!(nuocSX.trim().length()>0 && nuocSX.matches("([A-Z][A-Za-z]*)(\\\\s[A-Z][A-Za-z]*)*"))) {
				JOptionPane.showMessageDialog(this, "Nước sản xuất không được rỗng");
				traVe(txtnuocSanXuat);
				return false;
			}
			
			return true;
		}
		
}






