package humzaahmad.smartcounter.projects

import android.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toolbar
import humzaahmad.smartcounter.R
import humzaahmad.smartcounter.data.ProjectsDao
import humzaahmad.smartcounter.data.SmartCounterDatabase
import humzaahmad.smartcounter.data.SmartCounterLocalDataSource
import humzaahmad.smartcounter.util.AppExecutors

class ProjectsActivity : AppCompatActivity() {

    private lateinit var projectPresenter: ProjectsPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_projects)

        //val toolbar: android.support.v7.widget.Toolbar = findViewById(R.id.toolbar)
        //setSupportActionBar(toolbar)
        //toolbar.title = "Projects"

        //var projectsDatabase = SmartCounterDatabase.getInstance(applicationContext)

        //val projectsView = Fragment

        /* Create the presenter
        projectPresenter = ProjectsPresenter(SmartCounterLocalDataSource.getInstance
        (AppExecutors(), projectsDatabase.projectsDao(), projectsDatabase.countersDao()),
                projectsView)
                */
    }
}
