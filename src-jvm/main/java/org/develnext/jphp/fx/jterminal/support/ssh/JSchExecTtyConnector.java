package org.develnext.jphp.fx.jterminal.support.ssh;

import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;

public class JSchExecTtyConnector extends JSchTtyConnector<ChannelExec> {
    private final String command;

    public JSchExecTtyConnector(Session session, String command) {
        super(session);
        this.command = command;
    }

    @Override
    protected ChannelExec openChannel(Session session) throws JSchException {
        return (ChannelExec) session.openChannel("exec");
    }

    @Override
    protected void configureChannelShell(ChannelExec channel) {
        String lang = System.getenv().get("LANG");
        channel.setEnv("LANG", lang != null ? lang : "en_US.UTF-8");
        channel.setPtyType("xterm");
        channel.setCommand(command);
    }

    @Override
    protected void setPtySize(ChannelExec channel, int col, int row, int wp, int hp) {
        channel.setPtySize(col, row, wp, hp);
    }
}
