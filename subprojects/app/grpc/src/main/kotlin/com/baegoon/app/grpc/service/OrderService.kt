package com.baegoon.app.grpc.service

import dev.baesangwoo.mymodule.proto.OrderGrpc
import dev.baesangwoo.mymodule.proto.OrderRequestProto
import dev.baesangwoo.mymodule.proto.OrderResponseProto
import io.grpc.stub.StreamObserver
import org.lognet.springboot.grpc.GRpcService

@GRpcService
class OrderService : OrderGrpc.OrderImplBase() {

    // proto : https://github.com/sangwoobae/my-module-sample/blob/master/src/main/proto/order.proto
    override fun buy(request: OrderRequestProto, responseObserver: StreamObserver<OrderResponseProto>) {
        val price = getPrice(request.menu)

        val response = OrderResponseProto.newBuilder()
            .setMenu(request.menu)
            .setChange(getChange(request.money, price))
            .build()

        responseObserver.onNext(response)
        responseObserver.onCompleted()
    }

    private fun getPrice(menu: String): Int {
        return when (menu.toLowerCase()) {
            "coffee" -> 1000
            "tea" -> 2000
            else -> 1500
        }
    }

    private fun getChange(money: Int, price: Int): Int {
        return if (money > price) {
            money - price
        } else {
            0
        }
    }
}
