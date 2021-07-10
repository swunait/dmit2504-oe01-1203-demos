package ca.nait.dmit.dmit2054.wclclottoappwidget

import android.app.PendingIntent
import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.View
import android.widget.RemoteViews
import java.util.*
import kotlin.random.Random

/**
 * Implementation of App Widget functionality.
 * App Widget Configuration implemented in [LottoAppWidgetConfigureActivity]
 */
class LottoAppWidget : AppWidgetProvider() {

    companion object {
        const val EXTRA_APP_WIDGET_ID = "EXTRA_APP_WIDGET_ID"
    }

    override fun onReceive(context: Context?, intent: Intent?) {
        val appWidgetId = intent?.getIntExtra(EXTRA_APP_WIDGET_ID, AppWidgetManager.INVALID_APPWIDGET_ID)
        val appWidgetManager = AppWidgetManager.getInstance(context)
        if (appWidgetId != AppWidgetManager.INVALID_APPWIDGET_ID) {
            updateAppWidget(context!!, appWidgetManager, appWidgetId!!)
        }

        super.onReceive(context, intent)
    }

    override fun onUpdate(
        context: Context,
        appWidgetManager: AppWidgetManager,
        appWidgetIds: IntArray
    ) {
        // There may be multiple widgets active, so update all of them
        for (appWidgetId in appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId)
        }
    }

    override fun onDeleted(context: Context, appWidgetIds: IntArray) {
        // When the user deletes the widget, delete the preference associated with it.
//        for (appWidgetId in appWidgetIds) {
//            deleteTitlePref(context, appWidgetId)
//        }
    }

    override fun onEnabled(context: Context) {
        // Enter relevant functionality for when the first widget is created
    }

    override fun onDisabled(context: Context) {
        // Enter relevant functionality for when the last widget is disabled
    }
}

internal fun updateAppWidget(
    context: Context,
    appWidgetManager: AppWidgetManager,
    appWidgetId: Int
) {
    // Read the lottoType, totalNumbers and maxNumber from appWidgetId sharedPreference
    val prefs = context.getSharedPreferences(appWidgetId.toString(), Context.MODE_PRIVATE)
    val lottoType = prefs.getString(PREF_LOTTOTYPE,"LOTTO649")
    val totalNumbers = prefs.getInt(PREF_TOTAL_NUMBERS,6)
    val maxNumber = prefs.getInt(PREF_MAX_NUMBER, 49)
    // Generate random numbers for the lotto type
    val quickPickNumbersSet = TreeSet<Int>()
    while (quickPickNumbersSet.size < totalNumbers) {
        // Generate a random number between 1 and maxNumber
        val randomNumber = Random.nextInt(1, maxNumber + 1)
        quickPickNumbersSet.add(randomNumber)
    }
//    val widgetText = loadTitlePref(context, appWidgetId)
    val widgetText = quickPickNumbersSet.toString()

    // Construct the RemoteViews object
    val views = RemoteViews(context.packageName, R.layout.lotto_app_widget)
    views.setTextViewText(R.id.appwidget_text, widgetText)

    val lottoWidgetIntent = Intent(context, LottoAppWidget::class.java)
    lottoWidgetIntent.putExtra(LottoAppWidget.EXTRA_APP_WIDGET_ID, appWidgetId)
    lottoWidgetIntent.setData(Uri.parse(lottoWidgetIntent.toUri(Intent.URI_INTENT_SCHEME)))
    val lottoWidgetPendingIntent = PendingIntent.getBroadcast(context,0, lottoWidgetIntent,0)
    // Determine which ImageButton(Lotto649 or LottoMAX) to display
    if (lottoType == "LOTTO649") {
        views.setViewVisibility(R.id.appwidget_lottomax_button, View.GONE)
        views.setOnClickPendingIntent(R.id.appwidget_lotto649_button, lottoWidgetPendingIntent)
    } else {
        views.setViewVisibility(R.id.appwidget_lotto649_button, View.GONE)
        views.setOnClickPendingIntent(R.id.appwidget_lottomax_button, lottoWidgetPendingIntent)
    }

    // Instruct the widget manager to update the widget
    appWidgetManager.updateAppWidget(appWidgetId, views)
}