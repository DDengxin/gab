package com.tengzhi.base.tools.file;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * 文件类型、ContentType映射表
 * @links:http://tools.jb51.net/table/http_content_type/
 * @author: gaodu
 * @date: 2020/8/24 12:38
 **/
public class FileContentTypes {
    public final static Map<String, String> contentTypes;

    static {
        HashMap<String, String> tmp = new HashMap<String, String>() {{
            //.*	application/octet-stream	二进制流，不知道下载文件类型
            put(".", "application/x-");
            put(".pdf", "application/pdf"); //PDF（Portable Document Format的简称，意为“便携式文件格式”）
            put(".ai", "application/postscript"); //PostScript（PS）是主要用于电子产业和桌面出版领域的一种页面描述语言和编程语言。
            put(".xml", "application/atom+xml"); //Atom feeds
            put(".js", "application/ecmascript"); //相当于application / javascript但是严格的处理规则
            put(".edi", "application/EDI-X12"); //EDI ANSI ASC X12数据
            put(".edi", "application/EDIFACT"); //EDI EDIFACT数据
            put(".json", "application/json"); //JSON（JavaScript Object Notation）
            put(".js", "application/javascript"); //ECMAScript / JavaScript（相当于application / ecmascript但是宽松的处理规则）
            put(".ogg", "application/ogg"); //Ogg, 视频文件格式
            put(".rdf", "application/rdf+xml"); //资源描述框架（Resource Description Framework，缩写 RDF），是万维网联盟（W3C）提出的一组标记语言的技术标准，以便更为丰富地描述和表达网络资源的内容与结构。
            put(".xml", "application/rss+xml"); //RSS（Really Simple Syndication, 简易信息聚合）是一种消息来源格式规范，用以聚合经常发布更新数据的网站，例如博客文章、新闻、音频或视频的网摘
            put(".xml", "application/soap+xml"); //简单对象访问协议（SOAP，全写为Simple Object Access Protocol）是交换数据的一种协议规范，使用在计算机网络Web服务（web service）中，交换带结构信息。
            put(".woff", "application/font-woff"); //Web开放字体格式（Web Open Font Format，简称WOFF）是一种网页所采用的字体格式标准。（推荐使用；使用application / x - font - woff直到它变为官方标准）
            put(".xhtml", "application/xhtml+xml"); //可扩展超文本标记语言（eXtensible HyperText Markup Language，XHTML），是一种标记语言，表现方式与超文本标记语言（HTML）类似，不过语法上更加严格。
            put(".xml", "application/xml"); //可扩展标记语言（英语：eXtensible Markup Language，简称:XML），是一种标记语言。
            put(".dtd", "application/xml-dtd"); //XML文件的文件型别定义（Document Type Definition）可以看成一个或者多个XML文件的模板，在这里可以定义XML文件中的元素、元素的属性、元素的排列方式、元素包含的内容等等。
            put(".xml", "application/xop+xml"); //二进制优化封装协议(Xmlbinary Optimized Packaging)
            put(".zip", "application/zip"); //ZIP压缩文件
            put(".gzip", "application/gzip"); //Gzip是若干种文件压缩程序的简称，通常指GNU计划的实现，此处的gzip代表GNU zip。
            put(".xls", "application/x-xls"); //XLS 就是 Microsoft Excel 工作表，是一种非常常用的电子表格格式。
            put(".001", "application/x-001");
            put(".301", "application/x-301");
            put(".906", "application/x-906");
            put(".a11", "application/x-a11");
            put(".awf", "application/vnd.adobe.workflow");
            put(".bmp", "application/x-bmp");
            put(".c4t", "application/x-c4t");
            put(".cal", "application/x-cals");
            put(".cdf", "application/x-netcdf");
            put(".cel", "application/x-cel");
            put(".cg4", "application/x-g4");
            put(".cit", "application/x-cit");
            put(".bot", "application/x-bot");
            put(".c90", "application/x-c90");
            put(".cat", "application/vnd.ms-pki.seccat");
            put(".cdr", "application/x-cdr");
            put(".cer", "application/x-x509-ca-cert");
            put(".cgm", "application/x-cgm");
            put(".cmx", "application/x-cmx");
            put(".crl", "application/pkix-crl");
            put(".csi", "application/x-csi");
            put(".cut", "application/x-cut");
            put(".dbm", "application/x-dbm");
            put(".cmp", "application/x-cmp");
            put(".cot", "application/x-cot");
            put(".crt", "application/x-x509-ca-cert");
            put(".dbf", "application/x-dbf");
            put(".dbx", "application/x-dbx");
            put(".dcx", "application/x-dcx");
            put(".dgn", "application/x-dgn");
            put(".dll", "application/x-msdownload");
            put(".dot", "application/msword");
            put(".der", "application/x-x509-ca-cert");
            put(".dib", "application/x-dib");
            put(".doc", "application/msword");
            put(".drw", "application/x-drw");
            put(".dwf", "application/x-dwf");
            put(".dxb", "application/x-dxb");
            put(".edn", "application/vnd.adobe.edn");
            put(".dwg", "application/x-dwg");
            put(".dxf", "application/x-dxf");
            put(".emf", "application/x-emf");
            put(".epi", "application/x-epi");
            put(".eps", "application/postscript");
            put(".exe", "application/x-msdownload");
            put(".fdf", "application/vnd.fdf");
            put(".eps", "application/x-ps");
            put(".etd", "application/x-ebx");
            put(".fif", "application/fractals");
            put(".frm", "application/x-frm");
            put(".gbr", "application/x-gbr");
            put(".g4", "application/x-g4");
            put(".gl2", "application/x-gl2");
            put(".hgl", "application/x-hgl");
            put(".hpg", "application/x-hpgl");
            put(".hqx", "application/mac-binhex40");
            put(".hta", "application/hta");
            put(".gp4", "application/x-gp4");
            put(".hmr", "application/x-hmr");
            put(".hpl", "application/x-hpl");
            put(".hrf", "application/x-hrf");
            put(".icb", "application/x-icb");
            put(".ico", "application/x-ico");
            put(".ig4", "application/x-g4");
            put(".iii", "application/x-iphone");
            put(".ins", "application/x-internet-signup");
            put(".iff", "application/x-iff");
            put(".igs", "application/x-igs");
            put(".img", "application/x-img");
            put(".isp", "application/x-internet-signup");
            put(".jpe", "application/x-jpe");
            put(".js", "application/x-javascript");
            put(".jpg", "application/x-jpg");
            put(".lar", "application/x-laplayer-reg");
            put(".latex", "application/x-latex");
            put(".lbm", "application/x-lbm");
            put(".ls", "application/x-javascript");
            put(".ltr", "application/x-ltr");
            put(".man", "application/x-troff-man");
            put(".mdb", "application/msaccess");
            put(".mac", "application/x-mac");
            put(".mdb", "application/x-mdb");
            put(".mfp", "application/x-shockwave-flash");
            put(".mi", "application/x-mi");
            put(".mil", "application/x-mil");
            put(".mocha", "application/x-javascript");
            put(".mpd", "application/vnd.ms-project");
            put(".mpp", "application/vnd.ms-project");
            put(".mpt", "application/vnd.ms-project");
            put(".mpw", "application/vnd.ms-project");
            put(".mpx", "application/vnd.ms-project");
            put(".mxp", "application/x-mmxp");
            put(".nrf", "application/x-nrf");
            put(".out", "application/x-out");
            put(".p12", "application/x-pkcs12");
            put(".p7c", "application/pkcs7-mime");
            put(".p7r", "application/x-pkcs7-certreqresp");
            put(".pc5", "application/x-pc5");
            put(".pcl", "application/x-pcl");
            put(".pdx", "application/vnd.adobe.pdx");
            put(".pgl", "application/x-pgl");
            put(".pko", "application/vnd.ms-pki.pko");
            put(".p10", "application/pkcs10");
            put(".p7b", "application/x-pkcs7-certificates");
            put(".p7m", "application/pkcs7-mime");
            put(".p7s", "application/pkcs7-signature");
            put(".pci", "application/x-pci");
            put(".pcx", "application/x-pcx");
            put(".pdf", "application/pdf");
            put(".pfx", "application/x-pkcs12");
            put(".pic", "application/x-pic");
            put(".pl", "application/x-perl");
            put(".plt", "application/x-plt");
            put(".png", "application/x-png");
            put(".ppa", "application/vnd.ms-powerpoint");
            put(".pps", "application/vnd.ms-powerpoint");
            put(".ppt", "application/x-ppt");
            put(".prf", "application/pics-rules");
            put(".prt", "application/x-prt");
            put(".ps", "application/postscript");
            put(".pwz", "application/vnd.ms-powerpoint");
            put(".ra", "audio/vnd.rn-realaudio"); //RealAudio是一种由RealNetworks发展的RealMedia多媒体音频文件格式，仅指RealPlayer中能够识别的音频文件，也可以理解为real格式的音频文件。
            put(".ras", "application/x-ras");
            put(".pot", "application/vnd.ms-powerpoint");
            put(".ppm", "application/x-ppm");
            put(".ppt", "application/vnd.ms-powerpoint");
            put(".pr", "application/x-pr");
            put(".prn", "application/x-prn");
            put(".ps", "application/x-ps");
            put(".ptn", "application/x-ptn");
            put(".red", "application/x-red");
            put(".rjs", "application/vnd.rn-realsystem-rjs");
            put(".rlc", "application/x-rlc");
            put(".rm", "application/vnd.rn-realmedia");
            put(".rat", "application/rat-file");
            put(".rec", "application/vnd.rn-recording");
            put(".rgb", "application/x-rgb");
            put(".rjt", "application/vnd.rn-realsystem-rjt");
            put(".rle", "application/x-rle");
            put(".rmf", "application/vnd.adobe.rmf");
            put(".rmj", "application/vnd.rn-realsystem-rmj");
            put(".rmp", "application/vnd.rn-rn_music_package");
            put(".rmvb", "application/vnd.rn-realmedia-vbr");
            put(".rnx", "application/vnd.rn-realplayer");
            put(".rpm", "audio/x-pn-realaudio-plugin");
            put(".rms", "application/vnd.rn-realmedia-secure");
            put(".rmx", "application/vnd.rn-realsystem-rmx");
            put(".rsml", "application/vnd.rn-rsml");
            put(".rtf", "application/msword");
            put(".rv", "video/vnd.rn-realvideo");
            put(".sat", "application/x-sat");
            put(".sdw", "application/x-sdw");
            put(".slb", "application/x-slb");
            put(".rtf", "application/x-rtf");
            put(".sam", "application/x-sam");
            put(".sdp", "application/sdp");
            put(".sit", "application/x-stuffit");
            put(".sld", "application/x-sld");
            put(".smi", "application/smil");
            put(".smk", "application/x-smk");
            put(".smil", "application/smil");
            put(".spc", "application/x-pkcs7-certificates");
            put(".spl", "application/futuresplash");
            put(".ssm", "application/streamingmedia");
            put(".stl", "application/vnd.ms-pki.stl");
            put(".sst", "application/vnd.ms-pki.certstore");
            put(".tdf", "application/x-tdf");
            put(".tga", "application/x-tga");
            put(".sty", "application/x-sty");
            put(".swf", "application/x-shockwave-flash");
            put(".tg4", "application/x-tg4");
            put(".tif", "application/x-tif");
            put(".vdx", "application/vnd.visio");
            put(".vpg", "application/x-vpeg005");
            put(".vsd", "application/x-vsd");
            put(".vst", "application/vnd.visio");
            put(".vsw", "application/vnd.visio");
            put(".vtx", "application/vnd.visio");
            put(".torrent", "application/x-bittorrent");
            put(".vda", "application/x-vda");
            put(".vsd", "application/vnd.visio");
            put(".vss", "application/vnd.visio");
            put(".vst", "application/x-vst");
            put(".vsx", "application/vnd.visio");
            put(".wb1", "application/x-wb1");
            put(".wb3", "application/x-wb3");
            put(".wiz", "application/msword");
            put(".wk4", "application/x-wk4");
            put(".wks", "application/x-wks");
            put(".wb2", "application/x-wb2");
            put(".wk3", "application/x-wk3");
            put(".wkq", "application/x-wkq");
            put(".wmf", "application/x-wmf");
            put(".wmd", "application/x-ms-wmd");
            put(".wp6", "application/x-wp6");
            put(".wpg", "application/x-wpg");
            put(".wq1", "application/x-wq1");
            put(".wri", "application/x-wri");
            put(".ws", "application/x-ws");
            put(".wmz", "application/x-ms-wmz");
            put(".wpd", "application/x-wpd");
            put(".wpl", "application/vnd.ms-wpl");
            put(".wr1", "application/x-wr1");
            put(".wrk", "application/x-wrk");
            put(".ws2", "application/x-ws");
            put(".xdp", "application/vnd.adobe.xdp");
            put(".xfd", "application/vnd.adobe.xfd");
            put(".xfdf", "application/vnd.adobe.xfdf");
            put(".xls", "application/vnd.ms-excel");
            put(".xwd", "application/x-xwd");
            put(".sis", "application/vnd.symbian.install");
            put(".x_t", "application/x-x_t");
            put(".apk", "application/vnd.android.package-archive");
            put(".x_b", "application/x-x_b");
            put(".sisx", "application/vnd.symbian.install");
            put(".ipa", "application/vnd.iphone");
            put(".xap", "application/x-silverlight-app");
            put(".xlw", "application/x-xlw");
            put(".xpl", "audio/scpls");
            put(".anv", "application/x-anv");
            put(".uin", "application/x-icq");
            put(".323", "text/h323");
            put(".biz", "text/xml");
            put(".cml", "text/xml");
            put(".asa", "text/asa");
            put(".asp", "text/asp");
            put(".css", "text/css"); //层叠样式表（英语：Cascading Style Sheets，简写CSS），又称串样式列表、层次结构式样式表文件，一种用来为结构化文档（如HTML文档或XML应用）添加样式（字体、间距和颜色等）的计算机语言，由W3C定义和维护。
            put(".csv", "text/csv"); //逗号分隔值（Comma - Separated Values，CSV，有时也称为字符分隔值，因为分隔字符也可以不是逗号），其文件以纯文本形式存储表格数据（数字和文本）。
            put(".dcd", "text/xml");
            put(".dtd", "text/xml");
            put(".ent", "text/xml");
            put(".fo", "text/xml");
            put(".htc", "text/x-component");
            put(".html", "text/html");
            put(".htx", "text/html");
            put(".htm", "text/html");
            put(".htt", "text/webviewhtml");
            put(".jsp", "text/html");
            put(".math", "text/xml");
            put(".mml", "text/xml");
            put(".mtx", "text/xml");
            put(".plg", "text/html");
            put(".rdf", "text/xml");
            put(".rt", "text/vnd.rn-realtext");
            put(".sol", "text/plain");
            put(".spp", "text/xml");
            put(".stm", "text/html");
            put(".svg", "text/xml");
            put(".tld", "text/xml");
            put(".txt", "text/plain"); //纯文字内容
            put(".uls", "text/iuls");
            put(".vml", "text/xml");
            put(".tsd", "text/xml");
            put(".vcf", "text/x-vcard");
            put(".vxml", "text/xml");
            put(".wml", "text/vnd.wap.wml");
            put(".wsdl", "text/xml");
            put(".wsc", "text/scriptlet");
            put(".xdr", "text/xml");
            put(".xql", "text/xml");
            put(".xsd", "text/xml");
            put(".xslt", "text/xml");
            put(".xml", "text/xml");
            put(".xq", "text/xml");
            put(".xquery", "text/xml");
            put(".xsl", "text/xml");
            put(".xhtml", "text/html");
            put(".odc", "text/x-ms-odc");
            put(".r3t", "text/vnd.rn-realtext3d");
            put(".sor", "text/plain");
            put(".acp", "audio/x-mei-aac");
            put(".aif", "audio/aiff");
            put(".aiff", "audio/aiff");
            put(".aifc", "audio/aiff");
            put(".au", "audio/basic");
            put(".la1", "audio/x-liquid-file");
            put(".lavs", "audio/x-liquid-secure");
            put(".lmsff", "audio/x-la-lms");
            put(".m3u", "audio/mpegurl");
            put(".midi", "audio/mid");
            put(".mid", "audio/mid");
            put(".mp2", "audio/mp2");
            put(".mp3", "audio/mp3");
            put(".mp4", "audio/mp4"); //MP4，全称MPEG - 4 Part 14，是一种使用MPEG - 4 的多媒体计算机文件格式，扩展名为.mp4，以存储数字音频及数字视频为主。
            put(".mnd", "audio/x-musicnet-download");
            put(".mp1", "audio/mp1");
            put(".mns", "audio/x-musicnet-stream");
            put(".mpga", "audio/rn-mpeg");
            put(".pls", "audio/scpls");
            put(".ram", "audio/x-pn-realaudio");
            put(".rmi", "audio/mid");
            put(".rmm", "audio/x-pn-realaudio");
            put(".snd", "audio/basic");
            put(".wav", "audio/wav");
            put(".wax", "audio/x-ms-wax");
            put(".wma", "audio/x-ms-wma");
            put(".asf", "video/x-ms-asf");
            put(".asx", "video/x-ms-asf");
            put(".avi", "video/avi");
            put(".IVF", "video/x-ivf");
            put(".m1v", "video/x-mpeg");
            put(".m2v", "video/x-mpeg");
            put(".m4e", "video/mpeg4");
            put(".movie", "video/x-sgi-movie");
            put(".mp2v", "video/mpeg");
            put(".mp4", "video/mpeg4"); //MP4，全称MPEG - 4 Part 14，是一种使用MPEG - 4 的多媒体计算机文件格式，扩展名为.mp4，以存储数字音频及数字视频为主。
            put(".mpa", "video/x-mpg");
            put(".mpe", "video/x-mpeg");
            put(".mpg", "video/mpg");
            put(".mpeg", "video/mpg");
            put(".mps", "video/x-mpeg");
            put(".mpv", "video/mpg");
            put(".mpv2", "video/mpeg");
            put(".wm", "video/x-ms-wm");
            put(".wmv", "video/x-ms-wmv");
            put(".wmx", "video/x-ms-wmx");
            put(".wvx", "video/x-ms-wvx");
            put(".tif", "image/tiff"); //标签图像文件格式（Tagged Image File Format，简写为TIFF）是一种主要用来存储包括照片和艺术图在内的图像的文件格式。它最初由Aldus公司与微软公司一起为PostScript打印开发。
            put(".fax", "image/fax");
            put(".gif", "image/gif"); //图像互换格式（GIF，Graphics Interchange Format）是一种位图图形文件格式，以8位色（即256种颜色）重现真彩色的图像。
            put(".ico", "image/x-icon");
            put(".jfif", "image/jpeg");
            put(".jpe", "image/jpeg");
            put(".jpeg", "image/jpeg"); //JPEG是一种针对相片图像而广泛使用的一种有损压缩标准方法。
            put(".jpg", "image/jpeg");
            put(".net", "image/pnetvue");
            put(".png", "image/png"); //便携式网络图形（Portable Network Graphics，PNG）是一种无损压缩的位图图形格式，支持索引、灰度、RGB三种颜色方案以及Alpha通道等特性。
            put(".rp", "image/vnd.rn-realpix");
            put(".tif", "image/tiff");
            put(".tiff", "image/tiff");
            put(".wbmp", "image/vnd.wap.wbmp");
            put(".eml", "message/rfc822");
            put(".mht", "message/rfc822");
            put(".mhtml", "message/rfc822");
            put(".nws", "message/rfc822");
            put(".907", "drawing/907");
            put(".slk", "drawing/x-slk");
            put(".top", "drawing/x-top");
            put(".class", "java/*");
            put(".java", "java/*");
            put(".dwf", "Model/vnd.dwf");
        }};
        contentTypes = Collections.unmodifiableMap(tmp);
    }

    /**
     * 获取文件的Content Type类型
     *
     * @param suffix 后缀名(可带.，也可不带点)
     * @return
     */
    public static String getContentType(String suffix) {
        suffix = suffix.startsWith(".") ? suffix : "." + suffix;
        if (contentTypes.containsKey(suffix)) {
            return contentTypes.get(suffix);
        } else {
            return "application/octet-stream"; //二进制流，不知道下载文件类型
        }
    }
}
