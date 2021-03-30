import java.util.List;

public class ConcreteStrategyQueue implements Strategy{

	@Override
	public void addClient(List<Queue> queue, Client client) {
		int minSize = 100000;
        Queue Q = null;
        for (Queue q : queue) {
            if (q.getQueue().size() < minSize) {
                minSize = q.getQueue().size();
                Q = q;
            }
        }
        if (Q != null) {
            Q.addClient(client);
        }
		
	}

}
