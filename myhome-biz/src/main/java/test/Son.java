package test;

/**
 * Created by zhouxiliang on 2015/11/18.
 */
public class Son extends Parent {
    protected String name = "son";

    protected void say(){
        System.out.println(super.getClass());
    }

    public void call(){
        super.call();
    }

    public static void main(String[] args) {
        new Son().call();
    }
}
