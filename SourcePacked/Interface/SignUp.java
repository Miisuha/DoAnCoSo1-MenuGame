package Interface;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import java.awt.Button;
import java.awt.SystemColor;
import javax.swing.JTextField;
import javax.swing.JSeparator;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.beans.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.LineBorder;

public class SignUp extends JFrame {

	private JPanel contentPane;
	private JTextField usernameField;
	private JTextField emailField;
	private JPasswordField passwordField;
	private JPasswordField repeatpasswordField;

	int xx, xy;

	public SignUp() {
		setUndecorated(true);
		setBackground(Color.WHITE);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 729, 476);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBackground(Color.DARK_GRAY);
		panel.setBounds(0, 0, 346, 490);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel label = new JLabel("");

		label.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {

				xx = e.getX();
				xy = e.getY();
			}
		});
		label.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent arg0) {

				int x = arg0.getXOnScreen();
				int y = arg0.getYOnScreen();
				SignUp.this.setLocation(x - xx, y - xy);
			}
		});
		label.setBounds(-59, -18, 441, 508);
		label.setVerticalAlignment(SwingConstants.TOP);
		label.setIcon(new ImageIcon(SignUp.class.getResource("/images/bg.jpg")));
		panel.add(label);

		Button buttonUp = new Button("SignUp");
		buttonUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!usernameField.getText().isBlank() && !passwordField.getText().isBlank()) {
					try {
						Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
						Connection con = DriverManager.getConnection(
								"jdbc:sqlserver://Miisu:1433;databaseName=KaraokeSongList;integratedSecurity=true",
								"sa", "Hiepbgbg1");

						PreparedStatement sttm = null;

						String sql = "insert into account (username, password) values ('" + usernameField.getText()
								+ "', '" + passwordField.getText() + "')";

						sttm = con.prepareStatement(sql);

						if (passwordField.getText().equals(repeatpasswordField.getText())) {
							if (sttm.executeUpdate() > 0) {

								String tableName = usernameField.getText();

								try {
									Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
									Connection conn = DriverManager.getConnection(
											"jdbc:sqlserver://Miisu:1433;databaseName=KaraokeSongList;integratedSecurity=true",
											"sa", "30062018");
									java.sql.Statement sttmm = conn.createStatement();

									String ssql = "use KaraokeSongList;" + "CREATE TABLE " + tableName
											+ "(playlistName NVARCHAR(50) NOT NULL PRIMARY KEY, " + ")";

									sttmm.executeUpdate(ssql);

								} catch (Exception exception) {
									// TODO: handle exception
									// System.out.println(exception.toString());
								}

								JOptionPane.showMessageDialog(null, "Register successful", "Messages", 1);
							}
						} else
							JOptionPane.showMessageDialog(null, "Wrong password", "Messages", 1);
					} catch (Exception exception) {
						// TODO: handle exception
						JOptionPane.showMessageDialog(null, "Please choose another username", "Messages", 0);
						System.out.print(exception.toString());
					}
				} else
					JOptionPane.showMessageDialog(null, "Vui lòng nhập đầy đủ thông tin", "Messages", 1);
			}
		});
		buttonUp.setForeground(Color.WHITE);
		buttonUp.setBackground(new Color(241, 57, 83));
		buttonUp.setBounds(395, 363, 283, 36);
		contentPane.add(buttonUp);

		usernameField = new JTextField();
		usernameField.setBounds(395, 83, 283, 36);
		contentPane.add(usernameField);
		usernameField.setColumns(10);

		JLabel lblUsername = new JLabel("USERNAME");
		lblUsername.setBounds(395, 58, 114, 14);
		contentPane.add(lblUsername);

		JLabel lblEmail = new JLabel("EMAIL");
		lblEmail.setBounds(395, 132, 54, 14);
		contentPane.add(lblEmail);

		emailField = new JTextField();
		emailField.setColumns(10);
		emailField.setBounds(395, 157, 283, 36);
		contentPane.add(emailField);

		JLabel lblPassword = new JLabel("PASSWORD");
		lblPassword.setBounds(395, 204, 96, 14);
		contentPane.add(lblPassword);

		JLabel lblRepeatPassword = new JLabel("REPEAT PASSWORD");
		lblRepeatPassword.setBounds(395, 275, 133, 14);
		contentPane.add(lblRepeatPassword);

		passwordField = new JPasswordField();
		passwordField.setBounds(395, 229, 283, 36);
		contentPane.add(passwordField);

		repeatpasswordField = new JPasswordField();
		repeatpasswordField.setBounds(395, 293, 283, 36);
		contentPane.add(repeatpasswordField);

		JLabel lbl_close = new JLabel("X");
		lbl_close.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				dispose();
			}
		});
		lbl_close.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_close.setForeground(new Color(241, 57, 83));
		lbl_close.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lbl_close.setBounds(676, 11, 37, 27);
		contentPane.add(lbl_close);

		Button buttonBack = new Button("Back");
		buttonBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		buttonBack.setForeground(Color.WHITE);
		buttonBack.setBackground(new Color(241, 57, 83));
		buttonBack.setBounds(395, 405, 283, 36);
		contentPane.add(buttonBack);
	}
}