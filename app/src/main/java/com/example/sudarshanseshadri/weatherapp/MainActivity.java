package com.example.sudarshanseshadri.weatherapp;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Iterator;

public class MainActivity extends AppCompatActivity {
    URL url;
    URLConnection urlConnection;
    InputStream inputStream;
    BufferedReader bufferedReader;

    TextView tempCurrent;
    TextView city;
    TextView quote;
    ImageView conditionCurrent;

    ImageView image1;
    ImageView image2;
    ImageView image3;
    ImageView image4;


    TextView text1;
    TextView text2;
    TextView text3;
    TextView text4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        tempCurrent=findViewById(R.id.textView_temp);
        city=findViewById(R.id.textView_city);
        quote=findViewById(R.id.textView_quote);
        conditionCurrent=findViewById(R.id.imageView_condition);

        text1=findViewById(R.id.textView_1);
        text2=findViewById(R.id.textView_2);
        text3=findViewById(R.id.textView_3);
        text4=findViewById(R.id.textView_4);

        image1=findViewById(R.id.imageView_1);
        image2=findViewById(R.id.imageView_2);
        image3=findViewById(R.id.imageView_3);
        image4=findViewById(R.id.imageView_4);


        String zipcode= getIntent().getStringExtra("ZIP");

        AsyncThread at = new AsyncThread();
        at.execute(zipcode);



    }

    public class AsyncThread extends AsyncTask<String, Void, String>
    {
        String info = "";

        @Override
        protected String doInBackground(String... strings) {
            //tempCurrent.setText("3˚C");
            String zip="08852";
            for (String s: strings) {
                zip=s;
            }


            try {
                url = new URL("http://api.openweathermap.org/data/2.5/forecast?zip="+zip+",us&APPID=40e95f00517dc507947dce2b6f96626a");
                //the easier-to-grade comment!


                urlConnection = url.openConnection();


                inputStream = urlConnection.getInputStream();

                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                bufferedReader = new BufferedReader(inputStreamReader);

                info = bufferedReader.readLine();
                Log.d("TAG", info);
            } catch (IOException e) {
                e.printStackTrace();
                Log.d("TAG", "ooooof");
            }

            return info;
        }

        @Override
        protected void onPostExecute(String s) {
            int far=0;

            try {
                JSONObject json=new JSONObject(info);
                String name = json.getJSONObject("city").getString("name");
                city.setText(name);
                JSONArray jsonArray = json.getJSONArray("list");



                JSONObject currentObject = jsonArray.getJSONObject(0);
                JSONObject main =currentObject.getJSONObject("main");

                String condition = currentObject.getJSONArray("weather").getJSONObject(0).getString("main");
                quote.setText(getQuote(far, condition));
                if (condition.equals("Rainy"))
                {
                    conditionCurrent.setImageResource(R.drawable.rain_white);
                }

                else if (condition.equals("Snow"))
                {
                    conditionCurrent.setImageResource(R.drawable.snow_white);
                }

                else if (condition.equals("Clear"))
                {
                    conditionCurrent.setImageResource(R.drawable.sun_white);
                }

                else if (condition.equals("Clouds"))
                {
                    conditionCurrent.setImageResource(R.drawable.cloud_white);
                }

                String temp = main.getString("temp");
                far=(int)((Double.parseDouble(temp)-273.15)*9/5)+32;
                tempCurrent.setText(far + "˚F");


                for (int i = 0; i < 4; i++) {

                    currentObject = jsonArray.getJSONObject(i);
                    main =currentObject.getJSONObject("main");
                    String conditionNew = currentObject.getJSONArray("weather").getJSONObject(0).getString("main");
                    String tempNew = main.getString("temp");


                    far=(int)((Double.parseDouble(tempNew)-273.15)*9/5)+32;


                    String time=currentObject.getString("dt_txt").substring(11, 13);
                    int timeInt=Integer.parseInt(time);
                    timeInt-=5;
                    String amOrPm=" AM";
                    if (timeInt<1)
                    {
                        timeInt+=12;
                    }
                    if (timeInt>12)
                    {
                        timeInt-=12;
                        amOrPm=" PM";
                    }

                    if (i==1) {
                        text1.setText(timeInt + amOrPm + ": " + far + "˚F");


                        if (conditionNew.equals("Rainy"))
                        {
                            image1.setImageResource(R.drawable.rain_black);
                        }
                        else if (condition.equals("Snow"))
                        {
                            image1.setImageResource(R.drawable.snow_black);
                        }

                        else if (conditionNew.equals("Clear"))
                        {
                            image1.setImageResource(R.drawable.sun_black);
                        }

                        else
                        {
                            image1.setImageResource(R.drawable.cloud_black);
                        }
                        Log.d("TAGI", "1");
                    }
                    else if (i==2) {
                        text2.setText(timeInt + amOrPm + ": " + far + "˚F");


                        if (conditionNew.equals("Rainy"))
                        {
                            image2.setImageResource(R.drawable.rain_black);
                        }
                        else if (condition.equals("Snow"))
                        {
                            image2.setImageResource(R.drawable.snow_black);
                        }
                        else if (conditionNew.equals("Clear"))
                        {
                            image2.setImageResource(R.drawable.sun_black);
                        }

                        else
                        {
                            image2.setImageResource(R.drawable.cloud_black);
                        }
                        Log.d("TAGI", "2");
                    }
                    else if (i==3) {
                        text3.setText(timeInt + amOrPm + ": " + far + "˚F");


                        if (conditionNew.equals("Rainy"))
                        {
                            image3.setImageResource(R.drawable.rain_black);
                        }
                        else if (condition.equals("Snow"))
                        {
                            image3.setImageResource(R.drawable.snow_black);
                        }

                        else if (conditionNew.equals("Clear"))
                        {
                            image3.setImageResource(R.drawable.sun_black);
                        }

                        else
                        {
                            image3.setImageResource(R.drawable.cloud_black);
                        }
                        Log.d("TAGI", "3");
                    }
                    else {
                        text4.setText(timeInt + amOrPm + ": " + far + "˚F");


                        if (conditionNew.equals("Rainy"))
                        {
                            image4.setImageResource(R.drawable.rain_black);
                        }

                        else if (conditionNew.equals("Clear"))
                        {
                            image4.setImageResource(R.drawable.sun_black);
                        }
                        else if (condition.equals("Snow"))
                        {
                            image4.setImageResource(R.drawable.snow_black);
                        }

                        else
                        {
                            image4.setImageResource(R.drawable.cloud_black);
                        }
                        Log.d("TAGI", "4");
                    }





                }







            } catch (Exception e) {
                e.printStackTrace();
            }

        }

        public String getQuote(int temp, String condition)
        {
            if(temp<32)
            {

                if ((int)(Math.random()*2)==1)
                {
                    return "PK Freeze!-Ness/Lucas";
                }
                else
                {
                    return "F-F-F-Freezinate!!-Riki";
                }

            }
            else if(temp<80)
            {

                if ((int)(Math.random()*2)==1)
                {
                    return "Charizard used Overheat!";
                }
                else
                {
                    return "That's it, fell the burn!";
                }
            }

            if (condition.equals("Rainy"))
            {
                if ((int)(Math.random()*2)==1)
                {
                    return "PK Rainstorm! - Lucas";
                }
                else
                {
                    return "It's raining like the Distant Planet!";
                }

            }

            if (condition.equals("Clear"))
            {
                if ((int)(Math.random()*2)==1)
                {
                    return "I'm really feeling good! - Shulk";
                }
                else
                {
                    return "Salute the Sun! -Wii Fit Trainer";
                }

            }

            if (condition.equals("Clouds"))
            {
                if ((int)(Math.random()*2)==1)
                {
                    return "Cloudy? Nerf Cloud!";
                }
                else
                {
                    return "Fox! Look out for those clouds!";
                }

            }


            return "PK...oof";
        }



//        public JSONArray getJSONArray(JSONObject j) throws JSONException {
//
//
//            for (int i = 0; i < jsonArray.length(); i++) {
//                JSONObject explrObject = jsonArray.getJSONObject(i);
//            }
//
//            return jsonArray;
//        }
    }
}
