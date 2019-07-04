package com.central.rest;

import org.apache.skywalking.apm.toolkit.trace.Trace;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: miv
 * @Date: 2019-06-13 18:46
 * @Web: www.xiejx.cn
 * @Email: 787824374@qq.com
 * @Description:
 */
@RestController
public class LogController {

    @Trace
    @GetMapping("log")
    public Object log() {
        return "log";
    }
}
