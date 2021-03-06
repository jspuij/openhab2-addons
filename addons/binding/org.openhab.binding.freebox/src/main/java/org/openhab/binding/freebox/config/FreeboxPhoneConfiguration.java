/**
 * Copyright (c) 2014-2016 by the respective copyright holders.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.openhab.binding.freebox.config;

/**
 * The {@link FreeboxPhoneConfiguration} is responsible for holding
 * configuration informations associated to a Freebox Phone thing type
 *
 * @author Laurent Garnier
 */
public class FreeboxPhoneConfiguration {

    public static final String REFRESH_PHONE_INTERVAL = "refreshPhoneInterval";
    public static final String REFRESH_PHONE_CALLS_INTERVAL = "refreshPhoneCallsInterval";

    public Integer refreshPhoneInterval;
    public Integer refreshPhoneCallsInterval;

}
