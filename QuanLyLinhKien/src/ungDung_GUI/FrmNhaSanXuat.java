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
import java.util.ArrayList;

import javax.swing.AbstractButton;
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
import javax.swing.event.AncestorListener;
import javax.swing.table.DefaultTableModel;

import connectDB.ConnectDB;
import dao.NhaSanXuat_DAO;
import entity.NhaSanXuat;




public class FrmNhaSanXuat extends JFrame implements ActionListener,MouseListener	 {
	
	private JButton btnThem;
	private JButton btnXoa;
	private JButton btnSua;
	private JButton btnXoaTrang;
	
	private DefaultTableModel dataModel;
	private JTable table;
	
	private AbstractButton btnTim;
	
	private JTextField txtTim;
	private JLabel lblmansx;
	private JTextField txtmansx;
	private JLabel lblten;
	private JLabel lbldiachi;
	private JTextField txtdiachi;
	private JTextField txtten;
	private JLabel lblsdt;
	private JTextField txtsdt;
	private JPanel pback;
	private JButton btnback;
	private JLabel lbthongbao;
	private ArrayList<NhaSanXuat> ds;
	private NhaSanXuat_DAO nhaSanXuat_DAO; 
	public FrmNhaSanXuat()
	{
		setTitle("Quản lý nhà sản xuất");
		setSize(1000,710);
		setLocationRelativeTo(null);
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
		pnlBanner.setBounds(0, 35, 1055, 30);
		JLabel lblTieuDe;
		pnlBanner.add(lblTieuDe=new JLabel("QUẢN LÝ NHÀ SẢN XUẤT"));
		lblTieuDe.setFont(new Font("arial",Font.BOLD,20));
		lblTieuDe.setForeground(Color.red);
		
		JPanel pnlContent=new JPanel();
		add(pnlContent);
		pnlContent.setBounds(0,80,1055,320);
		pnlContent.setLayout(null);
		
		
		
		pnlContent.add(lblmansx=new JLabel("Mã nhà sản xuất:"));
		pnlContent.add(txtmansx=new JTextField());
		pnlContent.add(lblten=new JLabel("Tên nhà sản xuất"));
		pnlContent.add(txtten=new JTextField());
		pnlContent.add(lbldiachi= new JLabel("Địa chỉ"));
		pnlContent.add(txtdiachi=new JTextField());
		pnlContent.add(lblsdt=new JLabel("Số điện thoại"));
		pnlContent.add(txtsdt=new JTextField());
		lbthongbao = new JLabel("*");
		pnlContent.add(lbthongbao);
		/**
		 * Mu
		 */

		pnlContent.add(btnThem=new JButton("Thêm"));
		pnlContent.add(btnXoa=new JButton("Xóa"));
		pnlContent.add(btnSua=new JButton("Sửa"));
		pnlContent.add(btnXoaTrang=new JButton("Xóa trắng"));
	
	
		int w1=100, w2=300, h=25;
		lblmansx.setBounds(50,100, w1, h);txtmansx.setBounds(150,100, w2, h);
		lblten.setBounds(550, 100, w1, h);txtten.setBounds(650, 100, w2, h);
	
		lbldiachi.setBounds(50, 150, w1, h);txtdiachi.setBounds(150, 150, w2, h);
		lblsdt.setBounds(550, 150, w1, h);txtsdt.setBounds(650, 150, w2, h);
		
			
		lbthongbao.setBounds(150, 180, 300, h);
		lbthongbao.setForeground(Color.red);
		lbthongbao.setFont(new Font("arial",Font.ITALIC,12));;
	
		
		JSplitPane split=new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
		pnlContent.add(split);
		split.setBounds(0, 210, 1055, 40);
		JPanel pnlRight,pnlLeft;
		split.add(pnlLeft=new JPanel());
		split.add(pnlRight=new JPanel());
		
		
		
		
		pnlLeft.add(txtTim=new JTextField(20));
		txtTim.setEnabled(true);
		txtTim.setDisabledTextColor(Color.GRAY);
		
		pnlLeft.add(btnTim=new JButton("Tìm"));
		pnlRight.add(btnThem);
		pnlRight.add(new JLabel("    "));
		pnlRight.add(btnXoa);
		pnlRight.add(new JLabel("    "));
		pnlRight.add(btnXoaTrang);
		pnlRight.add(new JLabel("    "));
		pnlRight.add(btnSua);
		pnlRight.add(new JLabel("    "));
		

		JScrollPane scroll;
		String[] header= {"Mã nhà sản xuất","Tên nhà sản xuất","Địa chỉ","Số điện thoại"};
		dataModel=new DefaultTableModel(header,0);
		add(scroll=new JScrollPane(table=new JTable(dataModel),JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED),BorderLayout.SOUTH);
		scroll.setBorder(BorderFactory.createTitledBorder("Danh sách nhà sản xuất"));
		table.setRowHeight(20);
		scroll.setPreferredSize(new Dimension(0,400));
		try {
			ConnectDB.getInstance().connect();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 nhaSanXuat_DAO= new NhaSanXuat_DAO();
		 ds= nhaSanXuat_DAO.getallnsx();
		for(int i=0;i<ds.size();i++)
		{
			NhaSanXuat nsx= ds.get(i);
			dataModel.addRow(new Object[] {nsx.getMaNSX(),nsx.getTenNSX(),nsx.getDiaChi(),nsx.getSoDT()});
		}
		
		
		btnback.addActionListener(this);
		btnTim.addActionListener(this);
		
		btnXoaTrang.addActionListener(this);
		btnThem.addActionListener(this);
		btnXoa.addActionListener(this);
		btnSua.addActionListener(this);
		table.addMouseListener(this);
		
		btnThem.setMnemonic(KeyEvent.VK_T);
		btnXoa.setMnemonic(KeyEvent.VK_X);
		btnXoaTrang.setMnemonic(KeyEvent.VK_R);
		btnSua.setMnemonic(KeyEvent.VK_S);
		btnTim.setMnemonic(KeyEvent.VK_F);
	}
	
	public static void main(String[] args) {
		new FrmNhaSanXuat().setVisible(true);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object obj = e.getSource();
		if(obj.equals(btnback))
		{
			setVisible(false);
			new GiaoDienChinh().setVisible(true);
		}
		if(obj.equals(btnTim))
		{
			if(ktratim()==true)
			{
			int ma= Integer.parseInt(txtTim.getText());
			
			nhaSanXuat_DAO=new NhaSanXuat_DAO();		
			NhaSanXuat nsx=nhaSanXuat_DAO.getnsx(ma);
			if(nsx!=null)
			{
			txtmansx.setText(String.valueOf(nsx.getMaNSX()));
			txtten.setText(nsx.getTenNSX());
			txtdiachi.setText(nsx.getDiaChi());
			txtsdt.setText(String.valueOf(nsx.getSoDT()));
			lbthongbao.setText("*");
			for(int i=0;i<ds.size();i++)
			{
				int x=Integer.parseInt(table.getValueAt(i, 0).toString());
				if(x==nsx.getMaNSX())
				{
					table.clearSelection();
					table.addRowSelectionInterval(i, i);
				}
			}
				
			
			}
			else
			{
				txtmansx.setText(null);
				txtdiachi.setText(null);
				txtsdt.setText(null);
				txtten.setText(null);
				lbthongbao.setText("Không tìm thấy ");
			}
			}
			else
			{
				lbthongbao.setText("Nhập mã là 5 chữ số");
			}
		}
		if(obj.equals(btnXoaTrang))
		{
			txtmansx.setText(null);
			txtdiachi.setText(null);
			txtsdt.setText(null);
			txtten.setText(null);
			lbthongbao.setText("*");
			txtmansx.setEditable(true);
		}
		if(obj.equals(btnThem))
		{
			if(!ktrama())
			{
				lbthongbao.setText("Nhập mã nhà sản xuất là 5 chữ số");
			}
			else if(!ktraten())
			{
				lbthongbao.setText("Tên nhà sản xuất viết hoa chữ đầu");
			}
			else if(!ktradiachi())
			{
				lbthongbao.setText("Địa chỉ không chứa kí tự đặc biệt");
			}
			else if(!ktrasdt())
			{
				lbthongbao.setText("Số điện thoại gồm 9 hoặc 10 số");
			}
			else
			{
				int ma = Integer.parseInt(txtmansx.getText());
				String ten=txtten.getText();
				String dc=txtdiachi.getText();
				int sdt=Integer.parseInt(txtsdt.getText());
				NhaSanXuat nsx = new NhaSanXuat(ma, ten, dc, sdt);
				if(ktratrungma(ma)==true)
				{
					nhaSanXuat_DAO =new NhaSanXuat_DAO();
					ds.add(nsx);
					nhaSanXuat_DAO.themnsx(nsx);
					
					dataModel.addRow(new Object[] {ma,ten,dc,sdt});
					JOptionPane.showMessageDialog(null,"Thêm nhà sản xuất thành công");
				}
				else
				{
					JOptionPane.showMessageDialog(null,"Mã nhà sản xuất bị trùng");
				}
				
			}
					
		}
		
		if(obj.equals(btnXoa))
		{
			int result = JOptionPane.showConfirmDialog(null,"Xác nhận xóa ?", "Xóa nhà sản xuất",
		               JOptionPane.YES_NO_OPTION,
		               JOptionPane.QUESTION_MESSAGE);
		    if(result == JOptionPane.YES_OPTION)
			{
			nhaSanXuat_DAO=new NhaSanXuat_DAO();
			int row =table.getSelectedRow();
			int ma=Integer.parseInt(table.getValueAt(row,0).toString());
			if(nhaSanXuat_DAO.xoansx(ma))
			{
				dataModel.removeRow(row);
				txtmansx.setText(null);
				txtdiachi.setText(null);
				txtsdt.setText(null);
				txtten.setText(null);
				JOptionPane.showMessageDialog(null, "Xóa thành công");
			}
			}
		    else
		    {
		    	
		    }
			
		}
		if(obj.equals(btnSua))
		{	int row = table.getSelectedRow();
			int ma = Integer.parseInt(txtmansx.getText());
			String ten=txtten.getText();
			String dc=txtdiachi.getText();
			int sdt=Integer.parseInt(txtsdt.getText());
			NhaSanXuat nsx = new NhaSanXuat(ma, ten, dc, sdt);
			nhaSanXuat_DAO=new NhaSanXuat_DAO();
			if(nhaSanXuat_DAO.suansx(nsx))
			{
				dataModel.setValueAt(txtten.getText(), row, 1);
				dataModel.setValueAt(txtdiachi.getText(), row, 2);
				dataModel.setValueAt(txtsdt.getText(), row, 3);
				JOptionPane.showMessageDialog(null,"Sửa thành công");
			}
		}
		
	
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		int row= table.getSelectedRow();
		txtmansx.setText(table.getValueAt(row, 0).toString());
		txtten.setText(table.getValueAt(row, 1).toString());
		txtdiachi.setText(table.getValueAt(row, 2).toString());
		txtsdt.setText(table.getValueAt(row, 3).toString());
		txtmansx.setEditable(false);
		
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
		if(txtmansx.getText().matches(reg))
		{
			return true;
		}return false;
	}
	public boolean ktraten()
	{

		if(txtten.getText().matches("([A-Z][A-Za-z]*)(\\s[A-Z][A-Za-z]*)*"))
		{
			return true;
		}return false;
	}
	public boolean ktradiachi()
	{
		String reg="[A-Za-z0-9]*";
		if(txtdiachi.getText().matches(reg))
		{
			return true;
		}return false;
	}
	public boolean ktrasdt()
	{
		String reg="[0-9]{9,10}";
		if(txtsdt.getText().matches(reg))
		{
			return true;
		}return false;
	}
	public boolean ktratrungma(int ma)
	{
		for(int i=0;i<ds.size();i++)
		{
			int kt=ds.get(i).getMaNSX();
			if(ma==kt)
			{
			return false;
			}
		}return true;
	}
	

}
