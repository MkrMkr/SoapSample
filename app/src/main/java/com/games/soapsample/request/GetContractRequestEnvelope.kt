package com.games.soapsample.request

import com.tickaroo.tikxml.annotation.Element
import com.tickaroo.tikxml.annotation.TextContent
import com.tickaroo.tikxml.annotation.Xml

@Xml(
    name = "soap:Envelope",
    writeNamespaces = ["soap=http://www.w3.org/2003/05/soap-envelope", "soap1=https://vonder-mock.dev.concisesoftware.com/SOAPVendorAPI"]
)
class GetContractRequestEnvelope(@Element var header: GetContractHeader, @Element var body: GetContractBody)

@Xml(name = "soap:Header")
class GetContractHeader(@Element var tokenTag: TokenTag)

@Xml(name = "token")
class TokenTag(@TextContent var token: String)

@Xml(name = "soap:Body")
class GetContractBody(@Element var contract: GetContract)

@Xml(name = "soap1:getContract")
class GetContract
