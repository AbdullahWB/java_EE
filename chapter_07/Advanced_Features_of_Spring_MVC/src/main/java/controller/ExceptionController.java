package controller;

import exception.MyException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;

@Controller
public class ExceptionController {

    @RequestMapping("/showNullPointer")
    public String showNullPointer() {
        ArrayList<String> list = null;
        list.get(0);
        return "success";
    }

    @RequestMapping("/showIOException")
    public String showIOException() throws FileNotFoundException {
        FileInputStream fis = new FileInputStream("JavaWeb.xml");
        return "success";
    }

    @RequestMapping("/showArithmetic")
    public String showArithmetic() {
        int result = 10 / 0;
        return "success";
    }

    @RequestMapping("/addData")
    public String addData() throws MyException {
        throw new MyException("新增数据异常！");
    }
}
