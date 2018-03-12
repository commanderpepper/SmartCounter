package humzaahmad.smartcounter.data

import android.arch.persistence.room.*
import humzaahmad.smartcounter.data.model.Project

/**
 * Created by Humza on 3/4/2018.
 * Data access object for the projects table
 */

@Dao
interface ProjectsDao {
    /**
     * Select all projects from the projects table
     *
     * @return A list of all projects
     */
    @Query("SELECT * FROM projects") fun getAllProjects() : List<Project>

    /**
     * Insert a project into the projects table. If the project already exists, replace it.
     *
     * @param project to be inserted
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE) fun insertProject(project: Project)

    /**
     * Update a project
     *
     * @param project to be updated
     * @return number of projects being updated. Should return 1
     */
    @Update() fun updateProject(project: Project) : Int

    /**
     * Delete a project
     *
     * @param projectid of project to be deleted
     * @return number of projects being deleted. Should return 1
     */
    @Query("DELETE FROM projects WHERE id = :projectid") fun deleteProject(projectid : String) : Int
}
