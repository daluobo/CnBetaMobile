package daluobo.cnbetamobile.base.view;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

import daluobo.cnbetamobile.base.arch.Resource;

public abstract class BasePageViewModel<T> extends ViewModel {
    protected final List<T> mData = new ArrayList<>();
    protected int mPage = 1;

    public LiveData<Resource<List<T>>> onRefresh() {
        mPage = 1;

        return loadPage(1);
    }

    public LiveData<Resource<List<T>>> onLoad() {
        return loadPage(mPage);
    }

    public void onPageLoad() {
        mPage++;
    }

    public int getPage() {
        return mPage;
    }

    public void setPage(int page) {
        mPage = page;
    }

    public List<T> getData() {
        return mData;
    }

    public abstract LiveData<Resource<List<T>>> loadPage(int page);
}
