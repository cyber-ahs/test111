package munirkhanani.helpers;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.ColorFilter;
import android.graphics.PorterDuff;
import android.net.Uri;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.core.content.ContextCompat;
import com.jakewharton.picasso.OkHttp3Downloader;
import com.microlinks.Munirkhanani.R;
import com.mikhaellopez.circularimageview.CircularImageView;
import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.Picasso;
import java.io.File;
import java.io.IOException;
import java.util.Random;
import munirkhanani.util.utils;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Response;
/* loaded from: classes2.dex */
public class ItemNav extends LinearLayout {
    private String apiKey;
    private String authorization;
    private CircularImageView circularImageView;
    private int colorActive;
    private int colorInactive;
    private int imageIcon;
    private int imageIconActive;
    private ImageView imageView;
    private Boolean isActive;
    private Boolean isError;
    private Boolean isProfile;
    private String pathImageProfile;
    private int profileActive;
    private int profileInactive;
    private RelativeLayout relativeLayout;
    private int textSizeForDimension;
    private TextView textView;
    private String titulo;
    private int valueSize;

    public ItemNav(Context context, int i, String str) {
        super(context);
        this.isError = false;
        this.isProfile = false;
        init(0, 0, i, str, 0);
    }

    public ItemNav(Context context, int i, int i2, int i3, int i4, String str) {
        super(context);
        this.isError = false;
        this.isProfile = false;
        init(i, i2, i3, str, i4);
    }

    public ItemNav(Context context, int i, int i2) {
        super(context);
        this.isError = false;
        this.isProfile = false;
        init(0, 0, i, "", i2);
    }

    public ItemNav(Context context, int i) {
        super(context);
        this.isError = false;
        this.isProfile = false;
        init(0, 0, i, "", 0);
    }

    private void init(int i, int i2, int i3, String str, int i4) {
        this.imageIcon = i3;
        this.titulo = str;
        this.imageIconActive = i4;
        utils.VALUE_SIZE = i;
        this.textSizeForDimension = i2;
        addComponent();
    }

    private void addComponent() {
        configureRootLayout();
        configureImageView();
        configureTextView();
    }

    private void configureRootLayout() {
        setOrientation(1);
        setLayoutParams(new LinearLayout.LayoutParams(-1, -1, 1.0f));
        setGravity(17);
    }

    private void configureTextView() {
        String str = this.titulo;
        if (str == null || str.trim().equals("")) {
            return;
        }
        TextView textView = new TextView(getContext());
        this.textView = textView;
        textView.setGravity(17);
        this.textView.setTextSize(this.textSizeForDimension);
        this.textView.setTextColor(getContext().getResources().getColor(R.color.colorPrimary));
        this.textView.setLayoutParams(new LinearLayout.LayoutParams(-1, -2));
        this.textView.setText(this.titulo);
        addView(this.textView);
    }

    private void configureImageView() {
        this.relativeLayout = new RelativeLayout(getContext());
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams.addRule(14);
        layoutParams.addRule(15);
        this.relativeLayout.setLayoutParams(layoutParams);
        RelativeLayout.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(utils.convertDpToPixel(utils.VALUE_SIZE, getContext()), utils.convertDpToPixel(utils.VALUE_SIZE, getContext()));
        ImageView imageView = new ImageView(getContext());
        this.imageView = imageView;
        imageView.setId(new Random().nextInt(100));
        this.imageView.setPadding(5, 5, 5, 5);
        this.imageView.setLayoutParams(layoutParams2);
        CircularImageView circularImageView = new CircularImageView(getContext());
        this.circularImageView = circularImageView;
        circularImageView.setId(new Random().nextInt(100));
        this.circularImageView.setPadding(5, 5, 5, 5);
        this.circularImageView.setLayoutParams(layoutParams2);
        this.circularImageView.setBorderWidth(3.0f);
        this.circularImageView.setVisibility(8);
        setIconInImageView(this.imageIcon);
        if (this.colorInactive != 0) {
            this.imageView.setColorFilter(ContextCompat.getColor(getContext(), this.colorInactive), PorterDuff.Mode.MULTIPLY);
            this.circularImageView.setBorderColor(ContextCompat.getColor(getContext(), this.colorInactive));
        }
        this.relativeLayout.addView(this.imageView);
        this.relativeLayout.addView(this.circularImageView);
        addView(this.relativeLayout);
    }

