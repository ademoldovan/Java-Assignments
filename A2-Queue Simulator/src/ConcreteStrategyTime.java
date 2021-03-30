import java.util.List;

public class ConcreteStrategyTime implements Strategy {

	@Override
	public void addClient(List<Queue> queue, Client client) {
		int minT = 10000;
		Queue Q = null;
		for (Queue q : queue) {
			if (q.getTimpDeAsteptare().intValue() < minT) {
				minT = q.getTimpDeAsteptare().intValue();
				Q = q;
			}
		}
		if (Q != null) {
			Q.addClient(client);
		}
	}

}
