package com.example.newscompose.di
import android.app.Application
import android.content.Context
import androidx.room.Room
import com.example.newscompose.data.local.dao.ArticleDao
import com.example.newscompose.data.local.database.AppDatabase
import com.example.newscompose.data.manager.LocalUserManagerImpl
import com.example.newscompose.data.remote.NewsApi
import com.example.newscompose.data.repository.ArticleRepositoryImpl
import com.example.newscompose.data.repository.NewsRepositoryImpl
import com.example.newscompose.domain.manager.LocalUserManager
import com.example.newscompose.domain.repository.ArticleRepository
import com.example.newscompose.domain.repository.NewsRepository
import com.example.newscompose.domain.usecases.AppEntryUseCases
import com.example.newscompose.domain.usecases.ReapAppEntry
import com.example.newscompose.domain.usecases.SaveAppEntry
import com.example.newscompose.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

/*
 * Author: Shivang Yadav
 * Created: 6/4/25
 * Description: [Add description here]
 */

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideLocalUserManager(
        application: Application
    ): LocalUserManager= LocalUserManagerImpl(application)

    @Provides
    @Singleton
    fun provideAppEntryUseCases(
        localUseManager: LocalUserManager
    )= AppEntryUseCases(
        readAppEntry = ReapAppEntry(localUseManager),
        saveAppEntry = SaveAppEntry(localUseManager)
    )

    @Provides
    @Singleton
    fun providePhotosApi(): NewsApi = Retrofit.Builder()
        .baseUrl(Constants.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(NewsApi::class.java)

    @Provides
    @Singleton
    fun provideNewsRepository(api: NewsApi): NewsRepository = NewsRepositoryImpl(api)


    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase{

        return Room.databaseBuilder(
            context,
            AppDatabase::class.java, "User.db")
            .build()
    }

    @Provides
    @Singleton
    fun getArticleDao(db: AppDatabase): ArticleDao{
        return db.articleDao()
    }

    @Provides
    @Singleton
    fun provideArticleRepository(
        db: AppDatabase
    ): ArticleRepository {
        return ArticleRepositoryImpl(db)
    }

}