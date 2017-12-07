package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;

import model.Quesito;
import view.TelaQuesito;

public class VerQuesitoPrincipal implements ActionListener{
	JFrame priframe;
	JButton btnVerQuesito;
	JComboBox cmbQuesito;
	
	public VerQuesitoPrincipal(JFrame priframe, JButton btnVerQuesito,JComboBox cmbQuesito) {
		this.priframe = priframe;
		this.btnVerQuesito = btnVerQuesito;
		this.cmbQuesito = cmbQuesito;
	}

	private void montaTelaQuesito() {
		TelaQuesito telaQ = new TelaQuesito(cmbQuesito);
		telaQ.queframe.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btnVerQuesito){
			montaTelaQuesito();
		}
		
	}
}
