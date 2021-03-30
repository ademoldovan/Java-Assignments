package Model;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import Model.Monom;
import Model.Polinom;

public class Model {

	public String adunaPolinoame(String pol1, String pol2) {
		Polinom polinom1 = creazaPolinom(pol1);
		Polinom polinom2 = creazaPolinom(pol2);
		int i = 0;
		int j = 0;
		ArrayList<Monom> pol = new ArrayList<Monom>();
		while (i < polinom1.getPolinom().size() && j < polinom2.getPolinom().size()) {
			if (polinom1.getPolinom().get(i).getGradMonom() == polinom2.getPolinom().get(j).getGradMonom()) {
				if (polinom1.getPolinom().get(i).getCoeficient() + polinom2.getPolinom().get(j).getCoeficient() != 0)
					pol.add(new Monom(
							polinom1.getPolinom().get(i).getCoeficient() + polinom2.getPolinom().get(j).getCoeficient(),
							polinom1.getPolinom().get(i).getGradMonom()));
				i++;
				j++;
			} else {
				if (polinom1.getPolinom().get(i).getGradMonom() > polinom2.getPolinom().get(j).getGradMonom()) {
					pol.add(new Monom(polinom1.getPolinom().get(i).getCoeficient(),
							polinom1.getPolinom().get(i).getGradMonom()));
					i++;
				} else {
					pol.add(new Monom(polinom2.getPolinom().get(j).getCoeficient(),
							polinom2.getPolinom().get(j).getGradMonom()));
					j++;
				}
			}
		}
		while (i < polinom1.getPolinom().size()) {
			pol.add(new Monom(polinom1.getPolinom().get(i).getCoeficient(),
					polinom1.getPolinom().get(i).getGradMonom()));
			i++;
		}
		while (j < polinom2.getPolinom().size()) {
			pol.add(new Monom(polinom2.getPolinom().get(j).getCoeficient(),
					polinom2.getPolinom().get(j).getGradMonom()));
			j++;
		}
		Polinom polinom3 = new Polinom(pol);
		return creazaString(polinom3);
	}

	/*
	 * Metoda care scade doua polinoame
	 * 
	 * @param polinom1,polinom2 sunt polinoamele care se scad, polinom1-polinom2
	 * 
	 * @return polinom3 = diferenta celor doua polinoame
	 */
	public String scadePolinoame(String pol1, String pol2) {
		Polinom polinom1 = creazaPolinom(pol1);
		Polinom polinom2 = creazaPolinom(pol2);
		int i = 0;
		int j = 0;
		ArrayList<Monom> pol = new ArrayList<Monom>();
		if (pol1.compareTo(pol2) == 0) {
			String s = new String();
			return s + '0';
		}
		while (i < polinom1.getPolinom().size() && j < polinom2.getPolinom().size()) {
			if (polinom1.getPolinom().get(i).getGradMonom() == polinom2.getPolinom().get(j).getGradMonom()) {
				if (polinom1.getPolinom().get(i).getCoeficient() - polinom2.getPolinom().get(j).getCoeficient() != 0)
					pol.add(new Monom(
							polinom1.getPolinom().get(i).getCoeficient() - polinom2.getPolinom().get(j).getCoeficient(),
							polinom1.getPolinom().get(i).getGradMonom()));
				i++;
				j++;
			} else {
				if (polinom1.getPolinom().get(i).getGradMonom() > polinom2.getPolinom().get(j).getGradMonom()) {
					pol.add(new Monom(polinom1.getPolinom().get(i).getCoeficient(),
							polinom1.getPolinom().get(i).getGradMonom()));
					i++;
				} else {
					pol.add(new Monom(polinom2.getPolinom().get(j).getCoeficient(),
							polinom2.getPolinom().get(j).getGradMonom()));
					j++;
				}
			}
		}
		while (i < polinom1.getPolinom().size()) {
			pol.add(new Monom((-1) * polinom1.getPolinom().get(i).getCoeficient(),
					polinom1.getPolinom().get(i).getGradMonom()));
			i++;
		}
		while (j < polinom2.getPolinom().size()) {
			pol.add(new Monom((-1) * polinom2.getPolinom().get(j).getCoeficient(),
					polinom2.getPolinom().get(j).getGradMonom()));
			j++;
		}
		Polinom polinom3 = new Polinom(pol);
		return creazaString(polinom3);
	}

