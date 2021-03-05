package single;

/*我来给你说一下吧！uniqueInstance = new Singleton() 不是原子操作，这段代码可以简单分为下面三步执行：
        为 uniqueInstance 分配内存空间;
        初始化 uniqueInstance;
        将 uniqueInstance 指向分配的内存地址
        由于但是由于 JVM 具有指令重排的特性，执行顺序有可能变成 1->3->2。指令重排在单线程环境下不会出现问题，但是在多线程环境下会导致一个线程获得还没有初始化的实例。例如，线程 a 执行了 1 和 3，此时 线程 b 调用 getUniqueInstance() 后发现 uniqueInstance 不为空，因此返回 uniqueInstance，但此时 uniqueInstance 还未被初始化，所以就会导致空指针异常。*/
public class Singleton {


        private static Singleton uniqueInstance;//增加volatile

        private Singleton() {
        }

        public static Singleton getUniqueInstance() {
            //先判断对象是否已经实例过，没有实例化过才进入加锁代码
            if (uniqueInstance == null) {
                //类对象加锁
                synchronized (Singleton.class) {
                    //对象为空才去创建（懒加载）
                    if (uniqueInstance == null) {
                        uniqueInstance = new Singleton();//非原子操作。注意！！！
                    }
                }
            }
            return uniqueInstance;
        }


}
