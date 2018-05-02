package presentation;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JTextPane;

import businessLogic.ArticleBusinessLogic;
import entity.Article;
import entity.Writer;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class WriterView extends Observable {

	private ArrayList<Observer> listObservers = new ArrayList<Observer>();
	private JFrame frame;
	Writer sessionWriter=new Writer();
	final ArticleBusinessLogic alogic=new ArticleBusinessLogic();
	final JScrollPane scrollPane = new JScrollPane();
	final JTextField textField_title=new JTextField();
	final JTextPane textPane_abstract = new JTextPane();
	final JTextPane textPane_body = new JTextPane();
	Article selectedArticle = new Article();
	public WriterView(Writer sessionWriter) {
		this.sessionWriter=sessionWriter;
		initialize();
		frame.setVisible(true);
	}

	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblTitle = new JLabel("Title:");
		lblTitle.setBounds(25, 50, 46, 14);
		frame.getContentPane().add(lblTitle);
		
		JLabel lblBody = new JLabel("Body:");
		lblBody.setBounds(25, 114, 46, 14);
		frame.getContentPane().add(lblBody);
		
		textField_title.setBounds(81, 47, 114, 20);
		frame.getContentPane().add(textField_title);
		textField_title.setColumns(10);
		
		textPane_body.setBounds(25, 132, 169, 118);
		frame.getContentPane().add(textPane_body);
		
		JLabel lblWriter = new JLabel("Writer:");
		lblWriter.setBounds(25, 25, 46, 14);
		frame.getContentPane().add(lblWriter);
		
		JLabel lblAbstract = new JLabel("Abstract: ");
		lblAbstract.setBounds(25, 75, 68, 14);
		frame.getContentPane().add(lblAbstract);
		
		textPane_abstract.setBounds(81, 78, 114, 43);
		frame.getContentPane().add(textPane_abstract);
		
		JLabel label_writer = new JLabel("");
		label_writer.setBounds(81, 25, 114, 14);
		frame.getContentPane().add(label_writer);
		label_writer.setText(sessionWriter.getName());
						
		scrollPane.setBounds(230, 132, 169, 118);
		frame.getContentPane().add(scrollPane);
		
		updateJList();
		
		//update, delete selected
		JButton btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selectedArticle.setTitle(textField_title.getText());
				selectedArticle.setAbstr(textPane_abstract.getText());
				selectedArticle.setBody(textPane_body.getText());
				alogic.updateArticle(selectedArticle);
				updateJList();
				notifyObserver();
			}
		});
		btnUpdate.setBounds(230, 85, 77, 23);
		frame.getContentPane().add(btnUpdate);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				alogic.deleteArticle(selectedArticle);
				updateJList();
				textField_title.setText(null);
				textPane_abstract.setText(null);
				textPane_body.setText(null);
				notifyObserver();
			}
		});
		btnDelete.setBounds(329, 85, 77, 23);
		frame.getContentPane().add(btnDelete);
		
		JButton btnPostArticle = new JButton("Post Article");
		btnPostArticle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Article a=new Article();
				a.setWriter(sessionWriter);
				a.setTitle(textField_title.getText());
				a.setAbstr(textPane_abstract.getText());
				a.setBody(textPane_body.getText());
				alogic.insertArticle(a);
				updateJList();
				notifyObserver();
			}
		});
		btnPostArticle.setBounds(230, 46, 176, 23);
		frame.getContentPane().add(btnPostArticle);
		
	}
	
	private void updateJList() {
		final DefaultListModel<String> listModel = new DefaultListModel<String>();
		List<Article> listarticles=alogic.allArticlesOfAWriter(sessionWriter.getId());
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
				textPane_abstract.setText(a.getAbstr());
				textPane_body.setText(a.getBody()); 
				selectedArticle = a;
			}
		});
	}

	@Override
	public void addObserver(Observer o) {
		listObservers.add(o);
	}

	public void removeObserver(Observer o) {
		listObservers.remove(o);		
	}

	public void notifyObserver() {		
		for (Observer reader :listObservers) {
			reader.update(null, null);
		}
	}
}
