package com.example.chucknorrisquotes;

import androidx.appcompat.app.AppCompatActivity;
import com.example.chucknorrisquotes.entities.ChuckNorrisResponse;
import com.example.chucknorrisquotes.entities.ChuckNorrisService;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

//SUMMARY
    //Includes random generated chuck norris quotes categorised by dev called from chuck norris API
    //Implemented button to launch load different quote from Chuck Norris
    //If user clicks image of Chuck Norris, an onclick listener directs the user to the youtube detail page
        //Detail page shows a video of chuck norris, which they can play by clicking the "play button".
        //Video is grabbed through the youtube android player API
            //connection is configured through API jar in library, and in the youtube config class which grabs the API key

public class MainActivity extends AppCompatActivity {

    TextView quoteText;
    Button showJoke;
    ImageView chuck;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        quoteText = findViewById(R.id.quoteText);
        showJoke = findViewById(R.id.showJoke);
        chuck = findViewById(R.id.chuck);

        setQuote();

        //sets up click listener on button to call for new quote
        showJoke.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //sets text to show progress of quote loading in..
                quoteText.setText("Loading another inspirational quote for you...");
                setQuote();
            }
        });

        setQuote();

        //on click listener to move to different page
        chuck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), YoutubeDetail.class);
                startActivity(intent);
            }
        });
    }
    public void setQuote(){
        //sets up retrofit
        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl("https://api.chucknorris.io/jokes/")
                .addConverterFactory(GsonConverterFactory.create());
        Retrofit retrofit = builder.build();
        ChuckNorrisService service = retrofit.create(ChuckNorrisService.class);
        Call<ChuckNorrisResponse> call = service.getQuote("dev");

        //implements enqueue to allow for execution of api
        call.enqueue(new Callback<ChuckNorrisResponse>() {
            @Override
            public void onResponse(Call<ChuckNorrisResponse> call, Response<ChuckNorrisResponse> response) {
                ChuckNorrisResponse quote = response.body();
                quoteText.setText(quote.getValue());
            }
            @Override
            public void onFailure(Call<ChuckNorrisResponse> call, Throwable t) {
            }
        });
    }
}
