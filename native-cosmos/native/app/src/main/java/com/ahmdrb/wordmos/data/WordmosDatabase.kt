package com.ahmdrb.wordmos.data

import android.content.Context
import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Entity(tableName = "documents")
data class DocumentEntity(@PrimaryKey val id: String, val title: String, val createdAt: Long = System.currentTimeMillis(), val updatedAt: Long = createdAt)

@Entity(tableName = "nodes", indices = [Index("documentId"), Index("parentId")])
data class NodeEntity(
    @PrimaryKey val id: String,
    val documentId: String,
    val parentId: String?,
    val title: String,
    val description: String = "",
    val nodeType: String = "concept",
    val position: Int = 0,
    val createdAt: Long = System.currentTimeMillis(),
    val updatedAt: Long = createdAt
)

@Dao
interface WordmosDao {
    @Query("SELECT * FROM documents ORDER BY updatedAt DESC") fun documents(): Flow<List<DocumentEntity>>
    @Query("SELECT * FROM nodes WHERE documentId = :documentId ORDER BY position ASC") fun nodes(documentId: String): Flow<List<NodeEntity>>
    @Insert(onConflict = OnConflictStrategy.REPLACE) suspend fun insertDocument(document: DocumentEntity)
    @Insert(onConflict = OnConflictStrategy.REPLACE) suspend fun insertNodes(nodes: List<NodeEntity>)
    @Update suspend fun updateNode(node: NodeEntity)
    @Delete suspend fun deleteDocument(document: DocumentEntity)
    @Query("DELETE FROM nodes WHERE documentId = :documentId") suspend fun deleteNodes(documentId: String)
}

@Database(entities = [DocumentEntity::class, NodeEntity::class], version = 1, exportSchema = false)
abstract class WordmosDatabase : RoomDatabase() {
    abstract fun dao(): WordmosDao
    companion object { @Volatile private var INSTANCE: WordmosDatabase? = null
        fun get(context: Context) = INSTANCE ?: synchronized(this) { INSTANCE ?: Room.databaseBuilder(context.applicationContext, WordmosDatabase::class.java, "wordmos.db").build().also { INSTANCE = it } }
    }
}
