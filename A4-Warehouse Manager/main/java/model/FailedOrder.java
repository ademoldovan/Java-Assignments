package model;

public class FailedOrder {

	private int nrOrder;
	private String numeC;
	private String numeP;

	public FailedOrder(int nrOrder, String numeC, String numeP) {
		this.nrOrder = nrOrder;
		this.numeC = numeC;
		this.numeP = numeP;
	}

	public int getNrOrder() {
		return nrOrder;
	}

	public void setNrOrder(int nrOrder) {
		this.nrOrder = nrOrder;
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

}
