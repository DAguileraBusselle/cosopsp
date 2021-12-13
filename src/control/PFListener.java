package control;

import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import javax.swing.JButton;

import view.PCrear;
import view.PEnBlanco;
import view.PNavegar;
import view.VPpal;

public class PFListener implements ActionListener{

	private VPpal vp;
	private PNavegar pn;
	private PEnBlanco pe;
	private PCrear pc;
	
	public PFListener(VPpal vp, PNavegar pn, PEnBlanco pe, PCrear pc) {
		this.vp = vp;
		this.pn = pn;
		this.pe = pe;
		this.pc = pc;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() instanceof JButton) {
			if (e.getActionCommand().equals(VPpal.BTN_LANZAR)) {
				String opcion = vp.txtCbxSelect();
				
				if (opcion.equals(VPpal.OPCION_NAVEGAR)) {
					vp.cargarPanel(pn);
					pn.cargarLista();
				} else if (opcion.equals(VPpal.OPCION_WORD)) {
					vp.cargarPanel(pe);
					try {
						Runtime r = Runtime.getRuntime();
						String comando = "C:\\Program Files\\Microsoft Office\\root\\Office16\\WINWORD";

						r.exec(comando);
					} catch (IOException e1) {
						pe.mostrarMsjError("NO SE PUDO ABRIR WORD");
					}
				} else if (opcion.equals(VPpal.OPCION_POWERPOINT)) {
					vp.cargarPanel(pe);
					try {
						Runtime r = Runtime.getRuntime();
						String comando = "C:\\Program Files\\Microsoft Office\\root\\Office16\\POWERPNT";

						r.exec(comando);
					} catch (IOException e1) {
						pe.mostrarMsjError("NO SE PUDO ABRIR POWERPOINT");
					}
				} else if (opcion.equals(VPpal.OPCION_EXCEL)){
					vp.cargarPanel(pe);
					try {
						Runtime r = Runtime.getRuntime();
						String comando = "C:\\Program Files\\Microsoft Office\\root\\Office16\\EXCEL";

						r.exec(comando);
					} catch (IOException e1) {
						pe.mostrarMsjError("NO SE PUDO ABRIR EXCEL");
					}
				} else if (opcion.equals(VPpal.OPCION_ARCHIVO)) {
					try {
						Runtime.getRuntime().exec("explorer.exe /documents, path");
					} catch (IOException e1) {
						vp.mostrarMsjError("NO SE PUDO LLEVAR A CABO LA ACCION");
					}
				} else if (opcion.equals(VPpal.OPCION_CREAR)) {
					vp.cargarPanel(pc);					
					
				}
			} else if (e.getActionCommand().equals(PNavegar.BTN_NAVEGAR)) {
				String web = pn.txtNavegar();
				boolean webValida = false;
				String webLista;
				
					if (web.isEmpty()) {
						webLista = pn.getList().getSelectedValue();
						
						try {
							
							Desktop.getDesktop().browse(new URI(webLista));
							
						} catch (IOException e1) {
							pn.mostrarMsjError("ERROR");
							
						} catch (URISyntaxException e1) {
							pn.mostrarMsjError("Debe introducir una pagina web valida");
							
						}
					} else {
						pn.getList().setSelectedIndex(-1);
						try {
							
							Desktop.getDesktop().browse(new URI(web));
							webValida = true;
						} catch (IOException e1) {
							pn.mostrarMsjError("ERROR");
							webValida = false;
						} catch (URISyntaxException e1) {
							pn.mostrarMsjError("Debe introducir una pagina web valida");
							webValida = false;
						}
						
						
					if (webValida) {
							pn.aniadirWebLista(web);
								try {
									pn.guardarLista(pn.getListaModelo());
								} catch (IOException e1) {
									pn.mostrarMsjError("ERROR");
								} 
								pn.limpiarTexto();
							}
					}
							
			} else if (e.getActionCommand().equals(PNavegar.BTN_LIMPIAR)) {
				pn.limpiarRegistros();
				
			} else if (e.getActionCommand().equals(PNavegar.BTN_ELIMINAR)) {
				int registroSelect = pn.getList().getSelectedIndex();
				
				if (registroSelect == -1) {
					pn.mostrarMsgInfo("Debe seleccionar un registro para eliminar");
				} else {
					String registroEliminado = pn.eliminar(registroSelect);
					
					pn.mostrarMsgInfo("Se elimino con exito " + registroEliminado);
				}
				
				
			} else if (e.getActionCommand().equals(PCrear.BTN_CREAR)) {
				String nombre = pc.getNombreArchivo();
				String opcion = pc.getOpcionArchivo();
				if (nombre.isEmpty()) {
					pc.mostrarMsgInfo("Debe introducir un nombre");
				} else {
					File file = null;
					if (opcion.equals(PCrear.OPCION_CREAR_EX)) {
						file = new File("C:\\Users\\Usuario\\Documents\\" + nombre + ".xlsx");
					} else if (opcion.equals(PCrear.OPCION_CREAR_PW)) {
						file = new File("C:\\Users\\Usuario\\Documents\\" + nombre + ".pptx");
					} else if (opcion.equals(PCrear.OPCION_CREAR_WO)) {
						file = new File("C:\\Users\\Usuario\\Documents\\" + nombre + ".docx");
					}
					
					
					boolean result;
					try {
						result = file.createNewFile();
						if (result) {
							pc.mostrarMsgInfo("Archivo creado " + file.getCanonicalPath());
						} else {
							pc.mostrarMsjError("Ya existe el archivo");
						}
					} catch (IOException e1) {
						pc.mostrarMsjError("NO SE PUDO CREAR EL ARCHIVO");
					}
				}
			}
		
		}
	
	
	}

}
