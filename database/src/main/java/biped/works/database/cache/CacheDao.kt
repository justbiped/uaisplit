package biped.works.database.cache

import androidx.room.ColumnInfo
import androidx.room.Dao
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.PrimaryKey
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import kotlinx.serialization.modules.SerializersModule

@Dao
interface CacheDao {

    @Query("SELECT* FROM cache")
    fun observeCache(): Flow<CacheEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(entity: CacheEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(entity: List<CacheEntity>)

    @Query("SELECT * FROM cache WHERE id = :id LIMIT 1")
    suspend fun get(id: String): CacheEntity?

    @Query("SELECT * FROM cache WHERE id IN(:ids)")
    suspend fun get(ids: List<String>): List<CacheEntity>

    @Query("SELECT * FROM cache WHERE id = :id LIMIT 1")
    suspend fun observe(id: String): Flow<CacheEntity?>

    @Query("SELECT * FROM cache WHERE id IN (:ids)")
    suspend fun observe(ids: List<String>): Flow<List<CacheEntity>>

    @Query("DELETE FROM cache WHERE datetime(updated / 1000, 'unixepoch', 'utc') <= datetime('now','utc',:time) AND id NOT IN (:idsToMaintain)")
    suspend fun delete(time: String, idsToMaintain: List<String> = emptyList())

}

@Entity(tableName = "cache")
data class CacheEntity(
    @PrimaryKey @ColumnInfo(name = "id") val id: String,
    @ColumnInfo(name = "search_key") val searchKey: String,
    //   @ColumnInfo(name = "type") val type: String,
    @ColumnInfo(name = "updated") val updated: Long,
    @ColumnInfo(name = "content") val content: String
)

interface Cacheable {
    val id: String
    val searchKey: String
}

@Serializable
@SerialName("ItemToCacheA")
data class ItemToCacheA(
    override val id: String,
    val title: String,
    val description: String
) : Cacheable {
    override val searchKey: String = "$title-$description"
    //override val type: String = this::class.java.simpleName
}

@Serializable
@SerialName("ItemToCacheB")
data class ItemToCacheB(
    override val id: String,
    val title: String,
    val description: String,
    val age: Int
) : Cacheable {
    override val searchKey: String = "$title-$description-$age"
    // override val type: String = this::class.java.simpleName
}

class CacheDatasource(private val cacheDao: CacheDao) {

    suspend fun insert(cacheable: Cacheable, module: SerializersModule) {
        val cacheEntity = cacheable.toEntity(module)
        cacheDao.insert(cacheEntity)
    }

    suspend fun insert(cacheables: List<Cacheable>, module: SerializersModule) {
        val cacheEntities = cacheables.map { it.toEntity(module) }
        cacheDao.insert(cacheEntities)
    }

    suspend fun get(id: String, module: SerializersModule): Cacheable {
        val entity = cacheDao.get(id) ?: throw Exception("Entity not found")
        return entity.toCacheable(module)
    }

    private fun Cacheable.toEntity(module: SerializersModule): CacheEntity {
        val parser = Json { serializersModule = module }
        val content = parser.encodeToString(this)

        return CacheEntity(
            id = id,
            searchKey = searchKey,
            updated = System.currentTimeMillis(),
            content = content
        )
    }

    private fun CacheEntity.toCacheable(module: SerializersModule): Cacheable {
        val parser = Json { serializersModule = module }
        return parser.decodeFromString(content)
    }
}