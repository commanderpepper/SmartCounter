package humzaahmad.smartcounter.data

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import humzaahmad.smartcounter.data.model.Counter

/**
 * Created by Humza on 3/4/2018.
 * Data access object for the counters table
 */
@Dao
interface CountersDao{
    /**
     * Selects all counters from the counters table from one project
     *
     * @param projectid the project id of the counters
     * @return a list of counter objects that have the same project id
     */
    @Query("SELECT * FROM counters WHERE projectid = :projectid") fun getCounters(projectid: String) : List<Counter>

    /**
     * Select one counter from the counters table
     *
     * @param counterid the counter id of the counter object wanted
     * @return a counter object
     */
    @Query("SELECT * FROM counters WHERE counterid =:counterid") fun getCounter(counterid: String) : Counter

    /**
     * Insert a counter in the database. If the counter exists, replace it.
     *
     * @param counter
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE) fun insertCounter(counter: Counter)

    /**
     * Change the count by the long being passed of a particular counter via a counter id
     * The variation can be positive or negative
     *
     * @param vCount amount being added
     * @param counterid id of counter being updated
     */
    @Query("UPDATE counters SET count = (count + :vCount) WHERE counterid = :counterid") fun countVariation(vCount : Long, counterid: String)

    /**
     * Set a parent ID for a counter
     *
     * @param parentId id from a counter that you want to act as the parent
     * @param counterid id you want to be the child
     */
    @Query("UPDATE counters SET parentid = :parentId WHERE counterid = :counterid") fun setParentCounter(parentId: String, counterid: String)

    /**
     * Delete a counter
     *
     * @param counterid  id of counter being deleted
     */
    @Query("DELETE FROM counters WHERE counterid = :counterid") fun deleteCounter(counterid: String)

}
