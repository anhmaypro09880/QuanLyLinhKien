package ungDung_GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.zip.DataFormatException;

import javax.swing.BorderFactory;
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
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import dao.CTHD_DAO;
import dao.HoaDon_DAO;
import dao.KhachHang_DAO;
import dao.NhaSanXuat_DAO;
import dao.NhanVien_DAO;
import entity.CTHD;
import entity.HoaDon;
import entity.KhachHang;
import entity.NhanVien;

import connectDB.ConnectDB;


public class FrmQuanLyHoaDon extends JFrame implements ActionListener,MouseListener {
		private JLabel lblMaHD;
		private JTextField txtMaHD;
		private JLabel lblMaNV;
		private JComboBox<Integer> cboMaNV;
		private JLabel lblMaKH;
		private JComboBox<Integer> cboMaKH;
		private JLabel lblNgayLap;
		private JTextField txtNgayLap;
		private JLabel lblThue;
		private JTextField txtThue;
		private JTable table;
		private DefaultTableModel dataModel;
		private JButton btnThem;
		private JButton btnXoa;
		private JButton btnXoaTrang;
	
		private JTextField txtTim;
		private JButton btnTim;
		private JButton btnSua;
		private JButton btnBack;
	
		
		private HoaDon_DAO hoaDon_DAO;
		private NhanVien_DAO nhanVien_DAO;
		private KhachHang_DAO khachHang_DAO;
		private CTHD_DAO cthd_DAO;
		private JButton btnChiTiet;
		
