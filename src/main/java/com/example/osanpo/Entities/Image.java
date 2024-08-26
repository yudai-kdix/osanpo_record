package com.example.osanpo.Entities;

import org.springframework.web.multipart.MultipartFile;

public class Image {

    private MultipartFile file;

    // コンストラクタ、ゲッター、セッター
    public Image() {
    }

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }

    // 追加の処理が必要であればここにメソッドを追加
}