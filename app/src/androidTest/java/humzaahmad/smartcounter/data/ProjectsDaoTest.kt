package humzaahmad.smartcounter.data

import android.arch.persistence.room.Room
import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4

import humzaahmad.smartcounter.data.model.Project
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.CoreMatchers.notNullValue
import org.hamcrest.CoreMatchers.nullValue
import org.hamcrest.MatcherAssert.assertThat


/**
 * Created by Humza on 3/6/2018.
 * Test class for the ProjectDAO class
 */
@RunWith(AndroidJUnit4::class)
class ProjectsDaoTest {

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
     * Insert a project and get the project from the database using a String id
     */
    @Test
    fun insertProjectAndGetId() {
        //Insert a project
        database.projectsDao().insertProject(PROJECT)
        //Retrieve the project
        val loaded = database.projectsDao().getProjectById(PROJECT.id)
        //Test against the project retrieved
        assertProject(loaded, PROJECT.id, PROJECT.title, PROJECT.description)
    }

    /**
     * Insert multiple projects and get a list of projects
     */
    @Test
    fun insertProjectsAndGetList() {
        //Insert two projects
        database.projectsDao().insertProject(PROJECT)
        database.projectsDao().insertProject(PROJECT2)
        //Get a list of projects
        val projectList = database.projectsDao().getAllProjects()
        //Test against the first project inserted
        assertProject(projectList[0], PROJECT.id, PROJECT.title, PROJECT.description)
        //Test against the second project inserted
        assertProject(projectList[1], PROJECT2.id, PROJECT2.title, PROJECT2.description)
    }

    /**
     * Insert and then delete a project
     */
    @Test
    fun insertProjectAndDeleteProject() {
        //Insert a project
        database.projectsDao().insertProject(PROJECT)
        //Delete a project
        database.projectsDao().deleteProject(PROJECT.id)
        //Get a project according to the id of the project inserted, val project should be null
        val project = database.projectsDao().getProjectById(PROJECT.id)
        //Check to see if a project is null
        assertProjectIsNull(project)
    }

    private fun assertProject(project: Project, id: String, title: String, description: String) {
        assertThat(project, notNullValue())
        assertThat(project.id, `is`<String>(id))
        assertThat(project.title, `is`(title))
        assertThat(project.description, `is`(description))
    }

    private fun assertProjectIsNull(project: Project) {
        assertThat(project, `is`(nullValue()))
    }

    companion object {
        private val PROJECT_TITLE: String = "Test Project"
        private val PROJECT_DESC: String = "It's a description"
        private val PROJECT = Project(title = PROJECT_TITLE, description = PROJECT_DESC)
        private val PROJECT2 = Project(title = PROJECT_TITLE, description = PROJECT_DESC)
    }
}