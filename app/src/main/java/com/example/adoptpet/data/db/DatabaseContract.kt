package com.example.adoptpet.data.db

import android.provider.BaseColumns

object DatabaseContract {

    const val DATABASE_NAME = "adoptpet.db"
    const val DATABASE_VERSION = 1

    object PetEntry : BaseColumns {
        const val TABLE_NAME = "pets"
        const val COL_NAME = "name"
        const val COL_SHELTER = "shelter"
        const val COL_BREED = "breed"
        const val COL_AGE = "age"
        const val COL_GENDER = "gender"
        const val COL_FEE = "adoption_fee"
        const val COL_RATING = "rating"
        const val COL_DESCRIPTION = "description"
        const val COL_IMAGE_RES_ID = "image_res_id"

        const val SQL_CREATE =
            "CREATE TABLE $TABLE_NAME (" +
                "${BaseColumns._ID} INTEGER PRIMARY KEY AUTOINCREMENT," +
                "$COL_NAME TEXT NOT NULL," +
                "$COL_SHELTER TEXT NOT NULL," +
                "$COL_BREED TEXT NOT NULL," +
                "$COL_AGE TEXT NOT NULL," +
                "$COL_GENDER TEXT NOT NULL," +
                "$COL_FEE INTEGER NOT NULL," +
                "$COL_RATING REAL NOT NULL," +
                "$COL_DESCRIPTION TEXT NOT NULL," +
                "$COL_IMAGE_RES_ID INTEGER NOT NULL" +
                ")"

        const val SQL_DROP = "DROP TABLE IF EXISTS $TABLE_NAME"
    }
}
