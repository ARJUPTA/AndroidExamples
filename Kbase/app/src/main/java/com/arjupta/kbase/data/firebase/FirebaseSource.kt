package com.arjupta.kbase.data.firebase

import com.google.firebase.firestore.FirebaseFirestore

class FirebaseSource {
    private val firebaseFirestore : FirebaseFirestore by lazy {
        FirebaseFirestore.getInstance()
    }

    fun getData()
}