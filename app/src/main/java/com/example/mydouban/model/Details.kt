package com.example.mydouban.model

data class Details(
        val `1`: Int,
        val `2`: Int,
        val `3`: Int,
        val `4`: Int,
        val `5`: Int
) {
        val total: Int get() =  `1` + `2` + `3` + `4` + `5`
}