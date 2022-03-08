package ungDung_GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.JobAttributes;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

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
import dao.KhachHang_DAO;
import entity.KhachHang;




public class FrmQuanLyKhachHang extends JFrame implements ActionListener,MouseListener{
		private JLabel lblMaKhachHang;
		private JTextField txtMaKhachHang;
		private JLabel lblTenKhachHang;
		private JTextField txtTenKhachHang;
		private JLabel lblSDTKhachHang;
		private JTextField txtSDTKhachHang;
		private JLabel lblEmail;
		private JTextField txtEmail;
		private JLabel lblDiaChi;
		private JTextField txtDiaChi;

		private JButton btnThem;
		private JButton btnXoa;
		private JButton btnSua;
		private JButton btnXoaTrang;
		private JButton btnLuu;
		private JComboBox cbxTim;
		private JTextField txtTim;
		private JButton btnTim;
		private DefaultTableModel dataModel;
		private JTable table;
		private JComboBox cbonhaSX;
		private ComboBoxModel cbxStr;
		private KhachHang_DAO kh_dao;
		private ArrayList<KhachHang> dskh;
		private JPanel pback;
		private JButton btnback;
		public FrmQuanLyKhachHang() {
			pback=new JPanel();
			pback.setBounds(10, 0, 100,50);
			pback.add(btnback=new JButton("Back"));
			ImageIcon imageIcon1 = new ImageIcon("Image/Back.png");
			Image image1 =imageIcon1.getImage();
			Image imageResize1 = image1.getScaledInstance(20, 20, Image.SCALE_SMOOTH);
			btnback.setIcon(new ImageIcon(imageResize1));
			add(pback);
			JPanel pnlBanner=new JPanel();
			add(pnlBanner);
			pnlBanner.setBounds(0, 50, 1055, 30);
			JLabel lblTieuDe;
			pnlBanner.add(lblTieuDe=new JLabel("QUẢN LÝ KHÁCH HÀNG"));
			lblTieuDe.setFont(new Font("arial",Font.BOLD,20));
			lblTieuDe.setForeground(Color.RED);
			
			JPanel pnlContent=new JPanel();
			add(pnlContent);
			pnlContent.setBounds(0,80,1055,320);
			pnlContent.setLayout(null);
			
			pnlContent.add(lblMaKhachHang=new JLabel("Mã Khách Hàng:"));
			pnlContent.add(txtMaKhachHang=new JTextField());
			pnlContent.add(lblTenKhachHang=new JLabel("Tên Khách Hàng:"));
			pnlContent.add(txtTenKhachHang=new JTextField());
			pnlContent.add(lblSDTKhachHang=new JLabel("SDT Khách Hàng:"));
			pnlContent.add(txtSDTKhachHang=new JTextField());
			pnlContent.add(lblEmail=new JLabel("Email:"));
			pnlContent.add(txtEmail=new JTextField());
			pnlContent.add(lblDiaChi=new JLabel("Địa Chỉ:"));
			pnlContent.add(txtDiaChi=new JTextField());
			
			pnlContent.add(btnThem=new JButton("Thêm"));
			pnlContent.add(btnXoa=new JButton("Xóa"));
			pnlContent.add(btnSua=new JButton("Sửa"));
			pnlContent.add(btnXoaTrang=new JButton("Xóa trắng"));
	
			
			int w1=100, w2=300, h=25;
			lblMaKhachHang.setBounds(50,100, w1, h);txtMaKhachHang.setBounds(150,100, w2, h);
			lblTenKhachHang.setBounds(550, 100, w1, h);txtTenKhachHang.setBounds(650, 100, w2, h);
			
			lblSDTKhachHang.setBounds(50, 150, w1, h);txtSDTKhachHang.setBounds(150, 150, w2, h);
			lblEmail.setBounds(550, 150, w1, h);txtEmail.setBounds(650, 150, w2, h);
			
			lblDiaChi.setBounds(50, 200, w1, h);txtDiaChi.setBounds(150, 200, w2, h);
			
			
			JSplitPane split=new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
			pnlContent.add(split);
			split.setBounds(0, 395, 1055, 40);
			JPanel pnlRight,pnlLeft;
			split.add(pnlLeft=new JPanel());
			split.add(pnlRight=new JPanel());
			
			
//			String[] cbxStr = {"Mã Linh Kiện","Tên Linh Kiện"};
			cbxTim = new JComboBox();
			cbxTim.addItem("Mã Khách Hàng");
			cbxTim.addItem("Tên Khách Hàng");
			pnlLeft.add(txtTim=new JTextField(20));
			txtTim.setEnabled(true);
			txtTim.setDisabledTextColor(Color.GRAY);
			pnlLeft.add(cbxTim);
			pnlLeft.add(btnTim=new JButton("Tìm"));
			pnlRight.add(btnThem);
			pnlRight.add(btnXoa);
			pnlRight.add(btnXoaTrang);
			pnlRight.add(btnSua);
			
			JScrollPane scroll;
			String[] header= {"Mã Khách Hàng","Tên Khách Hàng","SDT Khách Hàng","Email","Địa Chỉ"};
			dataModel=new DefaultTableModel(header,0);
			add(scroll=new JScrollPane(table=new JTable(dataModel),JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED),BorderLayout.SOUTH);
			scroll.setBorder(BorderFactory.createTitledBorder("Danh Sách Khách Hàng"));
			table.setRowHeight(20);
			scroll.setPreferredSize(new Dimension(0,285));
			
			setTitle("Quản lý khách hàng");
			setSize(1055,780);
			setDefaultCloseOperation(EXIT_ON_CLOSE);
			setLocationRelativeTo(null);
			setVisible(true);
			
			try {
				ConnectDB.getInstance().connect();
			} catch (Exception e) {
				e.printStackTrace();
			}
			kh_dao = new KhachHang_DAO();
			dskh = kh_dao.gettalltbKhachHang();
			
			DocDuLieuVaoData();
			
			btnThem.addActionListener(this);
			btnXoa.addActionListener(this);
			table.addMouseListener(this);
			btnXoaTrang.addActionListener(this);
			btnSua.addActionListener(this);
			btnTim.addActionListener(this);
			btnback.addActionListener(this);
			
			btnThem.setMnemonic(KeyEvent.VK_T);
			btnXoa.setMnemonic(KeyEvent.VK_X);
			btnXoaTrang.setMnemonic(KeyEvent.VK_R);
			btnSua.setMnemonic(KeyEvent.VK_S);
			btnTim.setMnemonic(KeyEvent.VK_F);
		}
		public static void main(String[] args) {
			new FrmQuanLyKhachHang().setVisible(true);
		}
		@Override
		public void mouseClicked(MouseEvent e) {
			int row= table.getSelectedRow();
			txtMaKhachHang.setText(table.getValueAt(row, 0).toString());
			txtTenKhachHang.setText(table.getValueAt(row, 1).toString());
			txtSDTKhachHang.setText(table.getValueAt(row, 2).toString());
			txtEmail.setText(table.getValueAt(row, 3).toString());
			txtDiaChi.setText(table.getValueAt(row, 4).toString());
		}
		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			
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
		public void actionPerformed(ActionEvent e) {
			Object obj = e.getSource();
			if(obj.equals(btnThem)) {
				if(txtMaKhachHang.getText().equals("") | txtTenKhachHang.getText().equals("") | txtSDTKhachHang.getText().equals("") | txtEmail.getText().equals("") |  txtDiaChi.getText().equals("") )
					 JOptionPane.showMessageDialog(this, "Vui lòng nhập đầy đủ thông tin");
				else if(!ktrama())
				{
					JOptionPane.showMessageDialog(this,"Nhập mã khách hàng là 5 chữ số");
				}
				else if(!ktraten())
				{
					JOptionPane.showMessageDialog(this,"Tên khách hàng xuất viết hoa chữ đầu");
				}
				else if(!ktrasdt())
				{
					JOptionPane.showMessageDialog(this,"Số điện thoại gồm 9 hoặc 10 số");
				}
				else if(!ktraemail())
				{
					JOptionPane.showMessageDialog(this,"Email có dạng:*****@gmail.com");
				}
				else if(!ktradiachi())
				{
					JOptionPane.showMessageDialog(this,"Địa chỉ không chứa kí tự đặc biệt");
				}
				else {
				String tenKH,Email,diaChi;
				int maKH = Integer.parseInt(txtMaKhachHang.getText());
				tenKH = txtTenKhachHang.getText();
				int SDTKH = Integer.parseInt(txtSDTKhachHang.getText());
				Email =  txtEmail.getText();
				diaChi =  txtDiaChi.getText();
				KhachHang kh = new KhachHang(maKH,tenKH,SDTKH,Email,diaChi);
				if(ktratrungma(maKH)==true)
				{
					kh_dao.create(kh);
					dskh.add(kh);
					dataModel.addRow(new Object[] {maKH,tenKH,SDTKH,Email,diaChi});
					JOptionPane.showMessageDialog(null,"Thêm khách hàng thành công");
				}
				else
				{
					JOptionPane.showMessageDialog(null,"Mã khách hàng bị trùng");
				}
				}
			
			}
			if(obj.equals(btnXoa))
			{
				int result = JOptionPane.showConfirmDialog(this,"Xác nhận xóa ?", "Xóa nhà khách hàng",
			               JOptionPane.YES_NO_OPTION);
			    if(result == JOptionPane.YES_OPTION)
				{
				KhachHang_DAO dao=new KhachHang_DAO();
				int row =table.getSelectedRow();
				int ma=Integer.parseInt(table.getValueAt(row,0).toString());
				if(dao.XoaKH(ma))
				{
					dataModel.removeRow(row);
					txtMaKhachHang.setText(null);
					txtTenKhachHang.setText(null);
					txtSDTKhachHang.setText(null);
					txtEmail.setText(null);
					txtDiaChi.setText(null);
					JOptionPane.showMessageDialog(null, "Xóa thành công");
				}
				}
			    else
			    {
			    	
			    }
			}
			if(obj.equals(btnXoaTrang)) {
				txtMaKhachHang.setText(null);
				txtTenKhachHang.setText(null);
				txtSDTKhachHang.setText(null);
				txtEmail.setText(null);
				txtDiaChi.setText(null);
			}
			if(obj.equals(btnSua))
			{	int row = table.getSelectedRow();
				int maKH = Integer.parseInt(txtMaKhachHang.getText());
				String tenKH=txtTenKhachHang.getText();
				int SDTKH=Integer.parseInt(txtSDTKhachHang.getText());
				String Email = txtEmail.getText();
				String diaChi=txtDiaChi.getText();
				KhachHang kh = new KhachHang(maKH,tenKH,SDTKH,Email, diaChi);
				KhachHang_DAO dao=new KhachHang_DAO();
				if(dao.update(kh))
				{
					dataModel.setValueAt(txtTenKhachHang.getText(), row, 1);
					dataModel.setValueAt(txtSDTKhachHang.getText(), row, 2);
					dataModel.setValueAt(txtEmail.getText(), row, 3);
					dataModel.setValueAt(txtDiaChi.getText(), row, 4);
					JOptionPane.showMessageDialog(null,"Sửa thành công");
				}
			}
			if(obj.equals(btnTim)) {
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
			if(obj.equals(btnback)) {
				new GiaoDienChinh().setVisible(true);
				setVisible(false);
			}
		}
	
		public void DocDuLieuVaoData() {
			ArrayList<KhachHang> list = kh_dao.gettalltbKhachHang();
			for(KhachHang kh : list) {
				dataModel.addRow(new Object[] {kh.getMaKH(),kh.getTenKH(),kh.getSDTKH(),kh.getEmail(),kh.getDiaChi()});
			}
		}
		public boolean ktratim()
		{
			String reg= "[0-9]{5}";
			if(txtTim.getText().matches(reg))
			{
				return true;
				
			}return false;
		}
		public boolean ktrama()
		{
			String reg= "[0-9]{5}";
			if(txtMaKhachHang.getText().matches(reg))
			{
				return true;
			}return false;
		}
		public boolean ktraten()
		{
			String reg="([A-Z][A-Za-z]*)(\\s[A-Z][A-Za-z]*)*";
			if(txtTenKhachHang.getText().matches(reg))
			{
				return true;
			}return false;
		}
		public boolean ktrasdt()
		{
			String reg="[0-9]{9,10}";
			if(txtSDTKhachHang.getText().matches(reg))
			{
				return true;
			}return false;
		}
		public boolean ktraemail()
		{
			String reg="\\w+@gmail\\.com";
			if(txtEmail.getText().matches(reg))
			{
				return true;
			}return false;
		}
		public boolean ktradiachi()
		{
			String reg="\\w+";
			if(txtDiaChi.getText().matches(reg))
			{
				return true;
			}return false;
		}
	
		public boolean ktratrungma(int ma)
		{
			for(int i=0;i<dskh.size();i++)
			{
				int kt=dskh.get(i).getMaKH();
				if(ma==kt)
				{
				return false;
				}
			}return true;
		}
}


