package it.polito.tdp.lab04.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import it.polito.tdp.lab04.model.Corso;
import it.polito.tdp.lab04.model.Studente;

public class StudenteDAO {

	public Studente getStudentById(int matricola) {
		final String sql = "select s.nome, s.cognome "+
							"from studente as s "+
							"where matricola=?";
		Studente s=null;
		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, matricola);

			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				String nome = rs.getString("nome");
				String cognome = rs.getString("cognome");
				s= new Studente(nome, cognome);

			}
			return s;

		} catch (SQLException e) {
			throw new RuntimeException("Errore Db");
		}
	}

	public Studente getStudentInfo(int matricola) {
		final String sql = "select s.matricola, s.nome, s.cognome, s.CDS "+
				"from studente as s "+
				"where matricola=?";
		Studente s=null;
		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, matricola);

			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				int matricola1 = rs.getInt("matricola");
				String nome = rs.getString("nome");
				String cognome = rs.getString("cognome");
				String cds = rs.getString("CDS");
				s= new Studente(matricola1, nome, cognome, cds);

			}
			return s;

		} catch (SQLException e) {
			throw new RuntimeException("Errore Db");
		}
	}

}
