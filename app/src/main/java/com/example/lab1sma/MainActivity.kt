package com.example.lab1sma

import android.os.Bundle
import android.R.id
import android.content.DialogInterface
import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Build
import android.util.Log
import android.view.View
import android.widget.*
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.lab1sma.lifecycle.ActA

class MainActivity : AppCompatActivity(), View.OnClickListener, AdapterView.OnItemSelectedListener {

    private lateinit var btnClick: Button
    private lateinit var nameTV: TextView
    private lateinit var nameET: EditText
    private lateinit var spinner: Spinner
    private lateinit var btnSearch: Button
    private lateinit var btnShare: Button
    private val colors: Map<String, Int> = mapOf(
        "White" to R.color.white,
        "Black" to R.color.black,
        "Red" to R.color.red,
        "Blue" to R.color.blue,
        "Green" to R.color.green
    )
    private lateinit var btnSwitch: Button
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
        spinner = findViewById(R.id.spinner)
        btnSearch = findViewById(R.id.bSearch)
        btnShare = findViewById(R.id.bShare)
        btnShare.setOnClickListener(this)
        btnSearch.setOnClickListener(this)
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, colors.keys.toList())
        spinner.adapter = adapter
        spinner.onItemSelectedListener = this
        btnSwitch = findViewById(R.id.lifecycle_screenswitch_button)
        btnSwitch.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v!!.id) {
            btnClick.id -> sayHelloPressed()
            btnSearch.id -> searchPressed()
            btnShare.id -> sharePressed()
            btnSwitch.id -> goToLifecyclesActivity()
        }

    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onItemSelected(
        parent: AdapterView<*>?,
        view: View?,
        position: Int,
        id: Long
    ) {
        val color = colors[spinner.selectedItem.toString()]
        btnClick.setTextColor(resources.getColor(color!!, null))
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
        Toast.makeText(this, "You haven't selected anything", Toast.LENGTH_SHORT)
            .show()
    }

    private fun sayHelloPressed() {
        if (nameET.text.isBlank()) Toast.makeText(this, "Please enter a name", Toast.LENGTH_SHORT)
            .show()
        else {
            nameTV.setText("Greetings ${nameET.text}")
            val dialogBuilder = AlertDialog.Builder(this)
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

    private fun sharePressed() {
        if (nameET.text.isNotEmpty()) {
            val intent = Intent()
            intent.action = Intent.ACTION_SEND
            intent.putExtra("name", nameET.text.toString())
            intent.type = "text/plain"
            startActivity(intent)
        } else{
            Toast.makeText(this, "Text must be filled", Toast.LENGTH_SHORT)
                .show()
        }
    }

    private fun searchPressed() {
        if (nameET.text.isNotEmpty()) {
            val intent = Intent()
            intent.action = Intent.ACTION_VIEW
            intent.data = Uri.parse("http://www.google.com/search?q=${nameET.text}")
            Log.d("IntentData", "searchPressed: ${intent.data}")
            startActivity(intent)
        } else {
            Toast.makeText(this, "Text must be filled", Toast.LENGTH_SHORT)
                .show()
        }
    }

    private fun goToLifecyclesActivity(){
        val intent : Intent = Intent(this, ActA::class.java)
        startActivity(intent)
    }
}
