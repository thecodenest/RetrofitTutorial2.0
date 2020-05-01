package com.rajendra.retrofittutorial;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.Gson;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    TextView responseText;
    Button getButton, postButton, getPostById, patchRequest, deleteRequest;
    EditText getId;
    ApiInterface apiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        responseText = findViewById(R.id.response_text);
        getButton = findViewById(R.id.get);
        postButton = findViewById(R.id.post);
        postButton = findViewById(R.id.post);
        getId = findViewById(R.id.getid);
        getPostById = findViewById(R.id.get_dynamic_post);
        patchRequest = findViewById(R.id.patch);
        deleteRequest = findViewById(R.id.delete);


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();


        apiInterface = retrofit.create(ApiInterface.class);


        getButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Call<List<Post>> call = apiInterface.getPost();


                call.enqueue(new Callback<List<Post>>() {
                    @Override
                    public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {

                        if (!response.isSuccessful()) {
                            responseText.setText("Data is not getting from server." + response.code());
                            return;
                        }

                        List<Post> postList = response.body();

                        for (Post post : postList) {

                            String data = "";
                            data += "ID " + post.getId() + "\n";
                            data += "UserID " + post.getUserId() + "\n";
                            data += "Title " + post.getTitle() + "\n";
                            data += "Body " + post.getBody() + "\n\n";

                            responseText.append(data);

                        }

                    }

                    @Override
                    public void onFailure(Call<List<Post>> call, Throwable t) {
                        responseText.setText(t.toString());
                    }
                });


            }
        });

        postButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final PostData postData = new PostData(1, "Welcome222 to the code nest", "The22 code Nest");

                Call<PostData> call2 = apiInterface.postDataToServer(postData);

                call2.enqueue(new Callback<PostData>() {
                    @Override
                    public void onResponse(Call<PostData> call, Response<PostData> response) {

                        String data = "";
                        data += "response " + response.code() + "\n";
                        data += "UserID " + postData.getUserId() + "\n";
                        data += "Title " + postData.getTitle() + "\n";
                        data += "Body " + postData.getBody() + "\n\n";

                        responseText.append(data);


                    }

                    @Override
                    public void onFailure(Call<PostData> call, Throwable t) {
                        responseText.append(t.toString());

                    }
                });

            }
        });


        getPostById.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String id = getId.getText().toString();

                Call<List<Post>> call = apiInterface.getPostById(Integer.parseInt(id));


                call.enqueue(new Callback<List<Post>>() {
                    @Override
                    public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {

                        if (!response.isSuccessful()) {
                            responseText.setText("Data is not getting from server." + response.code());
                            return;
                        }

                        List<Post> postList = response.body();

                        for (Post post : postList) {

                            String data = "";
                            data += "ID " + post.getId() + "\n";
                            data += "UserID " + post.getUserId() + "\n";
                            data += "Title " + post.getTitle() + "\n";
                            data += "Body " + post.getBody() + "\n\n";

                            responseText.append(data);

                        }

                    }

                    @Override
                    public void onFailure(Call<List<Post>> call, Throwable t) {
                        responseText.setText(t.toString());
                    }
                });



            }
        });


        patchRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                final PostData postData = new PostData(1, "this is patch request","this is patch request");

                Call<PostData> call2 = apiInterface.postDataToServer(postData);

                call2.enqueue(new Callback<PostData>() {
                    @Override
                    public void onResponse(Call<PostData> call, Response<PostData> response) {

                        String data = "";
                        data += "response " + response.code() + "\n";
                        data += "UserID " + postData.getUserId() + "\n";
                        data += "Title " + postData.getTitle() + "\n";
                        data += "Body " + postData.getBody() + "\n\n";

                        responseText.append(data);


                    }

                    @Override
                    public void onFailure(Call<PostData> call, Throwable t) {
                        responseText.append(t.toString());

                    }
                });

            }
        });

        deleteRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Call<Void> call2 = apiInterface.deletePost(1);

                call2.enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {

                        responseText.setText("Deleted"+ response.code());

                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {

                        responseText.setText("Deleted"+ t.toString());


                    }
                });

            }
        });



    }}
