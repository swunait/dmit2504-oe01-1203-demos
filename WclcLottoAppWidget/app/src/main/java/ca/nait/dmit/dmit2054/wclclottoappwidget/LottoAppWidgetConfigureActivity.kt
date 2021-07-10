package ca.nait.dmit.dmit2054.wclclottoappwidget

import android.app.Activity
import android.appwidget.AppWidgetManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.RadioGroup
import android.widget.Toast
import ca.nait.dmit.dmit2054.wclclottoappwidget.databinding.LottoAppWidgetConfigureBinding

/**
 * The configuration screen for the [LottoAppWidget] AppWidget.
 */

const val PREF_LOTTOTYPE = "ca.nait.dmit.dmit2504.LOTTO_TYPE"
const val PREF_TOTAL_NUMBERS = "ca.nait.dmit.dmit2504.TOTAL_NUMBERS"
const val PREF_MAX_NUMBER = "ca.nait.dmit.dmit2504.MAX_NUMBER"

class LottoAppWidgetConfigureActivity : Activity() {

    private var appWidgetId = AppWidgetManager.INVALID_APPWIDGET_ID
//    private lateinit var appWidgetText: EditText
    private lateinit var lottoTypeGroup: RadioGroup

    private var onClickListener = View.OnClickListener {
        val context = this@LottoAppWidgetConfigureActivity

        // When the button is clicked, store the string locally
//        val widgetText = appWidgetText.text.toString()
//        saveTitlePref(context, appWidgetId, widgetText)
        val prefs = getSharedPreferences(appWidgetId.toString(), MODE_PRIVATE).edit()
        when (lottoTypeGroup.checkedRadioButtonId) {
            R.id.configure_lotto649_radiobutton -> {
                prefs
                    .putString(PREF_LOTTOTYPE,"LOTTO649")
                    .putInt(PREF_TOTAL_NUMBERS, 6)
                    .putInt(PREF_MAX_NUMBER, 49)
                    .commit()
            }
            R.id.configure_lottomax_radiobutton -> {
                prefs
                    .putString(PREF_LOTTOTYPE,"LOTTOMAX")
                    .putInt(PREF_TOTAL_NUMBERS, 7)
                    .putInt(PREF_MAX_NUMBER, 50)
                    .commit()
            }
            else -> {
                Toast.makeText(this,"You must select a lotto type", Toast.LENGTH_SHORT).show()
            }
        }


        // It is the responsibility of the configuration activity to update the app widget
        val appWidgetManager = AppWidgetManager.getInstance(context)
        updateAppWidget(context, appWidgetManager, appWidgetId)

        // Make sure we pass back the original appWidgetId
        val resultValue = Intent()
        resultValue.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetId)
        setResult(RESULT_OK, resultValue)
        finish()
    }
    private lateinit var binding: LottoAppWidgetConfigureBinding

    public override fun onCreate(icicle: Bundle?) {
        super.onCreate(icicle)

        // Set the result to CANCELED.  This will cause the widget host to cancel
        // out of the widget placement if the user presses the back button.
        setResult(RESULT_CANCELED)

        binding = LottoAppWidgetConfigureBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        appWidgetText = binding.appwidgetText as EditText
        lottoTypeGroup = binding.configureLottotypeGroup

        binding.addButton.setOnClickListener(onClickListener)

        // Find the widget id from the intent.
        val intent = intent
        val extras = intent.extras
        if (extras != null) {
            appWidgetId = extras.getInt(
                AppWidgetManager.EXTRA_APPWIDGET_ID, AppWidgetManager.INVALID_APPWIDGET_ID
            )
        }

        // If this activity was started with an intent without an app widget ID, finish with an error.
        if (appWidgetId == AppWidgetManager.INVALID_APPWIDGET_ID) {
            finish()
            return
        }

//        appWidgetText.setText(loadTitlePref(this@LottoAppWidgetConfigureActivity, appWidgetId))
    }

}

//private const val PREFS_NAME = "ca.nait.dmit.dmit2054.wclclottoappwidget.LottoAppWidget"
//private const val PREF_PREFIX_KEY = "appwidget_"
//
//// Write the prefix to the SharedPreferences object for this widget
//internal fun saveTitlePref(context: Context, appWidgetId: Int, text: String) {
//    val prefs = context.getSharedPreferences(PREFS_NAME, 0).edit()
//    prefs.putString(PREF_PREFIX_KEY + appWidgetId, text)
//    prefs.apply()
//}
//
//// Read the prefix from the SharedPreferences object for this widget.
//// If there is no preference saved, get the default from a resource
//internal fun loadTitlePref(context: Context, appWidgetId: Int): String {
//    val prefs = context.getSharedPreferences(PREFS_NAME, 0)
//    val titleValue = prefs.getString(PREF_PREFIX_KEY + appWidgetId, null)
//    return titleValue ?: context.getString(R.string.appwidget_text)
//}
//
//internal fun deleteTitlePref(context: Context, appWidgetId: Int) {
//    val prefs = context.getSharedPreferences(PREFS_NAME, 0).edit()
//    prefs.remove(PREF_PREFIX_KEY + appWidgetId)
//    prefs.apply()
//}