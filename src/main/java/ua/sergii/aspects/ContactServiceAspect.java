package ua.sergii.aspects;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ContactServiceAspect {

    private final static Logger logger = Logger.getLogger(ContactServiceAspect.class);

    @Pointcut("execution(* ua.sergii.service.ContactServiceImpl.*(..))")
    public void pointCutContactService() {

    }

    @Before("pointCutContactService()")
    public void logBefore(JoinPoint joinPoint) {
	logger.debug("Starting working of method: " + joinPoint.getSignature().getName());
    }

    @After("pointCutContactService()")
    public void logAfter(JoinPoint joinPoint) {
	logger.debug("Ending working of method " + joinPoint.getSignature().getName());
    }

    @AfterThrowing(pointcut = "pointCutContactService()", throwing = "ex")
    public void errorInterceptor(Exception ex) {
	logger.error(ex);
    }
}
