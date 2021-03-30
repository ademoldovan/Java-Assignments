import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class ReadFromFile {

	private int N;
	private int Q;
	private int simulationInterval;
	private int minArrival;
	private int maxArrival;
	private int minService;
	private int maxService;
	static String out1 = "out-test-1.txt";
	static String out2 = "out-test-2.txt";
	static String out3 = "out-test-3.txt";

	public ReadFromFile(String fisier) throws IOException {
		// citim din fisier datele de intrare
		Scanner in = new Scanner(System.in);
		// String filename = fisier;
		File f = new File(fisier);
		String fullPath = new String();
		if (f.exists()) {
			fullPath = f.getAbsolutePath();
		}
		File file = new File(fullPath);
		Scanner fileReader = new Scanner(file);
		while (fileReader.hasNextLine()) {
			this.N = fileReader.nextInt();
			this.Q = fileReader.nextInt();
			this.simulationInterval = fileReader.nextInt();
			fileReader.nextLine();
			String t = fileReader.nextLine();
			String[] t1 = t.split(",");
			this.minArrival = Integer.parseInt(t1[0]);
			this.maxArrival = Integer.parseInt(t1[1]);
			String t2 = fileReader.nextLine();
			String[] t12 = t2.split(",");
			this.minService = Integer.parseInt(t12[0]);
			this.maxService = Integer.parseInt(t12[1]);
//          System.out.println(this.N);
//          System.out.println(this.Q);
//          System.out.println(this.simulationInterval);
//        	System.out.println(this.minArrival);
//			System.out.println(this.maxArrival);
//			System.out.println(this.minService);
//			System.out.println(this.maxService);
			break;
		}
		fileReader.close();
		in.close();
	}
	
	//functia care scrie in fisier
	public static void write(ArrayList<String> args) {
		try {
			File outFile = new File(out1);
			outFile.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			FileWriter output = new FileWriter(out1);
			for (String s : args) {
				output.write(s);
			}
			output.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public int getQ() {
		return Q;
	}

	public void setQ(int q) {
		Q = q;
	}

	public int getN() {
		return N;
	}

	public void setN(int n) {
		N = n;
	}

	public int getSimulationInterval() {
		return simulationInterval;
	}

	public void setSimulationInterval(int simulationInterval) {
		this.simulationInterval = simulationInterval;
	}

	public int getMinArrival() {
		return minArrival;
	}

	public void setMinArrival(int minArrival) {
		this.minArrival = minArrival;
	}

	public int getMaxArrival() {
		return maxArrival;
	}

	public void setMaxArrival(int maxArrival) {
		this.maxArrival = maxArrival;
	}

	public int getMinService() {
		return minService;
	}

	public void setMinService(int minService) {
		this.minService = minService;
	}

	public int getMaxService() {
		return maxService;
	}

	public void setMaxService(int maxService) {
		this.maxService = maxService;
	}

}
