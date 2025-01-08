package com.aminuolawale.muffassa.data.datasource

import androidx.room.TypeConverter
import com.aminuolawale.muffassa.domain.model.ResourceData
import com.google.common.reflect.TypeToken
import com.google.gson.Gson

class Converter {
    @TypeConverter
    fun resourceDataToJson(resourceData: ResourceData): String {
        return Gson().toJson(resourceData)
    }

    @TypeConverter
    fun jsonToResourceData(string: String): ResourceData {
        val typeToken = object : TypeToken<ResourceData>() {}.type
        return Gson().fromJson(string, typeToken)
    }
}