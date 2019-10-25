package clases;

import java.awt.event.*;
import java.io.*;
import java.util.Iterator;
import java.util.LinkedList;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;

public class Controlador extends WindowAdapter implements ActionListener{
	
	private Vista v;
	Valores valores;
	private LinkedList<Mision> misiones;
	
	public Controlador(Vista v) {
		this.v=v;
		this.cargarDatos();
	}
	
	private void cargarDatos() {
		File fichero = new File("./res/data.txt");
		try {
			FileInputStream fi = new FileInputStream(fichero);
			ObjectInputStream oi = new ObjectInputStream(fi);
			this.valores = (Valores) oi.readObject();
			this.misiones = (LinkedList<Mision>) oi.readObject();
			oi.close();
			fi.close();
		} catch (FileNotFoundException e) {
			System.out.println("File not found");
		} catch (IOException e) {
			System.out.println("Error initializing stream");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		this.actualizarBarra();
		this.actualizarTabla();
		System.out.println(this.valores.getExpToLvlUpBase()+" "+this.valores.getModificadorLvlUp());
	}
	
	private void guardarDatos() {
		File fichero = new File("./res/data.txt");
		try {
			FileOutputStream  fi = new FileOutputStream (fichero);
			ObjectOutputStream  oi = new ObjectOutputStream (fi);
			oi.writeObject(this.valores);
			oi.writeObject(this.misiones);
			oi.close();
			fi.close();
			System.out.println("yes");
		} catch (FileNotFoundException e) {
			System.out.println("File not found");
		} catch (IOException e) {
			System.out.println("Error initializing stream");
		}
	}
	
	private int getTotalExp() {
		int exp = 0;
		Iterator<Mision> i = this.misiones.iterator();
		Mision m = null;
		while(i.hasNext()) {
			m = (Mision) i.next();
			if(m.isCompletada()) {
				exp+=m.getExp();
			}
		}
		System.out.println(exp);
		return exp;
	}
	
	private void actualizarBarra() {
		int exp = this.getTotalExp();
		this.v.getLbltotalexp().setText("Exp Total: "+exp);
		JProgressBar bar = this.v.getBar();
		int base = this.valores.getExpToLvlUpBase();
		float mod = this.valores.getModificadorLvlUp();
		int lvl = 0;
		while(exp>base*mod) {
			exp-=base*mod;
			base=(int) (base*mod);
			lvl++;
		}
		this.v.getLbllvl().setText(lvl+"");
		bar.setMinimum(0);
		bar.setMaximum((int)(base*mod));
		bar.setValue(exp);
		this.v.getLblexptolvlup().setText("Exp para subir: "+((int)(base*mod)-exp));
	}
	
	private void actualizarTabla() {
		String nombres[] = {"Nombre", "Descripcion", "Exp", "Completado"};
		String contenido[][] = new String[this.misiones.size()][4];
		Iterator<Mision> it = this.misiones.iterator();
		Mision m = null;
		int cont = 0;
		while(it.hasNext()) {
			m = (Mision) it.next();
			contenido[cont][0] = m.getNombre();
			contenido[cont][1] = m.getDescripcion();
			contenido[cont][2] = ""+m.getExp();
			contenido[cont][3] = (m.isCompletada())?"Si":"No";
			cont++;
		}
		JTable jt = this.v.getTabla();
		DefaultTableModel tableModel = new DefaultTableModel(contenido, nombres) {
			private static final long serialVersionUID = 1L;

			@Override
		    public boolean isCellEditable(int row, int column) {
		       //all cells false
		       return false;
		    }
		};
		jt.setModel(tableModel);
		jt.getModel().addTableModelListener(new TableModelListener() {

			  public void tableChanged(TableModelEvent e) {
			     // your code goes here, whatever you want to do when something changes in the table
				  System.out.println(e.getColumn()+" "+e.getFirstRow());
			  }
			});
		System.out.println(this.misiones.toString());
	}
	
	public void windowClosing(WindowEvent e) {
		
		System.out.println("adios");
		this.guardarDatos();
		System.exit(0);
	}

	private Mision getMision(int fila) {
		Iterator<Mision> it = this.misiones.iterator();
		for(int i=0; i<fila; i++) {
			it.next();
		}
		return (Mision) it.next();
	} 
	
	private void editarMision(int fila) {
		Mision m = getMision(fila);
		System.out.println(m.toString());
		
		JTextField nombre = new JTextField(10);
		nombre.setText(m.getNombre());
	    JTextArea descripcion = new JTextArea(3,10);
	    descripcion.setText(m.getDescripcion());
	    JScrollPane scroll = new JScrollPane(descripcion);
	    JTextField exp = new JTextField(5);
	    exp.setText(m.getExp()+"");
	    
	    JPanel panel = new JPanel();
	    panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
	    JPanel panel1 = new JPanel();
	    panel1.add(new JLabel("Nombre:"));
	    panel1.add(nombre);
	    panel.add(panel1);
	    JPanel panel2 = new JPanel();
	    panel2.add(new JLabel("Descripcion"));
	    panel.add(panel2);
	    panel.add(scroll);
	    JPanel panel3 = new JPanel();
	    panel3.add(new JLabel("Experiencia:"));
	    panel3.add(exp);
	    panel.add(panel3);

	    int result = JOptionPane.showConfirmDialog(null, panel, 
	               "Editar Mision", JOptionPane.OK_CANCEL_OPTION);
	    if (result == JOptionPane.OK_OPTION) {
	         try {
				int expe = Integer.parseInt(exp.getText().toString());
				m.setNombre(nombre.getText().toString());
				m.setDescripcion(descripcion.getText().toString());
				m.setExp(expe);
				this.actualizarTabla();
				this.actualizarBarra();
			} catch (NumberFormatException  e) {
				JOptionPane.showMessageDialog(null, "Error al introducir datos", "Error", JOptionPane.ERROR_MESSAGE);
			}
	      }
	}
	
	private void completarMision(int fila) {
		Mision m = getMision(fila);
		if(m.isCompletada()) {
			int dialogResult = JOptionPane.showConfirmDialog (null, "¿Revertir la mision al estado\nde no completada?","Confirmacion",JOptionPane.YES_NO_OPTION);
			if(dialogResult == JOptionPane.YES_OPTION){
			  m.setCompletada(false);
			}
			this.actualizarTabla();
			this.actualizarBarra();
		}
		else {
			int dialogResult = JOptionPane.showConfirmDialog (null, "¿Completar misión marcada?","Confirmacion",JOptionPane.YES_NO_OPTION);
			if(dialogResult == JOptionPane.YES_OPTION){
			  m.setCompletada(true);
			}
			this.actualizarTabla();
			this.actualizarBarra();
		}
	}
	
	private void nuevaMision() {
		JTextField nombre = new JTextField(10);
	    JTextArea descripcion = new JTextArea(3,10);
	    JScrollPane scroll = new JScrollPane(descripcion);
	    JTextField exp = new JTextField(5);
	    
	    JPanel panel = new JPanel();
	    panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
	    JPanel panel1 = new JPanel();
	    panel1.add(new JLabel("Nombre:"));
	    panel1.add(nombre);
	    panel.add(panel1);
	    JPanel panel2 = new JPanel();
	    panel2.add(new JLabel("Descripcion"));
	    panel.add(panel2);
	    panel.add(scroll);
	    JPanel panel3 = new JPanel();
	    panel3.add(new JLabel("Experiencia:"));
	    panel3.add(exp);
	    panel.add(panel3);

	    int result = JOptionPane.showConfirmDialog(null, panel, 
	               "Editar Mision", JOptionPane.OK_CANCEL_OPTION);
	    if (result == JOptionPane.OK_OPTION) {
	    	
	         try {
				int expe = Integer.parseInt(exp.getText().toString());
				String nombr = nombre.getText().toString();
				String descrip = descripcion.getText().toString();
				if(nombr.equals("")||descrip.equals("")) {
					JOptionPane.showMessageDialog(null, "Error al introducir datos", "Error", JOptionPane.ERROR_MESSAGE);
				}
				else {
					Mision m = new Mision(expe, nombr, descrip, false);
					this.misiones.add(0, m);
					this.actualizarTabla();
					this.actualizarBarra();
				}
			} catch (NumberFormatException  e) {
				JOptionPane.showMessageDialog(null, "Error al introducir la experiencia", "Error", JOptionPane.ERROR_MESSAGE);
			}
	      }
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == this.v.getBtn_editar()) {
			//System.out.println(this.v.getTabla().getSelectedColumn()+" "+this.v.getTabla().getSelectedRow());
			if(this.v.getTabla().getSelectedRow()!=-1) {
				this.editarMision(this.v.getTabla().getSelectedRow());
			}else {
				System.out.println("mision no seleccionada");
			}
		}else if(e.getSource() == this.v.getBtn_completar()) {
			if(this.v.getTabla().getSelectedRow()!=-1) {
				this.completarMision(this.v.getTabla().getSelectedRow());
			}else {
				System.out.println("mision no seleccionada");
			}
		}else if(e.getSource() == this.v.getMi_nuevaMision()) {
			this.nuevaMision();
		}else if(e.getSource() == this.v.getMi_modificarValores()) {
			
		}else if(e.getSource() == this.v.getMi_acercaDe()) {
			System.out.println("hola");
		}
	}

}
