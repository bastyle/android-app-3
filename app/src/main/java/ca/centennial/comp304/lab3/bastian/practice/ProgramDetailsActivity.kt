package ca.centennial.comp304.lab3.bastian.practice


import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import ca.centennial.comp304.lab3.bastian.practice.databinding.ActivityProgramBinding

class ProgramDetailsActivity: AppCompatActivity() {
    private lateinit var binding: ActivityProgramBinding
    private var programDuration= String()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProgramBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val programDuration = resources.getStringArray(R.array.program_duration)
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, programDuration)

        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        // Apply the adapter to the spinner
        binding.spinner.adapter = adapter

        // Optionally, you can set an item selection listener to handle item selection events
        binding.spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                this@ProgramDetailsActivity.programDuration = programDuration[position]
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // Handle the case where nothing is selected
            }
        }
        binding.buttonPickDate.setOnClickListener {
            // Show or hide the DatePicker when the button is clicked
            if (binding.datePicker.visibility == View.VISIBLE) {
                binding.datePicker.visibility = View.GONE
            } else {
                binding.datePicker.visibility = View.VISIBLE
            }
        }
        binding.datePicker.setOnDateChangedListener { view, year, monthOfYear, dayOfMonth ->
            val selectedDate = "$year-${monthOfYear + 1}-$dayOfMonth" // month is 0-based
            // Validate the selected date (you can add your validation logic here)
            binding.datePicked.text = selectedDate
            binding.datePicker.visibility = View.GONE
        }

        val courseCheckBoxes = listOf(
            binding.checkBox1, binding.checkBox2, binding.checkBox3, binding.checkBox4
        )


        binding.buttonNext.setOnClickListener {
            //Toast.makeText(this, "Next", Toast.LENGTH_SHORT).show()
            if (binding.editTextProgramID.text.isEmpty() || binding.editTextProgramID.text.length != 4) {
                binding.editTextProgramID.error = "Enter a valid 4-digit Program ID"
            }else if (binding.editTextProgramName.text.isEmpty()) {
                binding.editTextProgramName.error = "Program Name cannot be empty"
            }  else if (courseCheckBoxes.filter { it.isChecked }.size < 2) {
                Toast.makeText(this, "Select at least two courses.", Toast.LENGTH_SHORT).show()
            } else if(binding.tuition.text.isEmpty()){
                Toast.makeText(this, "Tuition cannot be empty.", Toast.LENGTH_SHORT).show()
            }else{
                val intent = Intent(this, StudentDetailsActivity::class.java)
                intent.putExtra("program_name",binding.editTextProgramName.text.toString())
                intent.putExtra("tuition",binding.tuition.text.toString().toFloat())
                intent.putExtra("semesters",getSemesters().toString())
                intent.putExtra("total_tuition",getTotalTuition(binding.tuition.text.toString()).toString())
                startActivity(intent)
            }
        }

    }

    private fun getTotalTuition(tuition:String):Float{
        return tuition.toFloat()*getSemesters()
    }

    private fun getSemesters():Int{
        if(programDuration.contains("2")){
            return 2
        }else if(programDuration.contains("4")) {
            return 4
        }else if(programDuration.contains("6")) {
            return 6
        }else if(programDuration.contains("8")) {
            return 8
        }
        return 0
    }



}