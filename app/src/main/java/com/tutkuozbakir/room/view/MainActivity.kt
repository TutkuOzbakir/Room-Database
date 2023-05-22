package com.tutkuozbakir.room.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import com.tutkuozbakir.room.R
import com.tutkuozbakir.room.adapter.PlaceAdapter
import com.tutkuozbakir.room.databinding.ActivityMainBinding
import com.tutkuozbakir.room.model.Place
import com.tutkuozbakir.room.roomDb.PlaceDao
import com.tutkuozbakir.room.roomDb.PlaceDatabase
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var placeList = ArrayList<Place>()
    private lateinit var adapter: PlaceAdapter
    private lateinit var database: PlaceDatabase
    private lateinit var placeDao: PlaceDao
    private val compositeDisposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        database = Room.databaseBuilder(applicationContext, PlaceDatabase::class.java,"Places")
            .build()
        placeDao = database.placeDao()

        compositeDisposable.add(
            placeDao.getAllData()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this@MainActivity::handleResponse)
        )

    }

    private fun handleResponse(placeList : List<Place>){
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = PlaceAdapter(placeList)
        binding.recyclerView.adapter = adapter
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val menuInfalter = menuInflater.inflate(R.menu.options_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val intent = Intent(this@MainActivity, SecondActivity::class.java)
        startActivity(intent)
        return super.onOptionsItemSelected(item)
    }
}