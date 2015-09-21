package com.test.lock;

import java.util.concurrent.atomic.AtomicInteger;

import sun.misc.Unsafe;

/**
 * <b>CAS</b>: Compare and Swap, 翻译成比较并交换。
 * java.util.concurrent包中借助CAS实现了区别于synchronized同步锁的一种乐观锁。<br/>
 * CAS有3个操作数，内存值V，旧的预期值A，要修改的新值B。当且仅当预期值A和内存值V相同时，将内存值V修改为B，否则什么都不做。<br/>
 * <b>在没有锁的机制下可能需要借助volatile原语，保证线程间的数据是可见的（共享的）。</b> <br/>
 * CPU的锁有如下3种: <br/>
 * (1) 处理器自动保证基本内存操作的原子性,
 * 首先处理器会自动保证基本的内存操作的原子性。处理器保证从系统内存当中读取或者写入一个字节是原子的，意思是当一个处理器读取一个字节时，
 * 其他处理器不能访问这个字节的内存地址。<br/>
 * (2) 使用总线锁保证原子性,
 * 第一个机制是通过总线锁保证原子性。如果多个处理器同时对共享变量进行读改写（i++就是经典的读改写操作）操作，那么共享变量就会被多个处理器同时进行操作<br/>
 * (3) 使用缓存锁保证原子性,第二个机制是通过缓存锁定保证原子性。在同一时刻我们只需保证对某个内存地址的操作是原子性即可，
 * 但总线锁定把CPU和内存之间通信锁住了，这使得锁定期间，
 * 其他处理器不能操作其他内存地址的数据，所以总线锁定的开销比较大，最近的处理器在某些场合下使用缓存锁定代替总线锁定来进行优化。 <br/>
 * </br>CAS缺点</br>
 * CAS虽然很高效的解决原子操作，但是CAS仍然存在三大问题。ABA问题，循环时间长开销大和只能保证一个共享变量的原子操作</br> 1.
 * ABA问题。因为CAS需要在操作值的时候检查下值有没有发生变化，如果没有发生变化则更新，但是如果一个值原来是A，变成了B，又变成了A，
 * 那么使用CAS进行检查时会发现它的值没有发生变化
 * ，但是实际上却变化了。ABA问题的解决思路就是使用版本号。在变量前面追加上版本号，每次变量更新的时候把版本号加一，那么A－B－A
 * 就会变成1A-2B－3A。</br>
 * 从Java1.5开始JDK的atomic包里提供了一个类AtomicStampedReference来解决ABA问题。
 * 这个类的compareAndSet方法作用是首先检查当前引用是否等于预期引用
 * ，并且当前标志是否等于预期标志，如果全部相等，则以原子方式将该引用和该标志的值设置为给定的更新值。 <br/>
 * 由于java的CAS同时具有 volatile 读和volatile写的内存语义，因此Java线程之间的通信现在有了下面四种方式：<br/>
 * A线程写volatile变量，随后B线程读这个volatile变量。<br/>
 * A线程写volatile变量，随后B线程用CAS更新这个volatile变量。<br/>
 * A线程用CAS更新一个volatile变量，随后B线程用CAS更新这个volatile变量。<br/>
 * A线程用CAS更新一个volatile变量，随后B线程读这个volatile变量。<br/>
 * <br/>
 * 对于volatile修饰的变量，jvm虚拟机只是保证从主内存加载到线程工作内存的值是最新的<br/>
 * 
 * @author edwin
 * 
 */
public class CompareAndSwapTest {
	private static final Unsafe unsafe = Unsafe.getUnsafe();
	private volatile int value;
	private static final long valueOffset;

	static {
		try {
			valueOffset = unsafe.objectFieldOffset(AtomicInteger.class
					.getDeclaredField("value"));
		} catch (Exception ex) {
			throw new Error(ex);
		}
	}

	public boolean compareAndSet(int expect, int update) {
		return unsafe.compareAndSwapInt(this, valueOffset, expect, update);
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public static void main(String[] args) {

	}

}
