package com.lsb.todolist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var todoAdapter: TodoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        todoAdapter = TodoAdapter(mutableListOf())

        rvTodoItems.adapter = todoAdapter
        rvTodoItems.layoutManager = LinearLayoutManager(this)

        btnAddTodo.setOnClickListener {
            val todoTitle = etTodoTitle.text.toString()
            if(todoTitle.isNotEmpty()) {
                val todo = Todo(todoTitle)
                todoAdapter.addTodo(todo)
                etTodoTitle.text.clear()
            }
        }

        btnDeleteDone.setOnClickListener {
            todoAdapter.deleteDoneTodos()
        }





        //TODO

        val manager  = LinearLayoutManager(this)
        rvTodoItems.layoutManager = manager
/*
        val itemAdapter = TodoAdapter(mutableListOf(), this)
        rvTodoItems.adapter = itemAdapter
*/
        val dividerItemDecoration = DividerItemDecoration(this , manager.orientation)
        rvTodoItems.addItemDecoration(dividerItemDecoration)


        // Setup ItemTouchHelper
        val callback = DragManageAdapter(todoAdapter, this,
            ItemTouchHelper.UP.or(ItemTouchHelper.DOWN), ItemTouchHelper.LEFT.or(ItemTouchHelper.RIGHT))
        val helper = ItemTouchHelper(callback)
        helper.attachToRecyclerView(rvTodoItems)

    }
}

