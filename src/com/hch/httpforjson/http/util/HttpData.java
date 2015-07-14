package com.hch.httpforjson.http.util;

import java.util.HashMap;

import org.json.JSONObject;

public class HttpData<T> {

	/**
	 * @param cls
	 * @param param
	 * @param httpSuccess
	 * @param httpError
	 * @return
	 */
	public HttpClassRequest<T> newHttpRequest(Class<T> cls, HashMap<String, String> param, HttpSuccess<T> httpSuccess, HttpError httpError) {

		// 如果这里请求需要添加公共参数在此添加
		// param.put(key, value)

		return new HttpClassRequest<T>(cls, param, httpSuccess, httpError);

	}

	/**
	 * @description: 执行一个jsonobject http请求
	 * @author:hc
	 * @return:HttpJsonObjectRequest
	 * @param param
	 * @param httpSuccess
	 * @param httpError
	 * @return
	 */

	public HttpJsonObjectRequest newHttpRequest(HashMap<String, String> param, HttpSuccess<JSONObject> httpSuccess, HttpError httpError) {

		// 如果这里请求需要添加公共参数在此添加
		// param.put(key, value)

		return new HttpJsonObjectRequest(param, httpSuccess, httpError);
	}

	/**
	 * @description: 上传文件 用
	 * @author:hc
	 * @return:HttpFileRequest
	 * @param param
	 * @param httpSuccess
	 * @param httpError
	 * @return
	 */

	public HttpFileRequest newFileHttpRequest(HashMap<String, Object> param, HttpSuccess<JSONObject> httpSuccess, HttpError httpError) {

		// 如果这里请求需要添加公共参数在此添加
		// param.put(key, value)

		return new HttpFileRequest(param, httpSuccess, httpError);
	}

	/**
	 * @description: 上传文件 用
	 * @author:hc
	 * @return:HttpFileClassRequest<T>
	 * @param cls
	 * @param param
	 * @param httpSuccess
	 * @param httpError
	 * @return
	 */

	public HttpFileClassRequest<T> newFileClassHttpRequest(Class<T> cls, HashMap<String, Object> param, HttpSuccess<T> httpSuccess, HttpError httpError) {

		// 如果这里请求需要添加公共参数在此添加
		// param.put(key, value)

		return new HttpFileClassRequest<T>(cls, param, httpSuccess, httpError);
	}

}
