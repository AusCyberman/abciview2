package au.com.scooterise.ui.content
import io.realm.RealmObject;
import io.realm.annotations.RealmClass

@RealmClass(embedded = true)
open class Content_trailer(
    var image: String? = null,
    var turi: String? = null
): RealmObject() {}