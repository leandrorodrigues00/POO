package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import model.Escola;
import model.Jurado;
import model.Quesito;
import model.Notas;

public class InsertDao {
	private Connection c;

	public InsertDao() {
		GenericDao gDao = new GenericDao();
		c = gDao.getConnection();
	}
	
	public void inserir(Escola el, Jurado jr, Quesito qs, Notas sb) throws SQLException {
		String sql = "exec pc_sambao ?,?,?,?";
		PreparedStatement ps = c.prepareStatement(sql);
		ps.setString(1, el.getEscola());
		ps.setString(2, jr.getJurado());
		ps.setString(3, qs.getQuesito());
		ps.setDouble(4, sb.getNota());
		ps.execute();
		ps.close();

		
	}
}
