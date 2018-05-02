package entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="article")
public class Article {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public int id;
	@Column(name="title")
	private String title;
	@Column(name="abstract")
	private String abstr;
	@Column(name="body")
	private String body;
	@ManyToOne
	private Writer writer;
	public Article() {
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAbstr() {
		return abstr;
	}
	public void setAbstr(String abstr) {
		this.abstr = abstr;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	public Writer getWriter() {
		return writer;
	}
	public void setWriter(Writer writer) {
		this.writer = writer;
	}
	public int getId() {
		return id;
	}
	@Override
	public String toString() {
		return "Article [id=" + id + ", title=" + title + ", abstr=" + abstr + ", body=" + body + "]";
	}
	
}
