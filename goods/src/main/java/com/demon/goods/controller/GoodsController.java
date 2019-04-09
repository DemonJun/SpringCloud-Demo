package com.demon.goods.controller;

import com.demon.common.log.ControllerLog;
import com.demon.common.model.CustomResponse;
import com.demon.goods.service.OrderFeignClient;
import com.demon.goods.vo.OrderVo;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName GoodsController.java
 * @author DemonJun
 * @version 1.0.0
 * @Description
 * @createTime 2019-04-09 10:53:00
 */
@RestController
@RequestMapping("/goods")
@Slf4j
public class GoodsController {

  private final OrderFeignClient orderFeignClient;

  public GoodsController(OrderFeignClient orderFeignClient) {
    this.orderFeignClient = orderFeignClient;
  }


  @GetMapping
  @ControllerLog
  public CustomResponse<String> getGoods() {

    return CustomResponse.<String>builder().data(
        "Goods Service Get getGoods Success"
    ).build();
  }

  @GetMapping("/orderList")
  @ControllerLog
  public CustomResponse<List<OrderVo>> getOrderList() {

    return orderFeignClient.getOrderList();
  }
}
