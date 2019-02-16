import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;
class Cat implements Callable<String>{
	@Override
	public String call() throws Exception{
		for (int x = 0; x < 10; x ++) {
			System.out.println("Thread Executing:" + x);
		}
		return "cycgay";
	}
}

public class CallableTest{
	public static void main(String args[]) throws Exception {
		FutureTask<String> ft = new FutureTask<> (new Cat());
		new Thread(ft).start();
		System.out.println(ft.get());
	}
}