package model;

public class Product {

	private String nume;
	private int cantitate;
	private double pret;

	public Product(String nume, int cantitate, double pret) {
		this.nume = nume;
		this.cantitate = cantitate;
		this.pret = pret;
	}

	public Product() {
	}

	public String getNume() {
		return nume;
	}

	public void setNume(String nume) {
		this.nume = nume;
	}

	public int getCantitate() {
		return cantitate;
	}

	public void setCantitate(int cantitate) {
		this.cantitate = cantitate;
	}

	public double getPret() {
		return pret;
	}

	public void setPret(double pret) {
		this.pret = pret;
	}

}
