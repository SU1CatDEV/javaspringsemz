package su1cat.sem5.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

import java.util.Arrays;
import java.util.logging.Logger;

@Aspect
public class TrackUserAspect {
    private static final Logger logger =
            Logger.getLogger(TrackUserAspect.class.getName());
    @Before("@annotation(su1cat.sem5.aspects.TrackUserAction)")
    public void trackUserAction(JoinPoint joinPoint) {
        String user = "currentUser"; // с безопасностью еще разбераюсь.
        String methodName = joinPoint.getSignature().toString();
        Object[] args = joinPoint.getArgs();
        System.out.println("User " + user + " invoked " + methodName + " with arguments: " + Arrays.toString(args));
        logger.info("User " + user + " invoked " + methodName + " with arguments: " + Arrays.toString(args));
    }
}
