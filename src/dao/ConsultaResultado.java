package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Escola;

public class ConsultaResultado {

	private Connection c;

	public ConsultaResultado() {
		GenericDao gDao = new GenericDao();
		c = gDao.getConnection();
	}

	public List<Escola> read() {
		List<Escola> listEscola = new ArrayList<>();
		try {
			String sql = "select nome,dbo.fn_total(id) as total_pontos from escola order by total_pontos desc";
			PreparedStatement ps = c.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Escola el = new Escola();
				el.setEscola(rs.getString("nome"));
				el.setTotal(rs.getInt("total_pontos"));
				listEscola.add(el);
			}
			ps.execute();
			ps.close();
		} catch (SQLException ex) {
		}
		return listEscola;
	}
}
