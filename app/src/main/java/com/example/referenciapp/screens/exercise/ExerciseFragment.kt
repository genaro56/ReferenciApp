package com.example.referenciapp.screens.exercise

import android.content.ClipData
import android.content.ClipDescription
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Point
import android.graphics.drawable.ColorDrawable
import android.os.Build
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.DragEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.referenciapp.R
import com.example.referenciapp.ReferenceMenuViewModel
import com.example.referenciapp.database.PrintExercises
import com.example.referenciapp.databinding.FragmentExerciseBinding
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.android.synthetic.main.fragment_exercise.view.*
import kotlin.math.roundToInt


/**
 * A simple [Fragment] subclass.
 */
@Suppress("DEPRECATION")
class ExerciseFragment : Fragment() {

    lateinit var viewModel: ReferenceMenuViewModel
    companion object {
        // Default card elevation.
        const val CARD_ELEVATION_DEFAULT_DP: Float = 2f

        // Card elevation once the dragging has started.
        const val CARD_ELEVATION_DRAG_START_DP: Float = 8f

        // Card elevation once the color is dragged over one of the areas.
        const val CARD_ELEVATION_DRAG_ENTER_DP: Float = 16f
    }

    private val onLongClickListener = View.OnLongClickListener { view: View ->
        (view as? FloatingActionButton)?.let {

            // First we create the `ClipData.Item` that we will need for the `ClipData`.
            // The `ClipData` carries the information of what is being dragged.
            // If you look at the main activity layout XML, you'll see that we've stored
            // color values for each of the FABs as their tags.
            val item = ClipData.Item(it.tag as? CharSequence)

            // We create a `ClipData` for the drag action and save the color as plain
            // text using `ClipDescription.MIMETYPE_TEXT_PLAIN`.
            val dragData = ClipData(
                it.tag as? CharSequence,
                arrayOf(ClipDescription.MIMETYPE_TEXT_PLAIN),
                item
            )

            // Instantiates the drag shadow builder, which is the class we will use
            // to draw a shadow of the dragged object. The implementation details
            // are in the rest of the article.
            val myShadow = MyDragShadowBuilder(it)

            // Start the drag. The new method is called `startDragAndDrop()` instead
            // of `startDrag()`, so we'll use it on the newer API.
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                it.startDragAndDrop(dragData, myShadow, null, 0)
            } else {
                it.startDrag(dragData, myShadow, null, 0)
            }

            true
        }
        false
    }

    private val onDragListener = View.OnDragListener { view, dragEvent ->
        (view as? CardView)?.let {
            when (dragEvent.action) {
                // Once the drag event has started, we elevate all the views that are listening.
                // In our case, that's two of the areas.
                DragEvent.ACTION_DRAG_STARTED -> {
                    it.cardElevation = pxToDp(CARD_ELEVATION_DRAG_START_DP)
                    return@OnDragListener true
                }
                // Once the drag gesture enters a certain area, we want to elevate it even more.
                DragEvent.ACTION_DRAG_ENTERED -> {
                    it.cardElevation = pxToDp(CARD_ELEVATION_DRAG_ENTER_DP)
                    return@OnDragListener true
                }
                // No need to handle this for our use case.
                DragEvent.ACTION_DRAG_LOCATION -> {
                    return@OnDragListener true
                }
                // Once the drag gesture exits the area, we lower the elevation down to the previous one.
                DragEvent.ACTION_DRAG_EXITED -> {
                    it.cardElevation = pxToDp(CARD_ELEVATION_DRAG_START_DP)
                    return@OnDragListener true
                }
                // Once the color is dropped on the area, we want to paint it in that color.
                DragEvent.ACTION_DROP -> {
                    // Read color data from the clip data and apply it to the card view background.
                    val item: ClipData.Item = dragEvent.clipData.getItemAt(0)
                    val colorHex = item.text
                    it.setCardBackgroundColor(Color.parseColor(colorHex.toString()))
                    return@OnDragListener true
                }
                // Once the drag has ended, revert card views to the default elevation.
                DragEvent.ACTION_DRAG_ENDED -> {
                    it.cardElevation = pxToDp(CARD_ELEVATION_DEFAULT_DP)
                    return@OnDragListener true
                }
                else -> return@OnDragListener false
            }
        }
        false
    }

    private class MyDragShadowBuilder(v: View) : View.DragShadowBuilder(v) {

        private val shadowBorder = ColorDrawable(Color.BLACK)

        private val shadow = ColorDrawable(Color.parseColor(v.tag.toString()))

        // Defines a callback that sends the drag shadow dimensions and touch point back to the system.
        override fun onProvideShadowMetrics(size: Point, touch: Point) {
            // First, we define the shadow width and height. In our example, it will be
            // half of the size of the view that's been dragged.
            val width: Int = view.width / 2
            val height: Int = view.height / 2

            // The drag shadow is a `ColorDrawable`. This sets its dimensions to be the same as the
            // `Canvas` that the system will provide. We leave some room (four pixels) for the shadow border.
            shadow.setBounds(4, 4, width - 4, height - 4)
            shadowBorder.setBounds(0, 0, width, height)

            // Sets the size parameter's width and height values.
            // These get back to the system through the size parameter.
            size.set(width, height)

            // Sets the touch point's position to be in the middle of the drag shadow.
            touch.set(width / 2, height / 2)
        }

        // Defines a callback that draws the drag shadow in a `Canvas` that the
        // system constructs from the dimensions passed in `onProvideShadowMetrics()`.
        override fun onDrawShadow(canvas: Canvas) {

            // Draws the border drawable first.
            shadowBorder.draw(canvas)

            // Draws the actual shadow drawable onto the `Canvas` passed in
            // from the system so that the shadow content is above its border.
            shadow.draw(canvas)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentExerciseBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_exercise,
            container,
            false
        )

        // Set the View Model to observe the selected exercise.
        viewModel = ViewModelProvider(requireNotNull(activity)).get(ReferenceMenuViewModel::class.java)

        if(viewModel.resourceType.value == 0) {
            Toast.makeText(
                context,
                "Print: ${viewModel.currentPrintExercise.value!!.title}",
                Toast.LENGTH_SHORT
            ).show()
        }
        else {
            Toast.makeText(
                context,
                "Digital: ${viewModel.currentDigitalExercise.value!!.title}",
                Toast.LENGTH_SHORT
            ).show()
        }

        // Inflate the layout for this fragment
        val v = inflater.inflate(R.layout.fragment_exercise, container, false)
        val context = activity as AppCompatActivity

        val tv = v.findViewById<TextView>(R.id.reference_title)
        tv.text = arguments?.getString("title")
        val area1 = v.area1
        val area2 = v.area2
        val area3 = v.area3
        val area4 = v.area4
        val red = v.fabRed
        val blue = v.fabBlue
//        val green = v.fabGreen
        val purple = v.fabPurple
        val yellow = v.fabYellow

        area1.setOnDragListener(onDragListener)
        area2.setOnDragListener(onDragListener)
        area3.setOnDragListener(onDragListener)
        area4.setOnDragListener(onDragListener)

        red.setOnLongClickListener(onLongClickListener)
        blue.setOnLongClickListener(onLongClickListener)
//        green.setOnLongClickListener(onLongClickListener)
        purple.setOnLongClickListener(onLongClickListener)
        yellow.setOnLongClickListener(onLongClickListener)

        return v
    }

    private fun pxToDp(px: Float): Float {
        val displayMetrics = requireContext().resources.displayMetrics
        return (px / (displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT)).roundToInt().toFloat()
    }
}
