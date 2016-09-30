package com.program.thread;

public class ThreadDemo implements Runnable{

	@Override
	public void run() {
	System.out.println("For Evn");
	}

}


class ThreadDemo2 implements Runnable{

	@Override
	public void run() {
		System.out.println("For Odd");
		notify();
	}
	
}
