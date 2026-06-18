package proxy;

import net.ttddyy.dsproxy.listener.logging.SLF4JLogLevel;
import net.ttddyy.dsproxy.support.ProxyDataSourceBuilder;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.data.util.ReflectionUtils;

import javax.sql.DataSource;
import java.lang.reflect.Method;

public class ProxyDataSourceInterceptor implements MethodInterceptor {

    private final DataSource dataSource;

    // 自定义封装DataSource
    public ProxyDataSourceInterceptor(final DataSource dataSource) {
        this.dataSource = ProxyDataSourceBuilder.create(dataSource)
                .countQuery()
                .logQueryBySlf4j(SLF4JLogLevel.INFO)
                .build();
    }

    @Override
    public Object invoke(final MethodInvocation invocation) throws Throwable {
        Method proxyMethod = ReflectionUtils.findMethod(dataSource.getClass(), invocation.getMethod().getName());
        if (proxyMethod != null) {
            return proxyMethod.invoke(dataSource, invocation.getArguments());
        }
        return invocation.proceed();
    }
}
