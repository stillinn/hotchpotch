package com.stillinn.hotchpotch.controller;

/**
 * @author stillinn
 * Created on 2023-07-02
 */

import com.stillinn.hotchpotch.toolkit.rateLimit.SentinelLimitAnnotation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/test")
public class TestController {

    @RequestMapping("/limit")
    @ResponseBody
    @SentinelLimitAnnotation(resourceName = "controller", limitCount = 2)
    public String limit() {
        return "OK";
    }
}
