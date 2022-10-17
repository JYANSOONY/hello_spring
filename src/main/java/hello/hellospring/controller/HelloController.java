package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


//testtest
@Controller
public class HelloController {
    @GetMapping("hello")
    public String hello(Model model){
        model.addAttribute("data","hello!");
        return "hello";
        /** 리턴 값을 문자로 반환하면 뷰 리졸버가 화면을 찾아 처리하는데
         스프링 부트 템플릿엔지 기본 뷰 매핑은
         >> resources: template/ +{ViewName}+.html
         template/hello 라는 html 파일을 찾아서 넘겨라*/
    }

    @GetMapping("hello-mvc")
    public String hellMvc(@RequestParam(value = "name")String name, Model model){
        model.addAttribute("name", name);
    return "hello-template";
    }

    @GetMapping("hello-string")
    @ResponseBody // http 응답 바디에 리턴 데이터를 직접 넣어주겠다
    public String helloString(@RequestParam("name")String name){
        return "hello"+name; // hello spring 으로 바뀌겠지? html을 보여주는게 아니라 이 문자 리턴 값을 걍 보내주는거
    }

    @GetMapping("hello-api")
    @ResponseBody //리턴 값이 객체이면 JsonConvertor가 동작 단순 문자열이라면 StringConvertor가 동작
    public Hello helloApi(@RequestParam("name") String name){
        Hello hello = new Hello();
        hello.setName(name);
        return hello; //json방식으로 http 응답 바디에 객체 데이터가 넣어짐
    }

    static class Hello{
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
