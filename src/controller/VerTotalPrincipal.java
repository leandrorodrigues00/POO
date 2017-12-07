package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

import view.TelaTotal;

public class VerTotalPrincipal implements ActionListener{
	
	JFrame priframe;
	JButton btnVerTotal;
	
	public VerTotalPrincipal(JFrame priframe, JButton btnVerTotal) {
		this.priframe = priframe;
		this.btnVerTotal = btnVerTotal;
	}

	private void montaTelaTotal() {
		TelaTotal telaT = new TelaTotal();
		telaT.totframe.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btnVerTotal){
			montaTelaTotal();
		}
		
	}
}
