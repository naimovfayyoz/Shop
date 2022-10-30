package uz.fayyoz.a1shop.di


import com.google.gson.Gson
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import uz.fayyoz.a1shop.BuildConfig
import uz.fayyoz.a1shop.network.LoginService
import uz.fayyoz.a1shop.network.ShopService
import java.util.concurrent.TimeUnit

object RetrofitService {

    private val client: OkHttpClient = OkHttpClient.Builder().also { client ->
        if (BuildConfig.DEBUG) {
            val logging = HttpLoggingInterceptor()
            logging.setLevel(HttpLoggingInterceptor.Level.BODY)
            client.addInterceptor(logging)
        }
    }
        .connectTimeout(60, TimeUnit.SECONDS)
        .readTimeout(60, TimeUnit.SECONDS)
        .addInterceptor { chain ->

            chain.run {
                proceed(request())
                    .newBuilder()
//                    .addHeader("Authorization", "Bearer " +"eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJlbWFpbCI6ImpvaG5AbWFpbC5jb20iLCJzdWIiOjEsImlhdCI6MTY2NTM3MTgyMiwiZXhwIjoxNjY1Mzc1NDIyfQ.lIDoJCX2OZkK21Yoa-JcSAWS_Y8jZf0hthr1THofRE4")
                    .build()
            }
        }.build()

    private val retrofit: Retrofit =
        Retrofit.Builder()
            .client(client)
            .addConverterFactory(GsonConverterFactory.create(Gson()))
            .baseUrl("https://api.escuelajs.co/api/v1/")
            .build()

    val shopService: ShopService = retrofit.create(ShopService::class.java)
    val loginService: LoginService = retrofit.create(LoginService::class.java)

}