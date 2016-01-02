package woofsonmeowry.notificationspecification;

import android.app.IntentService;
import android.content.Intent;
import android.service.notification.NotificationListenerService;

public class AlarmIntentService extends IntentService
{
    public AlarmIntentService()
    {
        super("Mary"); //names service thread

    }

    @Override
    protected void onHandleIntent(Intent intent)
    {

    }
}
