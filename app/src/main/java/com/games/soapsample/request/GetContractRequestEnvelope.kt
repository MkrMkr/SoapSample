package com.games.soapsample.request

import com.games.soapsample.utils.Body
import com.games.soapsample.utils.BodyContent
import com.games.soapsample.utils.HeaderWithContent
import com.tickaroo.tikxml.annotation.Element
import com.tickaroo.tikxml.annotation.Xml

@Xml(
    name = "soap:Envelope",
    writeNamespaces = ["soap=http://www.w3.org/2003/05/soap-envelope", "soap1=https://vonder-mock.dev.concisesoftware.com/SOAPVendorAPI"]
)
class GetContractRequestEnvelope(@Element var header: HeaderWithContent, @Element var body: Body)

@Xml(name = "soap1:getContract")
class GetContract : BodyContent()
