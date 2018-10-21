package moeindeveloper.kotlinroomaac.DataSource

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

//data model class for room table and accessing data:

//define table name using Entity:
@Entity(tableName = "notes")
//defining columns:
data class KNote(@PrimaryKey(autoGenerate = true) val id: Long? = null,
                 val title: String,
                 val description: String)