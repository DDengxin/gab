package com.tengzhi.base.tools.file;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.system.OsInfo;
import cn.hutool.system.oshi.OshiUtil;
import com.tengzhi.base.tools.UUID.UUIDUtil;
import com.tengzhi.publicmethod.PropertiesUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.*;


/**
 * 公用文件处理类
 *
 * @author lqy
 */
public class FileUtil {
    private static Logger logger = Logger.getLogger(FileUtil.class);
    private static OsInfo osInfo = new OsInfo();
    /**
     * 默认上传【绝对】目录(default:/WEB-INF/upload)
     */
    private static String UPLOAD_ABS_PATH = String.format("%s%s%s%s", File.separator, "WEB-INF", File.separator,
            "upload");
    /**
     * 默认上传转换文件【相对】目录(default:/upload)
     */
    private static String UPLOAD_CONVERT_REAL_PATH = String.format("%s%s", File.separator, "upload");
    /**
     * 默认上传【绝对】目录(default window:C:/upload,linux:/upload)
     */
    private static String BACKUP_ABS_PATH =  String.format("%s%s%s", osInfo.isLinux() ? File.separator: "c:" , File.separator, UPLOAD_ABS_PATH);
    /**
     * 默认转换后文件HTML显示标题
     */
    private static String UPLOAD_CONVERT_TITLE = "腾智软件";

    /**
     * 最大TEXT预览大小
     */
    private static final int MAX_TEXT_PREVIEW_SIZE = 1024 * 1024 * 5;
    /**
     * 文本类型
     */
    public static final List<String> TEXT_SUFFIXS = Arrays.asList("txt", "html", "js", "css", "xml", "js", "c", "cpp",
            "go", "java", "java", "sql", "json", "markup", "markup", "cs", "git", "ini", "jsdoc", "less", "prel", "php",
            "plsql", "py", "r", "vim", "vb", "yml", "wiki");
    /**
     * office文档类型
     */
    public static final List<String> OFFICE_SUFFIXS = Arrays.asList("doc", "docx", "xlsx", "xls", "ppt", "pptx", "pdf");
    /**
     * 图片文件类型
     */
    public static final List<String> PICTURE_SUFFIXS = Arrays.asList("bmp", "jpg", "jpeg", "png", "gif");
    /**
     * 视频文件类型
     */
    public static final List<String> VIDEO_SUFFIXS = Arrays.asList("ogg", "mp4", "webm");
    /**
     * KKFILEVIEW能预览的文件类型
     */
    public static final List<String> KKFILE_VIEW_SUFFIXS = Arrays.asList("doc", "docx", "ppt", "pptx", "xls", "xlsx", "zip", "rar", "mp4", "mp3", "jpg", "jpeg", "png", "gif", "pdf");

    /**
     * 可预览的文件类型
     */
    public static List<String> PREVIEW_TYPES = new ArrayList<>();

    /**
     * 获取上传文件的根路径(绝对路径)
     *
     * @return
     */
    public static String getUploadAbsPath() {
        return UPLOAD_ABS_PATH;
    }

    /**
     * 获取上传文件通过工具转换后的根路径（绝对路径）
     *
     * @return
     */
    public static String getUploadConvertRealPath() {
        return UPLOAD_CONVERT_REAL_PATH;
    }

    /**
     * 获取上传文件通过工具转换后默认标题（绝对路径）
     *
     * @return
     */
    public static String getUploadConvertTitle() {
        return UPLOAD_CONVERT_TITLE;
    }

    /**
     * 获取备份文件根路径（绝对路径）
     *
     * @return
     */
    public static String getBackupAbsPath() {
        return BACKUP_ABS_PATH;
    }


    static {
        // 文件类型处理
        PREVIEW_TYPES.addAll(TEXT_SUFFIXS);
        PREVIEW_TYPES.addAll(OFFICE_SUFFIXS);
        PREVIEW_TYPES.addAll(PICTURE_SUFFIXS);
        PREVIEW_TYPES.addAll(VIDEO_SUFFIXS);

        // 读取上传路径配置
        String uploadPath = PropertiesUtil.getProperty("upload.path", UPLOAD_ABS_PATH);
        try {
            UPLOAD_ABS_PATH = handlerDir(getWebRootAbsolutePath(), uploadPath, "文件上传路径");
        } catch (Exception e) {
            UPLOAD_ABS_PATH = null;
        }
        String backupPath = PropertiesUtil.getProperty("upload.backup", BACKUP_ABS_PATH);
        try {
            BACKUP_ABS_PATH = handlerDir(null, backupPath, "文件备份路径");
        } catch (Exception e) {
            BACKUP_ABS_PATH = null;

        }
        UPLOAD_CONVERT_REAL_PATH = PropertiesUtil.getProperty("upload.convert.path", UPLOAD_CONVERT_REAL_PATH);
        UPLOAD_CONVERT_TITLE = PropertiesUtil.getProperty("upload.convert.title", UPLOAD_CONVERT_TITLE);
    }

