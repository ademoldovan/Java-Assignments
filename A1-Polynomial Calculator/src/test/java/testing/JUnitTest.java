package testing;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import Model.Model;

public class JUnitTest {

	private static int nrTesteExecutate = 0;
	private static int nrTesteCuSucces = 0;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		System.out.println("O singura data inaintea executiei setului de teste din clasa!");
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		System.out.println("O singura data dupa terminarea executiei setului de teste din clasa!");
				System.out.println("S-au executat " + nrTesteExecutate + " teste din care "+ nrTesteCuSucces + " au avut succes!");
	}

	@Before
	public void setUp() throws Exception {
		System.out.println("Incepe un nou test!");
		nrTesteExecutate++;
	}

	@After
	public void tearDown() throws Exception {
		System.out.println("S-a terminat testul curent!");
	}

	@Test
	public void testAdunare() {
		Model test = new Model();
		String output = test.adunaPolinoame("2x+1", "6x+2");
		assertEquals("8x+3",output);
		nrTesteCuSucces++;
	}
	@Test
	public void testScadereNrEgale() {
		Model test = new Model();
		String output = test.scadePolinoame("2x+1", "2x+1");
		assertEquals("0",output);
		nrTesteCuSucces++;
	}
	@Test
	public void testScadere() {
		Model test = new Model();
		String output = test.scadePolinoame("6x^2+x+1", "6x+2");
		assertEquals("6x^2-6x-1",output);
		nrTesteCuSucces++;
	}
	@Test
	public void testInmultire() {
		Model test = new Model();
		String output = test.inmultestePolinoame("6x^2+x+1", "6x+2");
		assertEquals("36x^3+12x^2+6x+2",output);
		nrTesteCuSucces++;
	}
	@Test
	public void testImpartire() {
		Model test = new Model();
		String output = test.impartePolinoame("6x^3+4x^2-5x+5", "-2x^2-1");
		assertEquals("C:-3x-2  ;  R: -8x+3",output);
		nrTesteCuSucces++;
	}
	@Test
	public void testDerivare() {
		Model test = new Model();
		String output = test.derivarePolinom("-2x^2-1");
		assertEquals("-4x",output);
		nrTesteCuSucces++;
	}
	@Test
	public void testIntegrare() {
		Model test = new Model();
		String output = test.integreazaPolinom("x+2");
		assertEquals("0.5x^2+2x",output);
		nrTesteCuSucces++;
	}

}
