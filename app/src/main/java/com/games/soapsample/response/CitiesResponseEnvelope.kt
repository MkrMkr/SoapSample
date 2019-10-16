package com.games.soapsample.response

import com.games.soapsample.utils.*
import com.tickaroo.tikxml.annotation.Element
import com.tickaroo.tikxml.annotation.TextContent
import com.tickaroo.tikxml.annotation.Xml

//TODO: are namespaces in envelope required?
//TODO: - data classes?
//TODO: - how to create global envelope? should i really do it?
@Xml(name = "env:Envelope")
class CitiesResponseEnvelope(@Element var citiesResponseBody: CitiesResponseBody)

@Xml(name = "env:Body")
class CitiesResponseBody(@Element var listOfCitiesResponse: ListOfCitiesResponse)

@Xml(name = "ns1:getListoOfCitiesResponse")
class ListOfCitiesResponse : TagWithEncodingStyle() {
    @Element
    lateinit var rpcResult: RpcResult
    @Element
    lateinit var ret: ReturnListOfCities
}

@Xml(name = "return", inheritance = true)
class ReturnListOfCities : TagWithList() {
    @Element
    lateinit var cityItem: List<CityItem>
}

@Xml(name = "item", inheritance = true)
class CityItem : TagWithXsiType() {
    @Element
    lateinit var cityItemId: CityItemId
    @Element
    lateinit var cityItemName: CityItemName
    @Element
    lateinit var cityIsNew: CityIsNew
    @Element
    lateinit var cityImage: CityImage
    @Element
    lateinit var minBookingTime: MinBookingTime
    @Element
    lateinit var moveInDays: MoveInDays
    @Element
    lateinit var moveOutDays: MoveOutDays
    @Element
    lateinit var currency: Currency
}

@Xml(name = "id", inheritance = true)
class CityItemId : TagWithTextContent()

@Xml(name = "name", inheritance = true)
class CityItemName : TagWithTextContent()

@Xml(name = "is_new", inheritance = true)
class CityIsNew : TagWithTextContent() {
    //this getter fixes compilation error and is required!
    fun getIsNew(): String = textContent
}

@Xml(name = "image", inheritance = true)
class CityImage : TagWithXsiType() {
    @TextContent
    lateinit var image: String
}

@Xml(name = "min_booking_time", inheritance = true)
class MinBookingTime : TagWithXsiType() {
    @TextContent
    lateinit var minBookingTimeValue: String
}

@Xml(name = "move_in_days", inheritance = true)
class MoveInDays : TagWithList() {
    @Element
    lateinit var moveInDaysItems: List<MoveInDaysItem>
}

@Xml(name = "item", inheritance = true)
class MoveInDaysItem : TagWithTextContent()

@Xml(name = "move_out_days", inheritance = true)
class MoveOutDays : TagWithList() {
    @Element
    lateinit var moveOutDaysItems: List<MoveOutDaysItem>
}

@Xml(name = "item", inheritance = true)
class MoveOutDaysItem : TagWithTextContent()

@Xml(name = "currency", inheritance = true)
class Currency : TagWithTextContent()