    /**
     * 初始化
     */
    public static FileUtil getInstance() {
        return FileUtilHolder.FileUtil;
    }

    /**
     * 获取上传文件的绝对地址
     *
     * @param uploadFilePath
     * @return
     */
    public static String getUploadFileAbsolutePath(String uploadFilePath) {
        return PathJoin(UPLOAD_ABS_PATH, uploadFilePath);
    }

    /**
     * 将图片文件流返回
     *
     * @param inputStream 文件输入流
     * @param response
     * @throws IOException
     */
    public void writeImage(InputStream inputStream, HttpServletResponse response) throws IOException {
        if (ObjectUtil.isNull(inputStream)) {
            return;
        } else {
            BufferedInputStream buf = null;
            ServletOutputStream out = null;
            try {
                buf = new BufferedInputStream(inputStream);
                out = response.getOutputStream();
                byte[] buffer = new byte[10000];
                while (buf.read(buffer) != -1) {
                    out.write(buffer);
                }
                out.flush();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (null != buf) {
                    buf.close();
                }
                if (null != out) {
                    out.close();
                }
            }
        }
    }

    /**
     * 将图片文件流返回
     *
     * @param path     文件路径(相对路径)
     * @param response
     * @throws IOException
     */
    public void writeImage(String path, HttpServletResponse response) throws IOException {
        String uploadFileAbsolutePath = FileUtil.PathJoin(FileUtil.getUploadAbsPath(), path);
        File file = new File(uploadFileAbsolutePath);
        if (!file.exists()) {
            return;
        }
        writeImage(new FileInputStream(path), response);
    }

    /**
     * 写入文件
     *
     * @param path 文件路径
     * @param file 文件对象
     * @throws IllegalStateException
     * @throws IOException
     */
    private void fileWrite(String path, String fileName, MultipartFile file) throws IllegalStateException, IOException {
        // 创建文件实例
        File filePath = new File(path, fileName);
        if (!filePath.getParentFile().exists()) {
            filePath.getParentFile().mkdirs();
        }
        file.transferTo(filePath);
    }

    /**
     * 文件上传
     *
     * @param file MultipartFile springmvc文件上传对象
     * @throws IllegalStateException
     * @throws IOException
     */
    @SuppressWarnings("unused")
    public Map<String, String> fileUpload(MultipartFile file) throws IllegalStateException, IOException {
        Map<String, String> map = new java.util.HashMap<String, String>();
        // 上传路径中间部分(exp:yyyyMMdd/uuid)
        String midPath = File.separator + new SimpleDateFormat("yyyy-MM-dd").format(new Date()) + File.separator
                + UUIDUtil.uuid();
        // 获取文件名
        String[] fileNames = getUploadFileName(file);
        String fileName = fileNames[0];
        String suffix = fileNames[2];
        // 写入上传目录以及备份目录
        fileWrite(PathJoin(UPLOAD_ABS_PATH, midPath), fileName, file);
        copy(UPLOAD_ABS_PATH + midPath + File.separator + fileName, BACKUP_ABS_PATH + midPath + File.separator + fileName);
//        fileWrite(PathJoin(BACKUP_ABS_PATH, midPath), fileName, file);
        map.put("path", PathJoin(midPath, fileName));
        map.put("suffix", suffix);
        map.put("fileName", fileName);
        return map;
    }

    /**
     * 刪除文件
     *
     * @param path           文件相对路径
     * @param isDeleteBackup 是否删除备份文件
     * @return
     * @throws IllegalStateException
     * @throws IOException
     */
    public boolean deletefile(String path, boolean isDeleteBackup) throws IllegalStateException {
        // 路径拼接
        String filePath = PathJoin(UPLOAD_ABS_PATH, path);
        String backupPath = PathJoin(BACKUP_ABS_PATH, path);
        // 删除文件
        boolean fileDeletedFlag = deletefile(filePath);
        if (isDeleteBackup) {
            boolean backupDeletedFlag = deletefile(backupPath);
            return backupDeletedFlag && fileDeletedFlag;
        }
        return fileDeletedFlag;
    }

