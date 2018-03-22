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
    }

    interface Presenter : BasePresenter {
        fun loadProject()

        fun addNewProject()

        fun openProjectDetails()
    }
}