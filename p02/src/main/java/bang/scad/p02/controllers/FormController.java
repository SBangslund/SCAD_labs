package bang.scad.p02.controllers;

import java.net.InetAddress;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import bang.scad.p02.models.Feedback;

@Controller
public class FormController {

    @GetMapping("/feedback")
    public String feedbackForm(Model model) {
        model.addAttribute("feedback", new Feedback());
        return "feedback";
    }

    @PostMapping("/feedback")
    public String feedbackSubmit(@ModelAttribute Feedback feedback, Model model) {
        model.addAttribute("feedback", feedback);
        return "result";
    }
}
