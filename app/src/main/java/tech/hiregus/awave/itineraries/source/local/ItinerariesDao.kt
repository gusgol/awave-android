package tech.hiregus.awave.itineraries.source.local

import androidx.room.*
import tech.hiregus.awave.itineraries.Itinerary

@Dao
interface ItinerariesDao {

    /**
     * Select all itineraries from the itineraries table.
     *
     * @return all itineraries.
     */
    @Query("SELECT * FROM itineraries")
    suspend fun getAll(): List<Itinerary>

    /**
     * Select an itinerary by id.
     *
     * @param id the itinerary id.
     * @return the task with taskId.
     */
    @Query("SELECT * FROM itineraries WHERE id = :id")
    suspend fun getItineraryById(id: String): Itinerary?

    /**
     * Insert an itinerary in the database. If the itinerary already exists, replace it.
     *
     * @param itinerary the itinerary to be inserted.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertItinerary(itinerary: Itinerary)

    /**
     * Update an itinerary.
     *
     * @param itinerary itinerary to be updated
     * @return the number of itineraries updated. This should always be 1.
     */
    @Update
    suspend fun updateItinerary(itinerary: Itinerary): Int

    /**
     * Delete an itinerary
     *
     * @param itinerary itinerary to be deleted
     */
    @Delete
    suspend fun delete(itinerary: Itinerary)

}