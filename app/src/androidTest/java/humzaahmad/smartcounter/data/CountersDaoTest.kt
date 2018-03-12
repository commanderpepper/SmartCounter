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
 */
@RunWith(AndroidJUnit4::class)
class CountersDaoTest {

    private lateinit var database: SmartCounterDatabase

    @Before fun initDb() {
        // using an in-memory database because the information stored here disappears when the
        // process is killed
        database = Room.inMemoryDatabaseBuilder(InstrumentationRegistry.getContext(),
                SmartCounterDatabase::class.java).build()
    }

    @After fun closeDb() { database.close() }

    @Test fun putCounterAndGetById() {
        database.projectsDao().insertProject(PROJECT)
        database.countersDao().insertCounter(COUNTER1)

        //val counter = database.countersDao().deleteCounter(COUNTER1.id)
        val counter = database.countersDao().getCounter(COUNTER1.id)
        assertCounter(counter, counterId = COUNTER1.id, projectId = COUNTER1.projectid)
    }

    @Test fun deleteCounter() {
        database.projectsDao().insertProject(PROJECT)
        database.countersDao().insertCounter(COUNTER1)

        database.countersDao().deleteCounter(COUNTER1.id)
        val counter = database.countersDao().getCounter(COUNTER1.id)
        assertCounterIsNull(counter)
    }

    @Test fun increaseCounter() {
        database.projectsDao().insertProject(PROJECT)
        database.countersDao().insertCounter(COUNTER1)

        database.countersDao().countVariation(5, COUNTER1.id)
        val counter = database.countersDao().getCounter(COUNTER1.id)
        assertCounter(counter, counterId = COUNTER1.id, projectId = COUNTER1.projectid, count = 5)
    }

    @Test fun decreaseCounter() {
        database.projectsDao().insertProject(PROJECT)
        database.countersDao().insertCounter(COUNTER1)

        database.countersDao().countVariation(-5, COUNTER1.id)
        val counter = database.countersDao().getCounter(COUNTER1.id)
        assertCounter(counter, counterId = COUNTER1.id, projectId = COUNTER1.projectid, count = -5)
    }

    @Test fun setParentCounter() {
        database.projectsDao().insertProject(PROJECT)
        database.countersDao().insertCounter(COUNTER1)
        database.countersDao().insertCounter(COUNTER2)

        database.countersDao().setParentCounter(COUNTER2.id, COUNTER1.id)
        val counter = database.countersDao().getCounter(COUNTER1.id)
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

    private fun assertCounterIsNull(counter: Counter) {assertThat(counter, `is`(nullValue()))}

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