package humzaahmad.smartcounter.data

import humzaahmad.smartcounter.data.model.Counter
import humzaahmad.smartcounter.data.model.Project

/**
 * Created by Humza on 3/5/2018.
 * Interface for Smart Counter Data Source
 */
interface SmartCounterDataSource {
    interface LoadProjectCallback {
        fun onProjectsLoaded() : List<Project>

        fun onDataNotAvailable()
    }

    interface GetProjectCallback {
        fun onProjectLoaded(project : Project)

        fun onDataNotAvailable()
    }

    interface LoadCounterCallback {
        fun onCountersLoaded() : List<Counter>

        fun onDataNotAvailable()
    }

    interface GetCounterCallback {
        fun onCounterLoaded(counter: Counter)

        fun onDataNotAvailable()
    }

    fun getProjects(callback: LoadProjectCallback)

    fun getProject(projectid: String, callback: GetProjectCallback)

    fun saveProject(project: Project)

    fun deleteProject(project: Project)

    fun getCounters(callback: LoadCounterCallback)

    fun getCounter(counterid: String, callback: GetCounterCallback)

    fun saveCounter(counter: Counter)

    fun deleteCounter(counter: Counter)
}
