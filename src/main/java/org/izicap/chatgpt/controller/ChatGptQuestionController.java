package org.izicap.chatgpt.controller;


import io.github.flashvayne.chatgpt.service.ChatgptService;
import org.izicap.chatgpt.dtos.ResultModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.PrintWriter;


@CrossOrigin
@RestController
public class ChatGptQuestionController {


   private final ChatgptService chatgptService;


    @Autowired
    public ChatGptQuestionController(ChatgptService chatgptService) {
        this.chatgptService = chatgptService;
    }

 /*   @Autowired
    privateChatgptService chatgptService; */

    @GetMapping("/send")
    public <HttpServletRequest> ResultModel sendMessage(HttpServletRequest request, @RequestParam String message) {

        System.out.println("start ex");
        if (message == null || message.trim().isEmpty()) {
            return ResultModel.fail("Message cannot be blank");
        }

        try {
            String responseMessage = chatgptService.sendMessage(message);
            //saveToCsvFile(message,responseMessage,"test.txt");
            return ResultModel.success(responseMessage);
        } catch (Exception e) {
            return new ResultModel(500, "Error", e.getMessage());
        }
    }

    public static void saveToCsvFile(String q,String result,String filepath){
        try {
            FileWriter fw=new FileWriter(filepath,true);
            BufferedWriter bw=new BufferedWriter(fw);
            PrintWriter pw=new PrintWriter(bw);

            pw.println(q+","+result);
        }catch (Exception e){
                e.printStackTrace();
             }
    }
}