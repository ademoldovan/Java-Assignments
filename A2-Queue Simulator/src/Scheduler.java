import java.util.ArrayList;
import java.util.List;

public class Scheduler {

	private Strategy strategy;
	private List<Queue> queues;

	public Scheduler(int clients, String strategy1, int max) {
		// for maxNoServers
		// create server object
		// create thread with the objects
		if (strategy1.compareTo("SHORTEST_QUEUE") == 0) {
			strategy = new ConcreteStrategyQueue();
		}
		if (strategy1.compareTo("SHORTEST_TIME") == 0) {
			strategy = new ConcreteStrategyQueue();
		}
		queues = new ArrayList<Queue>();
		//cream cate un thread pentru fiecare coada
		for (int i = 0; i < max; i++) {
			queues.add(new Queue(clients));
			Thread th = new Thread(queues.get(i));
			th.start();
		}
	}

	public float timpulMediuDeAsteptare() {
		int a = 0;
		float t = 0;
		for (Queue q : queues) {
			t = t + q.getTimpMediuDeAsteptare();
			a = a + q.getNumarClienti();
		}
		return t / a;
	}

	public void addClientToQueue(Client c) {
		strategy.addClient(queues, c);
	}

	public Strategy getStrategy() {
		return strategy;
	}

	public void setStrategy(Strategy strategy) {
		this.strategy = strategy;
	}

	public void setQueues(List<Queue> queues) {
		this.queues = queues;
	}

	public List<Queue> getQueues() {
		return queues;
	}

}
