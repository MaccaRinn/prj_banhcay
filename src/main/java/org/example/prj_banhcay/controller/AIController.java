package org.example.prj_banhcay.controller;

import org.example.prj_banhcay.DTO.request.ChatRequest;
import org.example.prj_banhcay.DTO.response.ChatResponse;
import org.example.prj_banhcay.service.GeminiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/ai")
@CrossOrigin(origins = "https://www.banhcaynguvi.com", allowCredentials = "true")
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