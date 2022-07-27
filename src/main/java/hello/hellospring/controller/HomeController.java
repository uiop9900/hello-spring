package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home() {  //1. 요청 2.mapping 찾기 3. 없으면 정적html 로 간다.
        return "home";
    }
}
