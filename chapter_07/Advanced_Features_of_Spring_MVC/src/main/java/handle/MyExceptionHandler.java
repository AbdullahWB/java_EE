package handle;

import exception.MyException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

@Component
public class MyExceptionHandler implements HandlerExceptionResolver {

    @Override
    public ModelAndView resolveException(
            HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        ModelAndView modelAndView = new ModelAndView();
        if (ex instanceof MyException) {
            modelAndView.addObject("msg", ex.getMessage());
        } else if (ex instanceof java.io.IOException) {
            modelAndView.addObject("msg", "网络异常！");
        } else {
            modelAndView.addObject("msg", "系统异常！");
        }
        modelAndView.setViewName("error");
        return modelAndView;
    }
}