package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.GridLayout;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import controller.InserirNotaPrincipal;
import controller.VerQuesitoPrincipal;
import controller.VerTotalPrincipal;
import dao.cmbEscolaDao;
import dao.cmbJuradoDao;
import dao.cmbQuesitoDao;
import model.Escola;
import model.Jurado;
import model.Quesito;
import javax.swing.JButton;

public class TelaPrincipal {

	private JFrame priframe;
	private JTextField txtNota;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaPrincipal window = new TelaPrincipal();
					window.priframe.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public TelaPrincipal() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		priframe = new JFrame();
		priframe.setBounds(100, 100, 340, 300);
		priframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		priframe.getContentPane().setLayout(new GridLayout(1, 0, 0, 0));

		JPanel panel = new JPanel();
		priframe.getContentPane().add(panel);
		panel.setLayout(null);

		JLabel lblEscola = new JLabel("Escola");
		lblEscola.setBounds(10, 42, 46, 14);
		panel.add(lblEscola);

		JLabel lblJurado = new JLabel("Jurado");
		lblJurado.setBounds(10, 82, 46, 14);
		panel.add(lblJurado);

		JLabel lblQuesito = new JLabel("Quesito");
		lblQuesito.setBounds(10, 123, 46, 14);
		panel.add(lblQuesito);

		JComboBox<Escola> cmbEscola = new JComboBox<Escola>();
		cmbEscola.setBounds(66, 39, 226, 20);
		panel.add(cmbEscola);

		JComboBox<Jurado> cmbJurado = new JComboBox<Jurado>();
		cmbJurado.setBounds(66, 79, 226, 20);
		panel.add(cmbJurado);

		JComboBox<Quesito> cmbQuesito = new JComboBox<Quesito>();
		cmbQuesito.setBounds(66, 120, 226, 20);
		panel.add(cmbQuesito);

		JLabel lblNota = new JLabel("Nota");
		lblNota.setBounds(19, 197, 37, 14);
		panel.add(lblNota);

		txtNota = new JTextField();
		txtNota.setBounds(66, 194, 111, 20);
		panel.add(txtNota);
		txtNota.setColumns(10);

		JButton btnInserir = new JButton("Inserir");
		btnInserir.setBounds(187, 193, 105, 23);
		panel.add(btnInserir);

		JButton btnVerQuesito = new JButton("Ver Quesito");
		btnVerQuesito.setBounds(66, 227, 111, 23);
		panel.add(btnVerQuesito);

		JButton btnVerTotal = new JButton("Ver Total");
		btnVerTotal.setBounds(184, 227, 108, 23);
		panel.add(btnVerTotal);

		VerTotalPrincipal vtp = new VerTotalPrincipal(priframe, btnVerTotal);
		btnVerTotal.addActionListener(vtp);

		VerQuesitoPrincipal vqp = new VerQuesitoPrincipal(priframe, btnVerQuesito, cmbQuesito);
		btnVerQuesito.addActionListener(vqp);

		InserirNotaPrincipal inp = new InserirNotaPrincipal(cmbEscola, cmbQuesito, cmbJurado, btnInserir, txtNota);
		btnInserir.addActionListener(inp);
		cmbEscola.addActionListener(inp);
		cmbJurado.addActionListener(inp);
		cmbQuesito.addActionListener(inp);

		readCmbEscola(cmbEscola);
		readCmbJurado(cmbJurado);
		readCmbQuesito(cmbQuesito);
	}

	public static void readCmbEscola(JComboBox cmbEscola) {
		cmbEscolaDao ced = new cmbEscolaDao();
		for (Escola e : ced.read()) {
			cmbEscola.addItem(e.getEscola());
		}
	}

	public static void readCmbJurado(JComboBox cmbJurado) {
		cmbJuradoDao cjd = new cmbJuradoDao();
		for (Jurado j : cjd.read()) {
			cmbJurado.addItem(j.getJurado());
		}
	}

	public static void readCmbQuesito(JComboBox cmbQuesito) {
		cmbQuesitoDao cqd = new cmbQuesitoDao();
		for (Quesito q : cqd.read()) {
			cmbQuesito.addItem(q.getQuesito());
		}
	}

}
