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
	
	private Model model;

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
    private ImageView imgCompila;

    @FXML
    private TextField txtnome;

    @FXML
    private TextField txtCognome;

    @FXML
    private Button btnCercaCorsi;

    @FXML
    private Button btnIscrivi;

    @FXML
    private TextArea txtResult;

    @FXML
    private Button btnReset;

    @FXML
    void doCompila(MouseEvent event) {
    	List<Studente> studenti = new LinkedList<Studente>(model.getAllStudents());
    	int matricola;
    	try {
    		matricola=Integer.parseInt(txtMatricola.getText());
    	}
    	catch(NumberFormatException e) {
    		txtResult.appendText("Devi inserire una matricola valida");
    		return;
    	}
    	Studente s = new Studente(matricola);
    	if(!studenti.contains(s)) {
   		txtResult.setText("Errore: Studente non presente nel DataBase");
  		return;
    	}
    	for(Studente s1: studenti) {
    		if(s1.getMatricola()==matricola) {
    			txtnome.setText(s1.getNome());
    			txtCognome.setText(s1.getCognome());
    		}
    	}
    }

    @FXML
    void doIscrivi(ActionEvent event) {
    	Corso c = btnComboBox.getValue();
    	if(c==null) {
    		txtResult.setText("Errore: Seleziona un corso");
    	}
    	int matricola;
    	try {
    		matricola=Integer.parseInt(txtMatricola.getText());
    	}
    	catch(NumberFormatException e) {
    		txtResult.appendText("Devi inserire una matricola valida");
    		return;
    	}
    	Studente s = new Studente(matricola);
    	if(model.isIscritto(c,s)) {
    		txtResult.setText("Lo studente è iscritto al corso selezionato");
    	}
    	else {
    		txtResult.setText("Lo studente non è iscritto al corso selezionato");
    		model.iscriviStudente(c,s);
    	}
    }

    @FXML
    void doReset(ActionEvent event) {
    	txtMatricola.clear();
    	txtnome.clear();
    	txtCognome.clear();
    	txtResult.clear();
    }

    @FXML
    void doSearchCourse(ActionEvent event) {
    	List<Corso> corsi = new LinkedList<Corso>(model.getAllCourses());
    	List<Studente> studenti = new LinkedList<Studente>(model.getAllStudents());
    	int matricola;
    	try {
    		matricola=Integer.parseInt(txtMatricola.getText());
    	}
    	catch(NumberFormatException e) {
    		txtResult.appendText("Devi inserire una matricola valida");
    		return;
    	}
    	Studente s = new Studente(matricola);
    	if(!studenti.contains(s)) {
    		txtResult.setText("Errore: Studente non presente nel DataBase");
    		return;
    	}
    }

    @FXML
    void doSearchStudent(ActionEvent event) {
    	txtResult.clear();
    	String risultato="";
    	Corso c = btnComboBox.getValue();
    	
    	if(c==null) {
    		txtResult.setText("Errore: Seleziona un corso dal menù a tendina");
    		return;
    	}
    	risultato+=model.cercaIscritti(c);
    	txtResult.setText(risultato);
    }

    @FXML
    void initialize() {
        assert btnComboBox != null : "fx:id=\"btnComboBox\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
        assert btnCercaIscritti != null : "fx:id=\"btnCercaIscritti\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
        assert txtMatricola != null : "fx:id=\"txtMatricola\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
        assert imgCompila != null : "fx:id=\"imgCompila\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
        assert txtnome != null : "fx:id=\"txtnome\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
        assert txtCognome != null : "fx:id=\"txtCognome\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
        assert btnCercaCorsi != null : "fx:id=\"btnCercaCorsi\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
        assert btnIscrivi != null : "fx:id=\"btnIscrivi\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
        assert btnReset != null : "fx:id=\"btnReset\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";

    }
    
    public void setModel(Model model) {
    	this.model=model;
    	btnComboBox.getItems().addAll(model.getAllCourses());
    }
}
