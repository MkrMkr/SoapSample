package com.games.soapsample.request

import com.tickaroo.tikxml.annotation.Element
import com.tickaroo.tikxml.annotation.Xml

@Xml(
    name = "soap:Envelope",
    writeNamespaces = ["soap=http://www.w3.org/2003/05/soap-envelope", "soap1=https://localhost/SOAPVendorAPI"]
)
class CitiesRequestEnvelope(@Element var header: CitiesRequestHeader, @Element var body: Body)

@Xml(name = "soap:Header")
class CitiesRequestHeader

@Xml(name = "soap:Body")
class Body(@Element var listOfCities: ListOfCities)

@Xml(name = "soap1:getListoOfCities")
class ListOfCities