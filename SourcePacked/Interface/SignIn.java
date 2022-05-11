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
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.prefs.Preferences;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;
import javax.swing.JCheckBox;

public class SignIn extends JFrame {

	private JPanel contentPane;
	public static JTextField usernameField;
	private JPasswordField passwordField;
	private JCheckBox checkRememberMe;

	int xx, xy;

	Preferences preferences;
	boolean rememberPreferences;

	public void RememberMe() {
		preferences = Preferences.userNodeForPackage(this.getClass());
		rememberPreferences = preferences.getBoolean("rememberMe", Boolean.valueOf(""));
		if (rememberPreferences) {
			usernameField.setText(preferences.get("Username", ""));
			passwordField.setText(preferences.get("password", ""));
			checkRememberMe.setSelected(rememberPreferences);
		}
	}

	public void highlight() {
		if (!usernameField.getText().equals("User") && !passwordField.getText().equals("password")) {
			usernameField.setForeground(Color.BLACK);
			passwordField.setForeground(Color.BLACK);
		}
	}

	public SignIn() {

		setBackground(Color.WHITE);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 729, 476);

		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(0, 0, 0)));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		checkRememberMe = new JCheckBox("Remember me");
		checkRememberMe.setBackground(Color.WHITE);
		checkRememberMe.setBounds(394, 237, 115, 23);
		contentPane.add(checkRememberMe);

		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 346, 490);
		panel.setBackground(Color.DARK_GRAY);
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
				SignIn.this.setLocation(x - xx, y - xy);
			}
		});
		label.setBounds(-59, -18, 441, 508);
		label.setVerticalAlignment(SwingConstants.TOP);
		label.setIcon(new ImageIcon(SignUp.class.getResource("/images/bg.jpg")));
		panel.add(label);

		Button buttonUp = new Button("SignUp");
		buttonUp.setBounds(395, 369, 283, 36);
		buttonUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SignUp signup = new SignUp();
				signup.setVisible(true);
				signup.setLocationRelativeTo(null);
			}
		});
		buttonUp.setForeground(Color.WHITE);
		buttonUp.setBackground(new Color(241, 57, 83));
		contentPane.add(buttonUp);

		usernameField = new JTextField();
		usernameField.setBounds(395, 113, 283, 36);
		usernameField.setFont(new Font("Tahoma", Font.BOLD, 14));
		contentPane.add(usernameField);
		usernameField.setColumns(10);

		JLabel lblUsername = new JLabel("USERNAME");
		lblUsername.setBounds(395, 88, 114, 14);
		contentPane.add(lblUsername);

		JLabel lblPassword = new JLabel("PASSWORD");
		lblPassword.setBounds(395, 160, 96, 14);
		contentPane.add(lblPassword);

		passwordField = new JPasswordField();
		passwordField.setBounds(395, 185, 283, 36);
		passwordField.setFont(new Font("Tahoma", Font.PLAIN, 14));
		contentPane.add(passwordField);

		JLabel lbl_close = new JLabel("X");
		lbl_close.setBounds(676, 11, 37, 27);
		lbl_close.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {

				System.exit(0);
			}
		});
		lbl_close.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_close.setForeground(new Color(241, 57, 83));
		lbl_close.setFont(new Font("Tahoma", Font.PLAIN, 18));
		contentPane.add(lbl_close);

		Button buttonIn = new Button("Sign In");

		buttonIn.setBounds(395, 279, 283, 36);
		buttonIn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {

					Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
					Connection con = DriverManager.getConnection(
							"jdbc:sqlserver://Miisu:1433;databaseName=KaraokeSongList;integratedSecurity=true", "sa",
							"Hiepbgbg1");

					Statement sttm = con.createStatement();

					String sql = "select * from account where username='" + usernameField.getText() + "' and password='"
							+ passwordField.getText() + "'";

					ResultSet rs = sttm.executeQuery(sql);

					if (rs.next()) {
						if (checkRememberMe.isSelected() && !rememberPreferences) {
							preferences.put("Username", usernameField.getText());
							preferences.put("password", passwordField.getText());
							preferences.putBoolean("rememberMe", true);
						} else if (!checkRememberMe.isSelected() && rememberPreferences) {
							preferences.put("Username", "");
							preferences.put("password", "");
							preferences.putBoolean("rememberMe", false);
						}

						JOptionPane.showMessageDialog(null, "Welcome back, " + usernameField.getText(),
								"Loggin Successful", 1);
						dispose();
						MenuInterface frame = new MenuInterface();
						frame.setVisible(true);
						frame.setLocationRelativeTo(null);

					} else {
						JOptionPane.showMessageDialog(null, "Wrong Username or Password!", "Error", 0);
					}
				} catch (Exception exception) {
					// TODO: handle exception
					exception.printStackTrace();
					System.out.println("Error");
				}

			}
		});
		buttonIn.setForeground(Color.WHITE);
		buttonIn.setBackground(new Color(241, 57, 83));
		contentPane.add(buttonIn);

		JLabel lblNewLabel = new JLabel("____Doesn't have Account?____");
		lblNewLabel.setForeground(Color.BLUE);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel.setBounds(423, 336, 260, 27);
		contentPane.add(lblNewLabel);
	}
}