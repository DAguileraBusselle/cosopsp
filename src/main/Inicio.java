package main;

import java.awt.EventQueue;

import control.PFListener;
import view.PCrear;
import view.PEnBlanco;
import view.PNavegar;
import view.VPpal;

public class Inicio {

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {

			@Override
			public void run() {
				VPpal vp = new VPpal();
			    PNavegar pn = new PNavegar();			  
			    PEnBlanco pe = new PEnBlanco();
			    PCrear pc = new PCrear();
				PFListener l = new PFListener(vp, pn, pe, pc);
				
				
				vp.setListener(l);				
				pn.setListener(l);
				pc.setListener(l);
				vp.hacerVisible();
			} 
			
		});

 }
}
