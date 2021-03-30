
public class Client implements Comparable<Client> {
	private int nrOfClients = 0;
	private int ID;
	private int tarrival;
	private int tservice;

	public Client() {
		
	}
	
	public Client( int tarival, int tservice) {
		this.setID(nrOfClients);
		this.setTarrival(tarival);
		this.setTservice(tservice);
		nrOfClients++;
	}

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public int getTarrival() {
		return tarrival;
	}

	public void setTarrival(int tarrival) {
		this.tarrival = tarrival;
	}

	public int getTservice() {
		return tservice;
	}

	public void setTservice(int tservice) {
		this.tservice = tservice;
	}

    public String toString() {
        return "(" + this.ID + ", " + this.tarrival + ", " + this.tservice + ")";
    }
    
	public int compareTo(Client o) {
		 return this.tarrival - o.tarrival;
	}
	
}
