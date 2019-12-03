package com.example.mvvmsamplekotlin.views.appviews.home.model

import android.os.Parcel
import android.os.Parcelable

class RegistrationRequest() : Parcelable{
    lateinit var username: String
    lateinit var password: String

    constructor(parcel: Parcel) : this() {
        username = parcel.readString()!!
        password = parcel.readString()!!
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(username)
        parcel.writeString(password)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<RegistrationRequest> {
        override fun createFromParcel(parcel: Parcel): RegistrationRequest {
            return RegistrationRequest(parcel)
        }

        override fun newArray(size: Int): Array<RegistrationRequest?> {
            return arrayOfNulls(size)
        }
    }


}
