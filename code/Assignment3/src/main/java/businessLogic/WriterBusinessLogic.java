package businessLogic;

import java.util.List;

import entity.Writer;
import repository.WriterRepo;

public class WriterBusinessLogic {
	public WriterRepo wrepo=new WriterRepo();
	public void insertWriter(Writer w) {
		wrepo.insertWriter(w);
	}
	public void updateWriter(Writer w) {
		wrepo.updateWriter(w);
	}
	public void deleteWriter(Writer w) {
		wrepo.deleteWriter(w);
	}
	public List<Writer> viewAllWriters() {
		return wrepo.viewAllWriters();
	}
	public Writer writerGivenUsername(String name) {
		return wrepo.writerGivenUsername(name);
	}
	public List<String> writerUsernames(){
		return wrepo.writerUsernames();
	}
	public List<String> writerPasswords(){
		return wrepo.writerPasswords();
	}
}
