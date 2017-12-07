package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Jurado;

public class cmbJuradoDao {
	
	private Connection c;

	public cmbJuradoDao() {
		GenericDao gDao = new GenericDao();
		c = gDao.getConnection();
	}
	
	public List<Jurado> read() {
		List<Jurado> listJurado = new ArrayList<>();
		try {
			String sql = "select * from jurado";
			PreparedStatement ps = c.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Jurado jr = new Jurado();
				jr.setJurado(rs.getString("nome"));
				listJurado.add(jr);
			}
			ps.execute();
			ps.close();
		} catch (SQLException ex) {
		}
		return listJurado;
	}
}
