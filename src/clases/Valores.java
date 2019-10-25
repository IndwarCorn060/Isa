package clases;

import java.io.Serializable;

public class Valores implements Serializable{
	private static final long serialVersionUID = 1L;
	private int expToLvlUpBase;
	private float modificadorLvlUp;
	
	public Valores(int base, float mod) {
		this.expToLvlUpBase=base;
		this.modificadorLvlUp=mod;
	}

	public int getExpToLvlUpBase() {
		return expToLvlUpBase;
	}

	public float getModificadorLvlUp() {
		return modificadorLvlUp;
	}

	
	
	
}
