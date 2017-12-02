package com.mdstech.sample.interceptor;

import com.mdstech.sample.CustomerService;

import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.StringJoiner;
import java.util.stream.Collectors;

@Interceptor
@LogRequest
public class LogInterceptor {

    @Inject
    CustomerService customerService;

    @Inject
    HttpServletRequest request;

    @AroundInvoke
    public Object checkAccess(InvocationContext ctx) throws Exception {
//        LogRequest annotation = ctx.getMethod().getAnnotation(LogRequest.class);
//        String role = annotation.role();
        String value = Arrays.stream(ctx.getParameters()).map(o -> o.toString()).collect(Collectors.joining());
        try {
            customerService.getRequestGauge().labels(request.getMethod(), request.getRequestURI(), request.getPathInfo()).set(new Double(value));
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        return ctx.proceed();
    }
}
