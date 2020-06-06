package com.example.todo

import android.R.attr.name
import android.annotation.SuppressLint
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton


class MainActivity : AppCompatActivity() {
    val iobj = IntentVariables()

    //mutable list of String which is used to store list item
    private var taskList = mutableListOf<String>()
        //ArrayList<String>()
    private lateinit var arrayAdapter: ArrayAdapter<String>

    //late initialization for app component and are of non-nullable type
    private lateinit var listView: ListView
    private lateinit var floatbtn: FloatingActionButton

    //list has no item initially so its defined null using nullability
    private var items: String? = null
    private var position: Int = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        listView = findViewById(R.id.itemListView)
        arrayAdapter = ArrayAdapter(this,android.R.layout.simple_list_item_1,taskList)
        listView.adapter = arrayAdapter

        floatbtn = findViewById(R.id.fab)
        //to add new item
        floatbtn.setOnClickListener{
            intent = Intent()
            intent.setClass(this,EditField::class.java)   //use to start EditField activity
            startActivityForResult(intent,iobj.INTENT_REQUEST_CODE)   //get the result from second activity
        }

        //to edit the selected item
        listView.setOnItemClickListener { _, _, position, _ ->
            intent = Intent()
            intent.setClass(this,EditItemList::class.java)   //use to start EditItemList activity
            intent.putExtra(iobj.INTENT_MESSAGE_DATA, taskList[position].toString())
            intent.putExtra(iobj.INTENT_ITEM_POSITION,position)  //pass the values

            startActivityForResult(intent,iobj.INTENT_REQUEST_CODE2)   //get the result from second activity

            Toast.makeText(applicationContext,"Long press to delete any item",Toast.LENGTH_LONG).show()


        }

        //long press particular item to delete it
        listView.setOnItemLongClickListener { parent: AdapterView<*>?, view: View?, position: Int, id: Long ->
             val itemposition = position
             AlertDialog.Builder(this)
                 .setIcon(android.R.drawable.ic_delete)
                 .setTitle("Are you sure?")
                 .setMessage("Do you want to delete this item")
                 .setPositiveButton("yes",DialogInterface.OnClickListener { dialog, which ->
                     taskList.removeAt(itemposition)
                     arrayAdapter.notifyDataSetChanged()
                 })
                 .setNegativeButton("No",null)
                 .show()   //dialog box is poped to confirm to delete item

            return@setOnItemLongClickListener true
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        //add item to the list passed through Intent
        if(resultCode == iobj.INTENT_RESULT_CODE){
               items = data!!.getStringExtra(iobj.INTENT_MESSAGE_FIELD)
               taskList.add(items.toString())
            arrayAdapter.notifyDataSetChanged()  //make changes in the ArrayAdapter
        }
        //remove element from list
        else{
            items = data!!.getStringExtra(iobj.INTENT_CHANGED_MESSAGE)
            position = data.getIntExtra(iobj.INTENT_ITEM_POSITION, 0)
            taskList.removeAt(index = position)
            taskList.add(position,items.toString())
            arrayAdapter.notifyDataSetChanged()   //make changes in the ArrayAdapter
        }

    }

}



