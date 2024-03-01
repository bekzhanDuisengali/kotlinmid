package com.example.todolistapp

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var todoListView: ListView
    private lateinit var addButton: Button
    private lateinit var adapter: ArrayAdapter<String>
    private lateinit var todoList: MutableList<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        todoListView = findViewById(R.id.todoListView)
        addButton = findViewById(R.id.addButton)

        todoList = mutableListOf()
        adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, todoList)
        todoListView.adapter = adapter

        addButton.setOnClickListener {
            val editText = EditText(this)
            AlertDialog.Builder(this)
                .setTitle("Add Task")
                .setView(editText)
                .setPositiveButton("Add") { dialog, _ ->
                    val task = editText.text.toString()
                    if (task.isNotEmpty()) {
                        todoList.add(task)
                        adapter.notifyDataSetChanged()
                    }
                    dialog.dismiss()
                }
                .setNegativeButton("Cancel") { dialog, _ ->
                    dialog.dismiss()
                }
                .create()
                .show()
        }
    }
}
