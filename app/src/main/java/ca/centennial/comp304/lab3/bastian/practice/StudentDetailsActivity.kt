package ca.centennial.comp304.lab3.bastian.practice

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import ca.centennial.comp304.lab3.bastian.practice.databinding.ActivityStudentDetailsBinding



class StudentDetailsActivity: AppCompatActivity() {
    private lateinit var binding: ActivityStudentDetailsBinding
    private var programDuration= String()
    private var semesters= String()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStudentDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val programName = intent.getStringExtra("program_name")
        val totalTuition = intent.getStringExtra("total_tuition")
        semesters = intent.getStringExtra("semesters").toString()
        binding.textViewProgramName.text="Program Information:".plus("\n").plus("Program Name- ").plus(programName).plus("\n").plus("Total Tuitio Fee -").plus(totalTuition)

        binding.buttonPrev.setOnClickListener {
            onBackPressed()
        }


        binding.buttonSubmit.setOnClickListener {
            val studentID = binding.editTextStudentID.text.toString()
            val studentName = binding.editTextStudentName.text.toString()
            val email = binding.editTextEmail.text.toString()

            //val rb = findViewById<RadioButton>(binding.radioGroupDeliveryMode.checkedRadioButtonId)
            //val deliveryMode = rb.text.toString()

            // Perform validation for Student ID, Student Name, and E-mail
            if (studentID.isEmpty() || studentName.isEmpty() || email.isEmpty()) {
                Toast.makeText(this, "Please fill in all required fields.", Toast.LENGTH_SHORT).show()
            } else if(binding.radioGroupDeliveryMode.checkedRadioButtonId==-1){
                Toast.makeText(this, "Please select delivery mode.", Toast.LENGTH_SHORT).show()
            } else {
                // Display information in a Toast
                val toastMessage = "Program Name: $programName\nStudent Name: $studentName\nTotal Tuition Fee: $totalTuition \nNumber of Semesters: $semesters"
                Toast.makeText(this, toastMessage, Toast.LENGTH_LONG).show()
            }
        }
    }
}