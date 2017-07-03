package com.hd.ibus.util.dataUtil;

import java.util.HashMap;
import java.util.Map;

public class Judge {
	
	 public static Map<String, Float> receiveWords (byte[] word){
		Map<String, Float> map=new HashMap<String,Float>();
		//先判断数组长度，是否属于返回值范围 温度电流电压
		byte[] nae=Commns.subBytes(word,3,4);

		float id1= Integer.parseInt(Commns.byteToHexString(word[1]),16);
		float id2= Integer.parseInt(Commns.byteToHexString(word[2]),16);
		//智能母线单元if(nae[1]==0x81&&nae[1]==0xE1&&nae[1]==0Xf1){
		//接电相单元else if(nae[1]==0x71&&nae[1]==11&&nae[1]==0x61&&nae[1]==0x01){}
		if(nae[1]==0x01){
			byte[] len=new byte[1];//长度数组
			len[0]=word[4];
			String length1=(Commns.byteArrayToHexString(len));//数组转字符串-16进制长度
			//String length2=Commns.hexString2binaryString(length1);//10进制长度
			int length=Integer.parseInt(length1, 16);
			//String length2=Commns.hexString2binaryString(length1);//10进制长度
			
			//System.out.println("length1:"+length1+",length:"+length+",length:"+length);
			if(length==18){
				//温度1
				int i=5;
				byte[]tp10=Commns.subBytes(word,i,2); 
				byte[] len1=new byte[1];//高字节
				len1[0]=tp10[0];
				String gao=(Commns.byteArrayToHexString(len1));
				int tp1t1=Integer.parseInt(gao, 16);
				byte[] len2=new byte[1];//低字节
				len2[0]=tp10[1];
				String di=(Commns.byteArrayToHexString(len2));
				int tp1t2=Integer.parseInt(di, 16);
				if(tp1t1<=80){
					float tp1=(float)tp1t2/10+(float)tp1t1*256/10;
					map.put("tp1", tp1);
				}else{
					float tp1=-(((float)tp1t1-80)*256+(float)tp1t2)/10;
					map.put("tp1", tp1);
				}
				i=i+2;
				//温度2
				byte[]qtp10=Commns.subBytes(word,i,2); 
				byte[] qlen1=new byte[1];//高字节
				qlen1[0]=qtp10[0];
				String qgao=(Commns.byteArrayToHexString(qlen1));
				int qtp1t1=Integer.parseInt(qgao, 16);
				byte[] qlen2=new byte[1];//低字节
				qlen2[0]=qtp10[1];
				String qdi=(Commns.byteArrayToHexString(qlen2));
				int qtp1t2=Integer.parseInt(qdi, 16);
				if(qtp1t1<=80){
					float qtp1=(float)qtp1t2/10+(float)qtp1t1*256/10;
					map.put("tp2", qtp1);
				}else{
					float qtp1=-(((float)qtp1t1-80)*256+(float)qtp1t2)/10;
					map.put("tp2", qtp1);
				}
				i=i+2;
				//温度3
				byte[]wtp10=Commns.subBytes(word,i,2); 
				byte[] wlen1=new byte[1];//高字节
				wlen1[0]=wtp10[0];
				String wgao=(Commns.byteArrayToHexString(wlen1));
				int wtp1t1=Integer.parseInt(wgao, 16);
				byte[] wlen2=new byte[1];//低字节
				wlen2[0]=wtp10[1];
				String wdi=(Commns.byteArrayToHexString(wlen2));
				int wtp1t2=Integer.parseInt(wdi, 16);
				if(wtp1t1<=80){
					float wtp1=(float)wtp1t2/10+(float)wtp1t1*256/10;
					map.put("tp3", wtp1);
				}else{
					float wtp1=-(((float)wtp1t1-80)*256+(float)wtp1t2)/10;
					map.put("tp3", wtp1);
				}
				i=i+2;
				//温度4
				byte[]rtp10=Commns.subBytes(word,i,2); 
				byte[] rlen1=new byte[1];//高字节
				rlen1[0]=rtp10[0];
				String rgao=(Commns.byteArrayToHexString(rlen1));
				int rtp1t1=Integer.parseInt(rgao, 16);
				byte[] rlen2=new byte[1];//低字节
				rlen2[0]=rtp10[1];
				String rdi=(Commns.byteArrayToHexString(rlen2));
				int rtp1t2=Integer.parseInt(rdi, 16);
				if(rtp1t1<=80){
					float rtp1=(float)rtp1t2/10+(float)rtp1t1*256/10;
					map.put("tp4", rtp1);
				}else{
					float rtp1=-(((float)rtp1t1-80)*256+(float)rtp1t2)/10;
					map.put("tp4", rtp1);
				}
				i=i+2;
				//温度5
				byte[]ttp10=Commns.subBytes(word,i,2); 
				byte[] tlen1=new byte[1];//高字节
				tlen1[0]=ttp10[0];
				String tgao=(Commns.byteArrayToHexString(tlen1));
				int ttp1t1=Integer.parseInt(tgao, 16);
				byte[] tlen2=new byte[1];//低字节
				tlen2[0]=ttp10[1];
				String tdi=(Commns.byteArrayToHexString(tlen2));
				int ttp1t2=Integer.parseInt(tdi, 16);
				if(ttp1t1<=80){
					float ttp1=(float)ttp1t2/10+(float)ttp1t1*256/10;
					map.put("tp5", ttp1);
				}else{
					float ttp1=-(((float)ttp1t1-80)*256+(float)ttp1t2)/10;
					map.put("tp5", ttp1);
				}
				i=i+2;
				//温度6
				byte[]ytp10=Commns.subBytes(word,i,2); 
				byte[] ylen1=new byte[1];//高字节
				ylen1[0]=ytp10[0];
				String ygao=(Commns.byteArrayToHexString(ylen1));
				int ytp1t1=Integer.parseInt(ygao, 16);
				byte[] ylen2=new byte[1];//低字节
				ylen2[0]=ytp10[1];
				String ydi=(Commns.byteArrayToHexString(ylen2));
				int ytp1t2=Integer.parseInt(ydi, 16);
				if(ytp1t1<=80){
					float ytp1=(float)ytp1t2/10+(float)ytp1t1*256/10;
					map.put("tp6", ytp1);
				}else{
					float ytp1=-(((float)ytp1t1-80)*256+(float)ytp1t2)/10;
					map.put("tp6", ytp1);
				}
				i=i+2;
				//温度7
				byte[]utp10=Commns.subBytes(word,i,2); 
				byte[] ulen1=new byte[1];//高字节
				ulen1[0]=utp10[0];
				String ugao=(Commns.byteArrayToHexString(ulen1));
				int utp1t1=Integer.parseInt(ugao, 16);
				byte[] ulen2=new byte[1];//低字节
				ulen2[0]=utp10[1];
				String udi=(Commns.byteArrayToHexString(ulen2));
				int utp1t2=Integer.parseInt(udi, 16);
				if(utp1t1<=80){
					float utp1=(float)utp1t2/10+(float)utp1t1*256/10;
					map.put("tp7", utp1);
				}else{
					float utp1=-(((float)utp1t1-80)*256+(float)utp1t2)/10;
					map.put("tp7", utp1);
				}
				i=i+2;
				//温度8
				byte[]itp10=Commns.subBytes(word,i,2); 
				byte[] ilen1=new byte[1];//高字节
				ilen1[0]=itp10[0];
				String igao=(Commns.byteArrayToHexString(ilen1));
				int itp1t1=Integer.parseInt(igao, 16);
				byte[] ilen2=new byte[1];//低字节
				ilen2[0]=itp10[1];
				String idi=(Commns.byteArrayToHexString(ilen2));
				int itp1t2=Integer.parseInt(idi, 16);
				if(itp1t1<=80){
					float itp1=(float)itp1t2/10+(float)itp1t1*256/10;
					map.put("tp8", itp1);
				}else{
					float itp1=-(((float)itp1t1-80)*256+(float)itp1t2)/10;
					map.put("tp8", itp1);
				}
				i=i+2;
				//温度9
				byte[]otp10=Commns.subBytes(word,i,2); 
				byte[] olen1=new byte[1];//高字节
				olen1[0]=otp10[0];
				String ogao=(Commns.byteArrayToHexString(olen1));
				int otp1t1=Integer.parseInt(ogao, 16);
				byte[] olen2=new byte[1];//低字节
				olen2[0]=otp10[1];
				String odi=(Commns.byteArrayToHexString(olen2));
				int otp1t2=Integer.parseInt(odi, 16);
				if(otp1t1<=80){
					float otp1=(float)otp1t2/10+(float)otp1t1*256/10;
					map.put("tp9", otp1);
				}else{
					float otp1=-(((float)otp1t1-80)*256+(float)otp1t2)/10;
					map.put("tp9", otp1);
				}
				i=i+2;
				map.put("id1", id1);
				map.put("id2", id2);
				map.put("type",81.0F);
				//System.out.println(id1+","+id2);
				return map;
			}
			if(length==36){
				//温度1
				int i=5;
				byte[]tp10=Commns.subBytes(word,i,2); 
				byte[] len1=new byte[1];//高字节
				len1[0]=tp10[0];
				String gao=(Commns.byteArrayToHexString(len1));
				int tp1t1=Integer.parseInt(gao, 16);
				byte[] len2=new byte[1];//低字节
				len2[0]=tp10[1];
				String di=(Commns.byteArrayToHexString(len2));
				int tp1t2=Integer.parseInt(di, 16);
				if(tp1t1<=80){
					float tp1=(float)tp1t2/10+(float)tp1t1*256/10;
					map.put("tp1", tp1);
				}else{
					float tp1=-(((float)tp1t1-80)*256+(float)tp1t2)/10;
					map.put("tp1", tp1);
				}
				i=i+2;
				//温度2
				byte[]qtp10=Commns.subBytes(word,i,2); 
				byte[] qlen1=new byte[1];//高字节
				qlen1[0]=qtp10[0];
				String qgao=(Commns.byteArrayToHexString(qlen1));
				int qtp1t1=Integer.parseInt(qgao, 16);
				byte[] qlen2=new byte[1];//低字节
				qlen2[0]=qtp10[1];
				String qdi=(Commns.byteArrayToHexString(qlen2));
				int qtp1t2=Integer.parseInt(qdi, 16);
				if(qtp1t1<=80){
					float qtp1=(float)qtp1t2/10+(float)qtp1t1*256/10;
					map.put("tp2", qtp1);
				}else{
					float qtp1=-(((float)qtp1t1-80)*256+(float)qtp1t2)/10;
					map.put("tp2", qtp1);
				}
				i=i+2;
				//温度3
				byte[]wtp10=Commns.subBytes(word,i,2); 
				byte[] wlen1=new byte[1];//高字节
				wlen1[0]=wtp10[0];
				String wgao=(Commns.byteArrayToHexString(wlen1));
				int wtp1t1=Integer.parseInt(wgao, 16);
				byte[] wlen2=new byte[1];//低字节
				wlen2[0]=wtp10[1];
				String wdi=(Commns.byteArrayToHexString(wlen2));
				int wtp1t2=Integer.parseInt(wdi, 16);
				if(wtp1t1<=80){
					float wtp1=(float)wtp1t2/10+(float)wtp1t1*256/10;
					map.put("tp3", wtp1);
				}else{
					float wtp1=-(((float)wtp1t1-80)*256+(float)wtp1t2)/10;
					map.put("tp3", wtp1);
				}
				i=i+2;
				//温度4
				byte[]rtp10=Commns.subBytes(word,i,2); 
				byte[] rlen1=new byte[1];//高字节
				rlen1[0]=rtp10[0];
				String rgao=(Commns.byteArrayToHexString(rlen1));
				int rtp1t1=Integer.parseInt(rgao, 16);
				byte[] rlen2=new byte[1];//低字节
				rlen2[0]=rtp10[1];
				String rdi=(Commns.byteArrayToHexString(rlen2));
				int rtp1t2=Integer.parseInt(rdi, 16);
				if(rtp1t1<=80){
					float rtp1=(float)rtp1t2/10+(float)rtp1t1*256/10;
					map.put("tp4", rtp1);
				}else{
					float rtp1=-(((float)rtp1t1-80)*256+(float)rtp1t2)/10;
					map.put("tp4", rtp1);
				}
				i=i+2;
				//温度5
				byte[]ttp10=Commns.subBytes(word,i,2); 
				byte[] tlen1=new byte[1];//高字节
				tlen1[0]=ttp10[0];
				String tgao=(Commns.byteArrayToHexString(tlen1));
				int ttp1t1=Integer.parseInt(tgao, 16);
				byte[] tlen2=new byte[1];//低字节
				tlen2[0]=ttp10[1];
				String tdi=(Commns.byteArrayToHexString(tlen2));
				int ttp1t2=Integer.parseInt(tdi, 16);
				if(ttp1t1<=80){
					float ttp1=(float)ttp1t2/10+(float)ttp1t1*256/10;
					map.put("tp5", ttp1);
				}else{
					float ttp1=-(((float)ttp1t1-80)*256+(float)ttp1t2)/10;
					map.put("tp5", ttp1);
				}
				i=i+2;
				//温度6
				byte[]ytp10=Commns.subBytes(word,i,2); 
				byte[] ylen1=new byte[1];//高字节
				ylen1[0]=ytp10[0];
				String ygao=(Commns.byteArrayToHexString(ylen1));
				int ytp1t1=Integer.parseInt(ygao, 16);
				byte[] ylen2=new byte[1];//低字节
				ylen2[0]=ytp10[1];
				String ydi=(Commns.byteArrayToHexString(ylen2));
				int ytp1t2=Integer.parseInt(ydi, 16);
				if(ytp1t1<=80){
					float ytp1=(float)ytp1t2/10+(float)ytp1t1*256/10;
					map.put("tp6", ytp1);
				}else{
					float ytp1=-(((float)ytp1t1-80)*256+(float)ytp1t2)/10;
					map.put("tp6", ytp1);
				}
				i=i+2;
				//温度7
				byte[]utp10=Commns.subBytes(word,i,2); 
				byte[] ulen1=new byte[1];//高字节
				ulen1[0]=utp10[0];
				String ugao=(Commns.byteArrayToHexString(ulen1));
				int utp1t1=Integer.parseInt(ugao, 16);
				byte[] ulen2=new byte[1];//低字节
				ulen2[0]=utp10[1];
				String udi=(Commns.byteArrayToHexString(ulen2));
				int utp1t2=Integer.parseInt(udi, 16);
				if(utp1t1<=80){
					float utp1=(float)utp1t2/10+(float)utp1t1*256/10;
					map.put("tp7", utp1);
				}else{
					float utp1=-(((float)utp1t1-80)*256+(float)utp1t2)/10;
					map.put("tp7", utp1);
				}
				i=i+2;
				//温度8
				byte[]itp10=Commns.subBytes(word,i,2); 
				byte[] ilen1=new byte[1];//高字节
				ilen1[0]=itp10[0];
				String igao=(Commns.byteArrayToHexString(ilen1));
				int itp1t1=Integer.parseInt(igao, 16);
				byte[] ilen2=new byte[1];//低字节
				ilen2[0]=itp10[1];
				String idi=(Commns.byteArrayToHexString(ilen2));
				int itp1t2=Integer.parseInt(idi, 16);
				if(itp1t1<=80){
					float itp1=(float)itp1t2/10+(float)itp1t1*256/10;
					map.put("tp8", itp1);
				}else{
					float itp1=-(((float)itp1t1-80)*256+(float)itp1t2)/10;
					map.put("tp8", itp1);
				}
				i=i+2;
				//温度9
				byte[]otp10=Commns.subBytes(word,i,2); 
				byte[] olen1=new byte[1];//高字节
				olen1[0]=otp10[0];
				String ogao=(Commns.byteArrayToHexString(olen1));
				int otp1t1=Integer.parseInt(ogao, 16);
				byte[] olen2=new byte[1];//低字节
				olen2[0]=otp10[1];
				String odi=(Commns.byteArrayToHexString(olen2));
				int otp1t2=Integer.parseInt(odi, 16);
				if(otp1t1<=80){
					float otp1=(float)otp1t2/10+(float)otp1t1*256/10;
					map.put("tp9", otp1);
				}else{
					float otp1=-(((float)otp1t1-80)*256+(float)otp1t2)/10;
					map.put("tp9", otp1);
				}
				i=i+2;
				//A相电压
				byte[]ptp10=Commns.subBytes(word,i,2); 
				byte[] plen1=new byte[1];//高字节
				plen1[0]=ptp10[0];
				String pgao=(Commns.byteArrayToHexString(plen1));
				int ptp1t1=Integer.parseInt(pgao, 16);
				byte[] plen2=new byte[1];//低字节
				plen2[0]=ptp10[1];
				String pdi=(Commns.byteArrayToHexString(plen2));
				int ptp1t2=Integer.parseInt(pdi, 16);
					float ptp1=((float)ptp1t1*256+(float)ptp1t2)/100;
					map.put("va", ptp1);
					i=i+2;
			    //b相电压
					byte[]atp10=Commns.subBytes(word,i,2); 
					byte[] alen1=new byte[1];//高字节
					alen1[0]=atp10[0];
					String agao=(Commns.byteArrayToHexString(alen1));
					int atp1t1=Integer.parseInt(agao, 16);
					byte[] alen2=new byte[1];//低字节
					alen2[0]=atp10[1];
					String adi=(Commns.byteArrayToHexString(alen2));
					int atp1t2=Integer.parseInt(adi, 16);
						float atp1=((float)atp1t1*256+(float)atp1t2)/100;
						map.put("vb", atp1);
						i=i+2;
				 //c相电压
						byte[]stp10=Commns.subBytes(word,i,2); 
						byte[] slen1=new byte[1];//高字节
						slen1[0]=stp10[0];
						String sgao=(Commns.byteArrayToHexString(slen1));
						int stp1t1=Integer.parseInt(sgao, 16);
						byte[] slen2=new byte[1];//低字节
						slen2[0]=stp10[1];
						String sdi=(Commns.byteArrayToHexString(slen2));
						int stp1t2=Integer.parseInt(sdi, 16);
						float stp1=((float)stp1t1*256+(float)stp1t2)/100;
							map.put("vc", stp1);
							i=i+2;
				//A相电流
				byte[]di0=Commns.subBytes(word,i,2);
				byte[] dalen1=new byte[1];//高字节
				dalen1[0]=di0[1];
				String dagao=(Commns.byteArrayToHexString(dalen1));
				int datp1t1=Integer.parseInt(dagao, 16);
				byte[] dalen2=new byte[1];//低字节
				dalen2[0]=di0[0];
				String dadi=(Commns.byteArrayToHexString(dalen2));
				int datp1t2=Integer.parseInt(dadi, 16);
				i=i+2;
				byte[]fi01=Commns.subBytes(word,i,2);
				byte[] fdalen1=new byte[1];//高字节
				fdalen1[0]=fi01[1];
				String fdagao=(Commns.byteArrayToHexString(fdalen1));
				int fdatp1t1=Integer.parseInt(fdagao, 16);
				byte[] fdalen2=new byte[1];//低字节
				fdalen2[0]=fi01[0];
				String fdadi=(Commns.byteArrayToHexString(fdalen2));
				int fdatp1t2=Integer.parseInt(fdadi, 16);
					float ia=(float)(fdatp1t2*256*256*256+fdatp1t1*256*256+datp1t2*256+datp1t1)/1000;
					map.put("ia", ia);
					i=i+2;
				//B相电流
					byte[]gdi0=Commns.subBytes(word,i,2);
					byte[] gdalen1=new byte[1];//高字节
					gdalen1[0]=gdi0[1];
					String gdagao=(Commns.byteArrayToHexString(gdalen1));
					int gdatp1t1=Integer.parseInt(gdagao, 16);
					byte[] gdalen2=new byte[1];//低字节
					gdalen2[0]=gdi0[0];
					String gdadi=(Commns.byteArrayToHexString(gdalen2));
					int gdatp1t2=Integer.parseInt(gdadi, 16);
					i=i+2;
					byte[]hfi01=Commns.subBytes(word,i,2);
					byte[] hfdalen1=new byte[1];//高字节
					hfdalen1[0]=hfi01[1];
					String hfdagao=(Commns.byteArrayToHexString(hfdalen1));
					int hfdatp1t1=Integer.parseInt(hfdagao, 16);
					byte[] hfdalen2=new byte[1];//低字节
					hfdalen2[0]=hfi01[0];
					String hfdadi=(Commns.byteArrayToHexString(hfdalen2));
					int hfdatp1t2=Integer.parseInt(hfdadi, 16);
						float ib=(float)(hfdatp1t2*256*256*256+hfdatp1t1*256*256+gdatp1t2*256+gdatp1t1)/1000;
						map.put("ib", ib);
						i=i+2;
						//System.out.print(i);
				//C相电流
						byte[]jdi0=Commns.subBytes(word,i,2);
						byte[] jdalen1=new byte[1];//高字节
						jdalen1[0]=jdi0[1];
						String jdagao=(Commns.byteArrayToHexString(jdalen1));
						int jdatp1t1=Integer.parseInt(jdagao, 16);
						byte[] jdalen2=new byte[1];//低字节
						jdalen2[0]=jdi0[0];
						String jdadi=(Commns.byteArrayToHexString(jdalen2));
						int jdatp1t2=Integer.parseInt(jdadi, 16);
						i=i+2;
						byte[]kfi01=Commns.subBytes(word,i,2);
						byte[] kfdalen1=new byte[1];//高字节
						kfdalen1[0]=kfi01[1];
						String kfdagao=(Commns.byteArrayToHexString(kfdalen1));
						int kfdatp1t1=Integer.parseInt(kfdagao, 16);
						byte[] kfdalen2=new byte[1];//低字节
						kfdalen2[0]=kfi01[0];
						String kfdadi=(Commns.byteArrayToHexString(kfdalen2));
						int kfdatp1t2=Integer.parseInt(kfdadi, 16);
						i=i+2;
							float ic=(float)(kfdatp1t2*256*256*256+kfdatp1t1*256*256+jdatp1t2*256+jdatp1t1)/1000;
							map.put("ic", ic);
							map.put("id1", id1);
							map.put("id2", id2);
							map.put("type",71.0F);
							//System.out.println(id1+","+id2);
			}else{
				return null;
			}return map;
		}else{
		return null;}
	 }

