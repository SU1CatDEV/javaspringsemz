package su1cat.sem7.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PermissionedController {
    @GetMapping("/public-data")
    public String publicData() {
        return "public.html";
    }

    @GetMapping("/private-data")
    public String privateData() {
        return "private.html";
    }
}
