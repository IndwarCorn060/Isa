package clases;

import java.io.Serializable;

public class Valores implements Serializable{
	private static final long serialVersionUID = 1L;
	private int expToLvlUpBase;
	private float modificadorLvlUp;
	private int limite;
	private boolean lineal;
	
	public Valores(int base, float mod, int limite, boolean f) {
		this.expToLvlUpBase=base;
		this.modificadorLvlUp=mod;
		this.limite=limite;
		this.lineal=f;
	}

	public int getExpToLvlUpBase() {
		return expToLvlUpBase;
	}

	public float getModificadorLvlUp() {
		return modificadorLvlUp;
	}

	public int getLimite() {
		return limite;
	}

	public void setLimite(int limite) {
		this.limite = limite;
	}

	public void setExpToLvlUpBase(int expToLvlUpBase) {
		this.expToLvlUpBase = expToLvlUpBase;
	}

	public void setModificadorLvlUp(float modificadorLvlUp) {
		this.modificadorLvlUp = modificadorLvlUp;
	}

	public boolean isLineal() {
		return lineal;
	}

	public void setLineal(boolean lineal) {
		this.lineal = lineal;
	}

	
	
	
}
