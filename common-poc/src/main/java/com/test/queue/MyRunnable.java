package com.test.queue;

import java.util.Date;
import java.util.concurrent.BlockingQueue;

public class MyRunnable implements Runnable {
	private BlockingQueue<Integer> queue;

	public MyRunnable(BlockingQueue<Integer> queue) {
		this.queue = queue;
	}

	public void run() {
		Date d = new Date();
		long starttime = d.getTime();
		System.err.println(starttime);
		int count = 0;
		while (true) {
			try {
				Integer i = this.queue.poll();
				if (i != null) {
					count++;
				}
				if (count == 100000) {
					Date e = new Date();
					long endtime = e.getTime();
					System.err.println(count);
					System.err.println(endtime);
					System.err.print(endtime - starttime);
					break;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}