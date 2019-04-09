package com.demon.common.model;

import static com.demon.common.constant.ResponseCode.SUCCESS_CODE;
import static com.demon.common.constant.ResponseCode.SUCCESS_MESSAGE;

import com.demon.common.util.DateUtils;
import java.io.Serializable;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Tolerate;
import org.joda.time.LocalDateTime;

/**
 * 响应模型，作为服务端返回的消息模型
 *
 * @author xueyongxin
 */
@Data
@Builder
@EqualsAndHashCode(callSuper = false)
public class CustomResponse<T> implements Serializable {

  private static final long serialVersionUID = 419968474767654560L;
  /**
   * 状态
   */
  @Builder.Default
  private String code = SUCCESS_CODE;
  /**
   * 消息 自定义
   */
  @Builder.Default
  private String message = SUCCESS_MESSAGE;
  /**
   * 数据
   */
  @Builder.Default
  private T data = null;

  @Builder.Default
  private String timestamp = DateUtils.getNowDateTimeString();


  @Tolerate
  public CustomResponse(T data) {
    this();
    this.data = data;
  }

  @Tolerate
  public CustomResponse() {
    this.code = SUCCESS_CODE;
    this.message = SUCCESS_MESSAGE;
    this.timestamp = DateUtils.getNowDateTimeString();
  }
}
