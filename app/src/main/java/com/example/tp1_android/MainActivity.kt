package com.example.tp1_android

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    lateinit var textName : EditText;
    lateinit var textPrenom : EditText;
    lateinit var textAdresse : EditText;
    lateinit var sizeSelect : Spinner;
    lateinit var ingredientSelect: Spinner;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        textName = findViewById(R.id.textName)
        sizeSelect = findViewById(R.id.spinner)
        ingredientSelect = findViewById(R.id.spinner2)
        val tailles = resources.getStringArray(R.array.PizzaTailles)
        val ingredients = resources.getStringArray(R.array.Ingredients)
        val adapter = ArrayAdapter(this,
            android.R.layout.simple_spinner_item, tailles)
        val adapter2 = ArrayAdapter(this,
            android.R.layout.simple_spinner_item, ingredients)
        sizeSelect.adapter = adapter;
        ingredientSelect.adapter = adapter2;
    }

    fun login(view: View) {
        if(!valid())
        Toast
            .makeText(
                this,
                "Please make sure to fill all the fields",
                Toast.LENGTH_SHORT)
            .show();
    }

    private fun valid() : Boolean {
        return textName.text.length > 0 && textAdresse.text.length > 0 && textPrenom.text.length > 0;
    }

    private fun sendEmail(recipient: String, subject: String, message: String) {
        /*ACTION_SEND action to launch an email client installed on your Android device.*/
        val mIntent = Intent(Intent.ACTION_SEND)
        /*To send an email you need to specify mailto: as URI using setData() method
        and data type will be to text/plain using setType() method*/
        mIntent.data = Uri.parse("mailto:")
        mIntent.type = "text/plain"
        // put recipient email in intent
        /* recipient is put as array because you may wanna send email to multiple emails
           so enter comma(,) separated emails, it will be stored in array*/
        mIntent.putExtra(Intent.EXTRA_EMAIL, arrayOf(recipient))
        //put the Subject in the intent
        mIntent.putExtra(Intent.EXTRA_SUBJECT, subject)
        //put the message in the intent
        mIntent.putExtra(Intent.EXTRA_TEXT, message)


        try {
            //start email intent
            startActivity(Intent.createChooser(mIntent, "Choose Email Client..."))
        }
        catch (e: Exception){
            //if any thing goes wrong for example no email client application or any exception
            //get and show exception message
            Toast.makeText(this, e.message, Toast.LENGTH_LONG).show()
        }

    }
}