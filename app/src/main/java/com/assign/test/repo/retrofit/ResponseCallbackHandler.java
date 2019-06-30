package com.assign.test.repo.retrofit;

import android.text.TextUtils;

import com.assign.test.baseview.BaseError;
import com.assign.test.baseview.BaseException;
import com.assign.test.baseview.BaseModel;
import com.assign.test.utils.ApiConstant;

import java.net.ConnectException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.HttpException;
import retrofit2.Response;

public   abstract class ResponseCallbackHandler<T> implements Callback<T> {

    private static boolean alreadyRefreshCalled = false;
    BaseModel<T> baseModel = new BaseModel<>();
    private String errorMessage;

    abstract protected void handleSuccess(T data);

    abstract protected void handleFailure(BaseModel error);

    abstract protected void tokenRefreshed();

    @Override
    public void onResponse(Call<T> call, Response<T> response) {
        if (response != null) {
            if (response.body() instanceof BaseModel && ((BaseModel) response.body()).getStatus() != null)
                if (((BaseModel) response.body()).getStatus().getCode() == ApiConstant.SUCCESS_CODE)
                    handleSuccess(response.body());

                else {
                    try {
                        if (((BaseModel) response.body()).getStatus().getDetails() != null) {
                            HashMap<String, ArrayList<String>> map = ((BaseModel) response.body()).getStatus().getDetails();
                            for (String key : map.keySet()) {
                                if (!TextUtils.isEmpty(errorMessage))
                                    errorMessage = errorMessage + "\n";
                                for (String message : map.get(key)) {
                                    if (TextUtils.isEmpty(errorMessage))
                                        errorMessage = message + "\n";
                                    else
                                        errorMessage = errorMessage + message + "\n";
                                }
                            }
                        }
                    } catch (Exception e) {
                    }
                    BaseError baseError = new BaseError();
                    baseError.setCode(((BaseModel) response.body()).getStatus().getCode());
                    baseError.setMessage(errorMessage);
                    baseError.setTitle(((BaseModel) response.body()).getStatus().getMessage());
                    baseModel.setBaseError(baseError);
                    baseModel.setFine(false);
                    handleFailure(baseModel);
                }
            else {
                baseModel.setUnknownFailure(true);
                handleFailure(baseModel);
            }

        } else {
            BaseError baseError = new BaseError();
            baseError.setCode(response.code());
            baseError.setMessage(response.message());
            baseModel.setBaseError(baseError);
            baseModel.setFine(false);
            handleFailure(baseModel);
        }
    }

    @Override
    public void onFailure(Call<T> call, Throwable t) {
        if (!call.isCanceled())
            if (t instanceof Exception) {
                Exception e = (Exception) t;
                BaseException baseException = new BaseException();
                if (e instanceof HttpException) {
                    baseException.setCode(((HttpException) e).code());
                    baseException.setException(e);
                    baseException.setMessage("API FAILED");
                } else if (e instanceof UnknownHostException) {
                    baseException.setCode(((UnknownHostException) e).hashCode());
                    baseException.setException(e);
                    baseException.setMessage("NO NETWORK");
                } else if (t instanceof ConnectException) {
                    baseException.setCode(((ConnectException) e).hashCode());
                    baseException.setException(e);
                    baseException.setMessage("NETWORK_INTERRUPT");
                } else {
                    baseException.setException(e);
                }
                baseModel.setBaseException(baseException);
                baseModel.setFine(false);
                handleFailure(baseModel);
            } else {
                baseModel.setUnknownFailure(true);
                handleFailure(baseModel);
            }
        else {
            baseModel.setCancelled(true);
            handleFailure(baseModel);
        }

    }




}