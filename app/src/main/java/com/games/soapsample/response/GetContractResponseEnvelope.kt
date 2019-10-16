package com.games.soapsample.response

import com.games.soapsample.utils.RpcResult
import com.games.soapsample.utils.TagWithEncodingStyle
import com.games.soapsample.utils.TagWithXsiType
import com.tickaroo.tikxml.annotation.Element
import com.tickaroo.tikxml.annotation.TextContent
import com.tickaroo.tikxml.annotation.Xml

@Xml(name = "env:Envelope")
class GetContractResponseEnvelope(@Element var getContractResponseBody: GetContractResponseBody)

@Xml(name = "env:Body")
class GetContractResponseBody(@Element var getContractResponse: GetContractResponse)

@Xml(name = "ns1:getContractResponse")
class GetContractResponse : TagWithEncodingStyle() {
    @Element
    lateinit var rpcResult: RpcResult
    @Element
    lateinit var ret: ReturnContractHtml
}

@Xml(name = "return", inheritance = true)
class ReturnContractHtml : TagWithXsiType(){
    @TextContent
    lateinit var htmlText: String
}