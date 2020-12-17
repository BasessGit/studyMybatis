package mybatis.plugin;

import com.sun.org.glassfish.gmbal.IncludeSubclass;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;

import java.sql.Statement;
import java.util.Properties;

@Intercepts(
        /*
        设置拦截参数，type：拦截四大对象的哪个对象
                                    method:拦截哪个方法
                                     args:拦截方法的参数
         */
        @Signature(type = StatementHandler.class,method = "parameterize",args = {Statement.class})
)
/**
 *  @author: wangwei
 *  @Date: 2020/12/15 19:06
 *  @Description:mybatis拦截器实现demo
 */
public class MyFirstPlugin implements Interceptor {
    /**
    * @Description 拦截目标对象的方法
    * @Author  wangwei
    * @Date   2020/12/13 18:47
    * @Param
    * @Return      Object
    * @Exception
    *
    */
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        System.out.println("执行mybatis...intercept将要拦截的目标方法"+invocation.getMethod());
        System.out.println("-----------------------------");
       Object target =  invocation.getTarget();
        MetaObject metaObject = SystemMetaObject.forObject(target);
        Object valve = metaObject.getValue("parameterHandler.parameterObject");
        System.out.println("参数值是："+valve);
        metaObject.setValue("parameterHandler.parameterObject",10);

        System.out.println("-----------------------------");
        //执行目标对象的方法
        Object proceed = invocation.proceed();
        return proceed;
    }
    /**
    * @Description 包装目标对象(为目标对象创建一个代理对象)   3422
    * 34@Author  wangwei
    * @Date   2020/12/13 18:49
    * @Param
    * @Return
    * @Exception
    *
    */
    @Override
    public Object plugin(Object target) {
        //使用mybatis封装的动态代理，返回动态代理对象
        System.out.println("执行mybatis...plugin插件将要包装的对象："+target);
     Object warp =    Plugin.wrap(target, this);
        return warp;
    }

    /**
    * @Description 设置插件配置的信息
    * @Author  wangwei
    * @Date   2020/12/14 18:48
    * @Param
    * @Return
    * @Exception
    *
    */
    @Override
    public void setProperties(Properties properties) {
        System.out.println("配置"+properties);
    }
}
