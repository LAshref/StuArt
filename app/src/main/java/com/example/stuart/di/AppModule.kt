package com.example.stuart.di

import android.app.Application
import android.content.Context
import android.content.Context.MODE_PRIVATE
import com.example.stuart.firebase.FirebaseCommon
import com.example.stuart.util.Constants.INTRODUCTION_SP
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.firestore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideFirebaseAuth() = FirebaseAuth.getInstance()

    @Provides
    @Singleton
    fun provideFirebaseFirestoreDatabase() = Firebase.firestore

    @Provides
    fun provideIntroductionSP(
        application : Application
    ) = application.getSharedPreferences(INTRODUCTION_SP,MODE_PRIVATE)
    /* INTRODUCTION_SP is declared in the constants File
    MODE_PRIVATE : indicate that only our app can read from this function of  shared Preferences
     */
    @Provides
    @Singleton
    fun provideFirebaseCommon(
        firebaseAuth: FirebaseAuth,
        firestore: FirebaseFirestore

    )=FirebaseCommon(firestore, firebaseAuth)
}