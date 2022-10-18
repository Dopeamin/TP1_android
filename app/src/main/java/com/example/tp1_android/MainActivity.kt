package com.example.tp1_android

import android.content.Intent
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import androidx.annotation.RequiresApi
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class MainActivity : AppCompatActivity() {
    lateinit var textName: EditText;
    lateinit var textPrenom: EditText;
    lateinit var textAdresse: EditText;
    lateinit var sizeSelect: Spinner;
    lateinit var ingredientSelect: Spinner;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        textName = findViewById(R.id.textName)
        textPrenom = findViewById(R.id.textPrenom)
        textAdresse = findViewById(R.id.textPostalAdress)
        sizeSelect = findViewById(R.id.spinner)
        ingredientSelect = findViewById(R.id.spinner2)
        val tailles = resources.getStringArray(R.array.PizzaTailles)
        val ingredients = resources.getStringArray(R.array.Ingredients)
        val adapter = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_item, tailles
        )
        val adapter2 = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_item, ingredients
        )
        sizeSelect.adapter = adapter;
        ingredientSelect.adapter = adapter2;
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun login(view: View) {
        if (!valid())
            Toast
                .makeText(
                    this,
                    "Please make sure to fill all the fields",
                    Toast.LENGTH_SHORT
                )
                .show();

        sendEmail(
            "hamdouni.medamin@gmail.com",
            "Commande : " + LocalDateTime.now()
                .format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")),
            toString()
        )
    }

    private fun valid(): Boolean {
        println(sizeSelect.selectedItem.toString());
        return textName.text.length > 0 && textAdresse.text.length > 0 && textPrenom.text.length > 0 && sizeSelect.selectedItem.toString().length > 0 && ingredientSelect.selectedItem.toString().length > 0;
    }

    override fun toString(): String {
        return "Nom : " + textName.text + "/n" + "Prenom : " + textPrenom.text + "/n" + "Adresse : " + textAdresse + "/n" + "Taille Pizza : " + sizeSelect.selectedItem.toString() + "Ingredients" + ingredientSelect.selectedItem.toString();
    }

    private fun sendEmail(recipient: String, subject: String, message: String) {
        /*ACTION_SEND action to launch an email client installed on your Android device.*/
        val mIntent = Intent(Intent.ACTION_SEND)
        /*To send an email you need to specify mailto: as URI using setData() method
        and data type will be to text/plain using setType() method*/
        mIntent.data = Uri.parse("mailto:")
        mIntent.type = "message/rfc822"
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
        } catch (e: Exception) {
            //if any thing goes wrong for example no email client application or any exception
            //get and show exception message
            Toast.makeText(this, e.message, Toast.LENGTH_LONG).show()
        }

    }
}