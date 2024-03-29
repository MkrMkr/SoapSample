package com.games.soapsample.request

import com.games.soapsample.utils.*
import com.tickaroo.tikxml.annotation.Element
import com.tickaroo.tikxml.annotation.Xml

@Xml(
    name = SOAP_ENVELOPE_TAG,
    writeNamespaces = [SOAP_W3_URL_NAMESPACE, SOAP1_VENDOR_API_NAMESPACE]
)
class CitiesRequestEnvelope(@Element var header: EmptyHeader = EmptyHeader(), @Element var body: Body)

@Xml(name = "soap1:getListoOfCities")
class ListOfCities : BodyContent()