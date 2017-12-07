package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Quesito;

public class cmbQuesitoDao {
	
	private Connection c;
	
	public cmbQuesitoDao() {
		GenericDao gDao = new GenericDao();
		c = gDao.getConnection();
	}
	
	public List<Quesito> read() {
		List<Quesito> listQuesito = new ArrayList<>();
		try {
			String sql = "select * from quesito";
			PreparedStatement ps = c.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Quesito qs = new Quesito();
				qs.setQuesito(rs.getString("nome"));
				listQuesito.add(qs);
			}
			ps.execute();
			ps.close();
		} catch (SQLException ex) {
		}
		return listQuesito;
	}
}
