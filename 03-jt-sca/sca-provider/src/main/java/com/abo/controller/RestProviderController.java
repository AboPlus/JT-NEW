package com.abo.controller;

import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Abo
 */
@RequestMapping("/provider/")
@RestController
public class RestProviderController {

    @DeleteMapping("{id}")
    public String doDeleteByid(@PathVariable Long ...id){
        // ...
        return Arrays.toString(id) + " is deleted ";
    }

    @PostMapping
    public Map<String,Object> doCreate(@RequestBody Map<String,Object> map){
        // ...
        System.out.println("provider.map= " + map);
        Map<String,Object> responseMap = new HashMap<>();
        responseMap.put("status", 200);
        responseMap.put("msg", "insert ok");
        return responseMap;
    }

    @PutMapping
    public Map<String,Object> doUpdate(@RequestBody Map<String,Object> map){
        System.out.println("provider.map= "+map);
        Map<String,Object> responseMap = new HashMap<>();
        responseMap.put("status", 200);
        responseMap.put("msg", "update ok");
        return responseMap;
    }

}
