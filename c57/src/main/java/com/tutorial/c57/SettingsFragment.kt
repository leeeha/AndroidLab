package com.tutorial.c57

import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import androidx.preference.EditTextPreference
import androidx.preference.ListPreference
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat

class SettingsFragment : PreferenceFragmentCompat() {
    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.settings, rootKey)

        val idPref = findPreference<EditTextPreference>("server_id")
        val soundPref = findPreference<ListPreference>("sound_list")

        // id 값이 변경되는 경우
        idPref?.setOnPreferenceChangeListener { preference, newValue ->
            Toast.makeText(activity, "새로운 값: $newValue", Toast.LENGTH_SHORT).show()
            true
        }

        // soundPref는 summary 그대로 출력
        soundPref!!.setSummaryProvider(ListPreference.SimpleSummaryProvider.getInstance())

        // idPref는 summary를 커스텀하여 출력
        idPref!!.summaryProvider =
            Preference.SummaryProvider<EditTextPreference> { preference ->
                val text = preference.text
                if(TextUtils.isEmpty(text)){
                    "id 값이 설정되지 않았습니다."
                }else{
                    "설정된 값은 $text 입니다."
                }
            }
    }
}