package com.younes.paybackcodingchallenge.roomdb

import androidx.room.Database
import androidx.room.RoomDatabase
import com.younes.paybackcodingchallenge.model.ImageResult

@Database(entities = [ImageResult::class],version = 5,exportSchema = false)
abstract class ImageDatabase : RoomDatabase() {
    abstract fun artDao() : ImageDao
}
