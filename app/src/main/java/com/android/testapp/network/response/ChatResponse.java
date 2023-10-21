package com.android.testapp.network.response;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ChatResponse {
    @SerializedName("id")
    private String id;

    @SerializedName("object")
    private String object;

    @SerializedName("created")
    private Long created;

    @SerializedName("model")
    private String model;

    @SerializedName("usage")
    private Usage usage;

    @SerializedName("choices")
    private List<Choice> choices;

    public String getId() {
        return id;
    }

    public String getObject() {
        return object;
    }

    public Long getCreated() {
        return created;
    }

    public String getModel() {
        return model;
    }

    public Usage getUsage() {
        return usage;
    }

    public List<Choice> getChoices() {
        return choices;
    }

    public static class Usage {
        @SerializedName("prompt_tokens")
        private Long promptTokens;

        @SerializedName("completion_tokens")
        private Long completionTokens;

        @SerializedName("total_tokens")
        private Long totalTokens;

        public Long getPromptTokens() {
            return promptTokens;
        }

        public Long getCompletionTokens() {
            return completionTokens;
        }

        public Long getTotalTokens() {
            return totalTokens;
        }
    }

    public static class Choice {
        @SerializedName("message")
        private Message message;

        @SerializedName("finish_reason")
        private String finishReason;

        public Message getMessage() {
            return message;
        }

        public String getFinishReason() {
            return finishReason;
        }
    }

    public static class Message {
        @SerializedName("role")
        private String role;

        @SerializedName("content")
        private String content;

        public String getRole() {
            return role;
        }

        public String getContent() {
            return content;
        }
    }
}

