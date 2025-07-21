package com.uilover.project2322.Activity

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.formatter.ValueFormatter
import com.uilover.project2322.Adapter.TransactionAdapter
import com.uilover.project2322.Domain.Profile
import com.uilover.project2322.R
import com.uilover.project2322.databinding.ActivityOverviewBinding
import java.util.Calendar
import java.util.Locale

class OverviewActivity : AppCompatActivity() {
    lateinit var binding: ActivityOverviewBinding
    private lateinit var profile: Profile

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOverviewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initChart()
        setVariable()
        bundle()
        initLastTransaction()

    }

    private fun initLastTransaction() {
        binding.transactionList.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.transactionList.adapter = TransactionAdapter(profile.transaction)
    }

    private fun bundle() {
        profile = intent.getSerializableExtra("object") as Profile
    }

    private fun setVariable() {
        binding.backBtn.setOnClickListener {
            startActivity(Intent(this@OverviewActivity, MainActivity::class.java))
        }
    }

    private fun initChart() {
        val values = listOf(150f, 200f, 50f, 100f, 250f, 80f, 300f)
        val entries = mutableListOf<BarEntry>()
        for (i in values.indices) {
            entries.add(BarEntry(i.toFloat(), values[i]))
        }
        val barDataSet = BarDataSet(entries, "Statistics")

        val colors = MutableList(values.size) { Color.GRAY }
        colors[values.lastIndex] = resources.getColor(R.color.green)
        barDataSet.colors = colors


        val data = BarData(barDataSet)
        data.barWidth = 0.7f
        binding.barChart.description.isEnabled = false
        binding.barChart.isEnabled = false

        binding.barChart.data = data

        val daysOfWeek = getLast7DaysShortNames()

        val xAxis = binding.barChart.xAxis
        xAxis.textColor = Color.WHITE
        xAxis.valueFormatter = object : ValueFormatter() {
            override fun getFormattedValue(value: Float): String {
                return daysOfWeek.getOrNull(value.toInt()) ?: value.toString()
            }
        }

        xAxis.position = XAxis.XAxisPosition.BOTTOM
        xAxis.setDrawGridLines(false)
        xAxis.granularity = 1f
        xAxis.labelCount = daysOfWeek.size

        binding.barChart.axisRight.isEnabled = false
        binding.barChart.invalidate()
    }

    private fun getLast7DaysShortNames(): List<String> {
        return if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            // برای API 26 به بالا (Android 8.0+)
            val today = java.time.LocalDate.now()
            (6 downTo 0).map { offset ->
                today.minusDays(offset.toLong())
                    .dayOfWeek
                    .getDisplayName(java.time.format.TextStyle.SHORT, java.util.Locale.ENGLISH)
            }

        } else {
            // برای API پایین‌تر
            val calendar = java.util.Calendar.getInstance()
            val format = java.text.SimpleDateFormat("EEE", java.util.Locale.ENGLISH)
            val days = mutableListOf<String>()
            for (i in 6 downTo 0) {
                calendar.time = java.util.Date()
                calendar.add(java.util.Calendar.DAY_OF_YEAR, -i)
                days.add(format.format(calendar.time))
            }
            days
        }
    }
}