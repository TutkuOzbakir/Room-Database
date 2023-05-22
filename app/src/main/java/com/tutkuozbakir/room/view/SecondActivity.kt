package com.tutkuozbakir.room.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.room.Room
import com.tutkuozbakir.room.adapter.PlaceAdapter
import com.tutkuozbakir.room.databinding.ActivitySecondBinding
import com.tutkuozbakir.room.model.Place
import com.tutkuozbakir.room.roomDb.PlaceDao
import com.tutkuozbakir.room.roomDb.PlaceDatabase
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers

class SecondActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySecondBinding
    private lateinit var database: PlaceDatabase
    private lateinit var placeDao: PlaceDao
    private val compositeDisposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        database = Room.databaseBuilder(applicationContext, PlaceDatabase::class.java,"Places")
            //.allowMainThreadQueries()
            .build()
        placeDao = database.placeDao()

        binding.buttonSave.setOnClickListener {
            val place = Place(binding.editTextName.text.toString(), binding.editTextCountry.text.toString())
            //placeDao.insert(place)    -> Main Thread error
            compositeDisposable.add(
                placeDao.insert(place)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(this@SecondActivity::handleResponse)
            )
        }

    }

    private fun handleResponse(){
        val intent = Intent(this@SecondActivity, MainActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        startActivity(intent)
    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.clear()
    }
}