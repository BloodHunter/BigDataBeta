package com.wbl.util;

import com.google.protobuf.InvalidProtocolBufferException;
import com.wbl.modal.CountModal;
import com.wbl.pb.CountModalProto;
import org.apache.log4j.Logger;

/**
 * Created by wbl on 16/10/8.
 */
public class PbMapper {

    private static Logger pbMapperLogger = Logger.getLogger(PbMapper.class);

    public static byte[] countModal2Pb(CountModal countModal){
        return CountModalProto.CountModal.newBuilder()
                .setCount(countModal.getCount())
                .setName(countModal.getName())
                .build().toByteArray();
    }

    public static CountModal pb2CountModal(byte[] pb){
        CountModal countModal = new CountModal();
        CountModalProto.CountModal countModalProto = null;
        try {
            countModalProto = CountModalProto.CountModal.parseFrom(pb);
        } catch (InvalidProtocolBufferException e) {
            pbMapperLogger.error("pb to CountModal error");
        }
        countModal.setCount(countModalProto.getCount());
        countModal.setName(countModalProto.getName());
        return countModal;
    }


    public static void main(String[] args) throws InvalidProtocolBufferException {
        CountModal countModal = new CountModal();
        countModal.setCount(1);
        countModal.setName("wbl");
        byte[] bytes = PbMapper.countModal2Pb(countModal);
        System.out.println(PbMapper.pb2CountModal(bytes));
    }
}
