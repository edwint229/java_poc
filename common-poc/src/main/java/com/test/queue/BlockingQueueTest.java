package com.test.queue;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class BlockingQueueTest {
	public static void main(String[] args) {
		BlockingQueue<Integer> queue = new LinkedBlockingQueue<Integer>();

		// consumer
		java.lang.Runnable r = new MyRunnable(queue);
		Thread t = new Thread(r);
		t.start();

		// publish
		while (true) {
			try {
				while (true) {
					for (int i = 0; i < 10000; i++) {
						queue.offer(i);
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
