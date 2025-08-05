package org.example.spring_jpa.controller.web;

import org.springframework.ui.Model;
import org.example.spring_jpa.service.PublisherService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/publishers")
public class PublisherController {
    private final PublisherService publisherService;

    public PublisherController(PublisherService publisherService) {
        this.publisherService = publisherService;
    }

    @GetMapping
    public String publishers(Model model) {
        model.addAttribute("publishers",publisherService.findAll());
        return "publishers";
    }
}
