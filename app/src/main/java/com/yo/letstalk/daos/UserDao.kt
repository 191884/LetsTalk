package com.yo.letstalk.daos

import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import com.yo.letstalk.models.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class UserDao {

    private val db = FirebaseFirestore.getInstance()
    private val usersCollection = db.collection("users")

    fun addUser(user: User){
         user.let {
             GlobalScope.launch(Dispatchers.IO) {
                 usersCollection.document(user.uid).set(it)
             }
        }
    }

    fun getUserById(uid: String): Task<DocumentSnapshot> {
            return usersCollection.document(uid).get()
    }

}
