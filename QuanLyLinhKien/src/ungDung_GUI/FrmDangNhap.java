package ungDung_GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;


public class FrmDangNhap extends JFrame implements  ActionListener,KeyListener {
	private JTextField txtTenDangNhap;
	private JTextField txtMatKhau;
	private JButton btnDangNhap;
	private JButton btnThoat;
	private String tenDangNhap[]= {"nhanvien1","nhanvien2"};
	private String matKhau[]= {"matkhau1","matkhau2"};
	
	public FrmDangNhap() {
		setTitle("Đăng nhập");
		setSize(450, 235);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);

		JPanel p = new JPanel(new BorderLayout());
		this.add(p);
		
		JPanel pTop = new JPanel();
	
		JLabel lblTitle = new JLabel("ĐĂNG NHẬP");
		lblTitle.setFont(new Font("serif", Font.BOLD, 25));	
		lblTitle.setForeground(Color.white);
		pTop.add(lblTitle);
		
		pTop.setBackground(Color.GRAY);
		p.add(pTop, BorderLayout.NORTH);
		
		JPanel pcenter = new JPanel();
		Box b,b1,b2;
		b =Box.createVerticalBox();
		b1=Box.createHorizontalBox();
		b2=Box.createHorizontalBox();
		
		txtTenDangNhap = new JTextField(20);
		ImageIcon imageIcon3 = new ImageIcon("Image/User.png");
		Image image3 =imageIcon3.getImage();
		Image imageResize3 = image3.getScaledInstance(25, 25, Image.SCALE_SMOOTH);
		b.add(Box.createVerticalStrut(10));
		b1.add(new JLabel(new ImageIcon(imageResize3)));
		b1.add(new JLabel("Tên đăng nhập:"));
		b1.add(Box.createHorizontalStrut(5));
		b1.add(txtTenDangNhap);
		b.add(b1);
		
		b.add(Box.createVerticalStrut(20));
		txtMatKhau = new JPasswordField(20);
		ImageIcon imageIcon4 = new ImageIcon("Image/Key.png");
		Image image4 =imageIcon4.getImage();
		Image imageResize4 = image4.getScaledInstance(25, 25, Image.SCALE_SMOOTH);
		b2.add(new JLabel(new ImageIcon(imageResize4)));
		b2.add(new JLabel("Mật khẩu:"));
		b2.add(Box.createHorizontalStrut(37));
		b2.add(txtMatKhau);
		
		b.add(b2);
		b.add(Box.createVerticalStrut(10));
		pcenter.add(b);
		
		
		p.add(pcenter,BorderLayout.CENTER);
		
		JPanel pBot = new JPanel();
		ImageIcon imageIcon1 = new ImageIcon("Image/Login.png");
		Image image1 =imageIcon1.getImage();
		Image imageResize1 = image1.getScaledInstance(20, 20, Image.SCALE_SMOOTH);
		btnDangNhap =new JButton("Đăng nhập",new ImageIcon(imageResize1));
		
		ImageIcon imageIcon2 = new ImageIcon("Image/exitmini.png");
		Image image2 =imageIcon2.getImage();
		Image imageResize2 = image2.getScaledInstance(20, 20, Image.SCALE_SMOOTH);
		btnThoat = new JButton("Thoát",new ImageIcon(imageResize2));
		pBot.add(Box.createHorizontalStrut(100));
		pBot.add(btnDangNhap);
	
		pBot.add(btnThoat);
		pcenter.add(pBot);
		
		btnDangNhap.addActionListener(this);
		btnThoat.addActionListener(this);
		txtTenDangNhap.addKeyListener(this);
		txtMatKhau.addKeyListener(this);
		
	}
	public static void main(String[] args) {
		new FrmDangNhap().setVisible(true);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		
		if (o.equals(btnDangNhap)) {
			String txtTen=txtTenDangNhap.getText();
			String txtPass=txtMatKhau.getText();
			
			int flag=0;
			for (int i = 0; i < tenDangNhap.length; i++) 
				if (txtTen.equals(tenDangNhap[i]) && txtPass.equals(matKhau[i])) 
					flag=1;
			
			if(flag==1) {
				ImageIcon imageIcon5 = new ImageIcon("Image/icon-thanh-cong.png");
				Image image5 =imageIcon5.getImage();
				Image imageResize5 = image5.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
			
				JOptionPane.showMessageDialog(this, "Đăng nhập thành công", "Thông báo",0,new ImageIcon(imageResize5));
				
				new GiaoDienChinh().setVisible(true);
				setVisible(false);
			}			
			else 
				JOptionPane.showMessageDialog(this, "Tài khoản không đúng", "Thông báo",2);
	
		}
		else if(o.equals(btnThoat)) {
			System.exit(0);
		}
		
	}
	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_ENTER) {
			btnDangNhap.doClick();
		}
	}
	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
}
