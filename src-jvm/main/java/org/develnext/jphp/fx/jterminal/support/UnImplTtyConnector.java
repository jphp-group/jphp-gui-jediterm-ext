package org.develnext.jphp.fx.jterminal.support;

import com.jediterm.terminal.Questioner;
import com.jediterm.terminal.TtyConnector;

import java.awt.*;
import java.io.IOException;

public class UnImplTtyConnector implements TtyConnector {
    @Override
    public boolean init(Questioner questioner) {
        return true;
    }

    @Override
    public void close() {

    }

    @Override
    public void resize(Dimension dimension, Dimension dimension1) {

    }

    @Override
    public String getName() {
        return "UnImpl";
    }

    @Override
    public int read(char[] chars, int i, int i1) throws IOException {
        return 0;
    }

    @Override
    public void write(byte[] bytes) throws IOException {

    }

    @Override
    public boolean isConnected() {
        return false;
    }

    @Override
    public void write(String s) throws IOException {

    }

    @Override
    public int waitFor() throws InterruptedException {
        return 0;
    }
}
