package humzaahmad.smartcounter.projects

import android.app.Fragment
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.ListView
import android.widget.TextView
import humzaahmad.smartcounter.R
import humzaahmad.smartcounter.data.model.Project
import java.util.ArrayList

/**
 * Created by Humza on 3/22/2018.
 */
class ProjectsFragment : Fragment(), ProjectsContract.View {

    override lateinit var presenter: ProjectsContract.Presenter

    private lateinit var noProjectsView: View
    private lateinit var noProjectsIcon: ImageView
    private lateinit var noProjectsMainView: TextView
    private lateinit var noProjectsAddView: TextView
    private lateinit var projectsView: ListView

    /**
     * Listener for clicks on Projects
     */
    internal var projectItemListener: ProjectItemListener = object : ProjectItemListener {
        override fun onProjectClick(clickedProject: Project) {
            presenter.openProjectDetails(clickedProject)
        }
    }

    private val listAdapter = ProjectsAdapter(ArrayList(0), projectItemListener)

    override fun onResume() {
        super.onResume()
        presenter.start()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        presenter.result(requestCode, resultCode)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val root = inflater?.inflate(R.layout.fragment_projects, container, false)

        // set up projects view
        with(root){
            val listview = this?.findViewById<ListView>(R.id.projects_list)?.apply{adapter = listAdapter}
        }

        return root
    }

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

    private class ProjectsAdapter(projects : List<Project>, private val projectItemListener: ProjectItemListener)
        : BaseAdapter() {
        override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

        override fun getItem(p0: Int): Any {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

        override fun getItemId(p0: Int): Long {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

        override fun getCount(): Int {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }


    }

    interface ProjectItemListener {
        fun onProjectClick(clickedProject: Project)
    }

    companion object {
        fun newInstance() = ProjectsFragment()
    }

}