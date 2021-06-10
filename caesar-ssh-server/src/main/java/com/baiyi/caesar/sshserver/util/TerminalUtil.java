package com.baiyi.caesar.sshserver.util;

import com.baiyi.caesar.sshcore.handler.RemoteInvokeHandler;
import com.baiyi.caesar.sshcore.model.JSchSession;
import com.baiyi.caesar.sshcore.model.JSchSessionContainer;
import com.jcraft.jsch.ChannelShell;
import org.jline.terminal.Attributes;
import org.jline.terminal.Size;
import org.jline.terminal.Terminal;

import java.util.EnumSet;

/**
 * @Author baiyi
 * @Date 2021/6/7 9:55 上午
 * @Version 1.0
 */
public class TerminalUtil {

    private TerminalUtil(){
    }

    // 原始模式 Ctrl+C
    public static void enterRawMode(Terminal terminal) {
        Attributes prvAttr = terminal.getAttributes();
        Attributes newAttr = new Attributes(prvAttr);
        newAttr.setLocalFlags(EnumSet.of(Attributes.LocalFlag.ICANON, Attributes.LocalFlag.ECHO, Attributes.LocalFlag.IEXTEN), false);
        newAttr.setInputFlags(EnumSet.of(Attributes.InputFlag.IXON, Attributes.InputFlag.ICRNL, Attributes.InputFlag.INLCR), false);
        newAttr.setControlChar(Attributes.ControlChar.VMIN, 1);
        newAttr.setControlChar(Attributes.ControlChar.VTIME, 0);
        newAttr.setControlChar(Attributes.ControlChar.VINTR, 0);
        terminal.setAttributes(newAttr);
    }

    public static void resize(String sessionId, String instanceId, Size size){
        JSchSession jSchSession = JSchSessionContainer.getBySessionId(sessionId, instanceId);
        RemoteInvokeHandler.setChannelPtySize((ChannelShell)jSchSession.getChannel(),size);
    }
}