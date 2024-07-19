package uz.androbeck.virtualbank.preferences

import android.content.SharedPreferences
import com.google.gson.Gson
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

fun SharedPreferences.stringNullable(
    defaultValue: String? = null, key: (KProperty<*>) -> String = KProperty<*>::name
): ReadWriteProperty<Any, String?> = object : ReadWriteProperty<Any, String?> {

    override fun getValue(thisRef: Any, property: KProperty<*>): String? =
        getString(key(property), defaultValue)

    override fun setValue(thisRef: Any, property: KProperty<*>, value: String?) {
        edit().putString(key(property), value).apply()
    }
}

fun SharedPreferences.string(
    defaultValue: String = "", key: (KProperty<*>) -> String = KProperty<*>::name
): ReadWriteProperty<Any, String> = object : ReadWriteProperty<Any, String> {
    override fun getValue(
        thisRef: Any, property: KProperty<*>
    ) = getString(key(property), defaultValue) ?: ""

    override fun setValue(
        thisRef: Any, property: KProperty<*>, value: String
    ) = edit().putString(key(property), value).apply()
}

fun SharedPreferences.int(
    defaultValue: Int = 0, key: (KProperty<*>) -> String = KProperty<*>::name
): ReadWriteProperty<Any, Int> = object : ReadWriteProperty<Any, Int> {
    override fun getValue(
        thisRef: Any, property: KProperty<*>
    ) = getInt(key(property), defaultValue)

    override fun setValue(
        thisRef: Any, property: KProperty<*>, value: Int
    ) = edit().putInt(key(property), value).apply()
}

fun SharedPreferences.float(
    defaultValue: Float = 0.0f, key: (KProperty<*>) -> String = KProperty<*>::name
): ReadWriteProperty<Any, Float> = object : ReadWriteProperty<Any, Float> {
    override fun getValue(
        thisRef: Any, property: KProperty<*>
    ) = getFloat(key(property), defaultValue)

    override fun setValue(
        thisRef: Any, property: KProperty<*>, value: Float
    ) = edit().putFloat(key(property), value).apply()
}

fun SharedPreferences.long(
    defaultValue: Long = 0, key: (KProperty<*>) -> String = KProperty<*>::name
): ReadWriteProperty<Any, Long> = object : ReadWriteProperty<Any, Long> {
    override fun getValue(
        thisRef: Any, property: KProperty<*>
    ) = getLong(key(property), defaultValue)

    override fun setValue(
        thisRef: Any, property: KProperty<*>, value: Long
    ) = edit().putLong(key(property), value).apply()
}

fun SharedPreferences.boolean(
    defaultValue: Boolean = false, key: (KProperty<*>) -> String = KProperty<*>::name
): ReadWriteProperty<Any, Boolean> = object : ReadWriteProperty<Any, Boolean> {
    override fun getValue(
        thisRef: Any, property: KProperty<*>
    ) = getBoolean(key(property), defaultValue)

    override fun setValue(
        thisRef: Any, property: KProperty<*>, value: Boolean
    ) = edit().putBoolean(key(property), value).apply()
}

inline fun <reified V> SharedPreferences.customObject(
    gson: Gson,
    crossinline key: (KProperty<*>) -> String = KProperty<*>::name,
): ReadWriteProperty<Any, V?> = object : ReadWriteProperty<Any, V?> {
    override fun getValue(thisRef: Any, property: KProperty<*>): V? {
        val json = getString(key(property), "")
        return gson.fromJson(json, V::class.java)
    }

    override fun setValue(thisRef: Any, property: KProperty<*>, value: V?) {
        val json = gson.toJson(value)
        edit().putString(key(property), json).apply()
    }
}

inline fun <reified T> SharedPreferences.custom(
    gson: Gson, crossinline key: (KProperty<*>) -> String = KProperty<*>::name
): ReadWriteProperty<Any, T?> = object : ReadWriteProperty<Any, T?> {
    override fun getValue(thisRef: Any, property: KProperty<*>): T? {
        val json = getString(key(property), "")
        return if (json.isNullOrBlank()) null
        else gson.fromJson(json, T::class.java)
    }

    override fun setValue(thisRef: Any, property: KProperty<*>, value: T?) {
        if (value != null) edit().putString(key(property), gson.toJson(value)).apply()
        else edit().putString(key(property), "").apply()
    }
}

inline fun <reified T> SharedPreferences.custom(
    gson: Gson, defaultValue: T, crossinline key: (KProperty<*>) -> String = KProperty<*>::name
): ReadWriteProperty<Any, T> = object : ReadWriteProperty<Any, T> {
    override fun getValue(thisRef: Any, property: KProperty<*>): T {
        val json = getString(key(property), "")
        return if (json.isNullOrBlank()) defaultValue
        else gson.fromJson(json, T::class.java)
    }

    override fun setValue(thisRef: Any, property: KProperty<*>, value: T) {
        edit().putString(key(property), gson.toJson(value)).apply()
    }
}





