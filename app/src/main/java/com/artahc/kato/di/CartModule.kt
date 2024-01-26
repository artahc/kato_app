package com.artahc.kato.di

import android.content.Context
import androidx.room.Room
import com.artahc.kato.domain.repository.CartRepository
import com.artahc.kato.data.repository.CartRepositoryImpl
import com.artahc.kato.data.database.KatoDatabase
import com.artahc.kato.data.database.dao.CartDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CartModule {
    @Provides
    @Singleton
    fun providesKatoDatabase(
        @ApplicationContext context: Context,
    ): KatoDatabase = Room.databaseBuilder(
        context,
        KatoDatabase::class.java,
        "kato-db",
    ).build()

    @Provides
    fun providesCartDao(db: KatoDatabase): CartDao {
        return db.cartDao()
    }

    @Provides
    @Singleton
    fun provideCartRepository(cartDao: CartDao): CartRepository {
        return CartRepositoryImpl(cartDao = cartDao, Dispatchers.IO)
    }

}