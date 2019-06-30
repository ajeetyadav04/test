package com.assign.test.baseview;

import android.os.Parcel;
import android.os.Parcelable;

public class BaseException implements Parcelable {

    public static final Creator<BaseException> CREATOR = new Creator<BaseException>() {
        @Override
        public BaseException createFromParcel(Parcel in) {
            return new BaseException(in);
        }

        @Override
        public BaseException[] newArray(int size) {
            return new BaseException[size];
        }
    };
    private Exception exception;
    private String message;
    private int code;

    public BaseException() {
    }

    protected BaseException(Parcel in) {
        message = in.readString();
        code = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(message);
        dest.writeInt(code);
    }

    @Override
    public int describeContents() {
        return 0;
    }


    public Exception getException() {
        return exception;
    }

    public void setException(Exception exception) {
        this.exception = exception;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
