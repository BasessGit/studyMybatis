package testProxy;

import testProxy.Impl.StudentImpl;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class StudentProxy implements InvocationHandler {
    private String name;
    private String sex;
    private StudentImpl studentImpl;

    public StudentProxy(StudentImpl studentImpl) {
       this.studentImpl = studentImpl;
    }

    public StudentProxy(String name, String sex) {
        this.name = name;
        this.sex = sex;
    }

    public StudentProxy() {
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        //如果是Object类的方法，直接
        if (Object.class.equals(method.getDeclaringClass())){
            return method.invoke(this,args);
       /* }else if ("say".equals(method.getName())){
            System.out.println("我是"+this.name+"是一个"+this.sex+"学生");
            System.out.println(Object.class.toString());
            System.out.println(method.getDeclaringClass().toString());*/
        }else {
            System.out.println("前置通知");
            if(this.studentImpl !=null){
                method.invoke(this.studentImpl,args);
            }else if ("say".equals(method.getName())){
                System.out.println("我是"+this.name+"是一个"+this.sex+"学生");
                System.out.println(Object.class.toString());
                System.out.println(method.getDeclaringClass().toString());
            }
            System.out.println("后置通知");
        }
        return null;
    }

    public static void main(String[] args) {
        //Proxy.newProxyInstance,会先从缓存中查找代理对象，否则会在调用静态类部类中的apply方法，apply方法中的ProxyGenerator.generateProxyClass方法将
        //传来的参数重新生成新的字节码文件，然后进行初始化操作，返回新的Proxy代理对象
        Student proxyStudent =  (Student) Proxy.newProxyInstance(StudentImpl.class.getClassLoader(), new Class[]{Student.class},new StudentProxy(new StudentImpl()));
        proxyStudent.say();
    }


}
