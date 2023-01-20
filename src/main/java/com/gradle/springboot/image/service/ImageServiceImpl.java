package com.gradle.springboot.image.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.gradle.springboot.image.dao.ImageRepository;
import com.gradle.springboot.image.vo.ImageDetailDto;
import com.gradle.springboot.image.vo.ImageDto;
import com.gradle.springboot.image.vo.SearchDto;
import com.gradle.springboot.util.RandomUtil;
import com.gradle.springboot.util.vo.RandomVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

@Service("ImageService")
@Slf4j
public class ImageServiceImpl implements ImageService {

    @Autowired
    ImageRepository imageDao;

    @Value("${onefolder.location}")
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

        Objects.requireNonNull(FILE_PATH, "file path is not null");
        File dir = new File(FILE_PATH);
        File[] filesArr = dir.listFiles();
        boolean titleBoolean;
        String pattern = "yyyyMMddHHmmss";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);

        try {

            ImageDto imageDto = new ImageDto();
            // 작가 목록 조회
            List<ImageDto> authList = imageDao.selectGalleryAuthList();

            for(File file : filesArr){
                titleBoolean = false;
                folderNm = file.getName();
                lastTime = file.lastModified();
                Date lastModifiedDate = new Date( lastTime );
                imageDto.setReg_date(simpleDateFormat.format(lastModifiedDate));
                imageDto.setAuthor(folderNm);
                File imgDir = new File(file.toString());
                File[] imgDirArr = imgDir.listFiles();

                // 폴더명, 작가명과 비교하여 페이지수 차이가 날 경우 처리
                for(ImageDto auth : authList){
                    if(folderNm.equals(auth.getAuthor())){
                        if(imgDirArr.length > auth.getCnt() || imgDirArr.length < auth.getCnt() ){
                            // 갤러리, 갤러리 디테일 삭제
                            imageDao.deleteGalleryDetail(auth);
                            imageDao.deleteGallery(auth);
                        } else {
                            titleBoolean = true;
                        }
                    }
                }

                if(titleBoolean){
                    continue;
                }

                gallerySeq = imageDao.selectGallerySeq();
                imageDto.setGallery_seq(gallerySeq);
                result = imageDao.insertGallery(imageDto);

                if(result < 1) {
                    continue;
                }

                try {
                    // 폴더에 있는 내용 DB에 추가
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

                        log.info(folderNm+":cleared");
                    } else {
                        continue;
                    }
                    successCnt += result;
                } catch (Exception e){
                    e.printStackTrace();
                }
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

    @Override
    public Page<ImageDto> getPageList(SearchDto searchDto) {
        HashMap<String, String> paramMap = new HashMap<>();
        paramMap.put("ORDER", searchDto.getOrder());
        paramMap.put("ORDER_FLAG", searchDto.getOrderFlag());
        paramMap.put("SEARCH_KEYWORD", searchDto.getSearchKeyword());

        // orderRn을 위한 정렬 추가
        if("DESC".equals(searchDto.getOrderFlag())) {
            paramMap.put("ORDER_RN", "ASC");
        } else {
            paramMap.put("ORDER_RN", "DESC");
        }

        PageHelper.startPage(searchDto.getPage(), 60);
        return imageDao.getPageList(paramMap);
    }

    @Override
    public List<ImageDetailDto> selectGalleryDetail(int gallerySeq) {
        HashMap<String, Object> paramMap = new HashMap<>();
        paramMap.put("GALLERY_SEQ", gallerySeq);
        // 이미지 조회시 조회 수 추가
        imageDao.updateGalleryViewCnt(gallerySeq);
        return imageDao.selectGalleryDetail(paramMap);
    }

    /**
     * 무작위 갤러리 이미지 조회
     * @return
     */
    @Override
    public int selectGalleryDetailRandom() {
        RandomVo imageDto = imageDao.selectGalleryCnt();
        RandomUtil randomUtil = new RandomUtil();
        int randomResult = randomUtil.makeRandomInt(imageDto);
        return randomResult;
    }
}
