package com.pius712.springredis


class Box<T> {

    private var value:T? = null

    fun set(value:T) {
        this.value = value
    }

    fun get():T? {
        return value
    }

}