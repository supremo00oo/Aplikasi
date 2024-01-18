package id.ac.sttpyk.myinventory.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class AlatModel(
    val `data`: List<Data>,
    val message: String,
    val response_code: Int
) :Parcelable{
    @Parcelize
    data class Data(
        val alat: String,
        val created_at: String,
        val id: Int,
        val jumlah: Int,
        val updated_at: String
    ):Parcelable
}