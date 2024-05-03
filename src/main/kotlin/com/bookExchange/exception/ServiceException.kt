package com.bookExchange.exception

import org.springframework.http.HttpStatus

class ServiceException(val status: HttpStatus, message: String) : Exception(message)
class WorkerException(message: String) : Exception(message)