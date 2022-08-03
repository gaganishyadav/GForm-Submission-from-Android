package com.example.formsubmit3;

import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

import com.example.formsubmit3.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private EditText editText_name, editText_email, editText_mnumber, editText_age;
    private Button button_register;
    private String name, email, mobile, age;
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(binding.navView, navController);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://docs.google.com/forms/d/")
                .build();
        final SendResponse spreadsheetWebService = retrofit.create(SendResponse.class);
        editText_name = (EditText) findViewById(R.id.editText_enter_name);
        editText_email = (EditText) findViewById(R.id.editText_enter_email);
        editText_mnumber = (EditText) findViewById(R.id.editText_enter_mnumber);
        editText_age = (EditText) findViewById(R.id.editText_enter_age);
        button_register = (Button) findViewById(R.id.button_register);
        button_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name = editText_name.getText().toString();
                Log.d("Name", "Submitted. " + name);
                email = editText_email.getText().toString();
                Log.d("Email", "Submitted. " + email);
                mobile = editText_mnumber.getText().toString();
                Log.d("Number", "Submitted. " + mobile);
                age = editText_age.getText().toString();
                Log.d("Age", "Submitted. " + age);
                if (name.length() > 0 && email.length() > 0 && mobile.length() > 0 && age.length() > 0) {

                    Call<Void> sendResponseCall = spreadsheetWebService.sendResponse(name, email, mobile, age);
                    sendResponseCall.enqueue(callCallback);
                    Toast.makeText(getApplicationContext(), "Sent Details", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Please Enter Valid Details", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private final Callback<Void> callCallback = new Callback<Void>() {
        @Override
        public void onResponse(Call<Void> call, Response<Void> response) {
            Log.d("XXX", "Submitted. " + response);
        }

        @Override
        public void onFailure(Call<Void> call, Throwable t) {
            Log.e("XXX", "Failed", t);
        }
    };
}