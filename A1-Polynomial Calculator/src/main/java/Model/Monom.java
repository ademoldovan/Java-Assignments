package Model;

public class Monom {

	private double coeficient;
	private int gradMonom;

	public Monom(double coeficient, int gradMonom) {
		this.coeficient = coeficient;
		this.gradMonom = gradMonom;
	}

	public int getGradMonom() {
		return gradMonom;
	}

	public void setGradMonom(int gradMonom) {
		this.gradMonom = gradMonom;
	}

	public double getCoeficient() {
		return coeficient;
	}

	public void setCoeficient(int coeficient) {
		this.coeficient = coeficient;
	}

}
