package com.wisnu.foundation.corejson

import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.JsonReader
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.squareup.moshi.internal.Util

import java.io.InputStream
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type
import okio.Buffer
import okio.BufferedSource

val moshiBuild by lazy { Moshi.Builder().build() }

inline fun <reified T : Any> String.fromJson(): T? = getAdapter<T>().fromJson(this)
inline fun <reified T> BufferedSource.fromJson(): T? = getAdapter<T>().fromJson(this)
inline fun <reified T> InputStream.fromJson(): T? = getAdapter<T>().fromJson(Buffer().readFrom(this))
inline fun <reified T> JsonReader.fromJson(): T? = getAdapter<T>().fromJson(this)

inline fun <reified T> String.listFromJson(): MutableList<T> = fromJson(Types.newParameterizedType(MutableList::class.java, T::class.java)) ?: mutableListOf()
inline fun <reified T> BufferedSource.listFromJson(): MutableList<T> = fromJson(Types.newParameterizedType(MutableList::class.java, T::class.java)) ?: mutableListOf()
inline fun <reified T> InputStream.listFromJson(): MutableList<T> = fromJson(Types.newParameterizedType(MutableList::class.java, T::class.java)) ?: mutableListOf()
inline fun <reified T> JsonReader.listFromJson(): MutableList<T> = fromJson(Types.newParameterizedType(MutableList::class.java, T::class.java)) ?: mutableListOf()
inline fun <reified K, reified V> String.mapFromJson(): MutableMap<K, V> = fromJson(Types.newParameterizedType(MutableMap::class.java, K::class.java, V::class.java)) ?: mutableMapOf()
inline fun <reified K, reified V> BufferedSource.mapFromJson(): MutableMap<K, V> = fromJson(Types.newParameterizedType(MutableMap::class.java, K::class.java, V::class.java)) ?: mutableMapOf()
inline fun <reified K, reified V> InputStream.mapFromJson(): MutableMap<K, V> = fromJson(Types.newParameterizedType(MutableMap::class.java, K::class.java, V::class.java)) ?: mutableMapOf()
inline fun <reified K, reified V> JsonReader.mapFromJson(): MutableMap<K, V> = fromJson(Types.newParameterizedType(MutableMap::class.java, K::class.java, V::class.java)) ?: mutableMapOf()
inline fun <reified T> toJson(t: T) = getAdapter<T>().toJson(t) ?: ""

fun <T> String.fromJson(type: Type): T? = getAdapter<T>(type).fromJson(this)
fun <T> BufferedSource.fromJson(type: Type): T? = getAdapter<T>(type).fromJson(this)
fun <T> InputStream.fromJson(type: Type): T? = getAdapter<T>(type).fromJson(Buffer().readFrom(this))
fun <T> JsonReader.fromJson(type: Type): T? = getAdapter<T>(type).fromJson(this)

fun <T> getAdapter(type: Type): JsonAdapter<T> = moshiBuild.adapter(type)
inline fun <reified T> getAdapter(): JsonAdapter<T> = moshiBuild.adapter(object : TypeToken<T>() {}.type)

abstract class TypeToken<T> {
    val type: Type
        get() = run {
            val superclass = javaClass.genericSuperclass
            Util.canonicalize((superclass as ParameterizedType).actualTypeArguments[0])
        }
}

fun String.prettyJson(): String {
    val source: Buffer = Buffer().writeUtf8(this)
    val reader: JsonReader = JsonReader.of(source)
    val value: Any? = reader.readJsonValue()
    val adapter = moshiBuild.adapter(Any::class.java).indent("    ")
    return adapter.toJson(value)
}
