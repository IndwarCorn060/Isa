package clases;

import java.io.*;
import java.util.LinkedList;

public class ResetValores {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Valores v = new Valores(10,1f,50,true);
		
		LinkedList<Mision> misiones = new LinkedList<Mision>();
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