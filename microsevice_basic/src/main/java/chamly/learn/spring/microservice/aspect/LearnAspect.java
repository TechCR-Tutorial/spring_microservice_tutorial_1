package chamly.learn.spring.microservice.aspect;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LearnAspect {

    @Before(value = "@annotation(chamly.learn.spring.microservice.aspect.Auditable) && @annotation(auditable)")
    public void testBeforeWithAnnotation(JoinPoint joinPoint, Auditable auditable) {
        //System.out.println(id);
        Method method = MethodSignature.class.cast(joinPoint.getSignature()).getMethod();
        System.out.println("...... Before method - " + method.getName());
    }

    @Before(value = "execution(* chamly.learn.spring.microservice.*.*DaoService.*(..)) && @annotation(auditable)")
    public void testBeforeWithExection(JoinPoint joinPoint, Auditable auditable) {
        //System.out.println(id);
        Method method = MethodSignature.class.cast(joinPoint.getSignature()).getMethod();
        Object[] args = joinPoint.getArgs();
        Annotation[][] parameterAnnotations = method.getParameterAnnotations();
        for(int i = 0; i < parameterAnnotations.length; i++){
            Annotation[] annotations = parameterAnnotations[i];
            if (Arrays.stream(annotations).anyMatch(annotation -> annotation.annotationType() == Auditable.class)) {
                Object object = args[i];
                System.out.println(object);
            }
        }
        System.out.println("...... Before method - " + method.getName());
    }
}
