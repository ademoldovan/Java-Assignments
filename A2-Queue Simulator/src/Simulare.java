
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Simulare implements Runnable {

	private int simulationInterval;
	private List<Client> clients;
	private Scheduler scheduler;
	private int N;
	static String out1 = "out-test-1.txt";
	static String out2 = "out-test-2.txt";
	static String out3 = "out-test-3.txt";

	public Simulare(int N, int Q, int minArrival, int maxArrival, int minService, int maxService,
			int simulationInterval, String strategyType) {
		// initialize queue and waitingPeriod
		this.setN(N);
		this.simulationInterval = simulationInterval;
		// se creeaza un nou scheduler
		scheduler = new Scheduler(N, strategyType, Q);
		clients = new ArrayList<Client>();
		Random r = new Random();
		// cream clienti random
		for (int i = 1; i <= N; i++) {
			Client client = new Client(r.nextInt(maxArrival - minArrival) + minArrival,
					r.nextInt(maxService - minService) + minService);
			clients.add(client);
		}
		Collections.sort(clients);
	}

	@Override
	public void run() {
		try {
			File outFile = new File(out1);
			outFile.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			FileWriter output = new FileWriter(out1);
			int currentTime = 0;
			while (currentTime < simulationInterval) {
				while (clients.size() > 0 && clients.get(0).getTarrival() == currentTime) {
					scheduler.addClientToQueue(clients.get(0));
					clients.remove(0);
				}
				output.write("Time: " + currentTime + "\nWaiting Clients:");
				for (Client c : clients) {
					output.write(c.toString());
				}
				output.write("\n");
				for (int i = 0; i < scheduler.getQueues().size(); i++) {
					output.write("Queue " + i + ": ");
					if (scheduler.getQueues().get(i).getQueue().size() > 0) {
						output.write(scheduler.getQueues().get(i).toString());
						if (scheduler.getQueues().get(i).getState() == Thread.State.WAITING) {
							scheduler.getQueues().get(i).getQueue().notify();
						}
						if (scheduler.getQueues().get(i).getTimpDeAsteptare().intValue() > 0) {
							int t = scheduler.getQueues().get(i).getTimpDeAsteptare().intValue();
							scheduler.getQueues().get(i).setTimpDeAsteptare(t - 1);
						}
						if (scheduler.getQueues().get(i).getQueue().peek().getTservice() > 0) {
							int t = scheduler.getQueues().get(i).getQueue().peek().getTservice();
							scheduler.getQueues().get(i).getQueue().peek().setTservice(t - 1);
						}
					} else {
						output.write("closed");
					}
					output.write("\n");
				}
				output.write("\n");
				currentTime++;
				Thread.sleep(90);
			}
			for (Queue i : scheduler.getQueues()) {
				i.close();
			}
			output.write("Timp mediu de asteptare: " + scheduler.timpulMediuDeAsteptare());
			output.close();
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		}
	}

	public int maxTimeArrival() {
		Client c = new Client(0, 0);
		for (Client i : this.clients) {
			if (i.getTarrival() > c.getTarrival()) {
				c = i;
			}
		}
		return c.getTarrival();
	}

	public int getN() {
		return N;
	}

	public void setN(int n) {
		N = n;
	}

}
