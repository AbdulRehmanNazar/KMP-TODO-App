package domain

import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey
import org.mongodb.kbson.ObjectId


/**
 * @Author: Abdul Rehman
 * @Date: 26/04/2024.
 */
class ToDoTask : RealmObject {
    @PrimaryKey
    var _id: ObjectId = ObjectId()
    var title: String = ""
    var description: String = ""
    var favourite: Boolean = false
    var completed: Boolean = false

}