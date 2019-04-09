package com.demon.order.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.demon.order.entity.Order;
import com.demon.order.mapper.OrderMapper;
import com.demon.order.service.IOrderService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 订单信息表 服务实现类
 * </p>
 *
 * @author JunDemon
 * @since 2019-04-09
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements IOrderService {

}
