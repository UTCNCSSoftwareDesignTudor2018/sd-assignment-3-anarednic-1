package appStart;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import businessLogic.ArticleBusinessLogic;
import businessLogic.WriterBusinessLogic;
import entity.Article;
import entity.Writer;
import presentation.FirstFrame;
import presentation.ReaderView;
import presentation.WriterView;

public class AppStart {
	public static void main(String[] args) {
		EntityManagerFactory entityManagerFactory = Persistence
				.createEntityManagerFactory("org.hibernate.tutorial.jpa");
		EntityManager eniEntityManager = entityManagerFactory.createEntityManager();
		FirstFrame f=new FirstFrame();
		System.out.println(f.response);
/*
		// writers*/
		WriterBusinessLogic wlogic = new WriterBusinessLogic();/*
		Writer w = new Writer();
		w.setName("Ana");
		w.setUsername("ana");
		w.setPassword("ana");
		wlogic.insertWriter(w);
		Writer w2 = new Writer();
		w2.setName("Paula");
		w2.setUsername("paula");
		w2.setPassword("paula");
		wlogic.insertWriter(w2);
		Writer w3 = new Writer();
		w3.setName("Ioana");
		w3.setUsername("ioana");
		w3.setPassword("ioana");
		wlogic.insertWriter(w3);

		// articles*/
		ArticleBusinessLogic alogic = new ArticleBusinessLogic();/*
		Article a = new Article();
		a.setTitle("Ana Article");
		a.setAbstr("Ana's Abstract");
		a.setBody("Ana's Body");
		a.setWriter(w);
		// a.id=1;
		// alogic.deleteArticle(a);
		alogic.insertArticle(a);
		// alogic.updateArticle(a);
		Article a2 = new Article();
		a2.setTitle("Paula Article");
		a2.setAbstr("Paula's Abstract");
		a2.setBody("Paula's Body");
		a2.setWriter(w2);
		alogic.insertArticle(a2);
		Article a3 = new Article();
		a3.setTitle("Ioana Article");
		a3.setAbstr("Ioana's Abstract");
		a3.setBody("Ioana's Body");
		a3.setWriter(w3);
		alogic.insertArticle(a3);
		*/
		/*
		Article a4 = new Article();
		a4.id=338;
		alogic.deleteArticle(a4);*/
		/*Writer w4 = new Writer();
		w4.id=328;
		wlogic.deleteWriter(w4);*/	
		/*Article a5 = new Article();
		a5.id=332;
		alogic.deleteArticle(a5);*/
	}
}
