package com.bookExchange.annotation

@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.ANNOTATION_CLASS, AnnotationTarget.CLASS,AnnotationTarget.TYPE)
@MustBeDocumented
annotation class ReadOnlyRepository