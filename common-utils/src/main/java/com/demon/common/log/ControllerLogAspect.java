package com.demon.common.log;

import com.demon.common.request.RequestUtil;
import com.demon.common.util.JsonUtils;
import java.lang.reflect.Method;
import javax.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @description:
 * @author: DemonJun
 * @date: 2019年03月21日
 **/
@Aspect
@Slf4j
public class ControllerLogAspect {

  /**
   * Controller层切点
   */
  @Pointcut("execution(* *..controller..*.*(..))")
  public void controllerAspect() {
  }

  @Before("controllerAspect()")
  public void doBefore(JoinPoint joinPoint) {

    Method method = ((MethodSignature) joinPoint.getSignature()).getMethod();
    boolean hasMethodLogAnnotation = method.isAnnotationPresent(ControllerLog.class);

    if (hasMethodLogAnnotation) {
      ControllerLog controllerLog = method.getAnnotation(ControllerLog.class);

      if (controllerLog.loggable() && controllerLog.scope().contains(ControllerLogEnum.REQUEST)) {
        HttpServletRequest request = RequestUtil.getRequest();
        String requestMethod = request.getMethod();
        String params;

        if (requestMethod.equals(RequestMethod.GET.name())) {
          params = request.getQueryString();
        } else {
          Object[] objects = joinPoint.getArgs();
          StringBuilder stringBuilder = new StringBuilder();

          for (Object object : objects) {
            stringBuilder.append(JsonUtils.toJsonString(object));
          }

          params = stringBuilder.toString();
        }

        String uri = request.getRequestURI();
        String desc = controllerLog.desc();
        switch (controllerLog.level()) {
          case "INFO": {
            log.info("请求说明：{}, method: {}, uri: {}, params: {}",
                desc, requestMethod, uri, params);
            break;
          }
          case "DEBUG": {
            log.debug("请求说明：{}, method: {}, uri: {}, params: {}",
                desc, requestMethod, uri, params);
            break;
          }
          case "WARN": {
            log.warn("请求说明：{}, method: {}, uri: {}, params: {}",
                desc, requestMethod, uri, params);
            break;
          }
          case "ERROR": {
            log.error("请求说明：{}, method: {}, uri: {}, params: {}",
                desc, requestMethod, uri, params);
            break;
          }
          default: {
            break;
          }
        }
      }
    }
  }


  @Around("controllerAspect()")
  public Object doAround(ProceedingJoinPoint point) throws Throwable {
    Method method = ((MethodSignature) point.getSignature()).getMethod();
    boolean hasMethodLogAnnotation = method.isAnnotationPresent(ControllerLog.class);

    if (!hasMethodLogAnnotation) {
      return point.proceed();
    }
    Object result = point.proceed();
    ControllerLog controllerLog = method.getAnnotation(ControllerLog.class);

    if (controllerLog.loggable() && controllerLog.scope().contains(ControllerLogEnum.RESPONSE)) {
      String desc = controllerLog.desc();
      switch (controllerLog.level()) {
        case "INFO": {
          log.info("请求说明：{}，返回值：{}",
              desc, JsonUtils.toJsonString(result));
          break;
        }
        case "DEBUG": {
          log.debug("请求说明：{}，返回值：{}",
              desc, JsonUtils.toJsonString(result));
          break;
        }
        case "WARN": {
          log.warn("请求说明：{}，返回值：{}",
              desc, JsonUtils.toJsonString(result));
          break;
        }
        case "ERROR": {
          log.error("请求说明：{}，返回值：{}",
              desc, JsonUtils.toJsonString(result));
          break;
        }
        default: {
          break;
        }
      }
    }

    return result;
  }
}
