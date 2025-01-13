package com.aminuolawale.muffassa.presentation.corpus

import android.net.Uri
import com.aminuolawale.muffassa.presentation.Screen

class CorpusUri(val corpusId: String?) {

    val resources: String
        get() {
            return Uri.Builder().path(Screen.CorpusHome.route).appendPath(corpusId)
                .appendPath("resources").build()
                .toString()

        }
    val home: String
        get() {

            return Uri.Builder().path(Screen.CorpusHome.route).appendPath(corpusId)
                .appendPath("home").build().toString()
        }

    val snippets: String
        get() {
            return Uri.Builder().path(Screen.CorpusHome.route).appendPath(corpusId)
                .appendPath("snippets").build()
                .toString()

        }

    val quiz: String
        get() {
            return Uri.Builder().path(Screen.CorpusHome.route).appendPath(corpusId)
                .appendPath("quiz").build().toString()

        }
    val settings: String
        get() {
            return Uri.Builder().path(Screen.CorpusHome.route).appendPath(corpusId)
                .appendPath("settings").build().toString()

        }


}
