package com.abdurrahmanjun.core.view

import android.content.Context
import android.text.InputType
import android.util.AttributeSet
import android.widget.EditText
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.abdurrahmanjun.core.R

class TrolliInputField(context: Context, attrs: AttributeSet) : ConstraintLayout(context, attrs) {

    private val tvLabel: TextView by lazy { findViewById(R.id.trolli_tv_field_title) }
    private val editText: EditText by lazy { findViewById(R.id.trolli_field_edit) }

    var text: String
        get() = editText.text.toString()
        set(value) {
            editText.setText(value)
        }

    init {
        inflate(context, R.layout.layout_trolli_input, this)

        context.obtainStyledAttributes(attrs, R.styleable.TrolliInputField, 0, 0).apply {
            val label = getString(R.styleable.TrolliInputField_android_label).orEmpty()
            val hint = getString(R.styleable.TrolliInputField_android_hint) ?: label
            val inputType = getInt(R.styleable.TrolliInputField_android_inputType, InputType.TYPE_CLASS_TEXT)

            tvLabel.text = label
            editText.hint = hint
            editText.inputType = inputType

            recycle()
        }
    }
}