	 public static Map<String, String> ctrlReceiveWords (byte[] word){
		 Map<String, String> map=new HashMap<String,String>();
		 String id1= String.valueOf(Integer.parseInt(Commns.byteToHexString(word[1]),16));
		 String word1=(Commns.byteArrayToHexString(word));
		 if("02".equals(id1)){//复位
			 map.put("key", id1);
			 map.put("word", word1);
			 return map;
		 }else if("03".equals(id1)){//设置阈值
			 map.put("key", id1);
			 map.put("word", word1);
			 return map;
		 }else if("04".equals(id1)){//查询阈值
		 String maxtp= String.valueOf(Integer.parseInt(Commns.byteToHexString(word[5]),16));
		 byte[] I=new byte[2];
		 I[0]=word[6];
		 I[1]=word[7];
		 int iS=Integer.parseInt(Commns.byteArrayToHexString(I));
		 String maxi=Integer.toHexString(iS);
		 String v1=String.valueOf(Integer.parseInt(Commns.byteToHexString(word[6]),16));
		 String v2=String.valueOf(Integer.parseInt(Commns.byteToHexString(word[7]),16));
		 String  v=String.valueOf(Integer.parseInt(v1+v2));
		 String mixv=String.valueOf(Integer.parseInt(Commns.byteToHexString(word[8]),16));
		 String maxv=String.valueOf(Integer.parseInt(Commns.byteToHexString(word[9]),16));
		 map.put("maxtp", maxtp);
		 map.put("maxi",maxi);
		 map.put("mixv", mixv);
		 map.put("maxv", maxv);
		 map.put("key", id1);
		 map.put("word", word1);
		 return map;
		 }else if("05".equals(id1)){//开关
			 map.put("key", id1);
			 map.put("word", word1); 
		 }else if("06".equals(id1)){//查询开关
			 map.put("key", id1);
			 map.put("word", word1);
			 String state=String.valueOf(Integer.parseInt(Commns.byteToHexString(word[5]),16));
			 map.put("state", state);
		 }
		 return null;

	 
	 
	

}
}