package com.test.news.presentation.sources;

import com.test.news.domain.models.Source;
import com.test.news.presentation.base.BaseContract;

import java.util.List;

public interface SourcesContract {

    interface View extends BaseContract.View {
        void setSources(List<Source> sources);
    }

    interface Presenter extends BaseContract.Presenter<View> {

    }
}
