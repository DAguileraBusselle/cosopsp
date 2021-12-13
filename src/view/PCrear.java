package view;

import javax.swing.JPanel;
import javax.swing.JTextField;

import control.PFListener;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.DefaultComboBoxModel;

public class PCrear extends JPanel {
	
	private static final long serialVersionUID = 1L;
	static int ALTO = 396;
	static int ANCHO = 509;
	
	public static String BTN_CREAR = "CREAR";
	public static String OPCION_CREAR_PW = "Powerpoint";
	public static String OPCION_CREAR_WO = "Word";
	public static String OPCION_CREAR_EX = "Excel";
 	private JTextField txtNombreArchivo;
	@SuppressWarnings("rawtypes")
	private JComboBox cbxOpcionArchivo;
	private JButton btnCrear;
	
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public PCrear() {
		setLayout(null);
		setSize(ANCHO, ALTO);
		
		txtNombreArchivo = new JTextField();
		txtNombreArchivo.setBounds(26, 185, 217, 20);
		add(txtNombreArchivo);
		txtNombreArchivo.setColumns(10);
		
		cbxOpcionArchivo = new JComboBox();
		cbxOpcionArchivo.setModel(new DefaultComboBoxModel(new String[] {OPCION_CREAR_PW, OPCION_CREAR_WO, OPCION_CREAR_EX}));
		cbxOpcionArchivo.setBounds(253, 184, 123, 22);
		add(cbxOpcionArchivo);
		
		btnCrear = new JButton(BTN_CREAR);
		btnCrear.setBounds(390, 184, 89, 23);
		add(btnCrear);
		
	}
	
	public String getOpcionArchivo() {
		return cbxOpcionArchivo.getSelectedItem().toString();
		
	}
	
	public String getNombreArchivo() {
		return txtNombreArchivo.getText();
	}
	
	public void mostrarMsjError(String msj) {
		JOptionPane.showMessageDialog(this, msj, "Error", JOptionPane.ERROR_MESSAGE);
		
	}
	
	public void mostrarMsgInfo(String msg) {
		JOptionPane.showMessageDialog(this, msg, "Información de operación", JOptionPane.INFORMATION_MESSAGE);
		
	}
	
	public void setListener(PFListener l) {
		btnCrear.addActionListener(l);
	}

}
