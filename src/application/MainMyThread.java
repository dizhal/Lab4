package application;

public class MainMyThread implements Runnable{

	private MyThread myThread;
	private String equation;
	
	public MainMyThread(MyThread myThread,String equation){
		this.myThread=myThread;
		this.equation=equation;
	}
	
	public void run() {				
		myThread.calculate();
	}

	public MyThread getMyThread() {
		return myThread;
	}
	
	public String getEquation() {
		return equation;
	}
}