    /**
     * 删除文件
     *
     * @param path 文件完整路径
     * @return
     * @throws IllegalStateException
     * @throws IOException
     */
    private boolean deletefile(String path) throws IllegalStateException {
        if (StringUtils.isNotEmpty(path)) {
            File file = new File(path);
            if (file.exists() && file.isFile()) {
                file.delete();
            }
            return true;
        } else {
            return false;
        }
    }

    /**
     * 获取下载链接
     *
     * @param request
     * @param response
     * @param filePath   需要下载的文件路径(相对路径)
     * @param fileName   文件名
     * @param fileSufixx 文件后缀名
     * @return
     */
    public boolean getDownloadResponse(HttpServletRequest request, HttpServletResponse response, String filePath, String fileName, String fileSufixx) {
        try {
            // 获取文件路径
            String uploadFileAbsolutePath = getUploadFileAbsolutePath(filePath);
            if (!cn.hutool.core.io.FileUtil.exist(uploadFileAbsolutePath)) {
                throw new RuntimeException("文件不存在");
            }
            // 传输文件
            request.setCharacterEncoding("UTF-8");
            BufferedInputStream bis = null;
            BufferedOutputStream bos = null;
            long fileLength = new File(uploadFileAbsolutePath).length();
            // 文件扩展名
            response.setHeader("Content-disposition", "attachment; filename=" + new String(fileName.getBytes("gb2312"), StandardCharsets.ISO_8859_1));
            response.setHeader("Content-Length", String.valueOf(fileLength));
            response.setContentType(FileContentTypes.getContentType(fileSufixx));

            bis = new BufferedInputStream(new FileInputStream(uploadFileAbsolutePath));
            bos = new BufferedOutputStream(response.getOutputStream());
            byte[] buff = new byte[2048];
            int bytesRead;
            while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
                bos.write(buff, 0, bytesRead);
            }
            bis.close();
            bos.close();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 获取下载链接
     *
     * @param request
     * @param response
     * @param filePath 需要下载的文件路径(相对路径)
     * @param fileName 文件名
     * @return
     */
    public boolean getDownloadResponse(HttpServletRequest request, HttpServletResponse response, String filePath, String fileName) {
        return getDownloadResponse(request, response, filePath, fileName, cn.hutool.core.io.FileUtil.extName(filePath));
    }

    /**
     * 获取下载链接
     *
     * @param request
     * @param response
     * @param filePath 需要下载的文件路径(相对路径)
     * @return
     */
    public boolean getDownloadResponse(HttpServletRequest request, HttpServletResponse response, String filePath) {
        return getDownloadResponse(request, response, filePath, cn.hutool.core.io.FileUtil.getName(filePath));
    }

    /*public boolean getDownloadResponse(HttpServletRequest request, HttpServletResponse response, SysUpload com_file) {
        try {
            // 获取文件路径
            String uploadFileAbsolutePath = getUploadFileAbsolutePath(com_file.getFile_path());
            if (!cn.hutool.core.io.FileUtil.exist(uploadFileAbsolutePath)) {
                throw new RuntimeException("文件不存在");
            }
            // 传输文件
            request.setCharacterEncoding("UTF-8");
            BufferedInputStream bis = null;
            BufferedOutputStream bos = null;
            long fileLength = new File(uploadFileAbsolutePath).length();
            // 文件扩展名
            response.setHeader("Content-disposition", "attachment; filename="
                    + new String(com_file.getFile_name().getBytes("gb2312"), StandardCharsets.ISO_8859_1));
            response.setHeader("Content-Length", String.valueOf(fileLength));
            String contenttype = getContentType(com_file.getFile_sufixx());
            if (null != contenttype) {
                response.setContentType(contenttype);
            }
            bis = new BufferedInputStream(new FileInputStream(uploadFileAbsolutePath));
            bos = new BufferedOutputStream(response.getOutputStream());
            byte[] buff = new byte[2048];
            int bytesRead;
            while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
                bos.write(buff, 0, bytesRead);
            }
            bis.close();
            bos.close();
            return true;
        } catch (Exception e) {
            return false;
        }
    }*/

    /**
     * 文件复制
     *
     * @param source
     * @param dest
     * @throws IOException
     */
    private void copyFileUsingFileStreams(File source, File dest) throws IOException {

        if (!dest.getParentFile().exists()) {
            dest.getParentFile().mkdirs();
        }
        InputStream input = null;
        OutputStream output = null;
        try {
            input = new FileInputStream(source);
            output = new FileOutputStream(dest);
            byte[] buf = new byte[1024];
            int bytesRead;
            while ((bytesRead = input.read(buf)) > 0) {
                output.write(buf, 0, bytesRead);
            }
        } finally {
            input.close();
            output.close();
        }
    }

    /**
     * 文件复制
     *
     * @param path      String
     * @param back_path String
     * @throws IOException
     */
    private void copy(String path, String back_path) throws IOException {
        File file = new File(path);
        File back_file = new File(back_path);
        copyFileUsingFileStreams(file, back_file);
    }

    /**
     * 转化附件
     * status  Y 转换完成，N已转换 X不能预览，V其他
     *
     * @param filePath        源文件路径(相对路径)
     * @param convertHtmlPath 转换后文件路径(相对路径)
     * @return
     */
    public Map<String, String> ConverterHtml(String filePath, String convertHtmlPath) {
        Map<String, String> map = new java.util.HashMap<String, String>();
        String sufixx = cn.hutool.core.io.FileUtil.extName(filePath);// 后缀

        String convertFilePath = PathJoin(getWebRootAbsolutePath(), convertHtmlPath);
        File file = new File(convertFilePath);

        if (StringUtils.isNotEmpty(convertFilePath) && !"null".equals(convertFilePath) && file.exists()) {
            // 已转化
            map.put("status", "N");
            map.put("path", convertFilePath);
        } else {
            // 未转化
            //上传文件绝对路径
            String uploadFileAbsolutePath = PathJoin(UPLOAD_ABS_PATH, filePath);
            //转换文件相对路径
            String convertFileRealPath = PathJoin(UPLOAD_CONVERT_REAL_PATH, filePath);
            convertFileRealPath = convertFileRealPath.substring(0, convertFileRealPath.indexOf(".")) + ".html";
            //转换文件绝对路径
            String convertFileAbsolutePath = PathJoin(getWebRootAbsolutePath(), convertFileRealPath);


            if (sufixx != null && OFFICE_SUFFIXS.contains(sufixx)) {
//                ConvertorPool.ConvertorObject convertobj = ConvertorPool.getInstance().getConvertor();
//                convertobj.convertor.setHtmlEncoding("UTF-8");
//                String fileName = com_file.getFile_name();
//                convertobj.convertor.setHtmlName(fileName.substring(0, fileName.lastIndexOf(".")));
//                convertobj.convertor.setHtmlTitle("腾智管理平台");
//                if (sufixx.equalsIgnoreCase("pdf")) {
//                    convertobj.convertor.convertPdfToHtml(uploadFileAbsolutePath, convertFileAbsolutePath);
//                } else {
//                    convertobj.convertor.convertMStoHtmlOfSvg(uploadFileAbsolutePath, convertFileAbsolutePath);
//                }
//                ConvertorPool.getInstance().returnConvertor(convertobj);
//                map.put("status", "Y");
//                //处理返回路径中的分隔符为url分隔符
//                map.put("path", convertFileRealPath.replace("\\\\","/"));
            } else {
                if (PREVIEW_TYPES.contains(sufixx)) {
                    map.put("status", "V");
                    map.put("path", "");
                    map.put("sufixx", sufixx);
                } else {
                    map.put("status", "X");
                    map.put("path", "");
                }
            }
        }
        return map;
    }

    /**
     * 读取Text文本
     *
     * @param filePath 文件路径(相对路径)
     * @return
     */
    public Map<String, String> readTxtFile(String filePath) {
        StringBuilder str = new StringBuilder();
        Map<String, String> map = new java.util.HashMap<String, String>();
        String uploadFileAbsolutePath = PathJoin(UPLOAD_ABS_PATH, filePath);
        try {
            String encoding = "UTF-8";
            File file = new File(uploadFileAbsolutePath);
            if (file.isFile() && file.exists()) { // 判断文件是否存在
                if (file.length() > MAX_TEXT_PREVIEW_SIZE) {
                    map.put("statas", "N");
                    map.put("msg", "文件过大,无法预览,建议自行下载后预览");
                } else {
                    InputStreamReader read = new InputStreamReader(new FileInputStream(file), encoding);// 考虑到编码格式
                    BufferedReader bufferedReader = new BufferedReader(read);
                    String lineTxt = null;
                    String lineSeparator = System.lineSeparator();
                    while ((lineTxt = bufferedReader.readLine()) != null) {
                        str.append(lineTxt).append(lineSeparator);

                    }
                    read.close();
                    map.put("statas", "Y");
                    map.put("data", str.toString());
                }

            } else {
                map.put("statas", "N");
                map.put("msg", "找不到指定的文件");
            }
        } catch (Exception e) {
            map.put("statas", "N");
            map.put("msg", "找不到指定的文件");
        }
        return map;
    }

    /**
     * 获取上传文件的名称,新文件名为原文件名加上时间戳
     *
     * @param multipartFile multipartFile
     * @return 文件名 (文件名(包含后缀),文件名(不包含后缀),后缀名(不带点))
     */
    protected String[] getUploadFileName(MultipartFile multipartFile) {
        // 原文件名(包含后缀)
        String uploadFileOriginalName = multipartFile.getOriginalFilename();
        if ("".equals(uploadFileOriginalName)) {
            uploadFileOriginalName = multipartFile.getName();
        }
        // 原文件名(无后缀名)
        String uploadFileOriginalNameWithOutSuffix = uploadFileOriginalName.substring(0, uploadFileOriginalName.lastIndexOf("."));
        // 后缀名(无点)
        String suffix = uploadFileOriginalName.substring(uploadFileOriginalName.lastIndexOf(".") + 1);
        return new String[]{uploadFileOriginalName, uploadFileOriginalNameWithOutSuffix, suffix};
    }

    /**
     * 获取WebRoot根路径
     *
     * @return
     */
    protected static String getWebRootAbsolutePath() {
        String path = null;
        String folderPath = FileUtil.class.getProtectionDomain().getCodeSource().getLocation().getPath();
        if (folderPath.startsWith("/")) {
            folderPath = folderPath.substring(1);
        }
        if (folderPath.indexOf("WEB-INF") > 0) {
            path = folderPath.substring(0, folderPath.indexOf("WEB-INF/classes"));
        }
        return path;
    }

    /**
     * 处理上传路径
     *
     * @param path     根路径
     * @param path     拼接路径
     * @param fileName 描述名称
     * @return
     */
    private static String handlerDir(String rootPath, String path, String fileName) throws Exception {
        if (null == path) {
            throw new Exception(String.format("“%s”配置为空", fileName));
        } else {
            File dir = new File(rootPath, path);
            if (!dir.exists()) {
                logger.warn(String.format("“%s”不存在:%s", fileName, path));
                if (!dir.mkdirs()) {
                    throw new Exception(String.format("“%s”创建失败:%s", fileName, path));
                } else {
                    logger.info(String.format("“%s”创建成功:%s", fileName, path));
                    return dir.getAbsolutePath();
                }
            } else if (!dir.isDirectory()) {
                throw new Exception(String.format("“%s”非路径:%s", fileName, path));
            } else if (!dir.canWrite()) {
                throw new Exception(String.format("“%s”不可写:%s", fileName, path));
            } else if (!dir.canRead()) {
                throw new Exception(String.format("“%s”不可读:%s", fileName, path));
            } else {
                return dir.getAbsolutePath();
            }
        }
    }

    /**
     * 路径拼接
     *
     * @param args
     * @return
     */
    public static String PathJoin(String... args) {
        StringBuilder sb = new StringBuilder();
        String temp = null;
        for (int i = 0, max = args.length; i < max; i += 1) {
            if (!StrUtil.isNullOrUndefined(args[i])) {
                temp = args[i].replaceAll(("/".equals(File.separator) ? "\\\\" : "/"),
                        ("/".equals(File.separator) ? "/" : "\\\\"));
                // 处理拼接中间的路径分隔符
                if (sb.length() > 0) {
                    if (temp.startsWith(File.separator) && sb.lastIndexOf(File.separator) == (sb.length() - 1)) {
                        temp = temp.substring(1);
                    } else if (!temp.startsWith(File.separator)
                            && sb.lastIndexOf(File.separator) != (sb.length() - 1)) {
                        temp = File.separator + temp;
                    }
                }

                /*
                 * if (i != max - 1 && !args[i+1].startsWith(File.separator)) { temp = temp +
                 * File.separator; }
                 */
                sb.append(temp);
                temp = null;
            }
        }
        return sb.toString();
    }
}

class FileUtilHolder {
    public static final FileUtil FileUtil = new FileUtil();
}