
public class RunnableInterface implements Runnable{
	private int ticket;
	public RunnableInterface(int ticket) {
		this.ticket = ticket;
	}
	@Override
	public void run() {
		for (int x = 0; x < 100; x ++) {
			if (this.ticket > 0) {
				System.out.println("ticket" + (this.ticket -- ));
			} else {
				break;
			}
		}
	}
}
