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

    interface LoadCounterCallbacl {
        fun onCountersLoaded() : List<Counter>

        fun onDataNotAvailable()
    }

    interface GetCounterCallback {
        fun onCounterLoaded(counter: Counter)

        fun onDataNotAvailable()
    }

    
}
