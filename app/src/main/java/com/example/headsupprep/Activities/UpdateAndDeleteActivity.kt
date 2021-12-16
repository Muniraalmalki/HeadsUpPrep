package com.example.headsupprep.Activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import android.util.Log

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

class UpdateAndDeleteActivity : AppCompatActivity() {

    private lateinit var etName: EditText

    private lateinit var etTaboo1: EditText
    private lateinit var etTaboo2: EditText
    private lateinit var etTaboo3: EditText

    private lateinit var updateButton: Button
    private lateinit var deleteButton: Button
    private lateinit var backButton: Button
    var pk =0


    val apiClient = APIClient().getClient()?.create(APIInterface::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_and_delete)

        etName = findViewById(R.id.etName)
        etTaboo1 = findViewById(R.id.etTaboo1)
        etTaboo2 = findViewById(R.id.etTaboo2)
        etTaboo3 = findViewById(R.id.eetTaboo3)
        updateButton = findViewById(R.id.updateButton)
        deleteButton = findViewById(R.id.deleteButton)
        backButton = findViewById(R.id.backButton)


        val data = intent.extras?.getStringArrayList("data")
        if (data != null) {
            etName.setText(data[1])
            etTaboo1.setText(data[2])
            etTaboo2.setText(data[3])
            etTaboo3.setText(data[4])
        } else {
            Toast.makeText(this, "Sorry null", Toast.LENGTH_SHORT).show()
        }


        updateButton.setOnClickListener {

            val pk = data!![0].toInt()
            Log.d("id","succeffully${pk}")

            val name = etName.text.toString()
            val taboo1 = etTaboo1.text.toString()
            val taboo2 = etTaboo2.text.toString()
            val taboo3 = etTaboo3.text.toString()

            apiClient!!.updateCelebrity(pk, CelebrityItem(name, pk, taboo1, taboo2, taboo3))
                ?.enqueue(object : Callback<CelebrityItem> {
                    override fun onResponse(
                        call: Call<CelebrityItem>,
                        response: Response<CelebrityItem>
                    ) {
                        Toast.makeText(
                            this@UpdateAndDeleteActivity,
                            "User Updated Successfully",
                            Toast.LENGTH_LONG
                        ).show()

                        val intent = Intent(this@UpdateAndDeleteActivity, MainActivity::class.java)
                        startActivity(intent)
                    }

                    override fun onFailure(call: Call<CelebrityItem>, t: Throwable) {
                        Toast.makeText(
                            this@UpdateAndDeleteActivity,
                            "Something went wrong ",
                            Toast.LENGTH_LONG
                        ).show()
                    }

                })

        }


        backButton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
        deleteButton.setOnClickListener {

            apiClient!!.deleteCelebrity(data!![0].toInt()).enqueue(object : Callback<Void> {
                override fun onResponse(call: Call<Void>, response: Response<Void>) {
                    Toast.makeText(
                        this@UpdateAndDeleteActivity,
                        "User Deleted Successfully",
                        Toast.LENGTH_LONG
                    ).show()
                    val intent = Intent(this@UpdateAndDeleteActivity, MainActivity::class.java)
                    startActivity(intent)
                }

                override fun onFailure(call: Call<Void>, t: Throwable) {
                    Toast.makeText(
                        this@UpdateAndDeleteActivity,
                        "Something Went wrong",
                        Toast.LENGTH_LONG
                    ).show()
                }
            })
        }
    }
}

