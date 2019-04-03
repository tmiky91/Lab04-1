package it.polito.tdp.lab04.controller;

import java.net.URL;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;

import it.polito.tdp.lab04.model.Corso;
import it.polito.tdp.lab04.model.Model;
import it.polito.tdp.lab04.model.Studente;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class SegreteriaStudentiController {
	
	Model model;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<Corso> btnComboBox;

    @FXML
    private Button btnCercaIscritti;

    @FXML
    private TextField txtMatricola;

    @FXML
    private ImageView imgPlay;

    @FXML
    private TextField txtNome;

    @FXML
    private TextField txtCognome;

    @FXML
    private Button btnIscrivi;

    @FXML
    private Button btnCercaCorsi;

    @FXML
    private Button btnCerca;

    @FXML
    private TextArea txtResult;

    @FXML
    private Button btnReset;

    @FXML
    void doCercaCorsi(ActionEvent event) {
    	int matricola;
    	String risultato="";
    	List<Studente> studenti = new LinkedList<Studente>(model.getAllStudents());
    	try {
    		matricola=Integer.parseInt(txtMatricola.getText());
    	}
    	catch(NumberFormatException e) {
    		txtResult.setText("Errore: Inserire una matricola valida");
    		return;
    	}
    	Studente s = new Studente(matricola);
    	if(!studenti.contains(s)) {
    		txtResult.setText("Errore: Studente non presente nel DataBase");
    		return;
    	}
    	risultato+=model.getCorsiSeguiti(s);
    	txtResult.setText(risultato);
    }

    @FXML
    void doCercaIscritti(ActionEvent event) {
    	Corso c = btnComboBox.getValue();
    	String risultato="";
    	if(c==null) {
    		txtResult.setText("Errore: Seleziona un corso dal menù a tendina");
    		return;
    	}
    	risultato+=model.getStudentiIscritti(c);
    	txtResult.setText(risultato);
    }

    @FXML
    void doIscrivi(ActionEvent event) {
    	int matricola;
    	Corso c = btnComboBox.getValue();
    	List<Studente> studenti = new LinkedList<Studente>(model.getAllStudents());
    	try {
    		matricola=Integer.parseInt(txtMatricola.getText());
    	}
    	catch(NumberFormatException e) {
    		txtResult.setText("Errore: Inserire una matricola valida");
    		return;
    	}
    	Studente s = new Studente(matricola);
    	if(!studenti.contains(s)) {
    		txtResult.setText("Errore: Studente non presente nel DataBase");
    		return;
    	}
    	if(c==null) {
    		txtResult.setText("Errore: Seleziona un corso dal menù a tendina");
    		return;
    	}
//    	if(model.isIscritto(c,s)) {
//    		txtResult.setText("Studente già iscritto al corso selezionato");
//    		return;
//    	}
//    	else {
//    		model.iscriviStudente(s,c);
//    		txtResult.setText("Iscrizione avvenuta");
//    		return;
//    	}
    }

    @FXML
    void doPlay(MouseEvent event) {
    	int matricola;
    	List<Studente> studenti = new LinkedList<Studente>(model.getAllStudents());
    	try {
    		matricola=Integer.parseInt(txtMatricola.getText());
    	}
    	catch(NumberFormatException e) {
    		txtResult.setText("Errore: Inserire una matricola valida");
    		return;
    	}
    	Studente s = new Studente(matricola);
    	if(!studenti.contains(s)) {
    		txtResult.setText("Errore: Studente non presente nel DataBase");
    		return;
    	}
    	for(Studente s1: studenti) {
    		if(s1.getMatricola()==matricola) {
    			txtNome.setText(s1.getNome());
    			txtCognome.setText(s1.getCognome());
    		}
    	}
    }

    @FXML
    void doReset(ActionEvent event) {
    	txtMatricola.clear();
    	txtResult.clear();
    	txtNome.clear();
    	txtCognome.clear();
    }

    @FXML
    void doSearch(ActionEvent event) {
    	int matricola;
    	List<Studente> studenti = new LinkedList<Studente>(model.getAllStudents());
    	try {
    		matricola=Integer.parseInt(txtMatricola.getText());
    	}
    	catch(NumberFormatException e) {
    		txtResult.setText("Errore: Inserire una matricola valida");
    		return;
    	}
    	Studente s = new Studente(matricola);
    	if(!studenti.contains(s)) {
    		txtResult.setText("Errore: Studente non presente nel DataBase");
    		return;
    	}
    	Corso c = btnComboBox.getValue();
    	if(c==null) {
    		txtResult.setText("Errore: Seleziona un corso dal menù a tendina");
    		return;
    	}
    	if(model.isIscritto(c,s)) {
    		txtResult.setText("Studente iscritto al corso selezionato");
    		return;
    	}
    	else {
    		txtResult.setText("Studente non iscritto al corso selezionato");
    		return;
    	}
    }

    @FXML
    void initialize() {
        assert btnComboBox != null : "fx:id=\"btnComboBox\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
        assert btnCercaIscritti != null : "fx:id=\"btnCercaIscritti\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
        assert txtMatricola != null : "fx:id=\"txtMatricola\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
        assert imgPlay != null : "fx:id=\"imgPlay\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
        assert txtNome != null : "fx:id=\"txtNome\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
        assert txtCognome != null : "fx:id=\"txtCognome\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
        assert btnIscrivi != null : "fx:id=\"btnIscrivi\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
        assert btnCercaCorsi != null : "fx:id=\"btnCercaCorsi\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
        assert btnCerca != null : "fx:id=\"btnCerca\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
        assert btnReset != null : "fx:id=\"btnReset\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";

    }

	public void setModel(Model model) {
		this.model = model;
		btnComboBox.getItems().addAll(model.getAllCourses());	
	}
}
