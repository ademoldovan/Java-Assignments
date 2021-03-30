import java.io.IOException;

public class MainClass {

	static String test1 = "in-test-1.txt";
	static String test2 = "in-test-2.txt";
	static String test3 = "in-test-3.txt";

	public static void main(String[] args) throws IOException {
		ReadFromFile read = new ReadFromFile(test1);
		String strategyType = "SHORTEST_TIME"; // sau SHORTEST_QUEUE
		Simulare sm = new Simulare(read.getN(), read.getQ(), read.getMinArrival(), read.getMaxArrival(),
				read.getMinService(), read.getMaxService(), read.getSimulationInterval(), strategyType);
		Thread start = new Thread(sm);
		start.start();
	}
   
}
