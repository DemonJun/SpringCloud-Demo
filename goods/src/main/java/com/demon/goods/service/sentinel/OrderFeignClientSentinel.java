package com.demon.goods.service.sentinel;

import com.demon.common.constant.ResponseCode;
import com.demon.common.exception.ServiceException;
import com.demon.common.model.CustomResponse;
import com.demon.goods.service.OrderFeignClient;
import com.demon.goods.vo.OrderVo;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @ClassName OrderFeignClientSentinel.java
 * @author DemonJun
 * @version 1.0.0
 * @Description
 * @createTime 2019-04-09 13:17:00
 */
@Component
@Slf4j
public class OrderFeignClientSentinel implements OrderFeignClient {

  @Override
  public CustomResponse<List<OrderVo>> getOrderList() {
    log.error("获取订单列表请求异常");
    throw ServiceException.buildCustomException(ResponseCode.FAILED_CODE, ResponseCode.FAILED_MESSAGE);
//    return CustomResponse.<List<OrderVo>>builder().code(ResponseCode.FAILED_CODE).message(ResponseCode.FAILED_MESSAGE).data(null).build();
  }
}