	/*
	 * Metoda care inmulteste 2 polinoame
	 * 
	 * @param polinom1,polinom2 sunt polinoamele care se inmultesc
	 * 
	 * @return polinom3 = produsul celor doua polinoame
	 */
	public String inmultestePolinoame(String pol1, String pol2) {
		Polinom polinom1 = creazaPolinom(pol1);
		Polinom polinom2 = creazaPolinom(pol2);
		ArrayList<Monom> polinom3 = new ArrayList<Monom>();
		for (Monom i : polinom1.getPolinom()) {
			for (Monom j : polinom2.getPolinom()) {
				polinom3.add(new Monom(i.getCoeficient() * j.getCoeficient(), i.getGradMonom() + j.getGradMonom()));
			}
		}
		int a = 0;
		a = polinom3.get(0).getGradMonom();
		a++;
		int[] v = new int[a];
		for (int i = 0; i < a; i++)
			v[i] = 0;
		for (int i = 0; i < polinom3.size(); i++)
			v[polinom3.get(i).getGradMonom()] = (int) (v[polinom3.get(i).getGradMonom()] + polinom3.get(i).getCoeficient());
		polinom3.clear();
		for (int i = a - 1; i >= 0; i--)
			if (v[i] != 0)
				polinom3.add(new Monom(v[i], i));
		Polinom polinom = new Polinom(polinom3);
		return creazaString(polinom);
	}

	/*
	 * Metoda care calculeaza derivata unui polinom
	 * 
	 * @param polinom1 este polinomul care se deriveaza
	 * 
	 * @return polinom1 - polinomul primit ca parametru, derivat
	 */
	public String derivarePolinom(String pol1) {
		Polinom polinom1 = creazaPolinom(pol1);
		int i = 0;
		if (polinom1.getPolinom().get(0).getGradMonom() == 0) {
			return "0";
		}
		while (i < polinom1.getPolinom().size()) {
			polinom1.getPolinom().set(i,
					new Monom(
							polinom1.getPolinom().get(i).getCoeficient() * polinom1.getPolinom().get(i).getGradMonom(),
							polinom1.getPolinom().get(i).getGradMonom() - 1));
			i++;
		}
		if (polinom1.getPolinom().get(polinom1.getPolinom().size() - 1).getCoeficient() == 0) {
			polinom1.getPolinom().remove(polinom1.getPolinom().size() - 1);
		}
		return creazaString(polinom1);
	}

	/*
	 * Metoda care calculeaza integrala unui polinom
	 * 
	 * @param polinom1 este polinomul pentru care se calculeaza integrala
	 * 
	 * @return polinom2 - polinomul primit ca parametru, integrat
	 */
	public String integreazaPolinom(String pol1) {
		Polinom polinom1 = creazaPolinom(pol1);
		int i = 0;
		while (i < polinom1.getPolinom().size()) {
			polinom1.getPolinom().set(i,
					new Monom(
							polinom1.getPolinom().get(i).getCoeficient()
									/ (polinom1.getPolinom().get(i).getGradMonom() + 1),
							polinom1.getPolinom().get(i).getGradMonom() + 1));
			i++;
		}
		return creazaString(polinom1);
	}

	/*
	 * Metoda care imparte 2 polinoame
	 * 
	 * @param polinom1,polinom2 sunt polinoamele care se impart, polinom1/polinom2
	 * 
	 * @return polinom3-catul celor doua polinoame; polinom4-restul celor 2
	 * polinoame
	 */

	public String impartePolinoame(String pol1, String pol2) {
		if (pol1.compareTo(pol2) == 0) {
			return "C: 1  ; R: 0";
		}
		Polinom polinom1 = creazaPolinom(pol1);
		Polinom polinom2 = creazaPolinom(pol2);

		Polinom cat = new Polinom(new ArrayList<Monom>()); // catul
		if (polinom1.getPolinom().get(0).getGradMonom() < polinom2.getPolinom().get(0).getGradMonom()) {
			return "C: 0  ; R:" + pol1;
		}

		while ((polinom1.getPolinom().size() != 0)
				&& polinom1.getPolinom().get(0).getGradMonom() >= polinom2.getPolinom().get(0).getGradMonom()) {

			double co = polinom1.getPolinom().get(0).getCoeficient() / polinom2.getPolinom().get(0).getCoeficient();
			int ex = polinom1.getPolinom().get(0).getGradMonom() - polinom2.getPolinom().get(0).getGradMonom();
			BigDecimal bd = new BigDecimal(co).setScale(2, RoundingMode.HALF_UP);
			Monom m = new Monom(co, ex);
			cat.getPolinom().add(m);
			System.out.println("ex: "+ex+"co: "+co);
			ArrayList<Monom> ar = new ArrayList<Monom>();
			ar.add(m);

			Polinom r = new Polinom(ar);
			String inmultire = this.inmultestePolinoame(creazaString(polinom2), creazaString(r));  //nu merge aici
			Polinom newPol = creazaPolinom(inmultire);
			
			System.out.println(inmultire);
			
			r.getPolinom().remove(0);
			
			polinom1 = creazaPolinom(scadePolinoame(creazaString(polinom1), creazaString(newPol)));

		}
		String cat1 = creazaString(cat);
		String rest = creazaString(polinom1);
		String rezultat = new String();
		rezultat = rezultat + "C:" + cat1 + "  ;  R: " + rest;
		return rezultat;
	}

