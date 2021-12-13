package view;

import javax.swing.JPanel;
import javax.swing.JOptionPane;

public class PEnBlanco extends JPanel{
	
	private static final long serialVersionUID = 1L;
	static int ALTO = 396;
	static int ANCHO = 509;
	public PEnBlanco() {
		setLayout(null);
		setSize(ANCHO, ALTO);
	}

	
	public void hacerVisible() {
		setVisible(true);
	}
	
	public void hacerInvisible() {
		setVisible(false);
	}
	
	public void mostrarMsjError(String msj) {
		JOptionPane.showMessageDialog(this, msj, "Error", JOptionPane.ERROR_MESSAGE);
		
	}
	
	public void mostrarMsgInfo(String msg) {
		JOptionPane.showMessageDialog(this, msg, "Información de operación", JOptionPane.INFORMATION_MESSAGE);
		
	}
}
