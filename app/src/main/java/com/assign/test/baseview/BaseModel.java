package com.assign.test.baseview;

import android.os.Parcel;
import android.os.Parcelable;

import com.assign.test.utils.ApiConstant;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BaseModel<T> implements Parcelable {

    public static final Creator<BaseModel> CREATOR = new Creator<BaseModel>() {
        @Override
        public BaseModel createFromParcel(Parcel in) {
            return new BaseModel(in);
        }

        @Override
        public BaseModel[] newArray(int size) {
            return new BaseModel[size];
        }
    };
    @SerializedName(ApiConstant.STATUS_KEY)
    @Expose(serialize = false, deserialize = true)
    private BaseStatus status;
    @SerializedName(ApiConstant.ARTICLES)
    @Expose
    private T articles;
    @SerializedName(ApiConstant.NO_OF_RESULT)
    @Expose
    private int noOfResult;

    private BaseError baseError;
    private BaseException baseException;
    private boolean isFine;
    private boolean unknownFailure;
    private boolean cancelled;


    public BaseModel() {
    }

    protected BaseModel(Parcel in) {
     /*   status = in.readParcelable(BaseStatus.class.getClassLoader());
        token = in.readString();
        next = in.readString();
        nextPage = in.readString();
        previous = in.readString();
        count = in.readInt();*/
        noOfResult = in.readInt();
        baseError = in.readParcelable(BaseError.class.getClassLoader());
        baseException = in.readParcelable(BaseException.class.getClassLoader());
        isFine = in.readByte() != 0;
        unknownFailure = in.readByte() != 0;
        cancelled = in.readByte() != 0;

    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(status, flags);

        dest.writeInt(noOfResult);

        dest.writeParcelable(baseError, flags);
        dest.writeParcelable(baseException, flags);
        dest.writeByte((byte) (isFine ? 1 : 0));
        dest.writeByte((byte) (unknownFailure ? 1 : 0));
        dest.writeByte((byte) (cancelled ? 1 : 0));

    }

    @Override
    public int describeContents() {
        return 0;
    }


    public boolean isUnknownFailure() {
        return unknownFailure;
    }

    public void setUnknownFailure(boolean unknownFailure) {
        this.unknownFailure = unknownFailure;
    }

    public boolean isFine() {
        return isFine;
    }

    public void setFine(boolean fine) {
        isFine = fine;
    }

    public BaseStatus getStatus() {
        return status;
    }

    public void setStatus(BaseStatus status) {
        this.status = status;
    }

    public BaseError getBaseError() {
        return baseError;
    }

    public void setBaseError(BaseError baseError) {
        this.baseError = baseError;
    }

    public BaseException getBaseException() {
        return baseException;
    }

    public void setBaseException(BaseException baseException) {
        this.baseException = baseException;
    }

    public T getArticles() {
        return articles;
    }

    public void setArticles(T data) {
        this.articles = articles;
    }


    public int getNoOfResult() {
        return noOfResult;
    }

    public void setNoOfResult(int noOfResult) {
        this.noOfResult = noOfResult;
    }


    public boolean isCancelled() {
        return cancelled;
    }

    public void setCancelled(boolean cancelled) {
        this.cancelled = cancelled;

    }
}
