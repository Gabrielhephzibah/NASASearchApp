package com.cherish.nasasearchapp.utils

import android.view.View
import android.view.ViewGroup
import androidx.core.view.children
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.testing.FragmentScenario
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.IdlingResource
import androidx.test.rule.ActivityTestRule
import com.cherish.nasasearchapp.presentation.milkyway.MilkyWayFragment
import java.util.UUID

    class DataBindingIdlingResource(
        private val activityTestRule: ActivityTestRule<*>
    ) : IdlingResource {
        // list of registered callbacks
        private val idlingCallbacks = mutableListOf<IdlingResource.ResourceCallback>()
        // give it a unique id to workaround an espresso bug where you cannot register/unregister
        // an idling resource w/ the same name.
        private val id = UUID.randomUUID().toString()
        // holds whether isIdle is called and the result was false. We track this to avoid calling
        // onTransitionToIdle callbacks if Espresso never thought we were idle in the first place.
        private var wasNotIdle = false

        override fun getName() = "DataBinding $id"

        override fun isIdleNow(): Boolean {
            val idle = !getBindings().any { it.hasPendingBindings() }
            @Suppress("LiftReturnOrAssignment")
            if (idle) {
                if (wasNotIdle) {
                    // notify observers to avoid espresso race detector
                    idlingCallbacks.forEach { it.onTransitionToIdle() }
                }
                wasNotIdle = false
            } else {
                wasNotIdle = true
                // check next frame
                activityTestRule.activity.findViewById<View>(android.R.id.content).postDelayed({
                    isIdleNow
                }, 16)
            }
            return idle
        }

        override fun registerIdleTransitionCallback(callback: IdlingResource.ResourceCallback) {
            idlingCallbacks.add(callback)
        }

        /**
         * Find all binding classes in all currently available fragments.
         */
        private fun getBindings(): List<ViewDataBinding> {
            return (activityTestRule.activity as? FragmentActivity)
                ?.supportFragmentManager
                ?.fragments
                ?.mapNotNull {
                    it.view?.let { view ->
                        DataBindingUtil.getBinding<ViewDataBinding>(
                            view
                        )
                    }
                } ?: emptyList()
        }
    }

//    // List of registered callbacks
//    private val idlingCallbacks = mutableListOf<IdlingResource.ResourceCallback>()
//    // Give it a unique id to work around an Espresso bug where you cannot register/unregister
//    // an idling resource with the same name.
//    private val id = UUID.randomUUID().toString()
//    // Holds whether isIdle was called and the result was false. We track this to avoid calling
//    // onTransitionToIdle callbacks if Espresso never thought we were idle in the first place.
//    private var wasNotIdle = false
//
//    lateinit var activity: FragmentActivity
//
//    override fun getName() = "DataBinding $id"
//
//    override fun isIdleNow(): Boolean {
//        val idle = !getBindings().any { it.hasPendingBindings() }
//        @Suppress("LiftReturnOrAssignment")
//        if (idle) {
//            if (wasNotIdle) {
//                // Notify observers to avoid Espresso race detector.
//                idlingCallbacks.forEach { it.onTransitionToIdle() }
//            }
//            wasNotIdle = false
//        } else {
//            wasNotIdle = true
//            // Check next frame.
//            activity.findViewById<View>(android.R.id.content).postDelayed({
//                isIdleNow
//            }, 16)
//        }
//        return idle
//    }
//
//    override fun registerIdleTransitionCallback(callback: IdlingResource.ResourceCallback) {
//        idlingCallbacks.add(callback)
//    }
//
//    /**
//     * Find all binding classes in all currently available fragments.
//     */
//    private fun getBindings(): List<ViewDataBinding> {
//        val fragments = (activity as? FragmentActivity)
//            ?.supportFragmentManager
//            ?.fragments
//
//        val bindings =
//            fragments?.mapNotNull {
//                it.view?.getBinding()
//            } ?: emptyList()
//        val childrenBindings = fragments?.flatMap { it.childFragmentManager.fragments }
//            ?.mapNotNull { it.view?.getBinding() } ?: emptyList()
//
//        return bindings + childrenBindings
//    }
//}
//
//private fun View.getBinding(): ViewDataBinding? = DataBindingUtil.getBinding(this)
//
///**
// * Sets the activity from an [ActivityScenario] to be used from [DataBindingIdlingResource].
// */
//fun DataBindingIdlingResource.monitorActivity(
//    activityScenario: ActivityScenario<out FragmentActivity>
//) {
//    activityScenario.onActivity {
//        this.activity = it
//    }
//}
//
///**
// * Sets the fragment from a [FragmentScenario] to be used from [DataBindingIdlingResource].
// */
//fun DataBindingIdlingResource.monitorFragment(fragment: Fragment) {
//            this.activity = fragment.requireActivity()
//}

