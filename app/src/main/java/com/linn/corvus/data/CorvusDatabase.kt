package com.linn.corvus.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

@Database(
    entities = [Item::class],
    version = 3,
    exportSchema = false
)
abstract class CorvusDatabase : RoomDatabase() {

    abstract fun itemDao(): ItemDao

    companion object {
        @Volatile
        private var Instance: CorvusDatabase? = null

        fun getDatabase(context: Context): CorvusDatabase {
            val migrationFrom2To3 = object : Migration(1, 2) {
                override fun migrate(db: SupportSQLiteDatabase) {
                    db.execSQL("ALTER TABLE items RENAME COLUMN color TO type")
                    db.execSQL("ALTER TABLE items RENAME COLUMN photoPath TO photo")
                }
            }

            return Instance ?: synchronized(this) {
                Room.databaseBuilder(context, CorvusDatabase::class.java, "item_database")
                    .addMigrations(migrationFrom2To3)
                    .build().also { Instance = it }
            }
        }
    }
}