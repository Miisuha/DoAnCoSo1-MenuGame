package Interface;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import Interface.SignIn;
import flappybird.FlappyBird;
import snake.RanAnMoi;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MenuInterface extends JFrame {

	private JPanel contentPane;
	private String username = SignIn.usernameField.getText();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MenuInterface frame = new MenuInterface();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MenuInterface() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 592, 414);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton buttonBird = new JButton("Play Flappy Bird");
		buttonBird.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new FlappyBird();
			}
		});
		buttonBird.setBounds(96, 111, 148, 56);
		contentPane.add(buttonBird);
		
		JButton buttonSnake = new JButton("Play Snake");
		buttonSnake.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new RanAnMoi();
			}
		});
		buttonSnake.setBounds(367, 111, 148, 56);
		contentPane.add(buttonSnake);
		
		JButton logoutButton = new JButton("");
		logoutButton.setBorder(new LineBorder(new Color(0, 206, 209), 1, true));
		logoutButton.setIgnoreRepaint(true);
		logoutButton.setInheritsPopupMenu(true);
		logoutButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				SignIn frame = new SignIn();
				frame.setUndecorated(true);
				frame.setVisible(true);
				frame.setLocationRelativeTo(null);

				frame.RememberMe();
				frame.highlight();
			}
		});

		ImageIcon out = new ImageIcon(MenuInterface.class.getResource("/images/67d96d458abdef21792e6d8e590244e7.png"));
		Image iconOut = out.getImage();
		Image modifyiconOut = iconOut.getScaledInstance(35, 35, java.awt.Image.SCALE_SMOOTH);
		out = new ImageIcon(modifyiconOut);
		logoutButton.setBounds(523, 321, 43, 43);
		logoutButton.setIcon(out);
		contentPane.add(logoutButton);
	}
}
