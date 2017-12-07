package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import dao.InsertDao;
import model.Escola;
import model.Jurado;
import model.Quesito;
import model.Notas;

public class InserirNotaPrincipal implements ActionListener {

	private JComboBox cmbEscola;
	private JComboBox cmbQuesito;
	private JComboBox cmbJurado;
	private JButton btnInserir;
	private JTextField txtNota;
	private int c;
	private int q;

	public InserirNotaPrincipal(JComboBox cmbEscola, JComboBox cmbQuesito, JComboBox cmbJurado, JButton btnInserir,
			JTextField txtNota) {
		this.cmbEscola = cmbEscola;
		this.cmbQuesito = cmbQuesito;
		this.cmbJurado = cmbJurado;
		this.btnInserir = btnInserir;
		this.txtNota = txtNota;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Notas nt = new Notas();
		Escola el = new Escola();
		Jurado jr = new Jurado();
		Quesito qs = new Quesito();
		if (e.getSource() == btnInserir) {
			el.setEscola((String) cmbEscola.getSelectedItem());
			jr.setJurado((String) cmbJurado.getSelectedItem());
			qs.setQuesito((String) cmbQuesito.getSelectedItem());
			nt.setNota(Double.parseDouble(txtNota.getText()));
			if(Double.parseDouble(txtNota.getText()) < 0 || Double.parseDouble(txtNota.getText()) > 10){
				JOptionPane.showMessageDialog(null,"Insira uma nota entre 0 e 10","ERRO", JOptionPane.ERROR_MESSAGE);
			}else{
				inserir(nt, el, jr, qs);
				trocarJ();
				trocarQ();
				trocarE();
			}
			limpacampos();
		}
	}

	private void limpacampos() {
		txtNota.setText("");
	}

	private void trocarE() {

		if (q % 9 == 0 && q != 0) {
			int n = cmbEscola.getSelectedIndex() + 1;

			Object obj = cmbEscola.getItemAt(n);
			cmbEscola.setSelectedItem(obj);
			q++;
		}

	}

	private void trocarQ() {
		if (c <= 45) {
			if (c % 5 == 0) {
				int n = cmbQuesito.getSelectedIndex() + 1;

				Object obj = cmbQuesito.getItemAt(n);
				cmbQuesito.setSelectedItem(obj);
				q++;
			}
		} else {
			Object obj = cmbQuesito.getItemAt(0);// c
			cmbJurado.setSelectedItem(obj);
			q = 0;
		}
	}

	private void trocarJ() {

		if (cmbJurado.getSelectedIndex() < cmbJurado.getItemCount() - 1) {
			int n = cmbJurado.getSelectedIndex() + 1;

			Object obj = cmbJurado.getItemAt(n);
			cmbJurado.setSelectedItem(obj);

			++c;
		} else {
			// para trocar antes dele zerar o c
			trocarQ();
			Object obj = cmbJurado.getItemAt(0);// c
			cmbJurado.setSelectedItem(obj);
			c++;
		}
	}

	private void inserir(Notas nt, Escola el, Jurado jr, Quesito qs) {
		InsertDao id = new InsertDao();
		try {
			id.inserir(el, jr, qs, nt);
			JOptionPane.showMessageDialog(null, "INSERIDO COM SUCESSO", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "ERRO", JOptionPane.ERROR_MESSAGE);
		}
	}

}
