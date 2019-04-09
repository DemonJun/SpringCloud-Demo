package com.demon.gateway.contant;

/**
 * @description:
 * @author: fanjunxiang
 * @date: 2018年12月19日
 **/
public class ResponseCode extends com.demon.common.constant.ResponseCode {

  /**
   * 用户身份过期
   */
  public static final String ACCOUNT_HAS_EXPIRED_CODE = "9999";
  public static final String ACCOUNT_HAS_EXPIRED_MESSAGE = "用户身份过期";

  /**
   * 无权限访问此资源
   */
  public static final String NO_PERMISSION_CODE = "9998";
  public static final String NO_PERMISSION_MESSAGE = "无权限访问此资源";
  /**
   * 资源不存在
   */
  public static final String NOT_FOUND_CODE = "9997";
  public static final String NOT_FOUND_MESSAGE = "访问资源不存在";
  /**
   * 项目环境配置错误
   */
  public static final String PROJECT_ENV_ERROR_CODE = "9996";
  public static final String PROJECT_ENV_ERROR_MESSAGE = "项目环境配置错误";
}
