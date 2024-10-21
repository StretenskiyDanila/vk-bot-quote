package org.example.vkbot.logs;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import static org.example.vkbot.utils.LogMessages.*;

@Aspect
@Component
@Slf4j
public class LogAspects {

    @Pointcut("execution(public * org.example.vkbot.services.impl.*.*(..))")
    public void services() {
    }

    @Before(value = "services()")
    public void logBeforeService(JoinPoint joinPoint) {
        StringBuilder params = new StringBuilder();
        for (Object arg : joinPoint.getArgs()) {
            params.append("\t").append(arg.toString()).append("\n");
        }

        String methodName = getMethodName(joinPoint);
        log.info(START_METHOD.getMessage(), methodName);
        log.info(REQUEST.getMessage(), methodName, params);
    }

    @After(value = "services()")
    public void logAfterService(JoinPoint joinPoint) {
        log.info(END_METHOD.getMessage(), getMethodName(joinPoint));
    }

    @AfterThrowing(pointcut = "services()", throwing = "exception")
    public void logAfterThrowingService(JoinPoint joinPoint, Exception exception) throws Exception {
        StringBuilder trace = new StringBuilder();
        for (StackTraceElement element : exception.getStackTrace()) {
            trace.append("\t").append(element).append("\n");
        }
        log.error(ERROR.getMessage(), getMethodName(joinPoint), trace);
        throw exception;
    }

    private String getMethodName(JoinPoint joinPoint) {
        return "Class: " + joinPoint.getSourceLocation().getWithinType().getName() +
                ". Method: " + joinPoint.getSignature().getName();
    }

}
