package com.test.news.domain.models.mapper;

import android.support.annotation.NonNull;
import android.text.TextUtils;

import com.test.news.data.entities.ArticleEntity;
import com.test.news.data.entities.SourceEntity;
import com.test.news.domain.models.Article;
import com.test.news.domain.models.Source;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class ModelMapper {

    public static Source transformSource(@NonNull SourceEntity entity) {
        Source model = new Source();
        model.setId(entity.getId());
        model.setTitle(entity.getName());
        model.setDescription(entity.getDescription());
        model.setCategory(entity.getCategory());
        model.setUrl(entity.getUrl());
        return model;
    }

    public static Article transformArticle(@NonNull ArticleEntity entity) throws ParseException {
        Article model = new Article();
        model.setTitle(entity.getTitle());
        model.setDescription(entity.getDescription());
        model.setImageUrl(entity.getUrlToImage());
        model.setAuthor(entity.getAuthor());
        model.setPublishedDate(timestampToDisplayable(entity.getPublishedAt()));
        return model;
    }

    private static String timestampToDisplayable(String timestamp) throws ParseException {
        if (TextUtils.isEmpty(timestamp)) {
            return "";
        }

        final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'",
                Locale.getDefault());
        final Date dateIn = dateFormat.parse(timestamp);
        dateFormat.applyPattern("dd/MM/yyyy");
        return dateFormat.format(dateIn);
    }

}
