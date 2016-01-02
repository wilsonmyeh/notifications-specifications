package woofsonmeowry.notificationspecification;

import android.content.Context;
import android.content.Intent;
import android.support.v4.content.WakefulBroadcastReceiver;
public class WakefulReceiver extends WakefulBroadcastReceiver
{
    @Override
    public void onReceive (Context context, Intent intent)
    {
        startWakefulService (context, intent);
    }
}