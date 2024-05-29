package com.example.inventarapp_v2

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: InventoryAdapter
    private var apiService: ApiService? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.toolbar))

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = InventoryAdapter(listOf())
        recyclerView.adapter = adapter

        apiService = RetrofitClient.instance
        loadInventoryData()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_filter -> {
                // Filter Logik hier
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun loadInventoryData() {
        apiService?.getAllInventoryItems()?.enqueue(object : Callback<List<InventoryItem>> {
            override fun onResponse(call: Call<List<InventoryItem>>, response: Response<List<InventoryItem>>) {
                if (response.isSuccessful) {
                    adapter.updateData(response.body()!!)
                } else {
                    AlertDialog.Builder(this@MainActivity)
                        .setTitle("Fehler")
                        .setMessage("Serverfehler: Statuscode ${response.code()}, Fehlermeldung: ${response.errorBody()?.string()}")
                        .setPositiveButton("Erneut versuchen") { dialog, which ->
                            loadInventoryData() // Versucht die Daten erneut zu laden
                        }
                        .setNegativeButton("Abbrechen", null)
                        .show()
                }
            }

            override fun onFailure(call: Call<List<InventoryItem>>, t: Throwable) {
                AlertDialog.Builder(this@MainActivity)
                    .setTitle("Fehler")
                    .setMessage("Fehler beim Abrufen der Inventardaten: ${t.message}")
                    .setPositiveButton("Erneut versuchen") { dialog, which ->
                        loadInventoryData() // Versucht die Daten erneut zu laden
                    }
                    .setNegativeButton("Abbrechen", null)
                    .show()
            }
        })
    }
}
