package com.assign.test.baseview;

import android.os.Parcel;
import android.os.Parcelable;

public class BaseError implements Parcelable {

    public static final Creator<BaseError> CREATOR = new Creator<BaseError>() {
        @Override
        public BaseError createFromParcel(Parcel in) {
            return new BaseError(in);
        }

        @Override
        public BaseError[] newArray(int size) {
            return new BaseError[size];
        }
    };
    private String message;
    private String title;
    private int code;

    public BaseError() {
    }

    protected BaseError(Parcel in) {
        message = in.readString();
        title = in.readString();
        code = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(message);
        dest.writeString(title);
        dest.writeInt(code);
    }

    @Override
    public int describeContents() {
        return 0;
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
