package com.example.mydouban.ui.list

import com.example.mydouban.model.MovieSubject

interface OnItemClickListener {
        fun onItemClick(movieSubject: MovieSubject)
        fun onItemClickWantWatch(movieSubject: MovieSubject){
            TODO("Not yet implemented")
        }
    }