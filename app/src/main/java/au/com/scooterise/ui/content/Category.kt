package au.com.scooterise.ui.content

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey
import org.bson.types.ObjectId;

open class Category(
    @PrimaryKey var _id: ObjectId? = null,
    var Name: String? = null,
    var id: Long? = null,
    var partition: String = ""
): RealmObject() {}