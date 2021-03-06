/*
 * Copyright 2013 Luluvise Ltd
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.github.luluvise.droid_utils.http;

import javax.annotation.concurrent.Immutable;

import com.google.api.client.http.HttpBackOffUnsuccessfulResponseHandler;
import com.google.api.client.http.HttpIOExceptionHandler;
import com.google.api.client.http.HttpUnsuccessfulResponseHandler;
import com.google.api.client.util.ExponentialBackOff;
import com.google.common.annotations.Beta;

/**
 * Static helper class containing factory methods to get instances of the
 * default {@link HttpUnsuccessfulResponseHandler} and
 * {@link HttpIOExceptionHandler}.
 * 
 * @since 1.0
 * @author Marco Salis
 */
@Beta
@Immutable
public class DefaultResponseHandlerFactory {

	private DefaultResponseHandlerFactory() {
		// no instantiation needed
	}

	/**
	 * Returns a new instance of the default
	 * {@link HttpUnsuccessfulResponseHandler}
	 */
	public static final HttpUnsuccessfulResponseHandler createHttpUnsuccessfulResponseHandler() {
		return new DefaultBackOffUnsuccessfulResponseHandler(
				NetworkConstants.DEFAULT_BACKOFF_REQUIRED, NetworkConstants.DEFAULT_BACKOFF);
	}

	/**
	 * Returns a new instance of a {@link HttpUnsuccessfulResponseHandler} using
	 * {@link ExponentialBackOff} as back off policy.
	 * 
	 * Do not reuse the created handler in more than a request as its state
	 * wouldn't be reset.
	 */
	public static final HttpUnsuccessfulResponseHandler createExponentialBackOffResponseHandler() {
		return new HttpBackOffUnsuccessfulResponseHandler(new ExponentialBackOff());
	}

	/**
	 * Returns a new instance of the default {@link HttpIOExceptionHandler}
	 */
	public static final HttpIOExceptionHandler createHttpIOExceptionHandler() {
		return new DefaultHttpIOExceptionHandler();
	}

}