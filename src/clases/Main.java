package clases;

import javax.swing.JFrame;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Vista v = new Vista();
		Controlador c = new Controlador(v);
		v.controlador(c);
		v.addWindowListener(c);
		v.pack();
		v.setSize(600,400);
		v.setVisible(true);

	}

}
