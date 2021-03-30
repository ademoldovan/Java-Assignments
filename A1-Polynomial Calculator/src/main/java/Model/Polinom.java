package Model;
import java.util.ArrayList;

public class Polinom {

	private ArrayList<Monom> polinom;

	public Polinom(){
		
	}
	
	public Polinom(ArrayList<Monom> polinom) {
		this.setPolinom(polinom);
	}

	public ArrayList<Monom> getPolinom() {
		return polinom;
	}

	public void setPolinom(ArrayList<Monom> polinom) {
		this.polinom = polinom;
	}
}
