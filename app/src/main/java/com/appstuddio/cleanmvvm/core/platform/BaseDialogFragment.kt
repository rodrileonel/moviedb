package com.appstuddio.cleanmvvm.core.platform

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager

abstract class BaseDialogFragment(private val matchParent: Boolean = false) : DialogFragment() {

    abstract fun layoutId(): Int
    private val nameDialog by lazy { getTagNameDialog() }

    abstract fun getTagNameDialog(): String

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog =  super.onCreateDialog(savedInstanceState)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        return dialog
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View =
        inflater.inflate(layoutId(), container, false)

    override fun onStart() {
        super.onStart()
        dialog?.let {d ->
            d.setCanceledOnTouchOutside(false)
            if (matchParent)
                d.window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT)
            else
                d.window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT)
        }
    }

    fun showDialog(fragmentManager: FragmentManager){
        if (fragmentManager.findFragmentByTag(nameDialog) == null)
            show(fragmentManager,nameDialog)
    }

    fun dismissDialog(){
        dialog?.let {
            if (it.isShowing) dismiss()
        }
    }

    open fun onBackPressed() {
        dismissDialog()
    }

}