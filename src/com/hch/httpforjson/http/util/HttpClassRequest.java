package com.hch.httpforjson.http.util;

import java.util.HashMap;
import java.util.Iterator;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.hch.httpforjson.util.Constant;
import com.hch.httpforjson.util.CustomLog;
import com.loopj.android.http.RequestParams;

public class HttpClassRequest<T> extends HttpRequest {

	private HashMap<String, String> params;
	private HttpError error;

	private HttpSuccess<T> success;

	private Class<T> cls;

	/**
	 * create a instance HttpRequest.
	 * 
	 * @param cls
	 * @param map
	 * @param httpSuccess
	 * @param httpError
	 */
	public HttpClassRequest(Class<T> cls, HashMap<String, String> map, HttpSuccess<T> httpSuccess, HttpError httpError) {
		this.cls = cls;
		this.params = map;
		this.success = httpSuccess;
		this.error = httpError;

	}

	/**
	 * @description: 获取参数
	 * @author:hc
	 * @return:RequestParams
	 * @return
	 */

	@Override
	public RequestParams getParams() {
		// TODO Auto-generated method stub
		final RequestParams requestParams = new RequestParams();

		StringBuilder stringBuilder = new StringBuilder();
		Iterator<String> iterator = params.keySet().iterator();

		while (iterator.hasNext()) {

			String key = iterator.next().toString();

			requestParams.put(key, params.get(key));

			String val = params.get(key);
			stringBuilder.append("&" + key + "=" + val);

		}

		CustomLog.d("提交参数为   %s", "=" + stringBuilder.toString());

		return requestParams;
	}

	/**
	 * @description:
	 * @author:hc
	 * @return:String
	 * @return
	 */

	@Override
	public String getUrlString() {
		return Constant.url;
	}

	/**
	 * @description:
	 * @author:hc
	 * @return:void
	 * @param arg3
	 */

	@Override
	public void onFailure(Throwable arg3) {
		if (error != null)
			error.onError(arg3);
	}

	/**
	 * @description:
	 * @author:hc
	 * @return:void
	 * @param arg2
	 */

	@Override
	public void onSuccess(String arg2) {

		Gson gson = new Gson();

		CustomLog.d("结果是=%s", arg2);

		try {
			if (success != null)
				success.onSuccess(gson.fromJson(arg2, cls));
		} catch (JsonSyntaxException e) {

			if (error != null)
				error.onError(e);
		}

	}
}
