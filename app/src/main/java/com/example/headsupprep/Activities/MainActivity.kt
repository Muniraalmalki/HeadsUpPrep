package com.example.headsupprep.Activities

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.headsupprep.API.APIClient
import com.example.headsupprep.API.APIInterface
import com.example.headsupprep.Adapter.RecyclerViewAdapter
import com.example.headsupprep.Model.Celebrity
import com.example.headsupprep.Model.CelebrityItem
import com.example.headsupprep.R
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var addCelebrityButton: Button
    private lateinit var etName: EditText
    private lateinit var submitButton: Button
    private lateinit var celebrityList: List<CelebrityItem>
    private lateinit var recyclerViewAdapter: RecyclerViewAdapter

    val apiClient = APIClient().getClient()?.create(APIInterface::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        celebrityList = listOf()

        getAllCelebrity()
        recyclerView = findViewById(R.id.recyclerView)
        recyclerViewAdapter = RecyclerViewAdapter((celebrityList))
        recyclerView.layoutManager = LinearLayoutManager(this@MainActivity)
        recyclerView.adapter = recyclerViewAdapter



        etName = findViewById(R.id.etName)
        addCelebrityButton = findViewById(R.id.addCelebrityButton)
        addCelebrityButton.setOnClickListener {
            val intent = Intent(this, AddCelebrityActivity::class.java)
            startActivity(intent)
            getAllCelebrity()

        }
        submitButton = findViewById(R.id.submitButton)
        submitButton.setOnClickListener {

       //     Toast.makeText(this, "Please enter",Toast.LENGTH_LONG).show()
            if (etName.text.isNotEmpty() || etName.text.isNotBlank()){
                searchName()
            }else{
                Toast.makeText(this,"Please Enter Name",Toast.LENGTH_LONG).show()
            }


        }
    }

    private fun getAllCelebrity() {
        apiClient?.getAllCelebrity()?.enqueue(object : Callback<Celebrity> {
            override fun onResponse(call: Call<Celebrity>, response: Response<Celebrity>) {

                val userList = response.body()!!
                recyclerView.adapter = RecyclerViewAdapter(userList)
                celebrityList = userList
                recyclerView.layoutManager = LinearLayoutManager(this@MainActivity)
                recyclerView.adapter?.notifyDataSetChanged()
            }

            override fun onFailure(call: Call<Celebrity>, t: Throwable) {
                Toast.makeText(applicationContext, "Something get data ", Toast.LENGTH_LONG)
                    .show()
            }
        })

    }

    private fun searchName() {
        for (i in celebrityList){
            if (etName.text.toString() == i.name){
                val intent = Intent(this, UpdateAndDeleteActivity::class.java)
                intent.putExtra("name:", i.name)
                intent.putExtra("pk:", i.pk)
                intent.putExtra("taboo1:", i.taboo1)
                intent.putExtra("taboo2:", i.taboo2)
                intent.putExtra("taboo3:", i.taboo3)
                startActivity(intent)
                //Toast.makeText(this,"Searching ",Toast.LENGTH_LONG).show()
                break
            }
            else if (etName.text.toString() != i.name){
               Toast.makeText(this, "Not found", Toast.LENGTH_SHORT).show()
            }
    }
    }
}