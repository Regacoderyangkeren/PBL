package com.example.pbl.Utilites.Authentification

import com.google.firebase.Firebase
import com.google.firebase.auth.auth

// Helper untuk menolonggggg
object AuthHelper {
    private val auth = Firebase.auth

    // true kalo user ke logged in
    val isLoggedIn: Boolean
        get() = auth.currentUser != null

    // false kalo user ke logged out
    val isNotLoggedIn: Boolean
        get() = auth.currentUser == null
}