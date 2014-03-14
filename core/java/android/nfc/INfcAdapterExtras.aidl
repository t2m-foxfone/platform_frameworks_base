/*
 * Copyright (C) 2011 The Android Open Source Project
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

/******************************************************************************
 *
 *  The original Work has been changed by NXP Semiconductors.
 *
 *  Copyright (C) 2013-2014 NXP Semiconductors
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
 ******************************************************************************/

package android.nfc;

import android.os.Bundle;


/**
 * {@hide}
 */
interface INfcAdapterExtras {
    Bundle open(in String pkg, IBinder b);
    Bundle close(in String pkg, IBinder b);
    Bundle transceive(in String pkg, in byte[] data_in);
    int getCardEmulationRoute(in String pkg);
    void setCardEmulationRoute(in String pkg, int route);
    void authenticate(in String pkg, in byte[] token);
    String getDriverName(in String pkg);
    int[] getSecureElementTechList(in String pkg);
    byte[] getSecureElementUid(in String pkg);
    boolean reset(in String pkg);
    Bundle getAtr(in String pkg);
}
