package com.example.osanpo.Services;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.osanpo.Entities.Image;

@Service
public class ImageService {

    private static final String UPLOAD_DIR = "uploads"; // アップロード先のディレクトリ

    public Path saveImage(Image image) {
        MultipartFile file = image.getFile();

        if (file != null && !file.isEmpty()) {
            try {
                // アップロードディレクトリが存在しない場合、作成
                File uploadDir = new File(UPLOAD_DIR);
                if (!uploadDir.exists()) {
                    uploadDir.mkdirs();
                }
                // ファイル名と保存先パスを決定
                String filename = file.getOriginalFilename();
                Path filepath = Paths.get(UPLOAD_DIR, filename);

                // ファイルを保存
                Files.write(filepath, file.getBytes());

                return filepath;
            } catch (IOException e) {
                e.printStackTrace();
                // 必要に応じてエラーハンドリングを追加
            }
        }
        return null;
    }
}