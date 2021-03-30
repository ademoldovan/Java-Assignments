package model;

public class Orders {

	private int nrOrder;
	private String numeC;
	private String numeP;
	private int cantitate;
	
	public Orders() {

	}

	public Orders( String numeC, String numeP, int cantitate) {
		this.numeC = numeC;
		this.numeP = numeP;
		this.cantitate = cantitate;
	}

	public String getNumeC() {
		return numeC;
	}

	public void setNumeC(String numeC) {
		this.numeC = numeC;
	}

	public String getNumeP() {
		return numeP;
	}

	public void setNumeP(String numeP) {
		this.numeP = numeP;
	}

	public int getCantitate() {
		return cantitate;
	}

	public void setCantitate(int cantitate) {
		this.cantitate = cantitate;
	}

	public int getNrOrder() {
		return nrOrder;
	}

	public void setNrOrder(int nrOrder) {
		this.nrOrder = nrOrder;
	}

}
