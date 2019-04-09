package com.demon.common.log;

import lombok.ToString;

/**
 * @description:
 * @author: DemonJun
 * @date: 2019年03月21日
 **/
@ToString
public enum ControllerLogEnum {
  // log作用范围
  ALL, REQUEST, RESPONSE;

  public boolean contains(ControllerLogEnum scope) {
    if (this == ALL) {
      return true;
    } else {
      return this == scope;
    }
  }
}
