public class ThreadExample extends Thread{
	private String title;
	public ThreadExample(String title) {
		this.title = title;
	}
	@Override
	public void run() {
		for (int x = 0; x < 10; x ++) {
			System.out.println(this.title + ":" + x);
		}
	}
}