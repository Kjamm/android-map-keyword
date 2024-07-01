package campus.tech.kakao.map

import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class PlaceDao(context: Context) {
    private val dbHelper = DatabaseHelper(context)

    fun getPlaces(query: String): List<Place> {
        val places = mutableListOf<Place>()
        val db = dbHelper.readableDatabase
        val cursor: Cursor = db.rawQuery("SELECT * FROM ${DatabaseHelper.TABLE_NAME} WHERE ${DatabaseHelper.COLUMN_NAME} LIKE ? OR ${DatabaseHelper.COLUMN_ADDRESS} LIKE ?", arrayOf("%$query%", "%$query%"))

        if (cursor.moveToFirst()) {
            do {
                val id = cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_ID))
                val name = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_NAME))
                val address = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_ADDRESS))
                places.add(Place(id, name, address))
            } while (cursor.moveToNext())
        }

        cursor.close()
        db.close()
        return places
    }
}
