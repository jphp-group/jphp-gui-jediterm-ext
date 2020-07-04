package org.develnext.jphp.fx.jterminal.classes.tty;


import com.jediterm.terminal.TtyConnector;
import org.develnext.jphp.fx.jterminal.JediTermGuiExtension;
import php.runtime.annotation.Reflection;
import php.runtime.env.Environment;
import php.runtime.lang.BaseWrapper;
import php.runtime.reflection.ClassEntity;

import java.awt.*;
import java.io.IOException;

@Reflection.Abstract
@Reflection.Name("TtyConnector")
@Reflection.Namespace(JediTermGuiExtension.NS_TTY)
abstract public class PTtyConnector<T extends TtyConnector> extends BaseWrapper<T> {
    public PTtyConnector(Environment env, T wrappedObject) {
        super(env, wrappedObject);
    }

    public PTtyConnector(Environment env, ClassEntity clazz) {
        super(env, clazz);
    }

    interface WrappedInterface {
        void close();
        void resize(Dimension termSize, Dimension pixelSize);
        String getName();
        int read(char[] var1, int var2, int var3) throws IOException;
        void write(byte[] var1) throws IOException;
        boolean isConnected();
        int waitFor() throws InterruptedException;
    }
}
