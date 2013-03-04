/**
 * This file was auto-generated by the Titanium Module SDK helper for Android
 * Appcelerator Titanium Mobile
 * Copyright (c) 2009-2010 by Appcelerator, Inc. All Rights Reserved.
 * Licensed under the terms of the Apache Public License
 * Please see the LICENSE included with this distribution for details.
 *
 */
package analytics.google;

import android.app.Activity;

import com.google.analytics.tracking.android.GAServiceManager;
import com.google.analytics.tracking.android.GoogleAnalytics;
import com.google.analytics.tracking.android.Tracker;

import org.appcelerator.kroll.KrollDict;
import org.appcelerator.kroll.KrollModule;
import org.appcelerator.kroll.annotations.Kroll;
import org.appcelerator.kroll.common.Log;
import org.appcelerator.kroll.common.TiConfig;

import org.appcelerator.titanium.TiApplication;
import org.appcelerator.titanium.util.TiConvert;

import java.util.HashMap;

@Kroll.module(name="GoogleAnalytics", id="analytics.google")
public class GoogleAnalyticsModule extends KrollModule
{

	// Standard Debugging variables
	private static final String LCAT = "GoogleAnalyticsModule";
	private static final boolean DBG = TiConfig.LOGD;

	private GoogleAnalytics mInstance;

	// You can define constants with @Kroll.constant, for example:
	// @Kroll.constant public static final String EXTERNAL_NAME = value;

	public GoogleAnalyticsModule()
	{
		super();

		TiApplication appContext = TiApplication.getInstance();
		Activity activity = appContext.getCurrentActivity();
		mInstance = GoogleAnalytics.getInstance(activity);
	}

	@Kroll.onAppCreate
	public static void onAppCreate(TiApplication app)
	{

	}

	// Methods
	@Kroll.method
	public TrackerProxy getTracker(String trackingID)
	{
		return new TrackerProxy(mInstance.getTracker(trackingID));
	}

	@Kroll.getProperty
	public TrackerProxy getDefaultTracker()
	{
		return new TrackerProxy(mInstance.getDefaultTracker());
	}

	@Kroll.setProperty
	public void setDispatchInterval(int interval)
	{
		GAServiceManager.getInstance().setDispatchPeriod(interval);
	}

	@Kroll.setProperty
	public void setOptOut(boolean optOut)
	{
		mInstance.setAppOptOut(optOut);
	}

	@Kroll.setProperty
	public void setDebug(boolean debug)
	{
		mInstance.setDebug(debug);
	}

	@Kroll.method
	public TransactionProxy makeTransaction(HashMap props)
	{
		KrollDict propsDict = new KrollDict(props);
		float orderTotal = TiConvert.toFloat(propsDict, "revenue");
		float shippingCost = TiConvert.toFloat(propsDict, "shipping");
		float tax = TiConvert.toFloat(propsDict, "tax");
		String transactionId = TiConvert.toString(propsDict, "id");
		String affiliation = TiConvert.toString(propsDict, "affiliation");

		return new TransactionProxy(transactionId, orderTotal, tax, shippingCost, affiliation);
	}

}

