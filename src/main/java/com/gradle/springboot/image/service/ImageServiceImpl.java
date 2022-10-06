package com.gradle.springboot.image.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.gradle.springboot.image.dao.ImageRepository;
import com.gradle.springboot.image.vo.ImageDetailDto;
import com.gradle.springboot.image.vo.ImageDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;

@Service("ImageService")
public class ImageServiceImpl implements ImageService {

    @Autowired
    ImageRepository imageDao;

    @Value("onefolder.location")
    private String FILE_PATH;

    /**
     * 파일 읽기
     * @return
     */
    @Transactional
    @Override
    public int readGalleryList() {
        String folderNm = "";
        String oriFileNm = ""; // 원본 파일 이름
        String extNm = ""; // 확장자 이름
        int result = 0;
        int successCnt = 0;
        int gallerySeq = 0;
        long lastTime= 0;

        File dir = new File(FILE_PATH);
        File[] filesArr = dir.listFiles();
        boolean titleBoolean = false;
        String pattern = "yyyyMMddHHmmss";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        // 작가 목록 조회
        List<ImageDto> authList = imageDao.selectGalleryAuthList();

        ImageDto idto = new ImageDto();
        try {
            for(File file : filesArr){
                titleBoolean = false;
                folderNm = file.getName();

                lastTime = file.lastModified();
                Date lastModifiedDate = new Date( lastTime );

                idto.setReg_date(simpleDateFormat.format(lastModifiedDate));
                idto.setAuthor(folderNm);
                File imgDir = new File(file.toString());
                File[] imgDirArr = imgDir.listFiles();

                for(ImageDto auth : authList){
                    if(folderNm.equals(auth.getAuthor())){
                        if(imgDirArr.length > auth.getCnt() || imgDirArr.length < auth.getCnt() ){
                            // 갤러리, 갤러리 디테일 삭제

                        } else {
                            titleBoolean = true;
                            continue;
                        }
                    }
                }

                if(titleBoolean){
                    continue;
                }

                gallerySeq = imageDao.selectGallerySeq();
                idto.setGallery_seq(gallerySeq);
                result = imageDao.insertGallery(idto);
                if(result < 1) {
                    continue;
                }

                try {
                    if(folderNm != null && !"".equals(folderNm)){
                        File dir2 = new File(file.toString());
                        File[] filesArr2 = dir2.listFiles();
                        int idx = 0;
                        for(File file2 : filesArr2){
                            ImageDetailDto idd = new ImageDetailDto();
                            oriFileNm = file2.getName(); // 파일명
                            extNm = oriFileNm.substring(oriFileNm.lastIndexOf(".")); // 확장자
                            idd.setFile_name(oriFileNm);
                            idd.setFile_path("/"+folderNm+"/");
                            idd.setFile_etc(extNm);
                            idd.setGallery_seq(gallerySeq);
                            idd.setPage_seq(idx++);
                            result = imageDao.insertGalleryDetail(idd);
                        }
                    } else {
                        continue;
                    }
                    successCnt += result;
                } catch (Exception e){
                    e.printStackTrace();
                }
                System.out.println(folderNm+"::clear");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return successCnt;
    }

    @Override
    public ImageDto selectGalleryList() {
        return imageDao.selectGalleryList();
    }

    // TODO 파라미터 변경예정
    @Override
    public Page<ImageDto> getPageList() {
        HashMap<String, String> paramMap = new HashMap<>();
        paramMap.put("SEARCH_KEYWORD", "cg");
        paramMap.put("ORDER", "AUTHOR");
        paramMap.put("ORDER_RN", "DESC");
        PageHelper.startPage(1, 20);
        return (Page<ImageDto>) imageDao.getPageList(paramMap);
    }
}
