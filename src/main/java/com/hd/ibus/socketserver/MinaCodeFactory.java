package com.hd.ibus.socketserver;

import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFactory;
import org.apache.mina.filter.codec.ProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolEncoder;

/**
 * Created by lekoxnfx on 2017/7/13.
 */
public class MinaCodeFactory implements ProtocolCodecFactory {
    public ProtocolEncoder getEncoder(IoSession ioSession) throws Exception {
        return new MinaEncoder();
    }

    public ProtocolDecoder getDecoder(IoSession ioSession) throws Exception {
        return new MinaDecoder();
    }
}
