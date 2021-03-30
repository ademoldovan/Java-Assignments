package model;

public class Clients {

	private String nume;
	private String adresa;

	public Clients() {
	}

	public Clients(String nume, String adresa) {
		this.nume = nume;
		this.adresa = adresa;
	}

	public String getNume() {
		return nume;
	}

	public void setNume(String nume) {
		this.nume = nume;
	}

	public String getAdresa() {
		return adresa;
	}

	public void setAdresa(String adresa) {
		this.adresa = adresa;
	}

}