	/*
	 * Metoda care converteste un polinom intr-un string
	 * 
	 * @param polinom- polinomul care urmeaza sa fie convertit in string
	 * 
	 * @return s- stringul caracteristic polinomului primit ca parametru
	 */
	public static String creazaString(Polinom polinom) {
		String s = new String();
		int i = 0;
		while (i < polinom.getPolinom().size()) {
			int a = (int) (polinom.getPolinom().get(i).getCoeficient() * 100);
			if (polinom.getPolinom().get(i).getCoeficient() > 0 && i != 0)
				s = s + "+";
			if (polinom.getPolinom().get(i).getGradMonom() == 0) {
				if (a % 10 == 0 && a / 10 % 10 == 0)
					s = s + a / 100;
				else
					s = s + String.format("%.1f", polinom.getPolinom().get(i).getCoeficient());
			} else {
				if (polinom.getPolinom().get(i).getGradMonom() == 1) {
					if (polinom.getPolinom().get(i).getCoeficient() != 1) {

						if (a % 10 == 0 && a / 10 % 10 == 0)
							s = s + a / 100 + "x";
						else
							s = s + String.format("%.1f", polinom.getPolinom().get(i).getCoeficient()) + "x";
					} else {
						s = s + "x";
					}
				} else {
					if (polinom.getPolinom().get(i).getCoeficient() != 1) {
						if (a % 10 == 0 && a / 10 % 10 == 0)
							s = s + a / 100 + "x^" + polinom.getPolinom().get(i).getGradMonom();
						else
							s = s + String.format("%.1f", polinom.getPolinom().get(i).getCoeficient()) + "x^"
									+ polinom.getPolinom().get(i).getGradMonom();
					} else {
						s = s + "x^" + polinom.getPolinom().get(i).getGradMonom();
					}
				}
			}
			i++;
		}
		return s;
	}

	/*
	 * Metoda care converteste un string intr-un obiect de tipul Polinom
	 * 
	 * @param s - stringul care se converteste
	 * 
	 * @return pol1 = polinomul caracteristic stringului primit ca parametru
	 */
	public static Polinom creazaPolinom(String s) {
		int i = 0;
		int numar = 0;
		int putere = 0;
		ArrayList<Monom> pol = new ArrayList<Monom>();
		while (i < s.length()) {
			numar = 0;
			putere = 0;
			if (Character.toString(s.charAt(i)).compareTo("-") == 0) {
				if (s.charAt(i + 1) == 'x') {
					numar = -1;
					i++;
				} else {
					i++;
					while (i < s.length() && s.charAt(i) >= '0' && s.charAt(i) <= '9') {
						numar = numar * 10 + Character.getNumericValue(s.charAt(i));
						i++;
					}
					numar = numar * (-1);
				}
			} else {
				if (i == 0) {
					if (s.charAt(i) == 'x') {
						numar = 1;

					} else
						while (i < s.length() && s.charAt(i) >= '0' && s.charAt(i) <= '9') {
							numar = numar * 10 + Character.getNumericValue(s.charAt(i));
							i++;
						}
				} else {
					if (s.charAt(i) == '+' && s.charAt(i + 1) == 'x') {
						numar = 1;
					} else {
						if (s.charAt(i) == '+' && s.charAt(i + 1) >= '0' && s.charAt(i + 1) <= '9') {
							i++;
							while (i < s.length() && s.charAt(i) >= '0' && s.charAt(i) <= '9') {
								numar = numar * 10 + Character.getNumericValue(s.charAt(i));
								i++;
							}
						}
					}
				}
			}
			if (numar == 1)
				i++;
			if ((i < s.length() - 1) && (s.charAt(i) == 'x' && s.charAt(i + 1) == '^')) {
				i += 2;
				while (i < s.length() && s.charAt(i) >= '0' && s.charAt(i) <= '9') {
					putere = putere * 10 + Character.getNumericValue(s.charAt(i));
					i++;
				}
				pol.add(new Monom(numar, putere));
			} else {
				if ((i < s.length() - 2) && s.charAt(i) == 'x' && s.charAt(i + 1) != '^') {
					putere = 1;
					pol.add(new Monom(numar, putere));
					i++;
					int semnulet = 1;
					if (s.charAt(i) == '-') {
						semnulet = -1;
					}
					i++;
					numar = 0;
					while (i < s.length() && s.charAt(i) >= '0' && s.charAt(i) <= '9') {
						numar = numar * 10 + Character.getNumericValue(s.charAt(i));
						i++;
					}
					pol.add(new Monom(numar * semnulet, 0));
				} else {
					if ((i == s.length() - 1) && s.charAt(i) == 'x') {
						putere = 1;
						pol.add(new Monom(numar, putere));
						i++;
					} else {
						pol.add(new Monom(numar, 0));
						i++;
					}
				}
			}
		}
		Polinom pol1 = new Polinom(pol);
		return pol1;
	}
}
