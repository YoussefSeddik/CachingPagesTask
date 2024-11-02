package com.youssef.seddik.data.repository

import com.youssef.seddik.data.dao.AbilityEntity
import com.youssef.seddik.data.dao.Dao
import com.youssef.seddik.network.ApiService
import kotlinx.coroutines.delay
import java.lang.Exception
import javax.inject.Inject

class GetAbilitiesRepo @Inject constructor(
    private val apiService: ApiService,
    private val dao: Dao
) {
    private var currentPage = 0
    private val limit = 20 // Define the number of items per page

    suspend fun getAbilitiesRepo(page: Int): List<AbilityEntity> {
        // Try to get data from the database first
        val cachedAbilities = dao.getAbilitiesByPage(page)
        if (cachedAbilities.isNotEmpty()) return cachedAbilities

        // If not cached, fetch from network
        val offset = page * limit
        val response = apiService.getAbilities(offset, limit)
        val abilities = response.results.map { AbilityEntity(0, it.name, it.url, page) }

        // Cache the data in Room
        dao.insertAbilities(abilities)
        currentPage = page
        return abilities
    }
}