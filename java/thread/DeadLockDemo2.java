package thread;
/*学过操作系统的朋友应该都知道产生死锁必须具备以下四个条件：
        互斥条件 ：该资源任意一个时刻只由一个线程占用。
        请求与保持条件 ：一个进程因请求资源而阻塞时，对已获得的资源保持不放。
        不剥夺条件 :线程已获得的资源在末使用完之前不能被其他线程强行剥夺，只有自己使用完毕后才释放资源。
        循环等待条件 :若干进程之间形成一种头尾相接的循环等待资源关系。*/

//引发
/*面试官 ：那你说说 synchronized 关键字和 volatile 关键字的区别吧！
        🙋 我 ：synchronized 关键字和 volatile 关键字是两个互补的存在，而不是对立的存在！
volatile 关键字是线程同步的轻量级实现，所以volatile 性能肯定比 synchronized 关键字要好。但是volatile 关键字只能用于变量而 synchronized 关键字可以修饰方法以及代码块。
volatile 关键字能保证数据的可见性，但不能保证数据的原子性。synchronized 关键字两者都能保证。
volatile 关键字主要用于解决变量在多个线程之间的可见性，而 synchronized 关键字解决的是多个线程之间访问资源的同步性。*/
public class DeadLockDemo2 {
    private static Object resource1 = new Object();//资源 1
    private static Object resource2 = new Object();//资源 2

    public static void main(String[] args) {
        new Thread(() -> {
            synchronized (resource1) {
                System.out.println(Thread.currentThread() + "get resource1");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread() + "waiting get resource2");
                synchronized (resource2) {
                    System.out.println(Thread.currentThread() + "get resource2");
                }
            }
        }, "线程 1").start();

        new Thread(() -> {
            synchronized (resource2) {
                System.out.println(Thread.currentThread() + "get resource2");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread() + "waiting get resource1");
                synchronized (resource1) {
                    System.out.println(Thread.currentThread() + "get resource1");
                }
            }
        }, "线程 2").start();
    }
}
