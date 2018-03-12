package humzaahmad.smartcounter.data.model

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.ForeignKey
import android.arch.persistence.room.ForeignKey.CASCADE
import android.arch.persistence.room.PrimaryKey
import java.util.*

/**
 * Created by Humza on 3/4/2018.
 * @param id - the unique id for a counter
 * @param projectid - the project id associated with a counter
 * @param parentCounterId - parent counter id
 * @param count - count of the counter
 */
@Entity (tableName = "counters",
        foreignKeys = (arrayOf(ForeignKey(entity = Project::class,
                parentColumns = arrayOf("id"),
                childColumns = arrayOf("projectid"),
                onDelete = CASCADE
        ))))
data class Counter @JvmOverloads constructor(
        @PrimaryKey @ColumnInfo (name = "counterid") var id: String = UUID.randomUUID().toString(),
        @ColumnInfo (name = "projectid") var projectid: String = ""

){
    @ColumnInfo (name = "parentCounterId") var parentCounterId: String = ""
    @ColumnInfo (name = "count") var count: Long = 0
}