//fun DataBindingIdlingResource.monitorFragment(fragmentScenario: FragmentScenario<out Fragment>) {
//    fragmentScenario.onFragment {
//        this.activity = it.requireActivity()
//    }
//}

//class DataBindingIdlingResource : IdlingResource {
//    // list of registered callbacks
//    private val idlingCallbacks = mutableListOf<IdlingResource.ResourceCallback>()
//    // give it a unique id to workaround an espresso bug where you cannot register/unregister
//    // an idling resource w/ the same name.
//    private val id = UUID.randomUUID().toString()
//    // holds whether isIdle is called and the result was false. We track this to avoid calling
//    // onTransitionToIdle callbacks if Espresso never thought we were idle in the first place.
//    private var wasNotIdle = false
//
//    private lateinit var scenario: FragmentScenario<Fragment>
//
//    override fun getName() = "DataBinding $id"
//
//    /**
//     * Sets the fragment from a [FragmentScenario] to be used from [DataBindingIdlingResource].
//     */
//    fun monitorFragment(fragmentScenario: FragmentScenario<Fragment>) {
//        scenario = fragmentScenario
//
//    }
//
//    override fun isIdleNow(): Boolean {
//        val idle = !getBindings().any { it.hasPendingBindings() }
//        @Suppress("LiftReturnOrAssignment")
//        if (idle) {
//            if (wasNotIdle) {
//                // notify observers to avoid espresso race detector
//                idlingCallbacks.forEach { it.onTransitionToIdle() }
//            }
//            wasNotIdle = false
//        } else {
//            wasNotIdle = true
//            // check next frame
//
//            scenario.onFragment {fragment ->
//                fragment.view?.postDelayed({
//                    if (fragment.view != null) {
//                        isIdleNow
//                    }
//                }, 16)
//            }
//        }
//        return idle
//    }
//
//    override fun registerIdleTransitionCallback(callback: IdlingResource.ResourceCallback) {
//        idlingCallbacks.add(callback)
//    }
//
//    /**
//     * Find all binding classes in all currently available fragments.
//     */
//    private fun getBindings(): List<ViewDataBinding> {
//        lateinit var bindings: List<ViewDataBinding>
//        scenario.onFragment {  fragment ->
//            bindings = fragment.requireView().flattenHierarchy().mapNotNull { view ->
//                DataBindingUtil.getBinding<ViewDataBinding>(view)
//            }
//        }
//        return bindings
//    }
//
//    private fun View.flattenHierarchy(): List<View> = if (this is ViewGroup) {
//        listOf(this) + children.map { it.flattenHierarchy() }.flatten()
//    } else {
//        listOf(this)
//    }
//}


    // list of registered callbacks
//    private val idlingCallbacks = mutableListOf<IdlingResource.ResourceCallback>()
//    // give it a unique id to workaround an espresso bug where you cannot register/unregister
//    // an idling resource w/ the same name.
//    private val id = UUID.randomUUID().toString()
//    // holds whether isIdle is called and the result was false. We track this to avoid calling
//    // onTransitionToIdle callbacks if Espresso never thought we were idle in the first place.
//    private var wasNotIdle = false
//
//    override fun getName() = "DataBinding $id"
//
//    override fun isIdleNow(): Boolean {
//        val idle = !getBindings().any { it.hasPendingBindings() }
//        @Suppress("LiftReturnOrAssignment")
//        if (idle) {
//            if (wasNotIdle) {
//                // notify observers to avoid espresso race detector
//                idlingCallbacks.forEach { it.onTransitionToIdle() }
//            }
//            wasNotIdle = false
//        } else {
//            wasNotIdle = true
//            // check next frame
//            activityTestRule.activity.findViewById<View>(android.R.id.content).postDelayed({
//                isIdleNow
//            }, 16)
//        }
//        return idle
//    }
//
//    override fun registerIdleTransitionCallback(callback: IdlingResource.ResourceCallback) {
//        idlingCallbacks.add(callback)
//    }
//
//    /**
//     * Find all binding classes in all currently available fragments.
//     */
//    private fun getBindings(): List<ViewDataBinding> {
//        return (activityTestRule.activity as? FragmentActivity)
//            ?.supportFragmentManager
//            ?.fragments
//            ?.mapNotNull {
//                it.view?.let { view ->
//                    DataBindingUtil.getBinding<ViewDataBinding>(
//                        view
//                    )
//                }
//            } ?: emptyList()
//    }
//}