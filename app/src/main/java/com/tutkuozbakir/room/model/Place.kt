package com.tutkuozbakir.room.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class Place(
    @ColumnInfo(name =  "name")
    var name: String,

    @ColumnInfo(name =  "country")
    var country: String){

    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}