import java.lang.Thread.State;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;
import static java.lang.Thread.*;

public class Queue implements Runnable {

	private AtomicInteger numarClienti;
	private AtomicInteger timpDeAsteptare;
	private AtomicInteger timpMediuDeAsteptare;
	private BlockingQueue<Client> queue;
	private boolean running = true;

	public Queue() {

	}

	public Queue(int clientsNumber) {
		queue = new ArrayBlockingQueue<Client>(clientsNumber);
		timpDeAsteptare = new AtomicInteger(0);
		numarClienti = new AtomicInteger(0);
		timpMediuDeAsteptare = new AtomicInteger(0);
	}

	public void run() {
		while (running == true) {
			if (queue.isEmpty() == true) {
				try {
					synchronized (this) {
						wait();
					}
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			} else {
				Client crt = queue.peek();
				try {
					sleep(100*crt.getTservice());
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				queue.remove();
			}
		}
	}

	//functie care adauga un client la coada
	public void addClient(Client c) {
		queue.add(c);
		synchronized (this) {
			notify();
		}
		timpMediuDeAsteptare.addAndGet(timpDeAsteptare.intValue());
		timpDeAsteptare.addAndGet(c.getTservice());
		numarClienti.incrementAndGet();
	}

	public String toString() {
		String s = "";
		for (Client c : queue) {
			s = s +  c.toString();
		}
		return s;
	}

	//functie care seteaza variabila running pe false pentru a se putea opri executia threadului pentru o anumita coada
	public void close() {
		this.running = false;
		synchronized (this) {
			notify();
		}
	}

	//returneaza o referinta catre thread-ul curent care ruleaza
	public State getState() {
		return Thread.currentThread().getState();
	}
	
	public void setTimpDeAsteptare(int waitingTime) {
		this.timpDeAsteptare = new AtomicInteger(waitingTime);
	}

	public AtomicInteger getTimpDeAsteptare() {
		return timpDeAsteptare;
	}

	public java.util.Queue<Client> getQueue() {
		return queue;
	}

	public float getTimpMediuDeAsteptare() {
		return timpMediuDeAsteptare.floatValue();
	}

	public int getNumarClienti() {
		return numarClienti.intValue();
	}
}
