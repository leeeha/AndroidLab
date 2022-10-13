package com.tutorial.c53

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.DialogInterface
import android.os.Bundle
import android.widget.Button
import android.widget.DatePicker
import android.widget.TimePicker
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val alertDialog = findViewById<Button>(R.id.alertDialog)
        val listDialog = findViewById<Button>(R.id.listDialog)
        val dateDialog = findViewById<Button>(R.id.dateDialog)
        val timeDialog = findViewById<Button>(R.id.timeDialog)

        alertDialog.setOnClickListener {
            AlertDialog.Builder(this).run {
                setTitle("test dialog")
                setIcon(android.R.drawable.ic_dialog_alert)
                setMessage("정말 종료하시겠습니까?")
                setPositiveButton("OK", null)
                setNegativeButton("Cancel", null)
                show()
            }
        }

        listDialog.setOnClickListener {
            val items = arrayOf("사과", "복숭아", "수박")
            AlertDialog.Builder(this).run {
                setTitle("items test")
                setItems(items, object: DialogInterface.OnClickListener{
                    override fun onClick(p0: DialogInterface?, pos: Int) {
                        Toast.makeText(this@MainActivity,
                            "선택한 항목: ${items[pos]}", Toast.LENGTH_SHORT).show()
                    }
                })
                setPositiveButton("OK", null)
                show()
            }
        }

        dateDialog.setOnClickListener {
            DatePickerDialog(this, object: DatePickerDialog.OnDateSetListener{
                override fun onDateSet(p0: DatePicker?, year: Int, month: Int, day: Int) {
                    Toast.makeText(this@MainActivity,
                        "${year}, ${month + 1}, ${day}", Toast.LENGTH_SHORT).show()
                }
            }, 2022, 10, 12).show()
        }

        timeDialog.setOnClickListener {
            TimePickerDialog(this, object: TimePickerDialog.OnTimeSetListener{
                override fun onTimeSet(p0: TimePicker?, p1: Int, p2: Int) {
                    Toast.makeText(this@MainActivity,
                        "$p1, $p2", Toast.LENGTH_SHORT).show()
                }
            }, 0, 0, true).show()
        }
    }
}
