package humzaahmad.smartcounter.data

import humzaahmad.smartcounter.data.model.Counter
import humzaahmad.smartcounter.data.model.Project

interface SmartCounterDataSource {
    interface LoadProjectCallback {
        fun onProjectsLoaded(projects: List<Project>)

        fun onDataNotAvailable()
    }

    interface GetProjectCallback {
        fun onProjectLoaded(project : Project)

        fun onDataNotAvailable()
    }

    interface LoadCounterCallback {
        fun onCountersLoaded(counters: List<Counter>)

        fun onDataNotAvailable()
    }

    interface GetCounterCallback {
        fun onCounterLoaded(counter: Counter)

        fun onDataNotAvailable()
    }

    fun getProjects(callback: LoadProjectCallback)

    fun getProject(projectid: String, callback: GetProjectCallback)

    fun saveProject(project: Project)

    fun deleteProject(projectId: String)

    fun getCounters(projectId: String, callback: LoadCounterCallback)

    fun getCounter(counterid: String, callback: GetCounterCallback)

    fun saveCounter(counter: Counter)

    fun deleteCounter(counterId: String)
}
