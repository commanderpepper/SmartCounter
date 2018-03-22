package humzaahmad.smartcounter.data

import android.arch.persistence.room.Room
import android.support.test.InstrumentationRegistry
import android.support.test.filters.LargeTest
import android.support.test.runner.AndroidJUnit4
import humzaahmad.smartcounter.data.model.Project
import humzaahmad.smartcounter.data.util.SingleExecutors
import org.hamcrest.core.Is
import org.junit.After
import org.junit.Assert
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify
import java.util.*

/**
 * Created by Humza on 3/14/2018.
 * Integration test for [SmartCounterDataSource]
 */

@RunWith(AndroidJUnit4::class)
@LargeTest
class SmartCounterLocalDataSourceTest {
    private lateinit var localDataSource: SmartCounterLocalDataSource
    private lateinit var database: SmartCounterDatabase

    @Before
    fun setUp() {
        // using an in-memory database for testing, since it doesn't survive killing the process
        database = Room.inMemoryDatabaseBuilder(InstrumentationRegistry.getContext(),
                SmartCounterDatabase::class.java)
                .build()

        // Make sure that we're not keeping a reference to the wrong instance.
        SmartCounterLocalDataSource.clearInstance()
        localDataSource = SmartCounterLocalDataSource.getInstance(
                SingleExecutors(), database.projectsDao(), database.countersDao())
    }

    @After
    fun cleanUp() {
        database.close()
        SmartCounterLocalDataSource.clearInstance()
    }

    @Test
    fun testPreConditions() {
        Assert.assertNotNull(localDataSource)
    }

    @Test
    fun saveProject_retrievesProject() {
        //given a new project
        val newProject = Project(title = "Test Project", description = "Test Desc")

        with(localDataSource) {
            // When saved into the persistent repository
            saveProject(newProject)

            // Then the project can be retrieved from the persistent repository
            getProject(newProject.id, object : SmartCounterDataSource.GetProjectCallback {
                override fun onProjectLoaded(project: Project) {
                    assertThat(project, Is.`is`(newProject))
                }

                override fun onDataNotAvailable() {
                    fail("Callback error")
                }
            })
        }
    }

    @Test
    fun deleteProject_emptyListOfProjects() {
        //given a new project
        val newProject = Project(title = "Test Project", description = "Test Desc")

        with(localDataSource) {
            // When saved into the persistent repository
            saveProject(newProject)
            val callback = mock(SmartCounterDataSource.LoadProjectCallback::class.java)

            // Then delete the project
            deleteProject(newProject.id)

            // Retrieve a list of projects
            getProjects(callback)

            verify(callback).onDataNotAvailable()
            verify(callback, Mockito.never()).onProjectsLoaded(LinkedList<Project>())
        }
    }

    @Test
    fun saveProjects_getListOfProjects() {
        //given a new project
        val newProject = Project(title = "Test Project", description = "Test Desc")
        val newProject1 = Project(title = "Test Project1", description = "Test Desc")

        with(localDataSource) {
            // add two projects
            saveProject(newProject)
            saveProject(newProject1)

            // retrieve a projects list
            getProjects(object : SmartCounterDataSource.LoadProjectCallback {
                override fun onProjectsLoaded(projects: List<Project>) {
                    assertNotNull(projects)
                    assertTrue(projects.size >= 2)

                    var newProject1IdFound = false
                    var newProject2IdFound = false

                    for (project in projects) {
                        if (project.id == newProject.id)
                            newProject1IdFound = true
                        if (project.id == newProject1.id)
                            newProject2IdFound = true
                    }

                    assertTrue(newProject1IdFound)
                    assertTrue(newProject2IdFound)
                }

                override fun onDataNotAvailable() {
                    fail()
                }
            })
        }
    }

}
