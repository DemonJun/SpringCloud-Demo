package com.demon.common.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @description: 分页对象
 * @author: DemonJun
 * @date: 2018年12月14日
 **/
@Data
@Builder
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class Pages<T> {

  /**
   * 总数
   */
  private Long total;
  /**
   * 当前页码
   */
  private Integer currentPage;
  /**
   * 页数量
   */
  private Integer pageSize;
  /**
   * 数据域
   */
  private T data;
}
