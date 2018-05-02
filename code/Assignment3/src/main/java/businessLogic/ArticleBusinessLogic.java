package businessLogic;

import java.util.List;

import entity.Article;
import repository.ArticleRepo;

public class ArticleBusinessLogic {
	public ArticleRepo arepo=new ArticleRepo();
	public void insertArticle(Article a) {
		arepo.insertArticle(a);
	}
	public void updateArticle(Article a) {
		arepo.updateArticle(a);
	}
	public void deleteArticle(Article a) {
		arepo.deleteArticle(a);
	}
	public List<Article> viewAllArticles() {
		return arepo.viewAllArticles();
	}
	public Article articleGivenName(String name) {
		return arepo.articleGivenName(name);
	}
	public List<Article> allArticlesOfAWriter(int id){
		return arepo.allArticlesOfAWriter(id);
	}
}
