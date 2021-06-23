package com.cy.jt.demo.comtroller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

/**
 * 通过此对象演示CRUD请求
 * restFul 规范=动作+url
 * @PutMapping      更新数据
 * @PatchMapping    更新部分数据
 * @author Abo
 */
@RestController
@RequestMapping("/demo/")
public class DemoController {
    /** @RequestMapping*/
    @DeleteMapping
    public String doDeleteById(Integer ...id){
        //.....
        return Arrays.toString(id) + "is deleted";
    }
}
