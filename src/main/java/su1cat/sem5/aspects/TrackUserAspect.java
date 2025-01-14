package su1cat.sem5.aspects;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import su1cat.sem5.observers.Observer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

@Aspect
@Component
public class TrackUserAspect {
    private static final Logger logger = Logger.getLogger(TrackUserAspect.class.getName());

    private final List<Observer> observers = new ArrayList<>();

    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    @Around("@annotation(su1cat.sem5.aspects.TrackUserAction)")
    public Object trackUserAction(ProceedingJoinPoint joinPoint) throws Throwable {
        String user = "currentUser";
        String methodName = joinPoint.getSignature().toString();
        Object[] args = joinPoint.getArgs();
        String logMessage = "User " + user + " invoked " + methodName + " with arguments: " + Arrays.toString(args);

        System.out.println(logMessage);
        logger.info(logMessage);

        notifyObservers(logMessage);

        return joinPoint.proceed();
    }

    private void notifyObservers(String message) {
        for (Observer observer : observers) {
            observer.update(message);
        }
    }
}

// todo: add subtasks. because im committed to over-engineering this.
// todo: add some semblance of security
// todo: tests. tests tests tests so many tests
