package co.deltapay.core.model

import android.os.Parcel
import android.os.Parcelable
import java.io.Serializable

data class OnBoardingData(
    val lang: String = "en",
    val docType: String = "ID",
    val firstName: String = "",
    val lastName: String = "",
    val id: String = "",
    val dateOfBirth: String = "",
    var customerId: String = "",
    var documentType: String = "",
    var phoneNumber: String = "",
    var email: String = ""
): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(lang)
        parcel.writeString(docType)
        parcel.writeString(firstName)
        parcel.writeString(lastName)
        parcel.writeString(id)
        parcel.writeString(dateOfBirth)
        parcel.writeString(customerId)
        parcel.writeString(documentType)
        parcel.writeString(phoneNumber)
        parcel.writeString(email)
    }

    override fun describeContents(): Int {
        return super.hashCode()
    }

    companion object CREATOR : Parcelable.Creator<OnBoardingData> {
        override fun createFromParcel(parcel: Parcel): OnBoardingData {
            return OnBoardingData(parcel)
        }

        override fun newArray(size: Int): Array<OnBoardingData?> {
            return arrayOfNulls(size)
        }
    }
}
