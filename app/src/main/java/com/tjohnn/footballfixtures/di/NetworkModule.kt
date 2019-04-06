package com.tjohnn.footballfixtures.di

import android.util.Log
import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import com.tjohnn.footballfixtures.data.source.remote.ApiService
import com.tjohnn.footballfixtures.utils.Constants
import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

private const val TAG : String = "NetworkModule"

@Module
class NetworkModule {


    @Singleton
    @Provides
    fun provideApiService(okHttpClient: OkHttpClient, gson: Gson): ApiService {
        val baseUrl = Constants.API_BASE_URL
        val retrofitBuilder: Retrofit.Builder = Retrofit.Builder()
        retrofitBuilder.baseUrl(baseUrl)
        retrofitBuilder.addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        retrofitBuilder.client(okHttpClient)
        retrofitBuilder.addConverterFactory(GsonConverterFactory.create(gson))
        return retrofitBuilder.build().create(ApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideOkhttp(interceptor: Interceptor, httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
        val builder = OkHttpClient.Builder()
        builder.connectTimeout(15, TimeUnit.SECONDS)
                .writeTimeout(15, TimeUnit.SECONDS)
                .readTimeout(15, TimeUnit.SECONDS)
                .addInterceptor(interceptor)
                .addInterceptor(httpLoggingInterceptor)
        return builder.build()
    }


    /**
     * Provides interceptor that injects api key as a parameter for every request
      */
    @Provides
    @Singleton
    fun provideInterceptor(): Interceptor {
        return Interceptor{ chain ->
            val original = chain.request()

            val request = original.newBuilder()
                    .header("X-Auth-Token", Constants.API_KEY)
                    .build()


            return@Interceptor chain.proceed(request)
        }
    }

    /**
     * provides gson for deserializing json string to objects
     */
    @Provides
    @Singleton
    fun provideGson(): Gson {
        val gsonBuilder = GsonBuilder()
        gsonBuilder.setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)

        return gsonBuilder.create()
    }

    /**
     * provides logging for retrofit requests
     */
    @Provides
    @Singleton
    fun httpLoggingInterceptor(): HttpLoggingInterceptor {
        val httpLoggingInterceptor = HttpLoggingInterceptor { message -> Log.d(TAG, message) }
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return httpLoggingInterceptor
    }

}
