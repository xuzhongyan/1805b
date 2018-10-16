package cn.tedu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import cn.tedu.client.EurekaServiceFeign;

@RestController
public class HelloController {
    @Autowired
	private EurekaServiceFeign feign;
    
    @RequestMapping("/hello/{name}")
    @HystrixCommand(fallbackMethod="helloFallback")
    public String hello(@PathVariable String name){
    	return feign.hello(name);
    }
    
    //shibai是，断路器自动回调这个方法，返回默认值
    //方法名字和fallback相同
    public String helloFallback(String name){
		return "tony";	//失败调用时，返回默认值
	}
}
