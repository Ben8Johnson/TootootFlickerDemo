package com.example.benjohnson.tootootflickerdemo

import android.support.annotation.AnimRes
import android.support.annotation.IdRes
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager

/**
 * Created by BenJohnson on 12/02/2018.
 */
class BackStackManager(@param:IdRes private val frameLayout: Int, val fragmentManager: FragmentManager) {

    @JvmOverloads
    fun goTo(fragment: Fragment, @AnimRes inAnimation: Int, @AnimRes outAnimation: Int,
             @AnimRes popInAnimation: Int = 0, @AnimRes popOutAnimation: Int = 0) {
        val transaction = fragmentManager.beginTransaction()
        transaction.setCustomAnimations(inAnimation, outAnimation, popInAnimation, popOutAnimation)
        transaction.replace(frameLayout, fragment)
        transaction.addToBackStack(BACK_STACK_ID)
        transaction.commitAllowingStateLoss()
    }

    @JvmOverloads
    fun goTo(fragment: Fragment, addToStack: Boolean = true) {
        val transaction = fragmentManager.beginTransaction().replace(frameLayout, fragment)
        if (addToStack) {
            transaction.addToBackStack(BACK_STACK_ID)
        }
        transaction.commitAllowingStateLoss()
    }

    fun goBack(): Boolean {
        if (fragmentManager.backStackEntryCount == 0) {
            return false
        }
        fragmentManager.popBackStack()
        return true
    }

    fun clear() {
        if (fragmentManager.backStackEntryCount > 0) {
            fragmentManager.popBackStackImmediate(null, FragmentManager.POP_BACK_STACK_INCLUSIVE)
        }
    }

    fun clearAndGoTo(fragment: Fragment) {
        clear()
        goTo(fragment, false)
    }

    companion object {
        val BACK_STACK_ID = "OemAppBackstack"
    }


}