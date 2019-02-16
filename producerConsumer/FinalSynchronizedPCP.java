package producerConsumer;
//pcp refers to "Producer consumer Problem"

public class FinalSynchronizedPCP {
	public static void main(String[] args) {
		Message2 msg = new Message2();
		new Thread(new Producer2(msg)).start();
		new Thread(new Consumer2(msg)).start();
	}
}

class Producer2 implements Runnable {
	public Message2 msg;
	public Producer2 (Message2 msg){
		this.msg = msg;
	}
	@Override
	public void run () {
		for (int x = 0; x < 100; x ++) {
			if ((x % 2) == 0) {
				this.msg.set("Cola", "2.99$");
			}else {
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				this.msg.set("Spring", "1.99$");
			}
		}
	}
}

class Consumer2 implements Runnable {
	public Message2 msg;
	public Consumer2(Message2 msg) {
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

class Message2 {
	public String name;
	public String title;
	public boolean flag = true; //协调生产与消费的指示灯
	//flag = true的时候表示可以生产，禁止消费
	//flag = false的时候表示可以消费，但是禁止生产
	synchronized public void set(String name, String title) {
		if (this.flag == false) {
			try {
				super.wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		this.name = name;
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.title = title;
		this.flag = false;
		super.notify();
	}
	synchronized public String get() {
		if (this.flag == true) {
			try {
				super.wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		try {
			Thread.sleep(10);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			return this.name + "  -  " + this.title;
		}finally {
			this.flag = true;
			super.notify();
		}
	}
}
