package com.games.soapsample.utils

import android.media.session.MediaSession
import com.games.soapsample.request.GetApartments
import com.games.soapsample.request.GetContract
import com.games.soapsample.request.ListOfCities
import com.tickaroo.tikxml.annotation.*

const val SOAP_ENVELOPE_TAG = "soap:Envelope"
const val SOAP_W3_URL_NAMESPACE = "soap=http://www.w3.org/2003/05/soap-envelope"
const val SOAP1_VENDOR_API_NAMESPACE =
    "soap1=https://vonder-mock.dev.concisesoftware.com/SOAPVendorAPI"

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

@Xml(
    name = SOAP_ENVELOPE_TAG,
    writeNamespaces = [SOAP_W3_URL_NAMESPACE,
        SOAP1_VENDOR_API_NAMESPACE]
)
class Envelope(@Element var header: SoapHeader, @Element var body: Body)


@Xml(name = "soap:Header")
class EmptyHeader

@Xml
open class SoapHeaderContent

@Xml(name = "soap:Header")
class SoapHeader(
    @Element(typesByElement = [ElementNameMatcher(type = TokenContainer::class)])
    var headerContent: SoapHeaderContent
)

@Xml(name = "token")
class TokenContainer(@TextContent var token: String): SoapHeaderContent()

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
