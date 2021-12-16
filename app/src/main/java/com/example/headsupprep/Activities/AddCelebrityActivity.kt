package com.example.headsupprep.Activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.headsupprep.API.APIClient
import com.example.headsupprep.API.APIInterface
import com.example.headsupprep.Model.CelebrityItem
import com.example.headsupprep.R
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AddCelebrityActivity : AppCompatActivity() {

    private lateinit var etName: EditText
    private lateinit var etTaboo1: EditText
    private lateinit var etTaboo2: EditText
    private lateinit var etTaboo3: EditText
    private lateinit var addCelebrityButton: Button
    private lateinit var backButton: Button

    val apiClient = APIClient().getClient()?.create(APIInterface::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_celebrity)

        etName = findViewById(R.id.etName)
        etTaboo1 = findViewById(R.id.etTaboo1)
        etTaboo2 = findViewById(R.id.etTaboo2)
        etTaboo3 = findViewById(R.id.eetTaboo3)
        addCelebrityButton = findViewById(R.id.addCelebrityButton)
        backButton = findViewById(R.id.backButton)


        backButton.setOnClickListener {
            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
        }


        addCelebrityButton.setOnClickListener {

            val name = etName.text.toString()
            val taboo1 = etTaboo1.text.toString()
            val taboo2 = etTaboo2.text.toString()
            val taboo3 = etTaboo3.text.toString()

            apiClient?.addCelebrity(CelebrityItem(name, 0,taboo1,taboo2,taboo3))?.enqueue(object : Callback<CelebrityItem> {
                override fun onResponse(call: Call<CelebrityItem>, response: Response<CelebrityItem>) {
                    Toast.makeText(
                        this@AddCelebrityActivity,
                        "data added successfully",
                        Toast.LENGTH_LONG
                    ).show()

                }

                override fun onFailure(call: Call<CelebrityItem>, t: Throwable) {
                    Toast.makeText(this@AddCelebrityActivity, "Something went wrong ", Toast.LENGTH_LONG)
                        .show()
                }
            })

        }
    }
}