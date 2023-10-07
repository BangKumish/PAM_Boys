package com.omkumis.projectpam.ui.helper

import com.omkumis.projectpam.R
import com.omkumis.projectpam.ui.skill.Skill
import com.omkumis.projectpam.ui.skill.SkillFragment

object ConstraintUtil {
    fun getSkillData(fragment: SkillFragment): ArrayList<Skill> {
        val skillArrayList = ArrayList<Skill>()
        val skills = fragment.resources.getStringArray(R.array.skill_str_array)
        val images = fragment.resources.obtainTypedArray(R.array.skill_img_array)
        for (i in skills.indices) {
            skillArrayList.add(Skill(images.getResourceId(i, 0), skills[i]))
        }
        images.recycle()
        return skillArrayList
    }
}