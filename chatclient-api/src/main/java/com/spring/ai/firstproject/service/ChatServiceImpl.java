package com.spring.ai.firstproject.service;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

@Service
public class ChatServiceImpl implements ChatService {

    private ChatClient chatClient;

    @Value("classpath:/prompts/user-message.st")
    private Resource userMessage;

    @Value("classpath:/prompts/system-message.st")
    private Resource systemMessage;

    public ChatServiceImpl(ChatClient.Builder builder) {
        this.chatClient = builder.build();
    }

    @Override
    public String chat(String query) {
//    @Override
//        public Tut chat(String query) {

        // String prompt="Tell me about virat kholi";

        //call the llm for response
//        String content=chatClient
//                 .prompt()
//                 .user(prompt)
//                 .system("As as expert in cricket")
//                 .call()
//                 .content();

//        //Default option
//        Prompt prompt1=new Prompt(query, OpenAiChatOptions.builder()
//                .model("gpt-4o-mini")
//                .temperature(0.3)
//                .maxTokens(100)
//                .build()
//        );


//        Prompt prompt1=new Prompt(query);
//        var content=chatClient
//                .prompt(prompt1)
//                .call()
//                .content();

//       Lecture 11 Dynamic Template and Prompt
        Prompt prompt1 = new Prompt(query);
//        modify this prompt and extra thing to prompt make it more interactive
        String queryStr = "As an expert in coding and promgamming. Always write program in java.Now reply for this question :{query}";
        var content = chatClient
                .prompt()
                .user(u -> u.text(queryStr).param("query", query))
                .call()
                .content();

//        Prompt prompt1=new Prompt(query);
//        var metadata=chatClient
//                .prompt(prompt1)
//                .call()
//                .chatResponse()
//                .getMetadata();
//        System.out.println(metadata);

//        Prompt prompt1=new Prompt(query);
//        var content=chatClient
//                .prompt(prompt1)
//                .call()
//                .chatResponse()
//                .getResult()
//                .getOutput()
//                .getText();
//        System.out.println(content);

//        Prompt prompt1=new Prompt(query);
//        Tut tutorial=chatClient
//                .prompt(prompt1)
//                .call()
//                .entity(Tut.class);

        return content;
    }


    public String chatTemplet() {

      /*  //first step
        PromptTemplate strTemplet = PromptTemplate.builder().template("What is {techName}? tell me also about {techExample}").build();

        //render the templet
        String renderedMessage = strTemplet.render(Map.of(
                "techName", "Spring",
                "techExample", "Spring Exception"
        ));

        Prompt prompt = new Prompt(renderedMessage);*/

        /*var systemPromptTemplate = SystemPromptTemplate.builder()
                .template("You are a helpful coding assistece, You are an expert in coding.")
                .build();

        var systemMessage = systemPromptTemplate.createMessage();

        var promptTemplate = PromptTemplate.builder()
                .template("What is {techName}? tell me also about {techExample}")
                .build();

        var userMessage = promptTemplate.createMessage(Map.of(
                "techName", "Spring",
                "techExample", "Spring Exception"
        ));

        Prompt prompt = new Prompt(systemMessage, userMessage);*/



        /*return this.chatClient
                .prompt()
                .system(system->
                        system.text("You are a helpful coding assistece, You are an expert in coding."))
                .user(user->
                        user.text("What is {techName}? tell me also about {techExample}")
                                .param("techName", "Spring")
                                .param("techExample", "Spring Exception"))
                .call()
                .content();*/

        return this.chatClient
                .prompt()
                .system(system ->
                        system.text(this.systemMessage))
                .user(user ->
                        user.text(this.userMessage).param("concept", "Python iteration"))
                .call()
                .content();
    }
}
