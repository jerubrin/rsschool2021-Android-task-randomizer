package com.rsschool.android2021

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment

class SecondFragment : Fragment() {

    private var backButton: Button? = null
    private var result: TextView? = null

    private var min: String = ""
    private var max: String = ""

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_second, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        result = view.findViewById(R.id.result)
        backButton = view.findViewById(R.id.back)

        min = arguments?.getString(MIN_VALUE_KEY) ?: ""
        max = arguments?.getString(MAX_VALUE_KEY) ?: ""

        result?.text = generate(min?.toIntOrNull() ?: 0, max?.toIntOrNull() ?: 0).toString()

        backButton?.setOnClickListener {
            parentFragmentManager.popBackStack();
        }
    }

    override fun onStop() {
        (activity as FragmentsSwitcher).switchToFirstFragment(result?.text.toString().toInt(), min, max)
        super.onStop()
    }

    private fun generate(min: Int, max: Int): Int {
        return (min..max).random()
    }

    companion object {

        @JvmStatic
        fun newInstance(min: String, max: String): SecondFragment {
            return SecondFragment().apply {
                arguments = Bundle().apply {
                    putString(MIN_VALUE_KEY, min)
                    putString(MAX_VALUE_KEY, max)
                }
            }
        }

        private const val MIN_VALUE_KEY = "MIN_VALUE"
        private const val MAX_VALUE_KEY = "MAX_VALUE"
    }
}