package com.abdurrahmanjun.core.util

import com.abdurrahmanjun.core.event.StateEvent
import com.abdurrahmanjun.core.remote.BaseResponse
import com.abdurrahmanjun.core.remote.TrolliResponse
import com.google.gson.FieldNamingPolicy
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import retrofit2.HttpException

fun <T: Any, U: Any> TrolliResponse<T>.mapObservable(mapper: (T) -> U): Observable<U> {
    return flatMap { response ->
        if (response.isSuccessful) {
            val body = response.body()
            val data = body?.data
            if (data != null) {
                val dataMapper = mapper.invoke(data)
                Observable.just(dataMapper)
            } else {
                val exception = Throwable("Response data is null, message: ${body?.message}")
                Observable.error(exception)
            }
        } else {
            val bodyError = response.errorBody()?.string()
            val gson = GsonBuilder()
                .setPrettyPrinting()
                .setLenient()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .create()

            val typeToken = object : TypeToken<BaseResponse<Any>>() {}.type
            val bodyErrorData = gson.fromJson<BaseResponse<Any>>(bodyError, typeToken)
            val messageResponse = bodyErrorData.message
            val messageHttp = response.message()
            val message = "$messageHttp, message: $messageResponse"
            val exception = HttpException(response)
            Observable.error(exception)
        }
    }
}

fun <T: Any> Observable<T>.fetchStateEventSubscriber(onSubscribe: (StateEvent<T>) -> Unit) : Disposable {
    return subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .doOnSubscribe {
            val eventLoading = StateEvent.Loading<T>()
            onSubscribe.invoke(eventLoading)
        }
        .subscribe({ data ->
            val eventSuccess = StateEvent.Success<T>(data)
            onSubscribe.invoke(eventSuccess)
        }, { throwable ->
            val code = if (throwable is HttpException) {
                throwable.code()
            } else {
                499
            }
            val eventFailure = StateEvent.Failure<T>(code, throwable)
            onSubscribe.invoke(eventFailure)
        })
}
