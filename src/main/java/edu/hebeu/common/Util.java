package edu.hebeu.common;

import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.lang.math.RandomUtils;
import org.apache.commons.lang.time.DateUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

public class Util {

    private static Properties properties = null;
    private static String encryptPwd = "SDFDSF234L23KSDUWLJFLSDFJ";
    private static DecimalFormat format = new DecimalFormat("0.00");


    /**
     * 格式化数字，保留两位小数
     */
    public static String formatNumber(Number number) {
        if (number == null) {
            return "";
        }
        return format.format(number.doubleValue());
    }

    /**
     * 格式化数字，保留两位小数
     */
    public static String formatNumber(Double number) {
        return format.format(number);
    }

    /**
     * 从map中取得指定key的内容,若key对应的内容不存在,则新建一个对象放入对应key中
     */
    public static <T> T getFromMap(Map map, Object key, Class<T> classT) {
        T result = (T) map.get(key);

        if (result == null) {
            try {
                result = classT.newInstance();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            map.put(key, result);
        }

        return result;
    }

    /**
     * 加载错误消息
     */
    public static void loadMessage() throws IOException {
        properties = new Properties();
        properties.load(new InputStreamReader(Util.class.getResourceAsStream("/message.properties"), "UTF-8"));
    }

    public static void main(String[] args) throws IOException {
    	Util.loadMessage();
    	for(Enumeration e = properties.propertyNames();e.hasMoreElements();) {
    		String key = (String)e.nextElement();
    		System.out.println(key +" = "+ properties.getProperty(key) );
    	}
	}
    
    
    /**
     * BigDecimal是null时,返回0,否则返回原值
     */
    public static BigDecimal nullToZero(BigDecimal otherPayAmount) {
        return otherPayAmount == null ? BigDecimal.ZERO : otherPayAmount;
    }

    /**
     * 根据消息key和消息参数生成最终消息
     */
    public static String getMessage(String key, String... argv) {
        // 消息文件未加载时, 先加载
        if (properties == null) {
            try {
                loadMessage();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        String message = properties.getProperty(key);

        // 有消息参数时,设置参数值到消息中
        if (argv != null) {
            for (String arg : argv) {
                message = message.replaceFirst("\\{[0-9]*\\}", arg);
            }
        }

        return message;
    }

    /**
     * 计算首条记录位置
     */
    public static int calFirst(int page, int size) {
        int first = (page - 1) * size;

        return first < 0 ? 0 : first;
    }

    /**
     * 验证登录密码
     */
//    public static void checkPassword(String password, String passwordSigned) {
//        if (!StringUtils.equalsIgnoreCase(DigestUtils.md5Hex(password), passwordSigned)) {
//            throw new ApiException(MessageCode.CODE_LOGIN_ERROR); // 需要改成 throw new ApiException(MessageCode.CODE_PASSWORD_ERROR);
//        }
//    }

    /**
     * 加密
     *
     * @param datasource byte[]
     * @return byte[]
     */
//    public static String encrypt(String datasource) {
//        return encrypt(datasource, encryptPwd);
//    }

    /**
     * 加密
     *
     * @param datasource byte[]
     * @param password   String
     * @return byte[]
     */
//    public static String encrypt(String datasource, String password) {
//        try {
//            int passwordLength = password.length() + (8 - password.length() % 8);
//            password = StringUtils.leftPad(password, passwordLength, '0');
//
//            SecureRandom random = new SecureRandom();
//            DESKeySpec desKey = new DESKeySpec(password.getBytes("UTF-8"));
//            //创建一个密匙工厂，然后用它把DESKeySpec转换成
//            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
//            SecretKey securekey = keyFactory.generateSecret(desKey);
//            //Cipher对象实际完成加密操作
//            Cipher cipher = Cipher.getInstance("DES");
//            //用密匙初始化Cipher对象
//            cipher.init(Cipher.ENCRYPT_MODE, securekey, random);
//            //现在，获取数据并加密
//            //正式执行加密操作
//            byte[] result = cipher.doFinal(datasource.getBytes("UTF-8"));
//
//            return Base64.encodeBase64String(result);
//        } catch (Throwable e) {
//            e.printStackTrace();
//        }
//
//        return null;
//    }

    /**
     * 解密
     *
     * @param src Base64字符串
     * @return byte[]
     */
//    public static String decrypt(String src) {
//        return decrypt(src, encryptPwd);
//    }

    /**
     * 解密
     *
     * @param src      Base64字符串
     * @param password String
     * @return byte[]
     * @throws Exception
     */
//    public static String decrypt(String src, String password) {
//        try {
//            int passwordLength = password.length() + (8 - password.length() % 8);
//            password = StringUtils.leftPad(password, passwordLength, '0');
//
//            // DES算法要求有一个可信任的随机数源
//            SecureRandom random = new SecureRandom();
//            // 创建一个DESKeySpec对象
//            DESKeySpec desKey = new DESKeySpec(password.getBytes("UTF-8"));
//            // 创建一个密匙工厂
//            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
//            // 将DESKeySpec对象转换成SecretKey对象
//            SecretKey securekey = keyFactory.generateSecret(desKey);
//            // Cipher对象实际完成解密操作
//            Cipher cipher = Cipher.getInstance("DES");
//            // 用密匙初始化Cipher对象
//            cipher.init(Cipher.DECRYPT_MODE, securekey, random);
//            // 真正开始解密操作
//            return new String(cipher.doFinal(Base64.decodeBase64(src)), "UTF-8");
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        return null;
//    }

    /**
     * 生成验证码
     */
    public static String genCaptcha(int length) {
        String captcha = "";

        for (int i = 0; i < length; i++) {
            captcha += RandomUtils.nextInt(10);
        }

        return captcha;
    }

    /**
     * 取指定日期的最后一秒
     */
    public static Date getEndOfDay(Date expirationAt) {
        Date result = DateUtils.truncate(expirationAt, Calendar.DATE);
        result = DateUtils.addDays(result, 1);
        result = DateUtils.addSeconds(result, -1);

        return result;
    }

    /**
     * 四舍五入
     */
    public static Double round(Number value) {
        if (value == null) {
            return null;
        }

        return new BigDecimal(value.doubleValue()).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    /**
     * 四舍五入,保留两位小数
     */
    public static BigDecimal roundToBigDecimal(Number value) {
        if (value == null) {
            return null;
        }

        return new BigDecimal(value.doubleValue()).setScale(2, BigDecimal.ROUND_HALF_UP);
    }

    /**
     * 四舍五入成整数
     */
    public static Integer roundToInteger(Number value) {
        if (value == null) {
            return null;
        }

        return new BigDecimal(value.doubleValue()).setScale(0, BigDecimal.ROUND_HALF_UP).intValue();
    }

    /**
     * 验证集合
     */
    public static void validCollection(Validator validator, Collection collection, Errors errors) {
        for (Object object : collection) {
            ValidationUtils.invokeValidator(validator, object, errors);
        }
    }

    /**
     * 生成随机数
     */
    public static Integer getRandom(int size) {
        Double b = Math.random();
        b = b * size;
        return b.intValue();
    }

    /**
     * 生成随机数
     */
    public static List<Integer> getRandomList(int size, int num) {
        Map<Integer, Integer> map = new HashMap<>();
        if (size <= num) {
            while (map.size() < size) {
                int i = ((Double) (Math.random() * size)).intValue();
                map.put(i, i);
            }
        } else {
            while (map.size() < num) {
                int i = ((Double) (Math.random() * size)).intValue();
                map.put(i, i);
            }
        }
        return new ArrayList<>(map.keySet());
    }

    public static String string2Unicode(String string) {
        StringBuffer unicode = new StringBuffer();
        for (int i = 0; i < string.length(); i++) {
            // 取出每一个字符
            char c = string.charAt(i);
            // 转换为unicode
            unicode.append("\\u" + Integer.toHexString(c));
        }
        return unicode.toString();
    }

    public static String unicode2String(String unicode) {
        StringBuffer string = new StringBuffer();
        String[] hex = unicode.split("\\\\u");
        for (int i = 1; i < hex.length; i++) {
            // 转换出每一个代码点
            int data = Integer.parseInt(hex[i], 16);
            // 追加成string
            string.append((char) data);
        }
        return string.toString();
    }

    public static long getTotalSizeOfFilesInDir(File file) {
        if (file.isFile())
            return file.length();
        final File[] children = file.listFiles();
        long total = 0;
        if (children != null)
            for (final File child : children)
                total += getTotalSizeOfFilesInDir(child);
        return total;
    }
}
