package humzaahmad.smartcounter.data

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Query

/**
 * Created by Humza on 3/4/2018.
 * Data access object for the counters table
 */
@Dao
interface CountersDao{
    @Query("SELECT * FROM counters WHERE projectid = :projectid") fun getCounters(projectid: String) : List<Counter>

    @Query("SELECT * FROM counters WHERE counterid =:counterid") fun getCounter(counterid: String) : Counter
}
