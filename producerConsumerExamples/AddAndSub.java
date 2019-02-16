package producerConsumerExamples;
//Add和Sub操作交替出现，最终只允许结果为1，0，-1
//生产者消费者问题延时要给够，防止延时跟不上复位的情况发生
public class AddAndSub {
	public static void main(String[] args) {
		Resource res = new Resource();
		AddOp ap = new AddOp(res);
		SubOp sp = new SubOp(res);
		new Thread(ap, "[add operation]1").start();
		new Thread(ap, "[add operation]2").start();
		new Thread(sp, "[sub operation]1").start();
		new Thread(sp, "[sub operation]2").start();	
	}
}

class AddOp implements Runnable{
	public Resource res;
	public AddOp(Resource res) {
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
				this.res.add();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}

class SubOp implements Runnable{
	public Resource res;
	public SubOp(Resource res) {
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
				this.res.sub();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}

class Resource{
	private int num = 0;
	private boolean flag = true;//标记
	//flag = true表示只能进行加法操作
	//flag = false表示只能进行减法操作
	public synchronized void add() throws InterruptedException {
		if (this.flag == false) {
			super.wait();
		}
		Thread.sleep(100);
		this.num ++;
		System.out.println(Thread.currentThread().getName() + ", num is: " + this.num);
		this.flag = false;
		super.notify();
	}
	public synchronized void sub() throws InterruptedException {
		if (this.flag == true) {
			super.wait();
		}
		Thread.sleep(200);
		this.num --;
		System.out.println(Thread.currentThread().getName() + ", num is: " + this.num);
		this.flag = true;
		super.notify();
	}
}