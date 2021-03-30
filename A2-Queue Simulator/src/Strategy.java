import java.util.List;

public interface Strategy {

	public void addClient(List<Queue> queue, Client client);
}
