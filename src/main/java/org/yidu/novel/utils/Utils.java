package org.yidu.novel.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.security.MessageDigest;
import java.util.Collection;
import java.util.Random;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
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
import org.apache.struts2.ServletActionContext;
import org.yidu.novel.constant.YiDuConfig;
import org.yidu.novel.constant.YiDuConstants;
import org.yidu.novel.entity.TChapter;

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
    public static String getContext(TChapter chapter, boolean escape) {
        return getContext(chapter, escape, false);
    }

    /**
     * 取得章节信息
     * 
     * @param articleno
     * @param chapterno
     * @param pseudo
     *            是否进行伪原创
     * @return 章节内容
     */
    public static String getContext(TChapter chapter, boolean escape, boolean pseudo) {

        String result = null;

        StringBuilder sb = new StringBuilder();
        String path = getTextFilePathByChapterno(chapter.getArticleno(), chapter.getChapterno());

        File file = new File(path);
        try {
            if (file.isFile() && file.exists()) {
                // 判断文件是否存在
                InputStreamReader read = new InputStreamReader(new FileInputStream(file),
                        YiDuConstants.yiduConf.getString(YiDuConfig.TXT_ENCODING));
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
                    result = sb.toString().replaceAll("\\s", "&nbsp;");
                } else {
                    result = sb.toString();
                }
                // 根据配置决定是否采用伪原创
                if (pseudo) {
                    if (YiDuConstants.pseudoConf.getBoolean("replace_keywords")) {
                        result = PseudoUtils.replaceKeywords(result);
                    }
                    if (YiDuConstants.pseudoConf.getBoolean("is_top_append")) {
                        result = PseudoUtils.topAppend(result);
                    }
                    if (YiDuConstants.pseudoConf.getBoolean("is_bottom_append")) {
                        result = PseudoUtils.bottomAppend(chapter, result);
                    }
                    if (YiDuConstants.pseudoConf.getBoolean("fetch_keywords_from_chapter")) {
                        result = PseudoUtils.fetchKeywords(result);
                    }
                }
            } else {
                logger.info("can not find chapter. articleno:" + chapter.getArticleno() + " chapterno:"
                        + chapter.getChapterno());
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return result;
    }

    public static String getTextFilePathByChapterno(int articleno, int chapterno) {
        String path = YiDuConstants.yiduConf.getString(YiDuConfig.FILE_PATH);

        // path = ServletActionContext.getServletContext().getRealPath("/") +
        // "/" + path + "/" + articleno / YiDuConstants.SUB_DIR_ARTICLES + "/"
        // + articleno + "/" + chapterno + ".txt";

        path = path + "/" + articleno / YiDuConstants.SUB_DIR_ARTICLES + "/" + articleno + "/" + chapterno + ".txt";
        return path;
    }

    public static String getTextDirectoryPathByArticleno(int articleno) {
        String path = YiDuConstants.yiduConf.getString(YiDuConfig.FILE_PATH);
        // path = ServletActionContext.getServletContext().getRealPath("/") +
        // "/" + path + "/" + articleno / YiDuConstants.SUB_DIR_ARTICLES + "/"
        // + articleno + "/" + chapterno + ".txt";
        path = path + "/" + articleno / YiDuConstants.SUB_DIR_ARTICLES + "/" + articleno + "/";
        return path;
    }

    public static String getImgDirectoryPathByArticleno(int articleno) {
        String path = YiDuConstants.yiduConf.getString(YiDuConfig.RELATIVE_IAMGE_PATH);
        path = ServletActionContext.getServletContext().getRealPath("/") + "/" + path + "/" + articleno
                / YiDuConstants.SUB_DIR_ARTICLES + "/" + articleno + "/";
        return path;
    }

    /**
     * 保存文件
     * 
     * @param articleno
     * @param chapterno
     * @param content
     * @throws IOException
     */
    public static void saveContext(int articleno, int chapterno, String content) throws IOException {
        String path = getTextFilePathByChapterno(articleno, chapterno);
        File file = new File(path);

        File parentPath = file.getParentFile();
        if (!parentPath.exists()) {
            parentPath.mkdirs();
        }
        try {
            OutputStreamWriter outputStream = new OutputStreamWriter(new FileOutputStream(file),
                    YiDuConstants.yiduConf.getString(YiDuConfig.TXT_ENCODING));
            outputStream.write(content);
            outputStream.flush();
            outputStream.close();

        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        }
    }

    /**
     * 取得指定URI内容
     * 
     * @param uri
     * @return 页面内容
     */
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

    /**
     * 删除目录（文件夹）以及目录下的文件
     * 
     * @param sPath
     *            被删除目录的文件路径
     * @return 目录删除成功返回true，否则返回false
     */
    public static boolean deleteDirectory(String sPath) {

        // 如果sPath不以文件分隔符结尾，自动添加文件分隔符
        if (!sPath.endsWith(File.separator)) {
            sPath = sPath + File.separator;
        }
        File dirFile = new File(sPath);
        // 如果dir对应的文件不存在，或者不是一个目录，则退出
        if (!dirFile.exists() || !dirFile.isDirectory()) {
            return false;
        }
        boolean flag = true;
        // 删除文件夹下的所有文件(包括子目录)
        File[] files = dirFile.listFiles();
        for (int i = 0; i < files.length; i++) {
            // 删除子文件
            if (files[i].isFile()) {
                flag = deleteFile(files[i].getAbsolutePath());
                if (!flag)
                    break;
            } // 删除子目录
            else {
                flag = deleteDirectory(files[i].getAbsolutePath());
                if (!flag)
                    break;
            }
        }
        if (!flag)
            return false;
        // 删除当前目录
        if (dirFile.delete()) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 删除单个文件
     * 
     * @param sPath
     *            被删除文件的文件名
     * @return 单个文件删除成功返回true，否则返回false
     */
    public static boolean deleteFile(String sPath) {
        boolean flag = false;
        File file = new File(sPath);
        // 路径为文件且不为空则进行删除
        if (file.isFile() && file.exists()) {
            file.delete();
            flag = true;
        }
        return flag;
    }

    public static void saveArticlespic(int articleno, File file, String fileName) throws Exception {
        String path = YiDuConstants.yiduConf.getString(YiDuConfig.RELATIVE_IAMGE_PATH);
        path = ServletActionContext.getServletContext().getRealPath("/") + "/" + path + "/" + articleno
                / YiDuConstants.SUB_DIR_ARTICLES + "/" + articleno + "/" + articleno + "s."
                + StringUtils.substringAfterLast(fileName, ".");
        File savefile = new File(path);
        if (!savefile.getParentFile().exists()) {
            savefile.getParentFile().mkdirs();
        }
        FileUtils.copyFile(file, savefile);
    }

    public static String getArticlePicPath(int articleno) {
        String path = YiDuConstants.yiduConf.getString(YiDuConfig.RELATIVE_IAMGE_PATH);
        path = ServletActionContext.getServletContext().getRealPath("/") + "/" + path + "/" + articleno
                / YiDuConstants.SUB_DIR_ARTICLES + "/" + articleno + "/";
        return path;
    }

    /**
     * * 将汉字转换为全拼 * *
     * 
     * @param src
     * @return String
     */
    public static String getPinYin(String src) {

        char[] charArray = null;
        charArray = src.toCharArray();
        String[] strArr = new String[charArray.length];
        // 设置汉字拼音输出的格式
        HanyuPinyinOutputFormat pinyinFormat = new HanyuPinyinOutputFormat();
        pinyinFormat.setCaseType(HanyuPinyinCaseType.UPPERCASE);
        pinyinFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
        pinyinFormat.setVCharType(HanyuPinyinVCharType.WITH_V);
        String retStr = "";
        try {
            for (int i = 0; i < charArray.length; i++) {
                // 判断是否为汉字字符
                if (Character.toString(charArray[i]).matches("[\\u4E00-\\u9FA5]+")) {
                    strArr = PinyinHelper.toHanyuPinyinStringArray(charArray[i], pinyinFormat);
                    // 将汉字的几种全拼都存到t2数组中
                    retStr += strArr[0];
                    // 取出该汉字全拼的第一种读音并连接到字符串retStr后
                } else {
                    // 如果不是汉字字符，直接取出字符并连接到字符串retStr后
                    retStr += Character.toString(charArray[i]);
                }
            }
        } catch (BadHanyuPinyinOutputFormatCombination e) {
            e.printStackTrace();
        }
        return retStr;
    }

    public static boolean isDefined(Object obj) {
        if (obj instanceof Collection) {
            return CollectionUtils.isNotEmpty((Collection<?>) obj);
        }

        if (obj instanceof String) {
            return org.apache.commons.lang3.StringUtils.isNotEmpty((String) obj);
        }

        if (obj instanceof Integer) {
            return obj != null && (Integer) obj != 0;
        }

        return obj != null;
    }

    /**
     * 生成随机字符串
     * 
     * @param length
     *            字符串长度
     * @return 随机字符串
     */
    public static final String getRandomString(int length) {

        Random randGen = null;
        char[] numbersAndLetters = null;
        if (length < 1) {
            return null;
        }
        if (randGen == null) {
            randGen = new Random();
            numbersAndLetters = ("0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ!\"#$%&'()'")
                    .toCharArray();
        }
        char[] randBuffer = new char[length];
        for (int i = 0; i < randBuffer.length; i++) {
            randBuffer[i] = numbersAndLetters[randGen.nextInt(numbersAndLetters.length)];
        }
        return new String(randBuffer);
    }

}
