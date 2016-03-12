package test;

/**
 * Created by zhouxiliang on 2015/11/18.
 */
public class Parent {
    protected String name = "parent";

    protected void say(){
        System.out.println(Parent.this.name);
    }

    public void call(){
        this.say();
    }

}
