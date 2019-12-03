package org.develnext.jphp.fx.jterminal.classes;

import com.jediterm.pty.PtyProcessTtyConnector;
import com.jediterm.terminal.ui.JediTermWidget;
import com.pty4j.PtyProcess;
import javafx.embed.swing.SwingNode;
import org.develnext.jphp.ext.javafx.classes.UXNode;
import org.develnext.jphp.fx.jterminal.JediTermGuiExtension;
import php.runtime.annotation.Reflection;
import php.runtime.env.Environment;
import php.runtime.lang.BaseWrapper;
import php.runtime.reflection.ClassEntity;

import java.lang.reflect.Field;
import java.nio.charset.StandardCharsets;

@Reflection.Name("JediTermWidget")
@Reflection.Namespace(JediTermGuiExtension.NS_UI)
public class PJediTermWidget extends BaseWrapper<JediTermWidget> {
    public PJediTermWidget(Environment env, JediTermWidget wrappedObject) {
        super(env, wrappedObject);
    }

    public PJediTermWidget(Environment env, ClassEntity clazz) {
        super(env, clazz);
    }

    @Reflection.Signature
    public void __construct(PtyProcess process) {
        __construct(process, new PSettingsProvider(__env__));
    }

    @Reflection.Signature
    public void __construct(PtyProcess process, PSettingsProvider settingsProvider) {
        __wrappedObject = new JediTermWidget(settingsProvider);

        if (process != null)
            __wrappedObject.createTerminalSession(new PtyProcessTtyConnector(process, StandardCharsets.UTF_8));
    }

    @Reflection.Signature
    public void __construct() {
        __wrappedObject = new JediTermWidget(new PSettingsProvider(__env__));
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
