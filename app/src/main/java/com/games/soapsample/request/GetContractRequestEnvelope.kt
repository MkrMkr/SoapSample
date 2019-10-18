package com.games.soapsample.request

import com.games.soapsample.utils.*
import com.tickaroo.tikxml.annotation.Element
import com.tickaroo.tikxml.annotation.Xml

@Xml(
    name = SOAP_ENVELOPE_TAG,
    writeNamespaces = [SOAP_W3_URL_NAMESPACE, SOAP1_VENDOR_API_NAMESPACE]
)
class GetContractRequestEnvelope(@Element var headerWithToken: HeaderWithToken, @Element var body: Body)

@Xml(name = "soap1:getContract")
class GetContract : BodyContent()
