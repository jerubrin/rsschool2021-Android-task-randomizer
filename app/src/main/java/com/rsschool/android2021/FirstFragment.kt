package com.rsschool.android2021

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment

class FirstFragment : Fragment() {

    private var generateButton: Button? = null
    private var previousResult: TextView? = null

    private var minEditText: EditText? = null
    private var maxEditText: EditText? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_first, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        previousResult = view.findViewById(R.id.previous_result)
        generateButton = view.findViewById(R.id.generate)

        minEditText = view.findViewById(R.id.min_value)
        maxEditText = view.findViewById(R.id.max_value)

        val result = arguments?.getInt(PREVIOUS_RESULT_KEY)
        val min = arguments?.getString(MIN_VALUE_KEY)
        val max = arguments?.getString(MAX_VALUE_KEY)
        previousResult?.text = "Previous result: ${result.toString()}"
        minEditText?.setText(min)
        maxEditText?.setText(max)

        generateButton?.setOnClickListener {
            val min = minEditText?.text.toString()
            val max = maxEditText?.text.toString()
            if (min <= max) {
                (activity as FragmentsSwitcher).switchToSecondFragment(min, max)
            } else {
                (activity as FragmentsSwitcher).switchToSecondFragment(max, min)
            }
        }
    }

    companion object {

        @JvmStatic
        fun newInstance(previousResult: Int, min: String, max: String): FirstFragment {
            val fragment = FirstFragment()
            val args = Bundle()
            args.putInt(PREVIOUS_RESULT_KEY, previousResult)
            args.putString(MIN_VALUE_KEY, min)
            args.putString(MAX_VALUE_KEY, max)
            fragment.arguments = args
            return fragment
        }

        private const val PREVIOUS_RESULT_KEY = "PREVIOUS_RESULT"
        private const val MIN_VALUE_KEY = "MIN_VALUE"
        private const val MAX_VALUE_KEY = "MAX_VALUE"
    }
}