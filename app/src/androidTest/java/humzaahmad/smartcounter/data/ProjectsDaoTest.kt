package humzaahmad.smartcounter.data

import android.arch.persistence.room.Room
import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import android.util.Log
import humzaahmad.smartcounter.data.model.Project
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.CoreMatchers.notNullValue
import org.hamcrest.CoreMatchers.nullValue
import org.hamcrest.MatcherAssert.assertThat
import org.mockito.Matchers.isNotNull


/**
 * Created by Humza on 3/6/2018.
 */
@RunWith(AndroidJUnit4::class)
class ProjectsDaoTest {

    private lateinit var database: SmartCounterDatabase

    @Before fun initDb() {
        // using an in-memory database because the information stored here disappears when the
        // process is killed
        Log.d("Room", "Before")
        database = Room.inMemoryDatabaseBuilder(InstrumentationRegistry.getContext(),
                SmartCounterDatabase::class.java).build()
    }

    @After fun closeDb() {
        Log.d("Room", "After")
        database.close()
    }

    @Test fun insertProjectAndGetId() {
        database.projectsDao().insertProject(PROJECT)

        val loaded = database.projectsDao().getAllProjects()[0]

        assertProject(loaded, PROJECT.id, PROJECT.title, PROJECT.description)
    }

    private fun assertProject(project: Project, id: String, title: String, description: String) {
        assertThat(project, notNullValue())
        assertThat(project.id, `is`<String>(id))
        assertThat(project.title, `is`(title))
        assertThat(project.description, `is`(description))
    }

    companion object {
        private val PROJECT_TITLE: String = "Test Project"
        private val PROJECT_DESC: String = "It's a description"
        private val PROJECT = Project(title = PROJECT_TITLE, description = PROJECT_DESC)
    }
}