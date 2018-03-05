package humzaahmad.smartcounter.data

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.ForeignKey
import android.arch.persistence.room.ForeignKey.CASCADE
import android.arch.persistence.room.PrimaryKey
import java.util.*

/**
 * Created by Humza on 3/4/2018.
 */
@Entity (tableName = "counters",
        foreignKeys = (arrayOf(ForeignKey(entity = Project::class,
                parentColumns = arrayOf("projectid"),
                childColumns = arrayOf("projectid"),
                onDelete = CASCADE
        ))))
data class Counter @JvmOverloads constructor(
        @PrimaryKey @ColumnInfo (name = "counterid") var id: String = UUID.randomUUID().toString(),
        @ColumnInfo (name = "projectid") var projectid: String = ""

){
    @ColumnInfo (name = "projectid") var parentCounterId: String = ""
    @ColumnInfo (name = "count") var count: Long = 0
}