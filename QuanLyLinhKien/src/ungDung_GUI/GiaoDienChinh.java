package ungDung_GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;



public class GiaoDienChinh extends JFrame implements ActionListener {
	
	private JButton btnKH;
	private JPanel pcenter;
	private JLabel tieuDe;
	private JPanel pnorth;
	private JButton btnlk;
	private JButton btndonhang;
	private JButton btndangxuat;
	private JButton btnthoat;
	private JButton btnnsx;
	private JLabel lb;
	private JPanel p;

	public GiaoDienChinh()
	{
		setTitle("Giao diện chính");
		setSize(1100,500);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		tieuDe= new JLabel("PHẦN MỀM QUẢN LÝ BÁN LINH KIỆN ĐIỆN TỬ");
		tieuDe.setForeground(Color.RED);
		tieuDe.setFont(new Font("Bookman",Font.BOLD,25));
		pnorth = new JPanel();
		pnorth.add(tieuDe);
		
		
		btnKH = new JButton("Khách hàng",new ImageIcon("anh/khachhang.jpg"));
		btnKH.setBackground(Color.WHITE);
		btnKH.setPreferredSize(new Dimension(300, 300));
		btnlk= new JButton("Linh Kiện",new ImageIcon("anh/Linhkien.jpg"));
		
		btnlk.setBackground(Color.WHITE);
		
		btndonhang=new JButton("Đơn hàng",new ImageIcon("anh/donhang.jpg"));
		btndonhang.setBackground(Color.WHITE);
		
		btndangxuat=new JButton("Đăng xuất",new ImageIcon("anh/dangxuat.png"));
		btndangxuat.setBackground(Color.WHITE);
		
		btnthoat=new JButton("Thoát",new ImageIcon("anh/thoat.png"));
		btnthoat.setBackground(Color.WHITE);
		
		btnnsx=new JButton("Nhà sản xuất",new ImageIcon("anh/nsx.png"));
		btnnsx.setBackground(Color.WHITE);
		
		
		pcenter=new JPanel();
		
		Box b = Box.createVerticalBox();
		Box b1 =Box.createHorizontalBox();
		Box b2 =Box.createHorizontalBox();
		Box b3 =Box.createHorizontalBox();
		Box b4 =Box.createHorizontalBox();
		Box b5 =Box.createHorizontalBox();
		Box b6 =Box.createHorizontalBox();
		Box b7 =Box.createHorizontalBox();
		Box b8 =Box.createHorizontalBox();
		b1.add(new JLabel("             "));
		b2.add(new JLabel("             "));
		b3.add(new JLabel("             "));
		b5.add(new JLabel("             "));
		b6.add(new JLabel("             "));
		b7.add(new JLabel("             "));
		b8.add(new JLabel("             "));
		b4.add(btnKH);
		b4.add(new JLabel("    "));
		b4.add(btnlk);
		b4.add(new JLabel("    "));
		b4.add(btndonhang);
		b4.add(new JLabel("    "));
		b4.add(btnnsx);
		b4.add(new JLabel("    "));
		b4.add(btndangxuat);
		b4.add(new JLabel("    "));
		b4.add(btnthoat);
		b.add(b1);
		b.add(b2);
		b.add(b3);
		b.add(b5);
		b.add(b6);
		b.add(b7);
		b.add(b8);
		b.add(b4);
		pnorth.setBackground(Color.black);
		pcenter.setBackground(Color.gray);
		
		pcenter.add(b);
		add(pnorth,BorderLayout.NORTH);
		add(pcenter);
		
		btnlk.addActionListener(this);
		btnnsx.addActionListener(this);	
		btndangxuat.addActionListener(this);
		btndonhang.addActionListener(this);
		btnKH.addActionListener(this);
		btnthoat.addActionListener(this);
		
	}
	
	public static void main(String[] args) {
		 new GiaoDienChinh().setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		if(obj.equals(btnlk))
		{
			new FrmLinhKien().setVisible(true);
			setVisible(false);		
		}
		else if(obj.equals(btnnsx))
		{
			setVisible(false);
			new FrmNhaSanXuat().setVisible(true);;
		}
		else if(obj.equals(btndangxuat))
		{
			setVisible(false);
			new FrmDangNhap().setVisible(true);
		}
		else if(obj.equals(btnthoat))
		{
			System.exit(0);
		}
		else if(obj.equals(btndonhang))
		{
			setVisible(false);
			new FrmQuanLyHoaDon().setVisible(true);
		}
		else if(obj.equals(btnKH))
		{
			setVisible(false);
			new FrmQuanLyKhachHang().setVisible(true);;
		}
	}

}
