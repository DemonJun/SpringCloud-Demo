package com.demon.goods.service;

import com.demon.common.model.CustomResponse;
import com.demon.goods.service.sentinel.OrderFeignClientSentinel;
import com.demon.goods.vo.OrderVo;
import java.util.List;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Primary;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @ClassName OrderFeignClient.java
 * @author DemonJun
 * @version 1.0.0
 * @Description
 * @createTime 2019-04-09 13:16:00
 */
@FeignClient(name = "order-service", contextId = "orderFeignClient",
    fallback = OrderFeignClientSentinel.class, path = "/order")
@Primary
public interface OrderFeignClient {

  @GetMapping("/order")
  CustomResponse<List<OrderVo>> getOrderList();
}
