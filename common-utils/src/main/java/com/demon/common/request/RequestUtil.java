package com.demon.common.request;

import javax.servlet.http.HttpServletRequest;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * @description: request工具类
 * @author: DemonJun
 * @date: 2019年03月21日
 **/
public class RequestUtil {


  /**
   * 获取当前请求
   */
  public static HttpServletRequest getRequest() {
    RequestAttributes ra = RequestContextHolder.getRequestAttributes();
    ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) ra;
    assert servletRequestAttributes != null;

    return servletRequestAttributes.getRequest();
  }

}
