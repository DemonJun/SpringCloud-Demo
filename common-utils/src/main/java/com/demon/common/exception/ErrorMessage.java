package com.demon.common.exception;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * @description:
 * @author: DemonJun
 * @date: 2019年01月28日
 **/
public class ErrorMessage {

  private static final boolean PRINT_ERROR_MESSAGE_STACK = true;

  /**
   * 获取异常的错误信息
   */
  public static String getErrorMessage(Throwable t) {
    StringBuffer buffer;
    if (PRINT_ERROR_MESSAGE_STACK) {
      StringWriter stringWriter = new StringWriter();
      PrintWriter writer = new PrintWriter(stringWriter);
      t.printStackTrace(writer);
      buffer = stringWriter.getBuffer();
    } else {
      buffer = new StringBuffer(t.getMessage());
    }

    return buffer.toString();

  }
}
