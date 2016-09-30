package com.program.thread;

public class ImplClass {

	public static void main(String[] args) {
Runnable even = new ThreadDemo();
Thread ev = new Thread(even);

Runnable odd = new ThreadDemo2();
Thread od = new Thread(odd);


synchronized (ev) {
	System.out.println("Waiting for odd to complete");
	try {
		if(!od.isAlive())
		{
		ev.wait();
		}
		od.start();
	
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
}
ev.start();
od.start();
System.out.println(ev.isAlive());
System.out.println(od.isAlive());
/*Thread printEven = new Thread(new Runnable() {
	
	@Override
	public void run() {
		System.out.println("Even");
		
	}
});


Thread printOdd = new Thread(new Runnable() {
	
	@Override
	public void run() {
		System.out.println("Odd");
		
	}
});


printEven.start();
printOdd.start();
*/	}

}
