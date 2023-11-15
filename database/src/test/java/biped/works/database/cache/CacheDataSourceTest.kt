package biped.works.database.cache

import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.test.runTest
import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.modules.polymorphic
import kotlinx.serialization.modules.subclass
import org.junit.Before
import org.junit.Test

class CacheDataSourceTest {
    @MockK lateinit var cacheDao: CacheDao

    lateinit var cacheableDataSource: CacheDatasource

    @Before
    fun setUp() {
        MockKAnnotations.init(this, relaxed = true)
        cacheableDataSource = CacheDatasource(cacheDao)
    }

    @Test
    fun `serialize cacheable to cache entity before insert into database`() = runTest {
        val itemA = ItemToCacheA("itemA", "Item A Title", "Description item A")
        cacheableDataSource.insert(
            itemA,
            SerializersModule {
                polymorphic(Cacheable::class) {
                    subclass(ItemToCacheA::class)
                    subclass(ItemToCacheB::class)
                }
            }
        )

        coVerify {
            cacheDao.insert(withArg<CacheEntity> {
                print(it.content)
            })
        }
    }

    @Test
    fun `get serialize cacheable to cache entity before insert into database`() = runTest {
        val itemA = ItemToCacheA("itemA", "Item A Title", "Description item A")

        val entity = CacheEntity(
            "itemA",
            "Item A Title",
            System.currentTimeMillis(),
            """{"type":"ItemToCacheA","id":"itemA","title":"Item A Title","description":"Description item A"}"""
        )

        coEvery { cacheDao.get("itemA") } returns entity

        val cacheable = cacheableDataSource.get(
            "itemA",
            SerializersModule {
                polymorphic(Cacheable::class) {
                    subclass(ItemToCacheA::class)
                    subclass(ItemToCacheB::class)
                }
            }
        )

        print(cacheable)
    }

}