    private void fileToImageView() {
        String str;
        if (!this.isProfile.booleanValue() || (str = this.pathImageProfile) == null || str.trim().equals("")) {
            return;
        }
        updatePathImageProfile(this.pathImageProfile, this.apiKey, this.authorization);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setIconInImageView(int i) {
        this.imageView.setVisibility(0);
        this.imageView.setImageDrawable(ContextCompat.getDrawable(getContext(), i));
        this.circularImageView.setVisibility(8);
    }

    public ItemNav addBadgeIndicator(BadgeIndicator badgeIndicator) {
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams.addRule(7, this.imageView.getId());
        layoutParams.addRule(6, this.imageView.getId());
        RelativeLayout relativeLayout = new RelativeLayout(getContext());
        relativeLayout.setLayoutParams(layoutParams);
        relativeLayout.addView(badgeIndicator);
        this.relativeLayout.addView(relativeLayout);
        return this;
    }

    public void updatePathImageProfile(String str, final String str2, final String str3) {
        this.pathImageProfile = str;
        this.apiKey = str2;
        this.authorization = str3;
        if (str != null && !str.equals("")) {
            if (new File(str).exists()) {
                Bitmap decodeFile = BitmapFactory.decodeFile(str);
                if (decodeFile != null) {
                    if (!this.isActive.booleanValue() && this.colorInactive != 0) {
                        deselect();
                    }
                    this.imageView.setVisibility(8);
                    this.circularImageView.setVisibility(0);
                    this.circularImageView.setImageBitmap(decodeFile);
                    return;
                }
                return;
            } else if (str.contains("https") || str.contains("http") || str.contains("www.") || str.contains(".jpg") || str.contains(".png")) {
                this.imageView.setVisibility(8);
                this.circularImageView.setVisibility(0);
                if (!this.isActive.booleanValue() && this.colorInactive != 0) {
                    deselect();
                }
                new Picasso.Builder(getContext()).downloader(new OkHttp3Downloader(new OkHttpClient.Builder().addInterceptor(new Interceptor() { // from class: munirkhanani.helpers.ItemNav.1
                    @Override // okhttp3.Interceptor
                    public Response intercept(Interceptor.Chain chain) throws IOException {
                        return chain.proceed(chain.request().newBuilder().addHeader("api-key", str2).addHeader("Authorization", str3).addHeader("Content-Type", "application/json").build());
                    }
                }).build())).listener(new Picasso.Listener() { // from class: munirkhanani.helpers.ItemNav.2
                    @Override // com.squareup.picasso.Picasso.Listener
                    public void onImageLoadFailed(Picasso picasso, Uri uri, Exception exc) {
                        ItemNav.this.isError = true;
                        ItemNav.this.imageView.setVisibility(0);
                        ItemNav.this.circularImageView.setVisibility(8);
                        if (!ItemNav.this.isActive.booleanValue() && ItemNav.this.colorInactive != 0) {
                            ItemNav.this.deselect();
                        }
                        ItemNav itemNav = ItemNav.this;
                        itemNav.setIconInImageView(itemNav.imageIcon);
                    }
                }).build().load(str).placeholder(ContextCompat.getDrawable(getContext(), this.imageIcon)).error(ContextCompat.getDrawable(getContext(), this.imageIcon)).memoryPolicy(MemoryPolicy.NO_CACHE, new MemoryPolicy[0]).into(this.circularImageView);
                return;
            } else {
                setIconInImageView(this.imageIcon);
                return;
            }
        }
        setIconInImageView(this.imageIcon);
    }

    public ItemNav addColorInative(int i) {
        this.colorInactive = i;
        return this;
    }

    public ItemNav addColorAtive(int i) {
        this.colorActive = i;
        return this;
    }

    public ItemNav addProfileColorInative(int i) {
        this.profileInactive = i;
        return this;
    }

    public ItemNav addProfileColorAtive(int i) {
        this.profileActive = i;
        return this;
    }

    public boolean isProfile() {
        return this.isProfile.booleanValue();
    }

    public ItemNav isProfileItem() {
        this.isProfile = true;
        return this;
    }

    public ItemNav setPathImageProfile(String str) {
        this.isProfile = true;
        this.pathImageProfile = str;
        fileToImageView();
        return this;
    }

    public void deselect() {
        this.isActive = false;
        if (isProfile() && !this.isError.booleanValue()) {
            selectInactiveColorProfile();
        } else {
            if (this.imageIconActive != 0) {
                setIconInImageView(this.imageIcon);
            }
            if (this.colorInactive != 0) {
                this.imageView.setColorFilter(ContextCompat.getColor(getContext(), this.colorInactive), PorterDuff.Mode.SRC_IN);
                this.circularImageView.setBorderColor(ContextCompat.getColor(getContext(), this.colorInactive));
                String str = this.titulo;
                if (str != null && !str.equals("")) {
                    this.textView.setTextColor(ContextCompat.getColor(getContext(), this.colorInactive));
                }
            } else {
                this.imageView.setColorFilter((ColorFilter) null);
                this.circularImageView.setBorderColor(0);
                String str2 = this.titulo;
                if (str2 != null && !str2.equals("")) {
                    this.textView.setTextColor(ContextCompat.getColor(getContext(), R.color.colorPrimary));
                }
            }
        }
        post(new Runnable() { // from class: munirkhanani.helpers.ItemNav.3
            @Override // java.lang.Runnable
            public void run() {
                RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(utils.convertDpToPixel(utils.VALUE_SIZE, ItemNav.this.getContext()), utils.convertDpToPixel(utils.VALUE_SIZE, ItemNav.this.getContext()));
                ItemNav.this.imageView.setLayoutParams(layoutParams);
                ItemNav.this.circularImageView.setLayoutParams(layoutParams);
            }
        });
    }

    public void select() {
        String str;
        this.isActive = true;
        if (isProfile() && (str = this.pathImageProfile) != null && !str.trim().equals("") && !this.isError.booleanValue()) {
            selectActiveColorProfile();
        } else {
            int i = this.imageIconActive;
            if (i != 0) {
                setIconInImageView(i);
            } else {
                selectColor();
            }
        }
        doBounceAnimation();
    }

    public void selectInactiveColorProfile() {
        if (this.profileInactive != 0) {
            this.circularImageView.setBorderColor(ContextCompat.getColor(getContext(), this.profileInactive));
        }
    }

    public void selectActiveColorProfile() {
        if (this.profileActive != 0) {
            this.circularImageView.setBorderColor(ContextCompat.getColor(getContext(), this.profileActive));
        }
    }

    public void selectColor() {
        if (this.colorActive != 0) {
            this.imageView.setColorFilter(ContextCompat.getColor(getContext(), this.colorActive), PorterDuff.Mode.SRC_IN);
            String str = this.titulo;
            if (str == null || str.equals("")) {
                return;
            }
            this.textView.setTextColor(ContextCompat.getColor(getContext(), this.colorActive));
        }
    }

    private void doBounceAnimation() {
        post(new Runnable() { // from class: munirkhanani.helpers.ItemNav.4
            @Override // java.lang.Runnable
            public void run() {
                RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(utils.convertDpToPixel(utils.VALUE_SIZE_ACTIVE, ItemNav.this.getContext()), utils.convertDpToPixel(utils.VALUE_SIZE_ACTIVE, ItemNav.this.getContext()));
                ItemNav.this.imageView.setLayoutParams(layoutParams);
                ItemNav.this.circularImageView.setLayoutParams(layoutParams);
            }
        });
    }
}
