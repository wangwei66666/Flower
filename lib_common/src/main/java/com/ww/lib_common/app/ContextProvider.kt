package com.ww.lib_common.app

import android.content.ContentProvider
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.net.Uri

/**
 * @author ww
 * @date 2021/2/22.
 * description：可在此ContentProvider中进行三方初始化
 *
 */
internal class ContextProvider:ContentProvider() {

    companion object {
        lateinit var mContext: Context
        fun context():Context{
            return mContext
        }
    }

    override fun onCreate(): Boolean {
        init(context!!)
        return true
    }

    private fun init(context: Context) {
        mContext=context
    }

    override fun insert(uri: Uri, values: ContentValues?): Uri? {
        TODO("Not yet implemented")
    }

    override fun query(
        uri: Uri,
        projection: Array<out String>?,
        selection: String?,
        selectionArgs: Array<out String>?,
        sortOrder: String?
    ): Cursor? {
        TODO("Not yet implemented")
    }

    override fun update(
        uri: Uri,
        values: ContentValues?,
        selection: String?,
        selectionArgs: Array<out String>?
    ): Int {
        TODO("Not yet implemented")
    }

    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<out String>?): Int {
        TODO("Not yet implemented")
    }

    override fun getType(uri: Uri): String? {
        TODO("Not yet implemented")
    }

}