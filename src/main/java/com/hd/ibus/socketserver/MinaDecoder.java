package com.hd.ibus.socketserver;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.CumulativeProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolDecoderOutput;

/**
 * Created by lekoxnfx on 2017/7/13.
 */
public class MinaDecoder extends CumulativeProtocolDecoder {

    protected boolean doDecode(IoSession ioSession, IoBuffer ioBuffer, ProtocolDecoderOutput protocolDecoderOutput) throws Exception {
        if(ioBuffer.remaining()>0){
            String outputHex = "";
            byte[] bytes = new byte[ioBuffer.remaining()];
            ioBuffer.get(bytes);
            outputHex = bytesToHexString(bytes);
            protocolDecoderOutput.write(outputHex);
        }
        return true;
    }

    //将byte[]转为Hex
    public String bytesToHexString(byte[] src){
        StringBuilder stringBuilder = new StringBuilder("");
        if (src == null || src.length <= 0) {
            return null;
        }
        for (int i = 0; i < src.length; i++) {
            int v = src[i] & 0xFF;
            String hv = Integer.toHexString(v);
            if (hv.length() < 2) {
                stringBuilder.append(0);
            }

            stringBuilder.append(hv.toUpperCase());
        }
        return stringBuilder.toString();
    }

    public String byteToHexString(byte src){
        byte[] bytes = {src};
        return bytesToHexString(bytes);
    }

    //将byte[]转换为ASCII,0x00除外
    public String bytesASCIIToString(byte[] src){
        int tRecvCount = src.length;
        String nRcvString;
        StringBuffer  tStringBuf=new StringBuffer ();
        for(int i=0;i<tRecvCount;i++) {
            if(src[i]!=0x00){
                tStringBuf.append((char)src[i]);
            }
        }
        nRcvString=tStringBuf.toString();
        return nRcvString;
    }
    public String byteASCIIToString(byte src){
        byte[] bytes = {src};
        return bytesASCIIToString(bytes);
    }
}
