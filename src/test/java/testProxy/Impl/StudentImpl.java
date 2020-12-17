package testProxy.Impl;

import testProxy.Student;

public class StudentImpl  implements Student {
    @Override
    public void say() {
        System.out.println("我是Student接口的实现类");
    }
}
