package humzaahmad.smartcounter.data

import android.arch.persistence.room.Room
import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import humzaahmad.smartcounter.data.model.Counter
import humzaahmad.smartcounter.data.model.Project
import org.junit.After
import org.junit.Before
import org.junit.runner.RunWith

import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.CoreMatchers.notNullValue
import org.hamcrest.CoreMatchers.nullValue
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test

/**
 * Created by Humza on 3/12/2018.
 * Test class for the CountersDAO
 */
@RunWith(AndroidJUnit4::class)
class CountersDaoTest {

    private lateinit var database: SmartCounterDatabase

    @Before
    fun initDb() {
        // using an in-memory database because the information stored here disappears when the
        // process is killed
        database = Room.inMemoryDatabaseBuilder(InstrumentationRegistry.getContext(),
                SmartCounterDatabase::class.java).build()
    }

    @After
    fun closeDb() {
        database.close()
    }

    /**
     * Insert a counter and retrieve a counter
     */
    @Test
    fun putCounterAndGetById() {
        //Insert a project
        database.projectsDao().insertProject(PROJECT)
        //Insert a counter
        database.countersDao().insertCounter(COUNTER1)

        //Get a counter using the id of COUNTER1
        val counter = database.countersDao().getCounter(COUNTER1.id)
        //Test against val counter
        assertCounter(counter, counterId = COUNTER1.id, projectId = COUNTER1.projectid)
    }

    /**
     * Insert a counter then delete a counter
     * Tests to see if the counter no longer exists
     */
    @Test
    fun deleteCounter() {
        //Insert a project
        database.projectsDao().insertProject(PROJECT)
        //Insert a counter
        database.countersDao().insertCounter(COUNTER1)

        //Delete a counter using the counter id
        database.countersDao().deleteCounter(COUNTER1.id)
        //Retrieve a counter, val counter should be null
        val counter = database.countersDao().getCounter(COUNTER1.id)
        //Tests to see if val counter is null
        assertCounterIsNull(counter)
    }

    /**
     * Increase the count of a particular counter
     */
    @Test
    fun increaseCounter() {
        //Insert a project
        database.projectsDao().insertProject(PROJECT)
        //Insert a counter
        database.countersDao().insertCounter(COUNTER1)

        //Increase the count by 5
        database.countersDao().countVariation(5, COUNTER1.id)
        //Retrieve a counter
        val counter = database.countersDao().getCounter(COUNTER1.id)
        //Check to see if the count of the counter object is 5
        assertCounter(counter, counterId = COUNTER1.id, projectId = COUNTER1.projectid, count = 5)
    }

    /**
     * Decrease a counter of a particular counter
     */
    @Test
    fun decreaseCounter() {
        //Insert a project
        database.projectsDao().insertProject(PROJECT)
        //Insert a counter
        database.countersDao().insertCounter(COUNTER1)

        //Decrease the count by -5
        database.countersDao().countVariation(-5, COUNTER1.id)
        //Retrieve a counter
        val counter = database.countersDao().getCounter(COUNTER1.id)
        //Check to see if the count of the counter object is -5
        assertCounter(counter, counterId = COUNTER1.id, projectId = COUNTER1.projectid, count = -5)
    }

    /**
     * Set the parent counter id of a counter to be that one of another counter
     */
    @Test
    fun setParentCounter() {
        //Insert a project
        database.projectsDao().insertProject(PROJECT)
        //Insert a counter, a child counter
        database.countersDao().insertCounter(COUNTER1)
        //Insert a counter, a parent counter
        database.countersDao().insertCounter(COUNTER2)

        //Set the parent counter of a counter
        database.countersDao().setParentCounter(COUNTER2.id, COUNTER1.id)
        //Retrieve a counter
        val counter = database.countersDao().getCounter(COUNTER1.id)
        //Tests to see if the parentID is the id of COUNTER2
        assertCounter(counter, counterId = COUNTER1.id, projectId = COUNTER1.projectid, parentId = COUNTER2.id)
    }

    //This method helps with testing, it tests for equality
    private fun assertCounter(counter: Counter, counterId: String, count: Long = 0, projectId: String, parentId: String = "") {
        assertThat(counter, notNullValue())
        assertThat(counter.id, `is`<String>(counterId))
        assertThat(counter.count, `is`<Long>(count))
        assertThat(counter.projectid, `is`<String>(projectId))
        assertThat(counter.parentCounterId, `is`<String>(parentId))
    }

    private fun assertCounterIsNull(counter: Counter) {
        assertThat(counter, `is`(nullValue()))
    }

    companion object {
        private val PROJECT_TITLE: String = "Test Project"
        private val PROJECT_DESC: String = "It's a description"
        private val PROJECT = Project(title = PROJECT_TITLE, description = PROJECT_DESC)
        private val PROJECT2 = Project(title = PROJECT_TITLE, description = PROJECT_DESC)

        private val COUNTER1 = Counter(projectid = PROJECT.id)
        private val COUNTER2 = Counter(projectid = PROJECT.id)
        private val COUNTER3 = Counter(projectid = PROJECT2.id)
    }
}