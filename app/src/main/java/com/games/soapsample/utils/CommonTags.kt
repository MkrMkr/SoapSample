package com.games.soapsample.utils

import com.games.soapsample.request.GetApartments
import com.games.soapsample.request.GetContract
import com.games.soapsample.request.ListOfCities
import com.tickaroo.tikxml.annotation.*

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

@Xml(name = "rpc:result")
class RpcResult(@TextContent var textContent: String)

@Xml(name = "soap:Header")
class EmptyHeader

@Xml(name = "soap:Header")
class HeaderWithToken(@Element var tokenContainer: TokenContainer)

@Xml(name = "token")
class TokenContainer(@TextContent var token: String)

@Xml
open class BodyContent

@Xml(name = "soap:Body")
class Body(
    @Element(
        typesByElement = [ElementNameMatcher(type = ListOfCities::class),
            ElementNameMatcher(type = GetApartments::class),
            ElementNameMatcher(type = GetContract::class)]
    )
    var bodyContent: BodyContent
)