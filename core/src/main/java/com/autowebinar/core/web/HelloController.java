package com.autowebinar.core.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.ui.ModelMap;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping()
public class HelloController{

    @RequestMapping(value="/api/hello", method = RequestMethod.GET)
    public String printHello(ModelMap model) {
        model.addAttribute("message", "Hello Spring MVC Framework!");
        return "hello";
    }

    @RequestMapping(value="/api/login", method = RequestMethod.GET)
    public String login(ModelMap model) {
        model.addAttribute("message", "Добро Пожаловать!");
        model.addAttribute("signed", "false");
        return "login";
    }

    @RequestMapping(value="/api/add_cat", method = RequestMethod.GET)
    public String addCat(Model model, HttpServletRequest request) {
        request.setAttribute("vasinCat", "true");
        return "hello";
    }


    public class Credit {
        private Integer month;
        private Integer payBody;
        private Integer repayRate;

        public Credit(Integer month, Integer payBody, Integer repayRate) {
            this.month = month;
            this.payBody = payBody;
            this.repayRate = repayRate;
        }

        public Integer getMonth() {
            return month;
        }

        public void setMonth(Integer month) {
            this.month = month;
        }

        public Integer getPayBody() {
            return payBody;
        }

        public void setPayBody(Integer payBody) {
            this.payBody = payBody;
        }

        public Integer getRepayRate() {
            return repayRate;
        }

        public void setRepayRate(Integer repayRate) {
            this.repayRate = repayRate;
        }
    }
}
