package com.zyh.firstlinecode.MapAndSwitchMap

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

/**
 * 这一部分写法非常固定，只需要定义好3个部分：
 * 1、数据库的版本号
 * 2、包含哪些实体类
 * 3、已经提供dao层的访问实例
 * */
@Database(version = 3, entities = [User::class, Book::class])
abstract class AppDatabase : RoomDatabase() {
    abstract fun UserDao(): UserDao

    abstract fun BookDao(): BookDao

    companion object {

        val MIGRATION_1_2 = object : Migration(1,2){
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("create table " +
                        "Book(id integer primary key autoincrement not null,name text not null,pages integer not null)")
            }
        }

        val MIGRATION_2_3 = object:Migration(2,3){
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("alter table Book add column author text not null default 'unknown'")
            }
        }

        private var instance: AppDatabase? = null

        @Synchronized
        fun getDatabase(context: Context): AppDatabase {
            instance?.let {
                return it
            }
            return Room.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java,
                "app_database"
            ).addMigrations(MIGRATION_1_2)
                .build().apply {
                instance = this
            }
        }
    }
}