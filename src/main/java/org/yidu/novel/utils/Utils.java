package org.yidu.novel.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.security.MessageDigest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.yidu.novel.constant.YiDuConfig;
import org.yidu.novel.constant.YiDuConstants;

public class Utils {

    protected static Log logger = LogFactory.getLog(Utils.class);

    public static String convert2MD5(final String input) {
        char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };
        try {
            byte[] btInput = input.getBytes();
            // 获得MD5摘要算法的 MessageDigest 对象
            MessageDigest mdInst = MessageDigest.getInstance("MD5");
            // 使用指定的字节更新摘要
            mdInst.update(btInput);
            // 获得密文
            byte[] md = mdInst.digest();
            // 把密文转换成十六进制的字符串形式
            int j = md.length;
            char str[] = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte byte0 = md[i];
                str[k++] = hexDigits[byte0 >>> 4 & 0xf];
                str[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(str).toLowerCase();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 取得章节信息
     * 
     * @param articleno
     * @param chapterno
     * @return 章节内容
     */
    public static String getContext(int articleno, int chapterno, boolean escape) {
        StringBuilder sb = new StringBuilder();
        String path = YiDuConstants.yiduConf.getString(YiDuConfig.FILE_PATH);

        // path = ServletActionContext.getServletContext().getRealPath("/") +
        // "/" + path + "/" + articleno / 1000 + "/"
        // + articleno + "/" + chapterno + ".txt";

        path = path + "/" + articleno / 1000 + "/" + articleno + "/" + chapterno + ".txt";

        File file = new File(path);
        try {
            if (file.isFile() && file.exists()) {
                // 判断文件是否存在
                InputStreamReader read = new InputStreamReader(new FileInputStream(file), YiDuConstants.ENCODING_GBK);
                BufferedReader bufferedReader = new BufferedReader(read);
                String lineTxt = null;
                while ((lineTxt = bufferedReader.readLine()) != null) {
                    sb.append(lineTxt);
                    if (escape) {
                        sb.append("<br/>");
                    } else {
                        sb.append("\n");
                    }
                }
                bufferedReader.close();
                read.close();
                if (escape) {
                    return sb.toString().replaceAll("\\s", "&nbsp;");
                } else {
                    return sb.toString();
                }
            } else {
                logger.info("can not find chapter.articleno:" + articleno + "chapterno:" + chapterno);
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return null;
    }

    public static void saveContext(int articleno, int chapterno, String content) {
        String path = YiDuConstants.yiduConf.getString(YiDuConfig.FILE_PATH);
        path = path + "/" + articleno / 1000 + "/" + articleno + "/" + chapterno + ".txt";
        File file = new File(path);
        FileWriter filewriter = null;
        try {
            filewriter = new FileWriter(file);
            filewriter.write(content);
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        } finally {
            if (filewriter != null) {
                try {
                    filewriter.close();
                } catch (IOException e) {
                }
            }
        }
    }

    public static String getContentFromUri(String uri) {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        try {
            HttpGet httpget = new HttpGet(uri);
            ResponseHandler<String> responseHandler = new ResponseHandler<String>() {
                public String handleResponse(final HttpResponse response) throws ClientProtocolException, IOException {
                    int status = response.getStatusLine().getStatusCode();
                    if (status >= 200 && status < 300) {
                        HttpEntity entity = response.getEntity();
                        return entity != null ? EntityUtils.toString(entity) : null;
                    } else {
                        throw new ClientProtocolException("Unexpected response status: " + status);
                    }
                }
            };
            return httpclient.execute(httpget, responseHandler);
        } catch (Exception e) {
            logger.error(e);
        } finally {
            try {
                httpclient.close();
            } catch (IOException e) {
                logger.error(e);
            }
        }
        return null;
    }
}
