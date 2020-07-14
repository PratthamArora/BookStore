package com.pratthamarora.data.model

import org.bson.codecs.pojo.annotations.BsonId
import org.bson.types.ObjectId

class Book(
    @field:BsonId var id: ObjectId?,
    var title: String,
    var author: String,
    var price: Float
) {

    constructor() : this(null, "not_set", "not_set", 0.0f)

}