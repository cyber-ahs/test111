package munirkhanani.helpers;

import android.hardware.fingerprint.FingerprintManager;
import android.os.CancellationSignal;
/* loaded from: classes2.dex */
public class FingerprintHelper extends FingerprintManager.AuthenticationCallback {
    private CancellationSignal cancellationSignal;
    private FingerprintHelperListener listener;

    /* loaded from: classes2.dex */
    public interface FingerprintHelperListener {
        void authenticationFailed(String str);

        void authenticationSuccess(FingerprintManager.AuthenticationResult authenticationResult);
    }

    public FingerprintHelper(FingerprintHelperListener fingerprintHelperListener) {
        this.listener = fingerprintHelperListener;
    }

    public void startAuth(FingerprintManager fingerprintManager, FingerprintManager.CryptoObject cryptoObject) {
        CancellationSignal cancellationSignal = new CancellationSignal();
        this.cancellationSignal = cancellationSignal;
        try {
            fingerprintManager.authenticate(cryptoObject, cancellationSignal, 0, this, null);
        } catch (SecurityException e) {
            FingerprintHelperListener fingerprintHelperListener = this.listener;
            fingerprintHelperListener.authenticationFailed("An error occurred: " + e.getMessage());
        } catch (Exception e2) {
            FingerprintHelperListener fingerprintHelperListener2 = this.listener;
            fingerprintHelperListener2.authenticationFailed("An error occurred: " + e2.getMessage());
        }
    }

    public void cancel() {
        CancellationSignal cancellationSignal = this.cancellationSignal;
        if (cancellationSignal != null) {
            cancellationSignal.cancel();
        }
    }

    @Override // android.hardware.fingerprint.FingerprintManager.AuthenticationCallback
    public void onAuthenticationError(int i, CharSequence charSequence) {
        if (i == 10) {
            this.listener.authenticationFailed("10");
        }
        FingerprintHelperListener fingerprintHelperListener = this.listener;
        fingerprintHelperListener.authenticationFailed("AuthenticationError : " + ((Object) charSequence));
    }

    @Override // android.hardware.fingerprint.FingerprintManager.AuthenticationCallback
    public void onAuthenticationHelp(int i, CharSequence charSequence) {
        FingerprintHelperListener fingerprintHelperListener = this.listener;
        fingerprintHelperListener.authenticationFailed("AuthenticationHelp : " + ((Object) charSequence));
    }

    @Override // android.hardware.fingerprint.FingerprintManager.AuthenticationCallback
    public void onAuthenticationSucceeded(FingerprintManager.AuthenticationResult authenticationResult) {
        this.listener.authenticationSuccess(authenticationResult);
    }

    @Override // android.hardware.fingerprint.FingerprintManager.AuthenticationCallback
    public void onAuthenticationFailed() {
        this.listener.authenticationFailed("Authentication Failed!");
    }
}
