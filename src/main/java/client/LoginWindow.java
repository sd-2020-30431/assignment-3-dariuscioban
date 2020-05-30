package client;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class LoginWindow {
	
	private JFrame frame;
	private ClientConnection cn;
	
	JPanel mainPanel = new JPanel();
	JPanel usernamePanel = new JPanel();
	JPanel passwordPanel = new JPanel();
	
	JTextField usernameField = new JTextField(10);
	JTextField passwordField = new JPasswordField(10);
	
	JButton loginBtn = new JButton("Login");
	JButton registerBtn = new JButton("Register");
	
	public LoginWindow() {
		cn = new ClientConnection();
		cn.addLoginWindow(this);
		initUI();
	}
	
	private void initUI() {
		frame = new JFrame("Wasteless");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(400, 200);
		
		usernameField.setMaximumSize(new Dimension(200,30));
		passwordField.setMaximumSize(new Dimension(200,30));
		
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		usernamePanel.setLayout(new BoxLayout(usernamePanel, BoxLayout.X_AXIS));
		passwordPanel.setLayout(new BoxLayout(passwordPanel, BoxLayout.X_AXIS));
		
		usernamePanel.add(new JLabel("          "));
		usernamePanel.add(new JLabel("Username: "));
		usernamePanel.add(usernameField);
		
		passwordPanel.add(new JLabel("          "));
		passwordPanel.add(new JLabel("Password: "));
		passwordPanel.add(passwordField);
		
		loginBtn.addActionListener(new Clicker(false));
		registerBtn.addActionListener(new Clicker(true));
		
		JPanel filler1 = new JPanel();
		filler1.setPreferredSize(new Dimension(100, 50));
		filler1.setMaximumSize(new Dimension(100, 50));
		
		JPanel filler2 = new JPanel();
		filler2.setPreferredSize(new Dimension(25, 25));
		filler2.setMaximumSize(new Dimension(25, 25));
		
		JPanel filler3 = new JPanel();
		filler3.setPreferredSize(new Dimension(25, 25));
		filler3.setMaximumSize(new Dimension(25, 25));
		
		JPanel filler4 = new JPanel();
		filler3.setPreferredSize(new Dimension(5, 5));
		filler3.setMaximumSize(new Dimension(5, 5));
		
		mainPanel.add(filler1);
		mainPanel.add(usernamePanel);
		mainPanel.add(filler2);
		mainPanel.add(passwordPanel);
		mainPanel.add(filler3);
		mainPanel.add(loginBtn);
		mainPanel.add(filler4);
		mainPanel.add(registerBtn);
		
		frame.setContentPane(mainPanel);
		frame.setVisible(true);
	}
	
	private class Clicker implements ActionListener {
		
		private Boolean register;
		
		public Clicker(Boolean register) {
			this.register = register;
		}

		public void actionPerformed(ActionEvent e) {
			String username = usernameField.getText();
			String password = passwordField.getText();
			if(Validator.validateLoginInfo(username, password)) {
				if(register)
					cn.sendRegisterRequest(username,  password);
				else
					cn.sendLoginRequest(username,  password);
			} else 
			{
				JOptionPane.showMessageDialog(frame, "Invalid format! Username should start with a letter and have no spaces. "
						+ "Password should start with a letter contain a number have no spaces.",
						"Error!", JOptionPane.ERROR_MESSAGE);	
			}
		}

	}
	
	public void loginSuccess(int userId) {
		if(userId == -1) {
			JOptionPane.showMessageDialog(frame, "Username/Password combination invalid!",
					"Error!", JOptionPane.ERROR_MESSAGE);	
		} else {
			MainWindow mw = new MainWindow(userId, cn);
			frame.setVisible(false);
		}
	}
}
