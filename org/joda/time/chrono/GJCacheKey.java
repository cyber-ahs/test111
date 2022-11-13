package org.joda.time.chrono;

import org.joda.time.DateTimeZone;
import org.joda.time.Instant;
/* loaded from: classes2.dex */
class GJCacheKey {
    private final Instant cutoverInstant;
    private final int minDaysInFirstWeek;
    private final DateTimeZone zone;

    /* JADX INFO: Access modifiers changed from: package-private */
    public GJCacheKey(DateTimeZone dateTimeZone, Instant instant, int i) {
        this.zone = dateTimeZone;
        this.cutoverInstant = instant;
        this.minDaysInFirstWeek = i;
    }

    public int hashCode() {
        Instant instant = this.cutoverInstant;
        int hashCode = ((((instant == null ? 0 : instant.hashCode()) + 31) * 31) + this.minDaysInFirstWeek) * 31;
        DateTimeZone dateTimeZone = this.zone;
        return hashCode + (dateTimeZone != null ? dateTimeZone.hashCode() : 0);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && (obj instanceof GJCacheKey)) {
            GJCacheKey gJCacheKey = (GJCacheKey) obj;
            Instant instant = this.cutoverInstant;
            if (instant == null) {
                if (gJCacheKey.cutoverInstant != null) {
                    return false;
                }
            } else if (!instant.equals(gJCacheKey.cutoverInstant)) {
                return false;
            }
            if (this.minDaysInFirstWeek != gJCacheKey.minDaysInFirstWeek) {
                return false;
            }
            DateTimeZone dateTimeZone = this.zone;
            if (dateTimeZone == null) {
                if (gJCacheKey.zone != null) {
                    return false;
                }
            } else if (!dateTimeZone.equals(gJCacheKey.zone)) {
                return false;
            }
            return true;
        }
        return false;
    }
}
