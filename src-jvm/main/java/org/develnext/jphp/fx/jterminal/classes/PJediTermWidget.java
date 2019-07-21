package org.develnext.jphp.fx.jterminal.classes;

import com.jediterm.pty.PtyProcessTtyConnector;
import com.jediterm.terminal.ui.JediTermWidget;
import com.jediterm.terminal.ui.settings.DefaultSettingsProvider;
import com.pty4j.PtyProcess;
import javafx.embed.swing.SwingNode;
import org.develnext.jphp.ext.javafx.classes.UXNode;
import org.develnext.jphp.fx.jterminal.JediTermGuiExtension;
import php.runtime.annotation.Reflection;
import php.runtime.env.Environment;
import php.runtime.lang.BaseWrapper;
import php.runtime.reflection.ClassEntity;

import java.nio.charset.Charset;

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
        __wrappedObject = new JediTermWidget(new DefaultSettingsProvider())
                .createTerminalSession(new PtyProcessTtyConnector(process, Charset.forName("UTF-8")));
    }

    @Reflection.Signature
    public void __construct() {
        __wrappedObject = new JediTermWidget(new DefaultSettingsProvider());
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
        getWrappedObject().createTerminalSession(new PtyProcessTtyConnector(process, Charset.forName("UTF-8")));
    }

    @Reflection.Signature
    public UXNode getFXNode() {
        SwingNode node = new SwingNode();
        node.setContent(getWrappedObject());

        return new UXNode<SwingNode>(__env__, node);
    }
}
