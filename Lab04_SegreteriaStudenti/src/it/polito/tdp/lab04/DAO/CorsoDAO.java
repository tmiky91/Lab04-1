package it.polito.tdp.lab04.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import it.polito.tdp.lab04.model.Corso;
import it.polito.tdp.lab04.model.Studente;

public class CorsoDAO {

	public List<Corso> getTuttiICorsi() {

		final String sql = "SELECT * FROM corso";

		List<Corso> corsi = new LinkedList<Corso>();

		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);

			ResultSet rs = st.executeQuery();

			while (rs.next()) {

				String codins = rs.getString("codins");
				int numeroCrediti = rs.getInt("crediti");
				String nome = rs.getString("nome");
				int periodoDidattico = rs.getInt("pd");

				Corso c = new Corso(codins, numeroCrediti, nome, periodoDidattico);
				corsi.add(c);
			}

			return corsi;

		} catch (SQLException e) {
			throw new RuntimeException("Errore Db");
		}
	}

	public List<Studente> getStudentiIscrittiAlCorso(Corso corso) {
		final String sql = "select s.matricola, s.nome, s.cognome, s.CDS " + 
							"from studente as s, iscrizione as i " + 
							"where s.matricola=i.matricola and i.codins=?"; //funziona anche solo con codins=?(senza i)
		List<Studente> studentiIscritti = new LinkedList<Studente>();
		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setString(1, corso.getCodins());

			ResultSet rs = st.executeQuery();

			while (rs.next()) {

				int matricola = rs.getInt("matricola");
				String nome = rs.getString("nome");
				String cognome = rs.getString("cognome");
				String cds = rs.getString("CDS");

				Studente s = new Studente(matricola, nome, cognome, cds);
				studentiIscritti.add(s);
			}

			return studentiIscritti;

		} catch (SQLException e) {
			throw new RuntimeException("Errore Db");
		}
	}

	public boolean inscriviStudenteACorso(Studente studente, Corso corso) {
		return false;
	}

	public List<Corso> getElencoCorsi(int matricola) {
		final String sql = "select c.codins, c.crediti, c.nome, c.pd " + 
							"from corso as c, iscrizione as i " + 
							"where c.codins=i.codins and matricola =?";
		List<Corso> corsiSeguiti = new LinkedList<Corso>();
		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, matricola);

			ResultSet rs = st.executeQuery();

			while (rs.next()) {

				String codins = rs.getString("codins");
				int numeroCrediti = rs.getInt("crediti");
				String nome = rs.getString("nome");
				int periodoDidattico = rs.getInt("pd");

				Corso c = new Corso(codins, numeroCrediti, nome, periodoDidattico);
				corsiSeguiti.add(c);
			}

			return corsiSeguiti;

		} catch (SQLException e) {
			throw new RuntimeException("Errore Db");
		}
	}
}
