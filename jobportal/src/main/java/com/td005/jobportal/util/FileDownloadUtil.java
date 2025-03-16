package com.td005.jobportal.util;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileDownloadUtil {
    private Path foundPath;

    public Resource getFileAsResource(String downloadDir , String fileName) throws IOException
    {
        Path path = Paths.get(downloadDir);
        Files.list(path).forEach(file -> {
            if(file.getFileName().toString().startsWith(fileName)){
                foundPath = file;
            }
        });
        if(foundPath != null)
        {
            return new UrlResource(foundPath.toUri());
        }


        return null;
    }
}
