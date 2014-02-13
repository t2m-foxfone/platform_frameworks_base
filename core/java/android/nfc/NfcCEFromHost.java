/*
 * Copyright (C) 2010 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
/*
 *
 *  The original Work has been changed by NXP Semiconductors.
 *
 *  Copyright (C) 2013 NXP Semiconductors
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *
 */

package android.nfc;

import android.nfc.tech.TagTechnology;
import android.os.RemoteException;
import android.util.Log;

import java.io.IOException;

/**
 * This class provides the primary API for managing all host Card Emulation aspects.
 */
public final class NfcCEFromHost {

    private static final String TAG = "NfcCEFromHost";
    private INfcCEFromHost mService;

    /**
     * @hide
     */
    public NfcCEFromHost(INfcCEFromHost mCEFromHostService) {
        mService = mCEFromHostService;
    }

    /**
     * Allows to enable the card emulation from host for Type A card.
     * Note: To use this API, the other card emulation(s) must be disabled by calling the Android API
     * public void deselectedSecureElement ().
     * <p>Requires {@link android.Manifest.permission#NFC} permission.
     *
     * @param pkg the package name of the calling application
     * @param sak the select acknowledgement
     * @param atqa the Answer To Request of Type A
     * @param app_data to be added.
     * @return True in case of success, otherwise False.
     */
    public boolean setCEFromHostTypeA(String pkg, byte sak, byte[] atqa, byte[] app_data) throws IOException {
        try {
            boolean status = mService.setCEFromHostTypeA(pkg, sak, atqa, app_data);
            // Handle potential errors
            if (status) {
                return status;
            } else {
                throw new IOException("Unable to Enable Host Card Emulation");
            }
         } catch (RemoteException e) {
                Log.e(TAG, "RemoteException in setCEFromHostTypeA(): ", e);
                throw new IOException("RemoteException in setCEFromHostTypeA()");
         }
    }

    /**
     * Allows to enable the card emulation from host for Type B card.
     * Note: To use this API, the other card emulation(s) must be disabled by calling the Android API
     * public void deselectedSecureElement ().
     * <p>Requires {@link android.Manifest.permission#NFC} permission.
     *
     * @param pkg the package name of the calling application
     * @param atqb the Answer To Request of Type B
     * @param hi_layer_resp to be added.
     * @param afi the Application Family Identifier. Card pre-selection criteria by application
     * @return True in case of success, otherwise False.
     */
    public boolean setCEFromHostTypeB(String pkg, byte[] atqb, byte[] hi_layer_resp, int afi) throws IOException {
        try {
            boolean status = mService.setCEFromHostTypeB(pkg, atqb, hi_layer_resp, afi);
            // Handle potential errors
            if (status) {
                return status;
            } else {
                throw new IOException("Unable to Enable Host Card Emulation");
            }
         } catch (RemoteException e) {
                Log.e(TAG, "RemoteException in setCEFromHostTypeB(): ", e);
                throw new IOException("RemoteException in setCEFromHostTypeB()");
         }
    }


    /**
     * Allows disabling the card emulation from host for Type A and Type B card.
     * <p>Requires {@link android.Manifest.permission#NFC} permission.
     *
     * @param pkg the package name of the calling application
     */
    public void resetCEFromHostType(String pkg) throws IOException {
        try {
            mService.resetCEFromHostType(pkg);
        } catch (RemoteException e) {
            Log.e(TAG, "RemoteException in resetCEFromHostType(): ", e);
            throw new IOException("RemoteException in resetCEFromHostType()");
        }
    }


    /**
     * Allows receiving the bytes from Card emulated form host for Type A and Type B card.
     * <p>Requires {@link android.Manifest.permission#NFC} permission.
     *
     * @param pkg the package name of the calling application
     * @return the bytes received form currently active card.
     */
    public byte[] receiveCEFromHost(String pkg) throws IOException {
        // Perform Receive
        try {
            byte[] response = mService.receiveCEFromHost(pkg);
            // Handle potential errors
            if (response == null) {
                throw new IOException("Receive APDU failed");
            }
            return response;
        } catch (RemoteException e) {
            Log.e(TAG, "RemoteException in receiveCEFromHost(): ", e);
            throw new IOException("RemoteException in receiveCEFromHost()");
        }
    }

    /**
     * Allows sending the bytes from Card emulated form host for Type A and Type B card.
     * <p>Requires {@link android.Manifest.permission#NFC} permission.
     *
     * @param pkg the package name of the calling application
     * @param data the data to be sent to the card.
     * @return True in case of success, otherwise False.
     */
    public boolean sendCEFromHost(String pkg, byte [] data) throws IOException {
        // Perform Send
        try {
            boolean response = mService.sendCEFromHost(pkg, data);
            // Handle potential errors
            if (response != true) {
                throw new IOException("Send APDU failed");
            }
            return response;
        } catch (RemoteException e) {
            Log.e(TAG, "RemoteException in sendCEFromHost(): ", e);
            throw new IOException("RemoteException in sendCEFromHost()");
        }
    }

}
