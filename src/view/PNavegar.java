package view;

import javax.swing.JPanel;
import javax.swing.JTextField;

import control.PFListener;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.JOptionPane;

public class PNavegar extends JPanel{
	
	private static final long serialVersionUID = 1L;
	static int ALTO = 396;
	static int ANCHO = 509;
	
	public static String BTN_NAVEGAR = "NAVEGAR";
	public static String BTN_LIMPIAR = "LIMPIAR REGISTROS";
	public static String BTN_ELIMINAR = "ELIMINAR";
	private JTextField txtNavegar;
	private JButton btnNavegar;
	private JButton btnLimpiar;
	private JButton btnEliminar;
	private JList<String> listaWebs;
	private JScrollPane scrollPane;
	private DefaultListModel<String> ModeloLista;
	public PNavegar() {
		setLayout(null);
		setSize(ANCHO, ALTO);
		
		txtNavegar = new JTextField();
		txtNavegar.setBounds(103, 38, 237, 20);
		add(txtNavegar);
		txtNavegar.setColumns(10);
		
		btnNavegar = new JButton(BTN_NAVEGAR);
		btnNavegar.setBounds(370, 37, 118, 23);	
		add(btnNavegar);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 82, 489, 269);
		
		add(scrollPane);
		
		ModeloLista = new DefaultListModel<String>();
	
		listaWebs = new JList<String>();
		scrollPane.setViewportView(listaWebs);
		
		listaWebs.setModel(ModeloLista);
		
		btnEliminar = new JButton(BTN_ELIMINAR);
		btnEliminar.setBounds(381, 362, 118, 23);
		add(btnEliminar);
		
		btnLimpiar = new JButton(BTN_LIMPIAR);
		btnLimpiar.setBounds(212, 362, 159, 23);
		add(btnLimpiar);
		
		
	}
	
	public void hacerVisible() {
		setVisible(true);
	}
	
	public void hacerInvisible() {
		setVisible(false);
	}
	
	public void cargarLista() { 
		ModeloLista.clear();
		String linea;

		try (BufferedReader br = new BufferedReader(
				new InputStreamReader(new FileInputStream("ListaWebs.txt"), "UTF-8"));) {
			
			while((linea = br.readLine()) != null) { 
				ModeloLista.addElement(linea);
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void guardarLista(DefaultListModel<String> listaWebs) throws IOException { 
		BufferedWriter bw = new BufferedWriter(new FileWriter("ListaWebs.txt"));
	    
		for (int i = 0; i < listaWebs.size(); i++) {
			bw.write(listaWebs.get(i).toString() + "\n");
		}
		bw.close();
	}
	
	public JList<String> getList() {
		return listaWebs;
	}
	
	public DefaultListModel<String> getListaModelo() {
		return ModeloLista;
	}
	
		
	public String txtNavegar() {
		return txtNavegar.getText();
	}

	public void aniadirWebLista(String web) {
		if (!ModeloLista.contains(web)) {
			ModeloLista.addElement(web);
		}		
		
	}
	
	public void setlModel(DefaultListModel<String> ModeloLista) {
		this.ModeloLista = ModeloLista;
	}

	public void setListener(PFListener l) {
		btnNavegar.addActionListener(l);
		btnLimpiar.addActionListener(l);
		btnEliminar.addActionListener(l);
	}

	public void limpiarTexto() {
		txtNavegar.setText("");
	}
	
	public void mostrarMsjError(String msj) {
		JOptionPane.showMessageDialog(this, msj, "Error", JOptionPane.ERROR_MESSAGE);
		
	}
	
	public void mostrarMsgInfo(String msg) {
		JOptionPane.showMessageDialog(this, msg, "Información de operación", JOptionPane.INFORMATION_MESSAGE);
		
	}

	public void limpiarRegistros() {
		ModeloLista.removeAllElements();
		try {
			guardarLista(ModeloLista);
		} catch (IOException e) {
			mostrarMsjError("ERROR AL LIMPIAR REGISTROS");
		}
		
	}

	public String eliminar(int registroSelect) {
		String registroEliminado = ModeloLista.remove(registroSelect);
		
		try {
			guardarLista(ModeloLista);
		} catch (IOException e) {
			mostrarMsjError("NO SE PUDO ELIMINAR EL REGISTRO ESPECIFICADO");
		}
		
		return registroEliminado;
		
	}
 }
