
public class ThreadDemo {

	public static void main(String[] args) {
		RunnableInterface q = new RunnableInterface(8);
		new Thread(q).start();
		new Thread(q).start();
	}

}
