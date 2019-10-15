package com.games.soapsample.response

import com.tickaroo.tikxml.annotation.Attribute
import com.tickaroo.tikxml.annotation.Element
import com.tickaroo.tikxml.annotation.TextContent
import com.tickaroo.tikxml.annotation.Xml

@Xml(name = "env:Envelope")
class CitiesResponseEnvelope(@Element var citiesResponseBody: CitiesResponseBody)


@Xml(name = "env:Body")
class CitiesResponseBody(@Element var listOfCitiesResponse: ListOfCitiesResponse)


@Xml(name = "ns1:getListoOfCitiesResponse")
class ListOfCitiesResponse(
    @Attribute(name = "env:encodingStyle")
    var encoding: String,
    @Element var rpcResult: RpcResult,
    @Element var ret: Return
)

@Xml(name = "rpc:result")
class RpcResult(@TextContent var result: String)

@Xml(name = "return")
class Return(
    @Attribute(name = "enc:itemType")
    var itemType: String,
    @Attribute(name = "enc:arraySize")
    var arraySize: String,
    @Attribute(name = "xsi:type")
    var type: String,
    @Element
    var cityItem: List<CityItem>
)

@Xml(name = "item")
class CityItem(
    @Attribute(name = "xsi:type")
    var xsiType: String,
    @Element var cityItemId: CityItemId,
    @Element var cityItemName: CityItemName,
    @Element var cityIsNew: CityIsNew,
    @Element var cityImage: CityImage,
    @Element var minBookingTime: MinBookingTime,
    @Element var moveInDays: MoveInDays,
    @Element var moveOutDays: MoveOutDays,
    @Element var currency: Currency
)


@Xml(name = "id")
class CityItemId(
    @Attribute(name = "xsi:type")
    var xsiType: String,
    //TODO: replace with property element?
    @TextContent
    var id: String
)

@Xml(name = "name")
class CityItemName(
    @Attribute(name = "xsi:type")
    var xsiType: String,
    @TextContent
    var name: String
)

@Xml(name = "is_new")
class CityIsNew(
    @Attribute(name = "xsi:type")
    var xsiType: String,
    //TODO: is it possible to use @PropertyElement here? Shout it be done?
    @TextContent
    var isNew: String
) {
    //this getter fixes compilation error and is required!
    fun getIsNew(): String = isNew
}

@Xml(name = "image")
class CityImage {
    @Attribute(name = "xsi:type")
    lateinit var xsiType: String

    @TextContent
    lateinit var image: String
}

@Xml(name = "min_booking_time")
class MinBookingTime(
    @Attribute(name = "xsi:type")
    var xsiType: String,

    @TextContent
    var minBookingTimeValue: String
)

@Xml(name = "move_in_days")
class MoveInDays(
    @Attribute(name = "enc:itemType")
    var itemType: String,
    @Attribute(name = "enc:arraySize")
    var arraySize: String,
    @Attribute(name = "xsi:type")
    var xsiType: String,

    @Element
    var moveInDaysItems: List<MoveInDaysItem>
)

@Xml(name = "item")
class MoveInDaysItem(
    @Attribute(name = "xsi:type")
    var xsiType: String,

    @TextContent
    var movInDay: String
)

@Xml(name = "move_out_days")
class MoveOutDays(
    @Attribute(name = "enc:itemType")
    var itemType: String,
    @Attribute(name = "enc:arraySize")
    var arraySize: String,
    @Attribute(name = "xsi:type")
    var xsiType: String,
    @Element
    var moveOutDaysItems: List<MoveOutDaysItem>
)

@Xml(name = "item")
class MoveOutDaysItem(
    @Attribute(name = "xsi:type")
    var xsiType: String,

    @TextContent
    var moveOutDay: String
)

@Xml(name = "currency")
class Currency(
    @Attribute(name = "xsi:type")
    var xsiType: String,

    @TextContent
    var currencyValue: String
)