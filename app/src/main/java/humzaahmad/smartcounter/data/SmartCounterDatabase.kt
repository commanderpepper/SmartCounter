package humzaahmad.smartcounter.data

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import humzaahmad.smartcounter.data.model.Counter
import humzaahmad.smartcounter.data.model.Project

/**
 * Created by Humza on 3/5/2018.
 * This is the Smart Counter Database, implements the Counter and Project class and the DAOs
 */
@Database(entities = arrayOf(Project::class, Counter::class), version = 1)
abstract class SmartCounterDatabase : RoomDatabase() {
    abstract fun projectsDao(): ProjectsDao
    abstract fun countersDao(): CountersDao

    companion object {
        private var INSTANCE: SmartCounterDatabase? = null

        private val lock = Any()

        fun getInstance(context: Context): SmartCounterDatabase {
            synchronized(lock){
                if(INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.applicationContext,
                            SmartCounterDatabase::class.java, "SmartCounter.db")
                            .build()
                }
                return INSTANCE!!
            }
        }
    }
}