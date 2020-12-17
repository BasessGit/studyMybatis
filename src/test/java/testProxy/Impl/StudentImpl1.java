package testProxy.Impl;

import testProxy.Student;

public class StudentImpl1 implements Student {
    @Override
    public void say() {
        System.out.println("这个是第二个实现Student的实现类");
    }
}
