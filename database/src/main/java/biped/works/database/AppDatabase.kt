package biped.works.database

import androidx.room.Database
import androidx.room.RoomDatabase
import biped.works.database.user.UserDao
import biped.works.database.user.UserEntity

@Database(entities = [UserEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
}