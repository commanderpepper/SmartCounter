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
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getProject(projectid: String, callback: SmartCounterDataSource.GetProjectCallback) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun saveProject(project: Project) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun deleteProject(project: Project) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getCounters(callback: SmartCounterDataSource.LoadCounterCallback) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getCounter(counterid: String, callback: SmartCounterDataSource.GetCounterCallback) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun saveCounter(counter: Counter) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun deleteCounter(counter: Counter) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    companion object {
        private val INSTANCE = SmartCounterLocalDataSource


    }

}