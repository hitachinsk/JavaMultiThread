package producerConsumer;

public class Pcp {
	public static void main(String[] args) {
		Message msg = new Message();
		new Thread(new Producer(msg)).start();
		new Thread(new Consumer(msg)).start();
	}
}

class Producer implements Runnable {
	public Message msg;
	public Producer (Message msg){
		this.msg = msg;
	}
	@Override
	public void run () {
		for (int x = 0; x < 100; x ++) {
			if ((x % 2) == 0) {
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				this.msg.setName("cyc");
				this.msg.setTitle("gay");
			}else {
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				this.msg.setName("Lisa");
				this.msg.setTitle("Cute");
			}
		}
	}
}

class Consumer implements Runnable {
	public Message msg;
	public Consumer(Message msg) {
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
			System.out.println(this.msg.getName() + "--" + this.msg.getTitle());
		}
	}
}

class Message {
	public String name;
	public String title;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
}