package humzaahmad.smartcounter.data

import android.arch.persistence.room.Room
import android.support.test.InstrumentationRegistry
import android.support.test.filters.LargeTest
import android.support.test.runner.AndroidJUnit4
import org.junit.Before
import org.junit.runner.RunWith

/**
 * Created by Humza on 3/14/2018.
 * Integration test for [SmartCounterDataSource]
 */

@RunWith(AndroidJUnit4::class) @LargeTest
class SmartCounterLocalDataSourceTest {
    private lateinit var localDataSource: SmartCounterLocalDataSource
    private lateinit var database: SmartCounterDatabase

    @Before
    fun setUp() {
        database = Room.inMemoryDatabaseBuilder(InstrumentationRegistry.getContext(),
                SmartCounterDatabase::class.java)
                .build()

        SmartCounterLocalDataSource.clearInstance()

    }

}
