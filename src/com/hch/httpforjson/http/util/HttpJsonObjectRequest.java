/**
 * @FILE:HttpJsonObjectRequest.java
 * @AUTHOR:hc
 * @DATE:2014-7-30 下午3:12:35
 **/
package com.hch.httpforjson.http.util;

import java.util.HashMap;
import java.util.Iterator;

import org.json.JSONException;
import org.json.JSONObject;

import com.hch.httpforjson.util.Constant;
import com.hch.httpforjson.util.CustomLog;
import com.loopj.android.http.RequestParams;

/*******************************************
 * @COMPANY:
 * @CLASS:HttpJsonObjectRequest
 * @DESCRIPTION:
 * @AUTHOR:hc
 * @VERSION:v1.0
 * @DATE:2014-7-30 下午3:12:35
 *******************************************/
public class HttpJsonObjectRequest extends HttpRequest {

	private HashMap<String, String> params;

	private HttpError error;

	private HttpSuccess<JSONObject> success;

	/**
	 * create a instance HttpRequest.
	 * 
	 * @param cls
	 * @param map
	 * @param httpSuccess
	 * @param httpError
	 */
	public HttpJsonObjectRequest(HashMap<String, String> map, HttpSuccess<JSONObject> httpSuccess, HttpError httpError) {

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

		CustomLog.d("提交参数为: %s", "=" + stringBuilder.toString());

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
	 * @description:失败后调用
	 * @author:hc
	 * @return:void
	 * @param arg3
	 */

	@Override
	public void onFailure(Throwable throwable) {
		if (error != null)
			error.onError(throwable);
	}

	/**
	 * @description:成功后调用
	 * @author:hc
	 * @return:void
	 * @param arg2
	 */

	@Override
	public void onSuccess(String result) {

		CustomLog.d("结果是：%s", result);
		try {
			JSONObject jsonObject = new JSONObject(result);
			if (success != null)
				success.onSuccess(jsonObject);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			if (error != null)
				error.onError(new Throwable());
		}
	}

}
