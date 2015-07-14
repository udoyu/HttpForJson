package com.hch.httpforjson;

import org.json.JSONException;
import org.json.JSONObject;

import com.hch.httpforjson.api.JsonTextData;
import com.hch.httpforjson.http.util.HttpError;
import com.hch.httpforjson.http.util.HttpRequestQueque;
import com.hch.httpforjson.http.util.HttpSuccess;
import com.hch.httpforjson.vi.JsonObj;

import android.support.v4.app.Fragment;
import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

public class MainActivity extends Activity {
	
	private HttpRequestQueque requestQueque; 
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
//		setContentView(R.layout.activity_main);

		requestQueque = new HttpRequestQueque(MainActivity.this);
		
		requestQueque.add(JsonTextData.getTestObj("1", new HttpSuccess<JsonObj>() {
			
			@Override
			public void onSuccess(JsonObj jsonObj) {
				
				// TODO Auto-generated method stub
				//
				String obj = jsonObj.getSystem_id();
				
			}
		}, new HttpError() {
			
			@Override
			public void onError(Throwable arg0) {
				
				// TODO Auto-generated method stub
				Toast.makeText(MainActivity.this, "失败了！！！！！", 1).show();
				
			}
		}));
		
		requestQueque.add(JsonTextData.isSuccess("", new HttpSuccess<JSONObject>() {
			
			@Override
			public void onSuccess(JSONObject result) {
				
				// TODO Auto-generated method stub
				try {
					if(result.getInt("return") == 1){
						
					}else{
						
					}
				} catch (JSONException e) {
					
					// TODO Auto-generated catch block
					e.printStackTrace();
					
				}
				
			}
		}, new HttpError() {
			
			@Override
			public void onError(Throwable arg0) {
				
				// TODO Auto-generated method stub
				
			}
		}));
	}

}
