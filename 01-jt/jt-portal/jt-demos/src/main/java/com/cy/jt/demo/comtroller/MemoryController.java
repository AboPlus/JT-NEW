package com.cy.jt.demo.comtroller;

import com.cy.jt.demo.service.MemoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @author Abo
 */
@RequestMapping("/memory/")
@RestController
public class MemoryController {

    @Autowired
    private MemoryService memoryService;

    @GetMapping
    public List<Map<String,Object>> doRetrieve(){
        return memoryService.list();
    }
}
