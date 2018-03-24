package humzaahmad.smartcounter.projects

import android.app.Fragment
import humzaahmad.smartcounter.data.model.Project

/**
 * Created by Humza on 3/22/2018.
 */
class ProjectsFragment : Fragment(), ProjectsContract.View {

    override lateinit var presenter: ProjectsContract.Presenter

    /**
     * Listener for clicks on Projects
     */

    override fun showProjects(projects: List<Project>) {
    }

    override fun showAddProject() {
    }

    override fun showProjectDetails(projectId: String) {
    }

    override fun showProjectLoadingError() {
    }

    override fun showNoProjects() {
    }

    interface ProjectItemListener {
        fun onProjectClick(clickedProject : Project)
    }

    companion object {
        fun newInstance() = ProjectsFragment()
    }

}