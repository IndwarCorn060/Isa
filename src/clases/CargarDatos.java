package clases;

import java.io.*;
import java.util.LinkedList;

public class CargarDatos {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Valores v = new Valores(10,1.1f);
		
		LinkedList<Mision> misiones = new LinkedList<Mision>();
		Mision m1 = new Mision(5, "colada", "haz la colada",false);
		Mision m2 = new Mision(12, "cocina", "limpia la cocina",false);
		Mision m3 = new Mision(4, "empresas", "haz los ejercicios",true);
		Mision m4 = new Mision(8, "daw", "haz los ejercicios",true);
		misiones.add(m1);
		misiones.add(m2);
		misiones.add(m3);
		misiones.add(m4);
		FileOutputStream f = null;
		ObjectOutputStream o = null;
		try {
			f = new FileOutputStream(new File("./res/data.txt"));
			o = new ObjectOutputStream(f);

			// Write objects to file
			System.out.println("1");
			o.writeObject(v);
			o.writeObject(misiones);
			System.out.println("2");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {

			if (f != null) {
				try {
					f.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

			if (o != null) {
				try {
					o.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

		}

	}

}
