package com.demon.common.constant;

/**
 * @author DemonJun
 */
public class ResponseCode {

  /**
   * 成功
   */
  public static final String SUCCESS_CODE = "0000";
  public static final String SUCCESS_MESSAGE = "成功";
  /**
   * 服务路由错误
   */
  public static final String ROUTE_SERVICE_FILED_CODE = "0001";
  public static final String ROUTE_SERVICE_FILED_MESSAGE = "服务路由错误";
  /**
   * 参数错误
   */
  public static final String PARAMETER_FAILED_CODE = "0002";
  public static final String PARAMETER_FAILED_MESSAGE = "参数错误";
  /**
   * 系统错误
   */
  public static final String FAILED_CODE = "0003";
  public static final String FAILED_MESSAGE = "系统错误";
  /**
   * 数据上链错误
   */
  public static final String BLOCKCHAIN_FAILED_CODE = "0004";
  public static final String BLOCKCHAIN_FAILED_MESSAGE = "数据上链失败";
  /**
   * 服务熔断错误
   */
  public static final String HYSTRIX_FAILED_CODE = "0005";
  public static final String HYSTRIX_FAILED_MESSAGE = "服务熔断错误";

}
