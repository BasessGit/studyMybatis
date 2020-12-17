package testProxy;

import testProxy.Impl.StudentImpl;

public class StaticProxy implements Student {

    private Student student;

    public StaticProxy() {
    }

    public StaticProxy(Student student) {
        this.student = student;
    }

    @Override
    public void say() {
        System.out.println("静态代理开始");
        student.say();
        System.out.println("静态代理结束");
    }


}
