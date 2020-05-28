package com.mvvmapp.di.module

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import com.google.gson.Gson
import com.mvvmapp.data.repository.AlbumRepositoryImp
import com.mvvmapp.data.repository.PhotoRepositoryImp
import com.mvvmapp.data.source.local.AppDatabase
import com.mvvmapp.data.source.remote.RetrofitService
import com.mvvmapp.domain.repository.AlbumRepository
import com.mvvmapp.domain.repository.PhotoRepository
import com.mvvmapp.util.Constants
import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module(includes = [ApplicationModule::class])
class NetworkModule {

    @Provides
    @Singleton
    fun provideRetrofit(gsonConverterFactory: GsonConverterFactory,
                        rxJava2CallAdapterFactory: RxJava2CallAdapterFactory,
                        okHttpClient: OkHttpClient) : Retrofit {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(gsonConverterFactory)
            .addCallAdapterFactory(rxJava2CallAdapterFactory)
            .client(okHttpClient)
            .build()
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(context: Context, isNetworkAvailable: Boolean): OkHttpClient {
        val cacheSize = (5 * 1024 * 1024).toLong()
        val mCache = Cache(context.cacheDir, cacheSize)
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        val client = OkHttpClient.Builder()
            .cache(mCache)
            .connectTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .addNetworkInterceptor(interceptor)
            .addInterceptor{ chain ->
                var request = chain.request()

                request = if(isNetworkAvailable) request.newBuilder().header("Cache-Control", "public, max-age=" + 5).build()

                else request.newBuilder().header("Cache-Control", "public, only-if-cached, max-stale=" + 60 * 60 * 24 *7).build()
                chain.proceed(request)
            }
        return client.build()
    }


    @Provides
    @Singleton
    fun provideGson(): Gson {
        return Gson()
    }

    @Provides
    @Singleton
    fun providesGsonConverterFactory(): GsonConverterFactory {
        return GsonConverterFactory.create()
    }

    @Provides
    @Singleton
    fun providesRxJavaCallAdapterFactory(): RxJava2CallAdapterFactory {
        return RxJava2CallAdapterFactory.create()
    }

    @Provides
    @Singleton
    fun provideIsNetworkAvailable(context: Context): Boolean {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork: NetworkInfo? = connectivityManager.activeNetworkInfo
        return activeNetwork != null && activeNetwork.isConnected
    }

    @Singleton
    @Provides
    fun provideService(retrofit: Retrofit): RetrofitService {
        return retrofit.create(RetrofitService::class.java)
    }

    @Singleton
    @Provides
    fun provideAlbumRepository(appDatabase: AppDatabase, retrofitService: RetrofitService): AlbumRepository {
        return AlbumRepositoryImp(appDatabase, retrofitService)
    }

    @Singleton
    @Provides
    fun providePhotoRepository(appDatabase: AppDatabase, retrofitService: RetrofitService): PhotoRepository {
        return PhotoRepositoryImp(appDatabase, retrofitService)
    }
}