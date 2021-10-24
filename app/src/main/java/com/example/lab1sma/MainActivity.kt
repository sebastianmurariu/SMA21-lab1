package com.example.lab1sma

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.R.id
import android.content.DialogInterface
import androidx.appcompat.app.AlertDialog
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var btnClick : Button
    private lateinit var nameTV : TextView
    private lateinit var nameET : EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initFields()
    }

    private fun initFields(){
        btnClick = findViewById(R.id.bClick)
        nameTV   = findViewById(R.id.tName)
        nameET   = findViewById(R.id.eName)
        btnClick.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        if (nameET.text.isBlank()) Toast.makeText(this, "Please enter a name", Toast.LENGTH_SHORT)
            .show()
        else {
            nameTV.setText("Greetings ${nameET.text}")
            var dialogBuilder = AlertDialog.Builder(this)
            dialogBuilder.setMessage("Hello ${nameET.text}")
            dialogBuilder.setPositiveButton("Ok", DialogInterface.OnClickListener { dialog, _ ->
                dialog.cancel()
                Toast.makeText(this, "Positive button", Toast.LENGTH_SHORT)
                    .show()
            })
            dialogBuilder.setNegativeButton("Cancel", DialogInterface.OnClickListener { dialog, _ ->
                dialog.cancel()
                Toast.makeText(this, "Positive button", Toast.LENGTH_SHORT)
                    .show()
            })

            val dialog = dialogBuilder.create()
            dialog.show()
        }
    }
}