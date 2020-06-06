package com.example.todo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class EditItemList : AppCompatActivity() {

     var position: Int = 0
    val iobj = IntentVariables()

    //late initialization for app component and are of non-nullable type
    lateinit var editItem: EditText
    lateinit var  saveBtn: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.todo_layout)

        intent = Intent()
        val itemText = intent.getStringExtra(iobj.INTENT_MESSAGE_DATA)
        position = intent.getIntExtra(iobj.INTENT_ITEM_POSITION,0)  //get the data

        editItem = findViewById(R.id.editText)
        editItem.setText(itemText)
        saveBtn = findViewById(R.id.saveBtn)

        saveBtn.setOnClickListener{
            val changeItem: String = editItem.text.toString()
            intent.putExtra(iobj.INTENT_CHANGED_MESSAGE,changeItem)
            intent.putExtra(iobj.INTENT_ITEM_POSITION,position)  //sends the data
            setResult(iobj.INTENT_RESULT_CODE2,intent)
            finish()
        }

    }
}
