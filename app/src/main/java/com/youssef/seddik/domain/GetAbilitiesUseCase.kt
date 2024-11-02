package com.youssef.seddik.domain

import com.youssef.seddik.data.Ability
import com.youssef.seddik.data.repository.GetAbilitiesRepo
import javax.inject.Inject

class GetAbilitiesUseCase @Inject constructor(private val getAbilitiesRepo: GetAbilitiesRepo) {

    suspend fun getAbilities(page: Int): List<Ability> {
        return getAbilitiesRepo.getAbilitiesRepo(page).map {
            it.toAbility()
        }
    }
}