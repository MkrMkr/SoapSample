package com.games.soapsample.request

import com.games.soapsample.utils.Body
import com.games.soapsample.utils.BodyContent
import com.games.soapsample.utils.EmptyHeader
import com.tickaroo.tikxml.annotation.Element
import com.tickaroo.tikxml.annotation.Xml

@Xml(
    name = "soap:Envelope",
    writeNamespaces = ["soap=http://www.w3.org/2003/05/soap-envelope", "soap1=https://localhost/SOAPVendorAPI"]
)
class CitiesRequestEnvelope(@Element var header: EmptyHeader = EmptyHeader(), @Element var body: Body)

@Xml(name = "soap1:getListoOfCities")
class ListOfCities : BodyContent()