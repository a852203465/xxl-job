package com.xxl.job.admin.common.config;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.xxl.job.admin.common.constants.AuthConstant;
import com.xxl.job.admin.common.pojo.entity.LoginToken;
import com.xxl.job.admin.service.LoginTokenService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 登录拦截器
 *
 * @author Rong.Jia
 * @date 2023/07/23
 **/
@Slf4j
public class LoginInterceptor implements HandlerInterceptor {

    @Autowired
    private LoginTokenService loginTokenService;

    /**
     * 检测全局session对象中是否有account数据，有则放行，没有则重定向到登陆界面
     * @param request 请求对象
     * @param response 响应对象
     * @param handler   处理器（url+Controller：映射）
     * @return 如果返回值为true放行当前的请求，如果为false，则表示拦截当前的请求
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String authorization = request.getHeader(AuthConstant.AUTHORIZATION_HEADER);
        String parameter = request.getParameter(AuthConstant.AUTHORIZATION_HEADER);

        if (request.getHeader("Accept").contains("html"))  return true;
        response.setContentType("text/json;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");

        if (StrUtil.isAllEmpty(authorization, parameter)) {
            log.error("缺失令牌,鉴权失败");
            response.sendRedirect("login");
            return false;
        }
        String token = StrUtil.isBlank(authorization) ? parameter : authorization;
        LoginToken loginToken = loginTokenService.findLoginTokenByToken(token);
        if (ObjectUtil.isEmpty(loginToken)) {
            log.error("未登录，或者授权过期");
            response.sendRedirect("login");
            return false;
        }

        loginTokenService.updateLoginTokenByToken(token);
        return true;
    }
}
