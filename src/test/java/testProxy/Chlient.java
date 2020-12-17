package testProxy;

import org.junit.Test;
import sun.misc.ProxyGenerator;
import testProxy.Impl.StudentImpl;
import testProxy.Impl.StudentImpl1;

import java.io.*;
import java.lang.reflect.Proxy;

public class Chlient {
    @Test
    public  void testProxyStudent(){
        Student student = (Student) Proxy.newProxyInstance(StudentImpl.class.getClassLoader(),new Class[]{StudentImpl.class},new StudentProxy("小明","男"));
          student.say();
    }

    @Test
    public void testProxyStudentImpl(){
       Student student =  (Student) Proxy.newProxyInstance(Student.class.getClassLoader(), new Class[]{Student.class},new StudentProxy());
        student.say();
        }

        @Test
        public void test01(){
        Student student = new StudentImpl1();
        StaticProxy staticProxy = new StaticProxy(student);
        staticProxy.say();
    }

    @Test
    public void buildProxy() throws IOException {
        byte[] bytes = ProxyGenerator.generateProxyClass("Student$Proxy1",new Class[]{Student.class});
            String fileName = "E:\\IDEA\\studyMybatis\\src\\test\\java\\Student&Proxy.class";
            File file = new File(fileName);
            FileOutputStream fileInputStream = new FileOutputStream(file);
            fileInputStream.write(bytes);
            fileInputStream.flush();
            fileInputStream.close();


    }
}
