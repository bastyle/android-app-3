package ca.centennial.comp304.lab3.bastian.practice

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import ca.centennial.comp304.lab3.bastian.practice.databinding.ActivityChartBinding
import java.util.ArrayList

private const val Y_AUX_VALUE = 600f
private const val X_AUX_VALUE = 150f
class ChartActivity: AppCompatActivity() {
    private lateinit var binding: ActivityChartBinding
    private lateinit var reusableImageView: ImageView
    private lateinit var textView: TextView
    private lateinit var canvas: Canvas
    private lateinit var extraBitmap: Bitmap
    private lateinit var bitmap: Bitmap
    private val paint = Paint()
    private val colors = arrayOf(
        Color.BLUE,
        Color.BLACK,
        Color.CYAN,
        Color.DKGRAY,
        Color.GREEN,
        Color.RED,
        Color.MAGENTA
    )
    //private lateinit var selectedItems: ArrayList<String>

    companion object {
        private const val TAG = "ChartActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChartBinding.inflate(layoutInflater)
        setContentView(binding.root)
        bitmap = Bitmap.createBitmap(
            windowManager
                .defaultDisplay.width, windowManager
                .defaultDisplay.height, Bitmap.Config.ARGB_8888
        )
        canvas = Canvas(bitmap)
        //canvas.drawColor(Color.GRAY) //background
        reusableImageView = binding.imageViewForDrawing as ImageView
        reusableImageView.setImageBitmap(bitmap)
        reusableImageView.setVisibility(View.VISIBLE)
        val selectedItems = intent.extras?.getStringArrayList("extra_data")
        paint.strokeWidth=8f
        drawGraph(selectedItems)
    }

    fun drawGraph(data: ArrayList<String>?) {
        var index =0
        data?.forEach{
            val values = resources.getStringArray(resources.getIdentifier("${it.replace(" ","_")}_values", "array", packageName))
            Log.e("Drawing: ", values.size.toString())
            var auxX:Float=X_AUX_VALUE
            var auxY:Float= Y_AUX_VALUE
            paint.color = colors[index]
            for (i in 1 until  values.size+1){
                //Log.e("TAG", "Y: "+auxY+ " Y2: "+values.get(i-1).toFloat())
                //Log.e("TAG", "X: "+auxX+ " X2: "+i*X_AUX_VALUE)
                var stopX = i*X_AUX_VALUE
                if(i==1) {
                    Log.e("TAG", "X: "+0+ " X2: "+stopX)
                    canvas.drawLine(0f,Y_AUX_VALUE - values.get(i - 1).toFloat(),stopX,Y_AUX_VALUE - values.get(i - 1).toFloat(),paint)
                }else{
                    canvas.drawLine(auxX,auxY, stopX, Y_AUX_VALUE - values.get(i-1).toFloat(),paint)
                }
                auxX=i*X_AUX_VALUE
                auxY=Y_AUX_VALUE-values.get(i-1).toFloat()
            }
            index++
        }
    }


}