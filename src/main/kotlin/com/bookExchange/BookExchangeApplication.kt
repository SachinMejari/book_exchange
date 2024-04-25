package com.bookExchange
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.openfeign.EnableFeignClients

@SpringBootApplication
@EnableFeignClients
class BookExchangeApplication
fun main(args: Array<String>) {
    runApplication<BookExchangeApplication>(*args)
}