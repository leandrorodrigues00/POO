package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JComboBox;

import model.Apoio;


public class QuesitoDao {

	private Connection c;
	JComboBox cmbQuesito;
	
	public QuesitoDao(JComboBox cmbQuesito) {
		GenericDao gDao = new GenericDao();
		c = gDao.getConnection();
		this.cmbQuesito = cmbQuesito;
	}

	public List<Apoio> read(){
		List<Apoio> listApoio = new ArrayList<>();
		try {
			String sql = "exec pc_verificaQ ?";
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setString(1, ((String) cmbQuesito.getSelectedItem()));
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Apoio ap = new Apoio();
				ap.setNome_escola(rs.getString("nome"));
				ap.setNota1(rs.getInt("nota1"));
				ap.setNota2(rs.getInt("nota2"));
				ap.setNota3(rs.getInt("nota3"));
				ap.setNota4(rs.getInt("nota4"));
				ap.setNota5(rs.getInt("nota5"));
				ap.setMaior(rs.getInt("maior_descartada"));
				ap.setMenor(rs.getInt("menor_descarta"));
				ap.setTotal(rs.getInt("total"));
				listApoio.add(ap);
			}
			ps.execute();
			ps.close();
		} catch (SQLException ex) {
			
		}
		return listApoio;
	}
}
