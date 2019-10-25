package clases;

import java.io.Serializable;

public class Mision implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private int exp;
	private String nombre, descripcion;
	private boolean completada;
	
	public Mision(int exp, String nombre, String descripcion, boolean f) {
		this.exp=exp;
		this.nombre=nombre;
		this.descripcion=descripcion;
		this.completada = f;
	}

	public int getExp() {
		return exp;
	}

	public String getNombre() {
		return nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public boolean isCompletada() {
		return completada;
	}
	
	public String toString() {
		return this.nombre+"*"+this.exp+"*"+this.completada+"*"+this.descripcion;
	}

	public void setExp(int exp) {
		this.exp = exp;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public void setCompletada(boolean completada) {
		this.completada = completada;
	}
	
	

}
