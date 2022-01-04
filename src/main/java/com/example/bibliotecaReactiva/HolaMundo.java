package com.example.bibliotecaReactiva;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HolaMundo {

    @RequestMapping("/holamundo")
    public String holamundo(){
        return "Hola mundo";
    }
}
