package com.demon.order.controller;


import com.demon.common.model.CustomResponse;
import com.demon.order.entity.Order;
import com.demon.order.service.IOrderService;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 订单信息表 前端控制器
 * </p>
 *
 * @author JunDemon
 * @since 2019-04-09
 */
@RestController
@RequestMapping("/order")
@Slf4j
public class OrderController {

  private final IOrderService iOrderService;

  public OrderController(IOrderService iOrderService) {
    this.iOrderService = iOrderService;
  }

  @GetMapping
  public CustomResponse<List<Order>> getOrderList() {

    return CustomResponse.<List<Order>>builder().data(
        iOrderService.list()
    ).build();
  }

}

