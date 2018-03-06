package humzaahmad.smartcounter.data.model

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import java.util.*

/**
 * Created by Humza on 3/4/2018.
 * Model for a Project
 *
 * @param id            unique id for project
 * @param title         title for project
 * @param description   description for projecy
 */

@Entity(tableName = "projects")
data class Project @JvmOverloads constructor(
        @PrimaryKey @ColumnInfo (name = "projectid") var id: String = UUID.randomUUID().toString(),
        @ColumnInfo(name = "title") var title: String = "",
        @ColumnInfo(name = "description") var description: String = ""
)