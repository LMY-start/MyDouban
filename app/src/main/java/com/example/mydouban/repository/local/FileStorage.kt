package com.example.mydouban.repository.local

import android.app.Activity
import android.content.Context
import java.io.BufferedReader
import java.io.InputStreamReader
import java.lang.StringBuilder

class FileStorage() {

    companion object {
        fun write(activity: Activity, fileName: String, content: String) {
            activity.openFileOutput(fileName, Context.MODE_PRIVATE).use {
                it.write(content.toByteArray())
            }
        }


        fun read(activity: Activity, fileName: String): String {
            val content = StringBuilder()
            BufferedReader(InputStreamReader(activity.openFileInput(fileName))).use {
                var line: String
                while (true) {
                    line = it.readLine() ?: break
                    content.append(line)
                }
            }
            return content.toString()
        }

        fun read(activity: Activity, fileName: String, lines: Int) {
            BufferedReader(InputStreamReader(activity.openFileInput(fileName))).use {
                val content = StringBuilder()
                var line: String
                var lineCount = 0
                while (lineCount < lines) {
                    line = it.readLine() ?: break
                    content.append(line)
                }
            }
        }
    }
}