package com.demon.common.log;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @description:
 * @author: DemonJun
 * @date: 2019年03月21日
 **/
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ControllerLog {

  /**
   * 是否输出日志
   */
  boolean loggable() default true;

  /**
   * 日志信息描述,可以记录该方法的作用等信息。
   */
  String desc() default "";

  /**
   * 日志等级
   */
  String level() default "INFO";

  /**
   * 日志输出范围,用于标记需要记录的日志信息范围，包含入参、返回值等。 ALL-入参和出参, REQUEST-入参, RESPONSE-出参
   */
  ControllerLogEnum scope() default ControllerLogEnum.ALL;

  /**
   * 入参输出范围，值为入参变量名，多个则逗号分割。不为空时，入参日志仅打印include中的变量
   */
  String include() default "";

  /**
   * 是否存入数据库
   */
  boolean db() default false;

  /**
   * 是否输出到控制台
   */
  boolean console() default true;
}
