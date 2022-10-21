package com.gl4.tp1

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.ActivityResultCallback
import androidx.activity.result.contract.ActivityResultContracts
import java.net.URI

class WelcomeActivity : AppCompatActivity(), View.OnClickListener {
    lateinit var txtWelcome : TextView;
    lateinit var imgWelcome : ImageView;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)
        val email = intent.getStringExtra("email");
        txtWelcome=findViewById(R.id.textView);
        imgWelcome=findViewById(R.id.imageView);
        txtWelcome.text= "Welcome $email";
    }

    override fun onClick(p0: View?) {

        if( p0?.id == R.id.btnLoad){
            val intentGallery = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
            pickImage.launch(intentGallery);

        }
    }

    val pickImage =registerForActivityResult(ActivityResultContracts.StartActivityForResult()){ result->
        if(result.resultCode == RESULT_OK && result.data != null){
            val selectedImg : Uri? = result.data?.data;
            imgWelcome.setImageURI(selectedImg);

        }
    }

}