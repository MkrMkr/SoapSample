package com.games.soapsample.request

import com.tickaroo.tikxml.annotation.Element
import com.tickaroo.tikxml.annotation.Xml

//TODO: are namespaces in envelop required?
const val XMLNS_SOAP = "soap=http://www.w3.org/2003/05/soap-envelope"
const val XMLNS_SOAP1 = "soap1=https://localhost/SOAPVendorAPI"

@Xml(name = "soap:Envelope", writeNamespaces = [XMLNS_SOAP, XMLNS_SOAP1])
class CitiesRequest(@Element var header: Header, @Element var body: Body)

@Xml(name = "soap:Header")
class Header

@Xml(name = "soap:Body")
class Body(@Element var listOfCities: ListOfCities)

@Xml(name = "soap1:getListoOfCities")
class ListOfCities