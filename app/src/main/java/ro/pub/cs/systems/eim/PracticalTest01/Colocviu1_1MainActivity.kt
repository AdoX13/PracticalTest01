package ro.pub.cs.systems.eim.PracticalTest01

import android.app.Activity
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import ro.pub.cs.systems.eim.PracticalTest01.Constants.INPUT1
import ro.pub.cs.systems.eim.PracticalTest01.Constants.INPUT2

class Colocviu1_1MainActivity : AppCompatActivity() {
    private lateinit var input1: EditText
    private lateinit var input2: EditText
    private val intentFilter = IntentFilter()

    private var leftNumber = 0
    private var rightNumber = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_colocviu1_1_main)

        input1 = findViewById(R.id.input1)
        input2 = findViewById(R.id.input2)
        input1.setText("0")
        input2.setText("0")
        val pressMeButton = findViewById<Button>(R.id.press_me_button)
        pressMeButton.setOnClickListener {
            leftNumber++
            input1.setText(leftNumber.toString())
        }

        val pressMeToo = findViewById<Button>(R.id.press_me_too_button)
        pressMeToo.setOnClickListener {
            rightNumber++
            input2.setText(rightNumber.toString())
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    val activityResultsLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            Toast.makeText(this, "The activity returned with result OK", Toast.LENGTH_LONG).show()
        }
        else if (result.resultCode == Activity.RESULT_CANCELED) {
            Toast.makeText(this, "The activity returned with result CANCELED", Toast.LENGTH_LONG).show()
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        outState.putString(INPUT1, input1.text.toString())
        outState.putString(INPUT2, input2.text.toString())
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        if (savedInstanceState.containsKey(INPUT1) && savedInstanceState.containsKey(INPUT2)) {
            input1.setText(savedInstanceState.getString(INPUT1))
            input2.setText(savedInstanceState.getString(INPUT2))

            leftNumber = savedInstanceState.getString(INPUT1)?.toInt() ?: 0
            rightNumber = savedInstanceState.getString(INPUT2)?.toInt() ?: 0
        }
    }
}