package com.codein.springbootjunit;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lxh on 2018/3/29.
 */
@RestController
@RequestMapping("hello")
public class HelloController {

    @GetMapping()
    @ResponseBody
    public String hello(@RequestParam(required = false) String name) {
        if (name == null || name.trim() == "") {
            return "hello";
        }
        return String.format("hello, %s", name);
    }

    @GetMapping(value = "json", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public Hello helloJson() {
        return new Hello("Michale", 25);
    }

    @GetMapping(value = "list/json", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public Map<String, Object> helloJsonList() {
        Map<String, Object> data = new HashMap<>();
        List<Hello> list = new ArrayList<Hello>();
        Hello hello1 = new Hello("wow", 19);
        list.add(hello1);
        Hello hello2 = new Hello("amazing", 28);
        list.add(hello2);

        data.put("total", list.size());
        data.put("list", list);

        return data;
    }

}

class Hello {

    private String name;

    private int age;

    public Hello() {
    }

    public Hello(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
