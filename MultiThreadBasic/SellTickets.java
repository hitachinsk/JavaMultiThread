//use volatile and synchronized
public class SellTickets {
	public static void main(String[] args) {
		Tickets tk = new Tickets();
		Thread ta = new Thread(tk, "Ticket seller 1");
		Thread tb = new Thread(tk, "Ticket seller 2");
		Thread tc = new Thread(tk, "Ticket seller 3");
		ta.start();
		tb.start();
		tc.start();
	}

}

class Tickets implements Runnable {
	public volatile int tickets = 100;
	@Override
	public void run() {
		while(this.tickets > 1) {
			synchronized(this) {
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println(Thread.currentThread().getName() + " sell NO." + (this.tickets--));
			}
		}
	}
}