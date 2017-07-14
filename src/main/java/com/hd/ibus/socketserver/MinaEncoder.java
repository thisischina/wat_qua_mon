package com.hd.ibus.socketserver;

import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolEncoder;
import org.apache.mina.filter.codec.ProtocolEncoderOutput;

/**
 * Created by lekoxnfx on 2017/7/13.
 */
public class MinaEncoder implements ProtocolEncoder {
    public void encode(IoSession ioSession, Object o, ProtocolEncoderOutput protocolEncoderOutput) throws Exception {

    }

    public void dispose(IoSession ioSession) throws Exception {

    }
}
