package ca.centennial.comp304.lab3.bastian.practice

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.CheckBox
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import ca.centennial.comp304.lab3.bastian.practice.databinding.ActivityCheckboxesBinding

class CheckboxesActivity: AppCompatActivity() {

    private lateinit var binding: ActivityCheckboxesBinding
    private var selectedItems = ArrayList<String>()

    companion object {
        private const val TAG = "CheckboxesActivity"

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCheckboxesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val provinces = resources.getStringArray(R.array.provinces)
        val container = binding.container

        provinces.forEach {
            val checkBox = CheckBox(this)
            checkBox.text = it
            container.addView(checkBox)
            checkBox.setOnCheckedChangeListener { buttonView, isChecked ->
                if (isChecked) {
                    selectedItems.add(it)
                } else {
                    selectedItems.remove(it)
                }
            }
        }

        binding.button3.setOnClickListener {
            Toast.makeText(this, "Button!!", Toast.LENGTH_SHORT).show()
            selectedItems.forEach{
                Log.e(TAG, it)
            }
            val intent = Intent(this, ChartActivity::class.java)
            intent.putExtra("extra_data",selectedItems)
            startActivity(intent)
        }
    }

    /*override fun onBackPressed() {
        val intent = Intent(this@CheckboxesActivity, MainActivity::class.java)
        startActivity(intent)
        finish()
    }*/


}