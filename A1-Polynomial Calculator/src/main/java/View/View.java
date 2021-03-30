package View;

import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class View {

	private JFrame frame;
	private JLabel label1;
	private JTextField textfield1;
	private JLabel label2;
	private JTextField textfield2;
	private JLabel label3;
	private JTextField textfield3;
	private JButton buttonPlus;
	private JButton buttonMinus;
	private JButton buttonOri;
	private JButton buttonImpartire;
	private JButton buttonIntegrare;
	private JButton buttonDerivare;
	private JLabel label4;
	private JLabel label5;

	public View() {
		frame = new JFrame("Calculator de Polinoame");
		frame.setSize(650, 380);
		frame.setVisible(true);
		frame.setLayout(null);

		label1 = new JLabel("Introduceti primul polinom:");
		label1.setBounds(20, 10, 200, 40);
		frame.add(label1);

		textfield1 = new JTextField();
		textfield1.setBounds(20, 50, 340, 30);
		frame.add(textfield1);

		label2 = new JLabel("Introduceti al 2-lea polinom:");
		label2.setBounds(20, 80, 200, 40);
		frame.add(label2);

		textfield2 = new JTextField();
		textfield2.setBounds(20, 120, 340, 30);
		frame.add(textfield2);

		label3 = new JLabel("Rezultat:");
		label3.setBounds(20, 150, 200, 40);
		frame.add(label3);

		textfield3 = new JTextField();
		textfield3.setBounds(20, 190, 340, 30);
		frame.add(textfield3);

		buttonPlus = new JButton("+");
		buttonPlus.setBounds(400, 50, 100, 30);
		frame.add(buttonPlus);

		buttonMinus = new JButton("-");
		buttonMinus.setBounds(510, 50, 100, 30);
		frame.add(buttonMinus);

		buttonOri = new JButton("*");
		buttonOri.setBounds(400, 100, 100, 30);
		frame.add(buttonOri);

		buttonImpartire = new JButton("/");
		buttonImpartire.setBounds(510, 100, 100, 30);
		frame.add(buttonImpartire);

		buttonIntegrare = new JButton("Integrare");
		buttonIntegrare.setBounds(400, 150, 100, 30);
		frame.add(buttonIntegrare);

		buttonDerivare = new JButton("Derivare");
		buttonDerivare.setBounds(510, 150, 100, 30);
		frame.add(buttonDerivare);

		label4 = new JLabel("*Polinoamele se vor introduce avand structura asemanatoare");
		label4.setBounds(20, 225, 350, 40);
		frame.add(label4);

		label4 = new JLabel("cu urmatoarele exemple:");
		label4.setBounds(20, 240, 350, 40);
		frame.add(label4);

		label5 = new JLabel("3x^3-2x^2+7x-10   sau   -x^4-2x^2+7");
		label5.setBounds(20, 265, 350, 40);
		frame.add(label5);

		frame.repaint();
	}

	public String getPolinom1() {
		return textfield1.getText();
	}

	public String getPolinom2() {
		return textfield2.getText();
	}

	public void setRezultat(String s1) {
		textfield3.setText(s1);
	}

	public void addPlusButtonActionListener(ActionListener actionListener) {
		buttonPlus.addActionListener(actionListener);
	}

	public void addMinusButtonActionListener(ActionListener actionListener) {
		buttonMinus.addActionListener(actionListener);
	}

	public void addOriButtonActionListener(ActionListener actionListener) {
		buttonOri.addActionListener(actionListener);
	}

	public void addImpartireButtonActionListener(ActionListener actionListener) {
		buttonImpartire.addActionListener(actionListener);
	}

	public void addDerivareButtonActionListener(ActionListener actionListener) {
		buttonDerivare.addActionListener(actionListener);
	}

	public void addIntegrareButtonActionListener(ActionListener actionListener) {
		buttonIntegrare.addActionListener(actionListener);
	}

}
