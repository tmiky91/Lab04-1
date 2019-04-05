package it.polito.tdp.lab04.controller;

import java.net.URL;
import java.util.ResourceBundle;

import it.polito.tdp.lab04.model.Corso;
import it.polito.tdp.lab04.model.Model;
import it.polito.tdp.lab04.model.Studente;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class SegreteriaStudentiController {

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
    private CheckBox btnCheck;

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

	private Model model;

    @FXML
    void doCompila(ActionEvent event) {
    	String matricola = txtMatricola.getText();
    	
    	// 1)Verifico se c'è scritto qualcosa
    	// 2)Verifico se l'input è del tipo voluto
    	// 3)Verifico che lo studente esista
    	
    	if(matricola!=null && !matricola.isEmpty()) {
    		if(model.isDigit(matricola)) {
    			Studente studente = model.getStudentInfoByInput(matricola);
    			if(studente != null) {
    				String nome = studente.getNome();
    				String cognome = studente.getCognome();
    				txtnome.setText(nome);
    				txtCognome.setText(cognome);
    			}
    			else {
    				showMessage("Lo studente non esiste");
    			}
    		}
    		else {
    			showMessage("Inserire una matricola valida di 6 cifre");
    		}
    	}
    	else {
    		showMessage("Inserire un numero");
    	}

    }

    @FXML
    void doIscrivi(ActionEvent event) {
    	String matricola = txtMatricola.getText();
    	Corso c = btnComboBox.getValue();
    	if(matricola!=null && !matricola.isEmpty()) {
    		if(model.isDigit(matricola)) {
    			Studente studente = model.getStudentByInput(matricola);
    			if(studente != null) {
    		    	if(c!=null) {
    		    		if(model.isIscritto(c, studente)) {
    		    			txtResult.setText("Studente iscritto al corso \n");
    		    		}
    		    		else {
    		    			txtResult.setText("Studente non iscritto al corso \n");
    		    		}
    		    	}
    		    	else{
    		    		showMessage("Seleziona un corso");
    		    	}
    			}
    			else {
    				showMessage("Lo studente non esiste");
    			}
    		}
    		else {
    			showMessage("Inserire una matricola valida di 6 cifre");
    		}
    	}
    	else {
    		showMessage("Inserire un numero");
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
    	String matricola = txtMatricola.getText();
    	
    	if(matricola!=null && !matricola.isEmpty()) {
    		if(model.isDigit(matricola)) {
    			Studente studente = model.getStudentInfoByInput(matricola);
    			if(studente != null) {
    				String elencoCorsi = model.getElencoCorsi(matricola);
    				txtResult.setText(elencoCorsi);
    			}
    			else {
    				showMessage("Lo studente non esiste");
    			}
    		}
    		else {
    			showMessage("Inserire una matricola valida di 6 cifre");
    		}
    	}
    	else {
    		showMessage("Inserire un numero");
    	}

    }

    @FXML
    void doSearchStudent(ActionEvent event) {
    	Corso c = btnComboBox.getValue();
    	// 1)Controllo se null
    	if(c!=null) {
    		String elencoStudenti = model.getElencoStudenti(c);
    		txtResult.setText(elencoStudenti);
    	}
    	else{
    		showMessage("Seleziona un corso");
    	}

    }

    @FXML
    void initialize() {
        assert btnComboBox != null : "fx:id=\"btnComboBox\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
        assert btnCercaIscritti != null : "fx:id=\"btnCercaIscritti\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
        assert txtMatricola != null : "fx:id=\"txtMatricola\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
        assert btnCheck != null : "fx:id=\"btnCheck\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
        assert txtnome != null : "fx:id=\"txtnome\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
        assert txtCognome != null : "fx:id=\"txtCognome\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
        assert btnCercaCorsi != null : "fx:id=\"btnCercaCorsi\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
        assert btnIscrivi != null : "fx:id=\"btnIscrivi\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
        assert btnReset != null : "fx:id=\"btnReset\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
        
        //Inizializzo la comboBox qui
        btnComboBox.getItems().addAll(Model.getAllCorsi()); //Model come classe perchè faccio una chiamata statica

    }

	public void setModel(Model model) {
		this.model = model;	
	}
	
	/*
	 * Metodo da copiare sempre per mostrare errori
	 */
	private void showMessage(String message) {
		Alert alert = new Alert(Alert.AlertType.ERROR);
		alert.setContentText(message);
		alert.show();
	}
}
