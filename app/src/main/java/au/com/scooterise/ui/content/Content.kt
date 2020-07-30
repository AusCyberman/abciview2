package au.com.scooterise.ui.content

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey
import io.realm.annotations.Required
import org.bson.types.ObjectId;

open class Content(
    @PrimaryKey var _id: ObjectId? = null,
    var CONTENT_TYPE: String? = null,
    var category: Long? = null,
    var channel: Long? = null,
    var classification: String? = null,
    var description: String? = null,
    var id: Long? = null,
    var image: String? = null,
    var isAdult: Boolean? = null,
    var isPopular: Boolean? = null,
    var name: String? = null,
    var partition: String = "",
    var trailer: Content_trailer? = null,

    var videos: RealmList<Content_videos> = RealmList()
): RealmObject() {}