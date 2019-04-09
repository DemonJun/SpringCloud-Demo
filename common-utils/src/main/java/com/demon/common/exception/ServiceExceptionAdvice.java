package com.demon.common.exception;

import com.demon.common.constant.ResponseCode;
import com.demon.common.model.CustomResponse;
import com.demon.common.util.DateUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @description:
 * @author: DemonJun
 * @date: 2018年09月26日
 **/
@RestControllerAdvice
@Slf4j
public class ServiceExceptionAdvice {

  @ExceptionHandler(Exception.class)
  @ResponseStatus(HttpStatus.OK)
  public CustomResponse serviceExceptionHandler(Exception e) {
    if (e instanceof ServiceException) {
      ServiceException serviceException = (ServiceException) e;

      return CustomResponse.builder()
          .code(serviceException.getCode())
          .message(serviceException.getMessage())
          .timestamp(DateUtils.getNowDateTimeString())
          .build();
    }

    log.error("get a error，error message:{}", ErrorMessage.getErrorMessage(e));

    return CustomResponse.builder()
        .code(ResponseCode.FAILED_CODE)
        .message(ResponseCode.FAILED_MESSAGE)
        .timestamp(DateUtils.getNowDateTimeString())
        .build();

  }
}
