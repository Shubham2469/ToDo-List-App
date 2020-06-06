package com.example.todo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class EditField : AppCompatActivity() {
   val iobj = IntentVariables()

    //late initialization for app component and are of non-nullable type
    lateinit var button: Button
    lateinit var editTextField: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.todo_layout)

        //initialization of lateinit variable
        editTextField = findViewById(R.id.editText)
        button = findViewById(R.id.saveBtn)
        
        button.setOnClickListener{
            val itemText = editTextField.text.toString()

            if(itemText == ""){
                  Toast.makeText(applicationContext,"Please add item to the list",Toast.LENGTH_LONG).show()
            }else{
                intent = Intent()
                intent.putExtra(iobj.INTENT_MESSAGE_FIELD,itemText)   //sends data to the MainActivity
                setResult(iobj.INTENT_RESULT_CODE,intent)
                finish()

            }
        }
    }

}
