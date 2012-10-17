package com.mincoms.book.aop;


import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;


@Component
@Aspect
public class MethodLogger {
	private static final Logger logger = LoggerFactory.getLogger(MethodLogger.class);
	
	/**
	 * <b>timeMethod Aop </b> <br />
	 * 메소드  실행후 메소시간을 측정함 
	 * <pre>
	 * <b>History:</b>	
	 * </pre>
	 * @author 김동훈
	 * @version 1.0
	 * @since 2012. 8. 24	
	 * @throws 
	 * @see ssss
	 */
	@Around("execution(* com.mincoms.book..*(..))")
	public Object timeMethod(ProceedingJoinPoint joinPoint) throws Throwable {
		StopWatch stopWatch = new StopWatch();
		stopWatch.start();

		Object retVal = joinPoint.proceed();

		stopWatch.stop();

		StringBuffer logMessageStringBuffer = new StringBuffer();
		logMessageStringBuffer.append(joinPoint.getTarget().getClass().getName());
		logMessageStringBuffer.append(".");
		logMessageStringBuffer.append(joinPoint.getSignature().getName());
		logMessageStringBuffer.append("(");
		logMessageStringBuffer.append(joinPoint.getArgs());
		logMessageStringBuffer.append(")");
		logMessageStringBuffer.append(" execution time: ");
		logMessageStringBuffer.append(stopWatch.getTotalTimeMillis());
		logMessageStringBuffer.append(" ms");
		System.out.println("===========================================================");
		System.out.println("===========================================================");
		logger.info(logMessageStringBuffer.toString());

		return retVal;
	}

}
