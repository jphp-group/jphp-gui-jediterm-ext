package org.develnext.jphp.fx.jterminal.classes.tty;

import com.jediterm.pty.PtyProcessTtyConnector;
import com.pty4j.PtyProcess;
import php.runtime.annotation.Reflection;
import php.runtime.env.Environment;
import php.runtime.reflection.ClassEntity;

import java.nio.charset.Charset;

@Reflection.Name("PtyProcessConnector")
public class PPtyProcessConnector extends PTtyConnector<PtyProcessTtyConnector> {
    public PPtyProcessConnector(Environment env, PtyProcessTtyConnector wrappedObject) {
        super(env, wrappedObject);
    }

    public PPtyProcessConnector(Environment env, ClassEntity clazz) {
        super(env, clazz);
    }

    private PtyProcess __ptyProcess;

    @Reflection.Signature
    public void __construct(PtyProcess process, String charset) {
        __wrappedObject = new PtyProcessTtyConnector(process, Charset.forName(charset));
        __ptyProcess = process;
    }

    @Reflection.Signature
    public void __construct(PtyProcess process) {
        __construct(process, "UTF-8");
    }

    @Reflection.Signature
    public PtyProcess getPtyProcess() {
        return __ptyProcess;
    }
}
