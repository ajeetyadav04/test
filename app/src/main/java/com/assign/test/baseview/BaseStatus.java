package com.assign.test.baseview;

import android.os.Parcel;
import android.os.Parcelable;

import com.assign.test.utils.ApiConstant;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.HashMap;

public class BaseStatus implements Parcelable {

    public static final Creator<BaseStatus> CREATOR = new Creator<BaseStatus>() {
        @Override
        public BaseStatus createFromParcel(Parcel in) {
            return new BaseStatus(in);
        }

        @Override
        public BaseStatus[] newArray(int size) {
            return new BaseStatus[size];
        }
    };
    @SerializedName(ApiConstant.CODE_KEY)
    @Expose
    private int code;
    @SerializedName(ApiConstant.MESSAGE_KEY)
    @Expose
    private String message;
    @SerializedName(ApiConstant.DETAIL_KEY)
    private HashMap<String, ArrayList<String>> details;

    protected BaseStatus(Parcel in) {
        code = in.readInt();
        message = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(code);
        dest.writeString(message);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public HashMap<String, ArrayList<String>> getDetails() {
        return details;
    }

    public void setDetails(HashMap<String, ArrayList<String>> details) {
        this.details = details;
    }


    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}