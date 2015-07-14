package com.hch.httpforjson.util;

import java.util.Locale;

import android.util.Log;

public class CustomLog {

	public static void i(String tag, String msg) {
		if (DefineClass.debug)
			Log.i(tag, msg);
	}

	public static void v(String format, Object... args) {
		if (DefineClass.debug) {
			Log.v(DefineClass.TAG, buildMessage(format, args));
		}
	}

	public static void d(String format, Object... args) {
		if (DefineClass.debug) {
			Log.d(DefineClass.TAG, buildMessage(format, args));
		}
	}

	public static void e(String format, Object... args) {
		if (DefineClass.debug) {
			Log.e(DefineClass.TAG, buildMessage(format, args));
		}
	}

	/**
	 * Formats the caller's provided message and prepends useful info like
	 * calling thread ID and method name.
	 */
	private static String buildMessage(String format, Object... args) {
		String msg = (args == null) ? format : String.format(Locale.US, format, args);
		StackTraceElement[] trace = new Throwable().fillInStackTrace().getStackTrace();

		String caller = "<unknown>";
		// Walk up the stack looking for the first caller outside of VolleyLog.
		// It will be at least two frames up, so start there.
		for (int i = 2; i < trace.length; i++) {
			Class<?> clazz = trace[i].getClass();
			if (!clazz.equals(CustomLog.class)) {
				String callingClass = trace[i].getClassName();
				callingClass = callingClass.substring(callingClass.lastIndexOf('.') + 1);
				callingClass = callingClass.substring(callingClass.lastIndexOf('$') + 1);

				caller = callingClass + "." + trace[i].getMethodName();
				break;
			}
		}
		return String.format(Locale.US, "[%d] %s: %s", Thread.currentThread().getId(), caller, msg);
	}
}
