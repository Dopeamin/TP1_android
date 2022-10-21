package com.gl4.tp1

import android.app.SearchManager
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast

class MainActivity : AppCompatActivity(), View.OnClickListener {

    lateinit var txtEmail : EditText;
    lateinit var txtPass : EditText;


    override fun onCreate(savedInstanceState: Bundle?) {
        print("hello");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtEmail = findViewById(R.id.editTextEmail);
        txtPass = findViewById(R.id.editTextPassword);

    }



    override fun onClick(p0: View?) {
        var email = txtEmail.text.toString();
        var password = txtPass.text.toString();
        if( p0?.id == R.id.btnLogin){
            if( email == "gl4@insat.tn" && password == "insat2022"){
                println("welcome");
                val text = "Welcome home master";
                val duration = Toast.LENGTH_SHORT;

                val toast = Toast.makeText(applicationContext, text, duration);
                toast.show();
                val intent = Intent(p0.context,WelcomeActivity::class.java);
                intent.putExtra("email",email);
                startActivity(intent);
            }else{
                println("nah");
                val text = "incorrect coordinates";
                val duration = Toast.LENGTH_SHORT;

                val toast = Toast.makeText(applicationContext, text, duration);
                toast.show();
            }

        }
    }
}