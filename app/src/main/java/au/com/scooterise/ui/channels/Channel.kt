package au.com.scooterise.ui.channels

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey
import org.bson.types.ObjectId;

open class Channel(
    @PrimaryKey var _id: ObjectId? = null,
    var id: Long? = null,
    var name: String? = null
): RealmObject() {}