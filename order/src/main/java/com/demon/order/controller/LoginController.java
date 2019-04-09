package com.demon.order.controller;

import com.demon.common.cache.CacheBuild;
import com.demon.common.model.CustomResponse;
import com.demon.common.util.JsonUtils;
import com.demon.common.util.TokenUtils;
import com.fasterxml.jackson.annotation.JsonInclude;
import javax.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName LoginController.java
 * @author DemonJun
 * @version 1.0.0
 * @Description
 * @createTime 2019-04-09 11:29:00
 */
@RestController
@RequestMapping("/login")
@Slf4j
public class LoginController {

  private final StringRedisTemplate redisTemplate;

  public LoginController(StringRedisTemplate redisTemplate) {
    this.redisTemplate = redisTemplate;
  }


  @PostMapping
  public CustomResponse<LoginModel> login(@Validated({LoginModel.login.class}) @RequestBody LoginModel loginModel) {

    if (loginModel.getName().equals("test") && loginModel.getPassword().equals("test")) {
      loginModel.setPassword(null);
      loginModel.setAccessToken(
          TokenUtils.getTokenFromObjectJson(
              JsonUtils.toJsonString(loginModel),
              CacheBuild.keyBuilder(TokenUtils.CACHE_KEY, loginModel.getName())
          )
      );
      redisTemplate.boundValueOps(
          CacheBuild.keyBuilder(TokenUtils.CACHE_KEY, loginModel.getName())
      ).set(loginModel.getAccessToken());
    }

    loginModel.setPassword(null);

    return CustomResponse.<LoginModel>builder().data(
        loginModel
    ).build();
  }
}

@Data
@Builder
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@Validated
class LoginModel {

  @NotBlank(message = "用户名不能为空", groups = {login.class})
  private String name;
  @NotBlank(message = "密码不能为空", groups = {login.class})
  private String password;
  private String accessToken;

  public interface login {

  }

}
