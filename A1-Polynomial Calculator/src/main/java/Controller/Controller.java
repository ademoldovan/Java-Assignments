package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Model.Model;
import View.View;

public class Controller {

	private View theView;
	private Model theModel;

	public Controller(View theView, Model theModel) {
		this.theModel = theModel;
		this.theView = theView;

		this.theView.addPlusButtonActionListener(new PlusListener());
		this.theView.addMinusButtonActionListener(new MinusListener());
		this.theView.addDerivareButtonActionListener(new DerivareListener());
		this.theView.addOriButtonActionListener(new OriListener());
		this.theView.addIntegrareButtonActionListener(new IntegrareListener());
		this.theView.addImpartireButtonActionListener(new ImpartireListener());
	}

	class PlusListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			String polinom1 = new String();
			String polinom2 = new String();
			String polinom3 = new String();
			polinom1 = theView.getPolinom1();
			polinom2 = theView.getPolinom2();
			polinom3 = theModel.adunaPolinoame(polinom1, polinom2);
			theView.setRezultat(polinom3);
		}
	}

	class MinusListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			String polinom1 = new String();
			String polinom2 = new String();
			String polinom3 = new String();
			polinom1 = theView.getPolinom1();
			polinom2 = theView.getPolinom2();
			polinom3 = theModel.scadePolinoame(polinom1, polinom2);
			theView.setRezultat(polinom3);
		}
	}

	class OriListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			String polinom1 = new String();
			String polinom2 = new String();
			String polinom3 = new String();
			polinom1 = theView.getPolinom1();
			polinom2 = theView.getPolinom2();
			polinom3 = theModel.inmultestePolinoame(polinom1, polinom2);
			theView.setRezultat(polinom3);
		}
	}

	class DerivareListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			String polinom1 = new String();
			String polinom3 = new String();
			polinom1 = theView.getPolinom1();
			polinom3 = theModel.derivarePolinom(polinom1);
			theView.setRezultat(polinom3);
		}
	}

	class IntegrareListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			String polinom1 = new String();
			String polinom3 = new String();
			polinom1 = theView.getPolinom1();
			polinom3 = theModel.integreazaPolinom(polinom1);
			theView.setRezultat(polinom3);
		}
	}

	class ImpartireListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			String polinom1 = new String();
			String polinom2 = new String();
			String polinom3 = new String();
			polinom1 = theView.getPolinom1();
			polinom2 = theView.getPolinom2();
			polinom3 = theModel.impartePolinoame(polinom1, polinom2);
			theView.setRezultat(polinom3);
		}
	}
}
