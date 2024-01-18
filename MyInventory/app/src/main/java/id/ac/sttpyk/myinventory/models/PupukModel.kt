package id.ac.sttpyk.myinventory.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class PupukModel(
    val `data`: List<Data>,
    val message: String,
    val response_code: Int
) :Parcelable{
    @Parcelize
    data class Data(
        val created_at: String,
        val id: Int,
        val jumlah: Int,
        val pupuk: String,
        val updated_at: String
    ):Parcelable
}