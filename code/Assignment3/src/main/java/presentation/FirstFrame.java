package presentation;

import javax.swing.JFrame;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import businessLogic.WriterBusinessLogic;
import entity.Writer;

import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.List;
import java.awt.event.ActionEvent;

public class FirstFrame extends JFrame implements ActionListener {

	private JFrame frame;
	private WriterBusinessLogic wlogic = new WriterBusinessLogic();
	private Writer sessionWriter = new Writer();
	private WriterView sessionView;
	private JTextField textField_newname;
	private JTextField textField_newusername;
	private JTextField textField_newpassword;
	public String response;
	public JButton btnWriter = new JButton("Writer");
	public JButton btnReader = new JButton("Reader");
	public JButton btnRegister = new JButton("Register");
	public JButton btnConnect = new JButton("Connect");

	public FirstFrame() {
		initialize();
		frame.setVisible(true);
	}

	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 189);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		// or here
		frame.setBounds(100, 100, 450, 286);
		final JLabel lblName = new JLabel("Name: ");
		lblName.setBounds(55, 200, 64, 14);
		frame.getContentPane().add(lblName);

		final JLabel lblUsername_1 = new JLabel("Username: ");
		lblUsername_1.setBounds(55, 150, 68, 14);
		frame.getContentPane().add(lblUsername_1);

		final JLabel lblPassword_1 = new JLabel("Password: ");
		lblPassword_1.setBounds(55, 175, 64, 14);
		frame.getContentPane().add(lblPassword_1);

		textField_newname = new JTextField();
		textField_newname.setBounds(142, 202, 124, 20);
		frame.getContentPane().add(textField_newname);
		textField_newname.setColumns(10);

		textField_newusername = new JTextField();
		textField_newusername.setBounds(142, 152, 124, 20);
		frame.getContentPane().add(textField_newusername);
		textField_newusername.setColumns(10);

		textField_newpassword = new JTextField();
		textField_newpassword.setBounds(142, 177, 124, 20);
		frame.getContentPane().add(textField_newpassword);
		textField_newpassword.setColumns(10);

		btnConnect.setBounds(299, 151, 89, 23);
		frame.getContentPane().add(btnConnect);

		btnWriter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setBounds(100, 100, 450, 290);

				btnConnect.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						try {
							if ((textField_newusername.getText().isEmpty())
									|| (textField_newpassword.getText().isEmpty())) {
								JOptionPane.showMessageDialog(null, "Please introduce both username and password");
							} else {
								List<String> usernames = wlogic.writerUsernames();
								List<String> passwords = wlogic.writerPasswords();
								boolean found = false;
								for (int i = 0; i < usernames.size(); i++) {
									String auxEmail = (String) usernames.get(i);
									String auxPass = (String) passwords.get(i);
									if ((auxEmail.equals(textField_newusername.getText()))
											&& (auxPass.equals(textField_newpassword.getText()))) {
										found = true;
									}
								}
								if (found) {
									response = "Writer";
									Writer w = new Writer();
									w = wlogic.writerGivenUsername(textField_newusername.getText());
									sessionWriter = w;
									sessionView = new WriterView(sessionWriter);
									frame.setBounds(100, 100, 450, 189);
								} else {
									JOptionPane.showMessageDialog(null, "Username or password incorrect");
								}
							}
						} catch (Exception e1) {
							JOptionPane.showMessageDialog(null, "Log-in failed");
						}
					}
				});
			}
		});
		btnWriter.setBounds(264, 54, 89, 23);
		frame.getContentPane().add(btnWriter);

		btnReader.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				response = "Reader";
				ReaderView reader = new ReaderView();
				sessionView.addObserver(reader);
			}
		});
		btnReader.setBounds(55, 54, 89, 23);
		frame.getContentPane().add(btnReader);

		btnRegister.addActionListener(this);
		/*
		 * btnRegister.addActionListener(new ActionListener() { public void
		 * actionPerformed(ActionEvent e) { if (textField_newname.getText().isEmpty() ||
		 * textField_newusername.getText().isEmpty() ||
		 * textField_newpassword.getText().isEmpty()) {
		 * JOptionPane.showMessageDialog(null, "Please introduce all data"); } else {
		 * Writer newW = new Writer(); newW.setName(textField_newname.getText());
		 * newW.setUsername(textField_newusername.getText());
		 * newW.setPassword(textField_newpassword.getText()); wlogic.insertWriter(newW);
		 * frame.getContentPane().remove(textField_newname);
		 * frame.getContentPane().remove(textField_newusername);
		 * frame.getContentPane().remove(textField_newpassword);
		 * frame.getContentPane().remove(lblName);
		 * frame.getContentPane().remove(lblUsername_1);
		 * frame.getContentPane().remove(lblPassword_1);
		 * frame.getContentPane().remove(btnRegister); frame.setBounds(100, 100, 450,
		 * 189); } } });
		 */
		btnRegister.setBounds(299, 191, 89, 23);
		frame.getContentPane().add(btnRegister);
	}

	public static void main(String[] args) {
		new FirstFrame();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(btnRegister)) {
			try {
				processInformationRegister();
			} catch (UnknownHostException e1) {
				e1.printStackTrace();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}

	public void processInformationRegister() throws UnknownHostException, IOException {
		Socket s = new Socket("localhost", 5056);
		ObjectOutputStream p = new ObjectOutputStream(s.getOutputStream());
		Writer newW = new Writer();
		newW.setName(textField_newname.getText());
		newW.setUsername(textField_newusername.getText());
		newW.setPassword(textField_newpassword.getText());
		//p.writeObject(newW);
		
		 String name="\"".concat(textField_newname.getText()).concat("\""); String
		 username="\"".concat(textField_newusername.getText()).concat("\""); String
		 password="\"".concat(textField_newpassword.getText()).concat("\"");
		 
		 ObjectMapper mapper = new ObjectMapper();
		 
		 String jsonString =
		 "{\"name\":"+name+", \"username\":"+username+", \"password\":"+password+"}";
		 p.writeObject(jsonString); /////// 
		 try{ Writer writer = mapper.readValue(jsonString, Writer.class); System.out.println(writer);
		 jsonString =
		 mapper.writerWithDefaultPrettyPrinter().writeValueAsString(writer);
		 System.out.println(jsonString); 
		 } catch (JsonParseException e) {
		 e.printStackTrace();} catch (JsonMappingException e) { e.printStackTrace(); }
		 catch (IOException e) { e.printStackTrace(); } 
		 
		p.flush();
		BufferedReader response = new BufferedReader(new InputStreamReader(s.getInputStream()));
		System.out.println("The server response: " + response.readLine());
		p.close();
		response.close();
		s.close();
	}
	/*
	 * public void processInformationReader() throws UnknownHostException,
	 * IOException { Socket s = new Socket("localhost", 5056); ObjectOutputStream p
	 * = new ObjectOutputStream(s.getOutputStream()); Article newa= new Article();
	 * newa.setTitle("Title"); newa.setAbstr("Abstract"); newa.setBody("Body");
	 * String command="Reader"; p.writeObject(command); p.flush(); BufferedReader
	 * response = new BufferedReader(new InputStreamReader( s.getInputStream()));
	 * System.out.println("The server response: "+response.readLine()); p.close();
	 * response.close(); s.close(); }
	 */
}
