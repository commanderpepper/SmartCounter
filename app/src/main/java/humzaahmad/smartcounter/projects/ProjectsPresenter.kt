package humzaahmad.smartcounter.projects

import humzaahmad.smartcounter.data.SmartCounterDataSource
import humzaahmad.smartcounter.data.SmartCounterLocalDataSource
import humzaahmad.smartcounter.data.model.Project

/**
 * Created by Humza on 3/21/2018.
 */
class ProjectsPresenter(val projectDataSource: SmartCounterLocalDataSource, val projectView: ProjectsContract.View)
    : ProjectsContract.Presenter {

    override fun start() {
        loadProjects()
    }

    override fun loadProjects() {
        projectDataSource.getProjects(object : SmartCounterDataSource.LoadProjectCallback {
            override fun onProjectsLoaded(projects: List<Project>) {
                if (projects.isEmpty())
                    projectView.showNoProjects()
                else
                    projectView.showProjects(projects)
            }

            override fun onDataNotAvailable() {
                projectView.showProjectLoadingError()
            }
        })
    }

    override fun addNewProject() {
        projectView.showAddProject()
    }

    override fun openProjectDetails(requestedProject: Project) {
        projectView.showProjectDetails(requestedProject.id)
    }

    override fun result(requestCode: Int, resultCode: Int) {
        //TODO: Add this code
        // If a task was successfully added, show snackbar
    }


}