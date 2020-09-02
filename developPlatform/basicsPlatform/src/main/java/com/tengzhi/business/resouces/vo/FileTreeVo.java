package com.tengzhi.business.resouces.vo;


import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FileTreeVo {
    public String name;
    public String fileName;
    public String suffix;

    public String parentPath;
    public String absolutePath;
    public String uri;

    public boolean isExists = false;
    public boolean isHidden = false;
    public boolean isFile = false;
    public boolean isDirectory = false;

    public List<FileTreeVo> children = new ArrayList<FileTreeVo>(0);

    /**
     * @param dir
     * @param suffixs
     * @return
     */
    public static FileTreeVo formFile( File dir, String[] suffixs) {
        String rootPath = "";
        try {
            rootPath = null != dir ? dir.getCanonicalPath() : rootPath;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return _formFile(dir, suffixs, rootPath);
    }

    /**
     * @param dir
     * @param suffixs
     * @return
     */
    private static FileTreeVo _formFile( File dir, String[] suffixs, String rootPath) {
        FileTreeVo fileTree = new FileTreeVo(dir);

        if (null == dir) {
            return null;
        } else if (dir.isDirectory()) {
            File[] files = dir.listFiles();
            for (int i = 0, max = files.length; i < max; i += 1) {
                FileTreeVo tmp = _formFile(files[i], suffixs, rootPath);
                if (null != tmp) {
                    fileTree.getChildren().add(tmp);
                }
            }
            return fileTree;
        } else {
            if (Arrays.asList(suffixs).contains(fileTree.getSuffix())) {
                FileTreeVo tmp = new FileTreeVo(dir);

                int index = tmp.getAbsolutePath().indexOf(rootPath);
                if (index >= 0) {
                    tmp.setUri(tmp.getAbsolutePath().replace(rootPath, ""));
                }

                return tmp;
            } else {
                return null;
            }
        }
    }

    /**
     * @param file
     */
    private FileTreeVo(File file) {
        setExists(!(null == file || !file.exists()));
        if (isExists()) {
            setName(file.getName());
            setHidden(file.isHidden());
            setFile(file.isFile());
            setDirectory(file.isDirectory());
            setParentPath(file.getParent());
            if (isFile()) {
                int suffixIndex = getName().lastIndexOf(".");
                setFileName(suffixIndex > 0 ? getName().substring(0, suffixIndex) : getName());
                if (suffixIndex > 0) {
                    setSuffix(getName().substring(suffixIndex));
                }
            }

            try {
                setAbsolutePath(file.getCanonicalPath());
            } catch (IOException e) {
                e.printStackTrace();
            }


        }
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getSuffix() {
        return suffix;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }

    public String getAbsolutePath() {
        return absolutePath;
    }

    public void setAbsolutePath(String absolutePath) {
        this.absolutePath = absolutePath;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public boolean isHidden() {
        return isHidden;
    }

    public void setHidden(boolean hidden) {
        isHidden = hidden;
    }

    public boolean isFile() {
        return isFile;
    }

    public void setFile(boolean file) {
        isFile = file;
    }

    public boolean isDirectory() {
        return isDirectory;
    }

    public void setDirectory(boolean directory) {
        isDirectory = directory;
    }

    public List<FileTreeVo> getChildren() {
        return children;
    }

    public void setChildren(List<FileTreeVo> children) {
        this.children = children;
    }

    public boolean isExists() {
        return isExists;
    }

    public void setExists(boolean exists) {
        isExists = exists;
    }

    public String getParentPath() {
        return parentPath;
    }

    public void setParentPath(String parentPath) {
        this.parentPath = parentPath;
    }
}
