package com.sytoss.gptgradle.services

import com.sytoss.gptgradle.data.RequestFile
import com.theokanning.openai.completion.chat.ChatCompletionRequest
import com.theokanning.openai.completion.chat.ChatMessage
import com.theokanning.openai.completion.chat.ChatMessageRole
import com.theokanning.openai.service.OpenAiService

object GptService {

    private val aptKey: String
    private val gptService: OpenAiService

    init {
        aptKey = javaClass.getResource("/key")?.readText() ?: throw IllegalStateException()
        gptService = OpenAiService(aptKey)
        println("aptKey = $aptKey ${2 + 3} ${"$"} ")
    }

    private fun genrateRequest(requestFile: List<RequestFile>): String {
        val requestBuilder = StringBuilder(
                """
                These are the code rules:
                1. BOM can't contain DTOs or another non-BOM elements;
                2. We work with repositories only in services.
                Let me know, what are the errors in code snippets below. Group warnings by classes.                    
                """
        )
        requestFile.forEach { (fileName, content): RequestFile ->
            requestBuilder.append(
                    """
                    Name: $fileName
                    Content:
                     ```
                    $content
                    ```
                    """
            )
        }
        return requestBuilder.toString()
    }

    fun generateReport(selectedFiles: List<RequestFile>): String{
        val request = ChatCompletionRequest.builder()
                .model("gpt-3.5-turbo")
                .messages(
                        listOf(
                                ChatMessage(
                                        ChatMessageRole.USER.value(), genrateRequest(selectedFiles)
                                )
                        )
                )
                .build()
            return gptService.createChatCompletion(request).choices[0].message.content
    }
}