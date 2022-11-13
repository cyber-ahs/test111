package org.java_websocket;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.ByteChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.SocketChannel;
import java.util.concurrent.ExecutorService;
import javax.net.ssl.SSLEngine;
import javax.net.ssl.SSLEngineResult;
import javax.net.ssl.SSLException;
import org.java_websocket.interfaces.ISSLChannel;
import org.java_websocket.util.ByteBufferUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/* loaded from: classes2.dex */
public class SSLSocketChannel implements WrappedByteChannel, ByteChannel, ISSLChannel {
    private final SSLEngine engine;
    private ExecutorService executor;
    private final Logger log = LoggerFactory.getLogger(SSLSocketChannel.class);
    private ByteBuffer myAppData;
    private ByteBuffer myNetData;
    private ByteBuffer peerAppData;
    private ByteBuffer peerNetData;
    private final SocketChannel socketChannel;

    @Override // org.java_websocket.WrappedByteChannel
    public boolean isNeedWrite() {
        return false;
    }

    @Override // org.java_websocket.WrappedByteChannel
    public void writeMore() throws IOException {
    }

    public SSLSocketChannel(SocketChannel socketChannel, SSLEngine sSLEngine, ExecutorService executorService, SelectionKey selectionKey) throws IOException {
        if (socketChannel == null || sSLEngine == null || this.executor == executorService) {
            throw new IllegalArgumentException("parameter must not be null");
        }
        this.socketChannel = socketChannel;
        this.engine = sSLEngine;
        this.executor = executorService;
        this.myNetData = ByteBuffer.allocate(sSLEngine.getSession().getPacketBufferSize());
        this.peerNetData = ByteBuffer.allocate(sSLEngine.getSession().getPacketBufferSize());
        sSLEngine.beginHandshake();
        if (doHandshake()) {
            if (selectionKey != null) {
                selectionKey.interestOps(selectionKey.interestOps() | 4);
                return;
            }
            return;
        }
        try {
            socketChannel.close();
        } catch (IOException e) {
            this.log.error("Exception during the closing of the channel", (Throwable) e);
        }
    }

    @Override // java.nio.channels.ReadableByteChannel
    public synchronized int read(ByteBuffer byteBuffer) throws IOException {
        if (byteBuffer.hasRemaining()) {
            if (this.peerAppData.hasRemaining()) {
                this.peerAppData.flip();
                return ByteBufferUtils.transferByteBuffer(this.peerAppData, byteBuffer);
            }
            this.peerNetData.compact();
            int read = this.socketChannel.read(this.peerNetData);
            if (read <= 0 && !this.peerNetData.hasRemaining()) {
                if (read < 0) {
                    handleEndOfStream();
                }
                ByteBufferUtils.transferByteBuffer(this.peerAppData, byteBuffer);
                return read;
            }
            this.peerNetData.flip();
            if (this.peerNetData.hasRemaining()) {
                this.peerAppData.compact();
                try {
                    SSLEngineResult unwrap = this.engine.unwrap(this.peerNetData, this.peerAppData);
                    int i = AnonymousClass1.$SwitchMap$javax$net$ssl$SSLEngineResult$Status[unwrap.getStatus().ordinal()];
                    if (i == 1) {
                        this.peerAppData.flip();
                        return ByteBufferUtils.transferByteBuffer(this.peerAppData, byteBuffer);
                    } else if (i == 2) {
                        this.peerAppData.flip();
                        return ByteBufferUtils.transferByteBuffer(this.peerAppData, byteBuffer);
                    } else if (i == 3) {
                        this.peerAppData = enlargeApplicationBuffer(this.peerAppData);
                        return read(byteBuffer);
                    } else if (i == 4) {
                        closeConnection();
                        byteBuffer.clear();
                        return -1;
                    } else {
                        throw new IllegalStateException("Invalid SSL status: " + unwrap.getStatus());
                    }
                } catch (SSLException e) {
                    this.log.error("SSLException during unwrap", (Throwable) e);
                    throw e;
                }
            }
            ByteBufferUtils.transferByteBuffer(this.peerAppData, byteBuffer);
            return read;
        }
        return 0;
    }

    @Override // java.nio.channels.WritableByteChannel
    public synchronized int write(ByteBuffer byteBuffer) throws IOException {
        int i = 0;
        while (byteBuffer.hasRemaining()) {
            this.myNetData.clear();
            SSLEngineResult wrap = this.engine.wrap(byteBuffer, this.myNetData);
            int i2 = AnonymousClass1.$SwitchMap$javax$net$ssl$SSLEngineResult$Status[wrap.getStatus().ordinal()];
            if (i2 == 1) {
                this.myNetData.flip();
                while (this.myNetData.hasRemaining()) {
                    i += this.socketChannel.write(this.myNetData);
                }
            } else if (i2 == 2) {
                throw new SSLException("Buffer underflow occurred after a wrap. I don't think we should ever get here.");
            } else {
                if (i2 != 3) {
                    if (i2 == 4) {
                        closeConnection();
                        return 0;
                    }
                    throw new IllegalStateException("Invalid SSL status: " + wrap.getStatus());
                }
                this.myNetData = enlargePacketBuffer(this.myNetData);
            }
        }
        return i;
    }