		public FrmQuanLyHoaDon() {
			
			try {
				ConnectDB.getInstance().connect();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			hoaDon_DAO = new HoaDon_DAO();
			nhanVien_DAO = new NhanVien_DAO();
			khachHang_DAO = new KhachHang_DAO();
			cthd_DAO = new CTHD_DAO();
			JPanel pnlBanner=new JPanel();
			add(pnlBanner);
			pnlBanner.setBounds(0, 50, 1055, 30);
			JLabel lblTieuDe;
			pnlBanner.add(lblTieuDe=new JLabel("QUẢN LÝ HÓA ĐƠN"));
			lblTieuDe.setFont(new Font("arial",Font.BOLD,20));
			lblTieuDe.setForeground(Color.RED);
			JPanel pnlContent=new JPanel();
			add(pnlContent);
			pnlContent.setBounds(0,80,1055,290);
			pnlContent.setLayout(null);
			
			JPanel pnlL=new JPanel();
			pnlContent.add(pnlL);
			pnlL.setLayout(null);
			pnlL.setBounds(0,80,1035,285);
			
			pnlL.setBorder( new TitledBorder(BorderFactory.createLoweredBevelBorder(),"Thông tin chung"));
			
			pnlL.add(lblMaHD=new JLabel("Mã HD:"));
			pnlL.add(txtMaHD =new JTextField());
			
			pnlL.add(lblMaNV=new JLabel("Mã NV:"));
			pnlL.add(cboMaNV = new JComboBox<Integer>());
					
			pnlL.add(lblMaKH=new JLabel("Mã KH:"));
			pnlL.add(cboMaKH = new JComboBox<Integer>());
			
			pnlL.add(lblNgayLap=new JLabel("Ngày lập:"));
			pnlL.add(txtNgayLap=new JTextField());

			pnlL.add(lblThue=new JLabel("Thuế:"));
			pnlL.add(txtThue = new JTextField());
			
			pnlContent.add(btnThem=new JButton("Thêm"));
			pnlContent.add(btnXoa=new JButton("Xóa"));
			pnlContent.add(btnXoaTrang=new JButton("Xóa trắng"));
			pnlContent.add(btnSua=new JButton("Sửa"));
	
			pnlContent.add(btnChiTiet= new JButton("Thêm chi tiết"));
			
			ImageIcon imageIcon1 = new ImageIcon("Image/Back.png");
			Image image1 =imageIcon1.getImage();
			Image imageResize1 = image1.getScaledInstance(25, 25, Image.SCALE_SMOOTH);
			
			
			
			pnlContent.add(btnBack=new JButton("Back"));
			btnBack.setIcon(new ImageIcon(imageResize1));
			btnBack.setBounds(20,20, 100,35);
			
			int w1=100, w2=300, h=25;
			
			lblMaHD.setBounds(50,40, w1, h);txtMaHD.setBounds(150,40, w2, h);
			
			lblMaNV.setBounds(550,40, w1, h);cboMaNV.setBounds(650,40, w2, h);
			
			lblMaKH.setBounds(50,90, w1, h);cboMaKH.setBounds(150,90, w2, h);
			
			lblNgayLap.setBounds(550,90, w1, h);txtNgayLap.setBounds(650,90, w2, h);
			
			lblThue.setBounds(50,140, w1, h);txtThue.setBounds(150,140, w2, h);
			
			JSplitPane split=new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
			pnlContent.add(split);
			split.setBounds(0, 370, 1055, 40);
			JPanel pnlRight,pnlLeft;
			split.add(pnlLeft=new JPanel());
			split.add(pnlRight=new JPanel());
			
			String[] cbxStr = {"Mã hóa đơn"};
			JComboBox cbxTim=new JComboBox(cbxStr);
			pnlLeft.add(txtTim=new JTextField(15));
			txtTim.setEnabled(true);
			txtTim.setDisabledTextColor(Color.GRAY);
			pnlLeft.add(cbxTim);
			pnlLeft.add(btnTim=new JButton("Tìm"));
			pnlRight.add(btnThem);
			pnlRight.add(btnXoa);
			
			pnlRight.add(btnXoaTrang);
			pnlRight.add(btnSua);
		
			pnlRight.add(btnChiTiet);
			

			
			JScrollPane scroll;
			String[] header={"Mã HD","Mã NV","Mã KH","Ngày lập","Thuế","Tổng tiền"};
			dataModel=new DefaultTableModel(header,0);
			add(scroll=new JScrollPane(table=new JTable(dataModel),JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED),BorderLayout.SOUTH);
			scroll.setBorder(BorderFactory.createTitledBorder("Danh Sách Hợp Đồng "));
			table.setRowHeight(20);
			scroll.setPreferredSize(new Dimension(0,240));
			
			ArrayList<CTHD> listCTHD = cthd_DAO.getalltbCTHD();
			
			ArrayList<HoaDon> listHD = hoaDon_DAO.getalltbHoaDon();
		
			for (HoaDon hd : listHD) {
				double tong=0;
				for (CTHD cthd : listCTHD) {
					if(hd.getMaHD() == cthd.getHoaDon().getMaHD()) {
						tong+=cthd.getSoLuong()*cthd.getDonGia();
					}
				}
				dataModel.addRow(new Object[ ] {hd.getMaHD(),hd.getNhanVien().getMaNV(),hd.getKhachHang().getMaKH(),hd.getNgayLap(),hd.getLaiSuatThue() +"%",tong!=0?tong+(tong*hd.getLaiSuatThue()/100):""});
			}
			ArrayList<NhanVien> listNV = nhanVien_DAO.getalltbNhanVien();
			for (NhanVien nv : listNV) {
				cboMaNV.addItem(nv.getMaNV());
			}
			
			ArrayList<KhachHang> listKH = khachHang_DAO.gettalltbKhachHang();
			for (KhachHang khachHang : listKH) {
				cboMaKH.addItem(khachHang.getMaKH());
			}
			
			setSize(1055,720);
			setVisible(true);
			setTitle("Quản lý hóa đơn");
			setDefaultCloseOperation(EXIT_ON_CLOSE);
			setResizable(false);
			setLocationRelativeTo(null);
			
			btnTim.addActionListener(this);
			btnThem.addActionListener(this);
			btnXoa.addActionListener(this);
			btnXoaTrang.addActionListener(this);
			btnSua.addActionListener(this);
			btnBack.addActionListener(this);
			btnChiTiet.addActionListener(this);
			table.addMouseListener(this);
			
			btnThem.setMnemonic(KeyEvent.VK_T);
			btnXoa.setMnemonic(KeyEvent.VK_X);
			btnXoaTrang.setMnemonic(KeyEvent.VK_R);
			btnSua.setMnemonic(KeyEvent.VK_S);
			btnTim.setMnemonic(KeyEvent.VK_F);
			
			
		}
		public static void main(String[] args) {
			new FrmQuanLyHoaDon().setVisible(true);
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			Object o = 	e.getSource();
			if(o.equals(btnBack)) {
				new GiaoDienChinh().setVisible(true);
				setVisible(false);
			}
			else if(o.equals(btnChiTiet)) {
				new FrmQuanLyCTHD().setVisible(true);
				setVisible(false);
			}
			else if(o.equals(btnXoaTrang)) {
				txtMaHD.setText("");
				cboMaNV.setSelectedIndex(0);
				cboMaKH.setSelectedIndex(0);
				txtNgayLap.setText("");
				txtThue.setText("");
				txtTim.setText("");
				txtMaHD.setEditable(true);
				table.clearSelection();
				txtMaHD.requestFocus();
			}
			else if(o.equals(btnThem)) {
				if(kiemTraDuLieu()) {
					String maHDString = txtMaHD.getText();
					String maNVString = cboMaNV.getSelectedItem().toString();
					String maKHString = cboMaKH.getSelectedItem().toString();
					String ngayLapString = txtNgayLap.getText();
					String thueString = txtThue.getText();
					
					int maHD = Integer.parseInt(maHDString);
					int maNV = Integer.parseInt(maNVString);
					int maKH = Integer.parseInt(maKHString);
					DateFormat df=new SimpleDateFormat("dd/MM/yyyy");
					Date ngayLap=null;
					java.sql.Date ngay=null;
					try {
						ngayLap = df.parse(txtNgayLap.getText());
						ngay= new java.sql.Date(ngayLap.getYear(),ngayLap.getMonth(),ngayLap.getDate());
					} catch (ParseException e1) {
					
						
					}
			
					float thue = Float.parseFloat(thueString);
					
					NhanVien nhanVien = new NhanVien(maNV);
					KhachHang khachHang = new KhachHang(maKH);
					
					HoaDon hD = new HoaDon(maHD, ngay,thue,khachHang, nhanVien );
					
					int ktma,flag=0 ;
					for (int i = 0; i < table.getRowCount(); i++) {
						ktma = Integer.parseInt(table.getValueAt(i, 0).toString());
						if(ktma==hD.getMaHD()){
							JOptionPane.showMessageDialog(this, "Trùng mã");
							flag=1;
							break;
						}
					}
					if(flag==0) {
						hoaDon_DAO.create(hD);
						dataModel.addRow(new Object[] {hD.getMaHD(),hD.getNhanVien().getMaNV(),
										hD.getKhachHang().getMaKH(),hD.getNgayLap(),hD.getLaiSuatThue()});
					}	
				}
			}
			else if(o.equals(btnXoa)) {
				int row =  table.getSelectedRow();
				if(row  !=-1) {
					
					int ma = Integer.parseInt(dataModel.getValueAt(row,0).toString());
					hoaDon_DAO.delete(ma);
					dataModel.removeRow(row);
					JOptionPane.showMessageDialog(this, "Xóa thành công");
				}
				else {
					JOptionPane.showMessageDialog(this, "Chọn dòng cần xóa");
				}
			}
			else if(o.equals(btnSua)) {
				int row = table.getSelectedRow();
				
				if(row != -1){					
					if(kiemTraDuLieu()){ 
						String maHDString = txtMaHD.getText();
						String maNVString = cboMaNV.getSelectedItem().toString();
						String maKHString = cboMaKH.getSelectedItem().toString();
						String ngayLapString = txtNgayLap.getText();
						String thueString = txtThue.getText();
						
						int maHD = Integer.parseInt(maHDString);
						int maNV = Integer.parseInt(maNVString);
						int maKH = Integer.parseInt(maKHString);
						DateFormat df=new SimpleDateFormat("dd/MM/yyyy");
						Date ngayLap=null;
						java.sql.Date ngay=null;
						try {
							ngayLap = df.parse(txtNgayLap.getText());
							ngay= new java.sql.Date(ngayLap.getYear(),ngayLap.getMonth(),ngayLap.getDate());
						} catch (ParseException e1) {
						
							
						}
				
						float thue = Float.parseFloat(thueString);
						
						NhanVien nhanVien = new NhanVien(maNV);
						KhachHang khachHang = new KhachHang(maKH);
						
						HoaDon hD = new HoaDon(maHD, ngay,thue,khachHang, nhanVien );
						try {
							hoaDon_DAO.update(hD);
							dataModel.setValueAt(ngay, row, 3);
							dataModel.setValueAt(thue, row, 4);
							JOptionPane.showMessageDialog(this, "Sửa thành công");
						} catch (Exception e2) {
							e2.printStackTrace();
							JOptionPane.showMessageDialog(this, "Trùng mã");
						}	
					}
				}
				else 
					JOptionPane.showMessageDialog(this, "Chọn dòng cần sửa");	
			}
			else if(o.equals(btnTim)) {
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
				if(table.getSelectedRow() != -1) {
					JOptionPane.showMessageDialog(this, "Tìm thấy");
				}
				else {
					JOptionPane.showMessageDialog(this, "Không tìm thấy");
				}
			}
		}
		@Override
		public void mouseClicked(MouseEvent e) {
			int row =  table.getSelectedRow();
			txtMaHD.setText(dataModel.getValueAt(row,0).toString());
			cboMaNV.setSelectedItem(Integer.parseInt(dataModel.getValueAt(row,1).toString()) );
			cboMaKH.setSelectedItem(Integer.parseInt(dataModel.getValueAt(row,2).toString()) );
			
			String dateString =dataModel.getValueAt(row,3).toString();	
			String[] a =dateString.split("-");
			String date = a[2] +"/"+a[1]+"/"+a[0];
			
			txtNgayLap.setText(date);
			txtThue.setText(dataModel.getValueAt(row,4).toString());	
			txtMaHD.setEditable(false);
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
		
		public boolean kiemTraDuLieu() {
			String maHD = txtMaHD.getText();
			String ngayLap = txtNgayLap.getText();
			String thue = txtThue.getText();
			
			if(!(maHD.trim().length()>0 && maHD.matches("\\d{5}"))) {
				JOptionPane.showMessageDialog(this, "Mã không được rỗng và gồm 5 ký số");
				txtMaHD.requestFocus();
				txtMaHD.selectAll();
				return false;
			}
			
			if(ngayLap.trim().length()>0) {
				if(!ngayLap.matches("[0-9]{1,2}(/)[0-9]{1,2}(/)[0-9]{1,4}")) {
					JOptionPane.showMessageDialog(this, "Ngày lập theo mẫu dd/mm/yyyy");
					txtNgayLap.requestFocus();
					txtNgayLap.selectAll();
					return false;
				}
			}
			else {
				JOptionPane.showMessageDialog(this, "Ngày lập không được rỗng");
				return false;
			}
			
			if(thue.trim().length()>0) {
				try {
					Float.parseFloat(thue);
				} catch (Exception e) {
					JOptionPane.showMessageDialog(this, "Thuế là số");
					return false;
				}
			}
			else {
				JOptionPane.showMessageDialog(this, "Thuế không được rỗng");
				return false;
			}
			
			return true;
		}
}
