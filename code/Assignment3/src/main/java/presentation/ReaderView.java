package presentation;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JList;

import businessLogic.ArticleBusinessLogic;
import entity.Article;
import javax.swing.JTextPane;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class ReaderView implements Observer {

	final ArticleBusinessLogic alogic=new ArticleBusinessLogic();
	private JFrame frame;
	private JTextField textField_title;
	private JTextField textField_author;
	private JTextField textField_abstract;
	final JTextPane textPane_body = new JTextPane();
	final JScrollPane scrollPane = new JScrollPane();
	
	public ReaderView() {
		initialize();
		frame.setVisible(true);
	}

	private void initialize() {
		
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		textPane_body.setBounds(205, 120, 219, 130);
		frame.getContentPane().add(textPane_body);
		
		JLabel lblTitle = new JLabel("Title: ");
		lblTitle.setBounds(205, 21, 46, 14);
		frame.getContentPane().add(lblTitle);
		
		JLabel lblAuthor = new JLabel("Author: ");
		lblAuthor.setBounds(205, 46, 46, 14);
		frame.getContentPane().add(lblAuthor);
		
		JLabel lblAbstract = new JLabel("Abstract: ");
		lblAbstract.setBounds(205, 71, 62, 14);
		frame.getContentPane().add(lblAbstract);
		
		JLabel lblBody = new JLabel("Body:");
		lblBody.setBounds(205, 106, 46, 14);
		frame.getContentPane().add(lblBody);
		
		textField_title = new JTextField();
		textField_title.setBounds(261, 18, 163, 20);
		frame.getContentPane().add(textField_title);
		textField_title.setColumns(10);
		
		textField_author = new JTextField();
		textField_author.setBounds(261, 43, 163, 20);
		frame.getContentPane().add(textField_author);
		textField_author.setColumns(10);
		
		textField_abstract = new JTextField();
		textField_abstract.setBounds(261, 68, 163, 41);
		frame.getContentPane().add(textField_abstract);
		textField_abstract.setColumns(10);
		
		scrollPane.setBounds(20, 21, 160, 226);
		frame.getContentPane().add(scrollPane);
		
		updateJList();
	}

	private void updateJList() {
		final DefaultListModel<String> listModel = new DefaultListModel<String>();
		List<Article> listarticles=alogic.viewAllArticles();
		for (int i=0;i<listarticles.size();i++) {
			listModel.addElement(listarticles.get(i).getTitle());
		}
		final JList<String> jlist = new JList<String>(listModel);
		scrollPane.setViewportView(jlist);
		listen(jlist);
	}
	
	private void listen(final JList<String> jlist) {
		jlist.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String selected=jlist.getSelectedValue();
				final Article a=alogic.articleGivenName(selected);
				textField_title.setText(a.getTitle());
				textField_abstract.setText(a.getAbstr());
				textPane_body.setText(a.getBody()); 
				textField_author.setText(a.getWriter().getName());
			}
		});
	}
	
	@Override
	public void update(Observable obj, Object arg) {
		updateJList();
	}
}