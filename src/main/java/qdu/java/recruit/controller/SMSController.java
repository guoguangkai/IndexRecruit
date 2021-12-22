package qdu.java.recruit.controller;

import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


import javax.servlet.http.HttpServletRequest;

import static qdu.java.recruit.util.SMSUtil.Sender.sendCode;
import static qdu.java.recruit.util.SMSUtil.Verify.verifyCode;

@Controller
@RequestMapping(value = "sms")
public class SMSController {

    @ResponseBody
    @PostMapping(value = "/sendCode")
    public void smssendCode(@RequestParam String mobile, HttpServletRequest request) {
        sendCode(mobile,request);
    }

    @ResponseBody
    @PostMapping(value = "/verifyCode")
    public String smsverifyCode(@RequestParam String mobile, @RequestParam String code, HttpServletRequest request) {
        String sessionKey = "smscode_" + request.getSession().getId();
        String res = code.equals(request.getSession().getAttribute(sessionKey)) ? "1" : "0";
        request.getSession().removeAttribute(sessionKey);
        return res;
        // int state =verifyCode(mobile,code);
        // if(state==Integer.valueOf("1")){
        //     return "1";
        // }else {
        //     return "0";
        // }
    }

}

