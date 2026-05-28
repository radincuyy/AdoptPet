package com.example.adoptpet.data.db

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.provider.BaseColumns
import com.example.adoptpet.data.db.DatabaseContract.PetEntry
import com.example.adoptpet.model.PetItem

class PetDao(context: Context) {

    private val helper = DatabaseHelper.getInstance(context)

    fun count(): Int {
        helper.readableDatabase.rawQuery(
            "SELECT COUNT(*) FROM ${PetEntry.TABLE_NAME}", null
        ).use { c ->
            return if (c.moveToFirst()) c.getInt(0) else 0
        }
    }

    fun getAll(): List<PetItem> {
        val items = mutableListOf<PetItem>()
        helper.readableDatabase.query(
            PetEntry.TABLE_NAME,
            null, null, null, null, null,
            "${BaseColumns._ID} ASC"
        ).use { c ->
            while (c.moveToNext()) items += c.toPetItem()
        }
        return items
    }

    fun getById(id: Int): PetItem? {
        helper.readableDatabase.query(
            PetEntry.TABLE_NAME,
            null,
            "${BaseColumns._ID} = ?",
            arrayOf(id.toString()),
            null, null, null
        ).use { c ->
            return if (c.moveToFirst()) c.toPetItem() else null
        }
    }

    fun insert(pet: PetItem): Long =
        helper.writableDatabase.insert(PetEntry.TABLE_NAME, null, pet.toValues())

    fun update(pet: PetItem): Int =
        helper.writableDatabase.update(
            PetEntry.TABLE_NAME,
            pet.toValues(),
            "${BaseColumns._ID} = ?",
            arrayOf(pet.id.toString())
        )

    fun delete(id: Int): Int =
        helper.writableDatabase.delete(
            PetEntry.TABLE_NAME,
            "${BaseColumns._ID} = ?",
            arrayOf(id.toString())
        )

    fun bulkInsert(pets: List<PetItem>) {
        val db = helper.writableDatabase
        db.beginTransaction()
        try {
            pets.forEach { db.insert(PetEntry.TABLE_NAME, null, it.toValues()) }
            db.setTransactionSuccessful()
        } finally {
            db.endTransaction()
        }
    }

    private fun PetItem.toValues(): ContentValues = ContentValues().apply {
        put(PetEntry.COL_NAME, name)
        put(PetEntry.COL_SHELTER, shelter)
        put(PetEntry.COL_BREED, breed)
        put(PetEntry.COL_AGE, age)
        put(PetEntry.COL_GENDER, gender)
        put(PetEntry.COL_FEE, adoptionFee)
        put(PetEntry.COL_RATING, rating)
        put(PetEntry.COL_DESCRIPTION, description)
        put(PetEntry.COL_IMAGE_RES_ID, imageResId)
    }

    private fun Cursor.toPetItem(): PetItem = PetItem(
        id = getInt(getColumnIndexOrThrow(BaseColumns._ID)),
        name = getString(getColumnIndexOrThrow(PetEntry.COL_NAME)),
        shelter = getString(getColumnIndexOrThrow(PetEntry.COL_SHELTER)),
        breed = getString(getColumnIndexOrThrow(PetEntry.COL_BREED)),
        age = getString(getColumnIndexOrThrow(PetEntry.COL_AGE)),
        gender = getString(getColumnIndexOrThrow(PetEntry.COL_GENDER)),
        adoptionFee = getInt(getColumnIndexOrThrow(PetEntry.COL_FEE)),
        rating = getDouble(getColumnIndexOrThrow(PetEntry.COL_RATING)),
        description = getString(getColumnIndexOrThrow(PetEntry.COL_DESCRIPTION)),
        imageResId = getInt(getColumnIndexOrThrow(PetEntry.COL_IMAGE_RES_ID))
    )
}
