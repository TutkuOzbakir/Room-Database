package com.tutkuozbakir.room.roomDb

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.tutkuozbakir.room.model.Place
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Flowable

@Dao
interface PlaceDao {

    @Query("SELECT * FROM Place")
    fun getAllData() : Flowable<List<Place>>

    /*
    @Query("SELECT * FROM Place WHERE id = :id")
    fun getPlace(id: String) : List<Place>
     */
    @Insert
    fun insert(place: Place) : Completable

    @Delete
    fun delete(place: Place) : Completable

}