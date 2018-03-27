package humzaahmad.smartcounter.projects

import android.app.Fragment
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.ListView
import android.widget.TextView
import humzaahmad.smartcounter.R
import humzaahmad.smartcounter.addeditproject.AddEditProjectActivity
import humzaahmad.smartcounter.data.model.Project
import kotlinx.android.synthetic.main.fragment_projects.*
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
        val root = inflater!!.inflate(R.layout.fragment_projects, container, false)

        // set up projects view
        with(root) {
            val listview = this.findViewById<ListView>(R.id.projects_list).apply { adapter = listAdapter }

            projectsView = findViewById(R.id.projects_list)

            // Set up no projects view
            noProjectsView = findViewById(R.id.noProjects)
            noProjectsIcon = findViewById(R.id.noProjectsIcon)
            noProjectsMainView = findViewById(R.id.noProjectsMain)
            noProjectsAddView = findViewById(R.id.noProjectsAdd)
        }

        // set up floating action button
        activity.findViewById<FloatingActionButton>(R.id.project_fab).apply {
            setImageResource(R.drawable.ic_add)
            setOnClickListener(presenter.addNewProject())
        }

        return root
    }

    // If there are projects, set the projects and make the projects view visible and the no projects view invisible
    override fun showProjects(projects: List<Project>) {
        listAdapter.projects
        projectsView.visibility = View.VISIBLE
        noProjectsView.visibility = View.INVISIBLE
    }

    override fun showAddProject() {
        val intent = Intent(activity.applicationContext, AddEditProjectActivity::class.java)
        startActivityForResult(intent, AddEditProjectActivity.REQUEST_ADD_TASK)
    }

    override fun showProjectDetails(projectId: String) {
    }

    override fun showProjectLoadingError() {
    }

    override fun showNoProjects() {
        showNoProjectView(resources.getString(R.string.no_projects), R.drawable.ic_insert_drive_file_black_24dp, false)
    }

    private fun showNoProjectView(mainText: String?, iconResource: Int, showAddView: Boolean) {
        projectsView.visibility = View.GONE
        noProjectsView.visibility = View.VISIBLE

        noProjectsMainView.text = mainText
        noProjectsIcon.setImageResource(iconResource)
        noProjectsAddView.visibility = if (showAddView) View.VISIBLE else View.GONE
    }


    private class ProjectsAdapter(projects: List<Project>, private val projectItemListener: ProjectItemListener)
        : BaseAdapter() {

        var projects: List<Project> = projects
            set(projects) {
                field = projects
                notifyDataSetChanged()
            }

        override fun getView(i: Int, view: View?, viewGroup: ViewGroup?): View {
            val project = getItem(i)
            val rowView = view ?: LayoutInflater.from(viewGroup!!.context)
                    .inflate(R.layout.project_item, viewGroup, false)

            // Set the project title
            with(rowView.findViewById<TextView>(R.id.project_title)) {
                text = project.title
            }

            // Set the project description
            with(rowView.findViewById<TextView>(R.id.project_desc)) {
                text = project.description
            }

            return rowView
        }

        override fun getItem(i: Int) = projects[i]

        override fun getItemId(i: Int) = i.toLong()

        override fun getCount() = projects.size

    }

    interface ProjectItemListener {
        fun onProjectClick(clickedProject: Project)
    }

    companion object {
        fun newInstance() = ProjectsFragment()
    }

}