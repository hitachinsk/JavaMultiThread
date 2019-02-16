package producerConsumerExamples;
//生产电脑问题，生产一个拿走一个，统计电脑个数
//生产者消费者问题延时要给够，防止延时跟不上复位的情况发生
public class ProducerComputer {
	public static void main(String[] args) {
		Resource1 res = new Resource1();
		Producer pd = new Producer(res);
		Consumer cm = new Consumer(res);
		new Thread(pd, "[Producer]").start();
		new Thread(cm, "[Consumer]").start();	
	}
}

class Producer implements Runnable{
	public Resource1 res;
	public Producer(Resource1 res) {
		this.res = res;
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		for (int x = 0; x < 50; x ++) {
			try {
				Thread.sleep(100);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			try {
				this.res.produce();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}

class Consumer implements Runnable{
	public Resource1 res;
	public Consumer(Resource1 res) {
		this.res = res;
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		for (int x = 0; x < 50; x ++) {
			try {
				Thread.sleep(100);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			try {
				this.res.get();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
class Resource1{
	private static int num = 0;
	public Computer cpt;
	public synchronized void produce() throws InterruptedException {
		if (this.cpt != null) {
			super.wait();
		}
		num ++;
		Thread.sleep(100);
		this.cpt = new Computer(2.3, "Lenovo");
		System.out.println(Thread.currentThread().getName() + "-Produce:" + this.cpt);
		System.out.println("The total num of computer is: " + num);
		super.notifyAll();
	}
	public synchronized void get() throws InterruptedException {
		if (this.cpt == null) {
			super.wait();
		}
		Thread.sleep(50);
		System.out.println(Thread.currentThread().getName() + "-Get:" + this.cpt);
		this.cpt = null;
		super.notifyAll();
	}
}
class Computer{
	public double price;
	public String brand;
	public Computer(double price, String brand) {
		this.price = price;
		this.brand = brand;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public String toString() {
		return "The computer\'s brand: " + this.brand + ", price: " + this.price;
	}
}