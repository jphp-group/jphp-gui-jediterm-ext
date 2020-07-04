package org.develnext.jphp.fx.jterminal.support.ssh;

import com.jcraft.jsch.ChannelShell;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;

public class JSchShellTtyConnector extends JSchTtyConnector<ChannelShell> {
    public JSchShellTtyConnector(Session session) {
        super(session);
    }

    @Override
    protected ChannelShell openChannel(Session session) throws JSchException {
        return (ChannelShell) session.openChannel("shell");
    }

    @Override
    protected void configureChannelShell(ChannelShell channel) {
        String lang = System.getenv().get("LANG");
        channel.setEnv("LANG", lang != null ? lang : "en_US.UTF-8");
        channel.setPtyType("xterm");
    }

    @Override
    protected void setPtySize(ChannelShell channel, int col, int row, int wp, int hp) {
        channel.setPtySize(col, row, wp, hp);
    }
}
