package id.ac.sttpyk.myinventory.api

import id.ac.sttpyk.myinventory.models.*
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface Api {
//pupuk

    @FormUrlEncoded
    @POST("login")
    fun login(
        @Field("username") username: String,
        @Field("password") password: String,
    ): Call<LoginModel>

    @FormUrlEncoded
    @POST("pupuk/simpan")
    fun entripupuk(
        @Field("pupuk") pupuk: String,
        @Field("jumlah") jumlah: String
    ): Call<SimpanModel>

    @FormUrlEncoded
    @POST("pupuk/hapus")
    fun hapuspupuk(
        @Field("id") id: Int?
    ): Call<SimpanModel>

    @FormUrlEncoded
    @POST("pupuk/edit")
    fun editpupuk(
        @Field("id") id: Int?,
        @Field("pupuk") pupuk: String,
        @Field("jumlah") jumlah: String
    ): Call<SimpanModel>

    @GET("pupuk/tampil")
    fun tampilpupuk(): Call<PupukModel>

//benih
    @FormUrlEncoded
    @POST("benih/simpan")
    fun entribenih(
        @Field("benih") benih: String,
        @Field("jumlah") jumlah: String
    ): Call<SimpanModel2>

    @FormUrlEncoded
    @POST("benih/hapus")
    fun hapusbenih(
        @Field("id") id: Int?
    ): Call<SimpanModel2>

    @FormUrlEncoded
    @POST("benih/edit")
    fun editbenih(
        @Field("id") id: Int?,
        @Field("benih") benih: String,
        @Field("jumlah") jumlah: String
    ): Call<SimpanModel2>

    @GET("benih/tampil")
    fun tampilbenih(): Call<BenihModel>

//alat
    @FormUrlEncoded
    @POST("alat/simpan")
    fun entrialat(
        @Field("alat") alat: String,
        @Field("jumlah") jumlah: String
    ): Call<SimpanModel3>

    @FormUrlEncoded
    @POST("alat/hapus")
    fun hapusalat(
        @Field("id") id: Int?
    ): Call<SimpanModel3>

    @FormUrlEncoded
    @POST("alat/edit")
    fun editalat(
        @Field("id") id: Int?,
        @Field("alat") alat: String,
        @Field("jumlah") jumlah: String
    ): Call<SimpanModel3>

    @GET("alat/tampil")
    fun tampilalat(): Call<AlatModel>

   companion object {
        fun create (): Api {
            val httpClient = OkHttpClient.Builder()
            var logging = HttpLoggingInterceptor()
            logging.level = HttpLoggingInterceptor.Level.BODY
            httpClient.addInterceptor(logging)

            val retrofit : Retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                //.baseUrl("http://10.0.2.2:8000/api/")
                .baseUrl("http://192.168.2.4:8000/api/")
                .client(httpClient.build())
                .build()

            return retrofit.create(Api::class.java)
        }
    }
}