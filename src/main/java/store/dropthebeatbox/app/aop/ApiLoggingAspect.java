package store.dropthebeatbox.app.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;

@Aspect
@Component
public class ApiLoggingAspect {

    private static final Logger logger = LoggerFactory.getLogger(ApiLoggingAspect.class);

    @Around("execution(* store.dropthebeatbox.app.web.controller.*.*(..))")
    public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();

        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

        logger.info("============= START OF REQUEST =============");
        // Log request details
        logger.info("Request Method: " + request.getMethod());
        logger.info("Request URI: " + request.getRequestURI());
//        logger.info("Request Params: " + Arrays.toString(joinPoint.getArgs()));
//        logger.info("Request Headers: " + Arrays.toString(request.getHeaderNames().asIterator().toArray()));
        logger.info("Client IP: " + request.getRemoteAddr());
        logger.info("User Agent: " + request.getHeader("User-Agent"));
        logger.info("Request timestamp: " + LocalDateTime.now());

        // Proceed with method execution
        Object proceed;
        try {
            proceed = joinPoint.proceed();
        } catch (Exception e) {
            // Log error information
            logger.error("Exception: ", e);
            throw e;
        }

        long executionTime = System.currentTimeMillis() - start;

        // Log response details
        logger.info("Response: " + proceed);
        logger.info("Execution time: " + executionTime + "ms");

        logger.info("============== END OF REQUEST ==============");

        return proceed;
    }
}