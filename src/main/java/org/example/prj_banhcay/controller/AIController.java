package org.example.prj_banhcay.controller;

import org.example.prj_banhcay.DTO.request.ChatRequest;
import org.example.prj_banhcay.DTO.response.ChatResponse;
import org.example.prj_banhcay.service.GeminiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/ai")
public class AIController {

    @Autowired
    private GeminiService geminiService;

    @PostMapping("/chat")
    public ChatResponse chat(@RequestBody ChatRequest request){

        return new ChatResponse(
                geminiService.askGemini(request.getQuestion())
        );
    }

}