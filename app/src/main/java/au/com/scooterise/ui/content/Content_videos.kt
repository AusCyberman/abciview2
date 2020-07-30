package au.com.scooterise.ui.content

import io.realm.RealmObject;
import io.realm.annotations.RealmClass

@RealmClass(embedded = true)
open class Content_videos(
    var Description: String? = null,
    var classification: String? = null,
    var content: Long? = null,
    var id: Long? = null,
    var name: String? = null,
    var turi: String? = null
): RealmObject() {}