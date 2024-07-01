package campus.tech.kakao.map

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log

class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    override fun onCreate(db: SQLiteDatabase) {
        val createTable = "CREATE TABLE $TABLE_NAME (" +
                "$COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                "$COLUMN_NAME TEXT," +
                "$COLUMN_ADDRESS TEXT)"
        db.execSQL(createTable)
        Log.d("DatabaseHelper", "Table created")

        for (i in 1..10) {
            val insertData = "INSERT INTO $TABLE_NAME ($COLUMN_NAME, $COLUMN_ADDRESS) VALUES ('카페$i', '서울 성동구 성수동 $i')"
            db.execSQL(insertData)
            Log.d("DatabaseHelper", "Inserted: 카페$i, 서울 성동구 성수동 $i")
        }
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)
    }

    companion object {
        private const val DATABASE_NAME = "searchDB.db"
        private const val DATABASE_VERSION = 1
        const val TABLE_NAME = "places"
        const val COLUMN_ID = "id"
        const val COLUMN_NAME = "name"
        const val COLUMN_ADDRESS = "address"
    }
}
