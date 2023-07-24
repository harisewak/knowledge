package com.harisewak.knowledge.ui.theme.threads

import android.os.Handler
import android.os.Looper
import android.os.Message
import android.os.SystemClock
import android.util.Log

class LooperThread: Thread() {
    var handler: Handler? = null

    override fun run() {
        Looper.prepare()
        handler = Looper.myLooper()?.let { MyHandler(it) }
        Looper.loop()
    }
}

class MyHandler(looper: Looper): Handler(looper) {

    override fun handleMessage(msg: Message) {
        Log.i("LooperThread", "what - ${msg.what}")
    }
}