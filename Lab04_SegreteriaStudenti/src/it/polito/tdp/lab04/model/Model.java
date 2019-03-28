package it.polito.tdp.lab04.model;

import java.util.LinkedList;
import java.util.List;

import it.polito.tdp.lab04.DAO.CorsoDAO;
import it.polito.tdp.lab04.DAO.StudenteDAO;

public class Model {

	private StudenteDAO studenteDao= new StudenteDAO();
	private CorsoDAO corsoDao=new CorsoDAO();
	
	
	public List<Studente> getAllStudents(){
		return studenteDao.getTuttiGliStudenti();
	}

	public List<Corso> getAllCourses() {
		return corsoDao.getTuttiICorsi();
	}

	public List<Studente> cercaIscritti(Corso corso) {
		List<Studente> matricoleStudentiIscritti = corsoDao.getStudentiIscrittiAlCorso(corso);
		List<Studente> studenti = studenteDao.getTuttiGliStudenti();
		List<Studente> studentiIscritti = new LinkedList<Studente>();
		for(Studente s: studenti) {
			for(Studente s1: matricoleStudentiIscritti) {
				if(s.getMatricola()==s1.getMatricola()) {
					studentiIscritti.add(s);
				}
			}
		}
		return studentiIscritti;
	}

	public boolean isIscritto(Corso corso, Studente studente) {
		boolean iscritto=false;
		List<Studente> matricoleStudentiIscritti = corsoDao.getStudentiIscrittiAlCorso(corso);
		for(Studente s: matricoleStudentiIscritti) {
			if(s.getMatricola()==studente.getMatricola()) {
				iscritto=true;
			}
		}
		return iscritto;
	}

	public void iscriviStudente(Corso corso, Studente studente) {
		List<Studente> matricoleStudentiIscritti = corsoDao.getStudentiIscrittiAlCorso(corso);
		for(Studente s : matricoleStudentiIscritti) {
			if(s.getMatricola()!=studente.getMatricola()) {
				matricoleStudentiIscritti.add(studente);
			}
		}
	}

}
