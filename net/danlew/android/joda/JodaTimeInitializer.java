package net.danlew.android.joda;

import android.content.Context;
import android.content.IntentFilter;
import androidx.startup.Initializer;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import org.joda.time.DateTimeZone;
/* loaded from: classes2.dex */
public class JodaTimeInitializer implements Initializer<Object> {
    @Override // androidx.startup.Initializer
    public Object create(Context context) {
        try {
            DateTimeZone.setProvider(new ResourceZoneInfoProvider(context));
            context.getApplicationContext().registerReceiver(new TimeZoneChangedReceiver(), new IntentFilter("android.intent.action.TIMEZONE_CHANGED"));
            return new Object();
        } catch (IOException e) {
            throw new RuntimeException("Could not read ZoneInfoMap. You are probably using Proguard wrong.", e);
        }
    }

    @Override // androidx.startup.Initializer
    public List<Class<? extends Initializer<?>>> dependencies() {
        return Collections.emptyList();
    }
}
