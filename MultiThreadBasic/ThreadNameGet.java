import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
class Tng implements Callable<Integer>{
	@Override
	public Integer call() throws Exception{
		int temp = 0;
		for (int x = 0; x < 1000; x ++) {
			System.out.println(Thread.currentThread().getName() + x);
			temp += x;
		}
		return temp;
	}
}
public class ThreadNameGet {

	public static void main(String[] args) throws InterruptedException, ExecutionException {
		System.out.println("Step 1 is created.");
		FutureTask<Integer> ft = new FutureTask<>(new Tng());
		new Thread(ft).start();
		System.out.println(ft.get());
		System.out.println("Step 2 goes on");
	}

}
