package humzaahmad.smartcounter.projects

import humzaahmad.smartcounter.BasePresenter
import humzaahmad.smartcounter.BaseView
import humzaahmad.smartcounter.data.model.Project

/**
 * Created by Humza on 3/21/2018.
 */
interface ProjectsContract {
    interface View : BaseView<Presenter> {
        fun showProjects(projects: List<Project>)

        fun showAddProject()

        fun showProjectDetails(projectId: String)

        fun showProjectLoadingError()

        fun showNoProjects()
    }

    interface Presenter : BasePresenter {
        fun loadProjects()

        fun addNewProject()

        fun openProjectDetails(requestedProject : Project)

        fun result(requestCode: Int, resultCode: Int)
    }
}