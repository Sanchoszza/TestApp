package com.android.testapp.fragment;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.android.testapp.R;
import com.android.testapp.network.ChatRequest;
import com.android.testapp.network.response.ChatResponse;
import com.android.testapp.view.ChatService;
import com.google.android.material.textfield.TextInputEditText;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ChatBotFragment extends Fragment {

    private TextView responseTV;
    private TextView questionTV;
    private TextInputEditText queryEdt;
    private ChatService chatGPTService;

    public static ChatBotFragment newInstance() {
        ChatBotFragment fragment = new ChatBotFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_chat_bot, container, false);

        responseTV = view.findViewById(R.id.idTVResponse);
        questionTV = view.findViewById(R.id.idTVQuestion);
        queryEdt = view.findViewById(R.id.idEdtQuery);

        queryEdt.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEND) {
                    responseTV.setText("Please wait..");
                    if (queryEdt.getText().toString().length() > 0) {
                        getResponse(queryEdt.getText().toString());
                    } else {
                        Toast.makeText(requireContext(), "Please enter your query..", Toast.LENGTH_SHORT).show();
                    }
                    return true;
                }
                return false;
            }
        });

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.openai.com/v1/completions/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        chatGPTService = retrofit.create(ChatService.class);

        return view;
    }

    private void getResponse(String query) {
        questionTV.setText(query);
        queryEdt.setText("");

        ChatRequest request = new ChatRequest(query);
        String apiKey = "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";

        Call<ChatResponse> call = chatGPTService.getCompletion(apiKey, request);
        call.enqueue(new Callback<ChatResponse>() {
            @Override
            public void onResponse(Call<ChatResponse> call, Response<ChatResponse> response) {
                if (response.isSuccessful()) {
                    ChatResponse completion = response.body();
                    if (completion != null && !completion.getChoices().isEmpty()) {
                        String responseMsg = completion.getChoices().get(0).getMessage().getContent();
                        responseTV.setText(responseMsg);
                    }
                } else {
                    Toast.makeText(requireContext(), "Error in API response", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ChatResponse> call, Throwable t) {
                Toast.makeText(requireContext(), "API call failed", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
