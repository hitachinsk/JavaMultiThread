package producerConsumer;
//pcp refers to "Producer consumer Problem"

public class SynchronizedPCP {
	public static void main(String[] args) {
		Message1 msg = new Message1();
		new Thread(new Producer1(msg)).start();
		new Thread(new Consumer1(msg)).start();
	}
}

class Producer1 implements Runnable {
	public Message1 msg;
	public Producer1 (Message1 msg){
		this.msg = msg;
	}
	@Override
	public void run () {
		for (int x = 0; x < 100; x ++) {
			if ((x % 2) == 0) {
				this.msg.set("cyc", "gay");
			}else {
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				this.msg.set("Lisa", "cute");
			}
		}
	}
}

class Consumer1 implements Runnable {
	public Message1 msg;
	public Consumer1(Message1 msg) {
		this.msg = msg;
	}
	@Override
	public void run() {
		for (int x = 0; x < 100; x ++) {
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println(this.msg.get());
		}
	}
}

class Message1 {
	public String name;
	public String title;
	synchronized public void set(String name, String title) {
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.name = name;
		this.title = title;
	}
	synchronized public String get() {
		try {
			Thread.sleep(10);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return this.name + "  -  " + this.title;
	}
}
