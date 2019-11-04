package designpatterns;

/**
 * @Author My
 * @Date 2019/11/4
 **/

/**
 * 策略模式
 * 生成一组算法族，封装每个算法，并且可以使他们互换
 * 使用场景:https://www.cnblogs.com/LoveShare/p/10953940.html
 */
public class Strategy {
    public static void main(String[] args) {
        Duck duck = new Duck();
        duck.setQuackBehavier(new Quack());
        duck.performQuack();
        duck.setQuackBehavier(new Suack());
        duck.performQuack();
    }
}
class Duck{
    private QuackBehavier quackBehavier;

    public void performQuack(){
        if(null != quackBehavier){
            quackBehavier.quack();
        }
    }

    public void setQuackBehavier(QuackBehavier quackBehavier){
        this.quackBehavier = quackBehavier;
    }
}

interface QuackBehavier{
    void quack();
}

class Quack implements QuackBehavier{

    @Override
    public void quack() {
        System.out.println("嘎嘎嘎嘎。。。");
    }
}

class Suack implements QuackBehavier{

    @Override
    public void quack() {
        System.out.println("吱吱吱吱。。。");
    }
}
