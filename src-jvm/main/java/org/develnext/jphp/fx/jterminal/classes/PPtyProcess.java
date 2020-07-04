package org.develnext.jphp.fx.jterminal.classes;

import com.pty4j.PtyProcess;
import org.develnext.jphp.fx.jterminal.JediTermGuiExtension;
import php.runtime.Memory;
import php.runtime.annotation.Reflection;
import php.runtime.env.Environment;
import php.runtime.ext.core.classes.stream.MiscStream;
import php.runtime.lang.BaseWrapper;
import php.runtime.memory.LongMemory;
import php.runtime.memory.ObjectMemory;
import php.runtime.reflection.ClassEntity;

import java.io.File;
import java.io.IOException;
import java.util.Map;

@Reflection.Name("PtyProcess")
@Reflection.Namespace(JediTermGuiExtension.NS_TTY)
@Reflection.Abstract
public class PPtyProcess extends BaseWrapper<PtyProcess> {

    public PPtyProcess(Environment env, PtyProcess wrappedObject) {
        super(env, wrappedObject);
    }

    public PPtyProcess(Environment env, ClassEntity clazz) {
        super(env, clazz);
    }

    @Reflection.Signature
    public static PtyProcess exec(String[] args) throws IOException {
        return PtyProcess.exec(args);
    }

    @Reflection.Signature
    public static PtyProcess exec(String[] args, Map<String, String> env) throws IOException {
        return PtyProcess.exec(args, env);
    }

    @Reflection.Signature
    public static PtyProcess exec(String[] args, Map<String, String> env, File workDir) throws IOException {
        return PtyProcess.exec(args, env, workDir.getAbsolutePath());
    }

    @Reflection.Signature
    public Memory getExitValue(Environment env, Memory... args) {
        try {
            return LongMemory.valueOf(this.getWrappedObject().exitValue());
        } catch (IllegalThreadStateException var4) {
            return Memory.NULL;
        }
    }

    @Reflection.Signature
    public Memory getInput(Environment env, Memory... args) {
        return new ObjectMemory(new MiscStream(env, this.getWrappedObject().getInputStream()));
    }

    @Reflection.Signature
    public Memory getOutput(Environment env, Memory... args) {
        return new ObjectMemory(new MiscStream(env, this.getWrappedObject().getOutputStream()));
    }

    @Reflection.Signature
    public Memory getError(Environment env, Memory... args) {
        return new ObjectMemory(new MiscStream(env, this.getWrappedObject().getErrorStream()));
    }

    @Reflection.Signature
    public void destroy(Environment env) {
        this.getWrappedObject().destroy();
    }

    @Reflection.Signature
    public boolean isAlive() {
        return this.getWrappedObject().isAlive();
    }

    @Reflection.Signature
    public int waitFor() throws InterruptedException {
        return this.getWrappedObject().waitFor();
    }
}
