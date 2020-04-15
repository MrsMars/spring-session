package com.aoher.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.session.ExpiringSession;
import org.springframework.session.FindByIndexNameSessionRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.util.Collection;

import static org.springframework.session.FindByIndexNameSessionRepository.PRINCIPAL_NAME_INDEX_NAME;

@Controller
public class TestController {

    @Autowired
    private FindByIndexNameSessionRepository<? extends ExpiringSession> sessions;

    @GetMapping("/")
    public String listSessions(Principal principal, HttpSession session, Model model) {
        Collection<? extends ExpiringSession> userSessions =
                sessions.findByIndexNameAndIndexValue(
                        PRINCIPAL_NAME_INDEX_NAME,
                        principal.getName()
                ).values();

        model.addAttribute("sessions", userSessions);
        model.addAttribute("currSessionId", session.getId());
        return "index";
    }
}
