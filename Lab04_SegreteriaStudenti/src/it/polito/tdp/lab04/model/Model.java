package it.polito.tdp.lab04.model;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import it.polito.tdp.lab04.DAO.CorsoDAO;
import it.polito.tdp.lab04.DAO.StudenteDAO;


public class Model {
	
	private CorsoDAO corsoDao = new CorsoDAO();
	private StudenteDAO studenteDao = new StudenteDAO();

	public List<Corso> getAllCourses() {
		return corsoDao.getTuttiICorsi();
	}

	public List<Studente> getAllStudents() {
		return studenteDao.getTuttiGliStudenti();
	}

	public List<Studente> getStudentiIscritti(Corso corso) {
		List<Studente> matricoleStudentiIscritti = new LinkedList<Studente>(corsoDao.getStudentiIscrittiAlCorso(corso));
		List<Studente> studenti = new LinkedList<Studente>(studenteDao.getTuttiGliStudenti());
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

	public List<Corso> getCorsiSeguiti(Studente studente) {
		List<Corso> codinsCorsi = new LinkedList<Corso>(corsoDao.getCorsiSeguitiDaStudente(studente));
		List<Corso> corsi = new LinkedList<Corso>(corsoDao.getTuttiICorsi());
		List<Corso> corsiSeguiti = new LinkedList<Corso>();
		for(Corso c: corsi) {
			for(Corso c1: codinsCorsi) {
				if(c.getCodins().compareTo(c1.getCodins())==0) {
					corsiSeguiti.add(c);
				}
			}
		}
		return corsiSeguiti;
	}

	public boolean isIscritto(Corso corso, Studente studente) {
		boolean iscritto=false;
		List<Studente> studentiIscritti = new LinkedList<Studente>(corsoDao.getStudentiIscrittiAlCorso(corso));
		for(Studente s: studentiIscritti) {
			if(s.getMatricola()==studente.getMatricola()) {
				iscritto=true;
			}
		}
		return iscritto;
	}

	public void iscriviStudente(Studente s, Corso c) {
		List<Studente> studentiIscritti = new LinkedList<Studente>(corsoDao.getStudentiIscrittiAlCorso(c));
		for(Studente s1: studentiIscritti) {
			if(s1.getMatricola()!=s.getMatricola()) {
				studentiIscritti.add(s);
			}
		}
		
	}

}
