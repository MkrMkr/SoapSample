package com.games.soapsample.utils

import com.tickaroo.tikxml.annotation.Attribute
import com.tickaroo.tikxml.annotation.TextContent
import com.tickaroo.tikxml.annotation.Xml

@Xml(inheritance = true)
open class TagWithXsiType {
    @Attribute(name = "xsi:type")
    lateinit var xsiType: String
}

@Xml(inheritance = true)
open class TagWithList : TagWithXsiType() {
    @Attribute(name = "enc:itemType")
    lateinit var itemType: String

    @Attribute(name = "enc:arraySize")
    lateinit var arraySize: String
}

@Xml
open class TagWithTextContent : TagWithXsiType() {
    @TextContent
    lateinit var textContent: String
}

@Xml
open class TagWithEncodingStyle() {
    @Attribute(name = "env:encodingStyle")
    lateinit var encoding: String
}
