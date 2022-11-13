package org.java_websocket.framing;

import org.java_websocket.enums.Opcode;
/* loaded from: classes2.dex */
public class ContinuousFrame extends DataFrame {
    public ContinuousFrame() {
        super(Opcode.CONTINUOUS);
    }
}
