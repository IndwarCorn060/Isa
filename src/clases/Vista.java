package clases;

import java.awt.*;
import java.util.Vector;

import javax.swing.*;

public class Vista extends JFrame{
	
	private static final long serialVersionUID = 1L;

	private JMenuBar mb;
	private JMenu m_nueva, m_opciones, m_ayuda;
	private JMenuItem mi_nuevaMision, mi_modificarValores, mi_acercaDe;
	
	private JLabel lbllvl, lbltotalexp, lblexptolvlup;
	private JProgressBar bar;
	private JTable tabla;
	private JButton btn_editar, btn_completar;
	
	private JPanel norte, norte_1, norte_1_1, sur;
	private JScrollPane sc_tabla;
	
	private void crearComponentes() {
		this.mb = new JMenuBar();
		this.m_nueva = new JMenu("Nueva");
		this.m_opciones = new JMenu("Opciones");
		this.m_ayuda = new JMenu("Ayuda");
		this.mi_nuevaMision = new JMenuItem("Nueva Mision");
		this.mi_modificarValores = new JMenuItem("Modificar Valores");
		this.mi_acercaDe = new JMenuItem("Acerca de..");
		
		this.lbllvl = new JLabel("0");
		this.lbllvl.setFont(new Font("Arial", Font.BOLD, 30));
		this.lbltotalexp = new JLabel("Exp Total: ");
		this.lblexptolvlup = new JLabel("Exp para subir: ");
		this.lblexptolvlup.setAlignmentX(RIGHT_ALIGNMENT);
		this.bar = new JProgressBar();
		this.tabla = new JTable();
		//this.tabla.setEnabled(false);
		this.btn_editar = new JButton("Editar");
		this.btn_completar = new JButton("Completar");
		
		this.norte = new JPanel();
		this.norte_1 = new JPanel(new GridLayout(2, 1));
		this.norte_1_1 = new JPanel(new GridLayout(1,3));
		this.sur = new JPanel();
		this.sc_tabla = new JScrollPane(this.tabla);
	}
	
	private void montarComponentes(Container c) {
		this.setJMenuBar(this.mb);
		this.mb.add(this.m_nueva);
			this.m_nueva.add(this.mi_nuevaMision);
		this.mb.add(this.m_opciones);
			this.m_opciones.add(this.mi_modificarValores);
		this.mb.add(this.m_ayuda);
			this.m_ayuda.add(this.mi_acercaDe);
		
		c.add(this.norte, BorderLayout.NORTH);
			this.norte.add(this.lbllvl);
			this.norte.add(this.norte_1);
				this.norte_1.add(this.bar);
				this.norte_1.add(this.norte_1_1);
					this.norte_1_1.add(this.lbltotalexp);
					this.norte_1_1.add(new JLabel());
					this.norte_1_1.add(this.lblexptolvlup);
		c.add(this.sc_tabla, BorderLayout.CENTER);
		c.add(this.sur, BorderLayout.SOUTH);
			this.sur.add(this.btn_editar);
			this.sur.add(this.btn_completar);
	}
	
	public void controlador(Controlador c) {
		this.btn_editar.addActionListener(c);
		this.btn_completar.addActionListener(c);
		this.mi_nuevaMision.addActionListener(c);
		this.mi_modificarValores.addActionListener(c);
		this.mi_acercaDe.addActionListener(c);
	}
	
	public Vista() {
		super("LvL Up");
		this.crearComponentes();
		this.montarComponentes(this.getContentPane());
	}

	public JLabel getLbllvl() {
		return lbllvl;
	}

	public JLabel getLbltotalexp() {
		return lbltotalexp;
	}

	public JLabel getLblexptolvlup() {
		return lblexptolvlup;
	}

	public JProgressBar getBar() {
		return bar;
	}

	public JTable getTabla() {
		return tabla;
	}

	public JMenuItem getMi_nuevaMision() {
		return mi_nuevaMision;
	}

	public JMenuItem getMi_modificarValores() {
		return mi_modificarValores;
	}

	public JMenuItem getMi_acercaDe() {
		return mi_acercaDe;
	}

	public JButton getBtn_editar() {
		return btn_editar;
	}

	public JButton getBtn_completar() {
		return btn_completar;
	}
	
	
	
}
