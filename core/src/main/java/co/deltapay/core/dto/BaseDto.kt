package co.deltapay.core.dto

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@JsonClass(generateAdapter = true)
@Parcelize
open class BaseDto<T: Parcelable> (
     @Json(name = "name")
     open val name: String,
     @Json(name = "attributes")
     open val attributes: Attributes<T>
): Parcelable

@JsonClass(generateAdapter = true)
abstract class Attributes<T> (val t: T): Parcelable