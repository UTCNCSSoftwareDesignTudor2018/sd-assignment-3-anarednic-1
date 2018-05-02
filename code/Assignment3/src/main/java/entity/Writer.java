package entity;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="writer")
public class Writer implements Serializable{
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
    public int id;
	@Column(name="name")
	private String name;
	@Column(name="username")
	private String username;
	@Column(name="password")
	private String password;
	@OneToMany(mappedBy="writer",cascade=CascadeType.ALL)
	private List<Article> listOfArticles = new LinkedList<Article>();
	public Writer() {
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public List<Article> getListOfArticles() {
		return listOfArticles;
	}
	public void setListOfArticles(List<Article> listOfArticles) {
		this.listOfArticles = listOfArticles;
	}
	public int getId() {
		return id;
	}
	public String toString(){
	      return "Writer [ name: "+name+", username: "+ username+ ", password: "+password+" ]";
	   }
}
