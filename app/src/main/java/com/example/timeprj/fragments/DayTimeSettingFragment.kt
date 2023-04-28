package com.example.timeprj.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.widget.SwitchCompat
import com.example.timeprj.R
import com.example.timeprj.databinding.FragmentDayTimeSettingBinding
import com.example.timeprj.util.*
import com.example.timeprj.viewmodels.DayTimeSettingViewModel
import com.google.android.material.timepicker.MaterialTimePicker
import com.google.android.material.timepicker.TimeFormat
import java.time.LocalDate

class DayTimeSettingFragment : AbstractFragment<DayTimeSettingViewModel>(
    DayTimeSettingViewModel()
) {

    private var _binding: FragmentDayTimeSettingBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        _binding = FragmentDayTimeSettingBinding.inflate(inflater, container, false)

        val view = binding.root

        addChangedListener(binding.time1EditText,  binding.time1SubmitButton, binding.daySlider, binding.time1EditText)
        addChangedListener(binding.time2EditText, binding.time2SubmitButton, binding.day2Slider, binding.time2EditText)
        addChangedListener(binding.time3EditText, binding.time3SubmitButton, binding.day3Slider, binding.time3EditText)
        addChangedListener(binding.time4EditText, binding.time4SubmitButton, binding.day4Slider, binding.time4EditText)
        addChangedListener(binding.time5EditText, binding.time5SubmitButton, binding.day5Slider, binding.time5EditText)
        addChangedListener(binding.time6EditText, binding.time6SubmitButton, binding.day6Slider, binding.time6EditText)

        addTimePicker(binding.time1EditText)
        addTimePicker(binding.time2EditText)
        addTimePicker(binding.time3EditText)
        addTimePicker(binding.time4EditText)
        addTimePicker(binding.time5EditText)
        addTimePicker(binding.time6EditText)

        // Pre-set current times fields
        binding.time1EditText.text = DayTimeSettingViewModel().getFromPrefs(DATE_TIME_1, "")
        binding.time2EditText.text = DayTimeSettingViewModel().getFromPrefs(DATE_TIME_2, "")
        binding.time3EditText.text = DayTimeSettingViewModel().getFromPrefs(DATE_TIME_3, "")
        binding.time4EditText.text = DayTimeSettingViewModel().getFromPrefs(DATE_TIME_4, "")
        binding.time5EditText.text = DayTimeSettingViewModel().getFromPrefs(DATE_TIME_5, "")
        binding.time6EditText.text = DayTimeSettingViewModel().getFromPrefs(DATE_TIME_6, "")

        // Pre-set current days fields
        binding.daySlider.isChecked = DayTimeSettingViewModel().getFromPrefs(DAY_1, false)
        binding.day2Slider.isChecked = DayTimeSettingViewModel().getFromPrefs(DAY_2, false)
        binding.day3Slider.isChecked = DayTimeSettingViewModel().getFromPrefs(DAY_3, false)
        binding.day4Slider.isChecked = DayTimeSettingViewModel().getFromPrefs(DAY_4, false)
        binding.day5Slider.isChecked = DayTimeSettingViewModel().getFromPrefs(DAY_5, false)
        binding.day6Slider.isChecked = DayTimeSettingViewModel().getFromPrefs(DAY_6, false)

        // Set submit button listeners for each field
        addSubmitButtonListener(binding.time1SubmitButton, binding.time1EditText, binding.daySlider, DATE_TIME_1, DAY_1, DATE_KEY_1)
        addSubmitButtonListener(binding.time2SubmitButton, binding.time2EditText, binding.day2Slider, DATE_TIME_2, DAY_2, DATE_KEY_2)
        addSubmitButtonListener(binding.time3SubmitButton, binding.time3EditText, binding.day3Slider, DATE_TIME_3, DAY_3, DATE_KEY_3)
        addSubmitButtonListener(binding.time4SubmitButton, binding.time4EditText, binding.day4Slider, DATE_TIME_4, DAY_4, DATE_KEY_4)
        addSubmitButtonListener(binding.time5SubmitButton, binding.time5EditText, binding.day5Slider, DATE_TIME_5, DAY_5, DATE_KEY_5)
        addSubmitButtonListener(binding.time6SubmitButton, binding.time6EditText, binding.day6Slider, DATE_TIME_6, DAY_6, DATE_KEY_6)

        // Set delete button listeners for each field
        addDeleteButtonListener(binding.time1DeleteButton, binding.time1EditText, binding.daySlider, DATE_TIME_1, DAY_1, DATE_KEY_1)
        addDeleteButtonListener(binding.time2DeleteButton, binding.time2EditText, binding.day2Slider, DATE_TIME_2, DAY_2, DATE_KEY_2)
        addDeleteButtonListener(binding.time3DeleteButton, binding.time3EditText, binding.day3Slider, DATE_TIME_3, DAY_3, DATE_KEY_3)
        addDeleteButtonListener(binding.time4DeleteButton, binding.time4EditText, binding.day4Slider, DATE_TIME_4, DAY_4, DATE_KEY_4)
        addDeleteButtonListener(binding.time5DeleteButton, binding.time5EditText, binding.day5Slider, DATE_TIME_5, DAY_5, DATE_KEY_5)
        addDeleteButtonListener(binding.time6DeleteButton, binding.time6EditText, binding.day6Slider, DATE_TIME_6, DAY_6, DATE_KEY_6)

        return view
    }

    private fun addTimePicker(textView: TextView) {
        textView.setOnClickListener {
            showTimePicker(textView)
        }
    }

    @SuppressLint("SetTextI18n")
    private fun showTimePicker(textView: TextView) {

        val picker = MaterialTimePicker.Builder()
            .setHour(0)
            .setMinute(0)
            .setTimeFormat(TimeFormat.CLOCK_24H)
            .build()
        picker.show(parentFragmentManager, null)

        picker.addOnPositiveButtonClickListener{
            val hour = picker.hour
            val minute = picker.minute
            // the format "%02d" specifies that the argument should be formatted as a decimal integer
            val formattedMinute = String.format("%02d", minute)

            Log.d("Show time", "$hour:$formattedMinute")
            textView.text = "$hour:$formattedMinute"

        }
    }

    private fun addChangedListener(textView: TextView, submitButton: Button, switch: SwitchCompat, mess: TextView) {

        textView.addTextChangedListener(object : TextWatcher {

            // This method is called after the text is changed
            override fun afterTextChanged(s: Editable?) {
            }
            // This method is called before the text is changed. It takes four parameters:
            // the text itself, the start position of the text which is going to change,
            // the length of the text which is going to change, and the count of the text which will be replaced by new text.
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            // This method is called when the text is changed. It also takes four parameters:
            // the changed text, the start position of the changed text,
            // the length of the changed text, and the count of the text which is replaced by new text.
            override fun onTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                val charSequence = s ?: ""
                handleTextErrors(submitButton, charSequence, mess)
            }
        })

    }

    private fun addSubmitButtonListener(
        submitButton: Button, textView: TextView, switch: SwitchCompat, timeKey: String, dayKey: String, dateKey: String) {

        submitButton.setOnClickListener {
            val time = textView.text.toString()
            val isSwitchChecked = switch.isChecked // if switch is checked then true else false
            val currentDate = LocalDate.now().toString()

            DayTimeSettingViewModel().saveToPrefs(timeKey, time)
            // Save switch state in SharedPreferences
            DayTimeSettingViewModel().saveToPrefs(dayKey, isSwitchChecked)
            // Save date state in SharedPreferences
            DayTimeSettingViewModel().saveToPrefs(dateKey, currentDate)
        }
    }

    private fun addDeleteButtonListener(deleteButton: Button, textView: TextView,
                                        switch: SwitchCompat, timeKey: String, dayKey: String, dateKey: String) {

        deleteButton.setOnClickListener {
            textView.text=""
            val time = textView.text.toString()
            switch.isChecked = false
            val isSwitchChecked = switch.isChecked
            val currentDate = ""

            // Save time state in SharedPreferences
            DayTimeSettingViewModel().saveToPrefs(timeKey, time)
            // Save switch state in SharedPreferences
            DayTimeSettingViewModel().saveToPrefs(dayKey, isSwitchChecked)
            // Save date state in SharedPreferences
            DayTimeSettingViewModel().saveToPrefs(dateKey, currentDate)
        }
    }

    /**
     * Check for input
     *
     */
    private fun handleTextErrors(button: Button, text: CharSequence, mess: TextView) {
        var error: Boolean

        if (text.toString().trim().length > 5) {
            mess.error = getString(R.string.set_right_time)
            error = true
        } else {
            mess.error = null
            error = false
        }

        // Disable button but no error if field is empty
        if (text.toString().trim().isEmpty()) {
            error = true
        }
        button.isEnabled = !error
    }

}
