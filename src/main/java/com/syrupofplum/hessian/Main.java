package com.syrupofplum.hessian;

import com.caucho.hessian.io.AbstractHessianOutput;
import com.caucho.hessian.io.Hessian2Output;
import com.caucho.hessian.io.SerializerFactory;
import org.apache.commons.lang3.StringUtils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Test implements Serializable {
    private int v;

    public int getV() {
        return v;
    }

    public void setV(int v) {
        this.v = v;
    }
}

/**
 * @author syrupofplum
 */
public class Main {

    static byte[] serialize(Object v) {
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        AbstractHessianOutput out = new Hessian2Output(os);

        out.setSerializerFactory(new SerializerFactory());
        try {
            out.writeObject(v);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                out.close();
                os.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return os.toByteArray();
    }

    static int[] parseByteArray(byte[] bytes) {
        int[] ret = new int[bytes.length];
        for (int i = 0; i < bytes.length; i++) {
            ret[i] = bytes[i];
            if (ret[i] < 0) {
                ret[i] = 256 + ret[i];
            }
        }
        return ret;
    }

    static void printByteArray(byte[] bytes) {
        String[] ret = new String[bytes.length];
        int[] intArray = parseByteArray(bytes);
        System.out.println(intArray.length);
        for (int i = 0; i < intArray.length; i++) {
            ret[i] = "0x" + Integer.toHexString(intArray[i]);
        }
        String printString = "[" + StringUtils.join(ret, ",") + "]";
        System.out.println(printString);

        for (int i = 0; i < intArray.length; i++) {
            if (intArray[i] != 1) {
                System.out.println(i + " " + "0x" + Integer.toHexString(intArray[i]));
            }
        }
    }

    public static byte[] serializeBool(boolean v) {
        return serialize(v);
    }

    public static byte[] serializeInt(int v) {
        return serialize(v);
    }

    public static byte[] serializeDouble(double v) {
        return serialize(v);
    }

    public static byte[] serializeLong(long v) {
        return serialize(v);
    }

    public static byte[] serializeString(String v) {
        return serialize(v);
    }

    public static byte[] serializeBinary(byte[] v) {
        return serialize(v);
    }

    public static byte[] serializeList(int[] v) {
        return serialize(v);
    }

    public static byte[] serializeList(List<Integer> v) {
        return serialize(v);
    }

    public static byte[] serializeList(Test[] v) {
        return serialize(v);
    }

    public static void main(String[] args) {
        int[] v = {};
//        System.out.println(Arrays.toString(v));
//        List<Integer> v = new ArrayList<>();
        printByteArray(serializeList(v));
//        String typ = "/[com.syrupofplum.hessian.Test";
//        printByteArray(serializeString(typ));

//        Test[] v = new Test[0];
//        printByteArray(serializeList(v));
    }
}
