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

    @RequestMapping(value="/hello", method = RequestMethod.GET)
    public String printHello(ModelMap model) {
        model.addAttribute("message", "Hello Spring MVC Framework!");
        return "hello";
    }

    @RequestMapping(value="/login", method = RequestMethod.GET)
    public String login(ModelMap model) {
        model.addAttribute("message", "������ ������� ����!");
        model.addAttribute("signed", "false");
        return "login";
    }

    @RequestMapping(value="/vk_auth", method = RequestMethod.GET)
    public String vkAuth(ModelMap model, HttpServletRequest request) {
        model.addAttribute("message", request.getParameter("name")+", �� ������ ������� �����������!");
        model.addAttribute("signed", "true");
        model.addAttribute("id", request.getParameter("name"));
        model.addAttribute("image", request.getParameter("url"));
        return "login";
    }

    @RequestMapping(value="/calculate_credit", method = RequestMethod.GET)
    public String acceptCreditDetails(Model model, HttpServletRequest request) {
        model.addAttribute("message", "Our model is:"+request.getParameter("body"));
        model.addAttribute("calculation", "true");
        request.setAttribute("calculation", "true");

        int numMonths = Integer.parseInt(request.getParameter("months"));
        int body = Integer.parseInt(request.getParameter("body"));
        int payRate = Integer.parseInt(request.getParameter("rate"));

        int payBody = body / numMonths;
        int repayRate = payBody * payRate / 100;

        List<Credit> creditList = new ArrayList<Credit>(numMonths);

        for (int i=0; i < numMonths; i++) {
            creditList.add(new Credit(i+1, payBody, repayRate));
        }

        request.setAttribute("credit", creditList);

        return "hello";
    }

    @RequestMapping(value="/add_cat", method = RequestMethod.GET)
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
