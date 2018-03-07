package au.com.cognizant.cognizantdev.feature.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName


data class AboutCanada(
        @SerializedName("title") val title: String,
        @SerializedName("rows") val facts: MutableList<Fact>
) : Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readString(),
            parcel.createTypedArrayList(Fact))

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(title)
        parcel.writeTypedList(facts)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<AboutCanada> {
        override fun createFromParcel(parcel: Parcel): AboutCanada {
            return AboutCanada(parcel)
        }

        override fun newArray(size: Int): Array<AboutCanada?> {
            return arrayOfNulls(size)
        }
    }
}

data class Fact(
        @SerializedName("title") val title: String?,
        @SerializedName("description") val description: String?,
        @SerializedName("imageHref") val imageHref: String?
) : Parcelable {

    val isNull: Boolean
        get() = title == null && description == null && imageHref == null

    constructor(parcel: Parcel) : this(
            parcel.readString(),
            parcel.readString(),
            parcel.readString())

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(title)
        parcel.writeString(description)
        parcel.writeString(imageHref)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Fact> {
        override fun createFromParcel(parcel: Parcel): Fact {
            return Fact(parcel)
        }

        override fun newArray(size: Int): Array<Fact?> {
            return arrayOfNulls(size)
        }
    }

}