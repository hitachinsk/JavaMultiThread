package producerConsumerExamples;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class GetChance {
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		Chance c = new Chance();
		FutureTask<String> ft1 = new FutureTask<String>(c);
		FutureTask<String> ft2 = new FutureTask<String>(c);
		FutureTask<String> ft3 = new FutureTask<String>(c);
		new Thread(ft1, "Competitor-A").start();
		new Thread(ft2, "Competitor-B").start();
		new Thread(ft3, "Competitor-C").start();
		System.out.println(ft1.get());
		System.out.println(ft2.get());
		System.out.println(ft3.get());	
	}
}

class Chance implements Callable<String>{
	public static boolean flag = false;
	@Override
	public String call() throws Exception {
		// TODO Auto-generated method stub
		synchronized(this) {
			if (flag == false) {
				Thread.sleep(100);
				flag = true;
				return Thread.currentThread().getName() + " get chance to answer";
			} else {
				Thread.sleep(100);
				return Thread.currentThread().getName() + " failed to get a chance";
			}
		}
	}
}
