package ca.nait.dmit.dmit2054.batterylevelhomescreenwidget

import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.*
import android.os.BatteryManager
import android.widget.RemoteViews

/**
 * Implementation of App Widget functionality.
 */
class BatteryLevelAppWidget : AppWidgetProvider() {

    inner class BatteryLevelBroadcastReceiver : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            // Read the current battery level
            val currentBatteryLevel = intent?.getIntExtra(BatteryManager.EXTRA_LEVEL, 0)
            // Create RemoteView of the AppWidget layout
            val appWidgetLayout = RemoteViews(context?.packageName, R.layout.battery_level_app_widget)
            appWidgetLayout.setTextViewText(R.id.appwidget_text,"Battery level: ${currentBatteryLevel}%")
            // Use the ComponentName instead of appWidgetIds to update all AppWidgets of the provider
            val appWidget = ComponentName(context!!, BatteryLevelAppWidget::class.java)
            AppWidgetManager.getInstance(context).updateAppWidget(appWidget, appWidgetLayout)
        }
    }
    // Create an instance of our BatteryLevelBroadcastReceiver
    val batteryLevelReceiver = BatteryLevelBroadcastReceiver()


    override fun onUpdate(
        context: Context,
        appWidgetManager: AppWidgetManager,
        appWidgetIds: IntArray
    ) {
        context.applicationContext.registerReceiver(batteryLevelReceiver,
            IntentFilter(Intent.ACTION_BATTERY_CHANGED))
    }

    override fun onEnabled(context: Context) {
        // Enter relevant functionality for when the first widget is created
    }

    override fun onDisabled(context: Context) {
        // Enter relevant functionality for when the last widget is disabled
    }
}

//internal fun updateAppWidget(
//    context: Context,
//    appWidgetManager: AppWidgetManager,
//    appWidgetId: Int
//) {
//    val widgetText = context.getString(R.string.appwidget_text)
//    // Construct the RemoteViews object
//    val views = RemoteViews(context.packageName, R.layout.battery_level_app_widget)
//    views.setTextViewText(R.id.appwidget_text, widgetText)
//
//    // Instruct the widget manager to update the widget
//    appWidgetManager.updateAppWidget(appWidgetId, views)
//}