package handle;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.servlet.HandlerInterceptor;
import pojo.User;

public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws Exception {

        // 1. 获取 Session
        HttpSession session = request.getSession(false);
        // 2. 从 Session 中获取用户信息
        User user = session == null ? null : (User) session.getAttribute("USER_SESSION");

        // 3. 判断用户是否已经登录
        if (user != null) {
            // 已经登录，放行请求
            return true;
        }
        // 4. 没有登录，跳转到登录页面
        response.sendRedirect(request.getContextPath() + "/login");
        // 5. 返回 false，表示拦截请求，不继续执行 Controller
        return false;
    }
}
