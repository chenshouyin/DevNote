package com.csy.common.utils.convert;

import java.nio.ByteBuffer;

/**
 * 
 * 数据皿 类型转换
 * 
 * @author L
 *
 */
public class DataTypeConvert {
	public static byte[] short2ByteArrays(short data) {
		byte[] b = new byte[2];
		b[0] = (byte) (data & 0xFF);
		b[1] = (byte) ((data >> 8) & 0xFF);
		return b;
	}

	public static byte[] int2ByteArrays(int data) {
		byte[] b = new byte[4];
		b[0] = (byte) (data & 0xFF);
		b[1] = (byte) ((data >> 8) & 0xFF);
		b[2] = (byte) ((data >> 16) & 0xFF);
		b[3] = (byte) ((data >> 24) & 0xFF);
		return b;
	}

	public static byte[] long2ByteArrays(long data){
		byte[] b = new byte[8];
		b[0] = (byte) (data & 0xFF);
		b[1] = (byte) ((data >> 8) & 0xFF);
		b[2] = (byte) ((data >> 16) & 0xFF);
		b[3] = (byte) ((data >> 24) & 0xFF);
		b[4] = (byte) ((data >> 32) & 0xFF);
		b[5] = (byte) ((data >> 40) & 0xFF);
		b[6] = (byte) ((data >> 48) & 0xFF);
		b[7] = (byte) ((data >> 56) & 0xFF);
		return b;
	}

	public static short byteArrays2Short(byte[] data) {
		return (short) ((data[0] & 0xFF) | (data[1] & 0xFF) << 8);
	}

	public static int byteArrays2Int(byte[] data) {
		int i = 0;
		i |= data[0] & 0xFF;
		i |= (data[1] & 0xFF) << 8;
		i |= (data[2] & 0xFF) << 16;
		i |= (data[3] & 0xFF) << 24;
		return i;
	}

	private static byte[] convert2Bytes(long data, int num) {
		byte[] b = new byte[num];
		for (int i = 0; i < num; i++) {
			b[i] = (byte) ((data >> (8 * i)) & 0xFF);
		}
		return b;
	}

//	private static long convert2Long(byte[] data, int num) {
//		long ret = 0;
//		for (int i = 0; i < num; i++) {
//			ret |= (data[i] << (8 * i));
//		}
//		return ret;
//	}
	
	/**
	 * @return float转int 四舍五入
	 */

	public static int floatToInt(float f) {
		int i = 0;
		if (f > 0) // 正数
			i = (int) ((f * 10 + 5) / 10);
		else if (f < 0) // 负数
			i = (int) ((f * 10 - 5) / 10);
		else
			i = 0;

		return i;

	}

	public static float getFloat(byte[] bytes) {
		ByteBuffer buf = ByteBuffer.allocateDirect(4); // 无额外内存的直接缓存
		// buf=buf.order(ByteOrder.LITTLE_ENDIAN);//默认大端，小端用这行
		buf.put(bytes);
		buf.rewind();
		return buf.getFloat();
	}


	// byte数组转成long
	public static long byteArrays2Long(byte[] b) {
		long s = 0;
		long s0 = b[0] & 0xff;// 朿低位
		long s1 = b[1] & 0xff;
		long s2 = b[2] & 0xff;
		long s3 = b[3] & 0xff;
		long s4 = b[4] & 0xff;// 朿低位
		long s5 = b[5] & 0xff;
		long s6 = b[6] & 0xff;
		long s7 = b[7] & 0xff;

		// s0不变
		s1 <<= 8;
		s2 <<= 16;
		s3 <<= 24;
		s4 <<= 8 * 4;
		s5 <<= 8 * 5;
		s6 <<= 8 * 6;
		s7 <<= 8 * 7;
		s = s0 | s1 | s2 | s3 | s4 | s5 | s6 | s7;
		return s;
	}

	private static byte[] Reverse(byte[] b) {

		byte[] temp = new byte[b.length];
		for (int i = 0; i < b.length; i++) {
			temp[i] = b[b.length - 1 - i];
		}
		return temp;
	}

	public static int getUint(byte b[]) {

		b = Reverse(b);
		int mask = 0xff;
		int temp = 0;
		int n = 0;
		for (int i = 0; i < 4; i++) {
			n <<= 8;
			temp = b[i] & mask;
			n |= temp;
		}
		return n;
	}

	/**
	 * 通过byte数组取到short
	 * 
	 * @param b
	 *            byte数组
	 * 
	 * @return
	 */
	public static short getShort(byte[] b) {
		return (short) (((b[1] << 8) | b[0] & 0xff));
	}
	
	public static int[] getIntArray(byte[] bytes){
		int[] data = new int[bytes.length / 4];
		int k = 0;
		for (int i = 0; i < bytes.length / 4; i++) {
			byte[] bdata = new byte[4];
			for (int j = 0; j < 4; j++, k++) {
				bdata[j] = bytes[k];
			}

			data[i] = DataTypeConvert.byteArrays2Int(bdata);

		}
		return data;
	}


	
	
	public static String getCharString(byte[] b){
		int i = 0;
		for (; i < b.length; i++){
			if (b[i] == 0)
				break;
		}
		return new String(b, 0, i);
	}
	
	public static String getTCharString(byte[] bytes) {
		char[] chs = new char[bytes.length / 2];

		for (int i = 0; i < chs.length; i++) {
			chs[i] = (char) (((bytes[2 * i + 1]) << 8) + bytes[2 * i]);
		}

		return charsToString(chs);

	}
	
	public static short[] getShortArray(byte[] bytes){
		short[] s = new short[bytes.length / 2];
		for (int i = 0; i < s.length; i++){
			s[i] = byteArrays2Short(new byte[]{bytes[2 * i], bytes[2 * i + 1]});
		}
		return s;
	}
	public static String charsToString(char[] chars){
		int i = 0;
		for (; i < chars.length; i++){
			if (chars[i] == '\0'){
				break;
			}
		}
		
		return new String(chars, 0, i);
	}
	
	public static byte[] stringToTCharBytes(String value){ 
	    char[] chars=value.toCharArray(); 
	    byte[] data=new byte[chars.length* 2 ]; 
	    
	    for(int i=0; i < chars.length ; i++){ 
	        data[i*2]=(byte) chars[i]; 
	        data[i*2+1]=(byte)(chars[i]>>8); 
	    } 
	    
	    return data; 
	}
	
}
