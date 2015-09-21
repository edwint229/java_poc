package com.test.lock;

import java.util.Random;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock.ReadLock;
import java.util.concurrent.locks.ReentrantReadWriteLock.WriteLock;

/**
 * 读锁：可并发 <br/>
 * 写锁：排他性（读锁）
 * 
 * @author edwin
 * 
 */
public class ReadWriteLockTest {
	private static int data = 0;
	private static ReentrantReadWriteLock rwLock = new ReentrantReadWriteLock();
	private static ReadLock rLock = rwLock.readLock();
	private static WriteLock wLock = rwLock.writeLock();

	public static void read() {
		String threadName = Thread.currentThread().getName();
		System.out.println("\n" + threadName + " wait for ReadLock");
		rLock.lock();
		try {
			System.out.println(threadName + " read data:" + data);
			System.out.println("wait for a while");
			long time = System.currentTimeMillis();
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			long duration = System.currentTimeMillis() - time;
			System.out.println("\n I sleep for:" + duration + "(millis)");
		} finally {
			System.out.println(threadName + " release  read lock");
			rLock.unlock();
		}

	}

	public static void write() {
		String threadName = Thread.currentThread().getName();
		System.out.println("======== " + threadName
				+ ",wait for get write lock");
		wLock.lock();
		try {
			System.out.println("=========== get write lock!");
			Random rand = new Random();
			data = rand.nextInt(10000);
			System.out.println("=========== finish write, release lock");
		} finally {
			wLock.unlock();
		}
	}

	public static void main(String[] args) {
		Thread[] threads = new Thread[4];
		for (int i = 0; i < 3; ++i) {
			threads[i] = new Thread() {
				public void run() {
					ReadWriteLockTest.read();
				}
			};
			threads[i].start();
		}
		threads[3] = new Thread() {
			public void run() {
				ReadWriteLockTest.write();
			}
		};
		threads[3].start();

		for (int i = 0; i < threads.length; ++i) {
			try {
				threads[i].join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println("parent threads exit!");
	}
}
