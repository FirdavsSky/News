package com.example.news.fragments

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.news.R
import com.example.news.viewModel.OptionViewModel
import com.google.android.material.materialswitch.MaterialSwitch
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

@AndroidEntryPoint
class OptionFragment : Fragment(R.layout.fragment_option) {

    private val viewModel: OptionViewModel by viewModels()

    private var switchPolitics: MaterialSwitch? = null
    private var switchSport: MaterialSwitch? = null
    private var switchNature: MaterialSwitch? = null
    private var switchScience: MaterialSwitch? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        switchPolitics = view.findViewById(R.id.switchPolitics)
        switchSport = view.findViewById(R.id.switchSport)
        switchNature = view.findViewById(R.id.switchNature)
        switchScience = view.findViewById(R.id.switchScience)

        viewModel.switchStates.observe(viewLifecycleOwner) { states ->
            switchPolitics?.isChecked = states["Politics"] ?: true
            switchSport?.isChecked = states["Sport"] ?: true
            switchNature?.isChecked = states["Nature"] ?: true
            switchScience?.isChecked = states["Science"] ?: true
        }

        switchPolitics?.setOnCheckedChangeListener { _, isChecked ->
            viewModel.updatePreference("Politics", isChecked)
        }

        switchSport?.setOnCheckedChangeListener { _, isChecked ->
            viewModel.updatePreference("Sport", isChecked)
        }

        switchNature?.setOnCheckedChangeListener { _, isChecked ->
            viewModel.updatePreference("Nature", isChecked)
        }

        switchScience?.setOnCheckedChangeListener { _, isChecked ->
            viewModel.updatePreference("Science", isChecked)
        }
    }
}