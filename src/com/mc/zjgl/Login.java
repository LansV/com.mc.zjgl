package com.mc.zjgl;

import java.awt.Container;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class Login extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	int qx;
	final static String Version = "V1.170212";
	String UserName;
	String PcName = null;
	String PcMac = null;
	boolean EqualsVersion = false;

	public Login() {
		JFrame jf=this;
		setAlwaysOnTop(true);
		Point p = GraphicsEnvironment.getLocalGraphicsEnvironment().getCenterPoint();
		ArrayList<String> sinfo = ServerIP.getServerInfo(jf);
		// -----------------------------------------------
		JFrame changePassW = new JFrame("请输入密码");
		changePassW.setResizable(false);
		changePassW.setAlwaysOnTop(true);
		changePassW.setBounds(p.x - 230 / 2, p.y - 160 / 2, 230, 160);
		Container changePassW_Content = changePassW.getContentPane();
		changePassW_Content.setLayout(null);
		JLabel changePassW_L1 = new JLabel("输入新密码:");
		changePassW_L1.setBounds(5, 15, 120, 24);
		changePassW_Content.add(changePassW_L1);
		JTextField changePassW_T1 = new JPasswordField();
		changePassW_T1.setBounds(80, 15, 130, 24);
		changePassW_Content.add(changePassW_T1);
		JLabel changePassW_L2 = new JLabel("确认新密码:");
		changePassW_L2.setBounds(5, 55, 120, 24);
		changePassW_Content.add(changePassW_L2);
		JTextField changePassW_T2 = new JPasswordField();
		changePassW_T2.setBounds(80, 55, 130, 24);
		changePassW_Content.add(changePassW_T2);
		JButton changePassW_B = new JButton("更改");
		changePassW_B.setBounds(85, 95, 60, 25);
		changePassW_Content.add(changePassW_B);
		changePassW_T2.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				if (e.getKeyCode() == '\n') {
					changePassW_B.doClick();
				}
			}
		});
		// ------------------------------------------------------------------------------
			InetAddress ia = null;
			try {
				ia = InetAddress.getLocalHost();
				String n = ia.toString();
				String[] s = n.split("/");
				PcName = s[0];
				// System.out.println(PcName);
			} catch (UnknownHostException e2) {
				// TODO Auto-generated catch block
				PcName = "";
			}
			try {
				byte[] mac = NetworkInterface.getByInetAddress(ia).getHardwareAddress();
				// System.out.println("mac数组长度："+mac.length);
				StringBuffer sb = new StringBuffer("");
				for (int i = 0; i < mac.length; i++) {
					if (i != 0) {
						sb.append("-");
					}
					// 字节转换为整数
					int temp = mac[i] & 0xff;
					String str = Integer.toHexString(temp);
					if (str.length() == 1) {
						sb.append("0" + str);
					} else {
						sb.append(str);
					}
				}
				PcMac = sb.toString().toUpperCase();
				// System.out.println(PcMac);
			} catch (Exception e) {
				PcMac = "";
				JOptionPane.showMessageDialog(this, "适配器被禁用或出现问题\n请检查后重试");
				System.exit(0);
			}
			try {
				Image img = Toolkit.getDefaultToolkit().getImage("order/Image/TLogo.png");
				this.setIconImage(img);
			} catch (Exception e) {

			}
			setResizable(false);
			setLayout(null);
			setBounds(100, 100, 300, 200);
			setBounds(p.x - 300 / 2, p.y - 200 / 2, 300, 200);
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			Container c = getContentPane();
			setTitle("用户登录");
			JLabel JL_user = new JLabel("账号：");
			JL_user.setBounds(50, 20, 40, 20);
			JLabel JL_pass = new JLabel("密码：");
			JL_pass.setBounds(50, 65, 40, 20);
			JTextField JT_user = new JTextField();
			JT_user.setText(sinfo.get(1));
			JT_user.setBounds(95, 20, 140, 24);
			JPasswordField JT_pass = new JPasswordField();
			JT_pass.setBounds(95, 65, 140, 24);
			JButton JB_login = new JButton("登陆");
			JB_login.setBounds(50, 140, 60, 25);
			JLabel serveripL=new JLabel("服务器IP：");
			serveripL.setBounds(50, 100, 80, 25);
			c.add(serveripL);
			JTextField serveripT=new JTextField();
			serveripT.setBounds(115, 100, 120, 25);
			serveripT.setText(sinfo.get(0));
			c.add(serveripT);
			JB_login.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					String user = JT_user.getText().trim();
					String pass = new String(JT_pass.getPassword());
					try {
						if (user.length() == 0 || pass.length() == 0||serveripT.getText().length()==0) {
							if(serveripT.getText().length()==0){
								JOptionPane.showMessageDialog(c, "服务器IP不能为空");
								serveripT.requestFocus();
							}
							if (user.length() == 0 && pass.length() == 0) {
								JOptionPane.showMessageDialog(c, "用户名与密码不能为空");
								JT_user.requestFocus();
							} else {
								if (user.length() == 0) {
									JOptionPane.showMessageDialog(c, "用户名不能为空");
									JT_user.requestFocus();
								} else {
									if (pass.length() == 0) {
										JOptionPane.showMessageDialog(c, "密码不能为空");
										JT_pass.requestFocus();
									}
								}
							}
						} else {
							String ip=serveripT.getText();
							new SQLFilter(JB_login, user + ";" + pass+";"+ip, user);
							if(isRightIp(ip)){
								ServerIP.writeSeverIP(jf,  "IP:"+ip+"\r\nUser:"+user);
								ServerIP.getServerInfo(jf);
								Dao d = new Dao();
								System.out.println("d");
								Connection con = d.getcon();
								Statement sql = con.createStatement();
								ResultSet res = sql.executeQuery("select*from UserB where username='" + user + "'");
								if (res.next()) {
									String xuser = res.getString("username").trim();
									String xpass = res.getString("pass").trim();
									int id = res.getInt("uid");
									int qx=res.getInt("qx");
									if (user.equals(xuser) && pass.equals(xpass)) {
										if (xpass.equals("123456")) {
											JOptionPane.showMessageDialog(c, "\t初始密码\n请修改密码");
											changePassW.setVisible(true);
										} else {
											if (CheckDate.ReturnCheckDateResult(JB_login) == true) {
												sql.execute("insert into LoginLog(UserId,Pc_name,Pc_Mac) values(" + id
														+ ",'" + PcName + "','" + PcMac + "')");
												dispose();
												new ExpertManage(user,qx);
											}
										}
									} else {
										JOptionPane.showMessageDialog(c, "用户名或密码错误！");
										JT_pass.setText("");
										JT_pass.requestFocus();
									}
								} else {
									JOptionPane.showMessageDialog(c, "用户名或密码错误！");
									JT_user.setText("");
									JT_pass.setText("");
									JT_user.requestFocus();
								}
							}else{
								JOptionPane.showMessageDialog(c, "IP有误");
								serveripT.requestFocus();
							}
						}
						;
					} catch (Exception e1) {
						JOptionPane.showMessageDialog(c, "数据库链接故障！请与管理员联系");
					}
				}

			});
			JT_pass.addKeyListener(new KeyAdapter() {
				public void keyTyped(KeyEvent e) {
					if (e.getKeyChar() == '\n')
						JB_login.doClick();
				}
			});
			JButton JB_exit = new JButton("更改密码");
			JB_exit.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					String user = JT_user.getText().trim();
					String pass = new String(JT_pass.getPassword());
					try {
						if (user.length() == 0 || pass.length() == 0) {
							if (user.length() == 0 && pass.length() == 0) {
								JOptionPane.showMessageDialog(c, "先输入账号密码后更改密码！");
								JT_user.requestFocus();
							} else {
								if (user.length() == 0) {
									JOptionPane.showMessageDialog(c, "用户名不能为空");
									JT_user.requestFocus();
								} else {
									if (pass.length() == 0) {
										JOptionPane.showMessageDialog(c, "密码不能为空");
										JT_pass.requestFocus();
									}
								}
							}
						} else {
							Dao d = new Dao();
							Connection con = d.getcon();
							Statement sql = con.createStatement();
							ResultSet res = sql.executeQuery("select*from UserB where username='" + user + "'");
							if (res.next()) {
								String xuser = res.getString("username").trim();
								String xpass = res.getString("pass").trim();
								// int id=res.getInt("id");
								if (user.equals(xuser) && pass.equals(xpass)) {
									changePassW.setVisible(true);
								} else {
									JOptionPane.showMessageDialog(c, "用户名或密码错误！");
									JT_pass.setText("");
									JT_pass.requestFocus();
								}
							} else {
								JOptionPane.showMessageDialog(c, "用户名或密码错误！");
								JT_user.setText("");
								JT_pass.setText("");
								JT_user.requestFocus();
							}
						}
						;
					} catch (Exception e1) {
						JOptionPane.showMessageDialog(c, "数据库链接故障！请与管理员联系");
					}
				}
			});
			JB_exit.setBounds(180, 140, 90, 25);
			changePassW_B.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					if (changePassW_T1.getText().equals(changePassW_T2.getText())) {
						if (changePassW_T2.getText().equals(new String(JT_pass.getPassword()))) {
							JOptionPane.showMessageDialog(changePassW, "新密码与旧密码不能相同！");
						} else {
							try {
								Dao d = new Dao();
								Connection con = d.getcon();
								Statement sql = con.createStatement();
								sql.execute("update UserB set pass='" + changePassW_T2.getText() + "' where "
										+ "username='" + JT_user.getText().trim() + "'");
								JOptionPane.showMessageDialog(changePassW, "修改成功");
								changePassW_T1.setText("");
								changePassW_T2.setText("");
								JT_pass.setText("");
								changePassW.dispose();
							} catch (Exception e1) {
								JOptionPane.showMessageDialog(changePassW, "修改密码错误");
							}
						}
					} else {
						JOptionPane.showMessageDialog(changePassW, "请确保两次输入一致");
					}
				}
			});
			changePassW.addWindowListener(new WindowAdapter() {
				public void windowClosing(WindowEvent arg0) {
					changePassW.dispose();
				}
			});
			c.add(JB_exit);
			c.add(JB_login);
			c.add(JT_pass);
			c.add(JT_user);
			c.add(JL_pass);
			c.add(JL_user);
			setVisible(true);
			if(sinfo.get(1).length()!=0){
				JT_pass.requestFocus();
			}

	}

	public static void main(String[] args) {
		new Login();
	}
	private boolean isRightIp(String ipAddress){  
          
	        String ips[] = ipAddress.split("\\.");  
	          
	        if(ips.length==4){  
	            for(String ip : ips){  
	                System.out.println(ip);  
	                if(Integer.parseInt(ip)<0||Integer.parseInt(ip)>255){  
	                    return false;  
	                }  
	            }  
	            return true;  
	        }else{  
	             return false;  
	        }  
	  
	    }  
}