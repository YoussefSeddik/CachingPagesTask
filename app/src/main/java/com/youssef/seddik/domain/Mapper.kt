package com.youssef.seddik.domain

import com.youssef.seddik.data.Ability
import com.youssef.seddik.data.dao.AbilityEntity


fun AbilityEntity.toAbility() = Ability(name = name, url = "")