package org.develnext.jphp.fx.jterminal.classes;

import com.jediterm.pty.PtyProcessTtyConnector;
import com.pty4j.PtyProcess;
import javafx.embed.swing.SwingNode;
import org.develnext.jphp.ext.javafx.classes.UXNode;
import org.develnext.jphp.fx.jterminal.JediTermGuiExtension;
import org.develnext.jphp.fx.jterminal.support.JediTermWidgetImpl;
import php.runtime.Memory;
import php.runtime.annotation.Reflection;
import php.runtime.env.Environment;
import php.runtime.lang.BaseWrapper;
import php.runtime.reflection.ClassEntity;

import java.nio.charset.StandardCharsets;

@Reflection.Name("JediTermWidget")
@Reflection.Namespace(JediTermGuiExtension.NS_UI)
public class PJediTermWidget extends BaseWrapper<JediTermWidgetImpl> {
    public PJediTermWidget(Environment env, JediTermWidgetImpl wrappedObject) {
        super(env, wrappedObject);
    }

    public PJediTermWidget(Environment env, ClassEntity clazz) {
        super(env, clazz);
    }

    @Reflection.Signature
    public void __construct(Memory process) {
        __construct(process, new PSettingsProvider(__env__));
    }

    @Reflection.Signature
    public void __construct(Memory process, PSettingsProvider settingsProvider) {
        __wrappedObject = new JediTermWidgetImpl(settingsProvider);

        if (!process.isNull())
            __wrappedObject.createTerminalSession(new PtyProcessTtyConnector((PtyProcess) Memory.unwrap(__env__, process), StandardCharsets.UTF_8));
    }

    @Reflection.Signature
    public void __construct() {
        __wrappedObject = new JediTermWidgetImpl(new PSettingsProvider(__env__));
    }

    @Reflection.Signature
    public void requestFocus() {
        getWrappedObject().requestFocus();
    }

    @Reflection.Signature
    public void requestFocusInWindow() {
        getWrappedObject().requestFocusInWindow();
    }

    @Reflection.Signature
    public void start() {
        getWrappedObject().start();
    }

    @Reflection.Signature
    public void stop() {
        getWrappedObject().stop();
    }

    @Reflection.Signature
    public void createTerminalSession(PtyProcess process) {
        getWrappedObject().createTerminalSession(new PtyProcessTtyConnector(process, StandardCharsets.UTF_8));
    }

    @Reflection.Signature
    public UXNode getFXNode() {
        SwingNode node = new SwingNode();
        node.setContent(getWrappedObject());

        return new UXNode<SwingNode>(__env__, node);
    }
}
