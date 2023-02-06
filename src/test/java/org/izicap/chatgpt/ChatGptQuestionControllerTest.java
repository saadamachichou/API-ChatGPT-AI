package org.izicap.chatgpt;

import org.izicap.chatgpt.controller.ChatGptQuestionController;
import org.izicap.chatgpt.dtos.ResultModel;
import io.github.flashvayne.chatgpt.service.ChatgptService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ChatGptQuestionControllerTest {
    @Mock
    private ChatgptService chatgptService;

    @InjectMocks
    private ChatGptQuestionController chatGptQuestionController;

    @Before
    public void setup() {
        when(chatgptService.sendMessage(anyString())).thenReturn("mock response");
    }

    @Test
    public void testSendMessageWithValidMessage() {
        ResultModel<String> result = chatGptQuestionController.sendMessage(null, "valid message");
        assertEquals(200, result.getCode().intValue());
        assertEquals("success", result.getMessage());
        assertEquals("mock response", result.getData());
    }

    @Test
    public void testSendMessageWithBlankMessage() {
        ResultModel<String> result = chatGptQuestionController.sendMessage(null, "");
        assertEquals(0, result.getCode().intValue());
        assertEquals("fail", result.getMessage());
        assertEquals("Message cannot be blank", result.getData());
    }
}
