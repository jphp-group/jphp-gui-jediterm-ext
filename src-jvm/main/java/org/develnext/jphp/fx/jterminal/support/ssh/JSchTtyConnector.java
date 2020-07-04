package org.develnext.jphp.fx.jterminal.support.ssh;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jediterm.terminal.Questioner;
import com.jediterm.terminal.TtyConnector;

import java.awt.*;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.atomic.AtomicBoolean;

public abstract class JSchTtyConnector<T extends Channel> implements TtyConnector {
    public static final int DEFAULT_PORT = 22;

    private InputStream myInputStream = null;
    private OutputStream myOutputStream = null;
    private Session mySession;
    private T myChannelShell;
    private final AtomicBoolean isInitiated = new AtomicBoolean(false);

    private Dimension myPendingTermSize;
    private Dimension myPendingPixelSize;
    private InputStreamReader myInputStreamReader;

    public JSchTtyConnector(Session session) {
        mySession = session;
    }

    abstract protected void setPtySize(T channel, int col, int row, int wp, int hp);

    private void resizeImmediately() {
        if (myPendingTermSize != null && myPendingPixelSize != null) {
            setPtySize(myChannelShell, myPendingTermSize.width, myPendingTermSize.height, myPendingPixelSize.width, myPendingPixelSize.height);
            myPendingTermSize = null;
            myPendingPixelSize = null;
        }
    }

    @Override
    public void resize(Dimension termSize, Dimension pixelSize) {
        myPendingTermSize = termSize;
        myPendingPixelSize = pixelSize;
        if (myChannelShell != null) {
            resizeImmediately();
        }
    }

    public void close() {
        if (mySession != null) {
            mySession.disconnect();
            mySession = null;
            myInputStream = null;
            myOutputStream = null;
        }
    }

    abstract protected T openChannel(Session session) throws JSchException;

    abstract protected void configureChannelShell(T channel);

    public boolean init(Questioner q) {
        try {
            myChannelShell = openChannel(mySession);
            configureChannelShell(myChannelShell);
            myInputStream = myChannelShell.getInputStream();
            myOutputStream = myChannelShell.getOutputStream();
            myInputStreamReader = new InputStreamReader(myInputStream, StandardCharsets.UTF_8);
            myChannelShell.connect();
            resizeImmediately();
            return true;
        } catch (final IOException | JSchException e) {
            q.showMessage(e.getMessage());
            return false;
        } finally {
            isInitiated.set(true);
        }
    }

    public String getName() {
        return "Remote";
    }

    @Override
    public int read(char[] buf, int offset, int length) throws IOException {
        return myInputStreamReader.read(buf, offset, length);
    }

    public int read(byte[] buf, int offset, int length) throws IOException {
        return myInputStream.read(buf, offset, length);
    }

    public void write(byte[] bytes) throws IOException {
        if (myOutputStream != null) {
            myOutputStream.write(bytes);
            myOutputStream.flush();
        }
    }

    @Override
    public boolean isConnected() {
        return myChannelShell != null && myChannelShell.isConnected();
    }

    @Override
    public void write(String string) throws IOException {
        write(string.getBytes(StandardCharsets.UTF_8));
    }

    @Override
    public int waitFor() throws InterruptedException {
        while (!isInitiated.get() || isRunning(myChannelShell)) {
            Thread.sleep(100); //TODO: remove busy wait
        }
        return myChannelShell.getExitStatus();
    }

    private static boolean isRunning(Channel channel) {
        return channel != null && channel.getExitStatus() < 0 && channel.isConnected();
    }

}