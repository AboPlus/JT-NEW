package com.cy.jt.demo.comtroller;

import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

/**
 * 通过此对象演示CRUD请求
 * restFul 规范=动作+url
 * C(Create) R(Retrieve) U(Update) D(Delete)
 * @PutMapping      更新数据
 * @PatchMapping    更新部分数据
 * @author Abo
 */
// @Lazy   //延迟加载
@RestController
@RequestMapping("/demo/")
public class DemoController {

    public DemoController(){
        System.out.println("DemoController()");
    }

    /** @RequestMapping */
    @DeleteMapping("{id}")
    public String doDeleteById(@PathVariable Integer ...id){
        //.....
        return Arrays.toString(id) + "is deleted";
    }


    // J.U.C 包下的线程安全的对象(提供了对长整型数据的原子操作，低层CAS算法)
    private AtomicLong counter = new AtomicLong(1);
    @GetMapping("{id}")
    public String doRetrieveById(@PathVariable Integer id) throws InterruptedException {
        String tName = Thread.currentThread().getName();
        System.out.println(counter.getAndIncrement());
        TimeUnit.SECONDS.sleep(3);
        return tName + id;
    }

    /**
     * 请求路径 http://ip:port/demo/
     * 请求内容类型 Content-Type:Application/json
     * 请求格式：{"id":1,"msg":"helloWorld"}
     * @param map
     * @return
     */
    @PostMapping
    public String doCreate(@RequestBody Map<String,Object> map){
        return map.toString() + " is created";
    }

    @PutMapping
    public String doUpdate(@RequestBody Map<String,Object> map){

        return map.toString() + " is updated";
    }

    public Integer count(Integer val){
        return null;
    }

}
