package com.games.soapsample.request

import com.games.soapsample.utils.Body
import com.games.soapsample.utils.BodyContent
import com.games.soapsample.utils.EmptyHeader
import com.tickaroo.tikxml.annotation.Element
import com.tickaroo.tikxml.annotation.TextContent
import com.tickaroo.tikxml.annotation.Xml

@Xml(
    name = "soap:Envelope",
    writeNamespaces = ["soap=http://www.w3.org/2003/05/soap-envelope",
        "soap1=https://vonder-mock.dev.concisesoftware.com/SOAPVendorAPI",
        "typ=https://vonder-mock.dev.concisesoftware.com/SOAPVendorAPI/types"]
)
class GetApartmentsRequestEnvelope(@Element var header: EmptyHeader = EmptyHeader(), @Element var body: Body)

@Xml(name = "soap1:getApartments")
class GetApartments(@Element var apartmentsFilter: ApartmentsFilter, @Element var offset: Offset, @Element var limit: Limit) :
    BodyContent()

@Xml(name = "typ:Filter")
class ApartmentsFilter(@Element var cityId: CityId, @Element var typeId: TypeId)

@Xml(name = "city_id")
class CityId(@TextContent var cityId: String)

@Xml(name = "type_id")
class TypeId(@TextContent var typeId: String)

@Xml(name = "offset")
class Offset(@TextContent var offset: String)

@Xml(name = "limit")
class Limit(@TextContent var limit: String)




