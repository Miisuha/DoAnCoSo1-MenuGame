package Interface;

import java.awt.EventQueue;

public class Main {
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SignIn frame = new SignIn();
					frame.setUndecorated(true);
					frame.setVisible(true);
					frame.setLocationRelativeTo(null);

					frame.RememberMe();
					frame.highlight();

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
