package designpatterns;

/**
 * @Author My
 * @Date 2019/10/24
 **/

/***
 * 懒汉式--线程不安全
 * 懒汉式--好处：没有用到该类，就不会实例化，节约资源
 */
public class LazySingleton {

    private static LazySingleton singleton;

    private LazySingleton(){

    }

    /**
     * 以下实现中，私有静态变量 singleton 被延迟实例化，这样做的好处是，
     * 如果没有用到该类，那么就不会实例化 uniqueInstance，从而节约资源
     * 在多线程环境下，singleton会被实例化多次。
     * @return
     */
    public LazySingleton getSingleton(){
        if(singleton == null){
            singleton = new LazySingleton();
        }
        return singleton;
    }
}

/**
 * 饿汉式--单例
 * 采取直接实例化 hungerSingleton 的方式就不会产生线程不安全问题。
 * 不足：直接实例化的方式也丢失了延迟实例化带来的节约资源的好处。
 */
class HungerSingleton{

    private static HungerSingleton hungerSingleton = new HungerSingleton();

    private HungerSingleton(){

    }

    public static HungerSingleton getHungerSingleton(){
        return hungerSingleton;
    }
}

/**
 * 懒汉式--低性能--线程安全
 *
 */
class SaleLowPerformanceLazySingleton{

    private static SaleLowPerformanceLazySingleton saleLowPerformanceLazySingleton;

    private SaleLowPerformanceLazySingleton(){

    }

    public static synchronized SaleLowPerformanceLazySingleton getSaleLowPerformanceLazySingleton(){
        if(null == saleLowPerformanceLazySingleton){
            saleLowPerformanceLazySingleton = new SaleLowPerformanceLazySingleton();
        }
        return saleLowPerformanceLazySingleton;
    }

}

/**
 * 懒汉式--高性能--线程安全
 */
class SaleHighPerformanceLazySingleton{

    private static SaleHighPerformanceLazySingleton saleHighPerformanceLazySingleton;

    private SaleHighPerformanceLazySingleton(){

    }

    public static SaleHighPerformanceLazySingleton getSaleLowPerformanceLazySingleton(){
        if(null == saleHighPerformanceLazySingleton){
            synchronized (SaleHighPerformanceLazySingleton.class){
                if(null == saleHighPerformanceLazySingleton){
                    saleHighPerformanceLazySingleton = new SaleHighPerformanceLazySingleton();
                }
            }
        }
        return saleHighPerformanceLazySingleton;
    }
}

/**
 * 匿名内部类实现单例
 * 好处:当StaticInnerClassSingleton类被加载时，SingletonHolder并没有被加载
 * 只有当调用 getUniqueInstance() 方法从而触发 SingletonHolder.INSTANCE 时 SingletonHolder 才会被加载，
 * 此时初始化 INSTANCE 实例，并且 JVM 能确保 INSTANCE 只被实例化一次。
 * 这种方式不仅具有延迟初始化的好处，而且由 JVM 提供了对线程安全的支持。
 */
class StaticInnerClassSingleton{

    private StaticInnerClassSingleton(){

    }

    public static class SingletonHolder{
        private final static StaticInnerClassSingleton staticInnerClassSingleton = new StaticInnerClassSingleton();
    }

    public static StaticInnerClassSingleton getStaticInnerClassSingleton(){
        return SingletonHolder.staticInnerClassSingleton;
    }
}

/**
 * 枚举类实现单例
 */
enum EnumerateSingleton{

    INSTANCE;

    private String objName;


    public String getObjName() {
        return objName;
    }


    public void setObjName(String objName) {
        this.objName = objName;
    }

    public static void main(String[] args) {

        // 单例测试
        EnumerateSingleton firstSingleton = EnumerateSingleton.INSTANCE;
        firstSingleton.setObjName("firstName");
        System.out.println(firstSingleton.getObjName());
        EnumerateSingleton secondSingleton = EnumerateSingleton.INSTANCE;
        secondSingleton.setObjName("secondName");
        System.out.println(firstSingleton.getObjName());
        System.out.println(secondSingleton.getObjName());

        // 反射获取实例测试
        try {
            EnumerateSingleton[] enumConstants = EnumerateSingleton.class.getEnumConstants();
            for (EnumerateSingleton enumConstant : enumConstants) {
                System.out.println(enumConstant.getObjName());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
