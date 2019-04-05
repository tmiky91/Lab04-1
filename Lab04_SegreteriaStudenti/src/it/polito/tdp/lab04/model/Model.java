package it.polito.tdp.lab04.model;

import java.util.LinkedList;
import java.util.List;

import it.polito.tdp.lab04.DAO.CorsoDAO;
import it.polito.tdp.lab04.DAO.StudenteDAO;

public class Model {
	
	
	/*
	 * Prende la stringa e con matches controlla se ci sono dei match con il pattern scelto da me (\\d{6}). 
	 * In questo caso io gli chiedo con /d di cercare numeri, sapendo che la matricola è composta da 6 numeri 
	 * gli specifico che voglio che trovi 6 numeri di seguito.
	 * In questo caso il metodo ritornerà true altrimenti ritorna false.
	 */
	public boolean isDigit(String matricola) {
		return matricola.matches("\\d{6}");
	}

	public Studente getStudentInfoByInput(String matricola) {
		StudenteDAO dao = new StudenteDAO();
		return dao.getStudentById(Integer.parseInt(matricola));
	}

	public String getElencoCorsi(String matricola) {
		CorsoDAO dao = new CorsoDAO();
		List<Corso> corsi = new LinkedList<Corso>(dao.getElencoCorsi(Integer.parseInt(matricola)));
		String elencoCorsi="";
		for(Corso c : corsi) {
			elencoCorsi+=c.toString()+"\n";
		}
		return elencoCorsi;
	}

	public String getElencoStudenti(Corso c) {
		CorsoDAO dao = new CorsoDAO();
		List<Studente> studenti = new LinkedList<Studente>(dao.getStudentiIscrittiAlCorso(c));
		String elencoStudenti="";
		for(Studente s: studenti) {
			elencoStudenti+=s.toString()+"\n";
		}
		return elencoStudenti;
	}

	public static List<Corso> getAllCorsi() {
		CorsoDAO dao = new CorsoDAO(); //Metodo statico quindi inizializzo la variabile dao dentro il metodo
		List<Corso> corsi = new LinkedList<Corso>(dao.getTuttiICorsi());
		return corsi;
	}

	public boolean isIscritto(Corso c, Studente s) {
		CorsoDAO dao = new CorsoDAO();
		boolean iscritto=false;
		List<Studente> studenti = new LinkedList<Studente>(dao.getStudentiIscrittiAlCorso(c));
		if(studenti.contains(s)) {
			iscritto=true;
		}
		return iscritto;
	}

	public Studente getStudentByInput(String matricola) {
		StudenteDAO dao = new StudenteDAO();
		return dao.getStudentInfo(Integer.parseInt(matricola));
	}

}