    private boolean doHandshake() throws IOException {
        SSLEngineResult.HandshakeStatus handshakeStatus;
        int applicationBufferSize = this.engine.getSession().getApplicationBufferSize();
        this.myAppData = ByteBuffer.allocate(applicationBufferSize);
        this.peerAppData = ByteBuffer.allocate(applicationBufferSize);
        this.myNetData.clear();
        this.peerNetData.clear();
        SSLEngineResult.HandshakeStatus handshakeStatus2 = this.engine.getHandshakeStatus();
        boolean z = false;
        while (!z) {
            int i = AnonymousClass1.$SwitchMap$javax$net$ssl$SSLEngineResult$HandshakeStatus[handshakeStatus2.ordinal()];
            if (i != 1) {
                if (i != 2) {
                    if (i == 3) {
                        this.myNetData.clear();
                        try {
                            SSLEngineResult wrap = this.engine.wrap(this.myAppData, this.myNetData);
                            handshakeStatus = wrap.getHandshakeStatus();
                            int i2 = AnonymousClass1.$SwitchMap$javax$net$ssl$SSLEngineResult$Status[wrap.getStatus().ordinal()];
                            if (i2 == 1) {
                                this.myNetData.flip();
                                while (this.myNetData.hasRemaining()) {
                                    this.socketChannel.write(this.myNetData);
                                }
                            } else if (i2 == 2) {
                                throw new SSLException("Buffer underflow occurred after a wrap. I don't think we should ever get here.");
                            } else {
                                if (i2 == 3) {
                                    this.myNetData = enlargePacketBuffer(this.myNetData);
                                } else if (i2 == 4) {
                                    try {
                                        this.myNetData.flip();
                                        while (this.myNetData.hasRemaining()) {
                                            this.socketChannel.write(this.myNetData);
                                        }
                                        this.peerNetData.clear();
                                    } catch (Exception unused) {
                                        handshakeStatus2 = this.engine.getHandshakeStatus();
                                    }
                                } else {
                                    throw new IllegalStateException("Invalid SSL status: " + wrap.getStatus());
                                }
                            }
                        } catch (SSLException unused2) {
                            this.engine.closeOutbound();
                            handshakeStatus2 = this.engine.getHandshakeStatus();
                        }
                    } else if (i == 4) {
                        while (true) {
                            Runnable delegatedTask = this.engine.getDelegatedTask();
                            if (delegatedTask == null) {
                                break;
                            }
                            this.executor.execute(delegatedTask);
                        }
                        handshakeStatus2 = this.engine.getHandshakeStatus();
                    } else if (i != 5) {
                        throw new IllegalStateException("Invalid SSL status: " + handshakeStatus2);
                    }
                } else if (this.socketChannel.read(this.peerNetData) < 0) {
                    if (this.engine.isInboundDone() && this.engine.isOutboundDone()) {
                        return false;
                    }
                    try {
                        this.engine.closeInbound();
                    } catch (SSLException unused3) {
                    }
                    this.engine.closeOutbound();
                    handshakeStatus2 = this.engine.getHandshakeStatus();
                } else {
                    this.peerNetData.flip();
                    try {
                        SSLEngineResult unwrap = this.engine.unwrap(this.peerNetData, this.peerAppData);
                        this.peerNetData.compact();
                        handshakeStatus = unwrap.getHandshakeStatus();
                        int i3 = AnonymousClass1.$SwitchMap$javax$net$ssl$SSLEngineResult$Status[unwrap.getStatus().ordinal()];
                        if (i3 != 1) {
                            if (i3 == 2) {
                                this.peerNetData = handleBufferUnderflow(this.peerNetData);
                            } else if (i3 == 3) {
                                this.peerAppData = enlargeApplicationBuffer(this.peerAppData);
                            } else if (i3 == 4) {
                                if (this.engine.isOutboundDone()) {
                                    return false;
                                }
                                this.engine.closeOutbound();
                                handshakeStatus2 = this.engine.getHandshakeStatus();
                            } else {
                                throw new IllegalStateException("Invalid SSL status: " + unwrap.getStatus());
                            }
                        }
                    } catch (SSLException unused4) {
                        this.engine.closeOutbound();
                        handshakeStatus2 = this.engine.getHandshakeStatus();
                    }
                }
                handshakeStatus2 = handshakeStatus;
            } else {
                z = !this.peerNetData.hasRemaining();
                if (z) {
                    return true;
                }
                this.socketChannel.write(this.peerNetData);
            }
        }
        return true;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: org.java_websocket.SSLSocketChannel$1  reason: invalid class name */
    /* loaded from: classes2.dex */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$javax$net$ssl$SSLEngineResult$HandshakeStatus;
        static final /* synthetic */ int[] $SwitchMap$javax$net$ssl$SSLEngineResult$Status;

        static {
            int[] iArr = new int[SSLEngineResult.HandshakeStatus.values().length];
            $SwitchMap$javax$net$ssl$SSLEngineResult$HandshakeStatus = iArr;
            try {
                iArr[SSLEngineResult.HandshakeStatus.FINISHED.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$javax$net$ssl$SSLEngineResult$HandshakeStatus[SSLEngineResult.HandshakeStatus.NEED_UNWRAP.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$javax$net$ssl$SSLEngineResult$HandshakeStatus[SSLEngineResult.HandshakeStatus.NEED_WRAP.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$javax$net$ssl$SSLEngineResult$HandshakeStatus[SSLEngineResult.HandshakeStatus.NEED_TASK.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$javax$net$ssl$SSLEngineResult$HandshakeStatus[SSLEngineResult.HandshakeStatus.NOT_HANDSHAKING.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            int[] iArr2 = new int[SSLEngineResult.Status.values().length];
            $SwitchMap$javax$net$ssl$SSLEngineResult$Status = iArr2;
            try {
                iArr2[SSLEngineResult.Status.OK.ordinal()] = 1;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$javax$net$ssl$SSLEngineResult$Status[SSLEngineResult.Status.BUFFER_UNDERFLOW.ordinal()] = 2;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                $SwitchMap$javax$net$ssl$SSLEngineResult$Status[SSLEngineResult.Status.BUFFER_OVERFLOW.ordinal()] = 3;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                $SwitchMap$javax$net$ssl$SSLEngineResult$Status[SSLEngineResult.Status.CLOSED.ordinal()] = 4;
            } catch (NoSuchFieldError unused9) {
            }
        }
    }

    private ByteBuffer enlargePacketBuffer(ByteBuffer byteBuffer) {
        return enlargeBuffer(byteBuffer, this.engine.getSession().getPacketBufferSize());
    }

    private ByteBuffer enlargeApplicationBuffer(ByteBuffer byteBuffer) {
        return enlargeBuffer(byteBuffer, this.engine.getSession().getApplicationBufferSize());
    }

    private ByteBuffer enlargeBuffer(ByteBuffer byteBuffer, int i) {
        if (i > byteBuffer.capacity()) {
            return ByteBuffer.allocate(i);
        }
        return ByteBuffer.allocate(byteBuffer.capacity() * 2);
    }

    private ByteBuffer handleBufferUnderflow(ByteBuffer byteBuffer) {
        if (this.engine.getSession().getPacketBufferSize() < byteBuffer.limit()) {
            return byteBuffer;
        }
        ByteBuffer enlargePacketBuffer = enlargePacketBuffer(byteBuffer);
        byteBuffer.flip();
        enlargePacketBuffer.put(byteBuffer);
        return enlargePacketBuffer;
    }

    private void closeConnection() throws IOException {
        this.engine.closeOutbound();
        try {
            doHandshake();
        } catch (IOException unused) {
        }
        this.socketChannel.close();
    }

    private void handleEndOfStream() throws IOException {
        try {
            this.engine.closeInbound();
        } catch (Exception unused) {
            this.log.error("This engine was forced to close inbound, without having received the proper SSL/TLS close notification message from the peer, due to end of stream.");
        }
        closeConnection();
    }

    @Override // org.java_websocket.WrappedByteChannel
    public boolean isNeedRead() {
        return this.peerNetData.hasRemaining() || this.peerAppData.hasRemaining();
    }

    @Override // org.java_websocket.WrappedByteChannel
    public int readMore(ByteBuffer byteBuffer) throws IOException {
        return read(byteBuffer);
    }

    @Override // org.java_websocket.WrappedByteChannel
    public boolean isBlocking() {
        return this.socketChannel.isBlocking();
    }

    @Override // java.nio.channels.Channel
    public boolean isOpen() {
        return this.socketChannel.isOpen();
    }

    @Override // java.nio.channels.Channel, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        closeConnection();
    }

    @Override // org.java_websocket.interfaces.ISSLChannel
    public SSLEngine getSSLEngine() {
        return this.engine;
    }
}
