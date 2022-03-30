package eu.learning.seconds

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.CalendarView
import android.widget.TextView
import android.widget.Toast
import java.sql.Date
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    // declaring the text views
    private var selectedTv: TextView? = null
    private var outputAgetv: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        // linking buttons and text views to their id's.
        val dpBtn : Button = findViewById(R.id.btn1)

        selectedTv = findViewById(R.id.SelectedDate)
        outputAgetv = findViewById(R.id.Age)

        // defining actions to be taken once the button is clicked
        dpBtn.setOnClickListener {
            clickDatePicker()
        }



    }
    private fun clickDatePicker(){
        // Define variables for the calendar
        val myCalendar = Calendar.getInstance()
        val year = myCalendar.get(Calendar.YEAR)
        val month = myCalendar.get(Calendar.MONTH)
        // month starts form 0 here, that is, Jan is 0th month and Dec is 11th.
        val day = myCalendar.get(Calendar.DAY_OF_MONTH)

        val dpd = DatePickerDialog(this, DatePickerDialog.OnDateSetListener{_,selectedYear,selectedMonth,selectedDay ->

            Toast.makeText(this , "day is $selectedDay, month is ${selectedMonth+1} year is $selectedYear" , Toast.LENGTH_LONG).show()

            val selectedDate = "$selectedDay/${selectedMonth+1}/$selectedYear "
            // object has been created to pass in the selectedTv text view.
            selectedTv?.text = selectedDate
            // selecteDate has been parsed into it's respective text view (selectedTv)

            // a date format is needed for formatting and parsing in a specific date format.
            val sdf = SimpleDateFormat("dd/MM/yyyy" , Locale.ENGLISH) // a class has been created for the same.

            val theDate = sdf.parse(selectedDate)
            // selectedDate has been parsed into the variable in sdf format.

            // we need time to in seconds from the selected date so:
            // we use time function to get time(in milli sec) passed from 1st jan of 1970 till the selected date.
            val selecteddateinsec = theDate.time /1000 // we have time in seconds now

            // to fetch time till the current date we take date from the system itself and parse it in a variable.
            val currentDate = sdf.parse(sdf.format(System.currentTimeMillis()))

            // we calculate time passed till(in milli sec) the current date from 1st Jan 1970
            val currentDateinsec  =currentDate.time/1000 // now we have time in seconds
            val diff = currentDateinsec-selecteddateinsec // we obtain the the time passed(in sec) in duration of selected date and current date

            outputAgetv?.text=diff.toString() // we assign the value of that time to the outputAgeTv text view.

        },
        year,
        month,
        day)

        // we put  a restriction on calendar to not take future dates.
        dpd.datePicker.maxDate= System.currentTimeMillis()-86400000

        // to show the toast message.
        dpd.show()


    }
}