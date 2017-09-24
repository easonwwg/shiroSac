package com.sac.commons;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;

/**
 * 编码，防止下载乱码
 * Created by EAISON on 2017/9/24.
 */
public class HttpFileEncodeTool {

    /**
     * 根据不同浏览器将文件名中的汉字转为UTF8编码的串,以便下载时能正确显示另存的文件名.
     * @param request
     * @param fileName 原文件名
     * @return 返回的文件名
     */
    public static String toUtf8String(HttpServletRequest request, String fileName) {
        String agent = request.getHeader("User-Agent");
        try {
            boolean isFireFox = (agent != null && agent.toLowerCase().indexOf("firefox") != -1);
            if (isFireFox) {
                fileName = new String(fileName.getBytes("UTF-8"), "ISO8859-1");
            } else {
                fileName = toUtf8String(fileName);
                if ((agent != null && agent.indexOf("MSIE") != -1)) {
                    // see http://support.microsoft.com/default.aspx?kbid=816868
                    if (fileName.length() > 150) {
                        // 根据request的locale 得出可能的编码
                        fileName = new String(fileName.getBytes("UTF-8"), "ISO8859-1");
                    }
                }
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return fileName;
    }

    /**
     * 编码
     * @param s
     * @return
     */
    public static String toUtf8String(String s) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c >= 0 && c <= 255) {
                sb.append(c);
            } else {
                byte[] b;
                try {
                    b = Character.toString(c).getBytes("utf-8");
                } catch (Exception ex) {
                    System.out.println(ex);
                    b = new byte[0];
                }
                for (int j = 0; j < b.length; j++) {
                    int k = b[j];
                    if (k < 0)
                        k += 256;
                    sb.append("%" + Integer.toHexString(k).toUpperCase());
                }
            }
        }
        return sb.toString();
    }
}
