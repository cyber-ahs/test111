package com.androidnetworking.common;

import com.androidnetworking.error.ANError;
import okhttp3.Response;
/* loaded from: classes.dex */
public class ANResponse<T> {
    private final ANError mANError;
    private final T mResult;
    private Response response;

    public static <T> ANResponse<T> success(T t) {
        return new ANResponse<>(t);
    }

    public static <T> ANResponse<T> failed(ANError aNError) {
        return new ANResponse<>(aNError);
    }

    public ANResponse(T t) {
        this.mResult = t;
        this.mANError = null;
    }

    public ANResponse(ANError aNError) {
        this.mResult = null;
        this.mANError = aNError;
    }

    public T getResult() {
        return this.mResult;
    }

    public boolean isSuccess() {
        return this.mANError == null;
    }

    public ANError getError() {
        return this.mANError;
    }

    public void setOkHttpResponse(Response response) {
        this.response = response;
    }

    public Response getOkHttpResponse() {
        return this.response;
    }
}
