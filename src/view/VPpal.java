package view;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.JScrollPane;

import control.PFListener;
import javax.swing.ScrollPaneConstants;

public class VPpal extends JFrame {

	private static final long serialVersionUID = 1L;
	static int ALTO = 500;
	static int ANCHO = 545;
	public static String BTN_LANZAR = "LANZAR";
	public static String OPCION_NAVEGAR = "NAVEGAR WEBS";
	public static String OPCION_WORD = "WORD";
	public static String OPCION_EXCEL = "EXCEL";
	public static String OPCION_POWERPOINT = "POWERPOINT";
	public static String OPCION_ARCHIVO = "ABRIR ARCHIVO"; 
	public static String OPCION_CREAR = "CREAR ARCHIVO";
	
	private JButton btnLanzar;
	@SuppressWarnings("rawtypes")
	private JComboBox cbxSelectLanzar;
	private JScrollPane scrp;
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public VPpal() {		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(ANCHO, ALTO);

		Dimension pantalla = Toolkit.getDefaultToolkit().getScreenSize();
		Dimension ventana = new Dimension(ANCHO, ALTO);
		setLocation((pantalla.width - ventana.width) / 2, (pantalla.height - ventana.height) / 2);
		getContentPane().setLayout(null);
		
		
		btnLanzar = new JButton(BTN_LANZAR);
		btnLanzar.setBounds(418, 11, 87, 32);
		getContentPane().add(btnLanzar);
		
		cbxSelectLanzar = new JComboBox();
		cbxSelectLanzar.setFont(new Font("Tahoma", Font.BOLD, 12));
		cbxSelectLanzar.setModel(new DefaultComboBoxModel(new String[] {OPCION_NAVEGAR, OPCION_WORD, OPCION_EXCEL, OPCION_POWERPOINT, OPCION_ARCHIVO, OPCION_CREAR}));
		cbxSelectLanzar.setBounds(246, 11, 153, 30);
		getContentPane().add(cbxSelectLanzar);
		
		scrp = new JScrollPane();
		scrp.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
		scrp.setBounds(10, 54, 509, 396);
		getContentPane().add(scrp);
		
		
	}

	public void hacerVisible() {
		setVisible(true);
	}

	public void setListener(PFListener l) {
		btnLanzar.addActionListener(l);
		
	}
	
	public void cargarPanel(JPanel panel) { 
		scrp.setViewportView(panel);
	}
	
	public String txtCbxSelect() {
		String txt = cbxSelectLanzar.getSelectedItem().toString();
		
		return txt; 
	}
	
	public void mostrarMsjError(String msj) {
		JOptionPane.showMessageDialog(this, msj, "Error", JOptionPane.ERROR_MESSAGE);
		
	}
	
	public void mostrarMsgInfo(String msg) {
		JOptionPane.showMessageDialog(this, msg, "Información de operación", JOptionPane.INFORMATION_MESSAGE);
		
	}
}
