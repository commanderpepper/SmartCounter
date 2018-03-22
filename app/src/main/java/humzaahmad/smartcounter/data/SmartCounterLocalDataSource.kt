package humzaahmad.smartcounter.data

import android.support.annotation.VisibleForTesting
import humzaahmad.smartcounter.data.model.Counter
import humzaahmad.smartcounter.data.model.Project
import humzaahmad.smartcounter.util.AppExecutors

/**
 * Created by Humza on 3/5/2018.
 * This is the concrete implementation of the SmartCounterDatabase
 */
class SmartCounterLocalDataSource private constructor(
        val appExecutors: AppExecutors,
        val projectsDao: ProjectsDao,
        val countersDao: CountersDao
) : SmartCounterDataSource {

    override fun getProjects(callback: SmartCounterDataSource.LoadProjectCallback) {
        appExecutors.diskIO.execute {
            val projects = projectsDao.getAllProjects()
            appExecutors.mainThread.execute {
                if (projects.isEmpty()) {
                    callback.onDataNotAvailable()
                } else {
                    callback.onProjectsLoaded(projects)
                }
            }
        }
    }

    override fun getProject(projectid: String, callback: SmartCounterDataSource.GetProjectCallback) {
        appExecutors.diskIO.execute {
            val project = projectsDao.getProjectById(projectid)
            appExecutors.mainThread.execute {
                if (project != null) {
                    callback.onProjectLoaded(project)
                } else {
                    callback.onDataNotAvailable()
                }
            }
        }
    }

    override fun saveProject(project: Project) {
        appExecutors.diskIO.execute { projectsDao.insertProject(project) }
    }

    override fun deleteProject(projectId: String) {
        appExecutors.diskIO.execute { projectsDao.deleteProject(projectId) }
    }

    override fun getCounters(projectId: String, callback: SmartCounterDataSource.LoadCounterCallback) {
        appExecutors.diskIO.execute {
            val counters = countersDao.getCounters(projectId)
            appExecutors.mainThread.execute {
                if (counters.isEmpty()) {
                    callback.onDataNotAvailable()
                } else {
                    callback.onCountersLoaded(counters)
                }
            }
        }
    }

    override fun getCounter(counterid: String, callback: SmartCounterDataSource.GetCounterCallback) {
        appExecutors.diskIO.execute {
            val counter = countersDao.getCounter(counterid)
            appExecutors.mainThread.execute {
                if (counter != null) {
                    callback.onCounterLoaded(counter)
                } else {
                    callback.onDataNotAvailable()
                }
            }
        }
    }

    override fun saveCounter(counter: Counter) {
        appExecutors.diskIO.execute { countersDao.insertCounter(counter) }
    }

    override fun deleteCounter(counterId: String) {
        appExecutors.diskIO.execute { countersDao.deleteCounter(counterId) }
    }

    companion object {
        private var INSTANCE: SmartCounterLocalDataSource? = null

        @JvmStatic
        fun getInstance(appExecutors: AppExecutors, projectDao: ProjectsDao, countersDao: CountersDao)
                : SmartCounterLocalDataSource {
            if (INSTANCE == null) {
                synchronized(SmartCounterLocalDataSource::javaClass) {
                    INSTANCE = SmartCounterLocalDataSource(appExecutors, projectDao, countersDao)
                }
            }
            return INSTANCE!!
        }

        @VisibleForTesting
        fun clearInstance() {
            INSTANCE = null
        }
    }

}