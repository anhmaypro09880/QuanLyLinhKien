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

import connectDB.ConnectDB;
import dao.CTHD_DAO;
import dao.HoaDon_DAO;
import dao.LinhKien_DAO;
import entity.CTHD;
import entity.HoaDon;
import entity.KhachHang;
import entity.LinhKien;
import entity.NhanVien;

public class FrmQuanLyCTHD extends JFrame implements ActionListener,MouseListener {
		private JLabel lblMaHD;
		private JLabel lblMaCTHD;
		private JTextField txtMaCTHD;
		private JComboBox cboMaHD;
		private JLabel lblMaLK;
		private JComboBox cboMaLK;
		private JLabel lblSoLuong;
		private JTextField txtSoLuong;
		private JLabel lblDonGia;
		private JTextField txtDonGia;
		private JButton btnBack;		
		private JTable table;
		private DefaultTableModel dataModel;
		private JButton btnThem;
		private JButton btnXoa;
		private JButton btnXoaTrang;
	
		private JTextField txtTim;
		private JButton btnTim;
		private JButton btnSua;
		
		private HoaDon_DAO hoaDon_DAO;
		private CTHD_DAO cthd_DAO;
		private LinhKien_DAO linhKien_DAO;
		
		public FrmQuanLyCTHD() {
			try {
				ConnectDB.getInstance().connect();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			hoaDon_DAO = new HoaDon_DAO();
			cthd_DAO = new CTHD_DAO();
			linhKien_DAO = new LinhKien_DAO();
			JPanel pnlBanner=new JPanel();
			add(pnlBanner);
			pnlBanner.setBounds(0, 50, 1055, 30);
			JLabel lblTieuDe;
			pnlBanner.add(lblTieuDe=new JLabel("QUẢN LÝ CHI TIẾT HÓA ĐƠN"));
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
			
			pnlL.add(lblMaCTHD=new JLabel("Mã CTHD:"));
			pnlL.add(txtMaCTHD =new JTextField());
			
			pnlL.add(lblMaHD=new JLabel("Mã HD:"));
			pnlL.add(cboMaHD = new JComboBox());
					
			pnlL.add(lblMaLK=new JLabel("Mã LK:"));
			pnlL.add(cboMaLK = new JComboBox());
			
			pnlL.add(lblSoLuong=new JLabel("Số lượng:"));
			pnlL.add(txtSoLuong=new JTextField());

			pnlL.add(lblDonGia=new JLabel("Đơn giá:"));
			pnlL.add(txtDonGia = new JTextField());
			
			pnlContent.add(btnThem=new JButton("Thêm"));
			pnlContent.add(btnXoa=new JButton("Xóa"));
			pnlContent.add(btnXoaTrang=new JButton("Xóa trắng"));
			pnlContent.add(btnSua=new JButton("Sửa"));
		
			ImageIcon imageIcon1 = new ImageIcon("Image/Back.png");
			Image image1 =imageIcon1.getImage();
			Image imageResize1 = image1.getScaledInstance(25, 25, Image.SCALE_SMOOTH);
			
			pnlContent.add(btnBack=new JButton("Back"));
			btnBack.setIcon(new ImageIcon(imageResize1));
			btnBack.setBounds(20,20, 100,35);
			
			int w1=100, w2=300, h=25;
			
			lblMaCTHD.setBounds(50,40, w1, h);txtMaCTHD.setBounds(150,40, w2, h);
			
			lblMaHD.setBounds(550,40, w1, h);cboMaHD.setBounds(650,40, w2, h);
			
			lblMaLK.setBounds(50,90, w1, h);cboMaLK.setBounds(150,90, w2, h);
			
			lblSoLuong.setBounds(50,140, w1, h);txtSoLuong.setBounds(150,140, w2, h);
			
			lblDonGia.setBounds(550,140, w1, h);txtDonGia.setBounds(650,140, w2, h);
			
			JSplitPane split=new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
			pnlContent.add(split);
			split.setBounds(0, 370, 1055, 40);
			JPanel pnlRight,pnlLeft;
			split.add(pnlLeft=new JPanel());
			split.add(pnlRight=new JPanel());
			
			String[] cbxStr = {"Mã CTHD"};
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
			
			JScrollPane scroll;
			String[] header={"Mã CTHD","Mã HD","Mã LK","Số lượng","Đơn giá","Thành tiền"};
			dataModel=new DefaultTableModel(header,0);
			add(scroll=new JScrollPane(table=new JTable(dataModel),JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED),BorderLayout.SOUTH);
			scroll.setBorder(BorderFactory.createTitledBorder("Danh Sách Hợp Đồng "));
			table.setRowHeight(20);
			scroll.setPreferredSize(new Dimension(0,240));
			
			ArrayList<HoaDon> listHD = hoaDon_DAO.getalltbHoaDon();
			for (HoaDon hd : listHD) {
				cboMaHD.addItem(hd.getMaHD());	
			}
			ArrayList<LinhKien> listLK =linhKien_DAO.getalltbLinhKien();
			for (LinhKien linhKien : listLK) {
				cboMaLK.addItem(linhKien.getMaLk());
			}
			ArrayList<CTHD> listCTHD = cthd_DAO.getalltbCTHD();
			for (CTHD cthd : listCTHD) {
				dataModel.addRow(new Object[] {cthd.getMaCTHD(),cthd.getHoaDon().getMaHD(),cthd.getLinhKien().getMaLk(),cthd.getSoLuong(),cthd.getDonGia(),cthd.getSoLuong()*cthd.getDonGia()});
			}
			
			setSize(1055,720);
			setVisible(true);
			setTitle("Quản lý chi tiết hóa đơn");
			setDefaultCloseOperation(EXIT_ON_CLOSE);
			setResizable(false);
			setLocationRelativeTo(null);
			
			btnTim.addActionListener(this);
			btnThem.addActionListener(this);
			btnXoa.addActionListener(this);
			btnXoaTrang.addActionListener(this);
			btnSua.addActionListener(this);
			btnBack.addActionListener(this);
			
			table.addMouseListener(this);
			
			
			btnThem.setMnemonic(KeyEvent.VK_T);
			btnXoa.setMnemonic(KeyEvent.VK_X);
			btnXoaTrang.setMnemonic(KeyEvent.VK_R);
			btnSua.setMnemonic(KeyEvent.VK_S);
			btnTim.setMnemonic(KeyEvent.VK_F);
			
		}
		public static void main(String[] args) {
			new FrmQuanLyCTHD().setVisible(true);
		}
		@Override
		public void actionPerformed(ActionEvent e) {
			Object o = e.getSource();
			if(o.equals(btnXoaTrang)) {
				txtMaCTHD.setText("");
				cboMaHD.setSelectedIndex(0);
				cboMaLK.setSelectedIndex(0);
				txtSoLuong.setText("");
				txtDonGia.setText("");	
				txtMaCTHD.setEditable(true);
				table.clearSelection();
				txtMaCTHD.requestFocus();
			}
			else if(o.equals(btnBack)){
				new FrmQuanLyHoaDon().setVisible(true);
				setVisible(false);
			}
			else if(o.equals(btnThem)) {
				if(kiemTraDuLieu()) {
					String maCTHDString = txtMaCTHD.getText();
					String maHDString = cboMaHD.getSelectedItem().toString();
					String maLKString = cboMaLK.getSelectedItem().toString();
					String soLuongString = txtSoLuong.getText();
					String donGiaString = txtDonGia.getText();
					
					int maCTHD = Integer.parseInt(maCTHDString);
					int maHD = Integer.parseInt(maHDString);
					int maLK = Integer.parseInt(maLKString);
					int soLuong = Integer.parseInt(soLuongString);
					double donGia = Double.parseDouble(donGiaString);
					
					LinhKien linhKien = new LinhKien(maLK);
					HoaDon hoaDon = new HoaDon(maHD);
					
					CTHD cthd = new CTHD(maCTHD, soLuong, donGia, hoaDon, linhKien);
					
					int ktma,flag=0 ;
					for (int i = 0; i < table.getRowCount(); i++) {
						ktma = Integer.parseInt(table.getValueAt(i, 0).toString());
						if(ktma==cthd.getMaCTHD()){
							JOptionPane.showMessageDialog(this, "Trùng mã");
							flag=1;
							break;
						}
					}
					if(flag==0) {
						cthd_DAO.create(cthd);
						dataModel.addRow(new Object[] {cthd.getMaCTHD(),cthd.getHoaDon().getMaHD(),
										cthd.getLinhKien().getMaLk(),cthd.getSoLuong(),cthd.getDonGia(),cthd.getSoLuong()*cthd.getDonGia()});
					}	
				}
			}
			else if(o.equals(btnXoa)) {
				int row =  table.getSelectedRow();
				if(row  !=-1) {
					
					int ma = Integer.parseInt(dataModel.getValueAt(row,0).toString());
					cthd_DAO.delete(ma);
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
						String maCTHDString = txtMaCTHD.getText();
						String maHDString = cboMaHD.getSelectedItem().toString();
						String maLKString = cboMaLK.getSelectedItem().toString();
						String soLuongString = txtSoLuong.getText();
						String donGiaString = txtDonGia.getText();
						
						int maCTHD = Integer.parseInt(maCTHDString);
						int maHD = Integer.parseInt(maHDString);
						int maLK = Integer.parseInt(maLKString);
						int soLuong = Integer.parseInt(soLuongString);
						double donGia = Double.parseDouble(donGiaString);
						
						LinhKien linhKien = new LinhKien(maLK);
						HoaDon hoaDon = new HoaDon(maHD);
						
						CTHD cthd = new CTHD(maCTHD, soLuong, donGia, hoaDon, linhKien);
						try {
							cthd_DAO.update(cthd);
							dataModel.setValueAt(soLuong, row, 3);
							dataModel.setValueAt(donGia, row, 4);
							dataModel.setValueAt(soLuong*donGia, row, 5);
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
		public void mouseClicked(MouseEvent arg0) {
			int row =  table.getSelectedRow();
			txtMaCTHD.setText(dataModel.getValueAt(row,0).toString());
			cboMaHD.setSelectedItem(Integer.parseInt(dataModel.getValueAt(row,1).toString()) );
			cboMaLK.setSelectedItem(Integer.parseInt(dataModel.getValueAt(row,2).toString()) );
			txtSoLuong.setText(dataModel.getValueAt(row,3).toString());
			txtDonGia.setText(dataModel.getValueAt(row,4).toString());	
			txtMaCTHD.setEditable(false);
			
		}
		@Override
		public void mouseEntered(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}
		@Override
		public void mouseExited(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}
		@Override
		public void mousePressed(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}
		@Override
		public void mouseReleased(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}
		public boolean kiemTraDuLieu() {
			String maCTHD = txtMaCTHD.getText();
			String donGia = txtDonGia.getText();
			String soLuong = txtSoLuong.getText();
			
			if(!(maCTHD.trim().length()>0 && maCTHD.matches("\\d{5}"))) {
				JOptionPane.showMessageDialog(this, "Mã không được rỗng và gồm 5 ký số");
				return false;
			}
			
			if(soLuong.trim().length()>0) {
				try {
					Integer.parseInt(soLuong);
				} catch (Exception e) {
					JOptionPane.showMessageDialog(this, "Số lượng là số nguyên");
					return false;
				}
			}
			else {
				JOptionPane.showMessageDialog(this, "Số lượng không được rỗng");
				return false;
			}
			
			if(donGia.trim().length()>0) {
				try {
					Double.parseDouble(donGia);
				} catch (Exception e) {
					JOptionPane.showMessageDialog(this, "Đơn giá là số");
					return false;
				}
			}
			else {
				JOptionPane.showMessageDialog(this, "Đơn giá không được rỗng");
				return false;
			}
			
			return true;
		}
}
