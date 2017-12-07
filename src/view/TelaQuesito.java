package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;

import dao.QuesitoDao;
import model.Apoio;

import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextField;

public class TelaQuesito {

	public JFrame queframe;
	private static JTable table;
	private JLabel lblQuesito;
	private JTextField textField;
	private static JComboBox cmbQuesito;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaQuesito window = new TelaQuesito(cmbQuesito);
					window.queframe.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public TelaQuesito(JComboBox cmbQuesito) {
		this.cmbQuesito = cmbQuesito;
		initialize();
	}

	/**
	 * Initialize the contents of the queframe.
	 */
	private void initialize() {
		queframe = new JFrame();
		queframe.setBounds(100, 100, 775, 386);

		JScrollPane scrollPane = new JScrollPane();

		lblQuesito = new JLabel("Quesito");

		textField = new JTextField();
		textField.setEditable(false);
		textField.setColumns(10);
		GroupLayout groupLayout = new GroupLayout(queframe.getContentPane());
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup().addContainerGap().addComponent(scrollPane,
										GroupLayout.DEFAULT_SIZE, 601, Short.MAX_VALUE))
								.addGroup(groupLayout.createSequentialGroup().addGap(30)
										.addComponent(lblQuesito, GroupLayout.PREFERRED_SIZE, 50,
												GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(ComponentPlacement.RELATED).addComponent(textField,
												GroupLayout.PREFERRED_SIZE, 212, GroupLayout.PREFERRED_SIZE)))
						.addContainerGap()));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.LEADING).addGroup(Alignment.TRAILING,
				groupLayout.createSequentialGroup().addContainerGap()
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(lblQuesito))
						.addGap(18)
						.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 252, GroupLayout.PREFERRED_SIZE)
						.addGap(36)));

		table = new JTable();
		table.setModel(new DefaultTableModel(new Object[][] {  
			
		},
				new String[] { "Escola", "1\u00BA Jurado", "2\u00BA Jurado", "3\u00BA Jurado", "4\u00BA Jurado",
						"5\u00BA Jurado", "Menor Descartada", "Maior Descartada" ,"Total"}));
		scrollPane.setViewportView(table);
		queframe.getContentPane().setLayout(groupLayout);
		
		Apoio ap = new Apoio();
		ap.setQuesito_escola((String) cmbQuesito.getSelectedItem());
		readJatble();
		textField.setText(ap.getQuesito_escola());
	}
	
	public static void readJatble(){
		QuesitoDao qs = new QuesitoDao(cmbQuesito);
		DefaultTableModel modelo = (DefaultTableModel) table.getModel();
			for(Apoio a: qs.read()){
				modelo.addRow(new Object[]{
						a.getNome_escola(),
						a.getNota1(),
						a.getNota2(),
						a.getNota3(),
						a.getNota4(),
						a.getNota5(),
						a.getMenor(),
						a.getMaior(),
						a.getTotal()
				});
			}
	}
}
