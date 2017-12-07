package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Escola;

public class cmbEscolaDao {
	
	private Connection c;

	public cmbEscolaDao() {
		GenericDao gDao = new GenericDao();
		c = gDao.getConnection();
	}
	
	public List<Escola> read() {
		List<Escola> listEscolas = new ArrayList<>();
		try {
			String sql = "select * from escola";
			PreparedStatement ps = c.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Escola es = new Escola();
				es.setEscola(rs.getString("nome"));
				listEscolas.add(es);
			}
			ps.execute();
			ps.close();
		} catch (SQLException ex) {
		}
		return listEscolas;
	}
}
