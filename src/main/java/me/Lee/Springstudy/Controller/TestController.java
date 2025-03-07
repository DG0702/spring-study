package me.Lee.Springstudy.Controller;

import me.Lee.Springstudy.domain.Member;
import me.Lee.Springstudy.Service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


import java.util.List;

@RestController
public class TestController {

    @Autowired
    TestService testService;

    @GetMapping("/test")
    public String test(){
        return "Hello World";
    }

    @GetMapping("/test1")
    public List<Member> getAllMembers(){
        List<Member> members = testService.getAllMembers();
        return members;
    }


}
