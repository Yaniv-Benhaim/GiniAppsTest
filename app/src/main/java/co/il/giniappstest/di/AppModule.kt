package co.il.giniappstest.di

import co.il.giniappstest.networking.NumberApi
import co.il.giniappstest.other.Constants.BASE_URL
import co.il.giniappstest.repositories.NumberRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun providesLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    }

    @Provides
    fun provideOkHttpClient(loggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
        val okHttpClient = OkHttpClient().newBuilder()

        okHttpClient.callTimeout(40, java.util.concurrent.TimeUnit.SECONDS)
        okHttpClient.connectTimeout(40, java.util.concurrent.TimeUnit.SECONDS)
        okHttpClient.readTimeout(40, java.util.concurrent.TimeUnit.SECONDS)
        okHttpClient.writeTimeout(40, java.util.concurrent.TimeUnit.SECONDS)
        okHttpClient.addInterceptor(loggingInterceptor)
        okHttpClient.build()
        return okHttpClient.build()
    }

    @Singleton
    @Provides
    fun provideNumberApi(okHttpClient: OkHttpClient): NumberApi {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .build()
            .create(NumberApi::class.java)
    }

    @Singleton
    @Provides
    fun provideMovieRepository(numberApi: NumberApi) = NumberRepository(numberApi)
}