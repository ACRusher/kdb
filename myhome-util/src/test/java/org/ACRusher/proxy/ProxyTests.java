package org.ACRusher.proxy;

import net.sf.cglib.beans.BulkBean;
import net.sf.cglib.proxy.Callback;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;
import net.sf.cglib.reflect.FastClass;
import org.apache.commons.lang.StringUtils;
import org.junit.Before;
import org.junit.Test;
import org.springframework.aop.support.AopUtils;

import java.lang.reflect.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author xiliang.zxl
 * @date 2016-03-05 下午11:22
 */

public class ProxyTests {

    @Before
    public void before() {

    }

    @Test
    public void testBulkBean() {
        //测试 cglib BulkBean 工具类
        Class clazz = Person.class;
        BulkBean bulkBean = BulkBean.create(clazz, getGetters(clazz),
                getSetters(clazz), getTypes(clazz));
        Person person = new Person();
        person.setName("name");
        person.setAge(11);
        Object[] arr = bulkBean.getPropertyValues(person);
        System.out.println(Arrays.toString(arr));
    }

    public String[] getGetters(Class clazz) {
        return getMethods(clazz, "get");
    }

    public String[] getSetters(Class clazz) {
        return getMethods(clazz, "set");
    }

    public String[] getMethods(Class clazz, String prefix) {
        Field[] fields = getFields(clazz);
        String[] result = new String[fields.length];
        for (int i = 0; i < fields.length; ++i) {
            Field f = fields[i];
            StringBuilder sb = new StringBuilder();
            sb.append(prefix).append(Character.toUpperCase(f.getName().charAt(0)))
                    .append(f.getName().substring(1));
            result[i] = sb.toString();
        }
        return result;

    }

    public Class[] getTypes(Class clazz) {
        Field[] fields = getFields(clazz);
        Class[] result = new Class[fields.length];
        for (int i = 0; i < fields.length; ++i)
            result[i] = fields[i].getType();
        return result;
    }

    public Field[] getFields(Class clazz) {
        List<Field> list = new ArrayList<Field>();
        while (clazz != Object.class) {
            list.addAll(Arrays.asList(clazz.getDeclaredFields()));
            clazz = clazz.getSuperclass();
        }
        Field[] arr = new Field[list.size()];
        for (int i = 0; i < list.size(); ++i) arr[i] = list.get(i);
        return arr;
    }

    @Test
    public void testFastClass() throws IllegalAccessException, InstantiationException, InvocationTargetException, NoSuchMethodException {

        //FastClass newInstance更快
        long s = System.currentTimeMillis();
        int cnt = 1000;
        for (int i = 0; i < cnt; ++i) {
            Reverser reverser = Reverser.class.newInstance();
        }
        long s1 = System.currentTimeMillis();
        System.out.println("plain newInstance cost: " + (s1 - s));
        FastClass fastClass = FastClass.create(Reverser.class);
        s = System.currentTimeMillis();
        for (int i = 0; i < 1000; ++i) {
            Reverser reverser = (Reverser) fastClass.newInstance();
        }
        s1 = System.currentTimeMillis();
        System.out.println("fastClass newInstance cost: " + (s1 - s));

        Reverser reverser = Reverser.class.newInstance();
        Method m1 = reverser.getClass().getDeclaredMethod("reverse", String.class);
        FastClass fastClass1 = FastClass.create(Reverser.class);
//        Method m2=fastClass1.getClass().getDeclaredMethod("reverse", String.class);
        Reverser reverser1 = (Reverser) fastClass.newInstance();
        //2 fastclass 实例 在 invoke时会更快
        s = System.currentTimeMillis();
        for (int i = 0; i < cnt; ++i) {
            m1.invoke(reverser, "123");
        }
        System.out.println("plain method invoke cost: " + (System.currentTimeMillis() - s));

        s = System.currentTimeMillis();
        for (int i = 0; i < cnt; ++i) {
            m1.invoke(reverser1, "123");
        }
        System.out.println("fastclass method invoke cost: " + (System.currentTimeMillis() - s));


    }

    @Test
    public void testCglibProxy() throws IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {

        //创建代理类
//        Enhancer enhancer=new Enhancer();
//        enhancer.setSuperclass(Reverser.class);
//        enhancer.setInterfaces(new Class[]{IEcho.class});
//        enhancer.setCallbackType(MyMethodInterceptor.class);
//        Class<?> clazz=enhancer.createClass();
//        System.out.println(Reverser.class.isAssignableFrom(clazz));
//        System.out.println(IEcho.class.isAssignableFrom(clazz));
//        Object obj=clazz.newInstance();
//        Method m=clazz.getMethod("setCallbacks", new Class[]{Callback[].class});
//        System.out.println(m.getName());
//        Class[] parameterTypes=m.getParameterTypes();
//        System.out.println(Arrays.toString(parameterTypes));
//        m.invoke(obj,  new Object[]{ new Callback[]{new MyMethodInterceptor()}});
//
//        System.out.println(((Reverser) obj).reverse("abc"));
//        System.out.println(((IEcho)obj).echo("abc"));

        //创建代理实例
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(Reverser.class);
        enhancer.setCallbacks(new Callback[]{new MyMethodInterceptor()});
        Reverser reverser = (Reverser) enhancer.create();
        System.out.println(reverser.reverse("123"));

    }

    @Test
    public void testJDKDynamicProxy() {
        IEcho iEcho = (IEcho) Proxy.newProxyInstance(getClass().getClassLoader(), new Class[]{IEcho.class},
                new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        System.out.println("Invocation handler's proxy : " + Proxy.isProxyClass(proxy.getClass()));
                        return method.getName() + " invoke from " + proxy.getClass().getSimpleName() + " , params : " + Arrays.toString(args);
                    }
                });
        System.out.println(iEcho.echo("hello"));
        System.out.println(AopUtils.isJdkDynamicProxy(iEcho));
    }

    private static interface IEcho extends Callback {
        String echo(String words);
    }

    public static class Reverser {
        public Reverser() {
        }

        public String reverse(String str) {
            return StringUtils.reverse(str);
        }
    }

    public class MyMethodInterceptor implements MethodInterceptor {

        @Override
        public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
            System.out.println(" methodInterceptor,target : " + o.getClass().getSimpleName());
            if (method.getName().equals("echo")) {
                return "echo : " + objects[0];
            } else {
                return methodProxy.invokeSuper(o, objects);
            }
        }
    }

    public static class Person {
        private String name;
        private int age;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }
    }


}
