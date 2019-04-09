package com.demon.common.exception;

import static com.demon.common.constant.ResponseCode.FAILED_CODE;
import static com.demon.common.constant.ResponseCode.FAILED_MESSAGE;

import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;

/**
 * @author jundemon
 */
@Getter
@Setter
public class ServiceException extends RuntimeException implements Serializable {


  private static final long serialVersionUID = 4499257612954466045L;
  private String message;
  private String code;


  ServiceException(String code, String message) {
    this.code = code;
    this.message = message;
  }


  public static ServiceException buildDefaultException() {
    return new ServiceException(FAILED_CODE, FAILED_MESSAGE);
  }

/** 
*
* <br/>
*@param null	 
* @return 
* @author DemonJun
* @date  
*/
  public static ServiceException buildCustomException(String code, String message) {

    return new ServiceException(code, message);
  }


}